package org.secondopinion.diagnosticcenter.dto;

import java.util.Objects;

import javax.persistence.Entity;

import javax.persistence.Table;

import org.apache.commons.lang3.StringUtils;
import org.secondopinion.diagnosticcenter.domain.BaseDiagnosticcenterfcmtoken;

@Entity
@Table(name = "diagnosticcenterfcmtoken")
public class Diagnosticcenterfcmtoken extends BaseDiagnosticcenterfcmtoken {

	public static Diagnosticcenterfcmtoken buildDiagnosticFCMTokenObject(Diagnosticcenterfcmtoken diagnosticcenterfcmtoken, 
			Diagnosticcenterfcmtoken dbDiagnosticcenterfcmtoken) {
		if(Objects.isNull(dbDiagnosticcenterfcmtoken.getDiagnosticcenterfcmtokenId())) {
			dbDiagnosticcenterfcmtoken.setDiagnosticCenterAddressId(Objects.nonNull( diagnosticcenterfcmtoken.getDiagnosticCenterAddressId()) ? diagnosticcenterfcmtoken.getDiagnosticCenterAddressId() : dbDiagnosticcenterfcmtoken.getDiagnosticCenterAddressId());
			dbDiagnosticcenterfcmtoken.setDiagnosticCenterUserId(Objects.nonNull( diagnosticcenterfcmtoken.getDiagnosticCenterUserId()) ? diagnosticcenterfcmtoken.getDiagnosticCenterUserId() : dbDiagnosticcenterfcmtoken.getDiagnosticCenterUserId());
		}
		dbDiagnosticcenterfcmtoken.setAndroidtoken(!StringUtils.isEmpty( diagnosticcenterfcmtoken.getAndroidtoken()) ? diagnosticcenterfcmtoken.getAndroidtoken() : dbDiagnosticcenterfcmtoken.getAndroidtoken());
		dbDiagnosticcenterfcmtoken.setBrowsertoken(!StringUtils.isEmpty( diagnosticcenterfcmtoken.getBrowsertoken()) ? diagnosticcenterfcmtoken.getBrowsertoken() : dbDiagnosticcenterfcmtoken.getBrowsertoken());
		dbDiagnosticcenterfcmtoken.setIphonetoken(!StringUtils.isEmpty( diagnosticcenterfcmtoken.getIphonetoken()) ? diagnosticcenterfcmtoken.getIphonetoken() : dbDiagnosticcenterfcmtoken.getIphonetoken());
		return dbDiagnosticcenterfcmtoken;
	}

}