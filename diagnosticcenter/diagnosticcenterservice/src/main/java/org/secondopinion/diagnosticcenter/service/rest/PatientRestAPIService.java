package org.secondopinion.diagnosticcenter.service.rest;

import org.secondopinion.configurations.CustomRestTemlpateConfig;
import org.secondopinion.diagnosticcenter.dto.Appointment;
import org.secondopinion.enums.AppointmentForEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;

@Component
public class PatientRestAPIService {

	@Autowired
	private CustomRestTemlpateConfig customRestTemlpateConfig;

	@Value("${update.patient.appointment.bydc.appointmentstatus}")
	private String updateAppointmentUponDCAppointmentStatus;

	@Value("${upload.patient.reports.bydc}")
	private String uploadReportsToThePatient;

	@Value("${update.appointment.bydcu}")
	private String updateAppointmentbyDiagnosticCenterUser;

	@Value("${cancle.appointment.bydcu}")
	private String cancelAppointmentForDiagnosticCenterUser;

	@Value("${reschedule.appointment.bydcu}")
	private String rescheduleAppointmentForDiagnosticCenterUser;

	public void rescheduleAppointmentForDiagnosticCenterUser(Appointment appointment) {
		String uri = String.format(rescheduleAppointmentForDiagnosticCenterUser, appointment.getAppointmentId(),
				appointment.getPatientId(), AppointmentForEnum.DIAGNOSTIC_CENTER.name());
		rescheduleAppointmentForDiagnosticCenterUser(uri, appointment);
	}

	private void rescheduleAppointmentForDiagnosticCenterUser(String uri, Appointment appointment) {
		customRestTemlpateConfig.callRestAPI(appointment, uri, HttpMethod.POST, String.class).getData();

	}

	public void cancelAppointmentForDiagnosticCenterUser(Appointment appointment) {
		String uri = String.format(cancelAppointmentForDiagnosticCenterUser, appointment.getAppointmentId(),
				appointment.getPatientId(), AppointmentForEnum.DIAGNOSTIC_CENTER.name());
		cancelAppointmentForDiagnosticCenterUser(uri, appointment);
	}

	private void cancelAppointmentForDiagnosticCenterUser(String uri, Appointment appointment) {
		customRestTemlpateConfig.callRestAPI(appointment, uri, HttpMethod.POST, String.class).getData();

	}

	public void updateAppointmentbyDiagnosticCenterUser(Appointment appointment) {
		String uri = String.format(updateAppointmentbyDiagnosticCenterUser, appointment.getDiagnosticCenterAddressId(),
				appointment.getPatientId(), AppointmentForEnum.DIAGNOSTIC_CENTER.name());
		updatePatientAppointmentUponDCAcceptsOrRejects(uri, appointment);
	}

	private void updatePatientAppointmentUponDCAcceptsOrRejects(String uri, Appointment appointment) {
		customRestTemlpateConfig.callRestAPI(appointment, uri, HttpMethod.POST, String.class).getData();
	}

	public void uploadReportsToThePatient(Long appointmentId, String uploadReference, String originalFilename) {

		String uri = String.format(uploadReportsToThePatient, appointmentId, uploadReference, originalFilename);

		customRestTemlpateConfig.callRestAPI(null, uri, HttpMethod.POST, String.class).getData();

	}

	public void updateAppointmentUponDCAppointmentStatus(Appointment appointment) {
		// call patient appointment rest api to update DC accepted column with 'Y'
		String uri = String.format(updateAppointmentUponDCAppointmentStatus, appointment.getAppointmentId(),
				appointment.getPatientId(), AppointmentForEnum.DIAGNOSTIC_CENTER.name());
		updatePatientAppointmentUponDCAcceptsOrRejects(uri, appointment);
	}

}
