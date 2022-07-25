package org.secondopinion.patient.dao;

import java.util.List;

import org.secondopinion.dao.IDAO;
import org.secondopinion.patient.dto.PatientPaymentDetails;

public interface PatientPaymentDetailsDAO extends IDAO<PatientPaymentDetails,Long >{

	List<PatientPaymentDetails> getByPatientAndAppointment(Long patientId, Long appointmentId);

	PatientPaymentDetails findPatientPaymentDetailsById(Long customerId);

	PatientPaymentDetails getByTransactionId(Long patientId, String transactionId);
}