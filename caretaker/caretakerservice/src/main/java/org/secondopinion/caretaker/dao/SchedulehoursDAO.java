package org.secondopinion.caretaker.dao;

import java.util.Date;
import java.util.List;

import org.secondopinion.caretaker.dto.Schedulehours;
import org.secondopinion.dao.IDAO;


public interface SchedulehoursDAO extends IDAO<Schedulehours,Long >{

	Schedulehours getByStartAndEndTimeAndScheduleId(Date startTime, Date endTime,
			Long scheduleId);


	List<Schedulehours> getCareTakerAllUpcomingScheduleHours(List<Long> scheduleIds, boolean isCurrentDay);


	List<Schedulehours> getByScheduleId(Long scheduleId);

	Schedulehours getBySchedulehoursId(Long schedulehoursId);

}