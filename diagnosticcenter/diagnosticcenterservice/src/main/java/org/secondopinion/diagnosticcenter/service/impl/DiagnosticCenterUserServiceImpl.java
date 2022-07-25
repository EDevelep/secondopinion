package org.secondopinion.diagnosticcenter.service.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.UUID;
import java.util.stream.Collectors;

import org.secondopinion.diagnosticcenter.dao.AppointmentDAO;
import org.secondopinion.diagnosticcenter.dao.DiagnosticcenterDAO;
import org.secondopinion.diagnosticcenter.dao.DiagnosticcenteraddressDAO;
import org.secondopinion.diagnosticcenter.dao.DiagnosticcenteruserDAO;
import org.secondopinion.diagnosticcenter.dao.RegistrationDAO;
import org.secondopinion.diagnosticcenter.dao.RoleDAO;
import org.secondopinion.diagnosticcenter.dao.ScheduleDAO;
import org.secondopinion.diagnosticcenter.dao.SchedulehoursDAO;
import org.secondopinion.diagnosticcenter.dao.UserroleDAO;
import org.secondopinion.diagnosticcenter.dto.Appointment;
import org.secondopinion.diagnosticcenter.dto.Baseschedule.ScheduleForEnum;
import org.secondopinion.diagnosticcenter.dto.Diagnosticcenter;
import org.secondopinion.diagnosticcenter.dto.Diagnosticcenteraddress;
import org.secondopinion.diagnosticcenter.dto.Diagnosticcenteruser;
import org.secondopinion.diagnosticcenter.dto.DiagnosticcenteruserUpdateDTO;
import org.secondopinion.diagnosticcenter.dto.Registration;
import org.secondopinion.diagnosticcenter.dto.Role;
import org.secondopinion.diagnosticcenter.dto.Schedule;
import org.secondopinion.diagnosticcenter.dto.Schedulehours;
import org.secondopinion.diagnosticcenter.dto.Userrole;
import org.secondopinion.diagnosticcenter.request.dto.AppointmentRequestDTO;
import org.secondopinion.diagnosticcenter.request.dto.PatientConfirmRequestDTO;
import org.secondopinion.diagnosticcenter.service.IDiagnosticCenterNotificationalertsService;
import org.secondopinion.diagnosticcenter.service.IDiagnosticCenterUserService;
import org.secondopinion.diagnosticcenter.service.rest.PatientRestAPIService;
import org.secondopinion.enums.AppointmentStatusEnum;
import org.secondopinion.enums.PrimaryContactEnum;
import org.secondopinion.enums.ScheduleStatusEnum;
import org.secondopinion.utils.MailProperties;
import org.secondopinion.utils.NotificationAlert;
import org.secondopinion.utils.StringUtil;
import org.secondopinion.utils.UserHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

@Service
public class DiagnosticCenterUserServiceImpl implements IDiagnosticCenterUserService {

	private static final int List = 0;

	@Autowired
	private DiagnosticcenteruserDAO diagnosticcenteruserDAO;

	@Autowired
	private RoleDAO roleDAO;
	
	@Autowired
	private DiagnosticcenterDAO diagnosticcenterDAO;

	@Autowired
	private MailProperties mailProperties;
	@Autowired
	private AppointmentDAO appointmentDAO;

	@Autowired
	private RegistrationDAO registrationDAO;
	@Autowired
	private SchedulehoursDAO schedulehoursDAO;

	@Autowired
	private ScheduleDAO scheduleDAO;

	@Autowired
	private IDiagnosticCenterNotificationalertsService notificationalertsService;

	@Autowired
	private PatientRestAPIService patientRestAPIService;

	@Value("${emailVerificationLink}")
	private String emailVerificationLink;

	@Value("${loginLinkForUI}")
	private String loginLinkForUI;
	@Autowired
	private DiagnosticcenteraddressDAO diagnosticcenteraddressDAO;

	@Autowired
	private UserroleDAO userRoleDAO;

