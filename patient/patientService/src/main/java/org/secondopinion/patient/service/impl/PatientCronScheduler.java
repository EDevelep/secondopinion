package org.secondopinion.patient.service.impl;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;
import java.util.Timer;
import java.util.TimerTask;

import org.secondopinion.patient.dao.AppointmentDAO;
import org.secondopinion.patient.dto.Appointment;
import org.secondopinion.patient.service.INotificationalertsService;
import org.secondopinion.utils.DateUtil;
import org.secondopinion.utils.NotificationAlert;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

@Component
public class PatientCronScheduler {

	private static final Logger LOGGER = LoggerFactory.getLogger(PatientCronScheduler.class);

	@Autowired
	private AppointmentDAO appointmentDAO;

	@Autowired
	private INotificationalertsService notificationalertsService;

	@Scheduled(cron = "40 0 * * * *")
	public void sendNotificationAlertAtTheTimeOfAppointment() {

		List<Appointment> appointments = appointmentDAO.getByAppointmentDateAndFromTime(
				DateUtil.getCurrentDateOnly(TimeZone.getDefault()),  DateUtil.getCurrentLocalTime(TimeZone.getDefault()));
		if(!CollectionUtils.isEmpty(appointments)) {
			String currentDate = DateUtil.getCurrentDateString( TimeZone.getTimeZone("UTC"));
			appointments.stream().forEach(ap -> {
				String dateTimeValue = currentDate + " " + ap.getFromTime().toString();
				currentDayAlerts(ap.getUserId(), "Appointment Consultation",
						ap.getAppointmentId(), "Please login to the curemetric to consult the Dr. " + ap.getReferenceEntityName() + "through " + ap.getChatType() , dateTimeValue);
			});
			}
	}

	@Scheduled(fixedRate=60*60*1000)
	public void currentappointmentalerts() {
		try {
			List<Appointment> appointments = appointmentDAO.getByAppointmentDateAndFromTime(
					DateUtil.getCurrentDateOnly(TimeZone.getDefault()),  DateUtil.getCurrentLocalTime(TimeZone.getDefault()));
			if(!CollectionUtils.isEmpty(appointments)) {
				appointments.stream().forEach(ap -> {
					NotificationAlert alert = new NotificationAlert(ap.getUserId(), ap.getAppointmentId(), "Upcoming Appointment", 
							 "You have an upcoming appointment today at " + ap.getFromTime());
					notificationalertsService.sendNotification(alert);
				});

			}
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
		}
	}
	

	@Scheduled(fixedRate=60*60*1000)
	public void sendFollowupupdate() {
		try {
			List<Appointment> appointments = appointmentDAO.getFollowupupdate(
					DateUtil.getCurrentDateOnly(TimeZone.getDefault()),  DateUtil.getCurrentLocalTime(TimeZone.getDefault()));
			if(!CollectionUtils.isEmpty(appointments)) {
				appointments.stream().forEach(ap -> {
					NotificationAlert alert = new NotificationAlert(ap.getUserId(), ap.getAppointmentId(), "Upcoming Followupup", 
							 "You have an upcoming Followupup  at " + ap.getFromTime());
					notificationalertsService.sendNotification(alert);
				});

			}
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
		}
	}

	//The task which you want to execute
	private class PatientTimerTask extends TimerTask
	{
		private NotificationAlert alert;

		public PatientTimerTask(Long userId, String message, Long objectKey, String objectName) {
			alert = new NotificationAlert(userId, objectKey, objectName, message);
		}


		public void run()
		{
			notificationalertsService.sendNotification(alert);
		}
	}

	public void currentDayAlerts(Long userId, String objectName,  Long objectKey, String message, String dateTimeValue) {
		//the Date and LocalTime at which you want to execute
		DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		
		try {
			Date date = dateFormatter.parse(dateTimeValue);
		
	
			//Now create the LocalTime and schedule it
			Timer timer = new Timer();
			PatientTimerTask ptt = new PatientTimerTask( userId,  message,  objectKey,  objectName);
			//Use this if you want to execute it once
			timer.schedule(ptt, date);

		} catch (ParseException e) {
			LOGGER.error(e.getMessage(), e);
		}
	}
	
}
