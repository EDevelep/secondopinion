package org.secondopinion.diagnosticcenter.dao;

import java.util.Date;
import java.util.List;

import org.secondopinion.dao.IDAO;
import org.secondopinion.diagnosticcenter.dto.Schedulehours;

public interface SchedulehoursDAO extends IDAO<Schedulehours,Long >{

	Schedulehours getByStartAndEndTimeAndScheduleId(Date startTime, Date endTime,
			Long scheduleId);


	
	List<Schedulehours> getAllUpcomingScheduleHours(List<Long> scheduleIds, boolean isCurrentDay);


	List<Schedulehours> getByScheduleId(Long scheduleId);

	Schedulehours getBySchedulehoursId(Long schedulehoursId);



	Schedulehours blockAndReturnScheduleHour(Long scheduleHourId);



	List<Schedulehours> getBySchedulehoursBySchedulehoursId(List<Long> list);

}