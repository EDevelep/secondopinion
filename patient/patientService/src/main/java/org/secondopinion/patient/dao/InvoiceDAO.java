package org.secondopinion.patient.dao;

import java.util.List;

import org.secondopinion.dao.IDAO;
import org.secondopinion.patient.dto.Invoice;
import org.secondopinion.patient.dto.InvoiceSearchDTO;
import org.secondopinion.request.Response;

public interface InvoiceDAO extends IDAO<Invoice,Long >{

	Response<List<Invoice>> getinvoiceForSerchcritiria(InvoiceSearchDTO invoicesSerchDTO);


	List<Invoice> findInvoiceByuserId(Long userId);
	
	

	List<Invoice> getByTypeAndEntityInvoiceIdAndStatus(String invoicetype, Long entityInvoiceId, String name);


	Response<List<Invoice>> getRequestPayments(Long forUserId, Integer pageNum, Integer maxResults);


	Response<List<Invoice>> getRequestPaymentDone(Long forUserId,String invoicetype, Integer pageNum, Integer maxResults);


	Invoice findInvoiceByentityInvoiceId(String fieldEntityinvoiceid, Long entityInvoiceId);


	Response<List<Invoice>> getRequestPaymentsForAll(Long forUserId, Integer pageNum, Integer maxResults);


	Response<List<Invoice>> getRequestPaymentsForAllDeliverd(Long patientId, Integer pageNum, Integer maxResult);


	
	 
}