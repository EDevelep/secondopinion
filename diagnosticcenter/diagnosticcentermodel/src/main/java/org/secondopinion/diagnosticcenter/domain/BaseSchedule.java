package org.secondopinion.diagnosticcenter.domain; 

import java.util.ArrayList; 
import java.util.List; 
import java.util.Date;

import org.hibernate.validator.constraints.Length;
import org.secondopinion.diagnosticcenter.dto.Schedule; 
import org.secondopinion.domain.BaseDomainObject;
import org.secondopinion.domain.KeyField; 
import org.secondopinion.domain.validation.ValidationMessage; 
import org.secondopinion.utils.ObjectUtil;
import org.secondopinion.utils.StringUtil;
import org.secondopinion.utils.threadlocal.AppThreadLocal;

import javax.persistence.Column; 
import javax.persistence.MappedSuperclass; 
import javax.validation.constraints.NotNull; 
import javax.persistence.GeneratedValue; 
import javax.persistence.GenerationType; 
import javax.persistence.Id; 
import javax.persistence.Transient;
@MappedSuperclass 
public abstract class BaseSchedule extends BaseDomainObject<Long> {

	
	public static final String FIELD_scheduleId = "scheduleId";
	public static final String FIELD_diagnosticCenterAddressId = "diagnosticCenterAddressId";
	public static final String FIELD_submenuId = "submenuId";
	public static final String FIELD_scheduleFor = "scheduleFor";
	public static final String FIELD_diagnosticCenterUserId = "diagnosticCenterUserId";
	public static final String FIELD_packageId = "packageId";
	public static final String FIELD_baseScheduleId = "baseScheduleId";
	public static final String FIELD_scheduleDate = "scheduleDate";
	public static final String FIELD_day = "day";
	public static final String FIELD_month = "month";
	public static final String FIELD_dayOfWeek = "dayOfWeek";
	public static final String FIELD_dayOfYear = "dayOfYear";
	public static final String FIELD_dayOfMonth = "dayOfMonth";
	public static final String FIELD_year = "year";
	public static final String FIELD_active = "active";
	
	private Long scheduleId;
	private Long diagnosticCenterAddressId;
	private Long submenuId;
	private String scheduleFor;
	private Long diagnosticCenterUserId;
	private Long packageId;
	private Long baseScheduleId;
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
	public void setDiagnosticCenterAddressId( Long  _diagnosticCenterAddressId){
		this.diagnosticCenterAddressId = _diagnosticCenterAddressId;
	}
	@NotNull 
	@Column (name="diagnosticCenterAddressId")
	public Long getDiagnosticCenterAddressId(){
		 return this.diagnosticCenterAddressId;
	}
	public void setSubmenuId( Long  _submenuId){
		this.submenuId = _submenuId;
	}
	@Column (name="submenuId")
	public Long getSubmenuId(){
		 return this.submenuId;
	}
	public void setDiagnosticCenterUserId( Long  _diagnosticCenterUserId){
		this.diagnosticCenterUserId = _diagnosticCenterUserId;
	}
	@Column (name="diagnosticCenterUserId")
	public Long getDiagnosticCenterUserId(){
		 return this.diagnosticCenterUserId;
	}
	public void setPackageId( Long  _packageId){
		this.packageId = _packageId;
	}
	@Column (name="packageId")
	public Long getPackageId(){
		 return this.packageId;
	}
	
	public void setScheduleDate( Date  _scheduleDate){
		this.scheduleDate = _scheduleDate;
	}
	@NotNull 
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
	@NotNull 
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
	@NotNull 
	@Column (name="baseScheduleId")
	public Long getBaseScheduleId() {
		return baseScheduleId;
	}
	public void setBaseScheduleId(Long baseScheduleId) {
		this.baseScheduleId = baseScheduleId;
	}
	public void setActive( Character  _active){
		this.active = _active;
	}
	@NotNull 
	@Column (name="active")
	public Character getActive(){
		 return this.active;
	}
	public void setScheduleFor( String  _scheduleFor){
		this.scheduleFor = _scheduleFor;
	}
	@NotNull 
	@Length(max=45)
	@Column (name="scheduleFor")
	public String getScheduleFor(){
		 return this.scheduleFor;
	}

