package org.secondopinion.diagnosticcenter.domain; 

import java.util.ArrayList; 
import java.util.List; 
import java.util.Date; 
import org.secondopinion.utils.threadlocal.AppThreadLocal;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonFormat.Shape;

import org.secondopinion.diagnosticcenter.dto.Appointment;
import org.secondopinion.domain.BaseDomainObject;
import org.secondopinion.domain.KeyField; 
import org.secondopinion.domain.validation.ValidationMessage; 
import org.secondopinion.utils.StringUtil; 
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
public abstract class BaseAppointment extends BaseDomainObject<Long> {

	
	
	public static final String FIELD_appointmentId = "appointmentId";
	public static final String FIELD_diagnosticCenterAddressId = "diagnosticCenterAddressId";
	public static final String FIELD_diagnosticCenterName = "diagnosticCenterName";
	public static final String FIELD_patientId = "patientId";
	public static final String FIELD_patientName = "patientName";
	public static final String FIELD_patientAppointmentId = "patientAppointmentId";
	public static final String FIELD_schedulehoursId = "schedulehoursId";
	public static final String FIELD_appointmentDate = "appointmentDate";
	public static final String FIELD_fromTime = "fromTime";
	public static final String FIELD_toTime = "toTime";
	public static final String FIELD_description = "description";
	public static final String FIELD_reason = "reason";
	public static final String FIELD_active = "active";
	public static final String FIELD_appointmentStatus = "appointmentStatus";


	private Long appointmentId;
	private Long diagnosticCenterAddressId;//ref entiy id 
	private String diagnosticCenterName;//ref antity name 
	private Long patientId; //user id
	private String patientName;
	private Long patientAppointmentId; //
	@JsonFormat(pattern = "yyyy-MM-dd", shape = Shape.STRING)
	private Date appointmentDate;
	private Long schedulehoursId;
	@JsonFormat(pattern="HH:mm", shape=Shape.STRING)
	private Date toTime;
	@JsonFormat(pattern="HH:mm", shape=Shape.STRING)
	private Date fromTime;
	private String description;
	private String reason;
	private Character active;
	private String appointmentStatus;

	public void setAppointmentId( Long  _appointmentId){
		this.appointmentId = _appointmentId;
	}
	@Id
 	@Column(name = "appointmentId") 
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Long getAppointmentId(){
		 return this.appointmentId;
	}
	public void setDiagnosticCenterAddressId( Long  _diagnosticCenterAddressId){
		this.diagnosticCenterAddressId = _diagnosticCenterAddressId;
	}
	@NotNull
	@Column (name="diagnosticCenterAddressId")
	public Long getDiagnosticCenterAddressId(){
		 return this.diagnosticCenterAddressId;
	}
	public void setDiagnosticCenterName( String  _diagnosticCenterName){
		this.diagnosticCenterName = _diagnosticCenterName;
	}
	@Length(max=45)
	@NotNull
	@Column (name="diagnosticCenterName")
	public String getDiagnosticCenterName(){
		 return this.diagnosticCenterName;
	}
	public void setPatientAppointmentId( Long  _patientAppointmentId){
		this.patientAppointmentId = _patientAppointmentId;
	}
	public void setPatientId( Long  _patientId){
		this.patientId = _patientId;
	}
	@NotNull
	@Column (name="patientId")
	public Long getPatientId(){
		 return this.patientId;
	}
	public void setPatientName( String  _patientName){
		this.patientName = _patientName;
	}
	@Length(max=45)
	@NotNull
	@Column (name="patientName")
	public String getPatientName(){
		 return this.patientName;
	}
	@Column (name="patientAppointmentId")
	public Long getPatientAppointmentId(){
		 return this.patientAppointmentId;
	}
	public void setSchedulehoursId( Long  _schedulehoursId){
		this.schedulehoursId = _schedulehoursId;
	}
	@NotNull 
	@Column (name="schedulehoursId")
	public Long getSchedulehoursId(){
		 return this.schedulehoursId;
	}
	public void setAppointmentDate( Date  _appointmentDate){
		this.appointmentDate = _appointmentDate;
	}
	@JsonFormat(pattern="yyyy-MM-dd", shape=Shape.STRING)
	@NotNull 
	@Temporal(TemporalType.DATE)
	@Column (name="appointmentDate")
	public Date getAppointmentDate(){
		 return this.appointmentDate;
	}
	public void setFromTime( Date  _fromTime){
		this.fromTime = _fromTime;
	}
	@JsonFormat(pattern="HH:mm:ss", shape=Shape.STRING)
	@NotNull 
	@Temporal(TemporalType.TIME)
	@Column (name="fromTime")
	public Date getFromTime(){
		 return this.fromTime;
	}
	public void setToTime( Date  _toTime){
		this.toTime = _toTime;
	}
	@JsonFormat(pattern="HH:mm:ss", shape=Shape.STRING)
	@NotNull 
	@Temporal(TemporalType.TIME)
	@Column (name="toTime")
	public Date getToTime(){
		 return this.toTime;
	}
	public void setDescription( String  _description){
		this.description = _description;
	}
	@Length(max=2000)
	@Column (name="description")
	public String getDescription(){
		 return this.description;
	}
	public void setReason( String  _reason){
		this.reason = _reason;
	}
	@Length(max=600)
	@Column (name="reason")
	public String getReason(){
		 return this.reason;
	}
	public void setActive( Character  _active){
		this.active = _active;
	}
	@NotNull 
	@Column (name="active")
	public Character getActive(){
		 return this.active;
	}
	public void setAppointmentStatus( String  _appointmentStatus){
		this.appointmentStatus = _appointmentStatus;
	}
	@NotNull
	@Length(max=45)
	@Column (name="appointmentStatus")
	public String getAppointmentStatus(){
		 return this.appointmentStatus;
	}

