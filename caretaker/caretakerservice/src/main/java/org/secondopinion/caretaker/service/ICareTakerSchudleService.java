package org.secondopinion.caretaker.service;

import java.util.Collection;
import java.util.List;

import org.secondopinion.caretaker.dto.Baseschedule;
import org.secondopinion.caretaker.dto.Schedule;
import org.secondopinion.caretaker.dto.ScheduleCriteriaDTO;
import org.secondopinion.caretaker.dto.Schedulehours;
import org.secondopinion.request.Response;

public interface ICareTakerSchudleService {

	//baseschedule
	void saveBaseschedule(Baseschedule baseschedule);
	Baseschedule getBasescheduleById(Long basseScheduleId);
	Response<List<Baseschedule>> getCaretakerAllBasesSchedules(Long caretakerId, Integer pageNum, Integer maxResults);
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
