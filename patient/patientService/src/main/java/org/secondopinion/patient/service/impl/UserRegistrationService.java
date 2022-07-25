package org.secondopinion.patient.service.impl;

import java.io.IOException;
import java.sql.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.UUID;
import java.util.stream.Collectors;
import org.apache.commons.lang3.StringUtils;
import org.secondopinion.enums.PrimaryContactEnum;
import org.secondopinion.patient.dao.AddressDAO;
import org.secondopinion.patient.dao.PersonaldetailDAO;
import org.secondopinion.patient.dao.RegistrationDAO;
import org.secondopinion.patient.dao.RelationshipDAO;
import org.secondopinion.patient.dao.RolesDAO;
import org.secondopinion.patient.dao.UserDAO;
import org.secondopinion.patient.dao.UserroleDAO;
import org.secondopinion.patient.domain.BaseRegistration;
import org.secondopinion.patient.domain.BaseUser;
import org.secondopinion.patient.dto.ACCESS_TYPE;
import org.secondopinion.patient.dto.Address;
import org.secondopinion.patient.dto.DoctorDashBoardDTO;
import org.secondopinion.patient.dto.ForUser;
// import org.secondopinion.patient.dto.AsscotedUserDTO;
import org.secondopinion.patient.dto.Personaldetail;
import org.secondopinion.patient.dto.PrivalgeDTO;
import org.secondopinion.patient.dto.ProfileCompltedDTO;
import org.secondopinion.patient.dto.RELATIONSHIP_TYPE;
import org.secondopinion.patient.dto.Registration;
import org.secondopinion.patient.dto.Relationship;
import org.secondopinion.patient.dto.Roles;
import org.secondopinion.patient.dto.StatusType;
import org.secondopinion.patient.dto.User;
import org.secondopinion.patient.dto.UserResponseDTO;
import org.secondopinion.patient.dto.Userrole;
import org.secondopinion.patient.service.IUserRegistrationService;
import org.secondopinion.patient.service.helper.UserServiceHelper;
import org.secondopinion.patient.service.rest.PatientElasticSerchRestAPIService;
// import org.secondopinion.patient.service.rest.PatientElasticSerchRestAPIService;
import org.secondopinion.utils.DateUtil;
import org.secondopinion.utils.EmailUtil;
import org.secondopinion.utils.EmailUtil.MailContentEnum;
import org.secondopinion.utils.MailProperties;
import org.secondopinion.utils.ObjectUtil;
import org.secondopinion.utils.OtpUtil;
import org.secondopinion.utils.SendSms;
import org.secondopinion.utils.StringUtil;
import org.secondopinion.utils.UserHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import org.videoroomotpphone.otp.OtpService;

import net.bytebuddy.asm.Advice.OffsetMapping.ForOrigin.Renderer.ForReturnTypeName;

@Service
public class UserRegistrationService implements IUserRegistrationService {

	@Autowired
	private PatientElasticSerchRestAPIService patientElasticSerchRestAPIService;

	@Autowired
	private UserDAO userDAO;

	@Autowired
	private RolesDAO rolesDAO;

	@Autowired
	private UserroleDAO userRoleDAO;
	@Autowired
	private RegistrationDAO registrationDAO;

	@Autowired
	private RelationshipDAO relationshipDAO;

	@Autowired
	private MailProperties mailProperties;
	@Autowired
	private PersonaldetailDAO personaldetailDAO;

	@Autowired
	private AddressDAO addressDAO;

	@Value("${loginLinkForUI}")
	private String loginLinkForUI;

	@Value("${emailVerificationLink}")
	private String emailVerificationLink;

	private static final String USER_NOT_FOUND_BY_EMAIL = "User not found by the requested email";

	private static final String OTP_VALIDATION = "OTP VALIDATION SUCESSFUL";
	