	@Override
	@Transactional
	public Diagnosticcenteruser login(String userName, String password) {
		Diagnosticcenteruser diagnosticcenteruser = diagnosticcenteruserDAO
				.findOneByProperty(Diagnosticcenteruser.FIELD_emailId, userName);

		if (Objects.isNull(diagnosticcenteruser)) {
			throw new IllegalArgumentException(
					"Invalid UserID or Password.");
		}
		
		Diagnosticcenter diagnosticcenter=diagnosticcenterDAO.findOneByProperty(Diagnosticcenter.FIELD_primaryUser, diagnosticcenteruser.getDiagnosticCenterUserId());
		if (Objects.isNull(diagnosticcenter)) {
			throw new IllegalArgumentException(
					"You have not registered with us with the email address that you have specified.");
		}
		Registration registration = registrationDAO.findOneByProperty(Registration.FIELD_diagnosticcenterId,
				diagnosticcenter.getDiagnosticcenterId());
		if (Objects.isNull(registration) || Objects.isNull(registration.getActive()) || registration.getActive() == 'N'
				|| diagnosticcenteruser.getActive() == 'N') {
			throw new IllegalArgumentException(
					"Your Email Id Is  Unverified.");
		}

		String hashedPasswordFromDb = diagnosticcenteruser.getPassword();
		if (!UserHelper.checkpw(password, hashedPasswordFromDb)) {

			updateRetryCountIfLoginFailed(diagnosticcenteruser.getDiagnosticCenterUserId(),
					diagnosticcenteruser.getRetry());
			throw new IllegalArgumentException("Invalid UserID or Password.");
			// return null;
		}

		List<Userrole> userRoles = userRoleDAO.getByRoleIdAndUserId(null,
				diagnosticcenteruser.getDiagnosticCenterUserId());
		if (CollectionUtils.isEmpty(userRoles)) {
			throw new IllegalArgumentException("Roles are not exists");
		}
		List<Integer> roleIds = userRoles.stream().map(ur -> ur.getRoleId()).collect(Collectors.toList());
		// Fetching the roles information and set to diagnostic center user object.
		diagnosticcenteruser.setRoles(roleDAO.getByUserroles(roleIds));
		updateLastLoginTime(diagnosticcenteruser.getDiagnosticCenterUserId());
		diagnosticcenteruser.setRoleId(roleIds.get(0));
		diagnosticcenteruser.setDiagnosticcenterId(diagnosticcenter.getDiagnosticcenterId());
		return diagnosticcenteruser;
	}

	private void updateRetryCountIfLoginFailed(Long diagnosticCenterUserId, Integer retry) {
		diagnosticcenteruserDAO.updateRetryCountIfLoginFailed(diagnosticCenterUserId, retry);

	}

	private void updateLastLoginTime(Long diagnosticCenterUserId) {
		diagnosticcenteruserDAO.updateLastLoginTime(diagnosticCenterUserId);

	}

