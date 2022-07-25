package org.secondopinion.caretaker.rest;


import org.secondopinion.caretaker.dto.Appointment;
import org.secondopinion.configurations.CustomRestTemlpateConfig;

import org.secondopinion.enums.AppointmentForEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;

@Component
public class PatientRestAPIService {
	
	@Autowired
	private CustomRestTemlpateConfig customRestTemlpateConfig;

	@Value("${update.patient.appointment.bydoctor.appointmentstatus}")
	private String updateAppointmentUponDoctorAppointmentStatus;

	@Value("${updateRelationshipUponDoctorRejections}")
	private String updateRelationshipUponDoctorRejectionsURI;
	
	@Value("${updateRelationshipUponDoctorAccepts}")
	private String updateRelationshipUponDoctorAcceptsURI;
	
	private void updatePatientAppointmentUponCaretakerAcceptsOrRejects(String uri, Appointment appointment) {
		customRestTemlpateConfig.callRestAPI(appointment, uri, HttpMethod.POST, String.class).getData();
	}
	
	public void updateAppointmentUponCaretakerAppointmentStatus(Appointment appointment) {
		//call patient appointment rest api to update doctoraccepted column with 'Y'
		String uri = String.format(updateAppointmentUponDoctorAppointmentStatus, appointment.getAppointmentId(), appointment.getUserId(), AppointmentForEnum.CARETAKER.name());
		updatePatientAppointmentUponCaretakerAcceptsOrRejects(uri, appointment);
	}
	
	public void uppdatePatientRequestUponCaretakerRejectsORAccepts(String uri) {
		customRestTemlpateConfig.callRestAPI(uri, HttpMethod.POST, String.class).getData();

	}
	
	/*
	 * public void updateRelationshipUponDoctorAccepts(PatientRequest
	 * patientRequest) { String uri =
	 * String.format(updateRelationshipUponDoctorAcceptsURI,
	 * patientRequest.getDoctorId(), patientRequest.getPatientId());
	 * uppdatePatientRequestUponDoctorRejectsORAccepts(uri); }
	 * 
	 * public void updateRelationshipUponDoctorRejections(PatientRequest
	 * patientRequest) { String uri =
	 * String.format(updateRelationshipUponDoctorRejectionsURI,
	 * patientRequest.getDoctorId(), patientRequest.getPatientId());
	 * uppdatePatientRequestUponDoctorRejectsORAccepts(uri); }
	 */
	
}
