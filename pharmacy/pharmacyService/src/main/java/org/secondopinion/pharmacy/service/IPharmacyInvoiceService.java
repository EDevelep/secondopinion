package org.secondopinion.pharmacy.service;

import java.util.List;


import org.secondopinion.enums.InvoiceStatusEnum;
import org.secondopinion.pharmacy.dto.Invoice;
import org.secondopinion.pharmacy.dto.InvoicesSerchDTO;
import org.secondopinion.request.Response;

public interface IPharmacyInvoiceService {

	void updateInvoiceAfterPaymentByPatient(Invoice invoice);
	
	
	void updateInvoiceAfterShipped(Long invoiceId,InvoiceStatusEnum invoiceStatusEnum);

	void saveInvoiceShipeeingDetail(Invoice invoice);

	void deletePharmacyInvoice(Invoice invoice);

	List<Invoice> getShipeeingDetail(Long invoiceId, Long patientId);

	void updateInvoiceByPharmacy(Long invoiceId, InvoiceStatusEnum invoiceStatusEnum);
	Response<List<Invoice>>  getinvoiceForSerchcritiria(InvoicesSerchDTO invoicesSerchDTO);
	Response<List<Invoice>> getInvoicesByStatus(Long pharmacyId, InvoiceStatusEnum invoiceStatusEnum, Integer pageNum,
			Integer maxResults);

}
