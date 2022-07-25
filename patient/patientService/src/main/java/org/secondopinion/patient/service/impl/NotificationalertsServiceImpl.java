package org.secondopinion.patient.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import org.apache.commons.lang3.StringUtils;
import org.secondopinion.configurations.UtilComponent;
import org.secondopinion.configurations.firebase.PushNotificationRequest;
import org.secondopinion.configurations.firebase.PushNotificationService;
import org.secondopinion.filter.interceptors.LoggingServiceImpl;
import org.secondopinion.patient.dao.AppointmentDAO;
import org.secondopinion.patient.dao.NotificationalertsDAO;
import org.secondopinion.patient.dao.PatientfcmtokenDAO;
import org.secondopinion.patient.dao.UserDAO;
import org.secondopinion.patient.dto.Appointment;
import org.secondopinion.patient.dto.Notificationalerts;
import org.secondopinion.patient.dto.PatientShippingUpdateDTO;
import org.secondopinion.patient.dto.Patientfcmtoken;
import org.secondopinion.patient.dto.User;
import org.secondopinion.patient.service.INotificationalertsService;
import org.secondopinion.utils.DateUtil;
import org.secondopinion.utils.EmailUtil;
import org.secondopinion.utils.EmailUtil.MailContentEnum;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.secondopinion.utils.MailProperties;
import org.secondopinion.utils.NotificationAlert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class NotificationalertsServiceImpl implements INotificationalertsService {
	private static final Logger LOGGER = LoggerFactory.getLogger(NotificationalertsServiceImpl.class);
	@Autowired
	private NotificationalertsDAO notificationalertsDAO;

	@Autowired
	private PushNotificationService puchNotificationService;

	@Autowired
	private MailProperties mailProperties;

	@Autowired
	private PatientfcmtokenDAO patientfcmtokenDAO;

	@Autowired
	private AppointmentDAO appointmentDAO;

	@Autowired
	private UserDAO userDAO;

	@Autowired
	private UtilComponent utilComponent;

	@Override
	@Transactional
	public List<Notificationalerts> getAllNotificationalerts(Long patientId) {

		return notificationalertsDAO.findnotificationalertsByPatientId(patientId);

	}

	@Override
	@Transactional(readOnly = true)
	public void saveNotificationalerts(Notificationalerts notificationalerts) {
		notificationalerts.setActive('Y');
		notificationalertsDAO.save(notificationalerts);
	}

	@Override
	@Transactional
	public void updateNotificationalerts(Long notificationId, Notificationalerts notificationalerts) {

		Notificationalerts dbnotificationalerts = notificationalertsDAO
				.findNotificationalertsBynotificationalertsId(notificationId);
		if (dbnotificationalerts == null) {
			throw new IllegalArgumentException("Notificationalerts is not found for given Id");
		}
		dbnotificationalerts.setNotificationalertsId(notificationId);
		dbnotificationalerts.setReason(notificationalerts.getReason());
		dbnotificationalerts.setPatientId(notificationalerts.getPatientId());
		dbnotificationalerts.setObjecttype(notificationalerts.getObjecttype());
		dbnotificationalerts.setViewed(notificationalerts.getViewed());
		notificationalertsDAO.save(dbnotificationalerts);
	}

	@Override
	@Transactional(readOnly = true)
	public List<Notificationalerts> getNotificationsToUserForDate(Long patientId, Date date) {
		return notificationalertsDAO.getNotificationsToUserForDate(patientId, date);
	}

	@Override
	@Transactional
	public void deleteNotificationalerts(Long notificationId) {

		Notificationalerts dbnotificationalerts = notificationalertsDAO
				.findNotificationalertsBynotificationalertsId(notificationId);
		if (dbnotificationalerts == null) {
			throw new IllegalArgumentException("No Notification found for the given Id");
		}
		dbnotificationalerts.setActive('N');
		notificationalertsDAO.save(dbnotificationalerts);
	}

	@Override
	public void sendNotificationalertsForFierbase(PushNotificationRequest pucNotificationRequest) {
		puchNotificationService.sendPushNotificationToToken(pucNotificationRequest);

	}

	@Override
	public void sendNotification(NotificationAlert alert) {

		Patientfcmtoken patientfcmtoken = patientfcmtokenDAO.findOneByProperty(Patientfcmtoken.FIELD_patientid,
				alert.getUserPrimaryKey());

		if (Objects.nonNull(patientfcmtoken)) {
			PushNotificationRequest request = new PushNotificationRequest(alert);
			request.setMessage("You got a " + alert.getObjectName() + " request.");
			if (!StringUtils.isEmpty(patientfcmtoken.getBrowsertoken())) {
				request.setToken(patientfcmtoken.getBrowsertoken());
				puchNotificationService.sendPushNotificationToToken(request);
			}
			if (!StringUtils.isEmpty(patientfcmtoken.getAndroidtoken())) {
				request.setToken(patientfcmtoken.getAndroidtoken());

				puchNotificationService.sendPushNotificationToToken(request);
			}
			if (!StringUtils.isEmpty(patientfcmtoken.getIphonetoken())) {
				request.setToken(patientfcmtoken.getIphonetoken());
				puchNotificationService.sendPushNotificationToToken(request);
			}

		}

		// User user = userDAO.findById((alert.getUserPrimaryKey()));

		// now we will get the address for the user and
		// sendEmail(user.getEmailId(), alert.getObjectName(), alert.getMessage());
		Notificationalerts notificationalerts = Notificationalerts.buildNotificationalerts(alert);
		notificationalertsDAO.save(notificationalerts);

	}

	@Override
	public void sendNotificationForInvoice(NotificationAlert alert) {

		// here we are getting the fcm tokent for the apprapate user
		Patientfcmtoken patientfcmtoken = patientfcmtokenDAO.findOneByProperty(Patientfcmtoken.FIELD_patientid,
				alert.getUserPrimaryKey());
		if (Objects.nonNull(patientfcmtoken)) {
			PushNotificationRequest request = new PushNotificationRequest(alert);

			request.setMessage("You got a " + alert.getObjectName() + " request.");

			// new we are sending the token to fcmserivice to notifactionalert

			// enum put switch case
			if (!StringUtils.isEmpty(patientfcmtoken.getBrowsertoken())) {
				request.setToken(patientfcmtoken.getBrowsertoken());
				puchNotificationService.sendPushNotificationToToken(request);
			}
			if (!StringUtils.isEmpty(patientfcmtoken.getAndroidtoken())) {
				request.setToken(patientfcmtoken.getAndroidtoken());
				puchNotificationService.sendPushNotificationToToken(request);
			}
			if (!StringUtils.isEmpty(patientfcmtoken.getIphonetoken())) {
				request.setToken(patientfcmtoken.getIphonetoken());
				puchNotificationService.sendPushNotificationToToken(request);
			}

		}

		String usersEmailI = userDAO.geUsersEmailId(alert.getUserPrimaryKey());

		sendEmail(usersEmailI, alert.getObjectName(), alert.getMessage());
		Notificationalerts notificationalerts = Notificationalerts.buildNotificationalerts(alert);
		notificationalertsDAO.save(notificationalerts);

	}

	@Override
	public void sendNotificationForAppointment(NotificationAlert alert) {

		// here we are getting the fcm tokent for the apprapate user
		Patientfcmtoken patientfcmtoken = patientfcmtokenDAO.findOneByProperty(Patientfcmtoken.FIELD_patientid,
				alert.getUserPrimaryKey());
		if (Objects.nonNull(patientfcmtoken)) {
			PushNotificationRequest request = new PushNotificationRequest(alert);

			request.setMessage("You got a " + alert.getObjectName() + " request.");
			// new we are sending the token to fcmserivice to notifactionalert
			// enum put switch case
			if (!StringUtils.isEmpty(patientfcmtoken.getBrowsertoken())) {
				request.setToken(patientfcmtoken.getBrowsertoken());
				puchNotificationService.sendPushNotificationToToken(request);
			}
			if (!StringUtils.isEmpty(patientfcmtoken.getAndroidtoken())) {
				request.setToken(patientfcmtoken.getAndroidtoken());

				LOGGER.info(patientfcmtoken.getAndroidtoken());
				puchNotificationService.sendPushNotificationToToken(request);
			}
			if (!StringUtils.isEmpty(patientfcmtoken.getIphonetoken())) {
				request.setToken(patientfcmtoken.getIphonetoken());
				puchNotificationService.sendPushNotificationToToken(request);
			}

		}

		String usersEmailId = userDAO.geUsersEmailId(alert.getUserPrimaryKey());
		User user = userDAO.findOneByProperty(User.FIELD_emailId, usersEmailId);
		Appointment appointment = appointmentDAO.findOneByProperty(Appointment.FIELD_appointmentId,
				alert.getObjectKey());
		// send the mail
		String appointmentDate = DateUtil.convertDateFormat(appointment.getAppointmentDate(), Appointment.DATE_FORMAT);
		String schudletime = DateUtil.convertDateFormat(appointment.getFromTime(), Appointment.TIME_FORMAT);
		String schudleDate = " on " + appointmentDate + " and time is " + schudletime;
		String name = appointment.getReferenceEntityName();
		String amount = String.valueOf(appointment.getAmountPaid());

		Map<String, String> model = new HashMap<>();
		model.put(MailContentEnum.NAME.name(), name);
		model.put(MailContentEnum.SCHUDLEDATE.name(), schudleDate);
		model.put(MailContentEnum.AMOUNT.name(), amount);
		String classpathEmailTemplate = "classpath:patient-appointment.html";
		EmailUtil.sendEmailWithHtmlContentFroAppointment(getMailProperties(user), "curemetric Appointment  info",
				classpathEmailTemplate, model);
		// message to phone
		// twilio
		// afetr that we want save the data into notifaction table

		Notificationalerts notificationalerts = Notificationalerts.buildNotificationalerts(alert);

		notificationalertsDAO.save(notificationalerts);

	}

	@Override
	public void sendNotificationForCancleAppointment(NotificationAlert alert) {

		// here we are getting the fcm tokent for the apprapate user
		Patientfcmtoken patientfcmtoken = patientfcmtokenDAO.findOneByProperty(Patientfcmtoken.FIELD_patientid,
				alert.getUserPrimaryKey());
		if (Objects.nonNull(patientfcmtoken)) {
			PushNotificationRequest request = new PushNotificationRequest(alert);

			request.setMessage("You got a " + alert.getObjectName() + " request.");

			// new we are sending the token to fcmserivice to notifactionalert

			// enum put switch case
			if (!StringUtils.isEmpty(patientfcmtoken.getBrowsertoken())) {
				request.setToken(patientfcmtoken.getBrowsertoken());
				puchNotificationService.sendPushNotificationToToken(request);
			}
			if (!StringUtils.isEmpty(patientfcmtoken.getAndroidtoken())) {
				request.setToken(patientfcmtoken.getAndroidtoken());
				puchNotificationService.sendPushNotificationToToken(request);
			}
			if (!StringUtils.isEmpty(patientfcmtoken.getIphonetoken())) {
				request.setToken(patientfcmtoken.getIphonetoken());
				puchNotificationService.sendPushNotificationToToken(request);
			}

		}

		String usersEmailId = userDAO.geUsersEmailId(alert.getUserPrimaryKey());
		User user = userDAO.findOneByProperty(User.FIELD_emailId, usersEmailId);
		Appointment appointment = appointmentDAO.findOneByProperty(Appointment.FIELD_userId,
				alert.getUserPrimaryKey());
		String appointmentDate = DateUtil.convertDateFormat(appointment.getAppointmentDate(), Appointment.DATE_FORMAT);
		String schudletime = DateUtil.convertDateFormat(appointment.getFromTime(), Appointment.TIME_FORMAT);
		String schudleDate = " on " + appointmentDate + " and " + schudletime;
		String name = appointment.getReferenceEntityName();
		String amount = String.valueOf(appointment.getAmountPaid());
		Map<String, String> model = new HashMap<>();
		model.put(MailContentEnum.NAME.name(), name);
		model.put(MailContentEnum.SCHUDLEDATE.name(), schudleDate);
		model.put(MailContentEnum.AMOUNT.name(), amount);

		String classpathEmailTemplate = "classpath:patient-cancle.html";

		EmailUtil.sendEmailWithHtmlContentFroAppointment(getMailProperties(user), "[curemetric] Appointment  info",
				classpathEmailTemplate, model);

		Notificationalerts notificationalerts = Notificationalerts.buildNotificationalerts(alert);

		notificationalertsDAO.save(notificationalerts);

	}

	@Override
	public void sendNotificationForReschudleAppointment(NotificationAlert alert) {

		// here we are getting the fcm tokent for the apprapate user
		Patientfcmtoken patientfcmtoken = patientfcmtokenDAO.findOneByProperty(Patientfcmtoken.FIELD_patientid,
				alert.getUserPrimaryKey());
		if (Objects.nonNull(patientfcmtoken)) {
			PushNotificationRequest request = new PushNotificationRequest(alert);

			request.setMessage("You got a " + alert.getObjectName() + " request.");

			// new we are sending the token to fcmserivice to notifactionalert

			// enum put switch case
			if (!StringUtils.isEmpty(patientfcmtoken.getBrowsertoken())) {
				request.setToken(patientfcmtoken.getBrowsertoken());
				puchNotificationService.sendPushNotificationToToken(request);
			}
			if (!StringUtils.isEmpty(patientfcmtoken.getAndroidtoken())) {
				request.setToken(patientfcmtoken.getAndroidtoken());
				puchNotificationService.sendPushNotificationToToken(request);
			}
			if (!StringUtils.isEmpty(patientfcmtoken.getIphonetoken())) {
				request.setToken(patientfcmtoken.getIphonetoken());
				puchNotificationService.sendPushNotificationToToken(request);
			}

		}
		String usersEmailId = userDAO.geUsersEmailId(alert.getUserPrimaryKey());
		User user = userDAO.findOneByProperty(User.FIELD_emailId, usersEmailId);
		Appointment appointment = appointmentDAO.findOneByProperty(Appointment.FIELD_userId, alert.getUserPrimaryKey());
		String appointmentDate = DateUtil.convertDateFormat(appointment.getAppointmentDate(), Appointment.DATE_FORMAT);
		String username = appointment.getPatientName();
		String schudletime = DateUtil.convertDateFormat(appointment.getFromTime(), Appointment.TIME_FORMAT);
		String schudleDate = " on " + appointmentDate + " and " + schudletime;
		String name = appointment.getReferenceEntityName();
		Map<String, String> model = new HashMap<>();
		model.put(MailContentEnum.NAME.name(), name);
		model.put(MailContentEnum.SCHUDLEDATE.name(), schudleDate);
		model.put(MailContentEnum.USERNAME.name(), username);

		String classpathEmailTemplate = "classpath:patient-reschudle.html";

		EmailUtil.sendEmailWithHtmlContentForReschudleAppointment(getMailProperties(user),
				"[curemetric] Appointment  info", classpathEmailTemplate, model);
		Notificationalerts notificationalerts = Notificationalerts.buildNotificationalerts(alert);
		notificationalertsDAO.save(notificationalerts);

	}

	private MailProperties getMailProperties(User user) {
		MailProperties properties = mailProperties.clone();
		properties.addToRecipient(user.getEmailId());
		return properties;
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

	public void sendEmail(String usersEmailId, String subject, String body) {
		MailProperties properties = mailProperties.clone();
		properties.addToRecipient(usersEmailId);
		EmailUtil.sendMail(properties, body, subject);
	}

	@Override
	@Transactional
	public List<Notificationalerts> getNotificationsNotificationsStatusIsview(Long patientId) {
		return notificationalertsDAO.getNotificationsNotificationsStatusIsview(patientId);
	}

	@Override
	@Transactional
	public void saveNotification(PatientShippingUpdateDTO notificationalerts) {

		Notificationalerts notificationalert = Notificationalerts
				.buildNotificationalertsForShipping(notificationalerts);
		notificationalertsDAO.save(notificationalert);

	}

}
