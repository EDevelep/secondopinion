package org.secondopinion.pharmacy.service.impl;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.UUID;
import java.util.stream.Collectors;
import org.secondopinion.enums.PrimaryContactEnum;
import org.secondopinion.pharmacy.dao.PharmacyDAO;
import org.secondopinion.pharmacy.dao.PharmacyaddressDAO;
import org.secondopinion.pharmacy.dao.PharmacyuserDAO;
import org.secondopinion.pharmacy.dao.RegistrationDAO;
import org.secondopinion.pharmacy.dao.RolesDAO;
import org.secondopinion.pharmacy.dao.UserroleDAO;
import org.secondopinion.pharmacy.domain.BasePharmacyuser;
import org.secondopinion.pharmacy.domain.BaseRegistration;
import org.secondopinion.pharmacy.dto.Pharmacy;
import org.secondopinion.pharmacy.dto.PharmacyUserUpadteDTO;
import org.secondopinion.pharmacy.dto.Pharmacyuser;
import org.secondopinion.pharmacy.dto.Registration;
import org.secondopinion.pharmacy.dto.Roles;
import org.secondopinion.pharmacy.dto.UpdatePasswordRequest;
import org.secondopinion.pharmacy.dto.Userrole;
import org.secondopinion.pharmacy.service.IPharmacyuserRegistrationService;
import org.secondopinion.request.Response;
import org.secondopinion.utils.EmailUtil;
import org.secondopinion.utils.EmailUtil.MailContentEnum;
import org.secondopinion.utils.MailProperties;
import org.secondopinion.utils.OtpUtil;
import org.secondopinion.utils.StringUtil;
import org.secondopinion.utils.UserHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

@Service
public class PharmacyuserRegistrationServiceImpl implements IPharmacyuserRegistrationService {

  @Value("${createPwdLinkUI}")
  private String createPwdLinkUI;

  @Autowired
  private PharmacyuserDAO pharmacyuserDAO;

  @Autowired
  private RegistrationDAO registrationDAO;
  @Autowired
  private PharmacyDAO pharmacyDAO;
  @Value("${emailVerificationLink}")
  private String emailVerificationLink;

  @Value("${loginLinkForUI}")
  private String loginLinkForUI;

  @Autowired
  private RolesDAO rolesDAO;

  @Autowired
  private UserroleDAO userRoleDAO;
  @Autowired
  private MailProperties mailProperties;

  @Autowired
  private PharmacyaddressDAO addressDAO;

  @Override
  @Transactional
  public Pharmacyuser addPharmacyuser(Pharmacyuser pharmacyuser, boolean createFromPharmacy,
      List<String> roleNames) {
    if (!roleNames.contains(Roles.PharmacyRolesEnum.ADMIN.name())) {
      throw new IllegalArgumentException("You are not authorized person to create a new user.");
    }

    savePharmacyuser(pharmacyuser);
    Pharmacy pharmacy = pharmacyDAO.findOneByProperty(Pharmacy.FIELD_pharmacyaddressId,
        pharmacyuser.getPharmacyaddressId());
    if (Objects.isNull(pharmacy)) {
      throw new IllegalArgumentException("pharmacy address not found.");
    }
    Registration registration = Registration.buildPharmacyuser(pharmacyuser,
        pharmacyuser.getPharmacyUserId(), pharmacy.getPharmacyId());
    registrationDAO.save(registration);

    emailSendToChangePassword(pharmacyuser);
    return pharmacyuser;
  }

  @SuppressWarnings("unused")
  private MailProperties getMailProperties(Pharmacy pharmacy) {
    MailProperties properties = mailProperties.clone();
    properties.addToRecipient(pharmacy.getEmailId());
    return properties;
  }

