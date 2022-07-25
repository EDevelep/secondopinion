package org.secondopinion.patient.dao;

import java.util.List;

import org.secondopinion.dao.IDAO;
import org.secondopinion.patient.dto.Patientratings;
import org.secondopinion.patient.dto.PatientratingsSearchCriteria;
import org.secondopinion.request.Response;

public interface PatientratingsDAO extends IDAO<Patientratings,Long >{

	Response<List<Patientratings>> getRatingsByCriteria(PatientratingsSearchCriteria ratings);

	List<Patientratings> getRatingsBydoctorIds(List<Long> doctorIds);
}