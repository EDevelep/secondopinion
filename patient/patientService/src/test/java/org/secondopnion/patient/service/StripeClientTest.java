/*
 * package org.secondopnion.patient.service;
 * 
 * import static org.junit.Assert.assertNotNull;
 * 
 * import java.util.Map;
 * 
 * import org.junit.Test; import org.secondopinion.request.Response; import
 * org.secondopinion.patient.dto.Carddetails; import
 * org.secondopinion.patient.dto.PatientPaymentDetails; import
 * org.secondopinion.patient.dto.PatientPaymentDetails.CurrencyTypeEnum; import
 * org.secondopinion.patient.dto.PatientPaymentDetails.TransactionTypeEnum;
 * import org.secondopinion.patient.service.IPatientPaymentDetailsService;
 * import org.secondopnion.patient.PatientApplicationTest; import
 * org.springframework.beans.factory.annotation.Autowired;
 * 
 * 
 * public class StripeClientTest extends PatientApplicationTest {
 * 
 * @Autowired private IPatientPaymentDetailsService
 * iPatientPaymentDetailsService;
 * 
 * @Test public void testChargeThePatientForPharmacy() throws Exception { Long
 * patientId = 173L; Carddetails carddetails = new Carddetails();
 * carddetails.setUsername("ajay");
 * carddetails.setCardnumber("4000056655665556"); carddetails.setCvv("123");
 * carddetails.setExpmonth(12L); carddetails.setExpyear(2024L);
 * carddetails.setUserId(patientId); carddetails.setCardbankname("HDFC");
 * carddetails.setCardtype("DEBIT"); carddetails.setSaveCardDetails(true);
 * 
 * PatientPaymentDetails patientPaymentDetails = new PatientPaymentDetails();
 * patientPaymentDetails.setStripeCustomerEmail("kajayui@gmail.com");
 * patientPaymentDetails.setStripeCustomerName("ajay");
 * patientPaymentDetails.setTransactionType(TransactionTypeEnum.DEBITCARD.name()
 * ); patientPaymentDetails.setChargeFor("PHARMACY");
 * patientPaymentDetails.setPatientId(patientId);
 * patientPaymentDetails.setPatientInvoiceId(5L);
 * patientPaymentDetails.setTotalAmount(34d);
 * patientPaymentDetails.setAmountPaid(34d);
 * patientPaymentDetails.setFeeType("Consultation");
 * patientPaymentDetails.setCurrencyType(CurrencyTypeEnum.INR.name());
 * patientPaymentDetails.setDescription("PHARMACY");
 * patientPaymentDetails.setDiscountAmount(10d);
 * patientPaymentDetails.setDiscountType("AMOUNT");
 * patientPaymentDetails.setPaymentThrough("DEBIT_CARD");
 * patientPaymentDetails.setCarddetails(carddetails);
 * 
 * Response<Map<String, String>> reposne =
 * iPatientPaymentDetailsService.chargeThePatient(patientPaymentDetails);
 * assertNotNull(reposne.getData()); }
 * 
 * public void saveCardDetail() {
 * 
 * }
 * 
 * }
 */