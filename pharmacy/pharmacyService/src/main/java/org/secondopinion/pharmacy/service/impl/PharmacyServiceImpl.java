package org.secondopinion.pharmacy.service.impl;

import java.io.IOException;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.UUID;
import java.util.stream.Collectors;

import org.apache.commons.lang3.StringUtils;
import org.secondopinion.pharmacy.dao.AddressDAO;
import org.secondopinion.pharmacy.dao.PersonaldetailDAO;
import org.secondopinion.pharmacy.dao.PharmacyDAO;
import org.secondopinion.pharmacy.dao.PharmacyaddressDAO;
import org.secondopinion.pharmacy.dao.PharmacyuserDAO;
import org.secondopinion.pharmacy.dao.RegistrationDAO;
import org.secondopinion.pharmacy.dao.RolesDAO;
import org.secondopinion.pharmacy.domain.BasePharmacy;
import org.secondopinion.pharmacy.domain.BasePharmacyuser;
import org.secondopinion.pharmacy.domain.BaseRegistration;
import org.secondopinion.pharmacy.dto.Address;
import org.secondopinion.pharmacy.dto.Personaldetail;
import org.secondopinion.pharmacy.dto.Pharmacy;
import org.secondopinion.pharmacy.dto.PharmacySearchRequest;
import org.secondopinion.pharmacy.dto.Pharmacyaddress;
import org.secondopinion.pharmacy.dto.Pharmacyuser;
import org.secondopinion.pharmacy.dto.ProfileCompltedDTO;
import org.secondopinion.pharmacy.dto.Registration;
import org.secondopinion.pharmacy.dto.Roles;
import org.secondopinion.pharmacy.service.IPharmacyService;
import org.secondopinion.pharmacy.service.IPharmacyuserRegistrationService;
// import org.secondopinion.pharmacy.service.rest.PharamcyElasticSerchRestAPIService;
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
import org.springframework.web.multipart.MultipartFile;

@Service
public class PharmacyServiceImpl implements IPharmacyService {

	@Autowired
	private PharmacyDAO pharmacyDAO;

	@Autowired
	private PersonaldetailDAO personaldetailDAO;

	// @Autowired
	// private PharamcyElasticSerchRestAPIService
	// pharamcyElasticSerchRestAPIService;
	@Autowired
	private RolesDAO rolesDAO;

	@Autowired
	private AddressDAO addressDAO2;
	@Autowired
	private PharmacyuserDAO pharmacyuserDAO;
	@Autowired
	private PharmacyaddressDAO addressDAO;
	@Autowired
	private MailProperties mailProperties;
	@Autowired
	private RegistrationDAO registrationDAO;

	@Value("${emailVerificationLink}")
	private String emailVerificationLink;

	@Value("${loginLinkForUI}")
	private String loginLinkForUI;

	@Autowired
	private IPharmacyuserRegistrationService pharmacyuserRegistrationService;

