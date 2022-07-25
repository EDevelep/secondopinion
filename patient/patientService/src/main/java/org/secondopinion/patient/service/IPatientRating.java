package org.secondopinion.patient.service;


import java.util.List;
import java.util.Map;

import org.secondopinion.patient.dto.Patientratings;
import org.secondopinion.patient.dto.PatientratingsSearchCriteria;
import org.secondopinion.request.Response;

public interface IPatientRating {

	void savePatientratings(Patientratings patientratings);

	Response<List<Patientratings>> getRatingsByCriteria(PatientratingsSearchCriteria ratings);
	
	List<Patientratings> getRatingsBydoctorIds(List<Long> doctorIds);

}
