package org.secondopinion.patient.service.impl;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

import org.payment.gateway.GatewayEnum;
import org.payment.gateway.PaymentGatewayConnectorService;
import org.payment.gateway.PaymentGatewayDTO;
import org.payment.gateway.razorpay.RazorPay;
import org.secondopinion.enums.AppointmentForEnum;
import org.secondopinion.enums.InvoiceStatusEnum;
import org.secondopinion.enums.InvoiceTypeEnum;
import org.secondopinion.patient.dao.AppointmentDAO;
import org.secondopinion.patient.dao.CarddetailsDAO;
import org.secondopinion.patient.dao.InvoiceDAO;
import org.secondopinion.patient.dao.MedicalprescriptionDAO;
import org.secondopinion.patient.dao.PatientPaymentDetailsDAO;
import org.secondopinion.patient.dao.PrescriptionDAO;
import org.secondopinion.patient.dao.UserDAO;
import org.secondopinion.patient.dto.Appointment;
import org.secondopinion.patient.dto.Carddetails;
import org.secondopinion.patient.dto.FetchPayment;
import org.secondopinion.patient.dto.Invoice;
import org.secondopinion.patient.dto.InvoiceDTO;
import org.secondopinion.patient.dto.InvoiceSearchDTO;
import org.secondopinion.patient.dto.Medicalprescription;
import org.secondopinion.patient.dto.PatientPaymentDetails;
import org.secondopinion.patient.dto.PatientPaymentDetails.TransactionStatusEnum;
import org.secondopinion.patient.dto.PatientPaymentDetails.TransactionTypeEnum;
import org.secondopinion.patient.dto.PatientPaymentDetailsDTO;
import org.secondopinion.patient.dto.Prescription;
import org.secondopinion.patient.dto.User;
import org.secondopinion.patient.exception.PatientServerException;
import org.secondopinion.patient.service.INotificationalertsService;
import org.secondopinion.patient.service.IPatientPaymentDetailsService;
import org.secondopinion.patient.service.helper.PatientPaymentDetailsMessage;
import org.secondopinion.patient.service.rest.DiagnosticRestAPIService;
import org.secondopinion.patient.service.rest.DoctorRestAPIService;
import org.secondopinion.patient.service.rest.PharmacyRestAPIService;
import org.secondopinion.request.Response;
import org.secondopinion.request.Response.StatusEnum;
import org.secondopinion.utils.DateUtil;
import org.secondopinion.utils.NotificationAlert;
import org.secondopinion.utils.Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import com.razorpay.Refund;
import com.stripe.model.Charge;

import io.grpc.netty.shaded.io.netty.util.internal.StringUtil;

@Service
public class PatientPaymentDetailsServiceImpl implements IPatientPaymentDetailsService {

	@Autowired
	private CarddetailsDAO carddetailsDAO;

	@Autowired
	private PatientPaymentDetailsDAO patientPaymentDetailsDAO;

	@Autowired
	private AppointmentDAO appointmentDAO;

	@Autowired
	private DoctorRestAPIService doctorRestAPIService;

	@Autowired
	private DiagnosticRestAPIService diagnosticRestServiceAPI;

	@Autowired
	private PharmacyRestAPIService pharmacyRestAPIService;

	@Autowired
	private INotificationalertsService notificationalertsService;

	@Autowired
	private PrescriptionDAO prescriptionDAO;

	@Autowired
	private InvoiceDAO invoiceDAO;

	@Autowired
	private UserDAO userDAO;
	@Value("${stripe_secret_key}")
	private String secretKey;

	@Autowired
	private MedicalprescriptionDAO medicalPrescriptionDAO;
	@Autowired
	private RazorPaymentServiceImpl razorPaymentServiceImpl;

	@Override
	@Transactional
	public void save(PatientPaymentDetails customerDetail) {
		customerDetail.setActive('Y');
		patientPaymentDetailsDAO.save(customerDetail);
	}

	@Override
	@Transactional
	public void save(Carddetails carddetails) {
		carddetailsDAO.save(carddetails);
	}

	@Override
	@Transactional
	public void deletecardDetails(Long cardId) {
		Carddetails carddetails = carddetailsDAO.findCarddetailsById(cardId);
		if (Objects.isNull(carddetails)) {
			throw new IllegalArgumentException("carddetails not found.");

		}
		carddetails.setActive('N');
		carddetailsDAO.save(carddetails);
	}

	@Override
	@Transactional
	public Carddetails getcardDetailsById(Long cardId) {
		return carddetailsDAO.findCarddetailsById(cardId);
	}

