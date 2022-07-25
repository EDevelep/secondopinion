package org.secondopinion.caretaker.dto;

import javax.persistence.Entity;

import javax.persistence.Table;

import org.apache.commons.lang3.StringUtils;
import org.secondopinioncaretaker.domain.BaseCaretakerfcmtoken;


@Entity
@Table(name = "caretakerfcmtoken")
public class Caretakerfcmtoken extends BaseCaretakerfcmtoken {

	public static Caretakerfcmtoken builddoctorfcmtokenObject(Caretakerfcmtoken caretakerfcmtoken,Caretakerfcmtoken dbcaretakerfcmtoken) {
		dbcaretakerfcmtoken.setAndroidtoken(!StringUtils.isEmpty( caretakerfcmtoken.getAndroidtoken()) ? caretakerfcmtoken.getAndroidtoken() : dbcaretakerfcmtoken.getAndroidtoken());
		dbcaretakerfcmtoken.setBrowsertoken(!StringUtils.isEmpty( caretakerfcmtoken.getBrowsertoken()) ? caretakerfcmtoken.getBrowsertoken() : dbcaretakerfcmtoken.getBrowsertoken());
		dbcaretakerfcmtoken.setIphonetoken(!StringUtils.isEmpty( caretakerfcmtoken.getIphonetoken()) ? caretakerfcmtoken.getIphonetoken() : dbcaretakerfcmtoken.getIphonetoken());
		return dbcaretakerfcmtoken;
	}
}