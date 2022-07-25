package org.secondopinion.pharmacy.dao;

import java.util.List;

import org.secondopinion.common.dto.RatingsDTO;
import org.secondopinion.dao.IDAO;
import org.secondopinion.pharmacy.dto.Pharmacyratings;
import org.secondopinion.pharmacy.dto.PharmacyratingsDTO;
import org.secondopinion.request.Response;

public interface PharmacyratingsDAO extends IDAO<Pharmacyratings,Long >{

	RatingsDTO getRatingValues(Long pharmacyid);
 Response<List<Pharmacyratings>> getPharmacyratingsSerchCritera(PharmacyratingsDTO pharmacyratingsDTO) ;
	Response<List<Pharmacyratings>> getRatingsByPharmacyid(Long pharmacyId, Integer pageNum, Integer maxResults);
	
}