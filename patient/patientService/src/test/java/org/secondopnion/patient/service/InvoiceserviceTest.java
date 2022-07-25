package org.secondopnion.patient.service;

import static org.junit.Assert.assertNotNull;

import java.sql.Array;
import java.util.Arrays;
import java.util.Date;

import org.junit.Test;
import org.secondopinion.patient.dto.Invoice;
import org.secondopinion.patient.dto.Invoice.ViewInvoiceEnum;
import org.secondopinion.patient.dto.InvoiceSearchDTO;
import org.secondopinion.patient.dto.ViewInvoiceEnumRequset;
import org.secondopinion.patient.service.IPatientInvoice;
import org.secondopnion.patient.PatientApplicationTest;
import org.springframework.beans.factory.annotation.Autowired;

public class InvoiceserviceTest extends PatientApplicationTest {

	@Autowired
	private IPatientInvoice patientInvoiceImpl;

	@Test
	public void savePatientInvoice() {
		Invoice invoice = new Invoice();
		// invoice.setInvoiceid(40L);
		invoice.setAmount(30.00);
		invoice.setCardnumber("424244272722");
		invoice.setTransactiontype("test");
		invoice.setInvoiceentityname("vishnu");
		invoice.setInvoiceentityreferenceid(123L);
		invoice.setPaid('Y');
		invoice.setInvoiceentityid(234L);
		invoice.setEntityInvoiceId(124L);
		invoice.setInvoicereferenceid(133L);
		invoice.setInvoicestatus("Paid");
		invoice.setPaidOn(new Date());
		invoice.setInvoicetype("apptemtn");
		invoice.setPatientid(141L);
		invoice.setPatientname("jatin");
		patientInvoiceImpl.savePatientInvoice(invoice);
		assertNotNull(invoice);
	}

	@Test
	public void getAllInvoice() {
		Long invoiceId = 26L;
		patientInvoiceImpl.getAllInvoiceForUser(invoiceId);
		assertNotNull(invoiceId);
	}

	@Test
	public void getInvoiceSerchCritera() {
		InvoiceSearchDTO invoiceSearchDTO = new InvoiceSearchDTO();
		invoiceSearchDTO.setPatientid(141L);
		patientInvoiceImpl.getinvoiceForSerchcritiria(invoiceSearchDTO);
	}

	@Test
	public void getRequestPaymentsForAll() {
		ViewInvoiceEnumRequset viewInvoiceEnumRequset = new ViewInvoiceEnumRequset();
		ViewInvoiceEnum viewInvoiceEnum = ViewInvoiceEnum.PAYMENT_DONE;
		viewInvoiceEnumRequset.setPatientId(50L);
		//viewInvoiceEnumRequset.setViewInvoiceEnum(Arrays.asList(viewInvoiceEnum));
		patientInvoiceImpl.getRequestPaymentsForAll(viewInvoiceEnumRequset);
	}

}
