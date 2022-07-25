package org.secondopinion.diagnosticcenter.domain; 

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.Transient;

import org.hibernate.validator.constraints.Length;
import org.secondopinion.diagnosticcenter.dto.Patientrequest;
import org.secondopinion.domain.BaseDomainObject;
import org.secondopinion.domain.KeyField;
import org.secondopinion.domain.validation.ValidationMessage;
import org.secondopinion.utils.ObjectUtil;
import org.secondopinion.utils.threadlocal.AppThreadLocal; 
@SuppressWarnings({ "serial"})
@MappedSuperclass 
public abstract class BasePatientrequest extends BaseDomainObject<Integer> {

	
	
	
	public static final String FIELD_patientRequestId = "patientRequestId";
	public static final String FIELD_diagnosticCenterAddressId = "diagnosticCenterAddressId";
	public static final String FIELD_patientId = "patientId";
	public static final String FIELD_patientName = "patientName";
	public static final String FIELD_requestedOn = "requestedOn";
	public static final String FIELD_newRequest = "newRequest";
	public static final String FIELD_requestAccepted = "requestAccepted";

	private Integer patientRequestId;
	private Long diagnosticCenterAddressId;
	private Long patientId;
	private String patientName;
	private Date requestedOn;
	private String newRequest;
	private String requestAccepted;

	public void setPatientRequestId( Integer  _patientRequestId){
		this.patientRequestId = _patientRequestId;
	}
	@Id
 	@Column(name = "patientRequestId") 
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Integer getPatientRequestId(){
		 return this.patientRequestId;
	}
	public void setDiagnosticCenterAddressId( Long  _diagnosticCenterAddressId){
		this.diagnosticCenterAddressId = _diagnosticCenterAddressId;
	}
	@Column (name="diagnosticCenterAddressId")
	public Long getDiagnosticCenterAddressId(){
		 return this.diagnosticCenterAddressId;
	}
	public void setPatientId( Long  _patientId){
		this.patientId = _patientId;
	}
	@Column (name="patientId")
	public Long getPatientId(){
		 return this.patientId;
	}
	public void setPatientName( String  _patientName){
		this.patientName = _patientName;
	}
	@Length(max=250)
	@Column (name="patientName")
	public String getPatientName(){
		 return this.patientName;
	}
	public void setRequestedOn( Date  _requestedOn){
		this.requestedOn = _requestedOn;
	}
	@Column (name="requestedOn")
	public Date getRequestedOn(){
		 return this.requestedOn;
	}
	public void setNewRequest( String  _newRequest){
		this.newRequest = _newRequest;
	}
	@Length(max=4)
	@Column (name="newRequest")
	public String getNewRequest(){
		 return this.newRequest;
	}
	public void setRequestAccepted( String  _requestAccepted){
		this.requestAccepted = _requestAccepted;
	}
	@Length(max=4)
	@Column (name="requestAccepted")
	public String getRequestAccepted(){
		 return this.requestAccepted;
	}
	
	@Override
	public boolean equals(Object o){
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		BasePatientrequest other = (BasePatientrequest)o;
		if(
			ObjectUtil.isEqual(getDiagnosticCenterAddressId(), other.getDiagnosticCenterAddressId()) && 
			ObjectUtil.isEqual(getPatientId(), other.getPatientId()) && 
			ObjectUtil.isEqual(getPatientName(), other.getPatientName()) && 
			ObjectUtil.isEqual(getRequestedOn(), other.getRequestedOn()) && 
			ObjectUtil.isEqual(getNewRequest(), other.getNewRequest()) && 
			ObjectUtil.isEqual(getRequestAccepted(), other.getRequestAccepted()) && 
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
		if((this.patientName != null) && (this.patientName.length()>250)){
			 list.add(new ValidationMessage("Field " + FIELD_patientName+  " cannot be longer than: " + 250));
		}

		if((this.newRequest != null) && (this.newRequest.length()>4)){
			 list.add(new ValidationMessage("Field " + FIELD_newRequest+  " cannot be longer than: " + 4));
		}

		if((this.requestAccepted != null) && (this.requestAccepted.length()>4)){
			 list.add(new ValidationMessage("Field " + FIELD_requestAccepted+  " cannot be longer than: " + 4));
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
		str.append("patientRequestId = " + patientRequestId + "\n");
;
		str.append("diagnosticCenterAddressId = " + diagnosticCenterAddressId + "\n");
		str.append("patientId = " + patientId + "\n");
		str.append("patientName = " + patientName + "\n");
		str.append("requestedOn = " + requestedOn + "\n");
		str.append("newRequest = " + newRequest + "\n");
		str.append("requestAccepted = " + requestAccepted + "\n");
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
	public Integer getId(){
		return getPatientRequestId();
	}

	@Override
	public Object clone() throws CloneNotSupportedException {
		Patientrequest patientrequest = new Patientrequest();
		patientrequest.setFromDB(false);
		patientrequest.setDiagnosticCenterAddressId(getDiagnosticCenterAddressId());
		patientrequest.setPatientId(getPatientId());
		patientrequest.setPatientName(getPatientName());
		patientrequest.setRequestedOn(getRequestedOn());
		patientrequest.setNewRequest(getNewRequest());
		patientrequest.setRequestAccepted(getRequestAccepted());
		//afterClone(patientrequest);
		return patientrequest;
	}
}