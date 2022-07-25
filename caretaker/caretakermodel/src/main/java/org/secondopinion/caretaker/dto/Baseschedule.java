package org.secondopinion.caretaker.dto; 


import java.time.LocalDate;
import java.util.Calendar;
import java.util.concurrent.TimeUnit;

import javax.persistence.Entity; 
import javax.persistence.Table;


import org.secondopinion.utils.DateUtil;
import org.secondopinion.utils.StringUtil;
import org.secondopinioncaretaker.domain.BaseBaseschedule; 





@Entity 
@Table (name="baseschedule")
public class Baseschedule extends BaseBaseschedule{

	public static Baseschedule buildNewBasescheduleFromSchedule(Schedule schedule) {
		Baseschedule baseschedule = new Baseschedule();
		String totalTime = null;
		Long minslot = null;
		if(schedule.getScheduleHours() != null) {
			for (Schedulehours schedulehours : schedule.getScheduleHours()) {
				minslot = schedulehours.getToTime().getTime() - schedulehours.getFromTime().getTime();
				String sctotalTime = DateUtil.convertDateFormat(schedulehours.getFromTime(), DateUtil.TIME_FORMAT_HH_MM)
						+ "-" + DateUtil.convertDateFormat(schedulehours.getToTime(), DateUtil.TIME_FORMAT_HH_MM);
				if(StringUtil.isNullOrEmpty(totalTime)) {
					totalTime = sctotalTime;
				} else {
					totalTime = totalTime + ", "  + sctotalTime;
				}
			}
		}

		LocalDate localDate = DateUtil.convertUtilDateToLocalDate(schedule.getScheduleDate());
		Integer dayOfMonth = localDate.getDayOfMonth();
		Integer monthValue = localDate.getMonthValue();
		Integer year = localDate.getYear();

		baseschedule.setCaretakerId((schedule.getCaretakerId()));
		baseschedule.setNumberofmonths(0);
		Calendar calendar = Calendar.getInstance();
		calendar.set(year, monthValue, dayOfMonth);
		if(Calendar.MONDAY == (calendar.get(Calendar.DAY_OF_WEEK))) {
			baseschedule.setMonday(totalTime);
		}
		if(Calendar.MONDAY == (calendar.get(Calendar.DAY_OF_WEEK))) {
			baseschedule.setMonday(totalTime);
		}
		if(Calendar.TUESDAY == (calendar.get(Calendar.DAY_OF_WEEK))) {
			baseschedule.setMonday(totalTime);
		}
		if(Calendar.WEDNESDAY == (calendar.get(Calendar.DAY_OF_WEEK))) {
			baseschedule.setMonday(totalTime);
		}
		if(Calendar.THURSDAY == (calendar.get(Calendar.DAY_OF_WEEK))) {
			baseschedule.setMonday(totalTime);
		}
		if(Calendar.FRIDAY == (calendar.get(Calendar.DAY_OF_WEEK))) {
			baseschedule.setMonday(totalTime);
		}
		if(Calendar.SATURDAY == (calendar.get(Calendar.DAY_OF_WEEK))) {
			baseschedule.setMonday(totalTime);
		}
		if(Calendar.SUNDAY == (calendar.get(Calendar.DAY_OF_WEEK))) {
			baseschedule.setMonday(totalTime);
		}
		long minutes = TimeUnit.MILLISECONDS.toMinutes(minslot);
		baseschedule.setMinSlot(minutes);


		return baseschedule;
	}
}