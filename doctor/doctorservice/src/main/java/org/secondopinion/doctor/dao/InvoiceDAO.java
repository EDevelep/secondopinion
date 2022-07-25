package org.secondopinion.doctor.dao; 

import java.util.List;

import org.secondopinion.dao.IDAO;
import org.secondopinion.doctor.dto.Invoice;
import org.secondopinion.doctor.dto.InvoicesSerchDTO;
import org.secondopinion.request.Response;

public interface InvoiceDAO extends IDAO<Invoice,Long >{

	Response<List<Invoice>> getinvoiceForSerchcritiria(InvoicesSerchDTO invoicesSerchDTO);
	Double getTotalRevenue(Long doctorid, String invoiceStatus);
}