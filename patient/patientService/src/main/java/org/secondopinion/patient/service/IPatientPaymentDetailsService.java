
package org.secondopinion.patient.service;

import java.util.Collection;
import java.util.List;
import java.util.Map;

import org.secondopinion.patient.dto.Carddetails;
import org.secondopinion.patient.dto.PatientPaymentDetails;
import org.secondopinion.patient.dto.PatientPaymentDetailsDTO;
import org.secondopinion.request.Response;

public interface IPatientPaymentDetailsService {

	void save(PatientPaymentDetails customerDetail);

	PatientPaymentDetails getById(Long customerId);

	List<PatientPaymentDetails> getPatientPaymentDetailsByPatientAndAppointment(Long patientId, Long appointmentId);

	Map<String, String> paymentForPatientAppointment(PatientPaymentDetails patientPaymentDetails);
	
	void save(Carddetails carddetails);
	
	void deletecardDetails(Long cardId);
	
	Carddetails getcardDetailsById(Long cardId);
	
	Collection<Carddetails> getCardDetailsbyUserID(Long userId);

	String chargeThePatient(PatientPaymentDetails patientPaymentDetails);

	String refundToThePatient(Long patientId, String transactionId, String reason);

	PatientPaymentDetailsDTO chargeThePatientForInvoice(PatientPaymentDetailsDTO patientPaymentDetails);
}
