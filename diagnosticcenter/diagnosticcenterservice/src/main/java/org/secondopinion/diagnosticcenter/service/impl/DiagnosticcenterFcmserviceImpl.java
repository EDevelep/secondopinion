package org.secondopinion.diagnosticcenter.service.impl;

import java.util.Objects;
import javax.transaction.Transactional;
import org.secondopinion.diagnosticcenter.dao.DiagnosticcenterfcmtokenDAO;
import org.secondopinion.diagnosticcenter.dto.Diagnosticcenterfcmtoken;
import org.secondopinion.diagnosticcenter.service.DiagnosticcenterFcmservice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DiagnosticcenterFcmserviceImpl implements DiagnosticcenterFcmservice {

	
	@Autowired
	private DiagnosticcenterfcmtokenDAO diagnosticcenterfcmtokenDAO;

	@Override
	@Transactional
	public Diagnosticcenterfcmtoken savediagnosticcenterfcmtoken(Diagnosticcenterfcmtoken diagnosticcenterfcmtoken) {
		Diagnosticcenterfcmtoken dbdiagnosticcenterfcmtoken = diagnosticcenterfcmtokenDAO.getByDiagnosticcenterAddressAndUserId(diagnosticcenterfcmtoken.getDiagnosticCenterAddressId(), diagnosticcenterfcmtoken.getDiagnosticCenterUserId());
		if (Objects.isNull(dbdiagnosticcenterfcmtoken)) {
			dbdiagnosticcenterfcmtoken = Diagnosticcenterfcmtoken.buildDiagnosticFCMTokenObject(diagnosticcenterfcmtoken,
					new Diagnosticcenterfcmtoken());
		}
		else {
			dbdiagnosticcenterfcmtoken = Diagnosticcenterfcmtoken.buildDiagnosticFCMTokenObject(diagnosticcenterfcmtoken,
					dbdiagnosticcenterfcmtoken);
		}
		
		diagnosticcenterfcmtokenDAO.save(dbdiagnosticcenterfcmtoken);
		return dbdiagnosticcenterfcmtoken;

	}

}
