package org.secondopinion.pharmacy.dao; 

import java.util.List;

import org.secondopinion.dao.IDAO;
import org.secondopinion.pharmacy.dto.Pharmacyfcmtoken;

public interface PharmacyfcmtokenDAO extends IDAO<Pharmacyfcmtoken,Long >{

	List<Pharmacyfcmtoken> getByPharmacyAddressId(Long pharmacyAddressId);

	Pharmacyfcmtoken getByPharmacyAddressAnduserId(Long pharmacyuserId, Long pharmacyAddressId);

}