package org.secondopinion.patient.service;

import java.util.List;
import java.util.Map;

import org.secondopinion.patient.dto.Invoice;
import org.secondopinion.patient.dto.Invoice.ViewInvoiceEnum;
import org.secondopinion.patient.dto.InvoiceSearchDTO;
import org.secondopinion.patient.dto.ViewInvoiceEnumRequset;
import org.secondopinion.request.Response;

public interface IPatientInvoice {

	Response<List<Invoice>> getinvoiceForSerchcritiria(InvoiceSearchDTO invoicesSerchDTO);

	void savePatientInvoice(Invoice invoice);

	Map<ViewInvoiceEnum, Response<List<Invoice>>> getRequestPaymentsForAll(ViewInvoiceEnumRequset viewInvoiceEnumRequset);

	Invoice findInvoiceByuserId(Long userId, Long invoiceId);

	List<Invoice> getAllInvoiceForUser(Long invoiceId);

	void updateInvoiceForPaymentByEntityInvoiceIdWithStatus(Invoice invoice);

	Response<List<Invoice>> getRequestPayments(Long forUserId, Integer pageNum, Integer maxResults);

	Response<List<Invoice>> getRequestPaymentDone(Long forUserId, String invoicetype, Integer pageNum,
			Integer maxResults);

	void updateInvoiceByEntityInvoiceIdWithStatus(Invoice invoice);

	void updateInvoiceShippingByEntityInvoiceIdWithStatus(Invoice invoice);

}
