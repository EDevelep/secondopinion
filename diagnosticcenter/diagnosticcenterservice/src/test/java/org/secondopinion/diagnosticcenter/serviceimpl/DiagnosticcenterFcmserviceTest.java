package org.secondopinion.diagnosticcenter.serviceimpl;

import org.secondopinion.diagnosticcenter.dto.Diagnosticcenterfcmtoken;
import org.secondopinion.diagnosticcenter.service.DiagnosticcenterFcmservice;
import org.secondopinion.diagnosticcenter.test.DiagnosticcenterServiceApplicationTest;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.Assert.assertNotNull;

import org.junit.Test;

public class DiagnosticcenterFcmserviceTest extends DiagnosticcenterServiceApplicationTest{

	@Autowired
	private DiagnosticcenterFcmservice diagnosticcenterFcmservice;

	@Test
	public void testSavediagnosticcenterfcmtoken() {

		Diagnosticcenterfcmtoken diagnosticcenterfcmtoken = new Diagnosticcenterfcmtoken();
		diagnosticcenterfcmtoken.setDiagnosticCenterAddressId(12L);
		diagnosticcenterfcmtoken.setAndroidtoken("abc@123");
	//	diagnosticcenterfcmtoken.setBrowsertoken("def@123");
	//	diagnosticcenterfcmtoken.setIphonetoken("idef@465");
		diagnosticcenterfcmtoken.setActive('Y');
		diagnosticcenterfcmtoken.setDiagnosticCenterUserId(23L);
		diagnosticcenterFcmservice.savediagnosticcenterfcmtoken(diagnosticcenterfcmtoken);
		assertNotNull(diagnosticcenterfcmtoken);


	}




}