	@Override
	@Transactional
	public List<Carddetails> getCardDetailsbyUserID(Long userId) {
		return carddetailsDAO.findCarddetailsByUserId(userId);
	}

	@Override
	@Transactional
	public PatientPaymentDetails getById(Long customerId) {

		return patientPaymentDetailsDAO.findPatientPaymentDetailsById(customerId);
	}

	/**
	 * 1. verifying and retrieve customer from stripe database. 2. if customer not
	 * exists in stripe database then we are creating the customer details in the
	 * stripe database along with card no, cvv, exp_month, exp_year 3. verifying
	 * emailid in the curemetricuser.customerdetails 4. if customerdetials not
	 * exists then create customer details into curemetricuser.customerdetails. 5.
	 * create charge details and payment for the customer.
	 * 
	 * @param paymentModel
	 */
	@Override
	@Transactional
	public Map<String, String> paymentForPatientAppointment(PatientPaymentDetails patientPaymentDetails) {

		Map<String, String> cardStatus = paymentTransaction(patientPaymentDetails);
		String transactionStatus = getTransactionStatusAfterPayment(cardStatus);

		if (transactionStatus != null && transactionStatus.equals(TransactionStatusEnum.FAILED.name())) {
			Long appointmentId = patientPaymentDetails.getAppointmentId();
			Appointment appointment = appointmentDAO.findAppointmentIdById(appointmentId);
			if (Objects.nonNull(appointment.getReferenceAppointmentId())) {
				if (appointment.getAppointmentFor().equals(AppointmentForEnum.DIAGNOSTIC_CENTER.name())) {
					// delete diagnostic appointment
					diagnosticRestServiceAPI
							.deleteDiagnosticAppointmentIfPaymentFailed(appointment.getReferenceAppointmentId());
				}

				if (appointment.getAppointmentFor().equals(AppointmentForEnum.DOCTOR.name())) { // delete doctor
																								// appointment
					doctorRestAPIService
							.deleteDoctorAppointmentIfPaymentFailed(appointment.getReferenceAppointmentId());
				}

			}

			appointment.setActive('N');
			appointmentDAO.save(appointment);
		}

		return cardStatus;
	}

	private void validatePatientPaymentDetails(PatientPaymentDetails patientPaymentDetails) {

		patientPaymentDetails.validate(false);
		Double referenceFee = patientPaymentDetails.getFee();

		// calculation starts here
		Double totalAmount = patientPaymentDetails.getTotalAmount();
		patientPaymentDetails.setTotalAmount(Util.addTwoDoubleOperands(referenceFee, totalAmount));// need to remove or
																									// add from
																									// feedetails
																									// calculation

	}

	private Carddetails saveCardDetails(Carddetails cardDetails) {
		validateCardDetails(cardDetails);
		if (!cardDetails.isSaveCardDetails()) {
			return cardDetails;
		}

		List<Carddetails> cardDetailses = carddetailsDAO.getByPatientAndCardNumber(cardDetails.getUserId(),
				cardDetails.getCardnumber());
		if (CollectionUtils.isEmpty(cardDetailses)) {
			if (!cardDetails.isSaveCardDetails()) {
				return cardDetails;
			} else {
				carddetailsDAO.save(cardDetails);
				return cardDetails;
			}
		}

		Carddetails dbCardDetails = cardDetailses.get(0);
		dbCardDetails.setCardtype(cardDetails.getCardtype());
		dbCardDetails.setCardbankname(cardDetails.getCardbankname());
		dbCardDetails.setExpmonth(cardDetails.getExpmonth());
		dbCardDetails.setExpyear(cardDetails.getExpyear());
		dbCardDetails.setCvv(cardDetails.getCvv());
		dbCardDetails.setUsername(cardDetails.getUsername());
		dbCardDetails.setActive('Y');

		carddetailsDAO.save(dbCardDetails);

		return dbCardDetails;
	}

	public static void validateCardDetails(Carddetails cardDetails) {

		if (Objects.isNull(cardDetails)) {
			throw new IllegalArgumentException(" card details are mondatory");
		}

	}

	@Override
	public List<PatientPaymentDetails> getPatientPaymentDetailsByPatientAndAppointment(Long patientId,
			Long appointmentId) {

		return patientPaymentDetailsDAO.getByPatientAndAppointment(patientId, appointmentId);
	}

