package org.secondopinion.doctor.service;

import java.util.List;

import org.secondopinion.doctor.domain.DoctorRatingDTO;
import org.secondopinion.doctor.dto.Doctorratings;
import org.secondopinion.request.Response;


public interface IDoctorratings {

	void saveRatings(Doctorratings ratings);
	List<Doctorratings> getDoctorratingsByid(Long doctorratingsId);
	void updateDoctorratings( Doctorratings ratings);
	 Response<List<Doctorratings>> getDoctorratingSerchCritera(DoctorRatingDTO doctorRatingDTO);
	List<Doctorratings> getDoctorratingsByid(List<Long> doctorId);
	
    
}
