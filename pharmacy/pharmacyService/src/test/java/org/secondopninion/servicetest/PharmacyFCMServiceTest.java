package org.secondopninion.servicetest;

import org.junit.Test;
import org.secondopinion.pharmacy.dto.Pharmacyfcmtoken;
import org.secondopinion.pharmacy.service.PharmacyFcmservice;
import org.secondopninion.PharmacyApplactionTest;
import org.springframework.beans.factory.annotation.Autowired;

public class PharmacyFCMServiceTest  extends PharmacyApplactionTest {

	@Autowired
	private PharmacyFcmservice pharmacyFCMService;
	
	@Test
	public void testSavepharmacyfcmtoken() {
		Pharmacyfcmtoken pharmacyfcmtoken = new Pharmacyfcmtoken();
		pharmacyfcmtoken.setPharmacyaddressId(16L);
		pharmacyfcmtoken.setPharmacyuserId(20L);
		pharmacyfcmtoken.setBrowsertoken("cKdLrnknXwjnpZqj_kpn3P:APA91bGjW5XHZrhxZYJVnNP1sJao2NLvoOFoUtRYEdlN6mrFSQzsongxi6wYcwPLxYhIXkTpH_bOe4Wlpp3RZcD9nYshDRjIaZdzsmw_j7_lxI6PRisHShF_4JE4egxTZF9HmIKr8Y");
		pharmacyFCMService.savepharmacyfcmtoken(pharmacyfcmtoken);
	}
}
