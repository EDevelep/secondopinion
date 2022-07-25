package org.secondopnion.patient.service;

import static org.junit.Assert.assertNotNull;

import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.secondopinion.patient.dto.Patientratings;
import org.secondopinion.patient.dto.PatientratingsSearchCriteria;
import org.secondopinion.patient.dto.Patientratings.RatingForEnum;
import org.secondopinion.patient.service.impl.PatientRatingServiceImpl;
import org.secondopinion.request.Response;
import org.secondopnion.patient.PatientApplicationTest;
import org.springframework.beans.factory.annotation.Autowired;

public class PatientRatingServiceTest extends PatientApplicationTest {

	@Autowired
	private PatientRatingServiceImpl patientRatingServiceImpl;
	
	@Test
	public void savePatientratingTest() {
		Patientratings patientratings=new Patientratings();
		patientratings.setPatientid(141L);
		patientratings.setRatingFor(RatingForEnum.DOCTOR_APPOINTMENT.name());
		patientratings.setReferenceId(51L);
		patientratings.setRating(5.0d);
		patientratings.setFeedback("test");
		patientRatingServiceImpl.savePatientratings(patientratings);;
	}
	
	@Test
	public void getRatingsByCriteriaTest() {
		PatientratingsSearchCriteria ratings = new PatientratingsSearchCriteria();
		ratings.setPatientid(141L);
		ratings.setRatingForEnum(RatingForEnum.DOCTOR_APPOINTMENT);
		ratings.setReferenceId(51L);
		Response<List<Patientratings>> patientRatings = patientRatingServiceImpl.getRatingsByCriteria(ratings);
		assertNotNull(patientRatings);
	}
}
