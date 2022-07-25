package org.secondopinion.diagnosticcenter.dao; 

import java.util.List;

import org.secondopinion.dao.IDAO;
import org.secondopinion.diagnosticcenter.dto.DiagnosticcenterSearchRequest;
import org.secondopinion.diagnosticcenter.dto.Diagnosticcenteraddress;
import org.secondopinion.request.Response;

public interface DiagnosticcenteraddressDAO extends IDAO<Diagnosticcenteraddress,Long >{

	
	Response<List<Diagnosticcenteraddress>> readAllAddressesOfDiagnosticcenter(Long diagnosticcenterId, Integer pageNum,
			Integer maxResults);

	List<Diagnosticcenteraddress> getByIds(List<Long> diagnosticcenteraddressIds);

	Diagnosticcenteraddress getDiagnosticCenterBYAddressId(Long diagnosticCenterAddressId);

	List<Diagnosticcenteraddress> findDiagnosticcenterByLocation(
			DiagnosticcenterSearchRequest diagnosticcenterSearchRequest);
}