package org.secondopinion.doctor.service.impl;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.UUID;
import org.apache.commons.lang3.StringUtils;
import org.secondopinion.doctor.dao.AssociationDAO;
import org.secondopinion.doctor.dao.CertificationDAO;
import org.secondopinion.doctor.dao.DoctorAddressDAO;
import org.secondopinion.doctor.dao.DoctorDAO;
import org.secondopinion.doctor.dao.FeedetailsDAO;
import org.secondopinion.doctor.dao.PersonaldetailDAO;
import org.secondopinion.doctor.dao.RegistrationDAO;
import org.secondopinion.doctor.domain.BaseAssociation;
import org.secondopinion.doctor.domain.BaseCertification;
import org.secondopinion.doctor.domain.BaseDoctor;
import org.secondopinion.doctor.domain.BaseDoctorAddress;
import org.secondopinion.doctor.domain.BaseFeedetails;
import org.secondopinion.doctor.domain.BasePersonaldetail;
import org.secondopinion.doctor.domain.BaseRegistration;
import org.secondopinion.doctor.dto.Doctor;
import org.secondopinion.doctor.dto.DoctorAddress;
import org.secondopinion.doctor.dto.DoctorFlagsRequest;
import org.secondopinion.doctor.dto.DoctorFlagsRequest.DoctorFlag;
import org.secondopinion.doctor.dto.Feedetails;
import org.secondopinion.doctor.dto.Personaldetail;
import org.secondopinion.doctor.dto.ProfileCompletedDTO;
import org.secondopinion.doctor.dto.Registration;
import org.secondopinion.doctor.dto.TYPE;
import org.secondopinion.doctor.service.IDoctorRegistrationService;
import org.secondopinion.doctor.service.rest.DoctorElasticSearchRestAPIService;
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
import org.springframework.util.CollectionUtils;
import org.springframework.web.multipart.MultipartFile;

@Service
public class DoctorRegistrationServiceImpl implements IDoctorRegistrationService {

	@Autowired
	private FeedetailsDAO feedetailsDAO;

	@Autowired
	private DoctorElasticSearchRestAPIService doctorElasticSearchRestAPIService;

	@Autowired
	private AssociationDAO associationDAO;

	@Autowired
	private RegistrationDAO registrationDAO;

	@Autowired
	private DoctorDAO doctorDAO;

	@Autowired
	private DoctorAddressDAO doctorAddressDAO;

	@Autowired
	private CertificationDAO certificationDAO;

	@Autowired
	private PersonaldetailDAO personalDetailDAO;

	@Autowired
	private MailProperties mailProperties;

	@Value("${emailVerificationLink}")
	private String emailVerificationLink;

	@Value("${loginLinkForUI}")
	private String loginLinkForUI;

	private static final String DOCTOR_NOT_FOUND_BY_EMAIL = "Doctor has not found with the requested email";