	@Override
	@Transactional
	public void registerUser(User user, MultipartFile profilePic) {

		if (user.getDependent() == 'Y') {
			UserHelper.passwordValidation(user.getPassword());
			user.setPassword(UserHelper.getHashedPassWord(user.getPassword()));
			if (StringUtil.isNullOrEmpty(user.getCellNumber())) {
				user.setCellNumber(user.getPrimaryContact());
			}
			user.setDependent('Y');
			userDAO.save(user);
			String verificationId = getVerificationId();
			String hashedVerificationId = UserHelper.getHashedPassWord(verificationId);
			Registration registration = Registration.build(user, hashedVerificationId, null);
			registrationDAO.save(registration);
			Integer otp = OtpUtil.otpToPhone(user.getCellNumber(), "Email Verification");
			registration.setEmailotp(otp);
			registrationDAO.save(registration);
			UserServiceHelper.sendEmailOTP(user, otp, mailProperties);
		} else {
			if (StringUtil.isNullOrEmpty(user.getCellNumber())) {
				throw new IllegalArgumentException("Cell Number can not be null.");
			}
			List<String> pcEnums = Arrays.stream(PrimaryContactEnum.values()).map(pc -> pc.name())
					.collect(Collectors.toList());
			if (StringUtil.isNullOrEmpty(user.getPrimaryContact()) || !pcEnums.contains(user.getPrimaryContact())) {
				throw new IllegalArgumentException(
						"Primary contact either can not be null or it should be in " + pcEnums);
			}
			User userByEmail = userDAO.findOneByProperty(BaseUser.FIELD_emailId, user.getEmailId());
			if (Objects.nonNull(userByEmail)) {
				throw new IllegalArgumentException("The email id  is already registered.");
			}
			UserHelper.passwordValidation(user.getPassword());
			user.setPassword(UserHelper.getHashedPassWord(user.getPassword()));
			if (StringUtil.isNullOrEmpty(user.getCellNumber())) {
				user.setCellNumber(user.getPrimaryContact());
			}
			user.setDependent('N');
			userDAO.save(user);
			String verificationId = getVerificationId();
			String hashedVerificationId = UserHelper.getHashedPassWord(verificationId);
			Registration registration = Registration.build(user, hashedVerificationId, null);
			registrationDAO.save(registration);
			Integer otp = OtpUtil.otpToPhone(user.getCellNumber(), "Email Verification");// send a text to phone
			registration.setEmailotp(otp);
			registrationDAO.save(registration);
			UserServiceHelper.sendEmailOTP(user, otp, mailProperties);

		}
	}

	private String getVerificationId() {
		return UUID.randomUUID().toString();
	}

	@Override
	@Transactional(readOnly = true)
	public boolean userNameExists(String userName) {
		User user = userDAO.findOneByProperty(BaseUser.FIELD_userName, userName);
		return (user != null);
	}

	@Override
	public boolean userEmailRegistered(String userEmail) {
		User user = null;
		user = userDAO.findUserByUserNameAndEmailId(userEmail);
		if (Objects.isNull(user)) {
			user = userDAO.findUserByUserNameAndEmailIdDependent(userEmail);
		}

		return (user != null);
	}

	@Override
	@Transactional()
	public boolean activateRegistration(Long userIdToActivate) {
		Registration registration = registrationDAO.findOneByProperty(BaseRegistration.FIELD_userId, userIdToActivate);

		if (!registration.hasUserVerified()) {
			throw new IllegalArgumentException(
					"User being activated has not verified his account, cannot be activated");
		}
		registration.markAsActivated();
		registrationDAO.save(registration);
		User user = userDAO.findById(userIdToActivate);
		user.markAsActive();
		userDAO.save(user);
		UserServiceHelper.sendAccountActivationEmail(user, loginLinkForUI, mailProperties);

		return true;
	}

	@Override
	public void resendOTPForResetPassword(String emailId, String resetPwdLink, String userName) {
		sendResetPasswordDetails(emailId, resetPwdLink, "Forgot Password", userName);
	}

	@Override
	@Transactional
	public void forgotPassword(String emailId, String resetPwdLink, String userName) {
		sendResetPasswordDetails(emailId, resetPwdLink, "Reset password", userName);

	}

