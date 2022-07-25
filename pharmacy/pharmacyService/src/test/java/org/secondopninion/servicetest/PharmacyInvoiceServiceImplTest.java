package org.secondopninion.servicetest;

import java.util.Date;

import org.junit.Test;
import org.secondopinion.enums.InvoiceStatusEnum;
import org.secondopinion.pharmacy.dto.Invoice;
import org.secondopinion.pharmacy.service.IPharmacyInvoiceService;
import org.secondopinion.pharmacy.service.impl.PharmacyInvoiceServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;

public class PharmacyInvoiceServiceImplTest extends PharmacyInvoiceServiceImpl {

	@Autowired
	private IPharmacyInvoiceService iPharmacyInvoiceService;

	@Test
	public void saveInvoiceAfterPaymentByPatient() {
		Invoice invoice = new Invoice();
		invoice.setPaid('Y');
		invoice.setDoctorid(4L);
		invoice.setTrackingId("test");
		invoice.setInvoicestatus(InvoiceStatusEnum.SHIPPED.name());
		invoice.setDoctorname("jatin");
		invoice.setExpectedDate(new Date());
		invoice.setPatientid(4L);
		invoice.setPharmacyaddressId(1L);
		invoice.setMobileNumber("9759655764");
		invoice.setShippedBy("Harsha Pharmacy");
		iPharmacyInvoiceService.saveInvoiceShipeeingDetail(invoice);
	}

	@Test
	public void updateInvoiceAfterShipped() {

		iPharmacyInvoiceService.updateInvoiceAfterShipped(2L, InvoiceStatusEnum.SHIPPED);
	}

}
