package org.secondopinion.doctor.service;

import java.util.Date;

import org.junit.Test;
import org.secondopinion.doctor.DoctorServiceApplicationTests;
import org.secondopinion.doctor.dto.Invoice;
import org.secondopinion.doctor.dto.InvoicesSerchDTO;
import org.secondopinion.enums.InvoiceStatusEnum;
import org.springframework.beans.factory.annotation.Autowired;

public class InvoiceServiceTestImpl extends DoctorServiceApplicationTests {
	@Autowired
	private IInvoiceService invoiceService;
	
	
	@Test
	public void saveInvoice() {
		Invoice invoice=new Invoice();
		
		invoice.setAmount(100.00);
		invoice.setCardnumber("124578999");
		invoice.setDoctorid(40L);
		invoice.setPaidOn(new Date());
		invoice.setPatientappointmentid(23L);
		invoice.setDoctorname("vishnujatinsuddher");
		invoice.setPatientname("jatin");
		invoice.setPatientid(141L);
		invoice.setDoctorappointmentid(32L);
		invoice.setPaid('y');
		invoice.setInvoicestatus(InvoiceStatusEnum.PAYMENT_DONE.name());
		invoice.setPaymentReferenceId("chit1356");
		invoiceService.saveInvoice(invoice);
	}
	
	
	@Test
	public void getinvoiceForSerchcritiria() {
		InvoicesSerchDTO invoicesSerchDTO =new InvoicesSerchDTO();
		invoicesSerchDTO.setAmount(100.00);
		invoicesSerchDTO.setPatientappointmentid(23L);
		invoicesSerchDTO.setDoctorappointmentid(32L);
		invoicesSerchDTO.setDoctorid(40L);
		invoicesSerchDTO.setPatientid(141L);
		invoicesSerchDTO.setPatientname("jatin");
		invoicesSerchDTO.setDoctorname("vishnu");
		invoicesSerchDTO.setPaymentReferenceId("chit1356");
		invoiceService.getinvoiceForSerchcritiria(invoicesSerchDTO);
	}
	
	
}
