package org.secondopinion.patient.domain; 

import java.io.Serializable; 
import java.util.ArrayList; 
import java.util.List; 
import java.util.Date; 
import org.secondopinion.utils.threadlocal.AppThreadLocal;
import org.secondopinion.domain.BaseDomainObject;
import org.secondopinion.domain.KeyField; 
import org.secondopinion.domain.validation.ValidationMessage;
import org.secondopinion.patient.dto.Personalsymptoms;
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
public abstract class BasePersonalsymptoms extends BaseDomainObject<Long> {

	public static final String FIELD_personalsymptomsid = "personalsymptomsid";
	public static final String FIELD_symptomsdesc = "symptomsdesc";
	public static final String FIELD_userId = "userId";
	public static final String FIELD_active = "active";
	public static final String FIELD_symptomselectted = "symptomselectted";
	public static final String FIELD_createdBy = "createdBy";
	public static final String FIELD_createdDate = "createdDate";
	public static final String FIELD_lastUpdatedBy = "lastUpdatedBy";
	public static final String FIELD_lastUpdatedTs = "lastUpdatedTs";

	private Long personalsymptomsid;
	private String symptomsdesc;
	private Long userId;
	private Character active;
	private Boolean symptomselectted;

	public void setPersonalsymptomsid( Long  _personalsymptomsid){
		this.personalsymptomsid = _personalsymptomsid;
	}
	@Id
 	@Column(name = "personalsymptomsid") 
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Long getPersonalsymptomsid(){
		 return this.personalsymptomsid;
	}
	public void setSymptomsdesc( String  _symptomsdesc){
		this.symptomsdesc = _symptomsdesc;
	}
	@Length(max=1000)
	@Column (name="symptomsdesc")
	public String getSymptomsdesc(){
		 return this.symptomsdesc;
	}
	public void setUserId( Long  _userId){
		this.userId = _userId;
	}
	@NotNull 
	@Column (name="userId")
	public Long getUserId(){
		 return this.userId;
	}
	public void setActive( Character  _active){
		this.active = _active;
	}
	@NotNull 
	@Column (name="active")
	public Character getActive(){
		 return this.active;
	}
	public void setSymptomselectted( Boolean  _symptomselectted){
		this.symptomselectted = _symptomselectted;
	}
	@Column (name="symptomselectted")
	public Boolean getSymptomselectted(){
		 return this.symptomselectted;
	}
	
	
	@Override
	public boolean equals(Object o){
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		BasePersonalsymptoms other = (BasePersonalsymptoms)o;
		if(
			ObjectUtil.isEqual(getSymptomsdesc(), other.getSymptomsdesc()) && 
			ObjectUtil.isEqual(getUserId(), other.getUserId()) && 
			ObjectUtil.isEqual(getActive(), other.getActive()) && 
			ObjectUtil.isEqual(getSymptomselectted(), other.getSymptomselectted()) && 
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
		result = result + (personalsymptomsid!= null ? personalsymptomsid.hashCode() : 0);

		return result;
	}

	public void validate(boolean validatePk){
		resetValidationMessages();

		List<ValidationMessage> list = new ArrayList<ValidationMessage>();
		if(validatePk){
		if(this.personalsymptomsid == null){
			 list.add(new ValidationMessage("Field " + FIELD_personalsymptomsid+  " cannot be null"));
		}

		}
		if((this.symptomsdesc != null) && (this.symptomsdesc.length()>1000)){
			 list.add(new ValidationMessage("Field " + FIELD_symptomsdesc+  " cannot be longer than: " + 1000));
		}

		if(this.userId == null){
			 list.add(new ValidationMessage("Field " + FIELD_userId+  " cannot be null"));
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
		str.append("personalsymptomsid = " + personalsymptomsid + "\n");
;
		str.append("symptomsdesc = " + symptomsdesc + "\n");
		str.append("userId = " + userId + "\n");
		str.append("active = " + active + "\n");
		str.append("symptomselectted = " + symptomselectted + "\n");
		str.append("creartedby = " + createdBy + "\n");
		str.append("createdDate = " + createdDate + "\n");
		str.append("lastUpdatedBy = " + lastUpdatedBy + "\n");
		str.append("lastUpdatedTs = " + lastUpdatedTs + "\n");
		return str.toString();
	}

	@Transient
	@Override
	public final boolean isKeyNull(){
		return (personalsymptomsid == null); 
	}

	@Transient
	@Override
	public final List<KeyField> getKeyField(){
		List<KeyField> list = new ArrayList<KeyField>();
		list.add(new KeyField(FIELD_personalsymptomsid, getPersonalsymptomsid()));
		return list;
	}

	@Transient
	@Override
	public Long getId(){
		return getPersonalsymptomsid();
	}

	@Override
	public Object clone() throws CloneNotSupportedException {
		Personalsymptoms personalsymptoms = new Personalsymptoms();
		personalsymptoms.setFromDB(false);
		personalsymptoms.setSymptomsdesc(getSymptomsdesc());
		personalsymptoms.setUserId(getUserId());
		personalsymptoms.setActive(getActive());
		personalsymptoms.setSymptomselectted(getSymptomselectted());
		personalsymptoms.setCreatedBy(getCreatedBy());
		personalsymptoms.setCreatedDate(getCreatedDate());
		//afterClone(personalsymptoms);
		return personalsymptoms;
	}
}