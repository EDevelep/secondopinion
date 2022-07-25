package org.secondopinion.diagnosticcenter.service.impl;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.UUID;
import java.util.stream.Collectors;
import org.apache.commons.lang3.StringUtils;
import org.secondopinion.diagnosticcenter.dao.AddressDAO;
import org.secondopinion.diagnosticcenter.dao.DiagnosticcenterDAO;
import org.secondopinion.diagnosticcenter.dao.DiagnosticcenteraddressDAO;
import org.secondopinion.diagnosticcenter.dao.DiagnosticcenteruserDAO;
import org.secondopinion.diagnosticcenter.dao.PersonaldetailDAO;
import org.secondopinion.diagnosticcenter.dao.RegistrationDAO;
import org.secondopinion.diagnosticcenter.dao.RoleDAO;
import org.secondopinion.diagnosticcenter.dao.SubmenuDAO;
import org.secondopinion.diagnosticcenter.domain.BaseDiagnosticcenter;
import org.secondopinion.diagnosticcenter.domain.BaseDiagnosticcenteruser;
import org.secondopinion.diagnosticcenter.domain.BaseRegistration;
import org.secondopinion.diagnosticcenter.dto.Address;
import org.secondopinion.diagnosticcenter.dto.Diagnosticcenter;
import org.secondopinion.diagnosticcenter.dto.DiagnosticcenterSearchRequest;
import org.secondopinion.diagnosticcenter.dto.Diagnosticcenteraddress;
import org.secondopinion.diagnosticcenter.dto.Diagnosticcenteruser;
import org.secondopinion.diagnosticcenter.dto.Personaldetail;
import org.secondopinion.diagnosticcenter.dto.ProfileCompltedDTO;
import org.secondopinion.diagnosticcenter.dto.Registration;
import org.secondopinion.diagnosticcenter.dto.Role;
import org.secondopinion.diagnosticcenter.dto.SubmenuDTO;
import org.secondopinion.diagnosticcenter.service.IDiagnosticcenterService;
import org.secondopinion.diagnosticcenter.service.IDiagnosticcenteruserRegistrationService;
import org.secondopinion.diagnosticcenter.service.rest.DiagnosticcenterElasticSerchRestAPIService;
// import
// org.secondopinion.diagnosticcenter.service.rest.DiagnosticcenterElasticSerchRestAPIService;
import org.secondopinion.request.Response;
import org.secondopinion.utils.DateUtil;
import org.secondopinion.utils.EmailUtil;
import org.secondopinion.utils.EmailUtil.MailContentEnum;
import org.secondopinion.utils.MailProperties;
import org.secondopinion.utils.OtpUtil;
import org.secondopinion.utils.SendSms;
import org.secondopinion.utils.StringUtil;
import org.secondopinion.utils.UserHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class DiagnosticcenterServiceImpl implements IDiagnosticcenterService {

  @Autowired
  private IDiagnosticcenteruserRegistrationService diagnosticcenterUserRegistration;

  @Autowired
  private DiagnosticcenterElasticSerchRestAPIService diagnosticcenterElasticSerchRestAPIService;

  @Autowired
  private PersonaldetailDAO personaldetailDAO;

  @Autowired
  private DiagnosticcenterDAO diagnosticcenterDAO;

  @Autowired
  private RegistrationDAO registrationDAO;

  @Autowired
  private DiagnosticcenteruserDAO diagnosticcenteruserDAO;
  @Autowired
  private DiagnosticcenteraddressDAO diagnosticcenteraddressDAO;

  @Autowired
  private RoleDAO roleDAO;
  @Autowired
  private AddressDAO addressDAO;

  @Autowired
  private SubmenuDAO submenuDAO;
  @Autowired
  private MailProperties mailProperties;

  @Value("${emailVerificationLink}")
  private String emailVerificationLink;

  @Value("${loginLinkForUI}")
  private String loginLinkForUI;

  @Override
  @Transactional
  public void registerDiagnosticcenter(Diagnosticcenter diagnosticcenter) {

    Diagnosticcenteraddress primaryDataCenterAddress =
        diagnosticcenter.getPrimaryDataCenterAddress();
    Diagnosticcenteruser primaryDiagnosticcenteruser =
        diagnosticcenter.getPrimaryDiagnosticcenteruser();

    if (Objects.isNull(primaryDataCenterAddress) || Objects.isNull(primaryDiagnosticcenteruser)) {
      throw new IllegalArgumentException(
          "Objects [primaryDataCenterAddress, primaryDiagnosticcenteruser] can not be null.");
    }

    String emailId = primaryDiagnosticcenteruser.getEmailId();
    if (StringUtil.isNullOrEmpty(emailId)) {
      throw new IllegalArgumentException(
          " Please provide valid [" + BaseDiagnosticcenteruser.FIELD_emailId + "]");
    }

    Diagnosticcenter diagnosticcenterByEmail = diagnosticcenterDAO.readByEmailId(emailId);
    if (Objects.nonNull(diagnosticcenterByEmail)) {
      throw new IllegalArgumentException("The email id ["
          + diagnosticcenterByEmail.getPrimaryUserEmailId() + "] is already registered.");
    }

    diagnosticcenter.setPrimaryUserEmailId(emailId);
    diagnosticcenterDAO.save(diagnosticcenter);

    String verificationId = getVerificationId();
    String hashedVerificationId = UserHelper.getHashedPassWord(verificationId);
    Registration registration = Registration.build(diagnosticcenter, hashedVerificationId, null);
    registrationDAO.save(registration);
    // create primary data center address
    primaryDataCenterAddress.setDiagnosticcenterId(diagnosticcenter.getDiagnosticcenterId());
    createPrimaryDataCentre(primaryDataCenterAddress);
    // Creating admin diagnosticcenter user.
    primaryDiagnosticcenteruser
        .setDiagnosticCenterAddressId(primaryDataCenterAddress.getDiagnosticCenterAddressId());
    primaryDiagnosticcenteruser.setDiagnosticName(diagnosticcenter.getName());
    createAdminUser(primaryDiagnosticcenteruser);
    // update with priamry address id
    diagnosticcenter
        .setPrimaryDataCenterAddressId(primaryDataCenterAddress.getDiagnosticCenterAddressId());
    diagnosticcenter.setPrimaryUser(primaryDiagnosticcenteruser.getDiagnosticCenterUserId());
    String cellNumber = diagnosticcenter.getCellNumber();
    Integer otp = OtpUtil.otpToPhone(cellNumber, "Email Verification");
    registration.setEmailotp(otp);
    registration.setActive('N');
    registrationDAO.save(registration);
    diagnosticcenterDAO.save(diagnosticcenter);
  //  diagnosticcenterElasticSerchRestAPIService.diagnosticcenterElasticSerchSave1(diagnosticcenter);
    String name = diagnosticcenter.getName().toUpperCase();
    String classpathEmailTemplate = "classpath:mail-emailverification.html";
    Map<String, String> model = new HashMap<>();
    model.put(MailContentEnum.OTP.name(), String.valueOf(otp));
    model.put(MailContentEnum.NAME.name(), name);
    EmailUtil.sendEmailWithHtmlContent(getMailProperties(diagnosticcenter),
        "CureMetric Email Verification", classpathEmailTemplate, model);


  }

  private void createPrimaryDataCentre(Diagnosticcenteraddress primaryDataCenterAddress) {
    diagnosticcenteraddressDAO.save(primaryDataCenterAddress);
  }

  private void createAdminUser(Diagnosticcenteruser primaryDiagnosticcenteruser) {

    String superAdminRole = Role.RoleEnum.ADMIN.name();
    Role roles = roleDAO.getByRoleName(superAdminRole);
    if (Objects.isNull(roles)) {
      throw new IllegalArgumentException(superAdminRole + " role not found.");
    }
    if (Objects.isNull(roles.getActive()) || roles.getActive() == 'N') {
      throw new IllegalArgumentException(superAdminRole + " role not found.");
    }
    primaryDiagnosticcenteruser.setRoleId(roles.getRoleId());
    primaryDiagnosticcenteruser.setActive('Y');
    diagnosticcenterUserRegistration.addprimaryDiagnosticcenteruser(primaryDiagnosticcenteruser);
  }

  @Override
  @Transactional(readOnly = true)
  public Collection<Diagnosticcenter> fetchAllDiagnosticcenters() {
    return diagnosticcenterDAO.findAll();
  }

  @Override
  @Transactional(readOnly = true)
  public Diagnosticcenter fetchByDiagnosticcenterId(Long diagnosticcenterId) {
    if (Objects.isNull(diagnosticcenterId)) {
      throw new IllegalArgumentException(
          " Please provide valid [" + BaseDiagnosticcenter.FIELD_diagnosticcenterId + "]");
    }
    return diagnosticcenterDAO.readByDiagnosticcenterId(diagnosticcenterId);
  }

  @Override
  @Transactional(readOnly = true)
  public Diagnosticcenter fetchDiagnosticcenterByLicenseNumber(String licenseNumber) {
    return null;
    /*
     * if (Objects.isNull(licenseNumber)) { throw new
     * IllegalArgumentException(" Please provide valid [" + BaseDiagnosticcenter.FIELD_licenseNumber
     * + "]"); } return diagnosticcenterDAO.readByLicenceNumber(licenseNumber);
     */
  }

  @Override
  public Boolean isEmailExistOrNot(String email) {
    if (Objects.isNull(email) || email.isEmpty()) {
      throw new IllegalArgumentException(
          " Please provide valid [" + BaseDiagnosticcenter.FIELD_primaryUserEmailId + "]");
    }
    Diagnosticcenter Diagnosticcenter = diagnosticcenterDAO.readByEmailId(email);
    if (Diagnosticcenter != null) {
      return true;
    }
    return false;
  }

  @Override
  @Transactional(readOnly = true)
  public Response<List<Diagnosticcenteraddress>> getAddressesOfDiagnosticcenter(
      Long DiagnosticcenterId, Integer pageNum, Integer maxResults) {

    return diagnosticcenteraddressDAO.readAllAddressesOfDiagnosticcenter(DiagnosticcenterId,
        pageNum, maxResults);
  }

  @Override
  @Transactional
  public void saveAddressesOfDiagnosticcenter(Diagnosticcenteraddress address) {
    address.setActive('Y');
    diagnosticcenteraddressDAO.save(address);
  //  diagnosticcenterElasticSerchRestAPIService.diagnosticcenterElasticSerchSave1(address);
  }

  @Override
  @Transactional
  public void updateAddressesOfDiagnosticcenter(Diagnosticcenteraddress address) {
    diagnosticcenteraddressDAO.save(address);
  }

  @Override
  @Transactional
  public List<Diagnosticcenteraddress> getAllDiagnosticcenterBySearchCritieria(
      DiagnosticcenterSearchRequest diagnosticcenterSearchRequest) {
    return diagnosticcenteraddressDAO.findDiagnosticcenterByLocation(diagnosticcenterSearchRequest);
  }

  @Override
  @Transactional(readOnly = true)
  public Map<String, Object> getMyReports(Long diagnosticCenterAddressid) {
    return diagnosticcenterDAO.getMyReports(diagnosticCenterAddressid);
  }

  @Override
  @Transactional(readOnly = true)
  public Diagnosticcenteraddress getDiagnosticcenteraddressById(Long diagnoticcenteraddressId) {
    return diagnosticcenteraddressDAO.findById(diagnoticcenteraddressId);
  }

  private MailProperties getMailProperties(Diagnosticcenter digDiagnosticcenter) {
    MailProperties properties = mailProperties.clone();
    properties.addToRecipient(digDiagnosticcenter.getPrimaryUserEmailId());
    return properties;
  }

  @Override
  public void emailVerification(String emailid, Integer emailotp) {

    Registration registration =
        registrationDAO.findOneByProperty(BaseRegistration.FIELD_emailotp, emailotp);
    if (Objects.isNull(registration)) {
      throw new IllegalArgumentException("Email verification details not found.");
    }
    Diagnosticcenter diagnosticcenter =
        diagnosticcenterDAO.findById(registration.getDiagnosticcenterId());

    if (Objects.isNull(diagnosticcenter)) {
      throw new IllegalArgumentException(
          "Diagnosticcenter has not found with the requested email.");
    }
    String cellNumber = diagnosticcenter.getCellNumber();
    if (StringUtils.isEmpty(cellNumber)) {
      throw new IllegalArgumentException("Cellnumber can not be null");
    }
    registration.setEmailVerified('Y');
    registration.setActive('N');
    registration.setOtpVerifiedOn(DateUtil.getDate());
    registration.setPhoneNumberVerified('Y');
    registration.setEmailVerifiedOn(DateUtil.getDate());
    registrationDAO.save(registration);
    // we need to validate otp
    if (!registration.getEmailotp().equals(emailotp)) {
      throw new IllegalArgumentException("Invalid otp.");
    }
    Integer otp = OtpUtil.otpToPhone(cellNumber, "Phone Number Verification");
    registration.setOtp(otp);// send a text to phone
    registration.setEmailVerified('Y');
    registration.setEmailVerifiedOn(DateUtil.getDate());
    registration.setOtp(otp);
    registrationDAO.save(registration);
    // the below code will be removed once twilio has integrated successfully
    String name = diagnosticcenter.getName().toUpperCase();
    SendSms.sendSms(
        "is your CUREMETRIC OTP for mobile verification. The code will valid for 10 min. Do not share this OTP with anyone"
            + otp,
        cellNumber);
    String classpathEmailTemplate = "classpath:mail-phoneVerification.html";
    Map<String, String> model = new HashMap<>();
    model.put(MailContentEnum.OTP.name(), String.valueOf(otp));
    model.put(MailContentEnum.NAME.name(), name);
    EmailUtil.sendEmailWithHtmlContent(getMailProperties(diagnosticcenter),
        "[CureMetric] PhoneVerification", classpathEmailTemplate, model);
  }

  @Override
  public void emailVerificationForDiagnosticcenterUser(String emailid, Integer emailotp) {

    Registration registration =
        registrationDAO.findOneByProperty(BaseRegistration.FIELD_emailotp, emailotp);
    if (Objects.isNull(registration)) {
      throw new IllegalArgumentException("Email verification details not found.");
    }
    Diagnosticcenteruser diagnosticcenter =
        diagnosticcenteruserDAO.findOneByProperty(Diagnosticcenteruser.FIELD_diagnosticCenterUserId,
            registration.getDiagnosticCenterUserId());

    if (Objects.isNull(diagnosticcenter)) {
      throw new IllegalArgumentException(
          "Diagnosticcenter has not found with the requested email.");
    }
    String cellNumber = diagnosticcenter.getCellNumber();
    if (StringUtils.isEmpty(cellNumber)) {
      throw new IllegalArgumentException("Cellnumber can not be null");
    }
    registration.setEmailVerified('Y');
    registration.setActive('N');
    registration.setOtpVerifiedOn(DateUtil.getDate());
    registration.setPhoneNumberVerified('N');
    registration.setEmailVerifiedOn(DateUtil.getDate());
    registrationDAO.save(registration);
    // we need to validate otp
    if (!registration.getEmailotp().equals(emailotp)) {
      throw new IllegalArgumentException("Invalid otp.");
    }
    Integer otp = OtpUtil.otpToPhone(cellNumber, "Phone Number Verification");
    registration.setOtp(otp);// send a text to phone
    registration.setEmailVerified('Y');
    registration.setEmailVerifiedOn(DateUtil.getDate());
    registration.setOtp(otp);
    registrationDAO.save(registration);
    // the below code will be removed once twilio has integrated successfully
    String name = diagnosticcenter.getFirstName().toUpperCase();
    SendSms.sendSms(
        "is your CUREMETRIC OTP for mobile verification. The code will valid for 10 min. Do not share this OTP with anyone"
            + otp,
        cellNumber);
    String classpathEmailTemplate = "classpath:mail-phoneVerification.html";
    Map<String, String> model = new HashMap<>();
    model.put(MailContentEnum.OTP.name(), String.valueOf(otp));
    model.put(MailContentEnum.NAME.name(), name);
    EmailUtil.sendEmailWithHtmlContent(getMailProperties(diagnosticcenter),
        "CureMetric PhoneVerification", classpathEmailTemplate, model);
  }

  private MailProperties getMailProperties(Diagnosticcenteruser diagnosticcenter) {
    MailProperties properties = mailProperties.clone();
    properties.addToRecipient(diagnosticcenter.getEmailId());
    return properties;
  }

  @Override
  public void resetPassword(String emailId, String newPassword, Integer otp) {
    // TODO Auto-generated method stub

  }

  @Override
  public void resendOTPForResetPassword(String emailId, String resetPasswordLink) {
    // TODO Auto-generated method stub

  }

  @Override
  public void forgotPassword(String emailId, String resetPwdLink) {
    // TODO Auto-generated method stub

  }

  @Override
  public void requestEmailVerificationLink(String emailId) {
    // TODO Auto-generated method stub

  }

  @Override
  public void phoneverification(String cellNumber, Integer otp) {
    Diagnosticcenter diagnosticcenter =
        diagnosticcenterDAO.findOneByProperty(BaseDiagnosticcenter.FIELD_cellNumber, cellNumber);
    if (Objects.isNull(diagnosticcenter)) {
      throw new IllegalArgumentException("Phone verification details not found.");
    }
    Registration registration = registrationDAO.findOneByProperty(
        Registration.FIELD_diagnosticcenterId, diagnosticcenter.getDiagnosticcenterId());

    if (Objects.isNull(registration)) {
      throw new IllegalArgumentException("Registration  details Not Found");
    }
    if (!registration.getOtp().equals(otp)) {
      throw new IllegalArgumentException(" Invalid  Otp ");
    }
    // logic otp exp..

    /*
     * int time = 5; Date otptime = registration.getOtpVerifiedOn(); Date currenttime =
     * DateUtil.getDate(); Date diffrencetime = DateUtil.addMinutes(otptime, time); if
     * (currenttime.getTime() <= diffrencetime.getTime()) { throw new
     * IllegalArgumentException("Your otp will Expire"); }
     */
    registration.setPhoneNumberVerified('Y');
    registration.setPhoneNumberVerifiedOn(DateUtil.getDate());
    registration.setActive('Y');
    diagnosticcenter.setActive('Y');
    diagnosticcenterDAO.save(diagnosticcenter);
    registrationDAO.save(registration);
    String name = diagnosticcenter.getName().toUpperCase();
    Map<String, String> model = new HashMap<>();
    model.put(MailContentEnum.LINK.name(), "" + "/" + loginLinkForUI);
    model.put(MailContentEnum.NAME.name(), name);
    String classpathEmailTemplate = "classpath:mail-login.html";
    EmailUtil.sendEmailWithHtmlContent(getMailProperties(diagnosticcenter),
        "[CureMetric] Account activated ", classpathEmailTemplate, model);
  }

  @Override
  public void phoneverificationForDiagnosticcenterUser(String cellNumber, Integer otp) {
    Diagnosticcenteruser diagnosticcenter = diagnosticcenteruserDAO
        .findOneByProperty(Diagnosticcenteruser.FIELD_cellNumber, cellNumber);
    if (Objects.isNull(diagnosticcenter)) {
      throw new IllegalArgumentException("Phone verification details not found.");
    }
    Registration registration = registrationDAO.findOneByProperty(
        Registration.FIELD_diagnosticCenterUserId, diagnosticcenter.getDiagnosticCenterUserId());

    if (Objects.isNull(registration)) {
      throw new IllegalArgumentException("Registration  details Not Found");
    }
    if (!registration.getOtp().equals(otp)) {
      throw new IllegalArgumentException(" Invalid  Otp ");
    }
    // logic otp exp..

    /*
     * int time = 5; Date otptime = registration.getOtpVerifiedOn(); Date currenttime =
     * DateUtil.getDate(); Date diffrencetime = DateUtil.addMinutes(otptime, time); if
     * (currenttime.getTime() <= diffrencetime.getTime()) { throw new
     * IllegalArgumentException("Your otp will Expire"); }
     */
    registration.setPhoneNumberVerified('Y');
    registration.setPhoneNumberVerifiedOn(DateUtil.getDate());
    registration.setActive('Y');
    diagnosticcenter.setActive('Y');
    diagnosticcenteruserDAO.save(diagnosticcenter);
    registrationDAO.save(registration);
    String name = diagnosticcenter.getFirstName().toUpperCase();
    Map<String, String> model = new HashMap<>();
    model.put(MailContentEnum.LINK.name(), "" + "/" + loginLinkForUI);
    model.put(MailContentEnum.NAME.name(), name);
    String classpathEmailTemplate = "classpath:mail-login.html";
    EmailUtil.sendEmailWithHtmlContent(getMailProperties(diagnosticcenter),
        "[CureMetric] Account activated ", classpathEmailTemplate, model);
  }

  @Override
  public void requestOTPForPhone(String cellNumber) {
    Diagnosticcenter diagnosticcenter =
        diagnosticcenterDAO.findOneByProperty(BaseDiagnosticcenter.FIELD_cellNumber, cellNumber);

    if (Objects.isNull(diagnosticcenter)) {
      throw new IllegalArgumentException("Diagnosticcenter  not found");
    }
    Integer otp = OtpUtil.otpToPhone(diagnosticcenter.getCellNumber(), "Resend Otp");// send a text
                                                                                     // to phone
                                                                                     // number
    Registration registration = registrationDAO.findOneByProperty(
        BaseRegistration.FIELD_diagnosticcenterId, diagnosticcenter.getDiagnosticcenterId());
    if (Objects.isNull(registration)) {
      String verificationId = getVerificationId();
      String hashedVerificationId = UserHelper.getHashedPassWord(verificationId);
      registration = Registration.build(diagnosticcenter, hashedVerificationId, otp);
      emailVerificationMailSend(diagnosticcenter, hashedVerificationId);
    }

    // send a text to phone
    registration.setOtp(otp);
    registration.setOtpVerifiedOn(DateUtil.getDate());
    registrationDAO.save(registration);
    SendSms.sendSms("Hii Welcome To CureMetric your new OTP is .." + otp, cellNumber);
    registration.setOtp(otp);
    registration.setPhoneNumberVerified('Y');
    registration.setPhoneNumberVerifiedOn(null);
    registration.setActive('N');
    registrationDAO.save(registration);

  }

  private void emailVerificationMailSend(Diagnosticcenter diagnosticcenter, String verificationId) {
    // will send email verification link
    if (StringUtil.isNullOrEmpty(diagnosticcenter.getUiHostURL())) {
      throw new IllegalArgumentException("uiHostURL field can not be null.");
    }
    String name = diagnosticcenter.getName();
    Map<String, String> model = new HashMap<>();
    model.put(MailContentEnum.LINK.name(),
        String.format(emailVerificationLink, verificationId, diagnosticcenter.getUiHostURL()));
    model.put(MailContentEnum.NAME.name(), name);

    String classpathEmailTemplate = "classpath:mail-emailverification.html";
    EmailUtil.sendEmailWithHtmlContent(getMailProperties(diagnosticcenter),
        "[CureMetric] Email Verification", classpathEmailTemplate, model);
  }

  private String getVerificationId() {
    return UUID.randomUUID().toString();
  }

  @Override
  public void requestOTPForEmail(String email) {
    Diagnosticcenter diagnosticcenter =
        diagnosticcenterDAO.findOneByProperty(BaseDiagnosticcenter.FIELD_primaryUserEmailId, email);

    if (Objects.isNull(diagnosticcenter)) {
      throw new IllegalArgumentException("Diagnosticcenter  not found");
    }
    String cellNumber = diagnosticcenter.getCellNumber();
    if (Objects.isNull(cellNumber)) {
      throw new IllegalArgumentException("cellNumber can not be null");
    }

    Integer otp = OtpUtil.otpToPhone(diagnosticcenter.getCellNumber(), "Email Verification");// send
                                                                                             // a
                                                                                             // text
                                                                                             // to
                                                                                             // phone
                                                                                             // number
    Registration registration = registrationDAO.findOneByProperty(
        BaseRegistration.FIELD_diagnosticcenterId, diagnosticcenter.getDiagnosticcenterId());
    if (Objects.isNull(registration)) {
      String verificationId = getVerificationId();
      String hashedVerificationId = UserHelper.getHashedPassWord(verificationId);
      registration = Registration.build(diagnosticcenter, hashedVerificationId, otp);
      emailVerificationMailSend(diagnosticcenter, hashedVerificationId);
    }

    registration.setEmailVerified('Y');
    registration.setEmailVerifiedOn(DateUtil.getDate());
    registration.setEmailotp(otp);
    registration.setOtpVerifiedOn(DateUtil.getDate());
    registrationDAO.save(registration);
    String name = diagnosticcenter.getName().toUpperCase();
    String classpathEmailTemplate = "classpath:mail-emailverification.html";
    Map<String, String> model = new HashMap<>();
    model.put(MailContentEnum.OTP.name(), String.valueOf(otp));
    model.put(MailContentEnum.NAME.name(), name);
    EmailUtil.sendEmailWithHtmlContent(getMailProperties(diagnosticcenter),
        "[CureMetric]  Resend Email Otp", classpathEmailTemplate, model);

  }

  @Override
  public void requestOTPForEmailDiagnosticcenterUser(String email) {
    Diagnosticcenteruser diagnosticcenter =
        diagnosticcenteruserDAO.findOneByProperty(Diagnosticcenteruser.FIELD_emailId, email);

    if (Objects.isNull(diagnosticcenter)) {
      throw new IllegalArgumentException("Diagnosticcenter  not found");
    }
    String cellNumber = diagnosticcenter.getCellNumber();
    if (Objects.isNull(cellNumber)) {
      throw new IllegalArgumentException("cellNumber can not be null");
    }

    Integer otp = OtpUtil.otpToPhone(diagnosticcenter.getCellNumber(), "Email Verification");// send
                                                                                             // a
                                                                                             // text
                                                                                             // to
                                                                                             // phone
                                                                                             // number
    Registration registration =
        registrationDAO.findOneByProperty(BaseRegistration.FIELD_diagnosticCenterUserId,
            diagnosticcenter.getDiagnosticCenterUserId());
    if (Objects.isNull(registration)) {
      String verificationId = getVerificationId();
      String hashedVerificationId = UserHelper.getHashedPassWord(verificationId);
      // registration = Registration.build(diagnosticcenter, hashedVerificationId, otp);
      // emailVerificationMailSend(diagnosticcenter, hashedVerificationId);
    }

    registration.setEmailVerified('Y');
    registration.setEmailVerifiedOn(DateUtil.getDate());
    registration.setEmailotp(otp);
    registration.setOtpVerifiedOn(DateUtil.getDate());
    registrationDAO.save(registration);
    String name = diagnosticcenter.getFirstName().toUpperCase();
    String classpathEmailTemplate = "classpath:mail-emailverification.html";
    Map<String, String> model = new HashMap<>();
    model.put(MailContentEnum.OTP.name(), String.valueOf(otp));
    model.put(MailContentEnum.NAME.name(), name);
    EmailUtil.sendEmailWithHtmlContent(getMailProperties(diagnosticcenter),
        "CureMetric  Resend Email Otp", classpathEmailTemplate, model);

  }

  @Override
  public void resendOTPForEmail(String email) {
    Diagnosticcenter diagnosticcenter =
        diagnosticcenterDAO.findOneByProperty(BaseDiagnosticcenter.FIELD_primaryUserEmailId, email);

    if (Objects.isNull(diagnosticcenter)) {
      throw new IllegalArgumentException("Diagnosticcenter  not found");
    }
    String cellNumber = diagnosticcenter.getCellNumber();
    if (Objects.isNull(cellNumber)) {
      throw new IllegalArgumentException("cellNumber can not be null");
    }

    Integer otp = OtpUtil.otpToPhone(diagnosticcenter.getCellNumber(), "Resend Eamil");// send a
                                                                                       // text to
                                                                                       // phone
                                                                                       // number
    Registration registration = registrationDAO.findOneByProperty(
        BaseRegistration.FIELD_diagnosticcenterId, diagnosticcenter.getDiagnosticcenterId());
    registration.setEmailotp(otp);
    registrationDAO.save(registration);
    String name = diagnosticcenter.getName().toUpperCase();
    String classpathEmailTemplate = "classpath:mail-emailverification.html";
    Map<String, String> model = new HashMap<>();
    model.put(MailContentEnum.OTP.name(), String.valueOf(otp));
    model.put(MailContentEnum.NAME.name(), name);
    EmailUtil.sendEmailWithHtmlContent(getMailProperties(diagnosticcenter),
        "[CureMetric]  Resend Email Otp", classpathEmailTemplate, model);

  }

  @Override
  @Transactional
  public Boolean isPhoneExistOrNot(String phonenumber) {
    if (Objects.isNull(phonenumber) || phonenumber.isEmpty()) {
      throw new IllegalArgumentException(
          " Please provide valid [" + BaseDiagnosticcenter.FIELD_cellNumber + "]");
    }
    Diagnosticcenter diagnosticcenter =
        diagnosticcenterDAO.findOneByProperty(Diagnosticcenter.FIELD_cellNumber, phonenumber);
    if (diagnosticcenter != null) {
      return true;
    }
    return false;
  }

  @Override
  public List<Diagnosticcenter> getAllDiagnosticcenterBySearchCritieria(List<String> serviceName) {
    List<SubmenuDTO> submenus = submenuDAO.getAllDiagnosticcenterIdBySearchCritieria(serviceName);
    if(submenus.isEmpty()) {
    	return new ArrayList<>();
    }
    List<Long> diagnosticcenterId = submenus.stream().map(SubmenuDTO::getDiagnosticCenterAddressId)
        .map(BigInteger::longValue).collect(Collectors.toList());
    Double amount = submenus.stream().mapToDouble(SubmenuDTO::getPrice).sum();
    
    List<Diagnosticcenter> diagnosticcenters =
        diagnosticcenterDAO.getAllDiagnosticcenterBySearchCritieria(diagnosticcenterId);
    diagnosticcenters.stream().forEach(n -> n.setAmount(amount));
    return diagnosticcenters;
  }

  @Override
  @Transactional
  public void savePersonaldetail(Personaldetail personaldetail) {
    personaldetail.setActive('Y');
    personaldetailDAO.save(personaldetail);
  }

  @Override
  @Transactional
  public Personaldetail findPersonalDetailBydiagnosticcenterId(Long diagnosticcenterId) {

    return personaldetailDAO.findPersonalDetailBydiagnosticcenterId(diagnosticcenterId);
  }

  @Override
  @Transactional
  public String otpverification(Integer otp, String emailId) {
    Diagnosticcenter diagnosticcenter =
        diagnosticcenterDAO.findOneByProperty(Diagnosticcenter.FIELD_primaryUserEmailId, emailId);

    Registration registration = registrationDAO.findOneByProperty(
        Registration.FIELD_diagnosticcenterId, diagnosticcenter.getDiagnosticcenterId());
    if (!registration.getEmailotp().equals(otp)) {
      throw new IllegalArgumentException("Invalid Otp");
    }
    return "OTP  verification is success";

  }

  @Override
  @Transactional
  public List<Diagnosticcenter> findDiagnosticcenterByLocation(
      DiagnosticcenterSearchRequest diagnosticcenterSearchRequest) {
    List<Diagnosticcenteraddress> diagnosticcenteraddresses =
        diagnosticcenteraddressDAO.findDiagnosticcenterByLocation(diagnosticcenterSearchRequest);

    if (io.jsonwebtoken.lang.Collections.isEmpty(diagnosticcenteraddresses)) {
      throw new IllegalArgumentException("No  Diagnosticcenter Register For This location");
    }
    List<Long> diagnosticcenterId = diagnosticcenteraddresses.stream()
        .map(Diagnosticcenteraddress::getDiagnosticCenterAddressId).collect(Collectors.toList());
    List<Diagnosticcenter> diagnosticcenters =
        diagnosticcenterDAO.getAllDiagnosticcenterBySearchCritieria(diagnosticcenterId);
    if (io.jsonwebtoken.lang.Collections.isEmpty(diagnosticcenters)) {
      throw new IllegalArgumentException("No  Diagnosticcenter Register For This location");
    }
    return diagnosticcenters;
  }

  @Override
  @Transactional
  public ProfileCompltedDTO isProfileCompleted(Long diagnoticCenterId) {
    ProfileCompltedDTO profileCompltedDTO = new ProfileCompltedDTO();
    Diagnosticcenter diagnosticcenter = diagnosticcenterDAO.findById(diagnoticCenterId);
    if (Objects.isNull(diagnosticcenter)) {
      throw new IllegalArgumentException("No  Diagnosticcenter Register For This Id");
    }

    Address address = addressDAO.findOneByProperty(Address.FIELD_diagnosticCenterUserId,
        diagnosticcenter.getPrimaryUser());
    if (Objects.isNull(address)) {
      profileCompltedDTO.setIsAddressCompleted('N');
      profileCompltedDTO.setIsPersonalDetailComplted('N');
    }

    Personaldetail personaldetail = personaldetailDAO.findOneByProperty(
        Personaldetail.FIELD_diagnosticcenteruserId, diagnosticcenter.getPrimaryUser());
    if (Objects.isNull(personaldetail)) {
      profileCompltedDTO.setIsAddressCompleted('Y');
      profileCompltedDTO.setIsPersonalDetailComplted('N');
    }
    profileCompltedDTO.setIsAddressCompleted('Y');
    profileCompltedDTO.setIsPersonalDetailComplted('Y');
    return profileCompltedDTO;
  }

@Override
@Transactional
public List<Diagnosticcenter> getAssoctedDiagnosticCenter(Long diagnosticCenteradminId) {
	List<Diagnosticcenter> diagnosticcenters=diagnosticcenterDAO.getAssoctedDiagnosticCenter(diagnosticCenteradminId);
	return diagnosticcenters;
}
}