	@Override
	@Transactional
	public void registerDoctor(Doctor doctor) {

		if (doctor.getType().equals(TYPE.NUTRITIONIST.name())) {
			String emailId = doctor.getEmailId();
			String password = doctor.getPassword();
			if (StringUtil.isNullOrEmpty(emailId) || StringUtil.isNullOrEmpty(password)) {

				throw new IllegalArgumentException(
						" Please provide valid [" + BaseDoctor.FIELD_emailId + "," + BaseDoctor.FIELD_password + "]");
			}

			UserHelper.passwordValidation(password);
			Doctor doctorByEmail = doctorDAO.findOneByProperty(BaseDoctor.FIELD_emailId, emailId);
			if (Objects.nonNull(doctorByEmail)) {
				throw new IllegalArgumentException("User already registered with this email ID. Use another email ID.");
			}
			doctor.setType("NUTRITIONIST");
			doctor.setActive('N');
			doctor.setPassword(UserHelper.getHashedPassWord(password));
			boolean phonenumber = verifyPhoneNumberExists(doctor.getCellNumber());
			if (phonenumber == true) {
				throw new IllegalArgumentException("Your  phone number already exists.");
			}
			saveOrUpdateDoctor(doctor, null);
			// build registration
			String verificationId = getVerificationId();
			String hashedVerificationId = UserHelper.getHashedPassWord(verificationId);
			Registration registration = Registration.build(doctor, hashedVerificationId, null);
			registrationDAO.save(registration);

			String cellNumber = doctor.getCellNumber();
			if (StringUtils.isEmpty(cellNumber)) {
				throw new IllegalArgumentException("Cellnumber can not be null");
			}
			registration.setEmailVerified('Y');
			registration.setEmailVerifiedOn(DateUtil.getDate());
			Integer otp = OtpUtil.otpToPhone(cellNumber, "Email Verification");// send a text to phone
			registration.setEmailotp(otp);
			registration.setOtpVerifiedOn(DateUtil.getDate());
			registrationDAO.save(registration);

			String name = doctor.getFirstName().toUpperCase() + " " + doctor.getLastName().toUpperCase();
			String classpathEmailTemplate = "classpath:mail-emailverification.html";
			Map<String, String> model = new HashMap<>();
			model.put(MailContentEnum.OTP.name(), String.valueOf(otp));
			model.put(MailContentEnum.NAME.name(), name);
			EmailUtil.sendEmailWithHtmlContent(getMailProperties(doctor), "CureMetric Email Verification",
					classpathEmailTemplate, model);
		//	doctorElasticSearchRestAPIService.updateDoctorElasticSearchData(doctor);
		} else if (doctor.getType().equals(TYPE.DOCTOR.name())) {
			String emailId = doctor.getEmailId();
			String password = doctor.getPassword();
			if (StringUtil.isNullOrEmpty(emailId) || StringUtil.isNullOrEmpty(password)) {

				throw new IllegalArgumentException(
						" Please provide valid [" + BaseDoctor.FIELD_emailId + "," + BaseDoctor.FIELD_password + "]");
			}

			UserHelper.passwordValidation(password);
			Doctor doctorByEmail = doctorDAO.findOneByProperty(BaseDoctor.FIELD_emailId, emailId);
			if (Objects.nonNull(doctorByEmail)) {
				throw new IllegalArgumentException("User already registered with this email ID. Use another email ID.");
			}
			doctor.setType("DOCTOR");
			doctor.setActive('N');
			doctor.setPassword(UserHelper.getHashedPassWord(password));
			boolean phonenumber = verifyPhoneNumberExists(doctor.getCellNumber());
			if (phonenumber == true) {
				throw new IllegalArgumentException("Your  phone number already exists.");
			}
			saveOrUpdateDoctor(doctor, null);
			// build registration
			String verificationId = getVerificationId();
			String hashedVerificationId = UserHelper.getHashedPassWord(verificationId);
			Registration registration = Registration.build(doctor, hashedVerificationId, null);
			registrationDAO.save(registration);

			String cellNumber = doctor.getCellNumber();
			if (StringUtils.isEmpty(cellNumber)) {
				throw new IllegalArgumentException("Cellnumber can not be null");
			}

			registration.setEmailVerified('Y');
			registration.setEmailVerifiedOn(DateUtil.getDate());
			Integer otp = OtpUtil.otpToPhone(cellNumber, "Email Verification");// send a text to phone
			registration.setEmailotp(otp);
			registration.setOtpVerifiedOn(DateUtil.getDate());
			registrationDAO.save(registration);
			String name = doctor.getFirstName().toUpperCase() + " " + doctor.getLastName().toUpperCase();
			String classpathEmailTemplate = "classpath:mail-emailverification.html";
			Map<String, String> model = new HashMap<>();
			model.put(MailContentEnum.OTP.name(), String.valueOf(otp));
			model.put(MailContentEnum.NAME.name(), name);
			EmailUtil.sendEmailWithHtmlContent(getMailProperties(doctor), "CureMetric Email Verification",
					classpathEmailTemplate, model);
		//	doctorElasticSearchRestAPIService.updateDoctorElasticSearchData(doctor);

		}

	}

	/**
	 * .
	 * 
	 * @param doctor
	 * @param verificationId
	 */
	private void emailVerificationMailSend(Doctor doctor, String verificationId) {
		// will send email verification link

		String name = doctor.getFirstName() + " " + doctor.getLastName();
		Map<String, String> model = new HashMap<>();
		model.put(MailContentEnum.LINK.name(),
				String.format(emailVerificationLink, verificationId, doctor.getUiHostURL()));
		model.put(MailContentEnum.NAME.name(), name);

		String classpathEmailTemplate = "classpath:mail-emailverification.html";
		EmailUtil.sendEmailWithHtmlContent(getMailProperties(doctor), "CureMetric Email Verification",
				classpathEmailTemplate, model);
	}

