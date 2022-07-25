package org.secondopinion.doctor.service.impl;

import java.util.List;
import java.util.Objects;

import org.secondopinion.doctor.dao.DoctorDAO;
import org.secondopinion.doctor.dao.PatientRequestDAO;
import org.secondopinion.doctor.dto.PatientRequest;
import org.secondopinion.doctor.service.IDoctorPatientRequestService;
import org.secondopinion.doctor.service.rest.PatientRestAPIService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class PatientRequestServiceImpl implements IDoctorPatientRequestService {

	@Autowired
	private PatientRequestDAO patientRequestDAO;
	
	@Autowired
	private DoctorDAO doctorDAO;
	
	@Autowired
	private PatientRestAPIService patientRestAPIService;
	
	@Override
	@Transactional
	public PatientRequest savePatientRequest(PatientRequest patientRequest) {
		
		PatientRequest dbpatientRequest = patientRequestDAO.getByDoctorIdAndPatientId(patientRequest.getDoctorId(), patientRequest.getPatientId());
		
		if(dbpatientRequest != null) {
			dbpatientRequest.setAlignment(patientRequest.getAlignment());
			dbpatientRequest.setDescription(patientRequest.getDescription());
			dbpatientRequest.setNewRequest(dbpatientRequest.getRequestAccepted() == 'N' ? 'Y' : 'N');
			dbpatientRequest.setRequestAccepted('N');
		} else {
			patientRequest.setNewRequest('Y');
			patientRequest.setRequestAccepted('N');
			dbpatientRequest = patientRequest;
		}
		dbpatientRequest.setActive('Y');
		patientRequestDAO.save(dbpatientRequest);

		
		return patientRequest;
	}
	
	
	@Override
	@Transactional(readOnly=true)
	public List<PatientRequest> getAllPatientRequestsForDoctor(Long doctorId) {
		return patientRequestDAO.findPatientRequestsForDoctorId(doctorId);
	}

	@Override
	@Transactional
	public void updatePatientRequests(PatientRequest patientRequest) {
		patientRequest.setActive('Y');
		patientRequestDAO.save(patientRequest);
		
	}
	
	@Override
	@Transactional
	public void updatePatientRequestsUponDoctorAccepts(Long patientRequestId) {
		PatientRequest patientRequest = patientRequestDAO.findById(patientRequestId);
		if(null == patientRequest) {
			throw new IllegalArgumentException("patient request not found.");
		}
		patientRestAPIService.updateRelationshipUponDoctorAccepts(patientRequest);
		
		patientRequest.setRequestAccepted('Y');
		patientRequest.setNewRequest('N');
		patientRequest.setActive('Y');
		patientRequestDAO.save(patientRequest);
	}
	
	@Override
	@Transactional
	public void updatePatientRequestsUponDoctorRejects(Long patientRequestId) {
		PatientRequest patientRequest = patientRequestDAO.findById(patientRequestId);
		if(null == patientRequest) {
			throw new IllegalArgumentException("patient request not found.");
		}
		
		patientRestAPIService.updateRelationshipUponDoctorRejections(patientRequest);
		patientRequest.setRequestAccepted('N');
		patientRequest.setActive('Y');
		patientRequestDAO.save(patientRequest);
	}
	
	@Override
	@Transactional(readOnly=true)
	public List<PatientRequest> getAllPatientRequestsAcepted(Long doctorId) {
		if(Objects.isNull(doctorDAO.findById(doctorId) )) {
			throw new IllegalArgumentException("Doctor not found");
		}
		return patientRequestDAO.getByDoctorIdAndRequestAcceptedFlag(doctorId, 'Y');
	}


	@Override
	@Transactional(readOnly=true)
	public List<PatientRequest> getAllPatientRequestsNotAccepted(Long doctorId) {
		if(Objects.isNull(doctorDAO.findById(doctorId) )) {
			throw new IllegalArgumentException("Doctor not found");
		}
		return patientRequestDAO.getByDoctorIdAndRequestAcceptedFlag(doctorId, 'N');
	}


	@Override
	public List<PatientRequest> getAllNewRequestedPAtients(Long doctorId) {
		if(Objects.isNull(doctorDAO.findById(doctorId) )) {
			throw new IllegalArgumentException("Doctor not found");
		}
		return patientRequestDAO.getByDoctorIdAndNewRequestFlag(doctorId, 'Y');
	}

}
