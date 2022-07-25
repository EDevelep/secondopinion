/**
 * 
 */
package org.secondopinion.diagnosticcenter.serviceimpl;

import static org.junit.Assert.assertNotNull;

import java.util.Date;
import java.util.List;

import org.junit.Test;
import org.secondopinion.diagnosticcenter.dto.Notificationalerts;
import org.secondopinion.diagnosticcenter.service.IDiagnosticCenterNotificationalertsService;
import org.secondopinion.diagnosticcenter.test.DiagnosticcenterServiceApplicationTest;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author admin
 *
 */
public class DiagnosticCenterNotificationalertsServiceTest extends DiagnosticcenterServiceApplicationTest{

	
	@Autowired
	private IDiagnosticCenterNotificationalertsService iDiagnosticCenterNotificationalertsService;

	@Test
	public void getAllNotificationalerts() {
		Long doctorId = 40L;
		List<Notificationalerts> notificationalerts = iDiagnosticCenterNotificationalertsService.getAllNotificationalerts(doctorId);
		assertNotNull(notificationalerts);
	}
	
	@Test
	public void getAllNotificationalertsByNextWeek() {
		Long doctorId = 40L;
		List<Notificationalerts> notificationalerts = iDiagnosticCenterNotificationalertsService.getNotificationalertsForDate(doctorId,null);
		assertNotNull(notificationalerts);
	}
	
	@Test
	public void testSaveDiagnosticcenterNotificationalerts() {
		Notificationalerts notificationalerts = new Notificationalerts();
		notificationalerts.setExpirydate(new Date());
		notificationalerts.setViewed('N');
		notificationalerts.setReason("test");
		notificationalerts.setDiagnosticCenterAddressId(123L);
		iDiagnosticCenterNotificationalertsService.saveNotificationalerts(notificationalerts);
		assertNotNull(notificationalerts);
	}
		
	@Test
	public void testUpdateNotificationalerts() {
		Notificationalerts notificationalerts = new Notificationalerts();
		notificationalerts.setExpirydate(new Date());
		notificationalerts.setViewed('N');
		notificationalerts.setReason("test");
		notificationalerts.setDiagnosticCenterAddressId(123L);
		iDiagnosticCenterNotificationalertsService.updateNotificationalerts(101L, notificationalerts);
		assertNotNull(notificationalerts);
	}
	

	
}