	@Override
	@Transactional
	public void requestEmailVerificationLink(String emailId) {
		Doctor doctor = doctorDAO.findOneByProperty(BaseDoctor.FIELD_emailId, emailId);
		if (Objects.isNull(doctor)) {
			throw new IllegalArgumentException(DOCTOR_NOT_FOUND_BY_EMAIL);
		}

		String verificationId = getVerificationId();
		String hashedVerificationId = UserHelper.getHashedPassWord(verificationId);
		Registration registration = registrationDAO.findOneByProperty(BaseRegistration.FIELD_doctorId,
				doctor.getDoctorId());

		if (Objects.isNull(registration)) {
			Integer otp = OtpUtil.otpToPhone(doctor.getCellNumber(), "Phone Number Verification");// send
																									// a
																									// text
																									// to
			// phone number
			registration = Registration.build(doctor, hashedVerificationId, otp);
		}

		registration.setVerificationId(hashedVerificationId);
		registration.setEmailVerified('N');
		registration.setEmailVerifiedOn(null);
		registration.setActive('Y');
		registrationDAO.save(registration);

		emailVerificationMailSend(doctor, hashedVerificationId);
	}

	@Override
	@Transactional
	public void phoneverification(String phonenumber, Integer otp) {

		Doctor doctor = doctorDAO.findOneByProperty(BaseDoctor.FIELD_cellNumber, phonenumber);
		if (Objects.isNull(doctor)) {
			throw new IllegalArgumentException("Doctor details not found.");
		}
		Registration registration = registrationDAO.findOneByProperty(Registration.FIELD_doctorId,
				doctor.getDoctorId());
		if (Objects.isNull(registration)) {
			throw new IllegalArgumentException("Registration details not found.");
		}
		if (!registration.getOtp().equals(otp)) {
			throw new IllegalArgumentException("Otp is invalid.");
		}
		// here we will found reg and otp
		registration.setPhoneNumberVerified('Y');
		registration.setPhoneNumberVerifiedOn(DateUtil.getDate());
		registration.setActive('Y');
		doctor.setActive('Y');
		doctor.setActivatedDate(DateUtil.getDate());
		registrationDAO.save(registration);
		doctorDAO.save(doctor);
		String name = doctor.getFirstName() + " " + doctor.getLastName();
		Map<String, String> model = new HashMap<>();
		model.put(MailContentEnum.LINK.name(), "/" + loginLinkForUI);
		model.put(MailContentEnum.NAME.name(), name);

		String classpathEmailTemplate = "classpath:mail-login.html";
		EmailUtil.sendEmailWithHtmlContent(getMailProperties(doctor), "CureMetric Account activated ",
				classpathEmailTemplate, model);

	}

	@Override
	@Transactional
	public void requestOTPForPhoneVerification(String phonenumber) {
		Doctor doctor = doctorDAO.findOneByProperty(BaseDoctor.FIELD_cellNumber, phonenumber);

		if (Objects.isNull(doctor)) {
			throw new IllegalArgumentException(DOCTOR_NOT_FOUND_BY_EMAIL);
		}

		String cellNumber = doctor.getCellNumber();
		if (Objects.isNull(cellNumber)) {
			throw new IllegalArgumentException("cellNumber can not be null");
		}
		Integer otp = OtpUtil.otpToPhone(doctor.getCellNumber(), "Phone Verification  ");// send a text
																							// to phone
																							// number
		Registration registration = registrationDAO.findOneByProperty(BaseRegistration.FIELD_doctorId,
				doctor.getDoctorId());
		if (Objects.isNull(registration)) {
			String verificationId = getVerificationId();
			String hashedVerificationId = UserHelper.getHashedPassWord(verificationId);
			registration = Registration.build(doctor, hashedVerificationId, otp);
			emailVerificationMailSend(doctor, hashedVerificationId);
		}
		registration.setOtp(otp);
		// create new calumn otp expiry time
		//
		registration.setOtp(otp);
		registration.setOtpVerifiedOn(DateUtil.getDate());
		registrationDAO.save(registration);
		SendSms.sendSms(
				"is your CUREMETRIC OTP for mobile verification. The code will valid for 10 min. Do not share this OTP with anyone"
						+ otp,
				doctor.getCellNumber());
		registration.setOtp(otp);
		registration.setPhoneNumberVerified('Y');
		registration.setPhoneNumberVerifiedOn(null);
		registration.setActive('N');
		registrationDAO.save(registration);
	}