	@Override
	public boolean equals(Object o){
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		BaseAppointment other = (BaseAppointment)o;
		if(
			ObjectUtil.isEqual(getDiagnosticCenterAddressId(), other.getDiagnosticCenterAddressId()) &&
			ObjectUtil.isEqual(getDiagnosticCenterName(), other.getDiagnosticCenterName()) &&
			ObjectUtil.isEqual(getPatientId(), other.getPatientId()) &&
			ObjectUtil.isEqual(getPatientName(), other.getPatientName()) &&
			ObjectUtil.isEqual(getPatientAppointmentId(), other.getPatientAppointmentId()) && 
			ObjectUtil.isEqual(getSchedulehoursId(), other.getSchedulehoursId()) &&
			ObjectUtil.isEqual(getAppointmentDate(), other.getAppointmentDate()) && 
			ObjectUtil.isEqual(getFromTime(), other.getFromTime()) && 
			ObjectUtil.isEqual(getToTime(), other.getToTime()) && 
			ObjectUtil.isEqual(getAppointmentStatus(), other.getAppointmentStatus()) && 
			ObjectUtil.isEqual(getDescription(), other.getDescription()) && 
			ObjectUtil.isEqual(getReason(), other.getReason()) && 
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
		result = result + (appointmentId!= null ? appointmentId.hashCode() : 0);

		return result;
	}

	public void validate(boolean validatePk){
		resetValidationMessages();

		List<ValidationMessage> list = new ArrayList<ValidationMessage>();
		if(validatePk){
		if(this.appointmentId == null){
			 list.add(new ValidationMessage("Field " + FIELD_appointmentId+  " cannot be null"));
		}

		}
		if(this.diagnosticCenterAddressId == null){
			 list.add(new ValidationMessage("Field " + FIELD_diagnosticCenterAddressId+  " cannot be null"));
		}
		
		if(StringUtil.isNullOrEmpty(this.diagnosticCenterName)){
			 list.add(new ValidationMessage("Field " + FIELD_diagnosticCenterName+  " cannot be null"));
		}
		if((this.diagnosticCenterName != null) && (this.diagnosticCenterName.length()>45)){
			 list.add(new ValidationMessage("Field " + FIELD_diagnosticCenterName+  " cannot be longer than: " + 45));
		}
		if(this.patientId == null){
			 list.add(new ValidationMessage("Field " + FIELD_patientId+  " cannot be null"));
		}
		if(StringUtil.isNullOrEmpty(this.patientName)){
			 list.add(new ValidationMessage("Field " + FIELD_patientName+  " cannot be null"));
		}
		if((this.patientName != null) && (this.patientName.length()>45)){
			 list.add(new ValidationMessage("Field " + FIELD_patientName+  " cannot be longer than: " + 45));
		}
		if( this.schedulehoursId == null){
			 list.add(new ValidationMessage("Field " + FIELD_schedulehoursId+  " cannot be null"));
		}
		if( this.appointmentDate == null){
			 list.add(new ValidationMessage("Field " + FIELD_appointmentDate+  " cannot be null"));
		}
		if( this.fromTime == null){
			 list.add(new ValidationMessage("Field " + FIELD_fromTime+  " cannot be null"));
		}
		if( this.toTime == null){
			 list.add(new ValidationMessage("Field " + FIELD_toTime+  " cannot be null"));
		}
		if((this.description != null) && (this.description.length()>2000)){
			 list.add(new ValidationMessage("Field " + FIELD_description+  " cannot be longer than: " + 2000));
		}

		if((this.reason != null) && (this.reason.length()>600)){
			 list.add(new ValidationMessage("Field " + FIELD_reason+  " cannot be longer than: " + 600));
		}

		

		if(this.active == null){
			 list.add(new ValidationMessage("Field " + FIELD_active+  " cannot be null"));
		}

		if((this.appointmentStatus != null) && (this.appointmentStatus.length()>45)){
			 list.add(new ValidationMessage("Field " + FIELD_appointmentStatus+  " cannot be longer than: " + 45));
		}

	if(this.isFromDB()){
		if((this.lastUpdatedBy != null) && (this.lastUpdatedBy.length()>100)){
			 list.add(new ValidationMessage("Field " + FIELD_lastUpdatedBy+  " cannot be longer than: " + 100));
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
		str.append("appointmentId = " + appointmentId + "\n");
;
		str.append("diagnosticCenterAddressId = " + diagnosticCenterAddressId + "\n");
		str.append("diagnosticCenterName = " + diagnosticCenterName + "\n");
		str.append("patientId = " + patientId + "\n");
		str.append("patientName = " + patientName + "\n");
		str.append("patientAppointmentId = " + patientAppointmentId + "\n");
		str.append("schedulehoursId = " + schedulehoursId + "\n");
		str.append("appointmentDate = " + appointmentDate + "\n");
		str.append("fromTime = " + fromTime + "\n");
		str.append("toTime = " + toTime + "\n");
		str.append("reason = " + reason + "\n");
		str.append("description = " + description + "\n");
		str.append("active = " + active + "\n");
		str.append("appointmentStatus = " + appointmentStatus + "\n");
		str.append("lastUpdatedBy = " + lastUpdatedBy + "\n");
		str.append("lastUpdatedTs = " + lastUpdatedTs + "\n");
		return str.toString();
	}

	@Transient
	@Override
	public final boolean isKeyNull(){
		return (appointmentId == null); 
	}

	@Transient
	@Override
	public final List<KeyField> getKeyField(){
		List<KeyField> list = new ArrayList<KeyField>();
		list.add(new KeyField(FIELD_appointmentId, getAppointmentId()));
		return list;
	}

	@Transient
	@Override
	public Long getId(){
		return getAppointmentId();
	}

	@Override
	public Object clone() throws CloneNotSupportedException {
		Appointment appointment = new Appointment();
		appointment.setFromDB(false);
		appointment.setDiagnosticCenterAddressId(getDiagnosticCenterAddressId());
		appointment.setDiagnosticCenterName(getDiagnosticCenterName());
		appointment.setPatientId(getPatientId());
		appointment.setPatientName(getPatientName());
		appointment.setPatientAppointmentId(getPatientAppointmentId());
		appointment.setSchedulehoursId(getSchedulehoursId());
		appointment.setAppointmentDate(getAppointmentDate());
		appointment.setFromTime(getFromTime());
		appointment.setToTime(getToTime());
		appointment.setDescription(getDescription());
		appointment.setReason(getReason());
		appointment.setActive(getActive());
		appointment.setAppointmentStatus(getAppointmentStatus());
		//afterClone(appointment);
		return appointment;
	}
}