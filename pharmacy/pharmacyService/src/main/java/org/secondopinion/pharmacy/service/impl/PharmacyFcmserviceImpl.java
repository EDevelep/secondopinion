package org.secondopinion.pharmacy.service.impl;

import java.util.Objects;

import javax.transaction.Transactional;

import org.secondopinion.pharmacy.dao.PharmacyfcmtokenDAO;
import org.secondopinion.pharmacy.dto.Pharmacyfcmtoken;
import org.secondopinion.pharmacy.service.PharmacyFcmservice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PharmacyFcmserviceImpl implements PharmacyFcmservice {

	@Autowired
	private PharmacyfcmtokenDAO pharmacyfcmtokenDAO;

	@Override
	@Transactional
	public Pharmacyfcmtoken savepharmacyfcmtoken(Pharmacyfcmtoken pharmacyfcmtoken) {
		Pharmacyfcmtoken dbpharmacyfcmtoken = pharmacyfcmtokenDAO.getByPharmacyAddressAnduserId(pharmacyfcmtoken.getPharmacyuserId(), 
				pharmacyfcmtoken.getPharmacyaddressId());
		if (Objects.isNull(dbpharmacyfcmtoken)) {
			dbpharmacyfcmtoken = Pharmacyfcmtoken.biuldForupadtepharmacyfcmtoken(pharmacyfcmtoken,
					new Pharmacyfcmtoken());
		} else {
			dbpharmacyfcmtoken = Pharmacyfcmtoken.biuldForupadtepharmacyfcmtoken(pharmacyfcmtoken,dbpharmacyfcmtoken);
		}
		
		pharmacyfcmtokenDAO.save(dbpharmacyfcmtoken);
		return dbpharmacyfcmtoken;

	}

}
