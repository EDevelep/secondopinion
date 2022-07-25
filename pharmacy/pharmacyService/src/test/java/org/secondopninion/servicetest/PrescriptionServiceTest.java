package org.secondopninion.servicetest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.junit.Test;
import static org.junit.Assert.assertNotNull;
import org.secondopinion.pharmacy.dto.FillPrescriptionRequestDTO;
import org.secondopinion.pharmacy.dto.Invoice;
import org.secondopinion.pharmacy.dto.InvoiceUpdateDTO;
import org.secondopinion.pharmacy.dto.PrescriptionFillRequestUpdateDTO;
import org.secondopinion.pharmacy.dto.PrescriptionPriceUpdateDTO;
import org.secondopinion.pharmacy.dto.Prescriptionfillrequest;
import org.secondopinion.pharmacy.dto.Prescriptionprice;
import org.secondopinion.pharmacy.dto.Shippingaddress;
import org.secondopinion.pharmacy.service.IPrescriptionService;
import org.secondopinion.request.Response;
import org.secondopninion.PharmacyApplactionTest;
import org.springframework.beans.factory.annotation.Autowired;

public class PrescriptionServiceTest extends PharmacyApplactionTest {
	
	@Autowired
	private IPrescriptionService iPrescriptionService;
	
	
	
	@Test
	public void testFillPrescription() 
	{
		FillPrescriptionRequestDTO request = new FillPrescriptionRequestDTO();
		request.setAppointmentDate(new Date());
		request.setPatientAppointmentId(12L);
		request.setDocumentLocation("doc");
		request.setDoctorName("entity name");
		request.setDoctorAppointmentId(123L);
	
	   request.setPharmacyaddressId(2L);
		iPrescriptionService.fillPrescription(request);
		
		assertNotNull(request);
		
	}
	
	@Test
	public void getPharmacyReports() {
		Map<String, Object> myReports = iPrescriptionService.getPharmacyReports(3L);
		assertNotNull(myReports);
	}
	

	@Test
	public void testupdatePrescriptionfillrequestWithPrices() {
		
		
	}
	
	@Test
	public void testgetNewPrescriptionFillRequests() {
		Response<List<Prescriptionfillrequest>> response = iPrescriptionService.
				getNewPrescriptionFillRequests(12L, null,null);
	}
	
	@Test
	public void testgetAllPrescriptionFillRequests() {
		Response<List<Prescriptionfillrequest>> response = iPrescriptionService.
				getAllPrescriptionFillRequests(12L, null,null);
	}

	@Test
	public void  testgetPrescriptionPricesByPFRId() {
		List<Prescriptionprice> pps = iPrescriptionService.getPrescriptionPricesByPFRId(12L);
	}

	@Test
	public void testgetInvoicesByPFRId() {
		Invoice pps = iPrescriptionService.getInvoicesByPFRId(12L);
	}

	@Test
	public void testgetShippingAddressesByPFRId() {
		List<Shippingaddress> sas = iPrescriptionService.getShippingAddressesByPFRId(12L);
	}
	
	@Test
	public void updatePrescriptionfillrequestWithPrices() {
		PrescriptionFillRequestUpdateDTO  fillRequestUpdateDTO=new PrescriptionFillRequestUpdateDTO();
		//{"prescriptionPriceUpdateDTOs":[{"quantity":2,"unitPrice":"50","totalPrice":93.6,"sgst":2,"cgst":"2","price":104,"discount":"10","active":"Y","appointmentId":15,"invoiceId":7,"medicalPrescriptionId":2,"medicine":"Augmentin 625 Duo Tablet","patientId":5,"power":"","prescriptionFillRequestId":7,"prescriptionPriceId":12},
		InvoiceUpdateDTO invoiceUpdateDTO=new InvoiceUpdateDTO();
		invoiceUpdateDTO.setDiscount(6D);
		invoiceUpdateDTO.setInvoiceId(7L);
		invoiceUpdateDTO.setTotal(93.6D);
		fillRequestUpdateDTO.setInvoiceUpdateDTO(invoiceUpdateDTO);
		fillRequestUpdateDTO.setPrescriptionFillRequestId(7L);
		PrescriptionPriceUpdateDTO prescriptionPriceUpdateDTOs=new PrescriptionPriceUpdateDTO();
		prescriptionPriceUpdateDTOs.setCgst(2D);
		prescriptionPriceUpdateDTOs.setMedicalPrescriptionId(12L);
		prescriptionPriceUpdateDTOs.setMedicine("BUDENASE 2.5 MG/1 MG RESPULES100");
		prescriptionPriceUpdateDTOs.setPower("100");
		prescriptionPriceUpdateDTOs.setSgst(2D);
		prescriptionPriceUpdateDTOs.setQuantity(2L);
		prescriptionPriceUpdateDTOs.setDiscount(10D);
		prescriptionPriceUpdateDTOs.setTotalPrice(93.6D);
		prescriptionPriceUpdateDTOs.setPatientId(6L);
		prescriptionPriceUpdateDTOs.setUnitPrice(50D);
		fillRequestUpdateDTO.setInvoiceUpdateDTO(invoiceUpdateDTO);
		fillRequestUpdateDTO.setPrescriptionPriceUpdateDTOs(Arrays.asList(prescriptionPriceUpdateDTOs));
		 iPrescriptionService.updatePrescriptionfillrequestWithPrices(fillRequestUpdateDTO);
	}

}
