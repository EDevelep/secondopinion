package org.secondopinion.diagnosticcenter.dao;


import java.time.LocalDate;
import java.util.Date;
import java.util.List;

import org.secondopinion.dao.IDAO;
import org.secondopinion.diagnosticcenter.dto.Schedule;
import org.secondopinion.diagnosticcenter.dto.ScheduleCriteriaDTO;
import org.secondopinion.request.Response;

public interface ScheduleDAO extends IDAO<Schedule,Long >{


	
	Schedule getByScheduleId(Long scheduleId);

	List<Schedule> findScheduleByBasseScheduleId(Long basseScheduleId);

	Response<List<Schedule>> getAllSchedulesBetweenTheDates(ScheduleCriteriaDTO scheduleCriteriaDTO, Date fromDate, Date toDate);

	Response<List<Schedule>> getByScheduleDate(ScheduleCriteriaDTO scheduleCrtieriaDTO, LocalDate locaDate);

	Response<List<Schedule>> getAllUpcomingSchedules(ScheduleCriteriaDTO scheduleCrtieriaDTO);

	Date findTheMaxDateOfExistingBaseschedule(ScheduleCriteriaDTO scheduleCrtieriaDTO);

}