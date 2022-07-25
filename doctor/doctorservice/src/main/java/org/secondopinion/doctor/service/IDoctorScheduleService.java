package org.secondopinion.doctor.service;

import java.util.Collection;
import java.util.List;

import org.secondopinion.doctor.dto.Baseschedule;
import org.secondopinion.doctor.dto.Schedule;
import org.secondopinion.doctor.dto.ScheduleCriteriaDTO;
import org.secondopinion.doctor.dto.Schedulehours;
import org.secondopinion.request.Response;

public interface IDoctorScheduleService {

	//baseschedule
	void saveBaseschedule(Baseschedule baseschedule);
	Baseschedule getBasescheduleById(Long basseScheduleId);
	Response<List<Baseschedule>> getDoctorAllBasesSchedules(Long doctorId, Integer pageNum, Integer maxResults);
	void updateBaseschedule(Baseschedule baseschedule, Long basescheduleId);
	
	//schedule
	Collection<Schedule> getDoctorAllSchedule(Long doctorId);
	Collection<Schedule> allSchedulesBycriteria(ScheduleCriteriaDTO scheduleCriteriaDTO);
	Schedule scheduleDetails(Long scheduleId);
	void deletSchedule(Long scheduleId);
	void saveSchedule(Schedule schedule);
	
	//schedulehours
	Collection<Schedulehours> scheduleHoursDetailsByScheduleId(Long scheduleId);
	void saveScheduleHours(Schedulehours schedulehours);
	void deletSchedulehours(Long schedulehoursId);
	
	
}
