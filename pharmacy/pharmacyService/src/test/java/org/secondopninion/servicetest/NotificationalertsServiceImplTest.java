package org.secondopninion.servicetest;

import static org.junit.Assert.assertNotNull;

import java.util.List;

import org.junit.Test;
import org.secondopinion.pharmacy.dto.Notificationalerts;
import org.secondopinion.pharmacy.service.INotificationalertsService;
import org.secondopninion.PharmacyApplactionTest;
import org.springframework.beans.factory.annotation.Autowired;

public class NotificationalertsServiceImplTest extends PharmacyApplactionTest{
	
	@Autowired
	private INotificationalertsService iNotificationalertsService;
	
	@Test
	public void testGetAllNotificationalerts() {
		List<Notificationalerts> notificationalerts = iNotificationalertsService.getAllNotificationalerts(16L);
		assertNotNull(notificationalerts);
	}
	
	@Test
	public void testSaveNotificationalerts() {
		Notificationalerts notificationalerts = new Notificationalerts();
//		notificationalerts.setCreartedby();
//		notificationalerts.setCreatedDate();
//		notificationalerts.setExpirydate();
//		notificationalerts.setLastUpdatedBy();
//		notificationalerts.setLastUpdatedTs();
//		notificationalerts.setNotificationalertsId();
		iNotificationalertsService.saveNotificationalerts(notificationalerts);
	}
	
	@Test
	public void testUpdateNotificationalerts() {
		Notificationalerts notificationalerts = new Notificationalerts();
		Long notificationId = notificationalerts.getNotificationalertsId();
//		notificationalerts.setCreartedby();
//		notificationalerts.setCreatedDate();
//		notificationalerts.setExpirydate();
//		notificationalerts.setLastUpdatedBy();
//		notificationalerts.setLastUpdatedTs();
//		notificationalerts.setNotificationalertsId();
		iNotificationalertsService.updateNotificationalerts(notificationId, notificationalerts);
	}

	@Test
	public void testDeleteNotificationalerts() {
		iNotificationalertsService.deleteNotificationalerts(101L);
	}
	
//	@Test
//	public void testSendNotificationalertstofierbase() {
//		PushNotificationRequest request = new PushNotificationRequest();
//		request.setMessage("message");
//		request.setTitle("title");
//		request.setToken("token");
//		request.setTopic("topic");
//		iNotificationalertsService.sendNotificationalertstofierbase(request);
//	}
	
//	@Test
//	public void testSendNotificationalertstoTheDoctorFromPatient() {
//		AlertsUtilDTO alertsUtilDTO = new AlertsUtilDTO();
//		alertsUtilDTO.setMessage("message");
//		alertsUtilDTO.setObjectKey(101L);
//		alertsUtilDTO.setObjectName("object name");
//		alertsUtilDTO.setUserPrimaryKey(12L);
//		iNotificationalertsService.sendNotificationalertstoTheDoctorFromPatient(alertsUtilDTO);
//	}
	
}
