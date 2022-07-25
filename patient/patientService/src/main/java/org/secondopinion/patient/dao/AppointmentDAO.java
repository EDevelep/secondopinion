package org.secondopinion.patient.dao;

import java.time.LocalTime;
import java.util.Date;
import java.util.List;

import org.secondopinion.dao.IDAO;
import org.secondopinion.patient.dto.Appointment;
import org.secondopinion.patient.dto.AppointmentSearchRequest;
import org.secondopinion.patient.dto.SearchSchedule;
import org.secondopinion.patient.dto.ViewAppointments;
import org.secondopinion.request.Response;

public interface AppointmentDAO extends IDAO<Appointment, Long> {

	Response<List<Appointment>> getupcomingAppointments(ViewAppointments viewAppointments);

	Response<List<Appointment>> currentAppointments(ViewAppointments viewAppointments);

	Response<List<Appointment>> previousAppointments(ViewAppointments viewAppointments);
	
	Response<List<Appointment>> retrieveRescheduledAppoitments(ViewAppointments viewAppointments);
	
	Response<List<Appointment>> retrieveCancelledAppoitments(ViewAppointments viewAppointments);
	
	Response<List<Appointment>> searchAppointments(Long userId, SearchSchedule search);
	
	Response<List<Appointment>> getAppointmentFor(Long refrenceEntityId, String appoitmentfor, Integer pageNum);

	Response<List<Appointment>> getByEntityAndPatientId(String appointmentFor, Long doctorId, Long patientId, Integer pageNum);

	Response<List<Appointment>> getAllAppointmentBySearchCritieria(AppointmentSearchRequest appointmentSearchRequest,Long userId);

	List<Appointment> getByPatientAndRoomSID(Long userId, String roomSID);

	List<Appointment> getByAppointmentDateAndFromTime(Date date, LocalTime LocalTime);

	Appointment findAppointmentIdById(Long appointmentId);

	Appointment findAppointmentByReferenceAppointmentId(Long referenceAppointmentId, String appointmentForEnum);

	Response<List<Appointment>> notAttendAppointments(ViewAppointments viewAppointments);

	List<Appointment> findTotelByReferenceAppointmentId(Long refrenceEntityId);

	List<Appointment> getAppointmentForCaretaker(Long userId);

	List<Appointment> currentAppointmentsForCareTaker(List<Long> userIds);

	List<Appointment> upcomeingAppointmentsForCareTaker(List<Long> userIds);

	List<Appointment> getFollowupupdate(Date currentDateOnly, LocalTime currentLocalTime);

}