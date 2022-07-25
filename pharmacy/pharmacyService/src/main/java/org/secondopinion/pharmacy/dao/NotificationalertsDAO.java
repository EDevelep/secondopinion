package org.secondopinion.pharmacy.dao;

import java.util.Date;
import java.util.List;

import org.secondopinion.dao.IDAO;
import org.secondopinion.pharmacy.dto.Notificationalerts;

public interface NotificationalertsDAO extends IDAO<Notificationalerts,Long >{
	List<Notificationalerts> getNotificationalerts(Long pharmacyId,Date date);
	List<Notificationalerts> getNotificationalerts(Long pharmacyId);

	Notificationalerts getByAlertId(Long notificationId);

	void deleteNotificationalerts(Notificationalerts dbnotificationalerts);
	

}