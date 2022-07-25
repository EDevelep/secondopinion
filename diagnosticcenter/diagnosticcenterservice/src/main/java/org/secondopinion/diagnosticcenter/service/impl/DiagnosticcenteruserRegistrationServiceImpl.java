package org.secondopinion.diagnosticcenter.service.impl;

import java.io.IOException;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.UUID;
import java.util.stream.Collectors;
import org.secondopinion.diagnosticcenter.dao.DiagnosticcenterDAO;
import org.secondopinion.diagnosticcenter.dao.DiagnosticcenteraddressDAO;
import org.secondopinion.diagnosticcenter.dao.DiagnosticcenteruserDAO;
import org.secondopinion.diagnosticcenter.dao.RegistrationDAO;
import org.secondopinion.diagnosticcenter.dao.RoleDAO;
import org.secondopinion.diagnosticcenter.dao.UserroleDAO;
import org.secondopinion.diagnosticcenter.domain.BaseRegistration;
import org.secondopinion.diagnosticcenter.dto.Diagnosticcenter;
import org.secondopinion.diagnosticcenter.dto.Diagnosticcenteruser;
import org.secondopinion.diagnosticcenter.dto.Registration;
import org.secondopinion.diagnosticcenter.dto.Role;
import org.secondopinion.diagnosticcenter.dto.UpdatePasswordRequest;
import org.secondopinion.diagnosticcenter.dto.Userrole;
import org.secondopinion.diagnosticcenter.service.IDiagnosticcenteruserRegistrationService;
import org.secondopinion.enums.PrimaryContactEnum;
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
import org.springframework.web.multipart.MultipartFile;

@Service
public class DiagnosticcenteruserRegistrationServiceImpl implements IDiagnosticcenteruserRegistrationService {

	@Value("${createPwdLinkUI}")
	private String createPwdLinkUI;

	@Autowired
	private DiagnosticcenteruserDAO diagnosticcenteruserDAO;

	@Autowired
	private RegistrationDAO registrationDAO;
	@Autowired
	private RoleDAO rolesDAO;

	@Autowired
	private DiagnosticcenterDAO diagnosticcenterDAO;
	@Autowired
	private UserroleDAO userRoleDAO;
	@Autowired
	private MailProperties mailProperties;

	@Value("${emailVerificationLink}")
	private String emailVerificationLink;

	@Value("${loginLinkForUI}")
	private String loginLinkForUI;
	@Autowired
	private DiagnosticcenteraddressDAO diagnosticcenteraddressDAO;

	@Override
	@Transactional
	public void addDiagnosticcenteruser(Diagnosticcenteruser diagnosticcenteruser, boolean createFromDC,
			List<String> roleNames) {
		if (!roleNames.contains(Role.RoleEnum.ADMIN.name())) {
			throw new IllegalArgumentException("You are not authorized person to create a new user.");
		}

		saveDiagnosticcenteruser(diagnosticcenteruser);

		Diagnosticcenter diagnosticcenter = diagnosticcenterDAO.findOneByProperty(
				Diagnosticcenter.FIELD_primaryDataCenterAddressId, diagnosticcenteruser.getDiagnosticCenterAddressId());
		if (Objects.isNull(diagnosticcenter)) {
			throw new IllegalArgumentException(
					"The diagnosticcenter is not found for this DiagnosticCenterAddressId .");
		}
		Registration registration = Registration.build(diagnosticcenteruser,
				diagnosticcenteruser.getDiagnosticCenterUserId(), diagnosticcenter.getDiagnosticcenterId());
		registrationDAO.save(registration);
		emailSendToChangePassword(diagnosticcenteruser);
	}

