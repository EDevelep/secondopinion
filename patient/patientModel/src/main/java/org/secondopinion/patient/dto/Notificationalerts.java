package org.secondopinion.patient.dto; 


import java.util.TimeZone;

import javax.persistence.Entity;
import javax.persistence.Table;


import org.secondopinion.patient.domain.BaseNotificationalerts;
import org.secondopinion.utils.DateUtil;
import org.secondopinion.utils.NotificationAlert; 



@Entity 
@Table (name="notificationalerts")
public class Notificationalerts extends BaseNotificationalerts{
	
	public static Notificationalerts buildNotificationalerts(NotificationAlert alert) {
		Notificationalerts notificationalerts = new Notificationalerts();

		notificationalerts.setPatientId(alert.getUserPrimaryKey());
		notificationalerts.setObjecttype(alert.getObjectName());
		notificationalerts.setObjectkey(alert.getObjectKey());
		notificationalerts.setReason(alert.getMessage());
		notificationalerts.setViewed('N');
		notificationalerts.setExpirydate(DateUtil.addAndGetDate( TimeZone.getDefault(), 7));
		
		return notificationalerts;
	}

	public static Notificationalerts buildNotificationalertsForShipping(PatientShippingUpdateDTO notificationalerts) {
		Notificationalerts notificationalerts1 = new Notificationalerts();

		notificationalerts1.setPatientId(notificationalerts.getPatientid());
		notificationalerts1.setObjecttype(notificationalerts.getName());
		notificationalerts1.setObjectkey(notificationalerts.getPharmacyId());
		notificationalerts1.setReason(notificationalerts.getMessage());
		notificationalerts1.setViewed('N');
		notificationalerts1.setExpirydate(DateUtil.addAndGetDate( TimeZone.getDefault(), 7));
		
		return notificationalerts1;
	}

	public static Notificationalerts buildNotificationalertsForShipping(InvoiceStatusDTO invoiceStatusDTO) {
		Notificationalerts notificationalerts1 = new Notificationalerts();

		notificationalerts1.setPatientId(invoiceStatusDTO.getPatientId());
		notificationalerts1.setObjecttype(invoiceStatusDTO.getStatus());
		notificationalerts1.setObjectkey(invoiceStatusDTO.getInvoiceId());
		notificationalerts1.setReason(invoiceStatusDTO.getMesssage());
		notificationalerts1.setViewed('N');
		notificationalerts1.setExpirydate(DateUtil.addAndGetDate( TimeZone.getDefault(), 7));
		
		return notificationalerts1;
	}


}