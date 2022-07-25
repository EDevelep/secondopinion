package org.secondopinion.doctor.dao;




import java.util.List;

import org.secondopinion.common.dto.RatingsDTO;
import org.secondopinion.dao.IDAO;
import org.secondopinion.doctor.domain.DoctorRatingDTO;
import org.secondopinion.doctor.dto.Doctorratings;
import org.secondopinion.request.Response;




public interface DoctorratingsDAO extends IDAO<Doctorratings,Long >{
	
	RatingsDTO getRatingValues(Long doctorId);
	

	 Response<List<Doctorratings>> getDoctorratingSerchCritera(DoctorRatingDTO doctorrating)	;	// TODO Auto-generated method stub


	List<Doctorratings> findByDoctorratings(Long doctorId);


	List<Doctorratings> findByDoctorratings(List<Long> doctorId);
		
	}