	@Override
	@Transactional
	public void registerPharmacy(Pharmacy pharmacy) {
		Pharmacyaddress primaryDataCenterAddress = pharmacy.getPrimaryPharmacyAddress();
		Pharmacyuser primaryPharmacyuser = pharmacy.getPrimaryPharmacyUser();
		if (Objects.isNull(primaryDataCenterAddress) || Objects.isNull(primaryPharmacyuser)) {
			throw new IllegalArgumentException(
					"Objects [primaryPharmacyAddress, primaryPharmacyUser] can not be null.");
		}

		Pharmacy pharmacyByEmail = pharmacyDAO.readByEmailId(pharmacy.getEmailId());
		if (Objects.nonNull(pharmacyByEmail)) {
			throw new IllegalArgumentException(
					"The email id [" + pharmacyByEmail.getEmailId() + "] is already registered.");
		}
		pharmacyDAO.save(pharmacy);
		String verificationId = getVerificationId();
		String hashedVerificationId = UserHelper.getHashedPassWord(verificationId);
		Registration registration = Registration.build(pharmacy, hashedVerificationId, null);
		String cellNumber = pharmacy.getPhoneNumber();
		Integer otp = OtpUtil.otpToPhone(cellNumber, "Email Verification");
		registration.setEmailotp(otp);
		registrationDAO.save(registration);
		primaryDataCenterAddress.setPharmacyId(pharmacy.getPharmacyId());

		primaryDataCenterAddress.setIsPrimary('Y');
		primaryDataCenterAddress.setActive('Y');
		addressDAO.save(primaryDataCenterAddress);
		primaryPharmacyuser.setPharmacyaddressId(primaryDataCenterAddress.getPharmacyaddressId());
		primaryPharmacyuser.setPharmacyName(pharmacy.getName());
		createAdminUser(primaryPharmacyuser);
		pharmacy.setPharmacyaddressId(primaryDataCenterAddress.getPharmacyaddressId());
		pharmacy.setPrimaryUser(primaryPharmacyuser.getPharmacyUserId());
		pharmacyDAO.save(pharmacy);

		String name = pharmacy.getName().toUpperCase();
		String classpathEmailTemplate = "classpath:mail-emailverification.html";
		Map<String, String> model = new HashMap<>();
		model.put(MailContentEnum.OTP.name(), String.valueOf(otp));
		model.put(MailContentEnum.NAME.name(), name);
		EmailUtil.sendEmailWithHtmlContent(getMailProperties(pharmacy), "CureMetric Email Verification",
				classpathEmailTemplate, model);

	}

	private void createAdminUser(Pharmacyuser primaryPharmacyuser) {

		Roles roles = rolesDAO.getByRoleName(Roles.PharmacyRolesEnum.ADMIN.name());
		if (Objects.isNull(roles)) {
			throw new IllegalArgumentException(Roles.PharmacyRolesEnum.ADMIN.name() + " not exist in database.");
		}
		primaryPharmacyuser.setRoleId(roles.getRoleId());
		primaryPharmacyuser.setActive('Y');

		pharmacyuserRegistrationService.addPrimaryPharmacyuser(primaryPharmacyuser);
	}

	@Override
	public Collection<Pharmacy> fetchAllPharmacies() {
		return pharmacyDAO.findAll();
	}

	@Override
	public Pharmacy fetchByPharmacyId(Long pharmacyId) {
		if (Objects.isNull(pharmacyId)) {
			throw new IllegalArgumentException(" Please provide valid [" + BasePharmacy.FIELD_pharmacyId + "]");
		}
		return pharmacyDAO.readByPharmacyId(pharmacyId);
	}

	@Override
	public Pharmacy fetchPharmacyByLicenseNumber(String licenseNumber) {
		if (Objects.isNull(licenseNumber)) {
			throw new IllegalArgumentException(" Please provide valid [" + BasePharmacy.FIELD_licenseNumber + "]");
		}
		return pharmacyDAO.readByLicenceNumber(licenseNumber);
	}

	@Override
	public Boolean isEmailExistOrNot(String email) {
		if (Objects.isNull(email) || email.isEmpty()) {
			throw new IllegalArgumentException(" Please provide valid [" + BasePharmacy.FIELD_emailId + "]");
		}
		Pharmacy pharmacy = pharmacyDAO.readByEmailId(email);
		if (pharmacy != null) {
			return true;
		}
		return false;
	}

	@Override
	@Transactional(readOnly = true)
	public Response<List<Pharmacyaddress>> getAddressesOfPharmacy(Long pharmacyId, Integer pageNum,
			Integer maxResults) {

		return addressDAO.readAllAddressesOfPharmacy(pharmacyId, pageNum, maxResults);
	}

	@Override
	@Transactional
	public void saveAddressesOfPharmacy(Pharmacyaddress address) {
		addressDAO.save(address);
	}

	@Override
	@Transactional
	public void updateAddressesOfPharmacy(Pharmacyaddress address) {
		addressDAO.save(address);
	}

	@Override
	public Response<List<Pharmacy>> getAllPharmacyBySearchCritieria(PharmacySearchRequest pharmacySearchRequest) {
		return pharmacyDAO.getAllPharmacyBySearchCritieria(pharmacySearchRequest);
	}

