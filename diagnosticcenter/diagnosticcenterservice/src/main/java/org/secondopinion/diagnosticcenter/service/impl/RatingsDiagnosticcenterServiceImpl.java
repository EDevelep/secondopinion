package org.secondopinion.diagnosticcenter.service.impl;

import java.util.List;
import java.util.Objects;

import org.secondopinion.common.dto.RatingsDTO;
import org.secondopinion.diagnosticcenter.dao.DiagnosticcenterDAO;
import org.secondopinion.diagnosticcenter.dao.DiagnosticcenterratingsDAO;

import org.secondopinion.diagnosticcenter.dto.Diagnosticcenterratings;
import org.secondopinion.diagnosticcenter.dto.DiagnosticcenterratingsDTO;
import org.secondopinion.diagnosticcenter.service.IRatingsDiagnosticcenter;
import org.secondopinion.request.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

@Service
public class RatingsDiagnosticcenterServiceImpl implements IRatingsDiagnosticcenter {

	
	@Autowired
	private DiagnosticcenterratingsDAO diagnosticcenterratingsDAO;
	@Autowired
	private DiagnosticcenterDAO diagnosticcenterDAO; 

	@Override
	@Transactional
	public void saveRatings(Diagnosticcenterratings diagnosticcenterratings) {
		Diagnosticcenterratings dbdiagnosticcenterratings = (diagnosticcenterratings);
		if(Objects.nonNull(dbdiagnosticcenterratings)) {
			diagnosticcenterratingsDAO.save(dbdiagnosticcenterratings);
		} else {
			diagnosticcenterratingsDAO.save(diagnosticcenterratings);
		}
		RatingsDTO values = diagnosticcenterratingsDAO.getRatingValues(diagnosticcenterratings.getDiagnosticcenterId());
		diagnosticcenterDAO.updatediagnosticcenterratings(diagnosticcenterratings.getDiagnosticcenterId(),values);
	}

	private Diagnosticcenterratings getDiagnosticcenterratings(Diagnosticcenterratings diagnosticcenterratings) {
		Diagnosticcenterratings dbRatings = null;
		DiagnosticcenterratingsDTO diagnosticenterratingsDTO = new DiagnosticcenterratingsDTO();
		diagnosticenterratingsDTO.setPatientid(diagnosticcenterratings.getPatientid());
		diagnosticenterratingsDTO.setDiagnosticcenterid(diagnosticcenterratings.getDiagnosticcenterId());

		Response<List<Diagnosticcenterratings>> diagnosticcenterRatingsdb = diagnosticcenterratingsDAO
				.getDiagnosticcenterratingsSerchCritera(diagnosticenterratingsDTO);
		List<Diagnosticcenterratings> diagnosticcenterRatingsList = diagnosticcenterRatingsdb.getData();
		if (!CollectionUtils.isEmpty(diagnosticcenterRatingsList)) {
			dbRatings = diagnosticcenterRatingsList.get(0);
			dbRatings.setRating(diagnosticcenterratings.getRating());
			dbRatings.setFeedback(diagnosticcenterratings.getFeedback());
		}
		return dbRatings;
	}

	@Override
	@Transactional(readOnly = true)
	public void updateRatings(Diagnosticcenterratings diagnosticcenterratings) {
		diagnosticcenterratingsDAO.save(diagnosticcenterratings);

	}

	@Override
	@Transactional(readOnly = true)
	public Response<List<Diagnosticcenterratings>> getRatingsBydiagnosticcenterSerchCritera(DiagnosticcenterratingsDTO diagnosticcenterratingsDTO) {
		return diagnosticcenterratingsDAO.getDiagnosticcenterratingsSerchCritera(diagnosticcenterratingsDTO);
	}

	
	

}