	private void saveDiagnosticcenteruser(Diagnosticcenteruser diagnosticcenteruser) {
		if (StringUtil.isNullOrEmpty(diagnosticcenteruser.getCellNumber())) {
			throw new IllegalArgumentException("Cell Number can not be null.");
		}
		String primaryContact = diagnosticcenteruser.getPrimaryContact();
		List<String> pcEnums = Arrays.stream(PrimaryContactEnum.values()).map(pc -> pc.name())
				.collect(Collectors.toList());
		if (StringUtil.isNullOrEmpty(primaryContact) || !pcEnums.contains(primaryContact)) {
			throw new IllegalArgumentException("primary contact either can not be null or it should be in " + pcEnums);
		}

		String password = diagnosticcenteruser.getPassword();

		if (!StringUtil.isNullOrEmpty(password)) {
			UserHelper.passwordValidation(password);
		} else {
			diagnosticcenteruser.setPassword(UserHelper.generateRandomPassword(10));
		}
		Diagnosticcenteruser dbDiagnosticcenteruser = diagnosticcenteruserDAO.getByDiagnosticcenteraddressIdAndEmailId(
				diagnosticcenteruser.getDiagnosticCenterAddressId(), diagnosticcenteruser.getEmailId(), Boolean.TRUE);
		if (Objects.nonNull(dbDiagnosticcenteruser)) {
			throw new IllegalArgumentException("The email id already exists for this Diagnosticcenter.");
		}
		dbDiagnosticcenteruser = diagnosticcenteruserDAO.getByDiagnosticcenteraddressIdAndEmailId(
				diagnosticcenteruser.getDiagnosticCenterAddressId(), diagnosticcenteruser.getEmailId(), Boolean.FALSE);
		if (Objects.nonNull(dbDiagnosticcenteruser)) {
			throw new IllegalArgumentException("The user is already register with the other Diagnosticcenter.");
		}
		diagnosticcenteruser.setPassword(UserHelper.getHashedPassWord(diagnosticcenteruser.getPassword()));
		String verificationId = UUID.randomUUID().toString();
		String hashedVerificationId = UserHelper.getHashedPassWord(verificationId);
		diagnosticcenteruser.setVerificationId(hashedVerificationId);
		diagnosticcenteruserDAO.save(diagnosticcenteruser);
		saveUserrole(diagnosticcenteruser);

	}

	@SuppressWarnings("unused")
	private void emailVerificationMailSend(Diagnosticcenteruser diagnosticcenteruser, String verificationId) {

		String name = diagnosticcenteruser.getFirstName() + " " + diagnosticcenteruser.getLastName();
		Map<String, String> model = new HashMap<>();
		model.put(MailContentEnum.LINK.name(),
				String.format(emailVerificationLink, verificationId, diagnosticcenteruser.getUiHostURL()));
		model.put(MailContentEnum.NAME.name(), name);

		String classpathEmailTemplate = "classpath:mail-emailverification.html";
		EmailUtil.sendEmailWithHtmlContent(getMailProperties(diagnosticcenteruser), "CureMetric Email Verification",
				classpathEmailTemplate, model);
	}

	private void saveUserrole(Diagnosticcenteruser diagnosticcenteruser) {
		List<Userrole> userroles = userRoleDAO.getByRoleIdAndUserId(diagnosticcenteruser.getRoleId(),
				diagnosticcenteruser.getDiagnosticCenterUserId());
		if (CollectionUtils.isEmpty(userroles)) {
			Userrole userrole = new Userrole();
			userrole.setRoleId(diagnosticcenteruser.getRoleId());
			userrole.setDiagnosticcenterUserId(diagnosticcenteruser.getDiagnosticCenterUserId());
			userrole.setActive('Y');
			userRoleDAO.save(userrole);
		}
	}

	private void emailSendToChangePassword(Diagnosticcenteruser diagnosticcenteruser) {
		if (StringUtil.isNullOrEmpty(diagnosticcenteruser.getUiHostURL())) {
			throw new IllegalArgumentException("uiHostURL field can not be null.");
		}
		String name = diagnosticcenteruser.getFirstName() + " " + diagnosticcenteruser.getLastName();
		Map<String, String> model = new HashMap<>();
		model.put(MailContentEnum.LINK.name(),
				String.format(diagnosticcenteruser.getUiHostURL() + "/" + createPwdLinkUI,
						diagnosticcenteruser.getDiagnosticCenterUserId()));
		model.put(MailContentEnum.NAME.name(), name);
		String classpathEmailTemplate = "classpath:mail-resetpwd.html";
		EmailUtil.sendEmailWithHtmlContent(getMailProperties(diagnosticcenteruser), "Curemetric Reset password",
				classpathEmailTemplate, model);
	}

	// validating username
	@Override
	@Transactional(readOnly = true)
	public Diagnosticcenteruser getByDiagnosticcenterIdAndEmailId(Long diagnosticcenterId, String emailId) {

		return diagnosticcenteruserDAO.getByDiagnosticcenteraddressIdAndEmailId(diagnosticcenterId, emailId, true);
	}

