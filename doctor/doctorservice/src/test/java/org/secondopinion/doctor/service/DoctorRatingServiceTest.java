package org.secondopinion.doctor.service;

import static org.junit.Assert.assertNotNull;

import org.junit.Test;
import org.secondopinion.doctor.DoctorServiceApplicationTests;
import org.secondopinion.doctor.dao.DoctorratingsDAO;
import org.secondopinion.doctor.domain.DoctorRatingDTO;
import org.secondopinion.doctor.dto.Doctorratings;
import org.secondopinion.doctor.service.impl.Doctorratingsserviceimpl;
import org.springframework.beans.factory.annotation.Autowired;

public class DoctorRatingServiceTest extends DoctorServiceApplicationTests {

	@Autowired
	private Doctorratingsserviceimpl doctorratingsserviceimpl;
	@Autowired
	private DoctorratingsDAO ratingsdao;

	@Test
	public void saveRating() {
		Doctorratings ratings = new Doctorratings();
		ratings.setDoctorratingid(56l);
		ratings.setDoctorid(38l);
		ratings.setPatientid(141l);
		ratings.setActive('Y');
		ratings.setAppointmentid(38l);
		ratings.setRating(5.00);
		ratings.setFeedback("test");
	//	ratingsdao.save(ratings);
		
		doctorratingsserviceimpl.saveRatings(ratings);
		
		assertNotNull(ratings);
	}

	@Test
	public void getDoctorratingsByid() {
		Long doctorId = 40L;
		doctorratingsserviceimpl.getDoctorratingsByid(doctorId);
	}
	
	@Test
	public void getDoctorratingSerchCritera() {
		DoctorRatingDTO doctorRatingDTO = new DoctorRatingDTO();
		doctorRatingDTO.setAppointmentid(51l);
		doctorRatingDTO.setDoctorid(51l);
		doctorRatingDTO.setFeedback("test");
		doctorRatingDTO.setMaxresult(5);
		doctorRatingDTO.setPagenumber(2);
		doctorRatingDTO.setPatientid(141l);
		doctorRatingDTO.setRating(35.42);
		doctorratingsserviceimpl.getDoctorratingSerchCritera(doctorRatingDTO);
	}
	
	@Test
	public void testUpdateDoctorratings() {
		Doctorratings ratings = new Doctorratings();
		ratings.setAppointmentid(101L);
		ratings.setDoctorid(38L);
		ratings.setPatientid(12L);
		ratings.setRating(10D);
		doctorratingsserviceimpl.updateDoctorratings(ratings);
		assertNotNull(ratings);
	}

}