  private void savePharmacyuser(Pharmacyuser pharmacyuser) {
    if (StringUtil.isNullOrEmpty(pharmacyuser.getCellNumber())) {
      throw new IllegalArgumentException("Cell Number can not be null.");
    }
    List<String> pcEnums = Arrays.stream(PrimaryContactEnum.values()).map(PrimaryContactEnum::name)
        .collect(Collectors.toList());
    if (StringUtil.isNullOrEmpty(pharmacyuser.getPrimaryContact())
        || !pcEnums.contains(pharmacyuser.getPrimaryContact())) {
      throw new IllegalArgumentException(
          "primary contact either can not be null or it should be in " + pcEnums);
    }
    String password = pharmacyuser.getPassword();

    if (!StringUtil.isNullOrEmpty(password)) {
      UserHelper.passwordValidation(password);
    } else {
      pharmacyuser.setPassword(UserHelper.generateRandomPassword(10));
    }
    Pharmacyuser dbPharmacyuser = pharmacyuserDAO.getByPharmacyAddressIdAndEmailId(
        pharmacyuser.getPharmacyaddressId(), pharmacyuser.getEmailId(), Boolean.TRUE);
    if (Objects.nonNull(dbPharmacyuser)) {
      throw new IllegalArgumentException("The email id already exists for this pharmacy.");
    }
    dbPharmacyuser = pharmacyuserDAO.getByPharmacyAddressIdAndEmailId(
        pharmacyuser.getPharmacyaddressId(), pharmacyuser.getEmailId(), Boolean.FALSE);
    if (Objects.nonNull(dbPharmacyuser)) {
      throw new IllegalArgumentException("The user is already register with the other pharmacy.");
    }
    pharmacyuser.setPassword(UserHelper.getHashedPassWord(pharmacyuser.getPassword()));
    String verificationId = getVerificationId();
    String hashedVerificationId = UserHelper.getHashedPassWord(verificationId);
    pharmacyuser.setVerificationId(hashedVerificationId);
    pharmacyuser.setActive('Y');
    pharmacyuserDAO.save(pharmacyuser);
    saveUserrole(pharmacyuser);

  }

  private void saveUserrole(Pharmacyuser pharmacyuser) {
    List<Userrole> userroles = userRoleDAO.getByRoleIdAndUserId(pharmacyuser.getRoleId(),
        pharmacyuser.getPharmacyUserId());
    if (CollectionUtils.isEmpty(userroles)) {
      Userrole userrole = new Userrole();
      userrole.setRoleId(pharmacyuser.getRoleId());
      userrole.setPharmacyUserId(pharmacyuser.getPharmacyUserId());
      userrole.setActive('Y');
      userRoleDAO.save(userrole);
    }
  }

  private void emailSendToChangePassword(Pharmacyuser pharmacyuser) {
    if (StringUtil.isNullOrEmpty(pharmacyuser.getUiHostURL())) {
      throw new IllegalArgumentException("uiHostURL field can not benull.");
    }
    String name = pharmacyuser.getFirstName() + " " + pharmacyuser.getLastName();
    Map<String, String> model = new HashMap<>();
    model.put(MailContentEnum.LINK.name(), String.format(
        pharmacyuser.getUiHostURL() + "/" + createPwdLinkUI, pharmacyuser.getPharmacyUserId()));
    model.put(MailContentEnum.NAME.name(), name);

    String classpathEmailTemplate = "classpath:mail-resetpwd.html";

    EmailUtil.sendEmailWithHtmlContent(getMailProperties(pharmacyuser), "curemetric Reset password",
        classpathEmailTemplate, model);
  }

  @Override
  @Transactional(readOnly = true)
  public Pharmacyuser getByPharmacyAddressIdAndEmailId(Long pharmacyAddressId, String emailId) {

    return pharmacyuserDAO.getByPharmacyAddressIdAndEmailId(pharmacyAddressId, emailId, true);
  }