	@Override
	public void emailVerification(String emilid, Integer emailotp) {
		Registration registration = registrationDAO.findOneByProperty(BaseRegistration.FIELD_emailotp, emailotp);
		if (Objects.isNull(registration)) {
			throw new IllegalArgumentException("Email verification details not found.");
		}
		Pharmacy pharmacy = pharmacyDAO.findById(registration.getPharmacyId());
		if (Objects.isNull(pharmacy)) {
			throw new IllegalArgumentException("pharmacy has not found with the requested email.");
		}
		String cellNumber = pharmacy.getPhoneNumber();
		if (StringUtils.isEmpty(cellNumber)) {
			throw new IllegalArgumentException("Cellnumber can not be null");
		}
		if (!registration.getEmailotp().equals(emailotp)) {
			throw new IllegalArgumentException("Invalid otp.");
		}
		registration.setEmailVerified('Y');
		registration.setEmailVerifiedOn(DateUtil.getDate());
		Integer otp = OtpUtil.otpToPhone(cellNumber, "Email  Verification");// send a text to phone
		registration.setOtp(otp);
		registrationDAO.save(registration);
		// the below code will be removed once twilio has integrated successfully
		String name = pharmacy.getName().toUpperCase();
		SendSms.sendSms("Hii" + name + " Welcome To CureMetric Your  Otp is .." + otp, cellNumber);
		String classpathEmailTemplate = "classpath:mail-phoneVerification.html";
		Map<String, String> model = new HashMap<>();
		model.put(MailContentEnum.OTP.name(), String.valueOf(otp));
		model.put(MailContentEnum.NAME.name(), name);
		EmailUtil.sendEmailWithHtmlContent(getMailProperties(pharmacy), "[CureMetric] PhoneVerification",
				classpathEmailTemplate, model);

	}

	@Override
	public void emailVerificationForPharmacyUser(String emilid, Integer emailotp) {
		Registration registration = registrationDAO.findOneByProperty(BaseRegistration.FIELD_emailotp, emailotp);
		if (Objects.isNull(registration)) {
			throw new IllegalArgumentException("Email verification details not found.");
		}
		Pharmacyuser pharmacy = pharmacyuserDAO.findOneByProperty(Registration.FIELD_pharmacyUserId,
				registration.getPharmacyUserId());
		if (Objects.isNull(pharmacy)) {
			throw new IllegalArgumentException("pharmacy has not found with the requested email.");
		}
		String cellNumber = pharmacy.getCellNumber();
		if (StringUtils.isEmpty(cellNumber)) {
			throw new IllegalArgumentException("Cellnumber can not be null");
		}
		if (!registration.getEmailotp().equals(emailotp)) {
			throw new IllegalArgumentException("Invalid otp.");
		}
		registration.setEmailVerified('Y');
		registration.setEmailVerifiedOn(DateUtil.getDate());
		Integer otp = OtpUtil.otpToPhone(cellNumber, "Email  Verification");// send a text to phone
		registration.setOtp(otp);
		registrationDAO.save(registration);
		// the below code will be removed once twilio has integrated successfully
		String name = pharmacy.getFirstName().toUpperCase();
		SendSms.sendSms("Hii" + name + " Welcome To CureMetric Your  Otp is .." + otp, cellNumber);
		String classpathEmailTemplate = "classpath:mail-phoneVerification.html";
		Map<String, String> model = new HashMap<>();
		model.put(MailContentEnum.OTP.name(), String.valueOf(otp));
		model.put(MailContentEnum.NAME.name(), name);
		EmailUtil.sendEmailWithHtmlContent(getMailProperties(pharmacy), "CureMetric PhoneVerification",
				classpathEmailTemplate, model);

	}

