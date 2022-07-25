package org.secondopinion.patient.domain; 

import java.io.Serializable; 
import java.util.ArrayList; 
import java.util.List; 
import java.util.Date; 
import org.secondopinion.utils.threadlocal.AppThreadLocal;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonFormat.Shape;

import org.secondopinion.domain.BaseDomainObject;
import org.secondopinion.domain.KeyField; 
import org.secondopinion.domain.validation.ValidationMessage;
import org.secondopinion.patient.dto.Notificationalerts;
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
@SuppressWarnings({ "serial"})
@MappedSuperclass 
public abstract class BaseNotificationalerts extends BaseDomainObject<Long> {

	public static final String FIELD_notificationalertsId = "notificationalertsId";
	public static final String FIELD_patientId = "patientId";
	public static final String FIELD_objectkey = "objectkey";
	public static final String FIELD_objecttype = "objecttype";
	public static final String FIELD_reason = "reason";
	public static final String FIELD_active = "active";
	public static final String FIELD_viewed = "viewed";
	public static final String FIELD_expirydate = "expirydate";
	public static final String FIELD_createdBy = "createdBy";
	public static final String FIELD_createdDate = "createdDate";
	public static final String FIELD_lastUpdatedBy = "lastUpdatedBy";
	public static final String FIELD_lastUpdatedTs = "lastUpdatedTs";

	private Long notificationalertsId;
	private Long patientId;
	private Long objectkey;
	private String objecttype;
	private String reason;
	private Character active;
	private Character viewed;
	//@JsonFormat(pattern="HH:mm:ss", shape=Shape.STRING)
	@JsonFormat(pattern = "dd-MM-yyyy", shape = Shape.STRING)
	private Date expirydate;
	

	public void setNotificationalertsId( Long  _notificationalertsId){
		this.notificationalertsId = _notificationalertsId;
	}
	@Id
 	@Column(name = "notificationalertsId") 
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Long getNotificationalertsId(){
		 return this.notificationalertsId;
	}
	public void setPatientId( Long  _patientId){
		this.patientId = _patientId;
	}
	@NotNull 
	@Column (name="patientId")
	public Long getPatientId(){
		 return this.patientId;
	}
	public void setObjectkey( Long  _objectkey){
		this.objectkey = _objectkey;
	}
	@NotNull 
	@Column (name="objectkey")
	public Long getObjectkey(){
		 return this.objectkey;
	}
	public void setObjecttype( String  _objecttype){
		this.objecttype = _objecttype;
	}
	@Length(max=345)
	@Column (name="objecttype")
	public String getObjecttype(){
		 return this.objecttype;
	}
	public void setReason( String  _reason){
		this.reason = _reason;
	}
	@Length(max=21845)
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
	public void setViewed( Character  _viewed){
		this.viewed = _viewed;
	}
	@Column (name="viewed")
	public Character getViewed(){
		 return this.viewed;
	}
	public void setExpirydate( Date  _expirydate){
		this.expirydate = _expirydate;
	}

	@JsonFormat(pattern = "dd-MM-yyyy", shape = Shape.STRING)
	@Temporal(TemporalType.DATE)
	@Column (name="expirydate")
	public Date getExpirydate(){
		 return this.expirydate;
	}
	

	@Override
	public boolean equals(Object o){
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		BaseNotificationalerts other = (BaseNotificationalerts)o;
		if(
			ObjectUtil.isEqual(getPatientId(), other.getPatientId()) && 
			ObjectUtil.isEqual(getObjectkey(), other.getObjectkey()) && 
			ObjectUtil.isEqual(getObjecttype(), other.getObjecttype()) && 
			ObjectUtil.isEqual(getReason(), other.getReason()) && 
			ObjectUtil.isEqual(getActive(), other.getActive()) && 
			ObjectUtil.isEqual(getViewed(), other.getViewed()) && 
			ObjectUtil.isEqual(getExpirydate(), other.getExpirydate()) && 
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
		result = result + (notificationalertsId!= null ? notificationalertsId.hashCode() : 0);

		return result;
	}

	public void validate(boolean validatePk){
		resetValidationMessages();

		List<ValidationMessage> list = new ArrayList<ValidationMessage>();
		if(validatePk){
		if(this.notificationalertsId == null){
			 list.add(new ValidationMessage("Field " + FIELD_notificationalertsId+  " cannot be null"));
		}

		}
		if(this.patientId == null){
			 list.add(new ValidationMessage("Field " + FIELD_patientId+  " cannot be null"));
		}

		if(this.objectkey == null){
			 list.add(new ValidationMessage("Field " + FIELD_objectkey+  " cannot be null"));
		}

		if((this.objecttype != null) && (this.objecttype.length()>345)){
			 list.add(new ValidationMessage("Field " + FIELD_objecttype+  " cannot be longer than: " + 45));
		}

		if((this.reason != null) && (this.reason.length()>21845)){
			 list.add(new ValidationMessage("Field " + FIELD_reason+  " cannot be longer than: " + 21845));
		}

		if(this.active == null){
			 list.add(new ValidationMessage("Field " + FIELD_active+  " cannot be null"));
		}

		if((this.createdBy != null) && (this.createdBy.length()>45)){
			 list.add(new ValidationMessage("Field " + FIELD_createdBy+  " cannot be longer than: " + 45));
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
		str.append("notificationalertsId = " + notificationalertsId + "\n");
;
		str.append("patientId = " + patientId + "\n");
		str.append("objectkey = " + objectkey + "\n");
		str.append("objecttype = " + objecttype + "\n");
		str.append("reason = " + reason + "\n");
		str.append("active = " + active + "\n");
		str.append("viewed = " + viewed + "\n");
		str.append("expirydate = " + expirydate + "\n");
		str.append("creartedby = " + createdBy + "\n");
		str.append("createdDate = " + createdDate + "\n");
		str.append("lastUpdatedBy = " + lastUpdatedBy + "\n");
		str.append("lastUpdatedTs = " + lastUpdatedTs + "\n");
		return str.toString();
	}

	@Transient
	@Override
	public final boolean isKeyNull(){
		return (notificationalertsId == null); 
	}

	@Transient
	@Override
	public final List<KeyField> getKeyField(){
		List<KeyField> list = new ArrayList<KeyField>();
		list.add(new KeyField(FIELD_notificationalertsId, getNotificationalertsId()));
		return list;
	}

	@Transient
	@Override
	public Long getId(){
		return getNotificationalertsId();
	}

	@Override
	public Object clone() throws CloneNotSupportedException {
		Notificationalerts notificationalerts = new Notificationalerts();
		notificationalerts.setFromDB(false);
		notificationalerts.setPatientId(getPatientId());
		notificationalerts.setObjectkey(getObjectkey());
		notificationalerts.setObjecttype(getObjecttype());
		notificationalerts.setReason(getReason());
		notificationalerts.setActive(getActive());
		notificationalerts.setViewed(getViewed());
		notificationalerts.setExpirydate(getExpirydate());
		notificationalerts.setCreatedBy(getCreatedBy());
		notificationalerts.setCreatedDate(getCreatedDate());
		//afterClone(notificationalerts);
		return notificationalerts;
	}
}