  @Override
  @Transactional()
  public Pharmacyuser getPharmacyuserByVerificationId(String verificationId,
      String passwordTypeEnum) {
    Pharmacyuser pharmacyuser =
        pharmacyuserDAO.findOneByProperty(BasePharmacyuser.FIELD_verificationId, verificationId);

    if (Objects.isNull(pharmacyuser)) {
      throw new IllegalArgumentException("The pharmacy user has not been registered");
    }
    if (!StringUtil.isNullOrEmpty(passwordTypeEnum)) {
      Integer otp = OtpUtil.otpToPhone(pharmacyuser.getCellNumber(), passwordTypeEnum);// send a
                                                                                       // text to
                                                                                       // phone
      pharmacyuser.setEmailotp(otp);
      pharmacyuserDAO.save(pharmacyuser);
      String name = pharmacyuser.getFirstName() + " " + pharmacyuser.getLastName();
      Map<String, String> model = new HashMap<>();
      model.put(MailContentEnum.NAME.name(), name);
      model.put(MailContentEnum.OTP.name(), String.valueOf(otp));

      String classpathEmailTemplate = "classpath:mail-phoneVerification.html";

      EmailUtil.sendEmailWithHtmlContent(getMailProperties(pharmacyuser),
          "[curemetric] Create password - OTP", classpathEmailTemplate, model);
    }
    return pharmacyuser;
  }

  private String getVerificationId() {
    return UUID.randomUUID().toString();
  }

  private MailProperties getMailProperties(Pharmacyuser pharmacyuser) {
    MailProperties properties = mailProperties.clone();
    properties.addToRecipient(pharmacyuser.getEmailId());
    return properties;
  }

  @Override
  @Transactional
  public void resetPasswordByVerificationId(UpdatePasswordRequest updatePasswordRequest) {
    UserHelper.passwordValidation(updatePasswordRequest.getNewPassword());
    Pharmacyuser pharmacyuser = pharmacyuserDAO.findOneByProperty(
        BasePharmacyuser.FIELD_verificationId, updatePasswordRequest.getVerificationid());
    updatePassword(pharmacyuser, updatePasswordRequest.getNewPassword(),
        updatePasswordRequest.getOtp());
  }

  @Override
  @Transactional
  public void resetPasswordByPharmacyUserId(UpdatePasswordRequest updatePasswordRequest) {
    UserHelper.passwordValidation(updatePasswordRequest.getNewPassword());
    Pharmacyuser pharmacyuser = pharmacyuserDAO.findOneByProperty(Pharmacyuser.FIELD_emailId,
        updatePasswordRequest.getEmailId());

    updatePassword(pharmacyuser, updatePasswordRequest.getNewPassword(),
        updatePasswordRequest.getOtp());

  }



  @Override
  @Transactional
  public void resetPasswordForPharmacyUserNew(UpdatePasswordRequest updatePasswordRequest) {
    UserHelper.passwordValidation(updatePasswordRequest.getNewPassword());
    Pharmacyuser pharmacyuser = pharmacyuserDAO.findOneByProperty(Pharmacyuser.FIELD_emailId,
        updatePasswordRequest.getEmailId());

    Registration registration = registrationDAO
        .findOneByProperty(BaseRegistration.FIELD_pharmacyUserId, pharmacyuser.getPharmacyUserId());

    if (!registration.getEmailotp().equals(updatePasswordRequest.getOtp())) {
      throw new IllegalArgumentException("Invalid otp");
    }
    pharmacyuser.setPassword(UserHelper.getHashedPassWord(updatePasswordRequest.getNewPassword()));
    registration.setActive('Y');
    registrationDAO.save(registration);
    pharmacyuser.setActive('Y');
    pharmacyuserDAO.save(pharmacyuser);



  }


  private void updatePassword(Pharmacyuser pharmacyuser, String newPassword, Integer otp) {
    if (Objects.isNull(pharmacyuser)) {
      throw new IllegalArgumentException("The User has not been registered");
    }

    Pharmacy pharmacy =
        pharmacyDAO.findOneByProperty(Pharmacy.FIELD_primaryUser, pharmacyuser.getPharmacyUserId());

    Registration registration = registrationDAO.findOneByProperty(BaseRegistration.FIELD_pharmacyId,
        pharmacy.getPharmacyId());

    if (!registration.getEmailotp().equals(otp)) {
      throw new IllegalArgumentException("Invalid otp");
    }

    pharmacyuser.setPassword(UserHelper.getHashedPassWord(newPassword));

    pharmacyuserDAO.save(pharmacyuser);
  }

