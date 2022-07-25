package org.secondopinion.diagnosticcenter.dao; 

import java.util.List;

import org.secondopinion.dao.IDAO;
import org.secondopinion.diagnosticcenter.dto.Diagnosticcenteruser;
import org.secondopinion.request.Response;

public interface DiagnosticcenteruserDAO extends IDAO<Diagnosticcenteruser,Long >{

	
	List<Diagnosticcenteruser> readById(Long diagnosticcenteruserId);

	void updateLastLoginTime(Long diagnosticCenterUserId);

	void updateRetryCountIfLoginFailed(Long diagnosticCenterUserId, Integer retry);

	List<Diagnosticcenteruser> readByDiagnosticCenterAddressId(Long diagnosticCenterAddressId);

	Diagnosticcenteruser getByDiagnosticcenteraddressIdAndEmailId(Long diagnosticCenterId, String emailId,
			boolean withinthesamediagsnosticcenter);

	Response<List<Diagnosticcenteruser>> getAssociatedUsersOfAddress(Long diagnosticcenteraddressId, Integer pageNum,
			Integer maxResults);

	Diagnosticcenteruser findDiagnosticcenterUserByUserId(Long diagnosticCenterUserId);

}