	@Override
	@Transactional()
	public Diagnosticcenteruser getDiagnosticcenteruserByVerificationId(String verificationId,
			String passwordTypeEnum) {
		Diagnosticcenteruser diagnosticcenteruser = diagnosticcenteruserDAO
				.findOneByProperty(Diagnosticcenteruser.FIELD_verificationId, verificationId);

		if (Objects.isNull(diagnosticcenteruser)) {
			throw new IllegalArgumentException("The Diagnosticcenter user has not been registered");
		}
		if (!StringUtil.isNullOrEmpty(passwordTypeEnum)) {
			Integer emailotp = OtpUtil.otpToPhone(diagnosticcenteruser.getCellNumber(), passwordTypeEnum);// send
																											// a
																											// text
																											// to
			diagnosticcenteruser.setEmailotp(emailotp);
			diagnosticcenteruserDAO.save(diagnosticcenteruser);
			String name = diagnosticcenteruser.getFirstName() + " " + diagnosticcenteruser.getLastName();
			Map<String, String> model = new HashMap<>();
			model.put(MailContentEnum.NAME.name(), name);
			model.put(MailContentEnum.OTP.name(), String.valueOf(emailotp));
			String classpathEmailTemplate = "classpath:mail-phoneVerification.html";
			EmailUtil.sendEmailWithHtmlContent(getMailProperties(diagnosticcenteruser),
					"Curemetric Create password - OTP", classpathEmailTemplate, model);
		}
		return diagnosticcenteruser;
	}

	private MailProperties getMailProperties(Diagnosticcenteruser Diagnosticcenteruser) {
		MailProperties properties = mailProperties.clone();
		properties.addToRecipient(Diagnosticcenteruser.getEmailId());
		return properties;
	}

	@Override
	@Transactional
	public void resetPassword(UpdatePasswordRequest updatePasswordRequest) {
		UserHelper.passwordValidation(updatePasswordRequest.getNewPassword());
		Diagnosticcenteruser diagnosticcenteruser = diagnosticcenteruserDAO
				.findOneByProperty(Diagnosticcenteruser.FIELD_emailId, updatePasswordRequest.getEmailId());
		updatePassword(diagnosticcenteruser, updatePasswordRequest.getNewPassword(), updatePasswordRequest.getOtp());
	}

	@Override
	@Transactional
	public void resetPasswordByDiagnosticcenter(UpdatePasswordRequest updatePasswordRequest) {
		UserHelper.passwordValidation(updatePasswordRequest.getNewPassword());
		Diagnosticcenteruser diagnosticcenteruser = diagnosticcenteruserDAO
				.findOneByProperty(Diagnosticcenteruser.FIELD_emailId, updatePasswordRequest.getEmailId());

		if (Objects.isNull(diagnosticcenteruser)) {
			throw new IllegalArgumentException("Email Id is Not Their");
		}

		updatePassword(diagnosticcenteruser, updatePasswordRequest.getNewPassword(), updatePasswordRequest.getOtp());

	}

	private void updatePassword(Diagnosticcenteruser diagnosticcenteruser, String newPassword, Integer otp) {
		if (Objects.isNull(diagnosticcenteruser)) {
			throw new IllegalArgumentException("The diagnosticcenteruser has not been registered");
		}

		Diagnosticcenter diagnosticcenter = diagnosticcenterDAO.findOneByProperty(Diagnosticcenter.FIELD_primaryUser,
				diagnosticcenteruser.getDiagnosticCenterUserId());

		if (Objects.isNull(diagnosticcenter)) {
			throw new IllegalArgumentException("The diagnosticcenter has not been registered");
		}

		Registration registration = registrationDAO.findOneByProperty(BaseRegistration.FIELD_diagnosticcenterId,
				diagnosticcenter.getDiagnosticcenterId());

		if (!registration.getEmailotp().equals(otp)) {
			throw new IllegalArgumentException("otp is invalid");
		}

		diagnosticcenteruser.setPassword(UserHelper.getHashedPassWord(newPassword));
		diagnosticcenteruserDAO.save(diagnosticcenteruser);
	}

	@Override
	@Transactional
	public void forgotPassword(String emailId, String resetPwdLink) {
		Diagnosticcenteruser user = diagnosticcenteruserDAO.getByDiagnosticcenteraddressIdAndEmailId(null, emailId,
				false);
		if (Objects.isNull(user)) {
			throw new IllegalArgumentException("The Diagnosticcenter  Email has not been registered");
		}

		Integer otp = OtpUtil.otpToPhone(user.getCellNumber(), "Forgot Password");// send a text to
																					// phone number
		String name = user.getFirstName() + " " + user.getLastName();
		Map<String, String> model = new HashMap<>();
		model.put(MailContentEnum.LINK.name(), resetPwdLink);
		model.put(MailContentEnum.NAME.name(), name);
		model.put(MailContentEnum.OTP.name(), String.valueOf(otp));

		String classpathEmailTemplate = "classpath:mail-resetpwd.html";

		EmailUtil.sendEmailWithHtmlContent(getMailProperties(user), "Curemetric Reset password info",
				classpathEmailTemplate, model);

	}

