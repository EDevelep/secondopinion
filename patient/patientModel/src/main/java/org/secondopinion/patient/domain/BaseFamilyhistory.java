package org.secondopinion.patient.domain; 

import java.io.Serializable; 
import java.util.ArrayList; 
import java.util.List; 
import java.util.Date; 

import org.secondopinion.utils.threadlocal.AppThreadLocal;
import org.secondopinion.domain.BaseDomainObject;
import org.secondopinion.domain.KeyField; 
import org.secondopinion.domain.validation.ValidationMessage;
import org.secondopinion.patient.dto.Familyhistory;
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
public abstract class BaseFamilyhistory extends BaseDomainObject<Long> {

	public static final String FIELD_familyhistoryid = "familyhistoryid";
	public static final String FIELD_ailment = "ailment";
	public static final String FIELD_relationship = "relationship";
	public static final String FIELD_active = "active";
	public static final String FIELD_userId = "userId";
	public static final String FIELD_creartedby = "creartedBy";
	public static final String FIELD_createdDate = "createdDate";
	public static final String FIELD_lastUpdatedBy = "lastUpdatedBy";
	public static final String FIELD_lastUpdatedTs = "lastUpdatedTs";

	private Long familyhistoryid;
	private String ailment;
	private String relationship;
	private Character active;
	private Long userId;
	
	
	public void setFamilyhistoryid( Long  _familyhistoryid){
		this.familyhistoryid = _familyhistoryid;
	}
	@Id
 	@Column(name = "familyhistoryid") 
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Long getFamilyhistoryid(){
		 return this.familyhistoryid;
	}
	public void setAilment( String  _ailment){
		this.ailment = _ailment;
	}
	@Length(max=450)
	@Column (name="ailment")
	public String getAilment(){
		 return this.ailment;
	}
	public void setRelationship( String  _relationship){
		this.relationship = _relationship;
	}
	@Length(max=250)
	@Column (name="relationship")
	public String getRelationship(){
		 return this.relationship;
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
		BaseFamilyhistory other = (BaseFamilyhistory)o;
		if(
			ObjectUtil.isEqual(getAilment(), other.getAilment()) && 
			ObjectUtil.isEqual(getRelationship(), other.getRelationship()) && 
			ObjectUtil.isEqual(getActive(), other.getActive()) && 
			ObjectUtil.isEqual(getUserId(), other.getUserId()) && 
			ObjectUtil.isEqual(getLastUpdatedBy(), other.getLastUpdatedBy()) && 
			ObjectUtil.isEqual(getLastUpdatedTs(), other.getLastUpdatedTs())){
			return true;
		}
		return false;
	}

	@Override
	public int hashCode(){
		int result = 0;
		result = result + (familyhistoryid!= null ? familyhistoryid.hashCode() : 0);

		return result;
	}

	public void validate(boolean validatePk){
		resetValidationMessages();

		List<ValidationMessage> list = new ArrayList<ValidationMessage>();
		if(validatePk){
		if(this.familyhistoryid == null){
			 list.add(new ValidationMessage("Field " + FIELD_familyhistoryid+  " cannot be null"));
		}

		}
		if((this.ailment != null) && (this.ailment.length()>450)){
			 list.add(new ValidationMessage("Field " + FIELD_ailment+  " cannot be longer than: " + 450));
		}

		if((this.relationship != null) && (this.relationship.length()>250)){
			 list.add(new ValidationMessage("Field " + FIELD_relationship+  " cannot be longer than: " + 250));
		}

		if(this.active == null){
			 list.add(new ValidationMessage("Field " + FIELD_active+  " cannot be null"));
		}

		if(this.userId == null){
			 list.add(new ValidationMessage("Field " + FIELD_userId+  " cannot be null"));
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
		str.append("familyhistoryid = " + familyhistoryid + "\n");
;
		str.append("ailment = " + ailment + "\n");
		str.append("relationship = " + relationship + "\n");
		str.append("active = " + active + "\n");
		str.append("userId = " + userId + "\n");
		str.append("lastUpdatedBy = " + lastUpdatedBy + "\n");
		str.append("lastUpdatedTs = " + lastUpdatedTs + "\n");
		return str.toString();
	}

	@Transient
	@Override
	public final boolean isKeyNull(){
		return (familyhistoryid == null); 
	}

	@Transient
	@Override
	public final List<KeyField> getKeyField(){
		List<KeyField> list = new ArrayList<KeyField>();
		list.add(new KeyField(FIELD_familyhistoryid, getFamilyhistoryid()));
		return list;
	}

	@Transient
	@Override
	public Long getId(){
		return getFamilyhistoryid();
	}

	@Override
	public Object clone() throws CloneNotSupportedException {
		Familyhistory familyhistory = new Familyhistory();
		familyhistory.setFromDB(false);
		familyhistory.setAilment(getAilment());
		familyhistory.setRelationship(getRelationship());
		familyhistory.setActive(getActive());
		familyhistory.setUserId(getUserId());

		//afterClone(familyhistory);
		return familyhistory;
	}
}