	public void sendResetPasswordDetails(String emailId, String resetPwdLink, String resetReason, String userName) {
		User user = null;
		user = userDAO.emailVerificationUserByUserNameAndEmailId(emailId, userName);
		if (Objects.isNull(user)) {
			user = userDAO.emailVerificationUserByUserNameAndEmailIds(emailId, userName);
		}

		Integer otp = OtpUtil.otpToPhone(user.getCellNumber(), resetReason);// send a text to phone
																			// number
		Map<String, String> model = new HashMap<>();
		model.put(MailContentEnum.LINK.name(), resetPwdLink);
		model.put(MailContentEnum.NAME.name(), user.getFullName());
		model.put(MailContentEnum.OTP.name(), String.valueOf(otp));
		EmailUtil.sendEmailWithHtmlContent(UserServiceHelper.getMailProperties(user, mailProperties),
				"curemetric Reset password info", EMAIL_TEMPLATES.RESET_PASSWORD.getTemplateName(), model);
	}

	@Override
	@Transactional
	public void emailVerification(String emailid, Integer emailotp, String userName) {

		User user = null;
		user = userDAO.emailVerificationUserByUserNameAndEmailId(emailid, userName);
		if (Objects.isNull(user)) {
			user = userDAO.emailVerificationUserByUserNameAndEmailIds(emailid, userName);
		}

		Registration registration = registrationDAO.findOneByProperty(BaseRegistration.FIELD_userId, user.getUserId());
		if (Objects.isNull(registration)) {
			throw new IllegalArgumentException("Email verification details not found.");
		}

		if (!registration.getEmailotp().equals(emailotp)) {
			throw new IllegalArgumentException("Otp is invalid.");
		}
		OtpService otpService = OtpService.getInstance();
	boolean	validate=otpService.validateOtp(user.getCellNumber(), emailotp);
	

		registration.markEmailVerified();

		Integer otp = OtpUtil.otpToPhone(user.getCellNumber(), "Phone Number Verification");// send a
																					// text to
																							// phone
		registration.setOtp(otp);
		registrationDAO.save(registration);

		SendSms.sendSms(
				"is your CUREMETRIC OTP for mobile verification. This code is valid for 10 min. Do not share this OTP with anyone."
						+ otp,
				user.getCellNumber());

		UserServiceHelper.sendPhoneOTP(user, otp, mailProperties);
	}

	@Transactional
	public void activeAssociateUser(String username) {
		User user = userDAO.findOneByProperty(User.FIELD_userName, username);
		if (Objects.isNull(user)) {
			throw new IllegalArgumentException("User not found");
		}
		user.setActive('Y');
		userDAO.save(user);

	}

	@Override
	@Transactional
	public void requestEmailVerificationLink(String emailId) {
		User user = userDAO.findOneByProperty(BaseUser.FIELD_emailId, emailId);

		if (Objects.isNull(user)) {
			throw new IllegalArgumentException(USER_NOT_FOUND_BY_EMAIL);
		}

		String verificationId = getVerificationId();
		String hashedVerificationId = UserHelper.getHashedPassWord(verificationId);
		Registration registration = registrationDAO.findOneByProperty(BaseRegistration.FIELD_userId, user.getUserId());
		if (Objects.isNull(registration)) {
			Integer otp = OtpUtil.otpToPhone(user.getCellNumber(), "Phone Number Verification");
			registration = Registration.build(user, hashedVerificationId, otp);

		}
		registration.resetEmailVerification(hashedVerificationId);
		registration.setActive('Y');
		registrationDAO.save(registration);

		UserServiceHelper.sendEmailVerificationMail(user, verificationId, mailProperties, emailVerificationLink);
	}

