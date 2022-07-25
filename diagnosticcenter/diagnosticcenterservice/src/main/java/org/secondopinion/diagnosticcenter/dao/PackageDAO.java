package org.secondopinion.diagnosticcenter.dao; 

import java.util.List;

import org.secondopinion.dao.IDAO;
import org.secondopinion.diagnosticcenter.dto.Appointment;
import org.secondopinion.diagnosticcenter.dto.Package ;
import org.secondopinion.diagnosticcenter.dto.PackageSearch;
import org.secondopinion.diagnosticcenter.dto.PackageSearchDTO;
import org.secondopinion.request.Response;

public interface PackageDAO extends IDAO<org.secondopinion.diagnosticcenter.dto.Package,Long >{

	
	Package getPackageDetails(Long diagnosticCenterId, Long packageId);

	List<Package> findPackageBydiagnosticCenterAddressId(Long diagnosticCenterAddressId);
	List<Package> searchPackage(PackageSearchDTO packageSearch);

	Response<List<Package>> searchPackageitem(PackageSearch packageSearch);

	void deletePackage(Package packaged);

	
}