	@Override
	public void phoneverification(String phonenumber, Integer otp) {
		Pharmacy pharmacy = pharmacyDAO.findOneByProperty(Pharmacy.FIELD_phoneNumber, phonenumber);
		if (Objects.isNull(pharmacy)) {
			throw new IllegalArgumentException("Phonenumber  details not found.");
		}
		Registration registration = registrationDAO.findOneByProperty(Registration.FIELD_pharmacyId,
				pharmacy.getPharmacyId());

		if (Objects.isNull(pharmacy)) {
			throw new IllegalArgumentException("Registration  details Not Found");
		}

		if (!registration.getOtp().equals(otp)) {
			throw new IllegalArgumentException(" Invalid  Otp ");
		}

		registration.setPhoneNumberVerified('Y');
		registration.setPhoneNumberVerifiedOn(DateUtil.getDate());
		registration.setActive('Y');
		pharmacy.setActive('Y');
		pharmacy.setActivatedDate(new Date());
		pharmacyDAO.save(pharmacy);
		registrationDAO.save(registration);
		String name = pharmacy.getName().toUpperCase();
		Map<String, String> model = new HashMap<>();
		model.put(MailContentEnum.LINK.name(), "" + "/" + loginLinkForUI);
		model.put(MailContentEnum.NAME.name(), name);
		String classpathEmailTemplate = "classpath:mail-login.html";
		EmailUtil.sendEmailWithHtmlContent(getMailProperties(pharmacy), "CureMetric Account activated ",
				classpathEmailTemplate, model);
	}

	@Override
	public void phoneverificationForPharmacyUser(String phonenumber, Integer otp) {
		Pharmacyuser pharmacy = pharmacyuserDAO.findOneByProperty(Pharmacyuser.FIELD_cellNumber, phonenumber);
		if (Objects.isNull(pharmacy)) {
			throw new IllegalArgumentException("Phonenumber  details not found.");
		}
		Registration registration = registrationDAO.findOneByProperty(Registration.FIELD_pharmacyUserId,
				pharmacy.getPharmacyUserId());

		if (Objects.isNull(pharmacy)) {
			throw new IllegalArgumentException("Registration  details Not Found");
		}

		if (!registration.getOtp().equals(otp)) {
			throw new IllegalArgumentException(" Invalid  Otp ");
		}

		registration.setPhoneNumberVerified('Y');
		registration.setPhoneNumberVerifiedOn(DateUtil.getDate());
		registration.setActive('Y');
		pharmacy.setActive('Y');
		pharmacyuserDAO.save(pharmacy);
		registrationDAO.save(registration);
		String name = pharmacy.getFirstName().toUpperCase();
		Map<String, String> model = new HashMap<>();
		model.put(MailContentEnum.LINK.name(), "" + "/" + loginLinkForUI);
		model.put(MailContentEnum.NAME.name(), name);
		String classpathEmailTemplate = "classpath:mail-login.html";
		EmailUtil.sendEmailWithHtmlContent(getMailProperties(pharmacy), "[CureMetric] Account activated ",
				classpathEmailTemplate, model);
	}

