package org.secondopinion.patient.domain; 

import java.io.Serializable; 
import java.util.ArrayList; 
import java.util.List; 
import java.util.Date; 
import org.secondopinion.utils.threadlocal.AppThreadLocal;
import org.secondopinion.domain.BaseDomainObject;
import org.secondopinion.domain.KeyField; 
import org.secondopinion.domain.validation.ValidationMessage;
import org.secondopinion.patient.dto.Patientpreference;
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
public abstract class BasePatientpreference extends BaseDomainObject<Long> {

	public static final String FIELD_patientpreferenceId = "patientpreferenceId";
	public static final String FIELD_patientid = "patientid";
	public static final String FIELD_diagnosticcenterid = "diagnosticcenterid";
	public static final String FIELD_active = "active";
	public static final String FIELD_pharmacyid = "pharmacyid";
	public static final String FIELD_clinicid = "clinicid";
	public static final String FIELD_lastUpdatedBy = "lastUpdatedBy";
	public static final String FIELD_lastUpdatedTs = "lastUpdatedTs";

	private Long patientpreferenceId;
	private Long patientid;
	private Long diagnosticcenterid;
	private Character active;
	private Long pharmacyid;
	private Long clinicid;

	public void setPatientpreferenceId( Long  _patientpreferenceId){
		this.patientpreferenceId = _patientpreferenceId;
	}
	@Id
 	@Column(name = "patientpreferenceId") 
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Long getPatientpreferenceId(){
		 return this.patientpreferenceId;
	}
	public void setPatientid( Long  _patientid){
		this.patientid = _patientid;
	}
	@NotNull 
	@Column (name="patientid")
	public Long getPatientid(){
		 return this.patientid;
	}
	public void setDiagnosticcenterid( Long  _diagnosticcenterid){
		this.diagnosticcenterid = _diagnosticcenterid;
	}
	@Column (name="diagnosticcenterid")
	public Long getDiagnosticcenterid(){
		 return this.diagnosticcenterid;
	}
	public void setActive( Character  _active){
		this.active = _active;
	}
	@NotNull 
	@Column (name="active")
	public Character getActive(){
		 return this.active;
	}
	public void setPharmacyid( Long  _pharmacyid){
		this.pharmacyid = _pharmacyid;
	}
	@Column (name="pharmacyid")
	public Long getPharmacyid(){
		 return this.pharmacyid;
	}
	public void setClinicid( Long  _clinicid){
		this.clinicid = _clinicid;
	}
	@Column (name="clinicid")
	public Long getClinicid(){
		 return this.clinicid;
	}
	
	
	@Override
	public boolean equals(Object o){
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		BasePatientpreference other = (BasePatientpreference)o;
		if(
			ObjectUtil.isEqual(getPatientid(), other.getPatientid()) && 
			ObjectUtil.isEqual(getDiagnosticcenterid(), other.getDiagnosticcenterid()) && 
			ObjectUtil.isEqual(getActive(), other.getActive()) && 
			ObjectUtil.isEqual(getPharmacyid(), other.getPharmacyid()) && 
			ObjectUtil.isEqual(getClinicid(), other.getClinicid()) && 
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
		result = result + (patientpreferenceId!= null ? patientpreferenceId.hashCode() : 0);

		return result;
	}

	public void validate(boolean validatePk){
		resetValidationMessages();

		List<ValidationMessage> list = new ArrayList<ValidationMessage>();
		if(validatePk){
		if(this.patientpreferenceId == null){
			 list.add(new ValidationMessage("Field " + FIELD_patientpreferenceId+  " cannot be null"));
		}

		}
		if(this.patientid == null){
			 list.add(new ValidationMessage("Field " + FIELD_patientid+  " cannot be null"));
		}

		if(this.active == null){
			 list.add(new ValidationMessage("Field " + FIELD_active+  " cannot be null"));
		}
		if((this.createdBy != null) && (this.createdBy.length()>255)){
			 list.add(new ValidationMessage("Field " + FIELD_createdBy+  " cannot be longer than: " + 255));
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
		str.append("patientpreferenceId = " + patientpreferenceId + "\n");
;
		str.append("patientid = " + patientid + "\n");
		str.append("diagnosticcenterid = " + diagnosticcenterid + "\n");
		str.append("active = " + active + "\n");
		str.append("pharmacyid = " + pharmacyid + "\n");
		str.append("clinicid = " + clinicid + "\n");
		str.append("createdBy = " + createdBy + "\n");
		str.append("createdDate = " + createdDate + "\n");
		str.append("lastUpdatedBy = " + lastUpdatedBy + "\n");
		str.append("lastUpdatedTs = " + lastUpdatedTs + "\n");
		return str.toString();
	}

	@Transient
	@Override
	public final boolean isKeyNull(){
		return (patientpreferenceId == null); 
	}

	@Transient
	@Override
	public final List<KeyField> getKeyField(){
		List<KeyField> list = new ArrayList<KeyField>();
		list.add(new KeyField(FIELD_patientpreferenceId, getPatientpreferenceId()));
		return list;
	}

	@Transient
	@Override
	public Long getId(){
		return getPatientpreferenceId();
	}

	@Override
	public Object clone() throws CloneNotSupportedException {
		Patientpreference patientpreference = new Patientpreference();
		patientpreference.setFromDB(false);
		patientpreference.setPatientid(getPatientid());
		patientpreference.setDiagnosticcenterid(getDiagnosticcenterid());
		patientpreference.setActive(getActive());
		patientpreference.setPharmacyid(getPharmacyid());
		patientpreference.setCreatedBy(getCreatedBy());
		patientpreference.setCreatedDate(getCreatedDate());
		patientpreference.setClinicid(getClinicid());
		//afterClone(patientpreference);
		return patientpreference;
	}
}