package org.secondopinion.diagnosticcenter.service;

import java.util.Collection;
import java.util.List;

import org.secondopinion.diagnosticcenter.dto.Baseschedule;
import org.secondopinion.diagnosticcenter.dto.Diagnosticcenter;
import org.secondopinion.diagnosticcenter.dto.Schedule;
import org.secondopinion.diagnosticcenter.dto.ScheduleCriteriaDTO;
import org.secondopinion.diagnosticcenter.dto.Schedulehours;
import org.secondopinion.request.Response;

public interface IDiagnosticCenterScheduleService {

	
	
	void saveBaseschedule(Baseschedule baseschedule);
	void updateBaseschedule(Baseschedule uibaseschedule, Long basescheduleId);
	Response<List<Baseschedule>> getDiagnosticCenterAllBasesSchedules(ScheduleCriteriaDTO scheduleCriteriaDTO);
	Response<List<Schedule>> allSchedulesBycriteria(ScheduleCriteriaDTO scheduleCriteriaDTO);
	Baseschedule getBasescheduleById(Long basseScheduleId);
	
	Schedule scheduleDetails(Long scheduleId);
	Diagnosticcenter getDiagnosticCenterBySchedule(Long scheduleId);
	
	void saveSchedule(Schedule schedule);
	void deletSchedule(Long scheduleId);
	void saveScheduleHours(Schedulehours schedulehours);
	void deletSchedulehours(Long schedulehoursId);
	Collection<Schedulehours> scheduleHoursDetailsByScheduleId(Long scheduleId);
	

	
	
	
}