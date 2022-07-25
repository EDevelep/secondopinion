package org.secondopinion.pharmacy.service;

import java.util.Date;
import java.util.List;

import org.secondopinion.configurations.firebase.PushNotificationRequest;
import org.secondopinion.pharmacy.dto.Notificationalerts;
import org.secondopinion.utils.NotificationAlert;



public interface INotificationalertsService {

	List<Notificationalerts> getAllNotificationalerts(Long doctorId);
	List<Notificationalerts> getNotificationalertsForDate(Long pharmacyId,Date date);
	//List<Notificationalerts> getNotificationalertsForDate(Long pharmacyId);
	void sendNotificationalertstofierbase(PushNotificationRequest request);

	void saveNotificationalerts(Notificationalerts notificationalerts);

	void updateNotificationalerts(Long notificationId, Notificationalerts notificationalerts);

	void deleteNotificationalerts(Long notificationId);

	void utiliesMethodForNotification(Long pharmacyId, String objectName, Long objectKey, String meesage);

	void sendNotificationalertstoThePharmacyFromPatient(NotificationAlert alertsUtilDTO);
}