	@Override
	@Transactional(readOnly = true)
	public void verifyemail(Long diagnosticcenterId, String emailId) {
		if (Objects.isNull(diagnosticcenterId)) {
			Diagnosticcenteruser dbDiagnosticcenteruser = diagnosticcenteruserDAO
					.findOneByProperty(Diagnosticcenteruser.FIELD_emailId, emailId);
			if (Objects.nonNull(dbDiagnosticcenteruser)) {
				throw new IllegalArgumentException("The email id already exists.");
			}
		}
		Diagnosticcenteruser dbDiagnosticcenteruser = diagnosticcenteruserDAO
				.getByDiagnosticcenteraddressIdAndEmailId(diagnosticcenterId, emailId, Boolean.TRUE);
		if (Objects.nonNull(dbDiagnosticcenteruser)) {
			throw new IllegalArgumentException("The email id already exists for this Diagnosticcenter.");
		}
		dbDiagnosticcenteruser = diagnosticcenteruserDAO.getByDiagnosticcenteraddressIdAndEmailId(diagnosticcenterId,
				emailId, Boolean.FALSE);
		if (Objects.nonNull(dbDiagnosticcenteruser)) {
			throw new IllegalArgumentException("The user is already register with the other Diagnosticcenter.");
		}
	}

	@Override
	@Transactional
	public void activateOrDeactivateUser(Long DiagnosticcenterUserId, boolean deactivateUser) {
		Diagnosticcenteruser dbDiagnosticcenteruser = diagnosticcenteruserDAO.findById(DiagnosticcenterUserId);
		if (Objects.isNull(dbDiagnosticcenteruser)) {
			throw new IllegalArgumentException("The user not found.");
		}
		dbDiagnosticcenteruser.setActive('Y');
		if (deactivateUser) {
			dbDiagnosticcenteruser.setActive('N');
		}
		diagnosticcenteruserDAO.save(dbDiagnosticcenteruser);
	}

	@Override
	@Transactional(readOnly = true)
	public Response<List<Diagnosticcenteruser>> getAssociatedUsers(Long DiagnosticcenterId, Integer pageNum,
			Integer maxResults) {
		Response<List<Diagnosticcenteruser>> list = diagnosticcenteruserDAO
				.getAssociatedUsersOfAddress(DiagnosticcenterId, pageNum, maxResults);
		List<Diagnosticcenteruser> diagnosticcenteruser = list.getData();
		if (!CollectionUtils.isEmpty(diagnosticcenteruser)) {
			diagnosticcenteruser.forEach(pu -> {
				List<Userrole> userRoles = userRoleDAO.getByRoleIdAndUserId(null, pu.getDiagnosticCenterUserId());
				if (CollectionUtils.isEmpty(userRoles)) {
					throw new IllegalArgumentException("Roles are not exists");
				}
				List<Integer> roleIds = userRoles.stream().map(ur -> ur.getRoleId()).collect(Collectors.toList());

				pu.setRoles(rolesDAO.getByUserroles(roleIds));
			});

		}
		return list;

	}

	@Override
	@Transactional(readOnly = true)
	public Diagnosticcenteruser getPrimaryDetails(Long Diagnosticcenteruserid) {

		Diagnosticcenteruser Diagnosticcenteruser = diagnosticcenteruserDAO.findById(Diagnosticcenteruserid);
		if (Objects.isNull(Diagnosticcenteruser)) {
			throw new IllegalArgumentException("Diagnosticcenter user not found");
		}
		Diagnosticcenteruser.setPassword("");
		return Diagnosticcenteruser;
	}