	@Override
	public void resendOTPForResetPassword(String emailId, String resetPwdLink) {
		Doctor doctor = doctorDAO.findOneByProperty(BaseDoctor.FIELD_emailId, emailId);

		if (Objects.isNull(doctor)) {
			throw new IllegalArgumentException(DOCTOR_NOT_FOUND_BY_EMAIL);
		}
		Integer otp = OtpUtil.otpToPhone(doctor.getCellNumber(), "Forgot Password");// send a text to
																					// phone number
		String name = doctor.getFirstName() + " " + doctor.getLastName();

		Map<String, String> model = new HashMap<>();
		model.put(MailContentEnum.NAME.name(), name);
		model.put(MailContentEnum.OTP.name(), String.valueOf(otp));

		String classpathEmailTemplate = "classpath:mail-resetpwd.html";

		EmailUtil.sendEmailWithHtmlContent(getMailProperties(doctor), "curemetric Reset password info",
				classpathEmailTemplate, model);
	}

	/**
	 * @return the mailProperties
	 */
	public MailProperties getMailProperties() {
		return mailProperties;
	}

	/**
	 * @param mailProperties the mailProperties to set
	 */
	public void setMailProperties(MailProperties mailProperties) {
		this.mailProperties = mailProperties;
	}

	private MailProperties getMailProperties(Doctor doctor) {
		MailProperties properties = mailProperties.clone();
		properties.addToRecipient(doctor.getEmailId());
		return properties;
	}

	private void saveOrUpdateDoctor(Doctor doctor, MultipartFile profilePic) {

		doctor.setActive('Y');
		doctorDAO.save(doctor);
		Long doctorId = doctor.getDoctorId();

		if (doctor.getFeedetails() != null) {
			doctor.getFeedetails().stream().forEach(feedetails -> {
				feedetails.setDoctorId(doctorId);
				feedetails.setActive('Y');
				feedetailsDAO.save(feedetails);
			});
		}
		Personaldetail pd = doctor.getPersonaldetail();
		byte[] profilePicBlob = (Objects.isNull(pd) ? null : pd.getProfilePic());
		if (profilePic != null) {
			try {
				profilePicBlob = profilePic.getBytes();
			} catch (IOException e) {
				throw new IllegalArgumentException("Invalid Profile pic.");
			}
		}

		if (Objects.nonNull(profilePic) && Objects.isNull(pd)) {
			pd = new Personaldetail();
			pd.setDoctorId(doctorId);
			pd.setProfilePic(profilePicBlob);
			pd.setActive('Y');
			personalDetailDAO.save(pd);
		}

		if (doctor.getAssociations() != null) {
			doctor.getAssociations().stream().forEach(association -> {
				association.setDoctorId(doctorId);
				association.setActive('Y');
				associationDAO.save(association);
			});
		}

		if (doctor.getDoctorAddresses() != null) {
			List<DoctorAddress> doctorAddresses = doctor.getDoctorAddresses();
			doctorAddresses.stream().forEach(doctorAddress -> {
				doctorAddress.setDoctorId(doctorId);
				doctorAddress.setActive('Y');
				doctorAddressDAO.save(doctorAddress);
			});

		}

		if (doctor.getCertifications() != null) {
			doctor.getCertifications().stream().forEach(ed -> {
				ed.setDoctorId(doctorId);
				ed.setActive('Y');
				certificationDAO.save(ed);
			});
		}
	}

