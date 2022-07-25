package org.secondopinion.caretaker.dao;

import java.time.LocalTime;
import java.util.Date;
import java.util.List;

import org.secondopinion.caretaker.dto.Appointment;
import org.secondopinion.caretaker.dto.AppointmentSearchRequest;
import org.secondopinion.dao.IDAO;


import org.secondopinion.request.Response;

public interface AppointmentDAO extends IDAO<Appointment, Long> {

	void rejectOtherAppointments(Long appointmentId, Long schedulehoursId);

	Response<List<Appointment>> previousAppointments(Long caretakerId,Long patientId,Integer pageNum,Integer maxResult);

	Response<List<Appointment>> currentAppointments(Long caretakerId, Integer pageNum,Integer maxResult);

	Response<List<Appointment>> upcomingAppointments(Long caretakerId, Integer pageNum,Integer maxResult);

	Response<List<Appointment>> getAllAppointmentBySearchCritieria(AppointmentSearchRequest appointmentSearchRequest);

	Response<List<Appointment>> getBySchedulehours(List<Long> schedulehoursId);

	List<Appointment> getByAppointmentDateAndFromTime(Date date, LocalTime LocalTime);

	Response<List<Appointment>> retrieveCancelledAppoitments(Long caretakerId, Integer pageNum,Integer maxResult);

	Response<List<Appointment>> getAppointmentsByCaretakerId(Long caretakerId);

	Long getTotalPatientsOfDoctor(Long caretakerId, String appointmentStatus);

	Response<List<Appointment>> retrieveRescheduledAppoitments(Long caretakerId,Integer pageNum,Integer maxResult);

	Response<List<Appointment>> previousAppointments(Long patientId, Long caretakerId);

	Appointment findappointmentDetailsById(Long appointmentId);

	List<Appointment> findappointmentByScheduleHoursId( Long scheduleHoursId);

}