	@Override
	@Transactional
	public void updatePrimaryDetails(Diagnosticcenteruser diagnosticcenteruser) {

		Diagnosticcenteruser dbDiagnosticcenteruser = diagnosticcenteruserDAO
				.findById(diagnosticcenteruser.getDiagnosticCenterUserId());
		if (Objects.isNull(dbDiagnosticcenteruser)) {
			throw new IllegalArgumentException("Diagnosticcenter user not found.");
		}
		Diagnosticcenter diagnosticcenter = diagnosticcenterDAO.findOneByProperty(Diagnosticcenter.FIELD_primaryUser,
				dbDiagnosticcenteruser.getDiagnosticCenterUserId());

		if (Objects.isNull(diagnosticcenter)) {
			throw new IllegalArgumentException("Diagnosticcenter user not found.");
		}
		diagnosticcenter.setName(diagnosticcenteruser.getDiagnosticName());
		diagnosticcenter.setCellNumber(diagnosticcenteruser.getCellNumber());
		dbDiagnosticcenteruser.setFirstName(diagnosticcenteruser.getFirstName());
		dbDiagnosticcenteruser.setDiagnosticName(diagnosticcenteruser.getDiagnosticName());
		dbDiagnosticcenteruser.setLastName(diagnosticcenteruser.getLastName());
		dbDiagnosticcenteruser.setCellNumber(diagnosticcenteruser.getCellNumber());
		dbDiagnosticcenteruser.setMiddleName(diagnosticcenteruser.getMiddleName());
		diagnosticcenteruserDAO.save(dbDiagnosticcenteruser);
		diagnosticcenterDAO.save(diagnosticcenter);
	}

	@Override
	@Transactional(readOnly = true)
	public Collection<Role> getAllRoles() {

		return rolesDAO.findAll();
	}

	@Override
	@Transactional
	public void resetPasswordForDiagnosticcenteruser(UpdatePasswordRequest updatePasswordRequest) {
		Diagnosticcenteruser diagnosticcenteruser = diagnosticcenteruserDAO.findOneByProperty(
				Diagnosticcenteruser.FIELD_verificationId, updatePasswordRequest.getVerificationid());
		if (Objects.isNull(diagnosticcenteruser)) {
			throw new IllegalArgumentException("Diagnosticcenter user not found.");
		}
		if (!diagnosticcenteruser.getEmailotp().equals(updatePasswordRequest.getOtp())) {
			throw new IllegalArgumentException("otp is invalid");
		}
		Registration registration = registrationDAO.findOneByProperty(Registration.FIELD_diagnosticCenterUserId,
				diagnosticcenteruser.getDiagnosticCenterUserId());
		if (Objects.isNull(registration)) {
			throw new IllegalArgumentException("registration is not found.");
		}
		registration.setActive('Y');
		registrationDAO.save(registration);
		diagnosticcenteruser.setPassword(UserHelper.getHashedPassWord(updatePasswordRequest.getNewPassword()));
		diagnosticcenteruser.setActive('Y');
		diagnosticcenteruserDAO.save(diagnosticcenteruser);

	}

	@Override
	@Transactional
	public void uploadProfilePic(Long diagnosticcenterId, MultipartFile profilePic) {
		if (Objects.isNull(profilePic)) {
			throw new IllegalArgumentException("ProfilePic Can Not be null");
		}

		Diagnosticcenter diagnosticcenter = diagnosticcenterDAO.findById(diagnosticcenterId);

		if (Objects.isNull(diagnosticcenter)) {
			throw new IllegalArgumentException("No Diagnosticcenter Avilable For Given Id");
		}
		try {
			byte[] profilePicBlob = profilePic.getBytes();
			diagnosticcenter.setProfilePic(profilePicBlob);
		} catch (IOException e) {
			throw new IllegalArgumentException("Invalid Profile pic.");
		}

		diagnosticcenterDAO.save(diagnosticcenter);
	}

	@Override
	@Transactional
	public Diagnosticcenteruser addprimaryDiagnosticcenteruser(Diagnosticcenteruser diagnosticcenteruser) {

		saveDiagnosticcenteruser(diagnosticcenteruser);
		return diagnosticcenteruser;
	}

	@Override
	@Transactional
	public void uploadReport(Long diagnosticcenterId, MultipartFile report) {
		if (Objects.isNull(report)) {
			throw new IllegalArgumentException("ProfilePic Can Not be null");
		}

		Diagnosticcenter diagnosticcenter = diagnosticcenterDAO.findById(diagnosticcenterId);

		if (Objects.isNull(diagnosticcenter)) {
			throw new IllegalArgumentException("No Diagnosticcenter Avilable For Given Id");
		}
		try {
			byte[] profilePicBlob = report.getBytes();
			diagnosticcenter.setReport(profilePicBlob);
		} catch (IOException e) {
			throw new IllegalArgumentException("Invalid Report pic.");
		}

		diagnosticcenterDAO.save(diagnosticcenter);

	}
}
