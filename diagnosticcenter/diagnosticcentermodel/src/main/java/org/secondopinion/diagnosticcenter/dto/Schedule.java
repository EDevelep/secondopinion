package org.secondopinion.diagnosticcenter.dto; 


import java.time.LocalDate;
import java.util.Date;
import java.util.List;

import javax.persistence.Entity; 
import javax.persistence.Table;
import javax.persistence.Transient;

import org.secondopinion.diagnosticcenter.domain.BaseSchedule; 



@Entity 
@Table (name="schedule")
public class Schedule extends BaseSchedule{
	
	private List<Schedulehours> schedulehours;

	@Transient
	public List<Schedulehours> getSchedulehours() {
		return schedulehours;
	}

	public void setSchedulehours(List<Schedulehours> schedulehours) {
		this.schedulehours = schedulehours;
	}

	public static Schedule buildScheduleData(Schedule schedule, Baseschedule baseschedule, Date addedDaysToTheDate,
			LocalDate localDate) {
		schedule.setSubmenuId(baseschedule.getSubMenuId());
		schedule.setPackageId(baseschedule.getPackageId());
		schedule.setDiagnosticCenterUserId(baseschedule.getDiagnosticcenterUserId());
		schedule.setDiagnosticCenterAddressId(baseschedule.getDiagnosticCenterAddressId());
		schedule.setBaseScheduleId(baseschedule.getBasseScheduleId());
		schedule.setScheduleDate(addedDaysToTheDate);
		schedule.setDay(localDate.getDayOfMonth());
		schedule.setMonth(localDate.getMonthValue());
		schedule.setYear(localDate.getYear());
		schedule.setDayOfWeek(localDate.getDayOfWeek().getValue());
		schedule.setDayOfMonth(localDate.getDayOfMonth());
		schedule.setDayOfYear(localDate.getDayOfYear());
		schedule.setScheduleFor(baseschedule.getScheduleFor());
		return schedule;
	}
	
	
}