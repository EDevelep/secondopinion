package org.secondopinion.pharmacy.dao; 

import java.util.List;

import org.secondopinion.dao.IDAO;
import org.secondopinion.enums.InvoiceStatusEnum;
import org.secondopinion.pharmacy.dto.Invoice;
import org.secondopinion.pharmacy.dto.InvoicesSerchDTO;
import org.secondopinion.request.Response;

public interface InvoiceDAO extends IDAO<Invoice,Long >{

	 List<Invoice> readByInvoiceForFillRequest(Long prescriptionFillRequestId);

	Invoice readByInvoiceId(Long invoiceId);

	Response<List<Invoice>> getInvoicesByStatus(Long pharmacyId, InvoiceStatusEnum invoiceStatusEnum, Integer pageNum,
			Integer maxResults);

	List<Invoice> findShipeeingDetail(Long invoiceId, Long patientId);

	Response<List<Invoice>> getinvoiceForSerchcritiria(InvoicesSerchDTO invoicesSerchDTO);

	Double findTotelRevenu(Long pharmacyaddressId);
}