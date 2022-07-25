package org.secondopinion.doctor.service.impl;

import java.util.List;
import java.util.Objects;

import org.secondopinion.common.dto.RatingsDTO;
import org.secondopinion.doctor.dao.DoctorDAO;
import org.secondopinion.doctor.dao.DoctorratingsDAO;
import org.secondopinion.doctor.domain.DoctorRatingDTO;
import org.secondopinion.doctor.dto.Doctorratings;
import org.secondopinion.doctor.service.IDoctorratings;
import org.secondopinion.request.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

@Service
public class Doctorratingsserviceimpl implements IDoctorratings {

	@Autowired
	private DoctorratingsDAO ratingsdao;
	@Autowired
	private DoctorDAO doctorDao;

	@Override
	@Transactional
	public void saveRatings(Doctorratings ratings) {
		Doctorratings dbRatings= getDoctorratingFromDb( ratings);
		if(Objects.nonNull(dbRatings)) {
			ratingsdao.save(dbRatings);
		} else {
			ratingsdao.save(ratings);
		}
		RatingsDTO values = ratingsdao.getRatingValues(ratings.getDoctorid());

		doctorDao.updateRatings(ratings.getDoctorid(), values);

	}
	
	private Doctorratings getDoctorratingFromDb(Doctorratings ratings) {
		Doctorratings dbRatings = null;
		DoctorRatingDTO doctorRatingDTO = new DoctorRatingDTO();
		doctorRatingDTO.setAppointmentid(ratings.getAppointmentid());
		doctorRatingDTO.setDoctorid(ratings.getDoctorid());
		
		Response<List<Doctorratings>> doctorRatings = ratingsdao.getDoctorratingSerchCritera(doctorRatingDTO);
		List<Doctorratings> doctorRatingsList = doctorRatings.getData();
		if(!CollectionUtils.isEmpty(doctorRatingsList)) {
			dbRatings = doctorRatingsList.get(0);
			dbRatings.setRating(ratings.getRating());
			dbRatings.setFeedback(ratings.getFeedback());
		}
		return dbRatings;
	}

	@Override
	@Transactional(readOnly = true)
	public Response<List<Doctorratings>> getDoctorratingSerchCritera(DoctorRatingDTO doctorRatingDTO) {
		return ratingsdao.getDoctorratingSerchCritera(doctorRatingDTO);
	}

	@Override
	@Transactional(readOnly = true)
	public List<Doctorratings> getDoctorratingsByid(Long doctorId) {
		return ratingsdao.findByDoctorratings(doctorId);
	}

	@Override
	@Transactional(readOnly = true)
	public List<Doctorratings> getDoctorratingsByid(List<Long> doctorId) {
		return ratingsdao.findByDoctorratings(doctorId);
	}
	@Override
	@Transactional
	public void updateDoctorratings(Doctorratings ratings) {
		ratings.setActive('Y');
		ratingsdao.save(ratings);
	}

}