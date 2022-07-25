package org.secondopninion.servicetest;

import org.junit.Test;
import org.secondopinion.pharmacy.service.IPharmacyuserLoginService;
import org.secondopninion.PharmacyApplactionTest;
import org.springframework.beans.factory.annotation.Autowired;

public class PharmacyuserLoginServiceImplTest  extends PharmacyApplactionTest{
	
	@Autowired
	private IPharmacyuserLoginService iPharmacyuserLoginService;

	@Test
	public void testLogin() {
		iPharmacyuserLoginService.login("ganesh@qontrack.com", "Gani@1994");	
	}
	
	
}
