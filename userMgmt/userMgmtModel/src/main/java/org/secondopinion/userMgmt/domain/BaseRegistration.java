package org.secondopinion.userMgmt.domain; 

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;

import org.secondopinion.userMgmt.dto.Registration;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.secondopinion.domain.BaseDomainObject;
import org.secondopinion.domain.KeyField;
import org.secondopinion.domain.validation.ValidationMessage;
import org.secondopinion.utils.ObjectUtil;
import org.secondopinion.utils.StringUtil;
import org.secondopinion.utils.threadlocal.AppThreadLocal; 
@SuppressWarnings({ "serial"})
@MappedSuperclass 
@JsonIgnoreProperties(value = { "keyField" })
public abstract class BaseRegistration extends BaseDomainObject<Integer> {

	public static final String FIELD_registrationId = "registrationId";
	public static final String FIELD_companyId = "companyId";
	public static final String FIELD_registrationDate = "registrationDate";
	public static final String FIELD_registrationExpiry = "registrationExpiry";
	public static final String FIELD_numberOfLogins = "numberOfLogins";
	public static final String FIELD_lastUpdatedBy = "lastUpdatedBy";
	public static final String FIELD_lastUpdatedTs = "lastUpdatedTs";

	private Integer registrationId;
	private Integer companyId;
	private Date registrationDate;
	private Date registrationExpiry;
	private Integer numberOfLogins;

	public void setRegistrationId( Integer  _registrationId){
		this.registrationId = _registrationId;
	}
	@Id
// 	@NotNull
	@Column(name = "registrationId") 
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Integer getRegistrationId(){
		 return this.registrationId;
	}
	public void setCompanyId( Integer  _companyId){
		this.companyId = _companyId;
	}
	@NotNull 
	@Column (name="companyId")
	public Integer getCompanyId(){
		 return this.companyId;
	}
	public void setRegistrationDate( Date  _registrationDate){
		this.registrationDate = _registrationDate;
	}
	@Column (name="registrationDate")
	public Date getRegistrationDate(){
		 return this.registrationDate;
	}
	public void setRegistrationExpiry( Date  _registrationExpiry){
		this.registrationExpiry = _registrationExpiry;
	}
	@Column (name="registrationExpiry")
	public Date getRegistrationExpiry(){
		 return this.registrationExpiry;
	}
	public void setNumberOfLogins( Integer  _numberOfLogins){
		this.numberOfLogins = _numberOfLogins;
	}
	@Column (name="numberOfLogins")
	public Integer getNumberOfLogins(){
		 return this.numberOfLogins;
	}

	@Override
	public boolean equals(Object o){
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		BaseRegistration other = (BaseRegistration)o;
		if(
			ObjectUtil.isEqual(getCompanyId(), other.getCompanyId()) && 
			ObjectUtil.isEqual(getRegistrationDate(), other.getRegistrationDate()) && 
			ObjectUtil.isEqual(getRegistrationExpiry(), other.getRegistrationExpiry()) && 
			ObjectUtil.isEqual(getNumberOfLogins(), other.getNumberOfLogins()) && 
			ObjectUtil.isEqual(getLastUpdatedBy(), other.getLastUpdatedBy()) && 
			ObjectUtil.isEqual(getLastUpdatedTs(), other.getLastUpdatedTs())){
			return true;
		}
		return false;
	}

	@Override
	public int hashCode(){
		int result = 0;
		result = result + (registrationId!= null ? registrationId.hashCode() : 0);

		return result;
	}

	public void validate(boolean validatePk){
		resetValidationMessages();

		List<ValidationMessage> list = new ArrayList<ValidationMessage>();
		if(validatePk){
		if(this.registrationId == null){
			 list.add(new ValidationMessage("Field " + FIELD_registrationId+  " cannot be null"));
		}

		}
		if(this.companyId == null){
			 list.add(new ValidationMessage("Field " + FIELD_companyId+  " cannot be null"));
		}

		if(this.isFromDB()){
			if( StringUtil.isNullOrEmpty(this.lastUpdatedBy)){
				 list.add(new ValidationMessage("Field " + FIELD_lastUpdatedBy+  " cannot be null"));
			}
		}
		if(this.isFromDB()){
			if((this.lastUpdatedBy != null) && (this.lastUpdatedBy.length()>255)){
				 list.add(new ValidationMessage("Field " + FIELD_lastUpdatedBy+  " cannot be longer than: " + 255));
			}
		}
		if(this.isFromDB()){
			if(this.lastUpdatedTs == null){
				 list.add(new ValidationMessage("Field " + FIELD_lastUpdatedTs+  " cannot be null"));
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
		str.append("registrationId = " + registrationId + "\n");
;
		str.append("companyId = " + companyId + "\n");
		str.append("registrationDate = " + registrationDate + "\n");
		str.append("registrationExpiry = " + registrationExpiry + "\n");
		str.append("numberOfLogins = " + numberOfLogins + "\n");
		str.append("lastUpdatedBy = " + lastUpdatedBy + "\n");
		str.append("lastUpdatedTs = " + lastUpdatedTs + "\n");
		return str.toString();
	}

	@Transient
	@Override
	public final boolean isKeyNull(){
		return (registrationId == null); 
	}

	@Transient
	@Override
	public final List<KeyField> getKeyField(){
		List<KeyField> list = new ArrayList<KeyField>();
		list.add(new KeyField(FIELD_registrationId, getRegistrationId()));
		return list;
	}

	@Transient
	@Override
	public Integer getId(){
		return getRegistrationId();
	}

	@Override
	public Object clone() throws CloneNotSupportedException {
		Registration registration = new Registration();
		registration.setFromDB(false);
		registration.setCompanyId(getCompanyId());
		registration.setRegistrationDate(getRegistrationDate());
		registration.setRegistrationExpiry(getRegistrationExpiry());
		registration.setNumberOfLogins(getNumberOfLogins());
		//afterClone(registration);
		return registration;
	}
}