	@SuppressWarnings("unchecked")
	@Override
	@Transactional
	public String chargeThePatient(PatientPaymentDetails patientPaymentDetails) {
		Map<String, String> transactionStatus = null;
		String successMesage = "Charg Is Done.";
		String chargeFor = patientPaymentDetails.getChargeFor();
		if (StringUtil.isNullOrEmpty(chargeFor)) {
			throw new IllegalArgumentException("Field chargeFor can not be null.");
		}
		try {
			if (chargeFor.equals(InvoiceTypeEnum.PHARMACY.name())) {
				transactionStatus = chargeForPrescription(patientPaymentDetails);
			}
		} catch (Exception e) {
			throw new IllegalArgumentException("Exception in  Payment.");

		}
		return successMesage;
	}

	private Response<Map<String, String>> getResponseOnTransactionStatus(Map<String, String> cardStatus) {
		Optional<String> transactionStatusOptional = cardStatus.entrySet().stream().map(es -> es.getValue())
				.findFirst();
		Response<Map<String, String>> respone = new Response<>(cardStatus, StatusEnum.SUCCESS, "Transaction success.");
		if (transactionStatusOptional.isPresent()) {
			String transactionStatus = transactionStatusOptional.get();

			if (transactionStatus.equals(TransactionStatusEnum.FAILED.name())) {
				respone = new Response<>(cardStatus, StatusEnum.FAILURE, "Transaction failed.");
			}

		}
		return respone;
	}

	private Map<String, String> chargeForAdditionalAppointmentTime(PatientPaymentDetails patientPaymentDetails) {
		Long appointmentId = patientPaymentDetails.getAppointmentId();
		if (Objects.isNull(appointmentId)) {
			throw new IllegalArgumentException(
					"Field " + PatientPaymentDetails.FIELD_appointmentId + " cannot be null. ");
		}
		Map<String, String> cardStatus = paymentTransaction(patientPaymentDetails);
		String transactionStatus = getTransactionStatusAfterPayment(cardStatus);
		Double newAmountPaid = patientPaymentDetails.getAmountPaid();
		if (transactionStatus != null && transactionStatus.equals(TransactionStatusEnum.SUCCESS.name())) {
			// to do add the current amount to previous amount in the appointment
			Appointment appointment = appointmentDAO.findAppointmentIdById(appointmentId);
			Double previousAmountPaid = appointment.getAmountPaid();

			Double totalAmountPaid = Util.addTwoDoubleOperands(previousAmountPaid, newAmountPaid);
			appointment.setAmountPaid(totalAmountPaid);
			appointmentDAO.save(appointment);

			// call the doctor rest api service
			appointment.setAppointmentId(appointment.getReferenceAppointmentId());
			doctorRestAPIService.updateDoctorAppointment(appointment);

			Invoice invoice = Invoice.buildInvoiceForAppointment(appointment, null);
			invoiceDAO.save(invoice);
			doctorRestAPIService.createEntityInvoice(invoice);

			NotificationAlert alert = new NotificationAlert(appointment.getUserId(), appointment.getAppointmentId(),
					"New Appointment", "Your transaction for additional appointment time is cleared.");
			notificationalertsService.sendNotification(alert);

		}
		return cardStatus;
	}

	private String getTransactionStatusAfterPayment(Map<String, String> cardStatus) {
		Optional<String> status = cardStatus.entrySet().stream().map(es -> es.getValue()).findFirst();

		return status.isPresent() ? status.get() : null;
	}

	private Map<String, String> paymentTransaction(PatientPaymentDetails patientPaymentDetails) {
		String transactionType = patientPaymentDetails.getTransactionType();
		if (StringUtil.isNullOrEmpty(patientPaymentDetails.getTransactionType())) {
			throw new IllegalArgumentException("transaction type is mandotry.");
		}
		List<String> enums = Arrays.stream(TransactionTypeEnum.values()).map(it -> it.name())
				.collect(Collectors.toList());
		if (!enums.contains(transactionType)) {
			throw new IllegalArgumentException("Field transactionType should be in " + enums);
		}

		Map<String, String> cardStatus = new HashMap<>();
		Carddetails carddetails = null;
		if (Objects.isNull(carddetails)) {
			return cardStatus;
		}

		String cardNumber = carddetails.getCardnumber();
		carddetails = saveCardDetails(carddetails);

		validatePatientPaymentDetails(patientPaymentDetails);

		patientPaymentDetails.setCarddetailsId(carddetails.getCarddetailsId());

		Charge charge = null;
		String status = TransactionStatusEnum.SUCCESS.name();
		String alertMessage = "Your transaction is success on ";
		try {

			PaymentGatewayDTO pgDTO = new PaymentGatewayDTO(patientPaymentDetails.getStripeCustomerEmail(),
					patientPaymentDetails.getDescription(), patientPaymentDetails.getAmountPaid(),
					patientPaymentDetails.getCurrencyType(), cardNumber, carddetails.getExpmonth(),
					carddetails.getExpyear(), carddetails.getCvv(), GatewayEnum.STRIPE.name(), secretKey);

			charge = PaymentGatewayConnectorService.INSTANCE.chargeTheCustomer(pgDTO);

			patientPaymentDetails.setTransactionId(charge.getId());
			patientPaymentDetails.setTransactionstatus(status);
			patientPaymentDetails.setStripeCustomerId(charge.getCustomerObject().getId());
			cardStatus.put(cardNumber, status);
		} catch (Exception e) {
			status = TransactionStatusEnum.FAILED.name();
			patientPaymentDetails.setTransactionstatus(status);
			patientPaymentDetails.setErrorMessage(e.getMessage());
			patientPaymentDetails.setStripeCustomerId(charge != null ? charge.getCustomerObject().getId() : null);
			cardStatus.put(cardNumber, status);
			alertMessage = "Your transaction has failed on ";
		}
		patientPaymentDetails.setActive('Y');
		patientPaymentDetailsDAO.save(patientPaymentDetails);
		return cardStatus;
	}

