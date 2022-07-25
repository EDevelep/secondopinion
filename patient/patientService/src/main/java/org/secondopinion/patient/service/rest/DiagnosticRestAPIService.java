package org.secondopinion.patient.service.rest;

import org.json.simple.JSONObject;
import org.secondopinion.configurations.CustomRestTemlpateConfig;
import org.secondopinion.enums.AppointmentStatusEnum;
import org.secondopinion.patient.dto.Appointment;
import org.secondopinion.patient.dto.Invoice;
import org.secondopinion.patient.dto.InvoiceDTO;
import org.secondopinion.patient.dto.PatientRatingsDTO;
import org.secondopinion.utils.JSONHelper;
import org.secondopinion.utils.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;

@Component
public class DiagnosticRestAPIService {

	@Autowired
	private CustomRestTemlpateConfig customRestTemlpateConfig;

	@Value("${diagnostic.appointment.saveurl}")
	private String diagnosticAppointmentSaveURL;
	
	@Value("${diagnostic.invoice.save}")
	private String createDiagnosticInvoice;
	
	@Value("${diagnostic.rating.save}")
	private String createDiagnosticrating;
	
	@Value("${diagnostic.appointment.delete}")
	private String deleteDiagnosticAppointmentIfPaymentFailed;
	
	@Value("${diagnostic.appointment.update.status.bypatient}")
	private String updateDiagnosticAppointmentByPatient;

	public void createDiagnosticCentreAppointmentForPatient(Appointment appointment) {
		String jo = customRestTemlpateConfig.callRestAPI(appointment, diagnosticAppointmentSaveURL, HttpMethod.POST, String.class).getData();
		if (!StringUtil.isNullOrEmpty(jo)) {
			JSONObject gson = JSONHelper.buidJSONObject(jo);
			
			
			Long referenceAppointmentId = (Long) gson.get(Appointment.FIELD_appointmentId);
			appointment.setReferenceAppointmentId(referenceAppointmentId);
			appointment.setEntityAccepted('N');

			StringBuilder message = new StringBuilder();
			message.append("Your appointment has been successfully placed and ");
			String appointmentStatus = (String) gson.get(Appointment.FIELD_appointmentStatus);
			if(!appointmentStatus.equals(AppointmentStatusEnum.ENTITY_ACCEPTED.name()) ) {
				appointmentStatus = AppointmentStatusEnum.ENTITY_PENDING.name();
				message.append(" is pending to accept by "+ appointment.getReferenceEntityName());
			} else {
				appointment.setEntityAccepted('Y');
				message.append(" is accepted by "+ appointment.getReferenceEntityName());
			}
			message.append(appointment.getReferenceEntityName());
			appointment.setAppointmentStatus(appointmentStatus);
			
			appointment.setAppointmentId(null);
			appointment.setDescription(message.toString());

		}

	}

	public InvoiceDTO createEntityInvoice(Invoice invoice, Long paymentDetailsId) {
		InvoiceDTO invoiceDTO = InvoiceDTO.buildInvoiceForDiagnosticCenter(invoice, paymentDetailsId);
		String jo = customRestTemlpateConfig.callRestAPI(invoiceDTO, createDiagnosticInvoice, HttpMethod.POST, String.class).getData();
		
		JSONObject gson = JSONHelper.buidJSONObject(jo);
		invoiceDTO.setEntityInvoiceId((Long)gson.get(Invoice.FIELD_invoiceId));
		return invoiceDTO;
	}

	public void deleteDiagnosticAppointmentIfPaymentFailed(Long referenceAppointmentId) {
		String url = String.format(deleteDiagnosticAppointmentIfPaymentFailed, referenceAppointmentId);
		customRestTemlpateConfig.callRestAPI(url, HttpMethod.DELETE, String.class);
	}

	public void ratingSericeForDiagnosticCenter(PatientRatingsDTO patientratingsdto) {
		customRestTemlpateConfig.callRestAPI(patientratingsdto, createDiagnosticrating, HttpMethod.POST, String.class)
		.getData();
		
	}

	public void updateDoctorAppointmentUponPatientRejectsTheAppointment(Appointment appointment) {
		String url = String.format(updateDiagnosticAppointmentByPatient,
				appointment.getReferenceAppointmentId(), appointment.getAppointmentStatus());
		customRestTemlpateConfig.callRestAPI(url, HttpMethod.POST, String.class).getData();
	}
}