	@Override
	@Transactional
	public void phoneVerification(String cellNumber, Integer otp, String userName, Long forUserId) {

		if (forUserId == null) {
			User user = null;
			user = userDAO.findUserByUsercellNumber(cellNumber, userName);
			if (Objects.isNull(user)) {
				user = userDAO.findUserByUsercellNumbers(cellNumber, userName);
			}
			Registration registration = registrationDAO.findOneByProperty(Registration.FIELD_userId, user.getUserId());
			if (Objects.isNull(registration)) {
				throw new IllegalArgumentException("Registration details not found.");
			}
			if (!registration.getOtp().equals(otp)) {
				throw new IllegalArgumentException("Otp is invalid.");
			}
			registration.markPhoneVerified();
			user.markAsActive();
			relationshipDAO.save(Relationship.buildAssocateSelfRelationship(user.getUserId(), forUserId));
			registrationDAO.save(registration);
			userDAO.save(user);
			UserServiceHelper.sendAccountActivationEmail(user, loginLinkForUI, mailProperties);
		} else {
			User user = null;
			user = userDAO.findUserByUsercellNumber(cellNumber, userName);
			if (Objects.isNull(user)) {
				user = userDAO.findUserByUsercellNumbers(cellNumber, userName);
			}
			Registration registration = registrationDAO.findOneByProperty(Registration.FIELD_userId, user.getUserId());
			if (Objects.isNull(registration)) {
				throw new IllegalArgumentException("Registration details not found.");
			}
			if (!registration.getOtp().equals(otp)) {
				throw new IllegalArgumentException("Otp is invalid.");
			}
			registration.markPhoneVerified();
			user.markAsActive();
			relationshipDAO.save(Relationship.buildSelfRelationship(user.getUserId(), forUserId));
			registrationDAO.save(registration);
			userDAO.save(user);
			UserServiceHelper.sendAccountActivationEmail(user, loginLinkForUI, mailProperties);
		}

	}

	@Override
	@Transactional
	public void resetPassword(String emailId, String newPassword, Integer otp, String userName) {
		User user = null;

		user = userDAO.emailVerificationUserByUserNameAndEmailId(emailId, userName);

		if (Objects.isNull(user)) {
			user = userDAO.emailVerificationUserByUserNameAndEmailIds(emailId, userName);
		}
		Registration registration = registrationDAO.findOneByProperty(Registration.FIELD_userId, user.getUserId());

		if (!registration.getEmailotp().equals(otp)) {
			throw new IllegalArgumentException("Otp is invalid.");
		}
		user.setPassword(UserHelper.getHashedPassWord(newPassword));
		UserHelper.passwordValidation(newPassword);
		userDAO.save(user);

	}

	@Override
	@Transactional
	public void requestOTPForPhoneVerification(String phonenumber, String username) {
		User user = userDAO.findUserByUsercellNumbers(phonenumber, username);

		if (Objects.isNull(user)) {
			throw new IllegalArgumentException(USER_NOT_FOUND_BY_EMAIL);
		}

		String cellNumber = user.getCellNumber();
		Integer otp = OtpUtil.otpToPhone(user.getCellNumber(), "Phone Verification");// send a text to
																						// phone number

		Registration registration = registrationDAO.findOneByProperty(BaseRegistration.FIELD_userId, user.getUserId());

		if (Objects.isNull(registration)) {
			throw new RuntimeException("Invalid Request - We could not validate the details provided.");
		}

		registration.setOtp(otp);
		registration.setOtpVerifiedOn(DateUtil.getDate());
		registrationDAO.save(registration);
		SendSms.sendSms("Hi Welcome To CureMetric! Your new OTP is:" + otp, cellNumber);

	}

	@Override
	@Transactional
	public void deleteUser(Long userId) {
		User user = userDAO.findUserByUserId(userId);
		if (Objects.isNull(user)) {
			throw new IllegalArgumentException("User not fuond.");
		}
		user.setActive('N');
		userDAO.save(user);
	}

	@Override
	@Transactional
	public void resendOTPForEmailId(String emailId, String userName) {
		User user = null;
		user = userDAO.emailVerificationUserByUserNameAndEmailId(emailId, userName);

		if (Objects.isNull(user)) {
			user = userDAO.emailVerificationUserByUserNameAndEmailIds(emailId, userName);
		}

		if (user == null) {
			throw new IllegalArgumentException(USER_NOT_FOUND_BY_EMAIL);
		}
		Registration registration = registrationDAO.findOneByProperty(Registration.FIELD_userId, user.getUserId());

		String cellNumber = user.getCellNumber();
		if (StringUtils.isEmpty(cellNumber)) {
			throw new IllegalArgumentException("Cellnumber can not be null");
		}

		Integer otp = OtpUtil.otpToPhone(cellNumber, " Resend OTP ");

		registration.setEmailotp(otp);
		registrationDAO.save(registration);// send a text to phone

		EmailUtil.sendEmailWithHtmlContent(UserServiceHelper.getMailProperties(user, mailProperties),
				"CureMetric   Resend OTP", EMAIL_TEMPLATES.RESET_PASSWORD.getTemplateName(),
				UserServiceHelper.prepareModelForOTP(otp, user.getFullName()));

	}

