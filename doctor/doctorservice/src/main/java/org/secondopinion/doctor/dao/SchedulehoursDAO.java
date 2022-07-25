package org.secondopinion.doctor.dao;

import java.util.Date;
import java.util.List;

import org.secondopinion.dao.IDAO;
import org.secondopinion.doctor.dto.Schedulehours;

public interface SchedulehoursDAO extends IDAO<Schedulehours,Long >{

	Schedulehours getByStartAndEndTimeAndScheduleId(Date startTime, Date endTime,
			Long scheduleId);


	List<Schedulehours> getDoctorAllUpcomingScheduleHours(List<Long> scheduleIds, boolean isCurrentDay);


	List<Schedulehours> getByScheduleId(Long scheduleId);

	Schedulehours getBySchedulehoursId(Long schedulehoursId);

}