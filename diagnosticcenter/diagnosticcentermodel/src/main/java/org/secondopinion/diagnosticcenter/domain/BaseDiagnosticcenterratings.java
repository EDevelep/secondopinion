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
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;
import org.secondopinion.diagnosticcenter.dto.Diagnosticcenterratings;
import org.secondopinion.domain.BaseDomainObject;
import org.secondopinion.domain.KeyField;
import org.secondopinion.domain.validation.ValidationMessage;
import org.secondopinion.utils.ObjectUtil;
import org.secondopinion.utils.threadlocal.AppThreadLocal; 
@MappedSuperclass 
public abstract class BaseDiagnosticcenterratings extends BaseDomainObject<Long> {

	
	public static final String FIELD_diagnosticcenterratingid = "diagnosticcenterratingid";
	public static final String FIELD_diagnosticcenterId = "diagnosticcenterId";
	public static final String FIELD_patientid = "patientid";
	public static final String FIELD_rating = "rating";
	public static final String FIELD_feedback = "feedback";
	public static final String FIELD_active = "active";
	
	private Long diagnosticcenterratingid;
	private Long diagnosticcenterId;
	private Long patientid;
	private Double rating;
	private String feedback;
	private Character active;
	
	public void setDiagnosticcenterratingid( Long  _diagnosticcenterratingid){
		this.diagnosticcenterratingid = _diagnosticcenterratingid;
	}
	@Id
 	@Column(name = "diagnosticcenterratingid") 
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Long getDiagnosticcenterratingid(){
		 return this.diagnosticcenterratingid;
	}
	public void setDiagnosticcenterId( Long  _diagnosticcenterId){
		this.diagnosticcenterId = _diagnosticcenterId;
	}
	@NotNull 
	@Column (name="diagnosticcenterId")
	public Long getDiagnosticcenterId(){
		 return this.diagnosticcenterId;
	}
	public void setPatientid( Long  _patientid){
		this.patientid = _patientid;
	}
	@NotNull 
	@Column (name="patientid")
	public Long getPatientid(){
		 return this.patientid;
	}
	public void setRating( Double  _rating){
		this.rating = _rating;
	}
	@NotNull 
	@Column (name="rating")
	public Double getRating(){
		 return this.rating;
	}
	public void setFeedback( String  _feedback){
		this.feedback = _feedback;
	}
	@Length(max=2000)
	@Column (name="feedback")
	public String getFeedback(){
		 return this.feedback;
	}
	public void setActive( Character  _active){
		this.active = _active;
	}
	@Column (name="active")
	public Character getActive(){
		 return this.active;
	}

	@Override
	public boolean equals(Object o){
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		BaseDiagnosticcenterratings other = (BaseDiagnosticcenterratings)o;
		if(
			ObjectUtil.isEqual(getDiagnosticcenterId(), other.getDiagnosticcenterId()) && 
			ObjectUtil.isEqual(getPatientid(), other.getPatientid()) && 
			ObjectUtil.isEqual(getRating(), other.getRating()) && 
			ObjectUtil.isEqual(getFeedback(), other.getFeedback()) && 
			ObjectUtil.isEqual(getActive(), other.getActive()) && 
			ObjectUtil.isEqual(getLastUpdatedBy(), other.getLastUpdatedBy()) && 
			ObjectUtil.isEqual(getLastUpdatedTs(), other.getLastUpdatedTs())){
			return true;
		}
		return false;
	}

	@Override
	public int hashCode(){
		int result = 0;
		result = result + (diagnosticcenterratingid!= null ? diagnosticcenterratingid.hashCode() : 0);

		return result;
	}

	public void validate(boolean validatePk){
		resetValidationMessages();

		List<ValidationMessage> list = new ArrayList<ValidationMessage>();
		if(validatePk){
		if(this.diagnosticcenterratingid == null){
			 list.add(new ValidationMessage("Field " + FIELD_diagnosticcenterratingid+  " cannot be null"));
		}

		}
		if(this.diagnosticcenterId == null){
			 list.add(new ValidationMessage("Field " + FIELD_diagnosticcenterId+  " cannot be null"));
		}

		if(this.patientid == null){
			 list.add(new ValidationMessage("Field " + FIELD_patientid+  " cannot be null"));
		}

		if(this.rating == null){
			 list.add(new ValidationMessage("Field " + FIELD_rating+  " cannot be null"));
		}

		if((this.feedback != null) && (this.feedback.length()>2000)){
			 list.add(new ValidationMessage("Field " + FIELD_feedback+  " cannot be longer than: " + 2000));
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
		str.append("diagnosticcenterratingid = " + diagnosticcenterratingid + "\n");
;
		str.append("diagnosticcenterId = " + diagnosticcenterId + "\n");
		str.append("patientid = " + patientid + "\n");
		str.append("rating = " + rating + "\n");
		str.append("feedback = " + feedback + "\n");
		str.append("active = " + active + "\n");
		str.append("lastUpdatedBy = " + lastUpdatedBy + "\n");
		str.append("lastUpdatedTs = " + lastUpdatedTs + "\n");
		return str.toString();
	}

	@Transient
	@Override
	public final boolean isKeyNull(){
		return (diagnosticcenterratingid == null); 
	}

	@Transient
	@Override
	public final List<KeyField> getKeyField(){
		List<KeyField> list = new ArrayList<KeyField>();
		list.add(new KeyField(FIELD_diagnosticcenterratingid, getDiagnosticcenterratingid()));
		return list;
	}

	@Transient
	@Override
	public Long getId(){
		return getDiagnosticcenterratingid();
	}

	@Override
	public Object clone() throws CloneNotSupportedException {
		Diagnosticcenterratings diagnosticcenterratings = new Diagnosticcenterratings();
		diagnosticcenterratings.setFromDB(false);
		diagnosticcenterratings.setDiagnosticcenterId(getDiagnosticcenterId());
		diagnosticcenterratings.setPatientid(getPatientid());
		diagnosticcenterratings.setRating(getRating());
		diagnosticcenterratings.setFeedback(getFeedback());
		diagnosticcenterratings.setActive(getActive());
		//afterClone(diagnosticcenterratings);
		return diagnosticcenterratings;
	}
}