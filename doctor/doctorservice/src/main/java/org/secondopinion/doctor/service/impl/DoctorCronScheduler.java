package org.secondopinion.doctor.service.impl;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;
import java.util.Timer;
import java.util.TimerTask;

import org.secondopinion.doctor.dao.AppointmentDAO;
import org.secondopinion.doctor.dto.Appointment;
import org.secondopinion.doctor.service.INotificationalertsService;
import org.secondopinion.utils.DateUtil;
import org.secondopinion.utils.NotificationAlert;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

@Component
public class DoctorCronScheduler {

	private static final Logger LOGGER = LoggerFactory.getLogger(DoctorCronScheduler.class);

	@Autowired
	private AppointmentDAO appointmentDAO;

	@Autowired
	private INotificationalertsService notificationalertsService;

	@Scheduled(cron = "40 0 * * * *")
	public void sendNotificationAlertAtTheTimeOfAppointment() {

		List<Appointment> appointments = appointmentDAO.getByAppointmentDateAndFromTime(
				DateUtil.getCurrentDateOnly(TimeZone.getDefault()),  DateUtil.getCurrentLocalTime(TimeZone.getDefault()));

		if (!CollectionUtils.isEmpty(appointments)) {
			String currentDate = DateUtil.getCurrentDateString( TimeZone.getTimeZone("UTC"));

			appointments.stream().forEach(ap -> {
				String dateTimeValue = currentDate + " " + ap.getFromTime().toString();

				currentDayAlerts(ap.getDoctorId(),
						"Appointment Consultation",
						ap.getAppointmentId(), "Please login to the curemetric to " + ap.getChatType() + " with Mr(s). " + ap.getPatientName(), dateTimeValue);
			});

		}
	}

	@Scheduled(fixedRate = 60 * 60 * 1000)
	public void currentappointmentalerts() {
		try {
			List<Appointment> appointments = appointmentDAO.getByAppointmentDateAndFromTime(
					DateUtil.getCurrentDateOnly(TimeZone.getDefault()),  DateUtil.getCurrentLocalTime(TimeZone.getDefault()));
			if (!CollectionUtils.isEmpty(appointments)) {
				appointments.stream().forEach(ap -> {
					NotificationAlert alert = new NotificationAlert(
							ap.getDoctorId(), ap.getAppointmentId(),"UpComing Appointment",
							 "You have an upcoming appointment in today at " + ap.getFromTime());
						//need to send the template upcomeing template 	
					notificationalertsService.sendNotification(alert);
				});
			}
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
		}

	}

	// The task which you want to execute
	private class DoctorTimerTask extends TimerTask {
		private NotificationAlert alert;

		public DoctorTimerTask(Long userId, String message, Long objectKey, String objectName) {
			alert = new NotificationAlert(userId, objectKey, objectName, message);
		}

		public void run() {
			notificationalertsService.sendNotification(alert);
		}
	}

	public void currentDayAlerts(Long userId, String objectName, Long objectKey, String message, String dateTimeValue) {

		DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

		try {
			Date date = dateFormatter.parse(dateTimeValue);

			// Now create the LocalTime and schedule it
			Timer timer = new Timer();
			DoctorTimerTask ptt = new DoctorTimerTask(userId, message, objectKey, objectName);
			// Use this if you want to execute it once
			timer.schedule(ptt, date);
		} catch (ParseException e) {
			LOGGER.error(e.getMessage(), e);
		}

	}

}
