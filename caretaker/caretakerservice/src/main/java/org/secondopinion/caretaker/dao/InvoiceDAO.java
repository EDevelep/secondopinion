package org.secondopinion.caretaker.dao; 

import java.util.List;

import org.secondopinion.caretaker.dto.Invoice;
import org.secondopinion.caretaker.dto.InvoicesSerchDTO;
import org.secondopinion.dao.IDAO;

import org.secondopinion.request.Response;

public interface InvoiceDAO extends IDAO<Invoice,Long >{

	Response<List<Invoice>> getinvoiceForSerchcritiria(InvoicesSerchDTO invoicesSerchDTO);
	Double getTotalRevenue(Long doctorid, String invoiceStatus);
}