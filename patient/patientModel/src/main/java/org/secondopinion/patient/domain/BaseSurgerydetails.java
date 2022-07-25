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
import org.secondopinion.patient.dto.Surgerydetails;
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
public abstract class BaseSurgerydetails extends BaseDomainObject<Long> {

	public static final String FIELD_surgerydetailsid = "surgerydetailsid";
	public static final String FIELD_date = "date";
	public static final String FIELD_name = "name";
	public static final String FIELD_surgerlookupid = "surgerlookupid";
	public static final String FIELD_active = "active";
	public static final String FIELD_userId = "userId";
	public static final String FIELD_createdBy = "createdBy";
	public static final String FIELD_createdDate = "createdDate";
	public static final String FIELD_lastUpdatedBy = "lastUpdatedBy";
	public static final String FIELD_lastUpdatedTs = "lastUpdatedTs";

	private Long surgerydetailsid;
	private Date date;
	private String name;
	private Long surgerlookupid;
	private Character active;
	private Long userId;

	public void setSurgerydetailsid( Long  _surgerydetailsid){
		this.surgerydetailsid = _surgerydetailsid;
	}
	@Id
 	@Column(name = "surgerydetailsid") 
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Long getSurgerydetailsid(){
		 return this.surgerydetailsid;
	}
	public void setDate( Date  _date){
		this.date = _date;
	}
	@JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSXXX", shape = Shape.STRING)
	@Column (name="date")
	public Date getDate(){
		 return this.date;
	}
	public void setName( String  _name){
		this.name = _name;
	}
	@Length(max=250)
	@Column (name="name")
	public String getName(){
		 return this.name;
	}
	public void setSurgerlookupid( Long  _surgerlookupid){
		this.surgerlookupid = _surgerlookupid;
	}
	@NotNull 
	@Column (name="surgerlookupid")
	public Long getSurgerlookupid(){
		 return this.surgerlookupid;
	}
	public void setActive( Character  _active){
		this.active = _active;
	}
	@NotNull 
	@Column (name="active")
	public Character getActive(){
		 return this.active;
	}
	public void setUserId( Long  _userId){
		this.userId = _userId;
	}
	@NotNull 
	@Column (name="userId")
	public Long getUserId(){
		 return this.userId;
	}


	@Override
	public boolean equals(Object o){
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		BaseSurgerydetails other = (BaseSurgerydetails)o;
		if(
			ObjectUtil.isEqual(getDate(), other.getDate()) && 
			ObjectUtil.isEqual(getName(), other.getName()) && 
			ObjectUtil.isEqual(getSurgerlookupid(), other.getSurgerlookupid()) && 
			ObjectUtil.isEqual(getActive(), other.getActive()) && 
			ObjectUtil.isEqual(getUserId(), other.getUserId()) && 
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
		result = result + (surgerydetailsid!= null ? surgerydetailsid.hashCode() : 0);

		return result;
	}

	public void validate(boolean validatePk){
		resetValidationMessages();

		List<ValidationMessage> list = new ArrayList<ValidationMessage>();
		if(validatePk){
		if(this.surgerydetailsid == null){
			 list.add(new ValidationMessage("Field " + FIELD_surgerydetailsid+  " cannot be null"));
		}

		}
		if((this.name != null) && (this.name.length()>250)){
			 list.add(new ValidationMessage("Field " + FIELD_name+  " cannot be longer than: " + 250));
		}

		if(this.surgerlookupid == null){
			 list.add(new ValidationMessage("Field " + FIELD_surgerlookupid+  " cannot be null"));
		}

		if(this.active == null){
			 list.add(new ValidationMessage("Field " + FIELD_active+  " cannot be null"));
		}

		if(this.userId == null){
			 list.add(new ValidationMessage("Field " + FIELD_userId+  " cannot be null"));
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
		str.append("surgerydetailsid = " + surgerydetailsid + "\n");
;
		str.append("date = " + date + "\n");
		str.append("name = " + name + "\n");
		str.append("surgerlookupid = " + surgerlookupid + "\n");
		str.append("active = " + active + "\n");
		str.append("userId = " + userId + "\n");
		str.append("creartedby = " + createdBy + "\n");
		str.append("createdDate = " + createdDate + "\n");
		str.append("lastUpdatedBy = " + lastUpdatedBy + "\n");
		str.append("lastUpdatedTs = " + lastUpdatedTs + "\n");
		return str.toString();
	}

	@Transient
	@Override
	public final boolean isKeyNull(){
		return (surgerydetailsid == null); 
	}

	@Transient
	@Override
	public final List<KeyField> getKeyField(){
		List<KeyField> list = new ArrayList<KeyField>();
		list.add(new KeyField(FIELD_surgerydetailsid, getSurgerydetailsid()));
		return list;
	}

	@Transient
	@Override
	public Long getId(){
		return getSurgerydetailsid();
	}

	@Override
	public Object clone() throws CloneNotSupportedException {
		Surgerydetails surgerydetails = new Surgerydetails();
		surgerydetails.setFromDB(false);
		surgerydetails.setDate(getDate());
		surgerydetails.setName(getName());
		surgerydetails.setSurgerlookupid(getSurgerlookupid());
		surgerydetails.setActive(getActive());
		surgerydetails.setUserId(getUserId());
		surgerydetails.setCreatedBy(getCreatedBy());
		surgerydetails.setCreatedDate(getCreatedDate());
		//afterClone(surgerydetails);
		return surgerydetails;
	}
}