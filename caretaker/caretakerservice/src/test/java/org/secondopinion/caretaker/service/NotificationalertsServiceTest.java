package org.secondopinion.caretaker.service;

import static org.junit.Assert.assertNotNull;

import java.util.Date;
import java.util.List;

import org.junit.Test;
import org.secondopinion.caretaker.CareTakerServiceApplicationTest;
import org.secondopinion.caretaker.dto.Notificationalerts;
import org.secondopinion.utils.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

public class NotificationalertsServiceTest extends CareTakerServiceApplicationTest {

	@Autowired
	private INotificationalertsService notificationalertsService;

	@Test
	public void getAllNotificationalerts() {
		Long doctorId = 40L;
		List<Notificationalerts> notificationalerts = notificationalertsService.getAllNotificationalerts(doctorId);
		assertNotNull(notificationalerts);
	}
	
	@Test
	public void getAllNotificationalertsByNextWeek() {
		Long doctorId = 40L;
		Date date=null;
		List<Notificationalerts> notificationalerts = notificationalertsService.getAllNotificationalertsByNextWeek(date,doctorId);
		assertNotNull(notificationalerts);
	}
	
	@Test
	public void testSaveNotificationalerts() {
		Notificationalerts notificationalerts = new Notificationalerts();
		notificationalerts.setCaretakerId(12L);
		notificationalerts.setExpirydate(new Date());
		notificationalerts.setViewed('N');
		notificationalerts.setReason("test");
		notificationalertsService.saveNotificationalerts(notificationalerts);
		assertNotNull(notificationalerts);
	}
		
	@Test
	public void testUpdateNotificationalerts() {
		Notificationalerts notificationalerts = new Notificationalerts();
		notificationalerts.setCaretakerId(12L);
		notificationalerts.setExpirydate(new Date());
		notificationalerts.setViewed('N');
		notificationalerts.setReason("test");
		notificationalertsService.updateNotificationalerts(101L, notificationalerts);
		assertNotNull(notificationalerts);
	}
	

}
