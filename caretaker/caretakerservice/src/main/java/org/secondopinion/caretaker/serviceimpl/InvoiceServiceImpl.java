package org.secondopinion.caretaker.serviceimpl;

import java.util.List;

import org.secondopinion.caretaker.dao.InvoiceDAO;
import org.secondopinion.caretaker.dto.Invoice;
import org.secondopinion.caretaker.dto.InvoicesSerchDTO;
import org.secondopinion.caretaker.service.IInvoiceService;
import org.secondopinion.request.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class InvoiceServiceImpl implements IInvoiceService {

	@Autowired
	private InvoiceDAO invoiceDAO;

	@Override
	@Transactional
	public Response<List<Invoice>> getinvoiceForSerchcritiria(InvoicesSerchDTO invoicesSerchDTO) {
		Response<List<Invoice>> listinvoice = invoiceDAO.getinvoiceForSerchcritiria(invoicesSerchDTO);
		return listinvoice;
	}

	@Override
	@Transactional
	public Invoice saveInvoice(Invoice invoice) {
		invoiceDAO.save(invoice);
		return invoice;

	}

}
