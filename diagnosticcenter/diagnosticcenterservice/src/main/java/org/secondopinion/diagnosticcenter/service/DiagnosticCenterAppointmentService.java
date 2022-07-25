 package org.secondopinion.diagnosticcenter.service;

import java.util.List;
import java.util.Map;

import org.secondopinion.diagnosticcenter.dto.Appointment;
import org.secondopinion.diagnosticcenter.dto.AppointmentSearchRequest;
import org.secondopinion.diagnosticcenter.dto.UpdateDiagnosticCenterAddressAppointmentStatus;
import org.secondopinion.diagnosticcenter.dto.ViewAppointments;
import org.secondopinion.diagnosticcenter.dto.ViewAppointments.ViewAppointmentEnum;
import org.secondopinion.request.Response;
import org.springframework.web.multipart.MultipartFile;

public interface DiagnosticCenterAppointmentService {

	
	
	Response<List<Appointment>> getAllAppointmentBySearchCritieria(AppointmentSearchRequest appointmentDTO);
	Map<ViewAppointmentEnum, Response<List<Appointment>>> myAppointments(ViewAppointments viewAppointments);
	Appointment bookAppointemntWithDiagnosticCenter(Appointment appointment);
	void updateAppointmentStatusUponPatientRejectsTheRequest(Long entityAppointmentId, String status);
	void updateAppointmentRequestsUponDCChoice(UpdateDiagnosticCenterAddressAppointmentStatus appointment);
	void deletAppointment(Long appointmentId);
	void uploadReportsToThePatient(Long appointmentId, MultipartFile medicalreport);
}