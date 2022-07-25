package org.secondopinion.patient.dao;

import org.secondopinion.dao.IDAO;
import org.secondopinion.patient.dto.Patientfcmtoken;

public interface PatientfcmtokenDAO extends IDAO<Patientfcmtoken,Long >{

	Patientfcmtoken findPatientfcmtokenByPatientid(Long patientid);
}