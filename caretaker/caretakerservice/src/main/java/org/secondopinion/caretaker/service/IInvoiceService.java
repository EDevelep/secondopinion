package org.secondopinion.caretaker.service;

import java.util.List;

import org.secondopinion.caretaker.dto.Invoice;
import org.secondopinion.caretaker.dto.InvoicesSerchDTO;
import org.secondopinion.request.Response;

public interface IInvoiceService {

	Invoice saveInvoice(Invoice invoice);

	Response<List<Invoice>>  getinvoiceForSerchcritiria(InvoicesSerchDTO invoicesSerchDTO);

}
