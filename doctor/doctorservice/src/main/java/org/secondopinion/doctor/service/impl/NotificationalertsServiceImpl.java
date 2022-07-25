package org.secondopinion.doctor.service.impl;

import java.util.Date;

import java.util.HashMap;

import java.util.List;
import java.util.Map;
import java.util.Objects;

import org.apache.commons.lang3.StringUtils;
import org.secondopinion.configurations.UtilComponent;
import org.secondopinion.configurations.firebase.PushNotificationRequest;
import org.secondopinion.configurations.firebase.PushNotificationService;
import org.secondopinion.doctor.dao.AppointmentDAO;
import org.secondopinion.doctor.dao.DoctorDAO;
import org.secondopinion.doctor.dao.DoctorfcmtokenDAO;
import org.secondopinion.doctor.dao.NotificationalertsDAO;
import org.secondopinion.doctor.dto.Appointment;
import org.secondopinion.doctor.dto.Doctor;
import org.secondopinion.doctor.dto.Doctorfcmtoken;
import org.secondopinion.doctor.dto.Notificationalerts;
import org.secondopinion.doctor.dto.Schedulehours;
import org.secondopinion.doctor.service.INotificationalertsService;
import org.secondopinion.enums.AppointmentStatusEnum;
import org.secondopinion.utils.NotificationAlert;
import org.secondopinion.utils.EmailUtil.MailContentEnum;
import org.secondopinion.utils.DateUtil;
import org.secondopinion.utils.EmailUtil;
import org.secondopinion.utils.MailProperties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class NotificationalertsServiceImpl implements INotificationalertsService {

	private static final Logger logger = LoggerFactory.getLogger(NotificationalertsServiceImpl.class);

	@Autowired
	private NotificationalertsDAO notificationalertsDAO;

	@Autowired
	private AppointmentDAO appointmentDAO;

	@Autowired
	private PushNotificationService puchNotificationService;

	@Autowired
	private DoctorfcmtokenDAO doctorfcmtokenDAO;

	@Autowired
	private MailProperties mailProperties;

	@Autowired
	private DoctorDAO doctorDAO;

	@Autowired
	private UtilComponent utilComponent;

	@Override
	@Transactional
	public List<Notificationalerts> getAllNotificationalerts(Long doctorId) {
		return notificationalertsDAO.findNotificationalertsByDoctorId(doctorId);
	}

	@Override
	@Transactional(readOnly = true)
	public void saveNotificationalerts(Notificationalerts notificationalerts) {

		notificationalerts.setExpirydate(DateUtil.addAndGetDate(utilComponent.getTimeZone(), 7));
		notificationalerts.setActive('Y');
		notificationalertsDAO.save(notificationalerts);
	}

	@Override
	@Transactional(readOnly = true)
	public List<Notificationalerts> getAllNotificationalertsByNextWeek(Date dateTo, Long doctorId) {
		return notificationalertsDAO.getNotificationalerts(dateTo, doctorId);
	}

	@Override
	@Transactional
	public void updateNotificationalerts(Long notificationId, Notificationalerts notificationalerts) {
		Notificationalerts dbnotificationalerts = notificationalertsDAO.findnotificationById(notificationId);
		if (dbnotificationalerts == null) {
			throw new IllegalArgumentException("Notificationalerts is not found for given Id");
		}
		notificationalerts.setNotificationalertsId(notificationId);

		notificationalerts.setExpirydate(DateUtil.addAndGetDate(utilComponent.getTimeZone(), 7));
		dbnotificationalerts.setNotificationalertsId(notificationId);
		dbnotificationalerts.setReason(notificationalerts.getReason());
		dbnotificationalerts.setDoctorId(notificationalerts.getDoctorId());
		dbnotificationalerts.setObjecttype(notificationalerts.getObjecttype());
		dbnotificationalerts.setViewed(notificationalerts.getViewed());
		notificationalertsDAO.save(dbnotificationalerts);
	}

	@Override
	@Transactional(readOnly = true)
	public void deleteNotificationalerts(Long notificationId) {

		Notificationalerts dbnotificationalerts = notificationalertsDAO.findnotificationById(notificationId);
		if (dbnotificationalerts == null) {
			throw new IllegalArgumentException("Notificationalerts is not found for given Id");
		}
		dbnotificationalerts.setActive('N');
		notificationalertsDAO.save(dbnotificationalerts);
	}

	@Override
	public void sendNotificationalertstofierbase(PushNotificationRequest request) {
		try {
			puchNotificationService.sendPushNotificationToToken(request);
		} catch (Exception e) {
			logger.debug("Error in sending Message");
		}
	}

	@Override
	public void sendNotification(NotificationAlert alert) {
		// here we are getting the fcm tokent for the apprapate user
		Doctorfcmtoken patientfcmtoken = doctorfcmtokenDAO.findOneByProperty(Doctorfcmtoken.FIELD_doctorId,
				alert.getUserPrimaryKey());

		if (Objects.nonNull(patientfcmtoken)) {
			PushNotificationRequest request = new PushNotificationRequest(alert);

			// new we are sending the token to fcmserivice to notifactionalert

			if (!StringUtils.isEmpty(patientfcmtoken.getBrowsertoken())) {
				request.setToken(patientfcmtoken.getBrowsertoken());
				logger.info(patientfcmtoken.getAndroidtoken());
				puchNotificationService.sendPushNotificationToToken(request);
			}

			if (!StringUtils.isEmpty(patientfcmtoken.getAndroidtoken())) {
				request.setToken(patientfcmtoken.getAndroidtoken());
				logger.info(patientfcmtoken.getAndroidtoken());

				puchNotificationService.sendPushNotificationToToken(request);
			}

			if (!StringUtils.isEmpty(patientfcmtoken.getIphonetoken())) {
				request.setToken(patientfcmtoken.getIphonetoken());
				logger.info(patientfcmtoken.getAndroidtoken());
				puchNotificationService.sendPushNotificationToToken(request);
			}

		}
		String doctorEmailId = doctorDAO.getDoctorsEmailId(alert.getUserPrimaryKey());
		sendEmail(doctorEmailId, alert.getObjectName(), alert.getMessage());
		// message to phone
		// twilio

		// afetr that we want save the data into notifaction table
		Notificationalerts notificationalerts = Notificationalerts.buildNotificationalerts(alert);
		notificationalerts.setActive('Y');
		notificationalertsDAO.save(notificationalerts);
	}

	private MailProperties getMailProperties(Doctor doctor) {
		MailProperties properties = mailProperties.clone();
		properties.addToRecipient(doctor.getEmailId());
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

	public void sendEmail(String emailId, String subject, String body) {
		MailProperties properties = mailProperties.clone();
		properties.addToRecipient(emailId);
		EmailUtil.sendMail(properties, body, subject);
	}

	@Override
	public void sendNotificationToTheDoctorByAppointment(NotificationAlert alert) {
		Appointment appointment = appointmentDAO.findById(alert.getObjectKey());

		if (Objects.nonNull(appointment)) {
			alert.setUserPrimaryKey(appointment.getDoctorId());
			sendNotification(alert);
		}
	}

	@Override
	public void sendNotificationForAppointment(NotificationAlert alert) {
		/// here we are getting the fcm tokent for the apprapate user

	
		Doctorfcmtoken patientfcmtoken = doctorfcmtokenDAO.findOneByProperty(Doctorfcmtoken.FIELD_doctorId,
				alert.getUserPrimaryKey());

		if (Objects.nonNull(patientfcmtoken)) {
			PushNotificationRequest request = new PushNotificationRequest(alert);

			// new we are sending the token to fcmserivice to notifactionalert

			if (!StringUtils.isEmpty(patientfcmtoken.getBrowsertoken())) {
				request.setToken(patientfcmtoken.getBrowsertoken());
				logger.info(patientfcmtoken.getAndroidtoken());
				puchNotificationService.sendPushNotificationToToken(request);
			}

			if (!StringUtils.isEmpty(patientfcmtoken.getAndroidtoken())) {
				request.setToken(patientfcmtoken.getAndroidtoken());
				logger.info(patientfcmtoken.getAndroidtoken());
				puchNotificationService.sendPushNotificationToToken(request);
			}

			if (!StringUtils.isEmpty(patientfcmtoken.getIphonetoken())) {
				request.setToken(patientfcmtoken.getIphonetoken());
				puchNotificationService.sendPushNotificationToToken(request);
			}

		}
		String doctorEmailId = doctorDAO.getDoctorsEmailId(alert.getUserPrimaryKey());
		Doctor doctor = doctorDAO.findOneByProperty(Doctor.FIELD_emailId, doctorEmailId);
		Appointment appointment = appointmentDAO.findOneByProperty(Appointment.FIELD_appointmentId,
				alert.getObjectKey());
		// send the mail
		
		String appointmentDate = DateUtil.convertDateFormat(appointment.getAppointmentDate(), Appointment.DATE_FORMAT);
		String schudletime = DateUtil.convertDateFormat(appointment.getFromTime(), Appointment.TIME_FORMAT);
		String schudleDate = " on " + appointmentDate + " and " + schudletime;
		String name = appointment.getPatientName();
		String amount = String.valueOf(appointment.getAmountPaid());

		Map<String, String> model = new HashMap<>();
		model.put(MailContentEnum.NAME.name(), name);
		model.put(MailContentEnum.SCHUDLEDATE.name(), schudleDate);
		model.put(MailContentEnum.AMOUNT.name(), amount);

		String classpathEmailTemplate = "classpath:doctor-appointment.html";

		try {
			EmailUtil.sendEmailWithHtmlContentFroAppointment(getMailProperties(doctor), "curemetric Appointment  info",
					classpathEmailTemplate, model);
			logger.info("Email send successFully..");
			Notificationalerts notificationalerts = Notificationalerts.buildNotificationalerts(alert);
		
			notificationalertsDAO.save(notificationalerts);
		}
		catch (Exception e) {
			logger.info(e.getMessage(),e);
		}
	
	}

	@Override
	public void sendNotificationForCancleAppointment(NotificationAlert alert) {
		/// here we are getting the fcm tokent for the apprapate user

		Doctorfcmtoken patientfcmtoken = doctorfcmtokenDAO.findOneByProperty(Doctorfcmtoken.FIELD_doctorId,
				alert.getUserPrimaryKey());

		if (Objects.nonNull(patientfcmtoken)) {
			PushNotificationRequest request = new PushNotificationRequest(alert);

			// new we are sending the token to fcmserivice to notifactionalert

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
		String doctorEmailId = doctorDAO.getDoctorsEmailId(alert.getUserPrimaryKey());
		Doctor doctor = doctorDAO.findOneByProperty(Doctor.FIELD_emailId, doctorEmailId);
		Appointment appointment = appointmentDAO.findOneByProperty(Appointment.FIELD_appointmentId,
				alert.getObjectKey());
		// send the mail
		String appointmentDate = DateUtil.convertDateFormat(appointment.getAppointmentDate(), Appointment.DATE_FORMAT);
		String schudletime = DateUtil.convertDateFormat(appointment.getFromTime(), Schedulehours.TIME_FORMAT);
		String schudleDate = " on " + appointmentDate + " and " + schudletime;
		String name = appointment.getPatientName();
		String amount = String.valueOf(appointment.getAmountPaid());
		String username = appointment.getPatientName();
		Map<String, String> model = new HashMap<>();
		model.put(MailContentEnum.NAME.name(), name);
		model.put(MailContentEnum.USERNAME.name(), username);
		model.put(MailContentEnum.SCHUDLEDATE.name(), schudleDate);
		model.put(MailContentEnum.AMOUNT.name(), amount);

		String classpathEmailTemplate = "classpath:doctor-cancle.html";

		EmailUtil.sendEmailWithHtmlContentForCancleAppointment(getMailProperties(doctor),
				"curemetric Appointment  info", classpathEmailTemplate, model);
		Notificationalerts notificationalerts = Notificationalerts.buildNotificationalerts(alert);

		notificationalertsDAO.save(notificationalerts);

	}
}