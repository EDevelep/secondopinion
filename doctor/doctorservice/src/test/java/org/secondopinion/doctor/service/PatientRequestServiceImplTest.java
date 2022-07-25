package org.secondopinion.doctor.service;

import static org.junit.Assert.assertNotNull;

import java.util.Date;
import java.util.List;
import java.util.Objects;

import org.junit.Test;
import org.secondopinion.doctor.DoctorServiceApplicationTests;
import org.secondopinion.doctor.dto.PatientRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

public class PatientRequestServiceImplTest extends DoctorServiceApplicationTests{
	
	@Autowired
	private IDoctorPatientRequestService iPatientRequestService;
	
	@Test
	public void testSavePatientRequest() {
		PatientRequest patientRequest = new PatientRequest();
		patientRequest.setDoctorId(11L);
		patientRequest.setPatientId(49L);
		patientRequest.setNewRequest('Y');
		patientRequest.setRequestAccepted('Y');
		patientRequest.setDescription("doctor appointment");
		patientRequest.setAlignment("serial order");
		patientRequest.setLastUpdatedBy("test2");
		//patientRequest.setLastUpdatedTs(new Date());
		iPatientRequestService.savePatientRequest(patientRequest);
		assertNotNull(patientRequest);
	}
	
	@Test
	public void testGetAllPatientRequestsForDoctor() {
		iPatientRequestService.getAllPatientRequestsForDoctor(11L);
	}
	
	@Test
	public void testUpdatePatientRequests() {
		PatientRequest patientRequest = new PatientRequest();
		patientRequest.setDoctorId(11L);
		patientRequest.setPatientId(49L);
		patientRequest.setNewRequest('Y');
		patientRequest.setRequestAccepted('N');
		patientRequest.setDescription("doctor");
		patientRequest.setAlignment("inline order");
		patientRequest.setLastUpdatedBy("test1");
		patientRequest.setLastUpdatedTs(new Date());
		iPatientRequestService.updatePatientRequests(patientRequest);
	}
	
	@Test
	public void testUpdatePatientRequestsUponDoctorAccepts() {
		iPatientRequestService.updatePatientRequestsUponDoctorAccepts(11L);
	}
	
	@Test
	public void testUpdatePatientRequestsUponDoctorRejects() {
		iPatientRequestService.updatePatientRequestsUponDoctorRejects(12L);
	}
	
	@Test
	public void testGetAllPatientRequestsAcepted() {
		List<PatientRequest> patientrequestdata = iPatientRequestService.getAllPatientRequestsAcepted(38L); 
	    assertNotNull(patientrequestdata);
	}
	
	@Test
	public void testGetAllPatientRequestsNotAccepted() {
		List<PatientRequest> patientrequestdata = iPatientRequestService.getAllPatientRequestsNotAccepted(38L);
		assertNotNull(patientrequestdata);
	}
	
	@Test
	public void testGetAllNewRequestedPAtients() {
		List<PatientRequest> patientrequestdata = iPatientRequestService.getAllNewRequestedPAtients(38L);
		assertNotNull(patientrequestdata);
	}
	
	
	

}