	@Override
	@Transactional(readOnly = true)
	public void forgotPassword(String emailId, String resetPwdLink) {

		Doctor doctor = doctorDAO.findOneByProperty(BaseDoctor.FIELD_emailId, emailId);
		if (Objects.isNull(doctor)) {
			throw new IllegalArgumentException("The Doctor Email has not been registered");
		}

		Integer otp = OtpUtil.otpToPhone(doctor.getCellNumber(), "Reset password");// send a text to
																					// phone number
		String name = doctor.getFirstName() + " " + doctor.getLastName();
		Map<String, String> model = new HashMap<>();
		model.put(MailContentEnum.LINK.name(), resetPwdLink);
		model.put(MailContentEnum.NAME.name(), name);
		model.put(MailContentEnum.OTP.name(), String.valueOf(otp));

		String classpathEmailTemplate = "classpath:mail-resetpwd.html";

		EmailUtil.sendEmailWithHtmlContent(getMailProperties(doctor), "Curemetric Reset password info",
				classpathEmailTemplate, model);

	}

	@Override
	@Transactional
	public void resetPassword(String emailId, String newPassword, Integer otp, String type) {

		if (StringUtil.isNullOrEmpty(emailId) || StringUtil.isNullOrEmpty(newPassword)) {
			throw new IllegalArgumentException(
					" Please provide valid [" + BaseDoctor.FIELD_emailId + "," + BaseDoctor.FIELD_password + "]");
		}
		UserHelper.passwordValidation(newPassword);
		Doctor doctor = doctorDAO.findByDoctorAndNutrations(emailId, type);

		if (Objects.isNull(doctor)) {
			throw new IllegalArgumentException("Doctor not found");
		}

		String hashedPasswordFromDb = doctor.getPassword();
		if (UserHelper.checkpw(newPassword, hashedPasswordFromDb)) {

			throw new IllegalArgumentException("Old Password And new Passwrd Not be Same");
		}
		Registration registration = registrationDAO.findOneByProperty(Registration.FIELD_doctorId,
				doctor.getDoctorId());

		if (!registration.getEmailotp().equals(otp)) {
			throw new IllegalArgumentException("Otp is invalid.");
		}
		doctor.setPassword(UserHelper.getHashedPassWord(newPassword));

		doctorDAO.save(doctor);

	}

	@Override
	@Transactional
	public void deletDoctor(Long doctorId) {

		Doctor doctor = doctorDAO.findDoctorById(doctorId);
		if (Objects.isNull(doctor)) {
			throw new IllegalArgumentException("Doctor not found.");
		}
		doctor.setActive('N');
		doctorDAO.save(doctor);
	}

	@Override
	@Transactional
	public void updateDoctorDetails(Doctor uiDoctor, MultipartFile profilePic) {
		Long doctorId = uiDoctor.getDoctorId();
		Doctor dbDoctor = doctorDAO.findById(doctorId);

		if (Objects.isNull(dbDoctor)) {
			throw new IllegalArgumentException("The Doctor has not been registered");
		}

		// validating email
		boolean emailExistsForOtherDoctrs = doctorDAO.checkIfEmailInUseForOtherDcotors(doctorId, uiDoctor.getEmailId());
		if (emailExistsForOtherDoctrs) {
			throw new IllegalArgumentException("The User Name " + uiDoctor.getEmailId() + " is already registered.");
		}

		dbDoctor = Doctor.buildForUpdate(dbDoctor, uiDoctor);
		saveOrUpdateDoctor(dbDoctor, profilePic);

	}

	private Doctor buildDoctorObjectWithChildObjects(Doctor doctor) {
		if (Objects.isNull(doctor)) {
			return doctor;
		}
		doctor.setAssociations(associationDAO.findByProperty(BaseAssociation.FIELD_doctorId, doctor.getDoctorId()));
		doctor.setFeedetails(feedetailsDAO.findByProperty(BaseFeedetails.FIELD_doctorId, doctor.getDoctorId()));
		doctor.setDoctorAddresses(
				doctorAddressDAO.findByProperty(BaseDoctorAddress.FIELD_doctorId, doctor.getDoctorId()));
		doctor.setCertifications(
				certificationDAO.findByProperty(BaseCertification.FIELD_doctorId, doctor.getDoctorId()));

		Personaldetail pd = personalDetailDAO.findOneByProperty(BasePersonaldetail.FIELD_doctorId,
				doctor.getDoctorId());
		doctor.setPersonaldetail(pd);

		return doctor;
	}

