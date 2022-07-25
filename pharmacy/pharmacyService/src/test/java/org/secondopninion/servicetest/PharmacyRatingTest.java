package org.secondopninion.servicetest;

import org.junit.Test;
import org.secondopinion.pharmacy.dto.Pharmacyratings;
import org.secondopinion.pharmacy.dto.PharmacyratingsDTO;
import org.secondopinion.pharmacy.service.IRatingsPharmacy;
import org.secondopninion.PharmacyApplactionTest;
import org.springframework.beans.factory.annotation.Autowired;

public class PharmacyRatingTest extends PharmacyApplactionTest {
	
	@Autowired
	private IRatingsPharmacy iRatingsPharmacy;
	
	@Test
	public void savePharmacyRating() {
		Pharmacyratings paharmacyratings=new Pharmacyratings();
		paharmacyratings.setPatientid(20L);
		paharmacyratings.setPharmacyaddressId(22L);
		paharmacyratings.setRating(1.0d);
		paharmacyratings.setFeedback("test feedback");
		iRatingsPharmacy.saveRatings(paharmacyratings);
	}
	
	@Test
	public void savePharmacyRating1() {
		PharmacyratingsDTO pharmacyratingsDTO=new PharmacyratingsDTO();
		pharmacyratingsDTO.setPatientid(22L);
		pharmacyratingsDTO.setMaxresult(1);
		pharmacyratingsDTO.setPharmacyid(22L);
		pharmacyratingsDTO.setPagenumber(1);
		iRatingsPharmacy.getRatingsByPharmacySerchCritera(pharmacyratingsDTO);
	}
	
	

}
