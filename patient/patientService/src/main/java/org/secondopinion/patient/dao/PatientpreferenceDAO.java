package org.secondopinion.patient.dao;

import java.util.List;

import org.secondopinion.dao.IDAO;
import org.secondopinion.patient.dto.Patientpreference;

public interface PatientpreferenceDAO extends IDAO<Patientpreference,Long >{

	List<Patientpreference> findPatientpreferenceBypatientid(Long patientid);

	List<Patientpreference> findPatientpreferenceForpharmacy(Long pharmacyid);

	Patientpreference getPatientpreferenceForpharmacyfillReq(Long patientId);
}