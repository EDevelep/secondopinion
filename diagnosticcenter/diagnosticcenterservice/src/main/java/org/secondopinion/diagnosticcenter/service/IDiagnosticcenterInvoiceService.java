package org.secondopinion.diagnosticcenter.service;

import java.util.List;

import org.secondopinion.diagnosticcenter.dto.Invoice;
import org.secondopinion.diagnosticcenter.dto.InvoicesSerchDTO;
import org.secondopinion.request.Response;


public interface IDiagnosticcenterInvoiceService {

	
	Invoice saveInvoice(Invoice invoice);

	Response<List<Invoice>> getInvoicesByStatus(Long diagnosticcenterId, Integer pageNum, Integer maxResults);
	Response<List<Invoice>>  getinvoiceForSerchcritiria(InvoicesSerchDTO invoicesSerchDTO);

    
}