	@Override
	@Transactional(readOnly = true)
	public Doctor getDoctorById(Long doctorId) {
		Doctor doctor = doctorDAO.findById(doctorId);

		return buildDoctorObjectWithChildObjects(doctor);
	}

	@Override
	@Transactional(readOnly = true)
	public Boolean verifyEmailIdExists(String emailId) {
		return doctorDAO.findOneByProperty(BaseDoctor.FIELD_emailId, emailId) != null;
	}

	@Override
	@Transactional
	public void emailVerification(String emailid, Integer emailotp) {

		Registration registration = registrationDAO.findOneByProperty(BaseRegistration.FIELD_emailotp, emailotp);
		if (Objects.isNull(registration)) {
			throw new IllegalArgumentException("Email verification details not found.");
		}
		Doctor doctor = doctorDAO.findById(registration.getDoctorId());
		if (Objects.isNull(doctor)) {
			throw new IllegalArgumentException("Doctor has not found with the requested email.");
		}
		String cellNumber = doctor.getCellNumber();
		if (StringUtils.isEmpty(cellNumber)) {
			throw new IllegalArgumentException("Cellnumber can not be null");
		}
		// we need to validate otp
		if (!registration.getEmailotp().equals(emailotp)) {
			throw new IllegalArgumentException("Invalid otp.");
		}
		registration.setEmailVerified('Y');
		registration.setEmailVerifiedOn(DateUtil.getDate());
		// otp to send phone number
		Integer otp = OtpUtil.otpToPhone(cellNumber, "Phone Number Verification");// send a text to
																					// phone
		registration.setOtp(otp);
		registration.setOtpVerifiedOn(DateUtil.getDate());
		registrationDAO.save(registration);
		SendSms.sendSms("Hii Welcome To CureMetric Your Otp is .." + otp, cellNumber);
		// the below code will be removed once twilio has integrated successfully
		String name = doctor.getFirstName() + " " + doctor.getLastName();
		String classpathEmailTemplate = "classpath:mail-phoneVerification.html";
		Map<String, String> model = new HashMap<>();
		model.put(MailContentEnum.OTP.name(), String.valueOf(otp));
		model.put(MailContentEnum.NAME.name(), name);
		EmailUtil.sendEmailWithHtmlContent(getMailProperties(doctor), "CureMetric Phone Number Verification",
				classpathEmailTemplate, model);
		// doctorElasticSearchRestAPIService.updateDoctorElasticSearchData(doctor);

	}

	private String getVerificationId() {
		return UUID.randomUUID().toString();
	}

	@Override
	@Transactional
	public Boolean approveDoctor(Long doctorId) {

		Registration registration = registrationDAO.findOneByProperty(BaseRegistration.FIELD_doctorId, doctorId);

		registration.setDoctorApproved('Y');
		registration.setDoctorApprovedOn(new Date());

		registrationDAO.save(registration);

		return true;
	}

	@Override
	@Transactional(readOnly = true)
	public Map<DoctorFlag, List<Doctor>> fetchDoctorsByFlagWithPagination(DoctorFlagsRequest doctorFlagsRequest) {
		Map<DoctorFlag, List<Doctor>> map = new HashMap<>();
		Set<DoctorFlag> doctorFlags = doctorFlagsRequest.getDoctorFlags();
		if (CollectionUtils.isEmpty(doctorFlags)) {
			return map;
		}
		for (DoctorFlag doctorFlag : doctorFlags) {
			map.put(doctorFlag, registrationDAO.fetchDoctorsByFlagWithPagination(doctorFlag,
					doctorFlagsRequest.getSortMap(), doctorFlagsRequest.getPageNo(), doctorFlagsRequest.getLimit()));
		}
		return map;
	}

	@Override
	@Transactional
	public void changePassword(String emailId, String newPassword) {
		Doctor doctor = doctorDAO.findOneByProperty(BaseDoctor.FIELD_emailId, emailId);
		if (Objects.isNull(doctor)) {
			throw new IllegalArgumentException("Doctor not found.");
		}

		UserHelper.passwordValidation(newPassword);
		doctor.setPassword(UserHelper.getHashedPassWord(newPassword));
		doctorDAO.save(doctor);
	}

