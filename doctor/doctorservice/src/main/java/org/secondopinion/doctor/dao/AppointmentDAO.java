package org.secondopinion.doctor.dao;

import java.time.LocalTime;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import org.secondopinion.dao.IDAO;
import org.secondopinion.doctor.dto.Appointment;
import org.secondopinion.doctor.dto.AppointmentSearchRequest;
import org.secondopinion.doctor.dto.DoctorDashBoardDTO;
import org.secondopinion.request.Response;

public interface AppointmentDAO extends IDAO<Appointment, Long> {

	void rejectOtherAppointments(Long appointmentId, Long schedulehoursId);

	Response<List<Appointment>> previousAppointments(Long doctorId,Long patientId,Integer pageNum,Integer maxResult);

	Response<List<Appointment>> currentAppointments(Long doctorId, Integer pageNum,Integer maxResult);

	Response<List<Appointment>> upcomingAppointments(Long doctorId, Integer pageNum,Integer maxResult);

	Response<List<Appointment>> getAllAppointmentBySearchCritieria(AppointmentSearchRequest appointmentSearchRequest);

	Response<List<Appointment>> getBySchedulehours(List<Long> schedulehoursId);

	List<Appointment> getByAppointmentDateAndFromTime(Date date, LocalTime LocalTime);

	Response<List<Appointment>> retrieveCancelledAppoitments(Long doctorId, Integer pageNum,Integer maxResult);

	Response<List<Appointment>> getAppointmentsByDoctorId(Long doctorId);

	Long getTotalPatientsOfDoctor(Long doctorid, String appointmentStatus);

	Response<List<Appointment>> retrieveRescheduledAppoitments(Long doctorId,Integer pageNum,Integer maxResult);

	Response<List<Appointment>> previousAppointments(Long patientId, Long doctorId);

	Appointment findappointmentDetailsById(Long appointmentId);

	List<Appointment> findappointmentByScheduleHoursId( Long scheduleHoursId);

	Collection<DoctorDashBoardDTO> appointmentDetailsForDoctor(Long doctorId, String appointmentFor);

}