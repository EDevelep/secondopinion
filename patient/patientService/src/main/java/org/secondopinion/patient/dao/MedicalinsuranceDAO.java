package org.secondopinion.patient.dao;

import java.util.List;

import org.secondopinion.dao.IDAO;
import org.secondopinion.patient.dto.ForUser;
import org.secondopinion.patient.dto.Medicalinsurance;

public interface MedicalinsuranceDAO extends IDAO<Medicalinsurance,Long >{
	
	List<Medicalinsurance> getMedicalInsuranceDtailsForUser( Long userId);

	Medicalinsurance getMediclInsuranceForUserId(Long userId);

	Medicalinsurance getMediclInsuranceFromedicalinsuranceId(Long userId);
}