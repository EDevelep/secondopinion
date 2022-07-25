package org.secondopnion.patient.service;

import static org.junit.Assert.assertNotNull;

import java.util.Collection;
import java.util.Map;

import org.junit.Test;
import org.secondopinion.patient.dto.Carddetails;
import org.secondopinion.patient.dto.PatientPaymentDetails;
import org.secondopinion.patient.service.IPatientPaymentDetailsService;
import org.secondopinion.request.Response;
import org.secondopnion.patient.PatientApplicationTest;
import org.springframework.beans.factory.annotation.Autowired;

public class PatientPaymentDetailsServiceTest extends PatientApplicationTest {

	@Autowired
	private IPatientPaymentDetailsService iPatientPaymentDetailsService;

	@Test
	public void getById() {
		Long customerId = 23L;
		iPatientPaymentDetailsService.getById(customerId);
	}

	@Test
	public void getcardDetailsById() {
		Long cardId = 3L;
		iPatientPaymentDetailsService.getcardDetailsById(cardId);
	}

	@Test
	public void getCardDetailsbyUserID() {
		Long userId = 141L;
		iPatientPaymentDetailsService.getCardDetailsbyUserID(userId);
	}

	@Test
	public void savepayment() {
		PatientPaymentDetails customerDetail = new PatientPaymentDetails();
		customerDetail.setAmountPaid(200d);
		customerDetail.setPatientId(32L);
		customerDetail.setAppointmentId(30L);
		customerDetail.setCarddetailsId(32L);
		customerDetail.setCurrencyType("INR");
		customerDetail.setTotalAmount(800.00);
		customerDetail.setTransactionstatus("SUCCESS");
		customerDetail.setDescription("test");
		customerDetail.setPaymentThrough("hdfc");
		customerDetail.setStripeCustomerEmail("jatin@curme-metrci.com");
		customerDetail.setStripeCustomerName("jatin");
		customerDetail.setActive('Y');
		customerDetail.setAmountPaid(800.00);
		customerDetail.setAppointmentId(100L);
		customerDetail.setChargeFor("DOCTOR");
		customerDetail.setTransactionType("DEBITCARD");
		customerDetail.setCarddetailsId(121L);
		iPatientPaymentDetailsService.save(customerDetail);
	}

	@Test
	public void testSaveCard() {
		Carddetails carddetails = new Carddetails();
		carddetails.setCardbankname("axis");
		carddetails.setCardnumber("42424242424242");
		carddetails.setCardtype("visa");
		carddetails.setCvv("605");
		carddetails.setUsername("jatinsharna");
		carddetails.setExpmonth(12L);
		carddetails.setUserId(32L);
		carddetails.setExpyear(2021L);
		iPatientPaymentDetailsService.save(carddetails);
		assertNotNull(carddetails);
	}

	@Test
	public void testupdateCard() {
		Carddetails carddetails = new Carddetails();
		carddetails.setCardbankname("hdfc");
		carddetails.setCardnumber("def345");
		carddetails.setCardtype("visa");
		carddetails.setCvv("605");
		iPatientPaymentDetailsService.save(carddetails);
		assertNotNull(carddetails);
	}

	@Test
	public void testGetcarddetails() {
		Carddetails carddetails = iPatientPaymentDetailsService.getcardDetailsById(14L);
		assertNotNull(carddetails);
	}

	@Test
	public void testgetCardDetailsbyUserID() {
		Collection<Carddetails> carddetails = iPatientPaymentDetailsService.getCardDetailsbyUserID(124L);
		assertNotNull(carddetails);
	}

	@Test
	public void testGetById() {
		PatientPaymentDetails patientPaymentDetails = iPatientPaymentDetailsService.getById(15L);
		assertNotNull(patientPaymentDetails);
	}

	@Test
	public void chargeThePatient() {
		PatientPaymentDetails patientPaymentDetails = new PatientPaymentDetails();

	//	Response<Map<String, String>> patientPaymentDetail = iPatientPaymentDetailsService.chargeThePatient(null);
		//assertNotNull(patientPaymentDetail);
	}

}
