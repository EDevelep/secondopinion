package org.secondopinion.doctor.dao;

import java.util.List;

import org.secondopinion.dao.IDAO;
import org.secondopinion.doctor.dto.PatientRequest;

public interface PatientRequestDAO extends IDAO<PatientRequest,Long >{

	PatientRequest getByDoctorIdAndPatientId(Long doctorId, Long patientId);

	public List<PatientRequest> getByDoctorIdAndRequestAcceptedFlag(Long doctorId, Character c);
	
	public List<PatientRequest> getByDoctorIdAndNewRequestFlag(Long doctorId, Character requestAccetedFlag);

	List<PatientRequest> findPatientRequestsForDoctorId(Long doctorId);

}