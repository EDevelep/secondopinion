package org.secondopinion.caretaker.dto;

import java.time.LocalTime;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;


import org.secondopinion.enums.ScheduleStatusEnum;
import org.secondopinion.utils.DateUtil;
import org.secondopinioncaretaker.domain.BaseSchedulehours;

@Entity
@Table(name = "schedulehours")
public class Schedulehours extends BaseSchedulehours {

	private static final LocalTime NOON = LocalTime.parse("12:00");
	private static final LocalTime EVENING = LocalTime.parse("17:00");
	public static final String TIME_FORMAT = "HH:mm";

	public static Schedulehours build(Long scheduleId, LocalTime startLocalTime,
			LocalTime startLocalTimePlus30Minutes) {
		Schedulehours scheduleHours = new Schedulehours();

		scheduleHours.setScheduleId(scheduleId);
		scheduleHours.setActive('Y');
		scheduleHours.setFromTime(DateUtil.convertLocalTimeToDate(startLocalTime));
		scheduleHours.setToTime(DateUtil.convertLocalTimeToDate(startLocalTimePlus30Minutes));
		scheduleHours.setScheduleStatus(ScheduleStatusEnum.AVAILABLE.name());

		 scheduleHours.calcTimeOfDay(startLocalTime);
		return scheduleHours;
	}

	public void calcTimeOfDay(LocalTime startLocalTime) {
		if (startLocalTime.isBefore(NOON)) {
			this.setTimeOfDay(1);
		} else if (startLocalTime.isAfter(EVENING)) {
			this.setTimeOfDay(3);
		} else {
			this.setTimeOfDay(2);
		}
	}

}