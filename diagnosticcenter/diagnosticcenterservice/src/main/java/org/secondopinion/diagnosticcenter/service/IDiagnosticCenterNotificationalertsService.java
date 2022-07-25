package org.secondopinion.diagnosticcenter.service;

import java.util.Date;
import java.util.List;

import org.secondopinion.configurations.firebase.PushNotificationRequest;
import org.secondopinion.diagnosticcenter.dto.Notificationalerts;
import org.secondopinion.utils.NotificationAlert;



public interface IDiagnosticCenterNotificationalertsService {

	
	List<Notificationalerts> getAllNotificationalerts(Long diagnosticCenterAddressId);
	 List<Notificationalerts> getNotificationalertsForDate(Long diagnoticCenterAddressId,Date date);
	//List<Notificationalerts> getNotificationalertsForWeek(Long diagnosticCenterAddressId);
	void sendNotificationalertstofierbase(PushNotificationRequest request);

	void saveNotificationalerts(Notificationalerts notificationalerts);

	void updateNotificationalerts(Long notificationId, Notificationalerts notificationalerts);

	void deleteNotificationalerts(Long notificationId);

	void utiliesMethodForNotification(Long diagnoticCenterId, String objectName, Long objectKey, String meesage);

	void sendNotificationalerts(NotificationAlert alertsUtilDTO);
}