	public Map<String, String> chargeForPrescription(PatientPaymentDetails patientPaymentDetails) {
		String transactionStatus = null;
		FetchPayment fetchPayment = null;
		try {
			validateRequiredFieldsForPrescription(patientPaymentDetails);

			Invoice invoice = fetchInvoiceFromDatabase(patientPaymentDetails);
			if (invoice.getInvoicestatus().equals(InvoiceStatusEnum.PAYMENT_DONE.name())) {
				throw new IllegalArgumentException("Payment already made for this Prescription.");
			}
			Medicalprescription medicalPrescription = medicalPrescriptionDAO
					.findOneByProperty(Medicalprescription.FIELD_prescriptionId, invoice.getInvoicereferenceid());
			if (Objects.isNull(medicalPrescription)) {
				throw new IllegalArgumentException("Prescription not found.");
			}
			Prescription prescription = prescriptionDAO.findByPrescrptionId(medicalPrescription.getPrescriptionId());
			if (Objects.isNull(prescription)) {
				throw new IllegalArgumentException("Prescription not found.");
			}
			Map<String, String> cardStatus = new HashMap<>();
			fetchPayment = razorPaymentServiceImpl.fetchPaymentsByUsingPaymentId(patientPaymentDetails.getPaymentId());
			String status = fetchPayment.getStatus();
			if (status != null && TransactionStatusEnum.captured.name().equals(status)) {
				patientPaymentDetails.setTransactionId(fetchPayment.getId());
				patientPaymentDetails.setTransactionDate(fetchPayment.getCreated_at());
				patientPaymentDetails.setTransactionType(fetchPayment.getMethod());
				patientPaymentDetails.setAmountPaid(patientPaymentDetails.getAmountPaid());
				patientPaymentDetails.setCurrencyType("INR");
				patientPaymentDetails.setPaymentThrough(status);
				patientPaymentDetails.setActive('Y');
				patientPaymentDetails.setTransactionstatus("SUCCESS");
				patientPaymentDetails.setStripeCustomerId(UUID.randomUUID().toString());
				patientPaymentDetails.setPrescriptionId(prescription.getPrescriptionId());
				patientPaymentDetails.setAppointmentId(prescription.getPatientAppointmentId());
				patientPaymentDetailsDAO.save(patientPaymentDetails);
				updateInvoiceAfterPayment(invoice, patientPaymentDetails);
				NotificationAlert alert = new NotificationAlert(patientPaymentDetails.getPatientId(),
						patientPaymentDetails.getPatientPaymentDetailsId(), "Payment Status",
						"Your transaction is successful. Paid on: " + invoice.getPaidOn() + ", chareged for: "
								+ patientPaymentDetails.getChargeFor());
				notificationalertsService.sendNotification(alert);

				InvoiceDTO invoiceDTO = InvoiceDTO.buildInvoiceForPharmacy(invoice, prescription);
				pharmacyRestAPIService.updatePharmacyInvoiceStatusAfterPayment(invoiceDTO);
			} else {

				cardStatus.put("status", "Your transaction has failed on" + new Date()
						+ "  if amont will deduct in you account it will refund in two three working days");
				// call refued method

			}

			return cardStatus;
		} catch (IllegalArgumentException iae) {
			throw new IllegalArgumentException(iae);
		} catch (Exception e) {

			throw new PatientServerException(e.getMessage(), e);
		}

	}

