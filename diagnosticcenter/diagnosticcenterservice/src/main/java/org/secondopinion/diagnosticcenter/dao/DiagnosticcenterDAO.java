package org.secondopinion.diagnosticcenter.dao; 

import java.util.List;
import java.util.Map;

import org.secondopinion.common.dto.RatingsDTO;
import org.secondopinion.dao.IDAO;
import org.secondopinion.diagnosticcenter.dto.Diagnosticcenter;
import org.secondopinion.diagnosticcenter.dto.DiagnosticcenterSearchRequest;
import org.secondopinion.diagnosticcenter.dto.Diagnosticcenteraddress;

public interface DiagnosticcenterDAO extends IDAO<Diagnosticcenter,Long >{

	
	Diagnosticcenter readByEmailId(String emailId);

	Diagnosticcenter readByDiagnosticcenterId(Long diagnosticcenterId);

	Diagnosticcenter readByLicenceNumber(String licenseNumber);

	void updatediagnosticcenterratings(Long diagnosticcenterid, RatingsDTO values);

	List<Diagnosticcenteraddress> getAllDiagnosticcenterBySearchCritieria(
			DiagnosticcenterSearchRequest diagnosticcenterSearchRequest);

	Map<String, Object> getMyReports(Long diagnosticCenterAddressid);

	List<Diagnosticcenter> getAllDiagnosticcenterBySearchCritieria(List<Long> diagnosticcenterId);

	List<Diagnosticcenter> getAssoctedDiagnosticCenter(Long diagnosticCenteradminId);


}