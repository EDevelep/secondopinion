package org.secondopinion.patient.service;

import java.util.List;

import org.secondopinion.patient.dto.Patientpreference;

public interface IPatientpreferenceService {

	void savePatientpreference(Patientpreference patientpreference);

	List<Patientpreference> getPatientpreference(Long patientid);

	List<Patientpreference> getPatientpreferenceForpharmacy(Long pharmacyid);

	Patientpreference getPatientpreferenceForpharmacyfillReq(Long patientId);

}