  @Override
  @Transactional
  public void forgotPassword(String emailId, String resetPwdLink) {
    Pharmacyuser user = pharmacyuserDAO.getByPharmacyAddressIdAndEmailId(null, emailId, false);
    if (Objects.isNull(user)) {
      throw new IllegalArgumentException("The pharmacy user Email has not been registered");
    }

    Integer otp = OtpUtil.otpToPhone(user.getCellNumber(), "Forgot Password");// send a text to
                                                                              // phone number
    String name = user.getFirstName() + " " + user.getLastName();
    Map<String, String> model = new HashMap<>();
    model.put(MailContentEnum.LINK.name(), resetPwdLink);
    model.put(MailContentEnum.NAME.name(), name);
    model.put(MailContentEnum.OTP.name(), String.valueOf(otp));

    String classpathEmailTemplate = "classpath:mail-resetpwd.html";

    EmailUtil.sendEmailWithHtmlContent(getMailProperties(user), "curemetric Reset password info",
        classpathEmailTemplate, model);

  }

  @Override
  @Transactional(readOnly = true)
  public void verifyemail(Long pharmacyAddressId, String emailId) {
    if (Objects.isNull(pharmacyAddressId)) {
      Pharmacyuser dbPharmacyuser =
          pharmacyuserDAO.findOneByProperty(BasePharmacyuser.FIELD_emailId, emailId);
      if (Objects.nonNull(dbPharmacyuser)) {
        throw new IllegalArgumentException("The email id already exists.");
      }
    }
    Pharmacyuser dbPharmacyuser =
        pharmacyuserDAO.getByPharmacyAddressIdAndEmailId(pharmacyAddressId, emailId, Boolean.TRUE);
    if (Objects.nonNull(dbPharmacyuser)) {
      throw new IllegalArgumentException("The email id already exists for this pharmacy.");
    }
    dbPharmacyuser =
        pharmacyuserDAO.getByPharmacyAddressIdAndEmailId(pharmacyAddressId, emailId, Boolean.FALSE);
    if (Objects.nonNull(dbPharmacyuser)) {
      throw new IllegalArgumentException("The user is already register with the other pharmacy.");
    }
  }

  @Override
  @Transactional
  public void activateOrDeactivateUser(Long pharmacyUserId, boolean deactivateUser) {
    // super admin validation
    Pharmacyuser dbPharmacyuser = pharmacyuserDAO.findById(pharmacyUserId);
    if (Objects.isNull(dbPharmacyuser)) {
      throw new IllegalArgumentException("The user not found.");
    }
    dbPharmacyuser.setActive('Y');
    if (deactivateUser) {
      dbPharmacyuser.setActive('N');
    }
    pharmacyuserDAO.save(dbPharmacyuser);
  }

  @Override
  @Transactional(readOnly = true)
  public Response<List<Pharmacyuser>> getAssociatedUsers(Long pharmacyaddressId, Integer pageNum,
      Integer maxResults) {

    Response<List<Pharmacyuser>> response =
        pharmacyuserDAO.getAssociatedUsers(pharmacyaddressId, pageNum, maxResults);
    List<Pharmacyuser> pharmacyUsers = response.getData();
    if (!CollectionUtils.isEmpty(pharmacyUsers)) {
      pharmacyUsers.forEach(pu -> {
        List<Userrole> userRoles = userRoleDAO.getByRoleIdAndUserId(null, pu.getPharmacyUserId());
        if (CollectionUtils.isEmpty(userRoles)) {
          throw new IllegalArgumentException("Roles are not exists");
        }
        List<Integer> roleIds =
            userRoles.stream().map(ur -> ur.getRoleId()).collect(Collectors.toList());
        // Fetching the roles information and set to pharmacy user object.
        pu.setRoles(rolesDAO.getByUserroles(roleIds));
      });

    }
    return response;
  }