	@SuppressWarnings({ "unused", "null" })
	@Override
	@Transactional
	public UserResponseDTO getUserByEmail(String userEmail, Long UserId, String username) {
		User user = null;
		UserResponseDTO userResponseDTO = null;
		user = userDAO.emailVerificationUserByUserNameAndEmailId(userEmail, username);

		if (Objects.isNull(user)) {
			user = userDAO.emailVerificationUserByUserNameAndEmailIds(userEmail, username);
		}

		if (Objects.isNull(user)) {
			return null;
		}
		Personaldetail personaldetail = personaldetailDAO.findOneByProperty(Personaldetail.FIELD_userId,
				user.getUserId());
		user.setPersonaldetail(personaldetail);
		userResponseDTO = UserResponseDTO.builidUserObject(user);
		userResponseDTO.setPersonaldetail(personaldetail);
		Relationship relationship = relationshipDAO.checkRelationshipExists(user.getUserId(), UserId);
		if (relationship == null) {
			userResponseDTO.setRelationShipExit('N');
		} else {
			userResponseDTO.setRelationShipExit('Y');
		}

		return userResponseDTO;
	}

	@Override
	public boolean userphoneNumberRegistered(String cellNumber) {

		User user = userDAO.findOneByProperty(BaseUser.FIELD_cellNumber, cellNumber);

		if (Objects.isNull(user)) {
			return false;
		}
		return true;
	}

	@Override
	public void requestOTPForEamilVerification(String emailId, String username) {
		User user = null;
		user = userDAO.emailVerificationUserByUserNameAndEmailId(emailId, username);

		if (Objects.isNull(user)) {
			user = userDAO.emailVerificationUserByUserNameAndEmailIds(emailId, username);
		}

		String cellNumber = user.getCellNumber();
		if (Objects.isNull(cellNumber)) {
			throw new IllegalArgumentException("cellNumber can not be null");
		}
		Integer otp = OtpUtil.otpToPhone(user.getCellNumber(), "Email Verification");// send a text to
																						// phone number
		Registration registration = registrationDAO.findOneByProperty(BaseRegistration.FIELD_userId, user.getUserId());

		if (Objects.isNull(registration)) {
			throw new RuntimeException("Invalid Request - We could not validate the details provided");
		}

		registration.setEmailotp(otp);
		registration.setActive('N');
		registrationDAO.save(registration);

		UserServiceHelper.sendEmailOTP(user, otp, mailProperties);

	}

	@Override
	@Transactional
	public void saveAssocateUserStatus(String emailid, StatusType status) {
		User user = userDAO.findOneByProperty(User.FIELD_emailId, emailid);
		if (Objects.isNull(user)) {
			throw new IllegalArgumentException("User can not be null");
		}
		if (status.equals(StatusType.VERIFIED)) {
			user.setStatus(StatusType.VERIFIED.name());
			user.setActive('Y');
		} else {
			user.setStatus(StatusType.REJECTED.name());
			user.setActive('N');
		}
		userDAO.save(user);
	}

	@Override
	@Transactional
	public void resendEmailForAssocateUser(String emailId, String userame) {
		User dbuser = null;
		dbuser = userDAO.emailVerificationUserByUserNameAndEmailId(emailId, userame);
		if (Objects.isNull(dbuser)) {
			dbuser = userDAO.emailVerificationUserByUserNameAndEmailIds(emailId, userame);
		}

		String name = dbuser.getFirstName().toUpperCase() + " " + dbuser.getLastName().toUpperCase();
		Map<String, String> model = new HashMap<>();

		model.put(MailContentEnum.NAME.name(), name);
		model.put(MailContentEnum.LINK.name(),
				String.format(emailVerificationLink, StatusType.VERIFIED.name(), dbuser.getEmailId()));
		model.put(MailContentEnum.LINK1.name(),
				String.format(emailVerificationLink, StatusType.REJECTED.name(), dbuser.getEmailId()));

		EmailUtil.sendAsscotedEmailWithHtmlContent(UserServiceHelper.getMailProperties(dbuser, mailProperties),
				"CureMetric User association ", EMAIL_TEMPLATES.ASSOCIATEUSER_EMAIL_VERIFICATION.getTemplateName(),
				model);
	}

