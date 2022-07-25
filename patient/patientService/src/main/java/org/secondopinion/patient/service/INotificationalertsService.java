package org.secondopinion.patient.service;

import java.util.Date;
import java.util.List;

import org.secondopinion.configurations.firebase.PushNotificationRequest;
import org.secondopinion.patient.dto.Notificationalerts;
import org.secondopinion.patient.dto.PatientShippingUpdateDTO;
import org.secondopinion.utils.NotificationAlert;

public interface INotificationalertsService {
	
	 void sendNotificationForCancleAppointment(NotificationAlert alert);
	 void sendNotificationForReschudleAppointment(NotificationAlert alert);
	 void sendNotificationForAppointment(NotificationAlert alert);
	List<Notificationalerts> getAllNotificationalerts(Long patientId);

	List<Notificationalerts> getNotificationsToUserForDate(Long patientId, Date date);

	void sendNotificationalertsForFierbase(PushNotificationRequest pucNotificationRequest);

	void saveNotificationalerts(Notificationalerts notificationalerts);

	void updateNotificationalerts(Long notificationId, Notificationalerts notificationalerts);

	void deleteNotificationalerts(Long notificationId);
	 void sendNotificationForInvoice(NotificationAlert alert);
	List<Notificationalerts> getNotificationsNotificationsStatusIsview(Long patientId);

	void sendNotification(NotificationAlert alert);
	void saveNotification(PatientShippingUpdateDTO notificationalerts);

}
