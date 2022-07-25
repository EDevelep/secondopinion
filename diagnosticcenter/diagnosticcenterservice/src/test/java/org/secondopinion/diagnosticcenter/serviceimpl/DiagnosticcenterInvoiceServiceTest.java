package org.secondopinion.diagnosticcenter.serviceimpl;

import static org.junit.Assert.assertNotNull;

import java.util.List;

import org.junit.Test;
import org.secondopinion.diagnosticcenter.dto.Invoice;
import org.secondopinion.diagnosticcenter.service.IDiagnosticcenterInvoiceService;
import org.secondopinion.diagnosticcenter.test.DiagnosticcenterServiceApplicationTest;
import org.secondopinion.request.Response;
import org.springframework.beans.factory.annotation.Autowired;

public class DiagnosticcenterInvoiceServiceTest extends DiagnosticcenterServiceApplicationTest {
	
	
	@Autowired
	private IDiagnosticcenterInvoiceService iDiagnosticcenterInvoiceService;

	@Test
	public void getInvoicesByStatus() {
		Long diagnosticcenterId=20L;
		Integer pageNum=1;
		Integer maxResults=2;
		Response<List<Invoice>> list	=iDiagnosticcenterInvoiceService.getInvoicesByStatus(diagnosticcenterId, pageNum, maxResults);
		assertNotNull(list);
	}
}
