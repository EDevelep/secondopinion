package org.secondopinioncaretaker.domain; 

import java.util.ArrayList; 
import java.util.List; 
import java.util.Date; 
import org.secondopinion.utils.threadlocal.AppThreadLocal;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonFormat.Shape;

import org.secondopinion.caretaker.dto.Schedule;
import org.secondopinion.domain.BaseDomainObject;
import org.secondopinion.domain.KeyField; 
import org.secondopinion.domain.validation.ValidationMessage; 
import org.secondopinion.utils.ObjectUtil; 

import javax.persistence.Column; 
import javax.persistence.MappedSuperclass;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull; 
import javax.persistence.GeneratedValue; 
import javax.persistence.GenerationType; 
import javax.persistence.Id; 
import javax.persistence.Transient;
import org.hibernate.validator.constraints.Length; 
@MappedSuperclass 
public abstract class BaseSchedule extends BaseDomainObject<Long> {

	public static final String FIELD_scheduleId = "scheduleId";
	public static final String FIELD_caretakerId = "caretakerId";
	public static final String FIELD_basseScheduleId = "basseScheduleId";
	public static final String FIELD_active = "active";
	public static final String FIELD_scheduleDate = "scheduleDate";
	public static final String FIELD_day = "day";
	public static final String FIELD_month = "month";
	public static final String FIELD_dayOfWeek = "dayOfWeek";
	public static final String FIELD_dayOfYear = "dayOfYear";
	public static final String FIELD_dayOfMonth = "dayOfMonth";
	public static final String FIELD_year = "year";
	
	private Long scheduleId;
	private Long caretakerId;
	private Long basseScheduleId;
	@JsonFormat(pattern="yyyy-MM-dd", shape=Shape.STRING)
	private Date scheduleDate;
	private Integer day;
	private Integer month;
	private Integer dayOfWeek;
	private Integer dayOfYear;
	private Integer dayOfMonth;
	private Integer year;
	private Character active;
	
	public void setScheduleId( Long  _scheduleId){
		this.scheduleId = _scheduleId;
	}
	@Id
 	@Column(name = "scheduleId") 
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Long getScheduleId(){
		 return this.scheduleId;
	}
	public void setCaretakerId( Long  _caretakerId){
		this.caretakerId = _caretakerId;
	}
	@NotNull 
	@Column (name="caretakerId")
	public Long getCaretakerId(){
		 return this.caretakerId;
	}
	public void setActive( Character  _active){
		this.active = _active;
	}
	public void setBasseScheduleId( Long  _basseScheduleId){
		this.basseScheduleId = _basseScheduleId;
	}
	@NotNull
 	@Column(name = "basseScheduleId") 
	public Long getBasseScheduleId(){
		 return this.basseScheduleId;
	}
	@NotNull 
	@Column (name="active")
	public Character getActive(){
		 return this.active;
	}
	public void setScheduleDate( Date  _scheduleDate){
		this.scheduleDate = _scheduleDate;
	}
	@JsonFormat(pattern="yyyy-MM-dd", shape=Shape.STRING)
	@NotNull 
	@Temporal(TemporalType.DATE)
	@Column (name="scheduleDate")
	public Date getScheduleDate(){
		 return this.scheduleDate;
	}
	public void setDay( Integer  _day){
		this.day = _day;
	}
	@NotNull 
	@Column (name="day")
	public Integer getDay(){
		 return this.day;
	}
	public void setMonth( Integer  _month){
		this.month = _month;
	}
	@NotNull 
	@Column (name="month")
	public Integer getMonth(){
		 return this.month;
	}
	public void setDayOfWeek( Integer  _dayOfWeek){
		this.dayOfWeek = _dayOfWeek;
	}
	@NotNull 
	@Column (name="dayOfWeek")
	public Integer getDayOfWeek(){
		 return this.dayOfWeek;
	}
	public void setDayOfYear( Integer  _dayOfYear){
		this.dayOfYear = _dayOfYear;
	}
	@Column (name="dayOfYear")
	public Integer getDayOfYear(){
		 return this.dayOfYear;
	}
	public void setDayOfMonth( Integer  _dayOfMonth){
		this.dayOfMonth = _dayOfMonth;
	}
	@NotNull 
	@Column (name="dayOfMonth")
	public Integer getDayOfMonth(){
		 return this.dayOfMonth;
	}
	public void setYear( Integer  _year){
		this.year = _year;
	}
	@NotNull 
	@Column (name="year")
	public Integer getYear(){
		 return this.year;
	}
	
	@Override
	public boolean equals(Object o){
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		BaseSchedule other = (BaseSchedule)o;
		if(
			ObjectUtil.isEqual(getCaretakerId(), other.getCaretakerId()) && 
			ObjectUtil.isEqual(getBasseScheduleId(), other.getBasseScheduleId()) && 
			ObjectUtil.isEqual(getActive(), other.getActive()) && 
			ObjectUtil.isEqual(getScheduleDate(), other.getScheduleDate()) && 
			ObjectUtil.isEqual(getDay(), other.getDay()) && 
			ObjectUtil.isEqual(getMonth(), other.getMonth()) && 
			ObjectUtil.isEqual(getDayOfWeek(), other.getDayOfWeek()) && 
			ObjectUtil.isEqual(getDayOfYear(), other.getDayOfYear()) && 
			ObjectUtil.isEqual(getDayOfMonth(), other.getDayOfMonth()) && 
			ObjectUtil.isEqual(getYear(), other.getYear()) && 
			ObjectUtil.isEqual(getCreatedBy(), other.getCreatedBy()) && 
			ObjectUtil.isEqual(getCreatedDate(), other.getCreatedDate()) && 
			ObjectUtil.isEqual(getLastUpdatedBy(), other.getLastUpdatedBy()) && 
			ObjectUtil.isEqual(getLastUpdatedTs(), other.getLastUpdatedTs())){
			return true;
		}
		return false;
	}

