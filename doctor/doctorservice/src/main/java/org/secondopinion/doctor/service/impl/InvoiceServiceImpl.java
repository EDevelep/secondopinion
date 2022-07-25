package org.secondopinion.doctor.service.impl;

import java.util.List;

import org.secondopinion.doctor.dao.AppointmentDAO;
import org.secondopinion.doctor.dao.InvoiceDAO;
import org.secondopinion.doctor.dto.Appointment;
import org.secondopinion.doctor.dto.Invoice;
import org.secondopinion.doctor.dto.InvoicesSerchDTO;
import org.secondopinion.doctor.service.IInvoiceService;
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
