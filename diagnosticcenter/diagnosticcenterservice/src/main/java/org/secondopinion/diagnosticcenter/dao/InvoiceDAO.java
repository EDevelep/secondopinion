package org.secondopinion.diagnosticcenter.dao; 

import java.util.List;

import org.secondopinion.dao.IDAO;
import org.secondopinion.diagnosticcenter.dto.Invoice;
import org.secondopinion.diagnosticcenter.dto.InvoicesSerchDTO;
import org.secondopinion.request.Response;

public interface InvoiceDAO extends IDAO<Invoice,Long >{

	

	Response<List<Invoice>> getInvoicesByStatus(Long diagnosticcenterId, Integer pageNum,
			Integer maxResults);

	Double getTotalRevenue(Long diagnosticCenterAddressid, String name);

	Response<List<Invoice>> getinvoiceForSerchcritiria(InvoicesSerchDTO invoicesSerchDTO);



}