	@Override
	@Transactional
	public void createUser(Diagnosticcenteruser user, List<String> roleNames, boolean createFromDC) {
		if (!roleNames.contains(Role.RoleEnum.ADMIN.name())) {
			throw new IllegalArgumentException("You are not authorized person to create a new user.");
		}
		if (!createFromDC) {
			Diagnosticcenteraddress diagnosticcenter = diagnosticcenteraddressDAO
					.getDiagnosticCenterBYAddressId(user.getDiagnosticCenterAddressId());
			if (Objects.isNull(diagnosticcenter)) {
				throw new IllegalArgumentException("Diagnosticcenter not found.");
			}

		}
		saveDiagnosticcenteruser(user);
		saveUserrole(user);

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
		// save or updates
		diagnosticcenteruserDAO.save(diagnosticcenteruser);

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

	@Override
	@Transactional
	public void updateUser(Diagnosticcenteruser user) {
		Diagnosticcenteruser dbDiagnosticcenteruser = diagnosticcenteruserDAO
				.findDiagnosticcenterUserByUserId(user.getDiagnosticCenterUserId());
		if (Objects.isNull(dbDiagnosticcenteruser)) {
			throw new IllegalArgumentException("Diagnosticcenter user not found.");
		}
		dbDiagnosticcenteruser.setFirstName(user.getFirstName());
		dbDiagnosticcenteruser.setLastName(user.getLastName());
		dbDiagnosticcenteruser.setCellNumber(user.getCellNumber());
		dbDiagnosticcenteruser.setMiddleName(user.getMiddleName());
		diagnosticcenteruserDAO.save(dbDiagnosticcenteruser);

	}

	@Override
	@Transactional
	public void createAppointment(AppointmentRequestDTO appointmentRequest) {

		Appointment appointment = new Appointment();

		appointment.setSchedulehoursId(appointmentRequest.getScheduleHourId());
		appointment.setDescription(appointmentRequest.getReason());
		appointment.setDiagnosticCenterAddressId(appointmentRequest.getDiagnosticCenterAddressId());
		appointment.setPatientId(appointmentRequest.getPatientUserId());
		appointment.setPatientName(appointmentRequest.getPatientName());
		appointment.setReason(appointmentRequest.getReason());
		appointment.setDiagnosticCenterName(appointmentRequest.getDiagnosticCenterName());

		Schedulehours schedulehours = schedulehoursDAO
				.blockAndReturnScheduleHour(appointmentRequest.getScheduleHourId());

		//here we need to check List of schudelHours Id
	
		if (schedulehours == null) {
			throw new RuntimeException("Either schedule is invalid or unavailable");
		}

		List<Schedule> schedule = scheduleDAO.findScheduleByBasseScheduleId(appointmentRequest.getFromScheduleId());
		Schedule dbscSchedule = schedule.get(0);
		if (dbscSchedule == null) {
			throw new RuntimeException(" Schedule is invalid or unavailable");
		}

		appointment.setFromTime(schedulehours.getFromTime());
		appointment.setToTime(schedulehours.getToTime());
		appointment.setAppointmentStatus(AppointmentStatusEnum.NEW.name());
		appointment.setActive('Y');
		appointment.setReferenceEntityName(appointmentRequest.getDiagnosticCenterName());
		appointment.setAppointmentDate(dbscSchedule.getScheduleDate());
		appointmentDAO.save(appointment);

		// here we want to get the patientpayment detail from appointmentRequest and set
		// to appotment
		appointment.setPatientpaymentdetails(appointmentRequest.getPatientpaymentdetails());
		patientRestAPIService.updateAppointmentbyDiagnosticCenterUser(appointment);
		// Call API to Save Appointment at Patient side
		// Use the patient Appointment Id add to the appointment and update.
		// Send notification to the user
		// after appointment is booked

		// charge the patient we want patient payment detail

		NotificationAlert alert = new NotificationAlert(appointment.getDiagnosticCenterAddressId(),
				appointment.getAppointmentId(), "Appointment Request",
				"you have got a new appointment request from a DiagnosticCenter : " + appointment.getPatientName());

		notificationalertsService.sendNotificationalerts(alert);
	}

	@Override
	@Transactional
	public void cancelAppointment(AppointmentRequestDTO appointmentRequest) {
		Schedulehours schedulehours = schedulehoursDAO.getBySchedulehoursId(appointmentRequest.getScheduleHourId());
		if (Objects.isNull(schedulehours)) {
			throw new IllegalArgumentException("The schedule hours are not exists.");
		}
		schedulehours.setScheduleStatus(ScheduleStatusEnum.AVAILABLE.name());
		schedulehoursDAO.save(schedulehours);
		Appointment appointment = appointmentDAO.getAppointmentbypatientUserId(appointmentRequest.getPatientUserId(),
				appointmentRequest.getDiagnosticCenterAddressId());
		if (Objects.isNull(appointment)) {
			throw new IllegalArgumentException("appointment is Not exists For This User Id");
		}
		appointment.setAppointmentStatus(AppointmentStatusEnum.ENTITY_CANCELLED.name());

		patientRestAPIService.cancelAppointmentForDiagnosticCenterUser(appointment);

		appointmentDAO.save(appointment);

	}

	@Override
	@Transactional
	public void rescheduleAppointment(AppointmentRequestDTO appointmentRequest) {
		Schedulehours fromSchedulehours = schedulehoursDAO.getBySchedulehoursId(appointmentRequest.getFromScheduleId());
		if (Objects.isNull(fromSchedulehours)) {
			throw new IllegalArgumentException("The schedule hours are not exists.");
		}

		fromSchedulehours.setScheduleStatus(ScheduleStatusEnum.AVAILABLE.name());

		Schedulehours toSchedulehours = schedulehoursDAO
				.blockAndReturnScheduleHour(appointmentRequest.getToScheduleId());

		if (Objects.isNull(toSchedulehours)) {
			throw new RuntimeException("Schedule is unvailable");
		}

		schedulehoursDAO.save(fromSchedulehours);

		Appointment appointment = appointmentDAO.getAppointmentbypatientUserId(appointmentRequest.getPatientUserId(),
				appointmentRequest.getDiagnosticCenterAddressId());
		if (Objects.isNull(appointment)) {
			throw new IllegalArgumentException("appointment is Not exists For This User Id");
		}

		appointment.setDiagnosticCenterAddressId(appointmentRequest.getDiagnosticCenterAddressId());
		appointment.setAppointmentStatus(AppointmentStatusEnum.ENTITY_RESCHEDULED.name());
		appointment.setPatientId(appointmentRequest.getPatientUserId());
		appointment.setSchedulehoursId(toSchedulehours.getScheduleHoursId());
		appointment.setActive('Y');
		appointment.setFromTime(toSchedulehours.getFromTime());
		appointment.setToTime(toSchedulehours.getToTime());

		// rest api update patient side also
		patientRestAPIService.rescheduleAppointmentForDiagnosticCenterUser(appointment);

		appointmentDAO.save(appointment);

		NotificationAlert alert = new NotificationAlert(appointment.getDiagnosticCenterAddressId(),
				appointment.getAppointmentId(), "Appointment Request",
				"you have got NewrescheduleAppointment request from a DiagnosticCenter : "
						+ appointment.getPatientName());

		notificationalertsService.sendNotificationalerts(alert);
	}

	@Override
	@Transactional
	public void confirmAppointment(PatientConfirmRequestDTO appointmentRequest) {
		Appointment appointment = appointmentDAO.getAppointmentbypatientUserId(appointmentRequest.getPatientUserId(),
				appointmentRequest.getDiagnosticCenterAddressId());
		if (Objects.isNull(appointment)) {
			throw new IllegalArgumentException("appointment is Not exists For This User Id");
		}
		appointment.setAppointmentStatus(AppointmentStatusEnum.ENTITY_ACCEPTED.name());
		appointment.setDiagnosticCenterAddressId(appointmentRequest.getDiagnosticCenterAddressId());
		appointmentDAO.save(appointment);
	}

	@Override
	@Transactional
	public void updateUserRole(DiagnosticcenteruserUpdateDTO diagnosticcenteruserUpdateDTO) {
		Diagnosticcenteruser dbDiagnosticcenteruser = diagnosticcenteruserDAO
				.findDiagnosticcenterUserByUserId(diagnosticcenteruserUpdateDTO.getDiagnosticCenterUserId());
		if (Objects.isNull(dbDiagnosticcenteruser)) {
			throw new IllegalArgumentException("Diagnosticcenter user not found.");
		}
		Userrole userrole = userRoleDAO.findOneByProperty(Userrole.FIELD_diagnosticcenterUserId,
				dbDiagnosticcenteruser.getDiagnosticCenterUserId());

		if (Objects.isNull(userrole)) {
			throw new IllegalArgumentException("Userrole  not found.");
		}

		userrole.setRoleId(diagnosticcenteruserUpdateDTO.getRoleId());
		userRoleDAO.save(userrole);
	}

}
