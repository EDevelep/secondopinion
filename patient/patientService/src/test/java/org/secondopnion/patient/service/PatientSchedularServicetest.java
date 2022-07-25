
package org.secondopnion.patient.service;

import org.junit.Test;
import org.secondopinion.patient.service.impl.PatientCronScheduler;
import org.secondopnion.patient.PatientApplicationTest;
import org.springframework.beans.factory.annotation.Autowired;

public class PatientSchedularServicetest extends PatientApplicationTest {

	@Autowired
	private PatientCronScheduler patientSchedular;

	@Test
	public void currentappointmentalerts() {
		patientSchedular.currentappointmentalerts();
	}

	@Test
	public void sendNotificationAlertAtTheTimeOfAppointment() {
		patientSchedular.sendNotificationAlertAtTheTimeOfAppointment();
	}
}
