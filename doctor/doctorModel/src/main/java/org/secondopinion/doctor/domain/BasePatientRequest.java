package org.secondopinion.doctor.domain; 

import java.io.Serializable; 
import java.util.ArrayList; 
import java.util.List; 
import java.util.Date; 
import org.secondopinion.utils.threadlocal.AppThreadLocal;
import org.secondopinion.doctor.dto.PatientRequest;
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
public abstract class BasePatientRequest extends BaseDomainObject<Long> {

	public static final String FIELD_patientRequestId = "patientRequestId";
	public static final String FIELD_doctorId = "doctorId";
	public static final String FIELD_active = "active";
	public static final String FIELD_patientId = "patientId";
	public static final String FIELD_newRequest = "newRequest";
	public static final String FIELD_requestAccepted = "requestAccepted";
	public static final String FIELD_description = "description";
	public static final String FIELD_alignment = "alignment";
	
	private Long patientRequestId;
	private Long doctorId;
	private Character active;
	private Long patientId;
	private Character newRequest;
	private Character requestAccepted;
	private String description;
	private String alignment;

	public void setPatientRequestId( Long  _patientRequestId){
		this.patientRequestId = _patientRequestId;
	}
	@Id
 	@Column(name = "patientRequestId") 
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Long getPatientRequestId(){
		 return this.patientRequestId;
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
	public void setPatientId( Long  _patientId){
		this.patientId = _patientId;
	}
	@Column (name="patientId")
	public Long getPatientId(){
		 return this.patientId;
	}
	public void setNewRequest( Character  _newRequest){
		this.newRequest = _newRequest;
	}
	@Column (name="newRequest")
	public Character getNewRequest(){
		 return this.newRequest;
	}
	public void setRequestAccepted( Character  _requestAccepted){
		this.requestAccepted = _requestAccepted;
	}
	@Column (name="requestAccepted")
	public Character getRequestAccepted(){
		 return this.requestAccepted;
	}
	public void setDescription( String  _description){
		this.description = _description;
	}
	@Length(max=500)
	@Column (name="description")
	public String getDescription(){
		 return this.description;
	}
	public void setAlignment( String  _alignment){
		this.alignment = _alignment;
	}
	@Length(max=100)
	@Column (name="alignment")
	public String getAlignment(){
		 return this.alignment;
	}

	@Override
	public boolean equals(Object o){
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		BasePatientRequest other = (BasePatientRequest)o;
		if(
			ObjectUtil.isEqual(getDoctorId(), other.getDoctorId()) && 
			ObjectUtil.isEqual(getActive(), other.getActive()) && 
			ObjectUtil.isEqual(getPatientId(), other.getPatientId()) && 
			ObjectUtil.isEqual(getNewRequest(), other.getNewRequest()) && 
			ObjectUtil.isEqual(getRequestAccepted(), other.getRequestAccepted()) && 
			ObjectUtil.isEqual(getDescription(), other.getDescription()) && 
			ObjectUtil.isEqual(getAlignment(), other.getAlignment()) && 
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
		result = result + (patientRequestId!= null ? patientRequestId.hashCode() : 0);

		return result;
	}

	public void validate(boolean validatePk){
		resetValidationMessages();

		List<ValidationMessage> list = new ArrayList<ValidationMessage>();
		if(validatePk){
		if(this.patientRequestId == null){
			 list.add(new ValidationMessage("Field " + FIELD_patientRequestId+  " cannot be null"));
		}

		}
		if(this.active == null){
			 list.add(new ValidationMessage("Field " + FIELD_active+  " cannot be null"));
		}

		if((this.description != null) && (this.description.length()>500)){
			 list.add(new ValidationMessage("Field " + FIELD_description+  " cannot be longer than: " + 500));
		}

		if((this.alignment != null) && (this.alignment.length()>100)){
			 list.add(new ValidationMessage("Field " + FIELD_alignment+  " cannot be longer than: " + 100));
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
		str.append("patientRequestId = " + patientRequestId + "\n");
;
		str.append("doctorId = " + doctorId + "\n");
		str.append("active = " + active + "\n");
		str.append("patientId = " + patientId + "\n");
		str.append("newRequest = " + newRequest + "\n");
		str.append("requestAccepted = " + requestAccepted + "\n");
		str.append("description = " + description + "\n");
		str.append("alignment = " + alignment + "\n");
		str.append("createdBy = " + createdBy + "\n");
		str.append("createdDate = " + createdDate + "\n");
		str.append("lastUpdatedBy = " + lastUpdatedBy + "\n");
		str.append("lastUpdatedTs = " + lastUpdatedTs + "\n");
		return str.toString();
	}

	@Transient
	@Override
	public final boolean isKeyNull(){
		return (patientRequestId == null); 
	}

	@Transient
	@Override
	public final List<KeyField> getKeyField(){
		List<KeyField> list = new ArrayList<KeyField>();
		list.add(new KeyField(FIELD_patientRequestId, getPatientRequestId()));
		return list;
	}

	@Transient
	@Override
	public Long getId(){
		return getPatientRequestId();
	}

	@Override
	public Object clone() throws CloneNotSupportedException {
		PatientRequest patientrequest = new PatientRequest();
		patientrequest.setFromDB(false);
		patientrequest.setDoctorId(getDoctorId());
		patientrequest.setActive(getActive());
		patientrequest.setPatientId(getPatientId());
		patientrequest.setNewRequest(getNewRequest());
		patientrequest.setRequestAccepted(getRequestAccepted());
		patientrequest.setDescription(getDescription());
		patientrequest.setAlignment(getAlignment());
		patientrequest.setCreatedDate(getCreatedDate());
		//afterClone(patientrequest);
		return patientrequest;
	}
}