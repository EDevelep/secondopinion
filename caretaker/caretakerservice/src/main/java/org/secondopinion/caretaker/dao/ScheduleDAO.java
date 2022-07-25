package org.secondopinion.caretaker.dao;


import java.time.LocalDate;
import java.util.Date;
import java.util.List;

import org.secondopinion.caretaker.dto.Schedule;
import org.secondopinion.dao.IDAO;


public interface ScheduleDAO extends IDAO<Schedule,Long >{


	List<Schedule> getCaretakerAllUpcomingSchedules(Long caretakerId);

	List<Schedule> getCaretakerIdAllSchedulesBetweenTheDates(Long caretakerId, Date fromDate, Date toDate);

	List<Schedule> getByCaretakerIdAndScheduleDate(Long caretakerId, LocalDate locaDate);

	List<Schedule> getByCaretakerId(Long caretakerId);

	Schedule getByScheduleId(Long scheduleId);

	List<Schedule> findScheduleByCaretakerId(Long caretakerId);

	Schedule findScheduleByBasseScheduleIdAndDate(Long basseScheduleId, Long caretakerId, LocalDate localDate);

	List<Schedule> findScheduleByBasseScheduleId(Long basseScheduleId);

	Date findTheMaxDateOfExistingBaseschedule(Long basseScheduleId);

}