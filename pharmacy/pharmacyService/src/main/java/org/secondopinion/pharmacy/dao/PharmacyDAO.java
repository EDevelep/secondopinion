package org.secondopinion.pharmacy.dao; 

import java.util.List;

import org.secondopinion.common.dto.RatingsDTO;
import org.secondopinion.dao.IDAO;
import org.secondopinion.pharmacy.dto.Pharmacy;
import org.secondopinion.pharmacy.dto.PharmacySearchRequest;
import org.secondopinion.request.Response;


public interface PharmacyDAO extends IDAO<Pharmacy,Long >{

	Pharmacy readByPharmacyId(Long pharmacyId);
	
	List<Pharmacy> readByPharmacyByaddrssId(List<Long>  pharmacyaddressIds);
	Response<List<Pharmacy>> getAllPharmacyBySearchCritieria(PharmacySearchRequest pharmacySearchRequest);
	Pharmacy readByEmailId(String emailId);

	Pharmacy readByLicenceNumber(String licenseNumber);

	void updatepharmacy(Long pharmacyid, RatingsDTO values);

	Pharmacy readByphonenumber(String phone);

	List<Pharmacy> getAssoctedPharmcy(Long pharmacyadminId);

}