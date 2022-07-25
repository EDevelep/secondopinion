package org.secondopinion.diagnosticcenter.dao; 

import java.util.List;

import org.secondopinion.dao.IDAO;
import org.secondopinion.diagnosticcenter.dto.Diagnosticcenterfcmtoken;


public interface DiagnosticcenterfcmtokenDAO extends IDAO<Diagnosticcenterfcmtoken,Long >{

	
	Diagnosticcenterfcmtoken getByDiagnosticcenterAddressAndUserId(Long diagnosticcenteraddressId, Long diagnosticcenteruserId);

	List<Diagnosticcenterfcmtoken> getByDiagnosticcenteraddressId(Long diagnosticcenteraddressId);

}