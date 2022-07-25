package org.secondopinion.diagnosticcenter.dao; 

import java.time.LocalTime;
import java.util.Date;
import java.util.List;

import org.secondopinion.dao.IDAO;
import org.secondopinion.diagnosticcenter.dto.Appointment;
import org.secondopinion.diagnosticcenter.dto.AppointmentSearchRequest;
import org.secondopinion.request.Response;

public interface AppointmentDAO extends IDAO<Appointment,Long >{

	
	Response<List<Appointment>> getAllAppointmentBySearchCritieria(AppointmentSearchRequest appointmentDTO);

	List<Appointment> getByAppointmentDateAndFromTime(Date currentDateOnly, LocalTime currentLocalTime);

	Response<List<Appointment>> currentAppointments(Long diagnosticCenterAddressId, Integer pageNum, Integer maxResult);

	Response<List<Appointment>> previousAppointments(Long diagnosticCenterAddressId, Long patientId, Integer pageNum, Integer maxResult);

	Response<List<Appointment>> upcomingAppointments(Long diagnosticCenterAddressId, Integer pageNum, Integer maxResult);

	Response<List<Appointment>> retrieveCancelledAppoitments(Long diagnosticCenterAddressId, Integer pageNum, Integer maxResult);

	Response<List<Appointment>> retrieveRescheduledAppoitments(Long diagnosticCenterAddressId, Integer pageNum, Integer maxResult);

	Long getTotalPatientsOfDiagnosticCenter(Long diagnosticCenterId, String name);

	List<Appointment> findappointmentByScheduleHoursId(Long schedulehoursId);

	Appointment getAppointmentbypatientUserId(Long patientUserId, Long diagnosticCenterAddressId);

	
}