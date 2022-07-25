package org.secondopinion.pharmacy.service;

import java.util.List;

import org.secondopinion.pharmacy.dto.Pharmacyratings;
import org.secondopinion.pharmacy.dto.PharmacyratingsDTO;
import org.secondopinion.request.Response;



public interface IRatingsPharmacy {
	void saveRatings(Pharmacyratings paharmacyratings);
	void updateRatings(Pharmacyratings paharmacyratings);
	Response<List<Pharmacyratings>> getRatingsByPharmacySerchCritera(PharmacyratingsDTO pharmacyratingsDTO);

}
