package org.secondopinion.doctor.service;

import java.util.Date;
import java.util.List;

import org.secondopinion.configurations.firebase.PushNotificationRequest;
import org.secondopinion.doctor.dto.Notificationalerts;
import org.secondopinion.utils.NotificationAlert;



public interface INotificationalertsService {
	 void sendNotificationForAppointment(NotificationAlert alert);
	List<Notificationalerts> getAllNotificationalerts(Long doctorId);
	List<Notificationalerts> getAllNotificationalertsByNextWeek(Date dateTo,Long doctorId);
	void sendNotificationalertstofierbase(PushNotificationRequest request);

	void saveNotificationalerts(Notificationalerts notificationalerts);

	void updateNotificationalerts(Long notificationId, Notificationalerts notificationalerts);

	void deleteNotificationalerts(Long notificationId);

	void sendNotification(NotificationAlert alert);

	void sendNotificationToTheDoctorByAppointment(NotificationAlert alert);
	void sendNotificationForCancleAppointment(NotificationAlert alert);
	
}
