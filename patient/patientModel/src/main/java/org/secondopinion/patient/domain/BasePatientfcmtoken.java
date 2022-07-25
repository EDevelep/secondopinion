package org.secondopinion.patient.domain; 

import java.io.Serializable; 
import java.util.ArrayList; 
import java.util.List; 
import java.util.Date; 
import org.secondopinion.utils.threadlocal.AppThreadLocal;
import org.secondopinion.domain.BaseDomainObject;
import org.secondopinion.domain.KeyField; 
import org.secondopinion.domain.validation.ValidationMessage;
import org.secondopinion.patient.dto.Patientfcmtoken;
import org.secondopinion.utils.StringUtil; 
import org.secondopinion.utils.ObjectUtil; 

import javax.persistence.Column; 
import javax.persistence.MappedSuperclass; 
import javax.validation.constraints.NotNull; 
import javax.persistence.GeneratedValue; 
import javax.persistence.GenerationType; 
import javax.persistence.Id; 
import javax.persistence.Transient;
import org.hibernate.validator.constraints.Length; 
@SuppressWarnings({ "serial"})
@MappedSuperclass 
public abstract class BasePatientfcmtoken extends BaseDomainObject<Long> {

	public static final String FIELD_patientFCMtokenId = "patientFCMtokenId";
	public static final String FIELD_patientid = "patientid";
	public static final String FIELD_androidtoken = "androidtoken";
	public static final String FIELD_browsertoken = "browsertoken";
	public static final String FIELD_active = "active";
	public static final String FIELD_iphonetoken = "iphonetoken";
	public static final String FIELD_createdBy = "createdBy";
	public static final String FIELD_createdDate = "createdDate";
	public static final String FIELD_lastUpdatedBy = "lastUpdatedBy";
	public static final String FIELD_lastUpdatedTs = "lastUpdatedTs";

	private Long patientFCMtokenId;
	private Long patientid;
	private String androidtoken;
	private String browsertoken;
	private Character active;
	private String iphonetoken;
	

	public void setPatientFCMtokenId( Long  _patientFCMtokenId){
		this.patientFCMtokenId = _patientFCMtokenId;
	}
	@Id
 	@Column(name = "patientFCMtokenId") 
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Long getPatientFCMtokenId(){
		 return this.patientFCMtokenId;
	}
	public void setPatientid( Long  _patientid){
		this.patientid = _patientid;
	}
	@Column (name="patientid")
	public Long getPatientid(){
		 return this.patientid;
	}
	public void setAndroidtoken( String  _androidtoken){
		this.androidtoken = _androidtoken;
	}
	@Length(max=1020)
	@Column (name="androidtoken")
	public String getAndroidtoken(){
		 return this.androidtoken;
	}
	public void setBrowsertoken( String  _browsertoken){
		this.browsertoken = _browsertoken;
	}
	@Length(max=1020)
	@Column (name="browsertoken")
	public String getBrowsertoken(){
		 return this.browsertoken;
	}
	public void setActive( Character  _active){
		this.active = _active;
	}
	@NotNull 
	@Column (name="active")
	public Character getActive(){
		 return this.active;
	}
	public void setIphonetoken( String  _iphonetoken){
		this.iphonetoken = _iphonetoken;
	}
	@Length(max=1020)
	@Column (name="iphonetoken")
	public String getIphonetoken(){
		 return this.iphonetoken;
	}


	@Override
	public boolean equals(Object o){
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		BasePatientfcmtoken other = (BasePatientfcmtoken)o;
		if(
			ObjectUtil.isEqual(getPatientid(), other.getPatientid()) && 
			ObjectUtil.isEqual(getAndroidtoken(), other.getAndroidtoken()) && 
			ObjectUtil.isEqual(getBrowsertoken(), other.getBrowsertoken()) && 
			ObjectUtil.isEqual(getActive(), other.getActive()) && 
			ObjectUtil.isEqual(getIphonetoken(), other.getIphonetoken()) && 
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
		result = result + (patientFCMtokenId!= null ? patientFCMtokenId.hashCode() : 0);

		return result;
	}

	public void validate(boolean validatePk){
		resetValidationMessages();

		List<ValidationMessage> list = new ArrayList<ValidationMessage>();
		if(validatePk){
		if(this.patientFCMtokenId == null){
			 list.add(new ValidationMessage("Field " + FIELD_patientFCMtokenId+  " cannot be null"));
		}

		}
		if((this.androidtoken != null) && (this.androidtoken.length()>1020)){
			 list.add(new ValidationMessage("Field " + FIELD_androidtoken+  " cannot be longer than: " + 1020));
		}

		if((this.browsertoken != null) && (this.browsertoken.length()>1020)){
			 list.add(new ValidationMessage("Field " + FIELD_browsertoken+  " cannot be longer than: " + 1020));
		}

		if(this.active == null){
			 list.add(new ValidationMessage("Field " + FIELD_active+  " cannot be null"));
		}

		if((this.iphonetoken != null) && (this.iphonetoken.length()>1020)){
			 list.add(new ValidationMessage("Field " + FIELD_iphonetoken+  " cannot be longer than: " + 1020));
		}

	if(this.isFromDB()){
		if((this.createdBy != null) && (this.createdBy.length()>1020)){
			 list.add(new ValidationMessage("Field " + FIELD_createdBy+  " cannot be longer than: " + 1020));
		}
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
			this.createdBy = AppThreadLocal.getUserName();
		}
		this.lastUpdatedBy = AppThreadLocal.getUserName();
		this.lastUpdatedTs = AppThreadLocal.getRequestStartDate();
	}

	@Override
	public String toString(){
		StringBuilder str = new StringBuilder();
		str.append("patientFCMtokenId = " + patientFCMtokenId + "\n");
;
		str.append("patientid = " + patientid + "\n");
		str.append("androidtoken = " + androidtoken + "\n");
		str.append("browsertoken = " + browsertoken + "\n");
		str.append("active = " + active + "\n");
		str.append("iphonetoken = " + iphonetoken + "\n");
		str.append("createdBy = " + createdBy + "\n");
		str.append("createdDate = " + createdDate + "\n");
		str.append("lastUpdatedBy = " + lastUpdatedBy + "\n");
		str.append("lastUpdatedTs = " + lastUpdatedTs + "\n");
		return str.toString();
	}

	@Transient
	@Override
	public final boolean isKeyNull(){
		return (patientFCMtokenId == null); 
	}

	@Transient
	@Override
	public final List<KeyField> getKeyField(){
		List<KeyField> list = new ArrayList<KeyField>();
		list.add(new KeyField(FIELD_patientFCMtokenId, getPatientFCMtokenId()));
		return list;
	}

	@Transient
	@Override
	public Long getId(){
		return getPatientFCMtokenId();
	}

	@Override
	public Object clone() throws CloneNotSupportedException {
		Patientfcmtoken patientfcmtoken = new Patientfcmtoken();
		patientfcmtoken.setFromDB(false);
		patientfcmtoken.setPatientid(getPatientid());
		patientfcmtoken.setAndroidtoken(getAndroidtoken());
		patientfcmtoken.setBrowsertoken(getBrowsertoken());
		patientfcmtoken.setActive(getActive());
		patientfcmtoken.setIphonetoken(getIphonetoken());
		patientfcmtoken.setCreatedDate(getCreatedDate());
		//afterClone(patientfcmtoken);
		return patientfcmtoken;
	}
}