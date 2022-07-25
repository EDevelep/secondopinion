package org.secondopinion.doctor.dao;

import java.util.Date;
import java.util.List;

import org.secondopinion.dao.IDAO;
import org.secondopinion.doctor.dto.Notificationalerts;

public interface NotificationalertsDAO extends IDAO<Notificationalerts,Long >{
	
	List<Notificationalerts>   getNotificationalerts(Date dateTo,Long doctorId);

	Notificationalerts findnotificationById(Long notificationId);

	List<Notificationalerts> findNotificationalertsByDoctorId(Long doctorId);
}