	@Override
	public int hashCode(){
		int result = 0;
		result = result + (scheduleId!= null ? scheduleId.hashCode() : 0);

		return result;
	}

	public void validate(boolean validatePk){
		resetValidationMessages();

		List<ValidationMessage> list = new ArrayList<ValidationMessage>();
		if(validatePk){
		if(this.scheduleId == null){
			 list.add(new ValidationMessage("Field " + FIELD_scheduleId+  " cannot be null"));
		}

		}
		if(this.caretakerId == null){
			 list.add(new ValidationMessage("Field " + FIELD_caretakerId+  " cannot be null"));
		}
		if(this.basseScheduleId == null){
			 list.add(new ValidationMessage("Field " + FIELD_basseScheduleId+  " cannot be null"));
		}
		if(this.active == null){
			 list.add(new ValidationMessage("Field " + FIELD_active+  " cannot be null"));
		}

		if(this.scheduleDate == null){
			 list.add(new ValidationMessage("Field " + FIELD_scheduleDate+  " cannot be null"));
		}

		if(this.day == null){
			 list.add(new ValidationMessage("Field " + FIELD_day+  " cannot be null"));
		}

		if(this.month == null){
			 list.add(new ValidationMessage("Field " + FIELD_month+  " cannot be null"));
		}

		if(this.dayOfWeek == null){
			 list.add(new ValidationMessage("Field " + FIELD_dayOfWeek+  " cannot be null"));
		}

		if(this.dayOfMonth == null){
			 list.add(new ValidationMessage("Field " + FIELD_dayOfMonth+  " cannot be null"));
		}

		if(this.year == null){
			 list.add(new ValidationMessage("Field " + FIELD_year+  " cannot be null"));
		}

	if(this.isFromDB()){
		if((this.createdBy != null) && (this.createdBy.length()>45)){
			 list.add(new ValidationMessage("Field " + FIELD_createdBy+  " cannot be longer than: " + 45));
		}
	}
	if(this.isFromDB()){
		if((this.lastUpdatedBy != null) && (this.lastUpdatedBy.length()>45)){
			 list.add(new ValidationMessage("Field " + FIELD_lastUpdatedBy+  " cannot be longer than: " + 45));
		}
	}
		if(list.size()>0)setHasValidationErrors(true);

		setValidationMessages(list);
		
	}

	@Override
	public final void setAuditFields() {
		if(!isFromDB()){
			this.createdBy = AppThreadLocal.getUserName();
		}
		this.lastUpdatedBy = AppThreadLocal.getUserName();
		this.lastUpdatedTs = AppThreadLocal.getRequestStartDate();
	}

	@Override
	public String toString(){
		StringBuilder str = new StringBuilder();
		str.append("scheduleId = " + scheduleId + "\n");
;
		str.append("caretakerId = " + caretakerId + "\n");
		str.append("basseScheduleId = " + basseScheduleId + "\n");
		str.append("active = " + active + "\n");
		str.append("scheduleDate = " + scheduleDate + "\n");
		str.append("day = " + day + "\n");
		str.append("month = " + month + "\n");
		str.append("dayOfWeek = " + dayOfWeek + "\n");
		str.append("dayOfYear = " + dayOfYear + "\n");
		str.append("dayOfMonth = " + dayOfMonth + "\n");
		str.append("year = " + year + "\n");
		str.append("createdBy = " + createdBy + "\n");
		str.append("createdDate = " + createdDate + "\n");
		str.append("lastUpdatedBy = " + lastUpdatedBy + "\n");
		str.append("lastUpdatedTs = " + lastUpdatedTs + "\n");
		return str.toString();
	}

	@Transient
	@Override
	public final boolean isKeyNull(){
		return (scheduleId == null); 
	}

	@Transient
	@Override
	public final List<KeyField> getKeyField(){
		List<KeyField> list = new ArrayList<KeyField>();
		list.add(new KeyField(FIELD_scheduleId, getScheduleId()));
		return list;
	}

	@Transient
	@Override
	public Long getId(){
		return getScheduleId();
	}

	@Override
	public Object clone() throws CloneNotSupportedException {
		Schedule schedule = new Schedule();
		schedule.setFromDB(false);
		schedule.setCaretakerId(getCaretakerId());
		schedule.setBasseScheduleId(getBasseScheduleId());
		schedule.setActive(getActive());
		schedule.setScheduleDate(getScheduleDate());
		schedule.setDay(getDay());
		schedule.setMonth(getMonth());
		schedule.setDayOfWeek(getDayOfWeek());
		schedule.setDayOfYear(getDayOfYear());
		schedule.setDayOfMonth(getDayOfMonth());
		schedule.setYear(getYear());
		schedule.setCreatedDate(getCreatedDate());
		//afterClone(schedule);
		return schedule;
	}
}