	private void updateInvoiceAfterPayment(Invoice invoice, PatientPaymentDetails patientPaymentDetails) {
		invoice.setPaymentReferenceId(patientPaymentDetails.getTransactionId());
		invoice.setTransactiontype(patientPaymentDetails.getTransactionType());
		invoice.setPaidOn(patientPaymentDetails.getTransactionDate());
		invoice.setAmount(patientPaymentDetails.getAmountPaid());
		// invoice.setCardnumber(patientPaymentDetails.getCarddetails().getCardnumber());
		invoice.setInvoicestatus(InvoiceStatusEnum.PAYMENT_DONE.name());
		invoice.setPaid('Y');
		invoice.setPaidOn(DateUtil.getDate());
		invoiceDAO.save(invoice);
	}

	private Invoice fetchInvoiceFromDatabase(PatientPaymentDetails patientPaymentDetails) {
		InvoiceSearchDTO invoicesSerchDTO = new InvoiceSearchDTO();
		invoicesSerchDTO.setPatientid(patientPaymentDetails.getPatientId());
		invoicesSerchDTO.setPatientInvoiceId(patientPaymentDetails.getPatientInvoiceId());
		invoicesSerchDTO.setInvoiceTypeEnum(InvoiceTypeEnum.PHARMACY);

		Response<List<Invoice>> invoiceResponse = invoiceDAO.getinvoiceForSerchcritiria(invoicesSerchDTO);
		if (Objects.isNull(invoiceResponse)) {
			throw new IllegalArgumentException("Invoice not found.");
		}
		List<Invoice> invoices = invoiceResponse.getData();
		if (CollectionUtils.isEmpty(invoices)) {
			throw new IllegalArgumentException("Invoice not found.");
		}
		Invoice invoice = invoices.get(0);
		patientPaymentDetails.setPrescriptionId(invoice.getInvoicereferenceid());
		return invoice;
	}

	private void validateRequiredFieldsForPrescription(PatientPaymentDetails patientPaymentDetails) {
		Long patientId = patientPaymentDetails.getPatientId();
		if (Objects.isNull(patientId)) {
			throw new IllegalArgumentException("Field " + PatientPaymentDetails.FIELD_patientId + " cannot be null. ");
		}
		Long patientInvoiceId = patientPaymentDetails.getPatientInvoiceId();
		if (Objects.isNull(patientInvoiceId)) {
			throw new IllegalArgumentException("Field patientInvoiceId cannot be null. ");
		}

	}

	@Override
	@Transactional(readOnly = true)
	public String refundToThePatient(Long patientId, String transactionId, String reason) {
		PatientPaymentDetails ppd = patientPaymentDetailsDAO.getByTransactionId(patientId, transactionId);
		if (Objects.isNull(ppd)) {
			throw new IllegalArgumentException("Unable to  process refund. Invalid transactionId provided.");
		}
		String userMessage = PatientPaymentDetailsMessage.TransactionFailed + ppd.getTransactionDate();
		Refund refund = razorPaymentServiceImpl.refundToThePatient(transactionId, reason, ppd.getAmountPaid());
		Invoice invoice = invoiceDAO.findById(ppd.getPatientInvoiceId());
		invoice.setInvoicestatus(InvoiceStatusEnum.REFUNDED.name());
		invoiceDAO.save(invoice);
		ppd.setRefundAmount(ppd.getAmountPaid());
		ppd.setTransactionstatus(InvoiceStatusEnum.REFUNDED.name());
		patientPaymentDetailsDAO.save(ppd);
		return userMessage;
	}

	@Override
	@Transactional
	public PatientPaymentDetailsDTO chargeThePatientForInvoice(PatientPaymentDetailsDTO patientPaymentDetails) {
		PatientPaymentDetailsDTO patientPaymentDetailsDTO = new PatientPaymentDetailsDTO();
		User user = userDAO.findById(patientPaymentDetails.getPatientId());
		org.secondopinion.patient.dto.PaymentGatewayDTO paymentGatewayDTO = org.secondopinion.patient.dto.PaymentGatewayDTO
				.buildInvoicetObject(user, patientPaymentDetails);
		RazorPay razorPay = razorPaymentServiceImpl.chargeTheCustomer(paymentGatewayDTO);
		patientPaymentDetailsDTO.setOrderId(razorPay.getRazorpayOrderId());
		patientPaymentDetailsDTO.setAmount(patientPaymentDetails.getAmount());
		patientPaymentDetailsDTO.setPatientId(patientPaymentDetails.getPatientId());
		return patientPaymentDetailsDTO;
	}
}