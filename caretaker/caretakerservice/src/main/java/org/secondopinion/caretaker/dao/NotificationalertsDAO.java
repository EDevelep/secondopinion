package org.secondopinion.caretaker.dao;

import java.util.Date;
import java.util.List;

import org.secondopinion.caretaker.dto.Notificationalerts;
import org.secondopinion.dao.IDAO;


public interface NotificationalertsDAO extends IDAO<Notificationalerts,Long >{
	
	List<Notificationalerts>   getNotificationalerts(Date dateTo,Long caretakerId);

	Notificationalerts findnotificationById(Long notificationId);

	List<Notificationalerts> findNotificationalertsByCaretakerId(Long caretakerId);
}