	@Override
	public boolean equals(Object o){
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		BaseSchedule other = (BaseSchedule)o;
		if(
			ObjectUtil.isEqual(getDiagnosticCenterAddressId(), other.getDiagnosticCenterAddressId()) && 
			ObjectUtil.isEqual(getSubmenuId(), other.getSubmenuId()) && 
			ObjectUtil.isEqual(getScheduleFor(), other.getScheduleFor()) && 
			ObjectUtil.isEqual(getDiagnosticCenterUserId(), other.getDiagnosticCenterUserId()) && 
			ObjectUtil.isEqual(getPackageId(), other.getPackageId()) && 
			ObjectUtil.isEqual(getBaseScheduleId(), other.getBaseScheduleId()) && 
			ObjectUtil.isEqual(getScheduleDate(), other.getScheduleDate()) && 
			ObjectUtil.isEqual(getDay(), other.getDay()) && 
			ObjectUtil.isEqual(getMonth(), other.getMonth()) && 
			ObjectUtil.isEqual(getDayOfWeek(), other.getDayOfWeek()) && 
			ObjectUtil.isEqual(getDayOfYear(), other.getDayOfYear()) && 
			ObjectUtil.isEqual(getDayOfMonth(), other.getDayOfMonth()) && 
			ObjectUtil.isEqual(getYear(), other.getYear()) && 
			ObjectUtil.isEqual(getActive(), other.getActive()) && 
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
		if(this.diagnosticCenterAddressId == null){
			 list.add(new ValidationMessage("Field " + FIELD_diagnosticCenterAddressId+  " cannot be null"));
		}

		if(this.baseScheduleId == null){
			 list.add(new ValidationMessage("Field " + FIELD_baseScheduleId+  " cannot be null"));
		}

		if( StringUtil.isNullOrEmpty(this.scheduleFor)){
			 list.add(new ValidationMessage("Field " + FIELD_scheduleFor+  " cannot be null"));
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

		if(this.dayOfYear == null){
			 list.add(new ValidationMessage("Field " + FIELD_dayOfYear+  " cannot be null"));
		}

		if(this.dayOfMonth == null){
			 list.add(new ValidationMessage("Field " + FIELD_dayOfMonth+  " cannot be null"));
		}

		if(this.year == null){
			 list.add(new ValidationMessage("Field " + FIELD_year+  " cannot be null"));
		}

		if(this.active == null){
			 list.add(new ValidationMessage("Field " + FIELD_active+  " cannot be null"));
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
		}
		this.lastUpdatedBy = AppThreadLocal.getUserName();
		this.lastUpdatedTs = AppThreadLocal.getRequestStartDate();
	}

	@Override
	public String toString(){
		StringBuilder str = new StringBuilder();
		str.append("scheduleId = " + scheduleId + "\n");
;
		str.append("diagnosticCenterAddressId = " + diagnosticCenterAddressId + "\n");
		str.append("submenuId = " + submenuId + "\n");
		str.append("scheduleFor = " + scheduleFor + "\n");
		str.append("diagnosticCenterUserId = " + diagnosticCenterUserId + "\n");
		str.append("packageId = " + packageId + "\n");
		str.append("basseScheduleId = " + baseScheduleId + "\n");
		str.append("scheduleDate = " + scheduleDate + "\n");
		str.append("day = " + day + "\n");
		str.append("month = " + month + "\n");
		str.append("dayOfWeek = " + dayOfWeek + "\n");
		str.append("dayOfYear = " + dayOfYear + "\n");
		str.append("dayOfMonth = " + dayOfMonth + "\n");
		str.append("year = " + year + "\n");
		str.append("active = " + active + "\n");
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
		schedule.setDiagnosticCenterAddressId(getDiagnosticCenterAddressId());
		schedule.setSubmenuId(getSubmenuId());
		schedule.setScheduleFor(getScheduleFor());
		schedule.setDiagnosticCenterUserId(getDiagnosticCenterUserId());
		schedule.setPackageId(getPackageId());
		schedule.setBaseScheduleId(getBaseScheduleId());
		schedule.setScheduleDate(getScheduleDate());
		schedule.setDay(getDay());
		schedule.setMonth(getMonth());
		schedule.setDayOfWeek(getDayOfWeek());
		schedule.setDayOfYear(getDayOfYear());
		schedule.setDayOfMonth(getDayOfMonth());
		schedule.setYear(getYear());
		schedule.setActive(getActive());
		//afterClone(schedule);
		return schedule;
	}
}