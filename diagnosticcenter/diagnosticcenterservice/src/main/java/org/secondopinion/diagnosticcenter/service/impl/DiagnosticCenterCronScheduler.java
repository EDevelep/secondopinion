package org.secondopinion.diagnosticcenter.service.impl;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;
import java.util.Timer;
import java.util.TimerTask;

import org.secondopinion.diagnosticcenter.dao.AppointmentDAO;
import org.secondopinion.diagnosticcenter.dto.Appointment;
import org.secondopinion.diagnosticcenter.service.IDiagnosticCenterNotificationalertsService;
import org.secondopinion.utils.DateUtil;
import org.secondopinion.utils.NotificationAlert;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

@Component
public class DiagnosticCenterCronScheduler {

	
	private static final Logger LOGGER = LoggerFactory.getLogger(DiagnosticCenterCronScheduler.class);

	@Autowired
	private AppointmentDAO appointmentDAO;

	@Autowired
	private IDiagnosticCenterNotificationalertsService notificationalertsService;

	@Scheduled(cron = "40 0 * * * *")
	public void sendNotificationAlertAtTheTimeOfAppointment() {

		List<Appointment> appointments = appointmentDAO.getByAppointmentDateAndFromTime(
				DateUtil.getCurrentDateOnly(TimeZone.getDefault()),  DateUtil.getCurrentLocalTime(TimeZone.getDefault()));

		if (!CollectionUtils.isEmpty(appointments)) {
			String currentDate = DateUtil.getCurrentDateString( TimeZone.getTimeZone("UTC"));

			appointments.stream().forEach(ap -> {
				String dateTimeValue = currentDate + " " + ap.getFromTime().toString();

				currentDayAlerts(ap.getDiagnosticCenterAddressId(),
						"Appointment Consultation",
						ap.getAppointmentId(), " A user Mr(s). " + ap.getPatientName() + " is having an appointment.", dateTimeValue);
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
							ap.getDiagnosticCenterAddressId(), ap.getAppointmentId(),"UpComing Appointment",
							 "You have an upcoming appointment in today at " + ap.getFromTime());
							
					notificationalertsService.sendNotificationalerts(alert);
				});
			}
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
		}

	}

	// The task which you want to execute
	private class DiagnosticCenterTimerTask extends TimerTask {
		private NotificationAlert alert;

		public DiagnosticCenterTimerTask(Long dcAddressId, String message, Long objectKey, String objectName) {
			alert = new NotificationAlert(dcAddressId, objectKey, objectName, message);
		}

		public void run() {
			notificationalertsService.sendNotificationalerts(alert);
		}
	}

	public void currentDayAlerts(Long dcAddressId, String objectName, Long objectKey, String message, String dateTimeValue) {

		DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

		try {
			Date date = dateFormatter.parse(dateTimeValue);

			// Now create the LocalTime and schedule it
			Timer timer = new Timer();
			DiagnosticCenterTimerTask ptt = new DiagnosticCenterTimerTask(dcAddressId, message, objectKey, objectName);
			// Use this if you want to execute it once
			timer.schedule(ptt, date);
		} catch (ParseException e) {
			LOGGER.error(e.getMessage(), e);
		}

		// Use this if you want to execute it repeatedly
		// int period = 10000;//10secs
		// LocalTimer.schedule(new MyTimeTask(), date, period );
	}

}
