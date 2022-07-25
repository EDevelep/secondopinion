package org.secondopinion.doctor.dto;

import javax.persistence.Entity;

import javax.persistence.Table;

import org.apache.commons.lang3.StringUtils;
import org.secondopinion.doctor.domain.BaseDoctorfcmtoken;

@Entity
@Table(name = "doctorfcmtoken")
public class Doctorfcmtoken extends BaseDoctorfcmtoken {

	public static Doctorfcmtoken builddoctorfcmtokenObject(Doctorfcmtoken doctorfcmtoken,Doctorfcmtoken dbDoctorfcmtoken) {
		dbDoctorfcmtoken.setAndroidtoken(!StringUtils.isEmpty( doctorfcmtoken.getAndroidtoken()) ? doctorfcmtoken.getAndroidtoken() : dbDoctorfcmtoken.getAndroidtoken());
		dbDoctorfcmtoken.setBrowsertoken(!StringUtils.isEmpty( doctorfcmtoken.getBrowsertoken()) ? doctorfcmtoken.getBrowsertoken() : dbDoctorfcmtoken.getBrowsertoken());
		dbDoctorfcmtoken.setIphonetoken(!StringUtils.isEmpty( doctorfcmtoken.getIphonetoken()) ? doctorfcmtoken.getIphonetoken() : dbDoctorfcmtoken.getIphonetoken());
		return dbDoctorfcmtoken;
	}
}