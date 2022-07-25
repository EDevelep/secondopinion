package org.secondopnion.patient.service;

import org.junit.Test;
import org.secondopinion.patient.dto.Patientfcmtoken;
import org.secondopinion.patient.service.Patientfcmservice;
import org.secondopnion.patient.PatientApplicationTest;
import org.springframework.beans.factory.annotation.Autowired;

public class PatientfcmserviceTest  extends PatientApplicationTest{
     @Autowired
	private Patientfcmservice patientfcmservice;
	
	@Test
	public void save() {
		Patientfcmtoken patientfcmtoken=new Patientfcmtoken();
		patientfcmtoken.setAndroidtoken("bhafkjblbladla");
		patientfcmtoken.setBrowsertoken("bckjbvkajsbkjasbfjb");
		patientfcmtoken.setPatientid(100L);
		patientfcmtoken.setIphonetoken("bhdaBFajbFKJabflhq3kn4kn235");
		patientfcmservice.savePatientfcmToken(patientfcmtoken);
	}
}
