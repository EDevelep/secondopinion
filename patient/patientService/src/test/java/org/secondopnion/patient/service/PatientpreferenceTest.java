package org.secondopnion.patient.service;

import static org.junit.Assert.assertNotNull;

import java.util.List;

import org.junit.Test;
import org.secondopinion.patient.dto.Patientpreference;
import org.secondopinion.patient.service.IPatientpreferenceService;
import org.secondopnion.patient.PatientApplicationTest;
import org.springframework.beans.factory.annotation.Autowired;

public class PatientpreferenceTest extends PatientApplicationTest {

	@Autowired
	private IPatientpreferenceService patientpreferenceService;

	@Test
	public void save() {
		Patientpreference patientpreference = new Patientpreference();
		patientpreference.setClinicid(16L);
		patientpreference.setDiagnosticcenterid(162L);
		patientpreference.setPharmacyid(141L);
		patientpreference.setPatientid(101L);
		patientpreferenceService.savePatientpreference(patientpreference);
	}

	@Test
	public void getPatientpreference() {
		Long patientid = 141L;
		List<Patientpreference> patientpreferences = patientpreferenceService.getPatientpreference(patientid);
		assertNotNull(patientpreferences);
	}

	@Test
	public void getPatientpreferenceForpharmacy() {
		Long pharmacyid = 121L;
		List<Patientpreference> patientpreferences = patientpreferenceService.getPatientpreferenceForpharmacy(pharmacyid);
		assertNotNull(patientpreferences);
	}

}