  @Override
  @Transactional(readOnly = true)
  public Pharmacyuser getPrimaryDetails(Long pharmacyuserid) {

    Pharmacyuser pharmacyuser = pharmacyuserDAO.findById(pharmacyuserid);
    if (Objects.isNull(pharmacyuser)) {
      throw new IllegalArgumentException("pharmacy user not found");
    }
    pharmacyuser.setPassword("");
    return pharmacyuser;
  }

  @Override
  @Transactional
  public void updatePrimaryDetails(Pharmacyuser pharmacy) {
    Pharmacyuser pharmacyuser = pharmacyuserDAO.findOneByProperty(Pharmacyuser.FIELD_pharmacyUserId, pharmacy.getPharmacyUserId());
    if (Objects.isNull(pharmacyuser)) {
      throw new IllegalArgumentException("pharmacyuser user not found.");
    }
    Pharmacy dbpharmacy =
        pharmacyDAO.findOneByProperty(Pharmacy.FIELD_primaryUser, pharmacyuser.getPharmacyUserId());
    if (Objects.isNull(dbpharmacy)) {
      throw new IllegalArgumentException("pharmacy user not found.");
    }

    pharmacyuser.setFirstName(pharmacy.getFirstName());
    pharmacyuser.setLastName(pharmacy.getLastName());
    pharmacyuser.setMiddleName(pharmacy.getMiddleName());
    dbpharmacy.setPhoneNumber(pharmacy.getCellNumber());
    dbpharmacy.setName(pharmacy.getPharmacyName());
    pharmacyuserDAO.save(pharmacyuser);
    pharmacyDAO.save(dbpharmacy);
  }

  @Override
  @Transactional(readOnly = true)
  public Collection<Roles> getAllRoles() {
    return rolesDAO.findAll();
  }

  @Override
  @Transactional
  public void resetPasswordForPharmacyuser(UpdatePasswordRequest updatePasswordRequest) {
    Pharmacyuser pharmacyuser = pharmacyuserDAO.findOneByProperty(Pharmacyuser.FIELD_verificationId,
        updatePasswordRequest.getVerificationid());
    if (Objects.isNull(pharmacyuser)) {
      throw new IllegalArgumentException("pharmacy user not found.");
    }
    Registration registration = registrationDAO
        .findOneByProperty(BaseRegistration.FIELD_verificationId, pharmacyuser.getVerificationId());
    if (!pharmacyuser.getEmailotp().equals(updatePasswordRequest.getOtp())) {
      throw new IllegalArgumentException("Invalid otp");
    }
    pharmacyuser.setPassword(UserHelper.getHashedPassWord(updatePasswordRequest.getNewPassword()));
    registration.setActive('Y');
    registrationDAO.save(registration);
    pharmacyuser.setActive('Y');
    pharmacyuserDAO.save(pharmacyuser);
  }

  @Override
  @Transactional
  public void updatePharmacyuser(PharmacyUserUpadteDTO pharmacyUserUpadteDTO) {
    Pharmacyuser pharmacyuser = pharmacyuserDAO.findOneByProperty(Pharmacyuser.FIELD_pharmacyUserId,
        pharmacyUserUpadteDTO.getPharmacyUserId());
    if (Objects.isNull(pharmacyuser)) {
      throw new IllegalArgumentException("pharmacy user not found.");

    }
    Userrole userrole = userRoleDAO.findOneByProperty(Userrole.FIELD_pharmacyUserId,
        pharmacyuser.getPharmacyUserId());
    if (Objects.isNull(userrole)) {
      throw new IllegalArgumentException("Userrole  not found.");

    }
    userrole.setRoleId(pharmacyUserUpadteDTO.getRoleId());
    userrole.setPharmacyUserId(pharmacyuser.getPharmacyUserId());
    userRoleDAO.save(userrole);
  }

  @Override
  @Transactional
  public Pharmacyuser addPrimaryPharmacyuser(Pharmacyuser pharmacyuser) {

    savePharmacyuser(pharmacyuser);

    return pharmacyuser;
  }

}
