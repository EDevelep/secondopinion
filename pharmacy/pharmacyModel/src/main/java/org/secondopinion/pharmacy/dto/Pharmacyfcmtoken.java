package org.secondopinion.pharmacy.dto;


import java.util.Objects;

import javax.persistence.Entity;

import javax.persistence.Table;

import org.apache.commons.lang3.StringUtils;
import org.secondopinion.pharmacy.domain.BasePharmacyfcmtoken;

@Entity
@Table(name = "pharmacyfcmtoken")
public class Pharmacyfcmtoken extends BasePharmacyfcmtoken {

	public static Pharmacyfcmtoken biuldForupadtepharmacyfcmtoken(Pharmacyfcmtoken pharmacyfcmtoken,Pharmacyfcmtoken dbPharmacyfcmtoken) {
		dbPharmacyfcmtoken.setAndroidtoken(!StringUtils.isEmpty( pharmacyfcmtoken.getAndroidtoken()) ? pharmacyfcmtoken.getAndroidtoken() : dbPharmacyfcmtoken.getAndroidtoken());
		dbPharmacyfcmtoken.setBrowsertoken(!StringUtils.isEmpty( pharmacyfcmtoken.getBrowsertoken()) ? pharmacyfcmtoken.getBrowsertoken() : dbPharmacyfcmtoken.getBrowsertoken());
		dbPharmacyfcmtoken.setIphonetoken(!StringUtils.isEmpty( pharmacyfcmtoken.getIphonetoken()) ? pharmacyfcmtoken.getIphonetoken() : dbPharmacyfcmtoken.getIphonetoken());
		if(Objects.isNull(dbPharmacyfcmtoken.getPharmacyFCMtokenId())) {
			dbPharmacyfcmtoken.setPharmacyaddressId(pharmacyfcmtoken.getPharmacyaddressId());
			dbPharmacyfcmtoken.setPharmacyuserId(pharmacyfcmtoken.getPharmacyuserId());
		}
		
		return dbPharmacyfcmtoken;
	}
	
	
}