	@Override
	@Transactional
	public UserResponseDTO getPrimaryDetails(Long userid, ForUser forUser, RELATIONSHIP_TYPE relationship_TYPE) {
		Long userId = getAssociateUserIdFromForUser(forUser, ACCESS_TYPE.PERSONAL_DETAILS, relationship_TYPE);
		User user = userDAO.findOneByProperty(User.FIELD_userId, userId);

		if (Objects.isNull(user)) {
			throw new IllegalArgumentException("User  not register.");
		}
		Personaldetail personaldetail = personaldetailDAO.findOneByProperty(Personaldetail.FIELD_userId,
				user.getUserId());
		Address address = addressDAO.findOneByProperty(Address.FIELD_userId, user.getUserId());
		UserResponseDTO userResponseDTO = UserResponseDTO.builidUserObject(user);
		userResponseDTO.setAddress(Arrays.asList(address));
		userResponseDTO.setPersonaldetail(personaldetail);
		return userResponseDTO;
	}

	public Long getAssociateUserIdFromForUser(ForUser forUser, ACCESS_TYPE accessDetails,
			RELATIONSHIP_TYPE relationship_TYPE) {
		Long userId = null;
		if (userDAO.hasUserAccessToDetails(forUser.getUserId(), forUser.getForUserId(), accessDetails,
				relationship_TYPE)) {
			userId = forUser.getForUserId();
		} else if (forUser.isCallForSelf()) {
			userId = forUser.getUserId();
		}

		if (Objects.isNull(userId)) {
			throw new IllegalArgumentException("Invalid ForUser");
		}
		return userId;
	}

	@Override
	@Transactional
	public ProfileCompltedDTO userProfileCompleted(Long userid) {
		ProfileCompltedDTO profileCompltedDTO = new ProfileCompltedDTO();
		User user = userDAO.findOneByProperty(User.FIELD_userId, userid);
		Personaldetail personaldetail = personaldetailDAO.findOneByProperty(Personaldetail.FIELD_userId,
				user.getUserId());
		if (Objects.isNull(personaldetail)) {
			profileCompltedDTO.setIsPersonalDetailcompleted('N');
			profileCompltedDTO.setIsAddrssComplted('N');
			return profileCompltedDTO;
		}
		Address address = addressDAO.findOneByProperty(Address.FIELD_userId, user.getUserId());
		if (Objects.isNull(address)) {
			profileCompltedDTO.setIsAddrssComplted('N');
			profileCompltedDTO.setIsPersonalDetailcompleted('Y');
			return profileCompltedDTO;
		}
		profileCompltedDTO.setIsAddrssComplted('Y');
		profileCompltedDTO.setIsPersonalDetailcompleted('Y');
		return profileCompltedDTO;

	}

	@Override
	@Transactional
	public UserResponseDTO getUserDetailsByEmail(String userEmail, String userName) {
		User user = null;
		user = userDAO.emailVerificationUserByUserNameAndEmailId(userEmail, userName);
		if (Objects.isNull(user)) {
			user = userDAO.emailVerificationUserByUserNameAndEmailIds(userEmail, userName);

		}
		if (Objects.isNull(user)) {
			return null;
		}
		UserResponseDTO userResponseDTO = UserResponseDTO.builidUserObject(user);
		return userResponseDTO;
	}

	@Transactional
	public void activateAssociateUserbyRelationUserId(Long relationshipId, StatusType statusType) {
		Relationship relationship = relationshipDAO.findById(relationshipId);
		if (Objects.isNull(relationship)) {
			throw new IllegalArgumentException("Relationship not Found For given id..");
		}
		if (StatusType.REJECTED == statusType) {
			relationship.desactivate();
			relationshipDAO.save(relationship);
		} else {
			relationship.activate();
			relationshipDAO.save(relationship);
		}

	}

