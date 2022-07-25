package org.secondopinion.doctor.dto; 


import java.util.TimeZone;

import javax.persistence.Entity; 
 
import javax.persistence.Table;

import org.secondopinion.doctor.domain.BaseNotificationalerts;
import org.secondopinion.utils.DateUtil;
import org.secondopinion.utils.NotificationAlert; 





@Entity 
@Table (name="notificationalerts")
public class Notificationalerts extends BaseNotificationalerts{

	public static Notificationalerts buildNotificationalerts(NotificationAlert alert) {
		Notificationalerts notificationalerts = new Notificationalerts();

		notificationalerts.setDoctorId(alert.getUserPrimaryKey());
		notificationalerts.setObjecttype(alert.getObjectName());
		notificationalerts.setObjectkey(alert.getObjectKey());
		notificationalerts.setReason(alert.getMessage());
		notificationalerts.setViewed('N');
		notificationalerts.setExpirydate(DateUtil.addAndGetDate( TimeZone.getDefault(), 7));
		
		return notificationalerts;
	}
}