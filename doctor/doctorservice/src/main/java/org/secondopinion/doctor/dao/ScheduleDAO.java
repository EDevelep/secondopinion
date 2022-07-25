package org.secondopinion.doctor.dao;


import java.time.LocalDate;
import java.util.Date;
import java.util.List;

import org.secondopinion.dao.IDAO;
import org.secondopinion.doctor.dto.Schedule;

public interface ScheduleDAO extends IDAO<Schedule,Long >{


	List<Schedule> getDoctorAllUpcomingSchedules(Long doctorId);

	List<Schedule> getDoctorAllSchedulesBetweenTheDates(Long doctorId, Date fromDate, Date toDate);

	List<Schedule> getByDoctorIdAndScheduleDate(Long doctorId, LocalDate locaDate);

	List<Schedule> getByDoctorId(Long doctorId);

	Schedule getByScheduleId(Long scheduleId);

	List<Schedule> findScheduleByDoctorId(Long doctorId);

	Schedule findScheduleByBasseScheduleIdAndDate(Long basseScheduleId, Long doctorId, LocalDate localDate);

	List<Schedule> findScheduleByBasseScheduleId(Long basseScheduleId);

	Date findTheMaxDateOfExistingBaseschedule(Long basseScheduleId);

}