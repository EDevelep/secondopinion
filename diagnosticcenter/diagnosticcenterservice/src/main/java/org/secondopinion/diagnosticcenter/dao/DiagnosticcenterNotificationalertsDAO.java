package org.secondopinion.diagnosticcenter.dao;

import java.util.Date;
import java.util.List;

import org.secondopinion.dao.IDAO;
import org.secondopinion.diagnosticcenter.dto.Notificationalerts;


public interface DiagnosticcenterNotificationalertsDAO extends IDAO<Notificationalerts,Long >{
	List<Notificationalerts> getNotificationalerts(Long diagnoticCenterId);

	List<Notificationalerts> getNotificationalerts(Long diagnoticCenterId,Date date);
	Notificationalerts getByAlertId(Long notificationId);

	void deleteNotificationalerts(Notificationalerts dbnotificationalerts);

}