	@Override
	@Transactional(readOnly = true)
	public boolean userNameExists(String doctorName) {
		Doctor doctor = doctorDAO.findOneByProperty(BaseDoctor.FIELD_userName, doctorName);
		return (doctor != null);

	}

	@Override
	@Transactional(readOnly = true)
	public boolean verifyPhoneNumberExists(String cellNumber) {
		Doctor doctor = doctorDAO.findOneByProperty(BaseDoctor.FIELD_cellNumber, cellNumber);
		return (doctor != null);

	}

	@Override
	public void requestOTPForEmailVerification(String emailid) {
		Doctor doctor = doctorDAO.findOneByProperty(BaseDoctor.FIELD_emailId, emailid);

		if (Objects.isNull(doctor)) {
			throw new IllegalArgumentException(DOCTOR_NOT_FOUND_BY_EMAIL);
		}

		String cellNumber = doctor.getCellNumber();
		if (Objects.isNull(cellNumber)) {
			throw new IllegalArgumentException("cellNumber can not be null");
		}
		Integer otp = OtpUtil.otpToPhone(doctor.getCellNumber(), "Email Verification");// send a text to
																						// phone number
		Registration registration = registrationDAO.findOneByProperty(BaseRegistration.FIELD_doctorId,
				doctor.getDoctorId());
		if (Objects.isNull(registration)) {
			String verificationId = getVerificationId();
			String hashedVerificationId = UserHelper.getHashedPassWord(verificationId);
			registration = Registration.build(doctor, hashedVerificationId, otp);
			emailVerificationMailSend(doctor, hashedVerificationId);
		}

		registration.setEmailVerified('Y');
		registration.setEmailVerifiedOn(DateUtil.getDate());
		registration.setEmailotp(otp);
		registration.setOtpVerifiedOn(DateUtil.getDate());
		registration.setActive('N');
		registrationDAO.save(registration);
		String name = doctor.getFirstName().toUpperCase() + " " + doctor.getLastName().toUpperCase();
		String classpathEmailTemplate = "classpath:mail-emailverification.html";
		Map<String, String> model = new HashMap<>();
		model.put(MailContentEnum.OTP.name(), String.valueOf(otp));
		model.put(MailContentEnum.NAME.name(), name);
		EmailUtil.sendEmailWithHtmlContent(getMailProperties(doctor), "CureMetric Email Verification",
				classpathEmailTemplate, model);

	}

	@Override
	@Transactional
	public void resendOTPForEmail(String emailid) {
		Doctor doctor = doctorDAO.findOneByProperty(BaseDoctor.FIELD_emailId, emailid);
		if (Objects.isNull(doctor)) {
			throw new IllegalArgumentException(DOCTOR_NOT_FOUND_BY_EMAIL);
		}
		Registration registration = registrationDAO.findOneByProperty(BaseRegistration.FIELD_doctorId,
				doctor.getDoctorId());

		if (registration.getActive() == 'N') {
			throw new IllegalArgumentException(" Verification  is Not Done");
		}

		String cellNumber = doctor.getCellNumber();
		if (Objects.isNull(cellNumber)) {
			throw new IllegalArgumentException("cellNumber can not be null");
		}
		Integer otp = OtpUtil.otpToPhone(doctor.getCellNumber(), "Email Verification");// send a text to
																						// phone number

		if (Objects.isNull(registration)) {
			String verificationId = getVerificationId();
			String hashedVerificationId = UserHelper.getHashedPassWord(verificationId);
			registration = Registration.build(doctor, hashedVerificationId, otp);
			emailVerificationMailSend(doctor, hashedVerificationId);
		}

		registration.setEmailVerified('Y');
		registration.setEmailVerifiedOn(DateUtil.getDate());
		registration.setEmailotp(otp);
		registration.setOtpVerifiedOn(DateUtil.getDate());
		registrationDAO.save(registration);
		String name = doctor.getFirstName().toUpperCase() + " " + doctor.getLastName().toUpperCase();
		String classpathEmailTemplate = "classpath:mail-change-pswd.html";
		Map<String, String> model = new HashMap<>();
		model.put(MailContentEnum.OTP.name(), String.valueOf(otp));
		model.put(MailContentEnum.NAME.name(), name);
		EmailUtil.sendEmailWithHtmlContent(getMailProperties(doctor), "CureMetric Email Verification",
				classpathEmailTemplate, model);

	}

