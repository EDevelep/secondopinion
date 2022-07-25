package org.secondopinion.patient.dao;

import java.util.Date;
import java.util.List;

import org.secondopinion.dao.IDAO;
import org.secondopinion.patient.dto.Notificationalerts;

public interface NotificationalertsDAO extends IDAO<Notificationalerts,Long >{
	
	List<Notificationalerts> pushNotificationsToUser(Long foruserId);
	
	List<Notificationalerts> getNotificationsToUserForDate(Long patientId,Date date);

	List<Notificationalerts> findnotificationalertsByPatientId(Long patientId);

	Notificationalerts findNotificationalertsBynotificationalertsId(Long notificationId);
	
	 List<Notificationalerts> getNotificationsNotificationsStatusIsview(Long foruserId);

	List<Notificationalerts> findnotificationalertsBydate(Date date);
}