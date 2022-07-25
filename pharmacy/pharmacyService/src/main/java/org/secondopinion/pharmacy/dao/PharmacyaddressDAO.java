package org.secondopinion.pharmacy.dao; 

import java.util.List;

import org.secondopinion.dao.IDAO;
import org.secondopinion.pharmacy.dto.Pharmacyaddress;
import org.secondopinion.request.Response;

public interface PharmacyaddressDAO  extends IDAO<Pharmacyaddress,Long >{

	Response<List<Pharmacyaddress>> readAllAddressesOfPharmacy(Long pharmacyId, Integer pageNum, Integer maxResults);

	List<Pharmacyaddress> readByAddresseIdAndPharmacyIdAndActve(Long pharmacyId, Long addressId, Character active);

	void deleteAddressesOfPharmacy(Pharmacyaddress address);

	List<Pharmacyaddress> getByIds(List<Long> pharmacyaddressIds);

	List<Pharmacyaddress> getBypharmacyIds(List<Long> pharmacyId);
}