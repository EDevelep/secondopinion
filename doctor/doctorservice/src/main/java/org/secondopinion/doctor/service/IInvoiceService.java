package org.secondopinion.doctor.service;

import java.util.List;

import org.secondopinion.doctor.dto.Invoice;
import org.secondopinion.doctor.dto.InvoicesSerchDTO;
import org.secondopinion.request.Response;

public interface IInvoiceService {

	Invoice saveInvoice(Invoice invoice);

	Response<List<Invoice>>  getinvoiceForSerchcritiria(InvoicesSerchDTO invoicesSerchDTO);

}