	@Override
	public void requestOTPForPhone(String phoneNumber) {
		Pharmacy pharmacy = pharmacyDAO.findOneByProperty(BasePharmacy.FIELD_phoneNumber, phoneNumber);

		if (Objects.isNull(pharmacy)) {
			throw new IllegalArgumentException("Phonenumber Details are not found");
		}
		String cellNumber = pharmacy.getPhoneNumber();
		Integer otp = OtpUtil.otpToPhone(pharmacy.getPhoneNumber(), "Phone Verification");// send a text
																							// to phone
																							// number
		Registration registration = registrationDAO.findOneByProperty(BaseRegistration.FIELD_pharmacyId,
				pharmacy.getPharmacyId());
		if (Objects.isNull(registration)) {
			String verificationId = getVerificationId();
			String hashedVerificationId = UserHelper.getHashedPassWord(verificationId);
			registration = Registration.build(pharmacy, hashedVerificationId, otp);
			emailVerificationMailSend(pharmacy, hashedVerificationId);
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

	private void emailVerificationMailSend(Pharmacy pharmacy, String verificationId) {
		if (StringUtil.isNullOrEmpty(pharmacy.getUiHostURL())) {
			throw new IllegalArgumentException("uiHostURL field can not be null.");
		}
		String name = pharmacy.getName();
		Map<String, String> model = new HashMap<>();
		model.put(MailContentEnum.LINK.name(),
				String.format(emailVerificationLink, verificationId, pharmacy.getUiHostURL()));
		model.put(MailContentEnum.NAME.name(), name);

		String classpathEmailTemplate = "classpath:mail-emailverification.html";
		EmailUtil.sendEmailWithHtmlContent(getMailProperties(pharmacy), "CureMetric Email Verification",
				classpathEmailTemplate, model);

	}

	private String getVerificationId() {
		return UUID.randomUUID().toString();
	}

	private MailProperties getMailProperties(Pharmacy pharmacy) {
		MailProperties properties = mailProperties.clone();
		properties.addToRecipient(pharmacy.getEmailId());
		return properties;
	}

	@Override
	@Transactional
	public void uploadProfilePic(Long phrmacyId, MultipartFile profilePic) {

		if (Objects.isNull(profilePic)) {
			throw new IllegalArgumentException("ProfilePic Can Not be null");
		}

		Pharmacy pharmacy = pharmacyDAO.findById(phrmacyId);

		if (Objects.isNull(pharmacy)) {
			throw new IllegalArgumentException("No Pharmacy Avilable For Given Id");
		}
		try {
			byte[] profilePicBlob = profilePic.getBytes();
			pharmacy.setProfilePic(profilePicBlob);
		} catch (IOException e) {
			throw new IllegalArgumentException("Invalid Profile pic.");
		}

		pharmacyDAO.save(pharmacy);
	}

	@Override
	public Boolean isphoneExistOrNot(String phone) {
		if (Objects.isNull(phone) || phone.isEmpty()) {
			throw new IllegalArgumentException(" Please provide valid [" + BasePharmacy.FIELD_phoneNumber + "]");
		}

		Pharmacy pharmacy = pharmacyDAO.readByphonenumber(phone);
		if (pharmacy != null) {
			return true;
		}
		return false;
	}

	@Override
	public void requestOTPForEamil(String email) {
		Pharmacy pharmacy = pharmacyDAO.findOneByProperty(BasePharmacy.FIELD_emailId, email);

		if (Objects.isNull(pharmacy)) {
			throw new IllegalArgumentException("pharmacy details are not found");
		}

		String cellNumber = pharmacy.getPhoneNumber();
		if (Objects.isNull(cellNumber)) {
			throw new IllegalArgumentException("cellNumber can not be null");
		}
		Integer otp = OtpUtil.otpToPhone(pharmacy.getPhoneNumber(), "Email Verification");// send a text
																							// to phone
																							// number
		Registration registration = registrationDAO.findOneByProperty(BaseRegistration.FIELD_pharmacyId,
				pharmacy.getPharmacyId());
		if (Objects.isNull(registration)) {
			String verificationId = getVerificationId();
			String hashedVerificationId = UserHelper.getHashedPassWord(verificationId);
			registration = Registration.build(pharmacy, hashedVerificationId, otp);
			emailVerificationMailSend(pharmacy, hashedVerificationId);
		}

		registration.setEmailVerified('Y');
		registration.setEmailVerifiedOn(DateUtil.getDate());
		registration.setEmailotp(otp);
		registration.setOtpVerifiedOn(DateUtil.getDate());
		registration.setActive('N');
		registrationDAO.save(registration);
		String name = pharmacy.getName().toUpperCase();
		String classpathEmailTemplate = "classpath:mail-emailverification.html";
		Map<String, String> model = new HashMap<>();
		model.put(MailContentEnum.OTP.name(), String.valueOf(otp));
		model.put(MailContentEnum.NAME.name(), name);
		EmailUtil.sendEmailWithHtmlContent(getMailProperties(pharmacy), "CureMetric  Resend Email Otp",
				classpathEmailTemplate, model);

	}

	@Override
	public void requestOTPForEamilForPharmacyUser(String email) {
		Pharmacyuser pharmacy = pharmacyuserDAO.findOneByProperty(Pharmacyuser.FIELD_emailId, email);

		if (Objects.isNull(pharmacy)) {
			throw new IllegalArgumentException("Pharmacyuser details are not found");
		}

		String cellNumber = pharmacy.getCellNumber();
		if (Objects.isNull(cellNumber)) {
			throw new IllegalArgumentException("cellNumber can not be null");
		}
		Integer otp = OtpUtil.otpToPhone(pharmacy.getCellNumber(), "Email Verification");// send a text
																							// to phone
																							// number
		Registration registration = registrationDAO.findOneByProperty(BaseRegistration.FIELD_pharmacyUserId,
				pharmacy.getPharmacyUserId());
		if (Objects.isNull(registration)) {
			String verificationId = getVerificationId();
			String hashedVerificationId = UserHelper.getHashedPassWord(verificationId);
			// registration = Registration.build(pharmacy, hashedVerificationId, otp);
			// emailVerificationMailSend(pharmacy, hashedVerificationId);
		}

		registration.setEmailVerified('Y');
		registration.setEmailVerifiedOn(DateUtil.getDate());
		registration.setEmailotp(otp);
		registration.setOtpVerifiedOn(DateUtil.getDate());
		registration.setActive('N');
		registrationDAO.save(registration);
		String name = pharmacy.getFirstName().toUpperCase();
		String classpathEmailTemplate = "classpath:mail-emailverification.html";
		Map<String, String> model = new HashMap<>();
		model.put(MailContentEnum.OTP.name(), String.valueOf(otp));
		model.put(MailContentEnum.NAME.name(), name);
		EmailUtil.sendEmailWithHtmlContent(getMailProperties(pharmacy), "CureMetric  Resend Email Otp",
				classpathEmailTemplate, model);

	}

	private MailProperties getMailProperties(Pharmacyuser pharmacy) {
		MailProperties properties = mailProperties.clone();
		properties.addToRecipient(pharmacy.getEmailId());
		return properties;

	}

	@Override
	public void resendOTPForEamil(String email) {

		Pharmacy pharmacy = pharmacyDAO.findOneByProperty(BasePharmacy.FIELD_emailId, email);

		if (Objects.isNull(pharmacy)) {
			throw new IllegalArgumentException("pharmacy details are not found");
		}

		String cellNumber = pharmacy.getPhoneNumber();
		if (Objects.isNull(cellNumber)) {
			throw new IllegalArgumentException("cellNumber can not be null");
		}
		Integer otp = OtpUtil.otpToPhone(pharmacy.getPhoneNumber(), "Resend Otp");// send a text to
																					// phone number
		Registration registration = registrationDAO.findOneByProperty(BaseRegistration.FIELD_pharmacyId,
				pharmacy.getPharmacyId());

		registration.setEmailotp(otp);
		registrationDAO.save(registration);
		String name = pharmacy.getName().toUpperCase();
		String classpathEmailTemplate = "classpath:mail-emailverification.html";
		Map<String, String> model = new HashMap<>();
		model.put(MailContentEnum.OTP.name(), String.valueOf(otp));
		model.put(MailContentEnum.NAME.name(), name);
		EmailUtil.sendEmailWithHtmlContent(getMailProperties(pharmacy), "CureMetric  Resend Email Otp",
				classpathEmailTemplate, model);

	}

	@Override
	@Transactional
	public void savePersonaldetail(Personaldetail personaldetail) {
		personaldetail.setActive('Y');
		personaldetailDAO.save(personaldetail);
	}

	@Override
	@Transactional
	public Personaldetail getPersonaldetail(Long pharmacyuserId) {
		Personaldetail personaldetail = personaldetailDAO.findPersonalDetailBypharmacyId(pharmacyuserId);
		return personaldetail;
	}

	@Override
	@Transactional
	public String otpVerificationForPhoneNumber(Integer otp) {
		Registration registration = registrationDAO.findOneByProperty(Registration.FIELD_otp, otp);

		if (Objects.isNull(registration)) {
			throw new IllegalArgumentException("Otp is invalid");
		}

		else if (!registration.getOtp().equals(otp)) {
			throw new IllegalArgumentException("Otp is invalid");
		}
		return "Otp  Verification Success";
	}

	@Override
	@Transactional
	public String otpVerificationForEmail(Integer emailotp, String emailId) {
		Pharmacy pharmacy = pharmacyDAO.findOneByProperty(Pharmacy.FIELD_emailId, emailId);

		Registration registration = registrationDAO.findOneByProperty(Registration.FIELD_pharmacyId,
				pharmacy.getPharmacyId());

		if (!registration.getEmailotp().equals(emailotp)) {
			throw new IllegalArgumentException("Otp is invalid");
		}
		return "Otp  Verification Success";
	}

	@Override
	@Transactional
	public boolean oldPaswwordVerification(String emailid, String paswword) {
		Pharmacyuser pharmacyuser = pharmacyuserDAO.findOneByProperty(Pharmacyuser.FIELD_emailId, emailid);
		if (Objects.isNull(pharmacyuser)) {
			throw new IllegalArgumentException("Pharmacyuser is not Register");
		}
		String checkpswd = UserHelper.getHashedPassWord(pharmacyuser.getPassword());
		if (UserHelper.checkpw(paswword, checkpswd)) {
			return false;
		}
		return true;
	}

	@Override
	@Transactional
	public void emailVerification(String verificationId) {
		Registration registration = registrationDAO.findOneByProperty(BaseRegistration.FIELD_verificationId,
				verificationId);
		if (Objects.isNull(registration)) {
			throw new IllegalArgumentException("Email verification details not found.");
		}

	}

	@Override
	@Transactional
	public ProfileCompltedDTO isProfileComplted(Long pharmacyId) {
		ProfileCompltedDTO profileCompltedDTO = new ProfileCompltedDTO();
		Pharmacy pharmacy = pharmacyDAO.findById(pharmacyId);
		if (Objects.isNull(pharmacy)) {
			throw new IllegalArgumentException("Pharmacy is not Register.");
		}
		Address address = addressDAO2.findOneByProperty(Address.FIELD_pharmacyUserId, pharmacy.getPrimaryUser());
		if (Objects.isNull(address)) {
			profileCompltedDTO.setIsaddresComplted('N');
			profileCompltedDTO.setIspersonaldetailComplted('N');
			return profileCompltedDTO;
		}

		Personaldetail personaldetail = personaldetailDAO.findOneByProperty(Personaldetail.FIELD_pharmacyuserId,
				pharmacy.getPrimaryUser());
		if (Objects.isNull(personaldetail)) {
			profileCompltedDTO.setIsaddresComplted('Y');
			profileCompltedDTO.setIspersonaldetailComplted('N');
			return profileCompltedDTO;
		}
		profileCompltedDTO.setIsaddresComplted('Y');
		profileCompltedDTO.setIspersonaldetailComplted('Y');
		return profileCompltedDTO;
	}

	@Override
	@Transactional
	public List<Pharmacy> getAssoctedPharmcy(Long pharmacyadminId) {
		Pharmacy pharmacy = new Pharmacy();

		List<Pharmacy> pharmacies = pharmacyDAO.getAssoctedPharmcy(pharmacyadminId);
		if (Objects.isNull(pharmacies)) {
			return null;
		}
		pharmacies.add(pharmacy);

		return pharmacies;
	}

	@Override
	@Transactional
	public Boolean isUserExistOrNot(String userName) {
		if (Objects.isNull(userName) || userName.isEmpty()) {
			throw new IllegalArgumentException(" Please provide valid [" + BasePharmacyuser.FIELD_userName + "]");
		}

		Pharmacyuser pharmacy = pharmacyuserDAO.findOneByProperty(Pharmacyuser.FIELD_userName, userName);
		if (pharmacy != null) {
			return true;
		}
		return false;
	}
}
