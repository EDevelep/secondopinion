package org.secondopinion.diagnosticcenter.service.impl;

import java.util.List;

import org.secondopinion.diagnosticcenter.dao.InvoiceDAO;
import org.secondopinion.diagnosticcenter.dto.Invoice;
import org.secondopinion.diagnosticcenter.dto.InvoicesSerchDTO;
import org.secondopinion.diagnosticcenter.service.IDiagnosticcenterInvoiceService;
import org.secondopinion.request.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class DiagnosticcenterInvoiceServiceImpl implements IDiagnosticcenterInvoiceService {
	
	
	@Autowired
	private InvoiceDAO invoiceDAO;
	
	@Override
	@Transactional
	public Response<List<Invoice>> getInvoicesByStatus(Long diagnosticcenterId,
			Integer pageNum, Integer maxResults) {
		return invoiceDAO.getInvoicesByStatus(diagnosticcenterId, pageNum, maxResults);
	}

	@Override
	public Invoice saveInvoice(Invoice invoice) {
		invoice.setCGST(0d);
		invoice.setSGST(0d);
		invoice.setInvoiceDate(invoice.getPaidOn());
		invoice.setTotalInvoiceAmount(Double.sum(invoice.getAmount(), Double.sum(invoice.getCGST(), invoice.getSGST())));
		invoiceDAO.save(invoice);
		return invoice;
	}

	@Override
	@Transactional
	public Response<List<Invoice>> getinvoiceForSerchcritiria(InvoicesSerchDTO invoicesSerchDTO) {
		Response<List<Invoice>> listinvoice = invoiceDAO.getinvoiceForSerchcritiria(invoicesSerchDTO);
		return listinvoice;
	}


}
