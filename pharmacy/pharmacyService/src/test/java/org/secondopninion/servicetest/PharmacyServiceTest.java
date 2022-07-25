package org.secondopninion.servicetest;


import org.junit.Test;

import org.secondopinion.pharmacy.dto.PharmacySearchRequest;
import org.secondopinion.pharmacy.dto.Pharmacyaddress;
import org.secondopinion.pharmacy.service.IPharmacyService;
import org.secondopninion.PharmacyApplactionTest;
import org.springframework.beans.factory.annotation.Autowired;

public class PharmacyServiceTest extends PharmacyApplactionTest {
	@Autowired
	private IPharmacyService pharmacyService;

	

	@Test
	public void saveAddress() {
		Pharmacyaddress address = new Pharmacyaddress();
		address.setAddress1("asdsdf");
		address.setAddress2("sdgfd");
		address.setCity("hyd");
		address.setCountry("india");
		address.setPharmacyId(9L);
		address.setState("ts");
		address.setZip("506349");
		address.setIsPrimary('Y');
		pharmacyService.saveAddressesOfPharmacy(address);
	}
	
	
	/*
	 * @Test public void getAllPharmacyBySearchCritieria() { PharmacySearchRequest
	 * pharmacySearchRequest=new PharmacySearchRequest();
	 * pharmacySearchRequest.setPharmacyId(3L);
	 * pharmacySearchRequest.setAddressId(3L);
	 * pharmacyService.getAllPharmacyBySearchCritieria(pharmacySearchRequest); }
	 */
}
