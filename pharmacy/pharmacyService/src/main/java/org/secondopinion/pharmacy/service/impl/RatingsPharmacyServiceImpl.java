package org.secondopinion.pharmacy.service.impl;

import java.util.List;
import java.util.Objects;

import org.secondopinion.common.dto.RatingsDTO;
import org.secondopinion.pharmacy.dao.PharmacyDAO;
import org.secondopinion.pharmacy.dao.PharmacyratingsDAO;
import org.secondopinion.pharmacy.dto.Pharmacyratings;
import org.secondopinion.pharmacy.dto.PharmacyratingsDTO;
import org.secondopinion.pharmacy.service.IRatingsPharmacy;
import org.secondopinion.request.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

@Service
public class RatingsPharmacyServiceImpl implements IRatingsPharmacy {

	@Autowired
	private PharmacyratingsDAO pharmacyratingsDAO;
	@Autowired
	private PharmacyDAO pharmacyDAO;

	@Override
	@Transactional
	public void saveRatings(Pharmacyratings paharmacyratings) {
		Pharmacyratings dbpharmacyratings = getPharmacyratings(paharmacyratings);
		if(Objects.nonNull(dbpharmacyratings)) {
			pharmacyratingsDAO.save(dbpharmacyratings);
		} else {
			pharmacyratingsDAO.save(paharmacyratings);
		}
		RatingsDTO values = pharmacyratingsDAO.getRatingValues(paharmacyratings.getPharmacyaddressId());
		pharmacyDAO.updatepharmacy(paharmacyratings.getPharmacyaddressId(),values);
	}

	private Pharmacyratings getPharmacyratings(Pharmacyratings paharmacyratings) {
		Pharmacyratings dbRatings = null;
		PharmacyratingsDTO pharmacyratingsDTO = new PharmacyratingsDTO();
		pharmacyratingsDTO.setPatientid(paharmacyratings.getPatientid());
		pharmacyratingsDTO.setPharmacyid(paharmacyratings.getPharmacyaddressId());

		Response<List<Pharmacyratings>> pharmacyRatingsdb = pharmacyratingsDAO
				.getPharmacyratingsSerchCritera(pharmacyratingsDTO);
		List<Pharmacyratings> pharmacyRatingsList = pharmacyRatingsdb.getData();
		if (!CollectionUtils.isEmpty(pharmacyRatingsList)) {
			dbRatings = pharmacyRatingsList.get(0);
			dbRatings.setRating(paharmacyratings.getRating());
			dbRatings.setFeedback(paharmacyratings.getFeedback());
		}
		return dbRatings;
	}

	@Override
	@Transactional(readOnly = true)
	public void updateRatings(Pharmacyratings paharmacyratings) {
		pharmacyratingsDAO.save(paharmacyratings);

	}

	@Override
	@Transactional(readOnly = true)
	public Response<List<Pharmacyratings>> getRatingsByPharmacySerchCritera(PharmacyratingsDTO pharmacyratingsDTO) {
		return pharmacyratingsDAO.getPharmacyratingsSerchCritera(pharmacyratingsDTO);
	}

	
	

}