	@Override
	@Transactional
	public ProfileCompletedDTO doctorProfileCompleted(Long doctoId) {
		ProfileCompletedDTO profileCompletedDTO = new ProfileCompletedDTO();
		Doctor doctor = doctorDAO.findOneByProperty(Doctor.FIELD_doctorId, doctoId);

		Personaldetail personaldetail = personalDetailDAO.findOneByProperty(Personaldetail.FIELD_doctorId,
				doctor.getDoctorId());

		if (Objects.isNull(personaldetail)) {
			profileCompletedDTO.setIsAddressCompleted('N');
			profileCompletedDTO.setIsPersonalDetailComplted('N');
			profileCompletedDTO.setIsFeeDetailComplted('N');
			return profileCompletedDTO;
		}

		DoctorAddress address = doctorAddressDAO.findOneByProperty(DoctorAddress.FIELD_doctorId, doctor.getDoctorId());
		if (Objects.isNull(address)) {

			profileCompletedDTO.setIsPersonalDetailComplted('Y');
			profileCompletedDTO.setIsFeeDetailComplted('N');
			profileCompletedDTO.setIsAddressCompleted('N');
			return profileCompletedDTO;
		}

		Feedetails feedetails = feedetailsDAO.findOneByProperty(Feedetails.FIELD_doctorId, doctor.getDoctorId());

		if (Objects.isNull(feedetails)) {
			profileCompletedDTO.setIsPersonalDetailComplted('Y');
			profileCompletedDTO.setIsFeeDetailComplted('N');
			profileCompletedDTO.setIsAddressCompleted('Y');
			return profileCompletedDTO;
		}
		doctor.setIsprofilecompleted('Y');
		doctorDAO.save(doctor);
		profileCompletedDTO.setIsAddressCompleted('Y');
		profileCompletedDTO.setIsFeeDetailComplted('Y');
		profileCompletedDTO.setIsPersonalDetailComplted('Y');
		return profileCompletedDTO;
	}

	@Override
	@Transactional
	public String otpVerificationForEmail(Integer emailotp, String emailId, String type) {
		Doctor doctor = doctorDAO.findByDoctorAndNutrations(emailId, type);

		if (Objects.isNull(doctor)) {
			throw new IllegalArgumentException("doctor Details Not Found For given emailId ");
		}
		Registration registration = registrationDAO.findOneByProperty(Registration.FIELD_doctorId,
				doctor.getDoctorId());
		if (!registration.getEmailotp().equals(emailotp)) {
			throw new IllegalArgumentException("Otp is invalid.");
		}
		return "OTP Verification Is Success ";
	}

	@Override
	@Transactional
	public String otpVerificationForPhone(Integer otp) {
		Registration registration = registrationDAO.findOneByProperty(Registration.FIELD_otp, otp);

		if (Objects.isNull(registration)) {
			throw new IllegalArgumentException("Invalid otp.");
		}
		if (!registration.getEmailotp().equals(otp)) {
			throw new IllegalArgumentException("Otp is invalid.");
		}
		return "OTP Verification Is Success ";
	}

	@Override
	@Transactional
	public boolean oldPaswwordVerification(String emailid, String paswword) {

		Doctor doctor = doctorDAO.findOneByProperty(Doctor.FIELD_emailId, emailid);
		if (Objects.isNull(doctor)) {
			throw new IllegalArgumentException("Doctor Detail Is Not Found For Given EmailId.");
		}
		String hashpawsd = UserHelper.getHashedPassWord(doctor.getPassword());
		if (UserHelper.checkpw(paswword, hashpawsd)) {
			return false;

		}
		return true;
	}

	@Override
	@Transactional
	public Doctor getDoctorByEmailId(String emailId) {
		Doctor doctor = doctorDAO.findOneByProperty(Doctor.FIELD_emailId, emailId);
		if (Objects.isNull(doctor)) {
			throw new IllegalArgumentException("Doctor Detail Is Not Found For Given EmailId.");
		}
		return doctor;
	}

}
