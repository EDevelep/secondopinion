package org.secondopinion.doctor.dto;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Entity;

import javax.persistence.Table;
import javax.persistence.Transient;

import org.secondopinion.doctor.domain.BaseSchedule;

@Entity
@Table(name = "schedule")
public class Schedule extends BaseSchedule {
	
	//private Doctor doctor;
	
	private List<Schedulehours> morningScheduleHours;
	private List<Schedulehours> noonScheduleHours;
	private List<Schedulehours> eveningScheduleHours;
	
	private List<Schedulehours> scheduleHours;

	
	@Transient
	public List<Schedulehours> getMorningScheduleHours() {
		return morningScheduleHours;
	}
	
	@Transient
	public List<Schedulehours> getNoonScheduleHours() {
		return noonScheduleHours;
	}
	
	@Transient
	public List<Schedulehours> getEveningScheduleHours() {
		return eveningScheduleHours;
	}
	
	@Transient
	public List<Schedulehours> getScheduleHours() {
		return scheduleHours;
	}

	public void setScheduleHours(List<Schedulehours> scheduleHours) {
		if(scheduleHours != null) {
			this.scheduleHours = scheduleHours;
			
			scheduleHours.stream().forEach(n->{
				if(n.getTimeOfDay() == 1) {
					if(morningScheduleHours ==null) {
						morningScheduleHours = new ArrayList<>();
					}
					morningScheduleHours.add(n);
					
				} else if(n.getTimeOfDay() == 2) {
					if(noonScheduleHours ==null) {
						noonScheduleHours = new ArrayList<>();
					}
					noonScheduleHours.add(n);
					
				} else {
					if(eveningScheduleHours ==null) {
						eveningScheduleHours = new ArrayList<>();
					}
					eveningScheduleHours.add(n);
				}
				
			});
		}
	}

	public static Schedule buildScheduleData(Schedule schedule , Baseschedule baseschedule, Date addedDaysToTheDate,  LocalDate localDate) {
		schedule.setDoctorId(baseschedule.getDoctorId());
		schedule.setBasseScheduleId(baseschedule.getBasseScheduleId());
		schedule.setScheduleDate(addedDaysToTheDate);
		schedule.setDay(localDate.getDayOfMonth());
		schedule.setMonth(localDate.getMonthValue());
		schedule.setYear(localDate.getYear());
		schedule.setDayOfWeek(localDate.getDayOfWeek().getValue());
		schedule.setDayOfMonth(localDate.getDayOfMonth());
		schedule.setDayOfYear(localDate.getDayOfYear());
		 schedule.setAssociateEntityId(baseschedule.getAssociateEntityId());
		 schedule.setAssociateEntityType(baseschedule.getAssociateEntityType());
		 schedule.setAssociateEntityName(baseschedule.getAssociateEntityName());
		return schedule;
	}
	
	
}