	@Override
	@Transactional
	public String otpVerificationForPhone(Integer otp) {

		Registration registration = registrationDAO.findOneByProperty(Registration.FIELD_otp, otp);

		if (Objects.isNull(registration)) {
			throw new IllegalArgumentException("Invalid otp.");

		} else if (!registration.getEmailotp().equals(otp)) {
			throw new IllegalArgumentException("Invalid otp.");
		}
		return OTP_VALIDATION;

	}

	@Override
	@Transactional
	public String otpVerificationForEmail(Integer otp, String emailId, String userName) {
		User user = null;
		user = userDAO.emailVerificationUserByUserNameAndEmailId(emailId, userName);

		if (Objects.isNull(user)) {
			user = userDAO.emailVerificationUserByUserNameAndEmailIds(emailId, userName);
		}
		Registration registration = registrationDAO.findOneByProperty(Registration.FIELD_userId, user.getUserId());

		if (!registration.getEmailotp().equals(otp)) {
			throw new IllegalArgumentException("Otp is invalid.");
		}
		return OTP_VALIDATION;

	}

	@Override
	@Transactional
	public boolean oldPaswwordVerification(String emailid, String paswword, String username) {
		User user = null;
		user = userDAO.emailVerificationUserByUserNameAndEmailId(emailid, username);
		if (Objects.isNull(user)) {
			user = userDAO.emailVerificationUserByUserNameAndEmailIds(emailid, username);
		}
		String hashpawsd = UserHelper.getHashedPassWord(user.getPassword());
		if (UserHelper.checkpw(paswword, hashpawsd)) {
			return false;

		}
		return true;
	}

	@Override
	@Transactional
	public List<User> getUserAllUser() {
		List<User> user = (List<User>) userDAO.findAll();
		return user;
	}

	@Override
	@Transactional
	public PrivalgeDTO associateUserPrivalge(Long relationshipId) {
		Relationship relationship = relationshipDAO.findById(relationshipId);
		PrivalgeDTO privalgeDTO = PrivalgeDTO.buildPrivalge(relationship);
		return privalgeDTO;
	}

	@Override
	@Transactional
	public void associateUserPrivalgeUpdate(PrivalgeDTO privalgeDTO) {
		Relationship relationship = relationshipDAO.findById(privalgeDTO.getRelationShipId());
		if (Objects.isNull(relationship)) {
			throw new IllegalArgumentException("Relationship Is Not  Found");
		}
		if (Objects.nonNull(privalgeDTO.getAccessToInsurance())) {
			relationship.setAccessToInsurance(privalgeDTO.getAccessToInsurance());
		}
		if (Objects.nonNull(privalgeDTO.getAccessToPaymentDetails())) {
			relationship.setAccessToPaymentDetails(privalgeDTO.getAccessToPaymentDetails());
		}
		if (Objects.nonNull(privalgeDTO.getAccessToPersonalDetails())) {
			relationship.setAccessToPersonalDetails(privalgeDTO.getAccessToPersonalDetails());
		}
		if (Objects.nonNull(privalgeDTO.getAccessToPrescription())) {
			relationship.setAccessToPrescription(privalgeDTO.getAccessToPrescription());
		}
		if (Objects.nonNull(privalgeDTO.getAccessToRecords())) {
			relationship.setAccessToRecords(privalgeDTO.getAccessToRecords());
		}
		if (Objects.nonNull(privalgeDTO.getAccessToAppoitment())) {
			relationship.setAccessToAppoitment(privalgeDTO.getAccessToAppoitment());
		}
		if (Objects.nonNull(privalgeDTO.getAccessToProfile())) {
			relationship.setAccessToProfile(privalgeDTO.getAccessToProfile());
		}
		if (Objects.nonNull(privalgeDTO.getAccessToNotifaction())) {
			relationship.setAccessToNotifaction(privalgeDTO.getAccessToNotifaction());
		}
		if (Objects.nonNull(privalgeDTO.getAccessToVitals())) {
			relationship.setAccessToVitals(privalgeDTO.getAccessToVitals());
		}
		relationshipDAO.save(relationship);

	}

	@Override
	@Transactional
	public Collection<DoctorDashBoardDTO> getDoctorDashBoardForDoctorIdAndAppointmentFor(Long doctorId,
			String appointmentFor) {

		return userDAO.getDoctorDashBoardForDoctorIdAndAppointmentFor(doctorId, appointmentFor);
	}
}
