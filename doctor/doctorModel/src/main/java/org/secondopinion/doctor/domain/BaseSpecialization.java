package org.secondopinion.doctor.domain; 

import java.io.Serializable; 
import java.util.ArrayList; 
import java.util.List; 
import java.util.Date;  
import org.secondopinion.utils.threadlocal.AppThreadLocal;
import org.secondopinion.doctor.dto.Specialization;
import org.secondopinion.domain.BaseDomainObject;
import org.secondopinion.domain.KeyField; 
import org.secondopinion.domain.validation.ValidationMessage; 
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
public abstract class BaseSpecialization extends BaseDomainObject<Long> {

	public static final String FIELD_specializationId = "specializationId";
	public static final String FIELD_doctorId = "doctorId";
	public static final String FIELD_active = "active";
	public static final String FIELD_specializations = "specializations";
	public static final String FIELD_educationDetails = "educationDetails";
	
	private Long specializationId;
	private Long doctorId;
	private Character active;
	private String specializations;
	private String educationDetails;
	
	public void setSpecializationId( Long  _specializationId){
		this.specializationId = _specializationId;
	}
	@Id
 	@Column(name = "specializationId") 
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Long getSpecializationId(){
		 return this.specializationId;
	}
	public void setDoctorId( Long  _doctorId){
		this.doctorId = _doctorId;
	}

	@Column (name="doctorId")
	public Long getDoctorId(){
		 return this.doctorId;
	}
	public void setActive( Character  _active){
		this.active = _active;
	}
	@NotNull 
	@Column (name="active")
	public Character getActive(){
		 return this.active;
	}
	public void setSpecializations( String  _specializations){
		this.specializations = _specializations;
	}
	@Length(max=500)
	@Column (name="specializations")
	public String getSpecializations(){
		 return this.specializations;
	}
	public void setEducationDetails( String  _educationDetails){
		this.educationDetails = _educationDetails;
	}
	@Length(max=500)
	@Column (name="educationDetails")
	public String getEducationDetails(){
		 return this.educationDetails;
	}
	
	@Override
	public boolean equals(Object o){
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		BaseSpecialization other = (BaseSpecialization)o;
		if(
			ObjectUtil.isEqual(getDoctorId(), other.getDoctorId()) && 
			ObjectUtil.isEqual(getActive(), other.getActive()) && 
			ObjectUtil.isEqual(getSpecializations(), other.getSpecializations()) && 
			ObjectUtil.isEqual(getEducationDetails(), other.getEducationDetails()) && 
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
		result = result + (specializationId!= null ? specializationId.hashCode() : 0);

		return result;
	}

	public void validate(boolean validatePk){
		resetValidationMessages();

		List<ValidationMessage> list = new ArrayList<ValidationMessage>();
		if(validatePk){
		if(this.specializationId == null){
			 list.add(new ValidationMessage("Field " + FIELD_specializationId+  " cannot be null"));
		}

		}
		/*
		 * if(this.doctorId == null){ list.add(new ValidationMessage("Field " +
		 * FIELD_doctorId+ " cannot be null")); }
		 */
		if(this.active == null){
			 list.add(new ValidationMessage("Field " + FIELD_active+  " cannot be null"));
		}

		if((this.specializations != null) && (this.specializations.length()>500)){
			 list.add(new ValidationMessage("Field " + FIELD_specializations+  " cannot be longer than: " + 500));
		}

		if((this.educationDetails != null) && (this.educationDetails.length()>500)){
			 list.add(new ValidationMessage("Field " + FIELD_educationDetails+  " cannot be longer than: " + 500));
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
		str.append("specializationId = " + specializationId + "\n");
;
		str.append("doctorId = " + doctorId + "\n");
		str.append("active = " + active + "\n");
		str.append("specializations = " + specializations + "\n");
		str.append("educationDetails = " + educationDetails + "\n");
		str.append("createdBy = " + createdBy + "\n");
		str.append("createdDate = " + createdDate + "\n");
		str.append("lastUpdatedBy = " + lastUpdatedBy + "\n");
		str.append("lastUpdatedTs = " + lastUpdatedTs + "\n");
		return str.toString();
	}

	@Transient
	@Override
	public final boolean isKeyNull(){
		return (specializationId == null); 
	}

	@Transient
	@Override
	public final List<KeyField> getKeyField(){
		List<KeyField> list = new ArrayList<KeyField>();
		list.add(new KeyField(FIELD_specializationId, getSpecializationId()));
		return list;
	}

	@Transient
	@Override
	public Long getId(){
		return getSpecializationId();
	}

	@Override
	public Object clone() throws CloneNotSupportedException {
		Specialization specialization = new Specialization();
		specialization.setFromDB(false);
		specialization.setDoctorId(getDoctorId());
		specialization.setActive(getActive());
		specialization.setSpecializations(getSpecializations());
		specialization.setEducationDetails(getEducationDetails());
		specialization.setCreatedDate(getCreatedDate());
		//afterClone(specialization);
		return specialization;
	}
}