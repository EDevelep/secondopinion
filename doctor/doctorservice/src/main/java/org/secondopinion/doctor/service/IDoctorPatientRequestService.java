package org.secondopinion.doctor.service;

import java.util.List;

import org.secondopinion.doctor.dto.PatientRequest;

public interface IDoctorPatientRequestService {

	PatientRequest savePatientRequest(PatientRequest patientRequest);

	List<PatientRequest> getAllPatientRequestsForDoctor(Long doctorId);

	void updatePatientRequests(PatientRequest patientRequest);

	List<PatientRequest> getAllPatientRequestsAcepted(Long doctorId);

	List<PatientRequest> getAllPatientRequestsNotAccepted(Long doctorId);

	List<PatientRequest> getAllNewRequestedPAtients(Long doctorId);

	void updatePatientRequestsUponDoctorAccepts(Long patientRequestId);

	void updatePatientRequestsUponDoctorRejects(Long patientRequestId);

}
