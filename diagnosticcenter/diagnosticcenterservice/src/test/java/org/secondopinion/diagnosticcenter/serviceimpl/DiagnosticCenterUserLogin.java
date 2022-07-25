package org.secondopinion.diagnosticcenter.serviceimpl;

import static org.junit.Assert.assertNotNull;

import org.junit.Test;
import org.secondopinion.diagnosticcenter.dto.Diagnosticcenteruser;
import org.secondopinion.diagnosticcenter.service.IDiagnosticCenterUserService;
import org.secondopinion.diagnosticcenter.test.DiagnosticcenterServiceApplicationTest;
import org.springframework.beans.factory.annotation.Autowired;

public class DiagnosticCenterUserLogin extends DiagnosticcenterServiceApplicationTest {
	
	
	@Autowired
	private IDiagnosticCenterUserService diagnosticCenterUserService;
	
	@Test
	public void logindiagnosticCenter() {
		String userName="ganesh.buddi1994@gmail.com";
		String  password="Gani@1994";
		Diagnosticcenteruser diagnosticcenteruser=	diagnosticCenterUserService.login(userName, password);
		assertNotNull(diagnosticcenteruser);
	}
}
