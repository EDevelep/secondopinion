package org.secondopinion.patient.service.impl;

import java.util.List;
import java.util.Objects;

import org.secondopinion.patient.dao.PatientpreferenceDAO;
import org.secondopinion.patient.dto.Patientpreference;
import org.secondopinion.patient.service.IPatientpreferenceService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class PatientpreferenceServiceImpl implements IPatientpreferenceService {

	@Autowired
	private PatientpreferenceDAO patientpreferenceDAO;

	@Override
	@Transactional
	public void savePatientpreference(Patientpreference patientpreference) {
		Patientpreference dbpatientpreference = patientpreferenceDAO
				.findOneByProperty(Patientpreference.FIELD_patientid, patientpreference.getPatientid());
		if (Objects.nonNull(dbpatientpreference)) {
			dbpatientpreference.setPharmacyid(patientpreference.getPharmacyid());
			dbpatientpreference.setDiagnosticcenterid(patientpreference.getDiagnosticcenterid());
			dbpatientpreference.setClinicid(patientpreference.getClinicid());
			patientpreference.setActive('Y');
			patientpreferenceDAO.save(dbpatientpreference);
		} else {
			patientpreference.setActive('Y');
			patientpreferenceDAO.save(patientpreference);
		}

	}

	@Override
	@Transactional(readOnly = true)
	public List<Patientpreference> getPatientpreference(Long patientid) {
		return patientpreferenceDAO.findPatientpreferenceBypatientid(patientid);
	}

	@Override
	@Transactional(readOnly = true)
	public List<Patientpreference> getPatientpreferenceForpharmacy(Long pharmacyid) {
		return patientpreferenceDAO.findPatientpreferenceForpharmacy(pharmacyid);
	}

	@Override
	@Transactional
	public Patientpreference getPatientpreferenceForpharmacyfillReq(Long patientId) {
		
		return patientpreferenceDAO.getPatientpreferenceForpharmacyfillReq(patientId);
	}

}
