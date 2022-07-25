package org.secondopinion.patient.service.impl;

import java.util.Objects;

import javax.transaction.Transactional;

import org.secondopinion.patient.dao.PatientfcmtokenDAO;
import org.secondopinion.patient.dto.Patientfcmtoken;
import org.secondopinion.patient.service.Patientfcmservice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class Patientfcmserviceimpl implements Patientfcmservice {
	
	@Autowired
	private PatientfcmtokenDAO patientfcmtokenDAO;

	@Override
	@Transactional
	public Patientfcmtoken savePatientfcmToken(Patientfcmtoken patientfcmtoken) {
		Patientfcmtoken patientfcmtokens = patientfcmtokenDAO.findOneByProperty(Patientfcmtoken.FIELD_patientid, patientfcmtoken.getPatientid());
		if(Objects.isNull(patientfcmtokens)) {
			patientfcmtokens = Patientfcmtoken.biuldForupadtepatientfcmtoken(patientfcmtoken, new Patientfcmtoken());
		} else {
			patientfcmtokens = Patientfcmtoken.biuldForupadtepatientfcmtoken(patientfcmtoken, patientfcmtokens);
		}
		patientfcmtokens.setActive('Y');
		patientfcmtokenDAO.save(patientfcmtokens);
		return patientfcmtokens;
	}


}
