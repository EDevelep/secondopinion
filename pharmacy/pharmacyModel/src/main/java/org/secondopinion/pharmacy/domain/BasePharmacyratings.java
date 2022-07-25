package org.secondopinion.pharmacy.domain; 

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
import org.secondopinion.domain.BaseDomainObject;
import org.secondopinion.domain.KeyField;
import org.secondopinion.domain.validation.ValidationMessage;
import org.secondopinion.pharmacy.dto.Pharmacyratings;
import org.secondopinion.utils.ObjectUtil;
import org.secondopinion.utils.threadlocal.AppThreadLocal;
@MappedSuperclass 
public abstract class BasePharmacyratings extends BaseDomainObject<Long> {

	public static final String FIELD_pharmacyratingid = "pharmacyratingid";
	public static final String FIELD_pharmacyaddressId = "pharmacyaddressId";
	public static final String FIELD_patientid = "patientid";
	public static final String FIELD_rating = "rating";
	public static final String FIELD_feedback = "feedback";
	public static final String FIELD_active = "active";
	
	private Long pharmacyratingid;
	private Long pharmacyaddressId;
	private Long patientid;
	private Double rating;
	private String feedback;
	private Character active;

	public void setPharmacyratingid( Long  _pharmacyratingid){
		this.pharmacyratingid = _pharmacyratingid;
	}
	@Id
 	@Column(name = "pharmacyratingid") 
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Long getPharmacyratingid(){
		 return this.pharmacyratingid;
	}
	public void setPharmacyaddressId( Long  _pharmacyaddressId){
		this.pharmacyaddressId = _pharmacyaddressId;
	}
	@NotNull
	@Column (name="pharmacyaddressId")
	public Long getPharmacyaddressId(){
		 return this.pharmacyaddressId;
	}
	
	public void setPatientid( Long  _patientid){
		this.patientid = _patientid;
	}
	@NotNull
	@Column (name="patientid")
	public Long getPatientid(){
		 return this.patientid;
	}
	public void setRating(Double rating) {
		this.rating = rating;
	}
	@Column (name="rating")
	public Double getRating() {
		return rating;
	}
	public void setFeedback(String feedback) {
		this.feedback = feedback;
	}
	@Column (name="feedback")
	public String getFeedback() {
		return feedback;
	}
	public void setActive( Character  _active){
		this.active = _active;
	}
	@NotNull 
	@Column (name="active")
	public Character getActive(){
		 return this.active;
	}
		
	@Override
	public boolean equals(Object o){
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		BasePharmacyratings other = (BasePharmacyratings)o;
		if(
			ObjectUtil.isEqual(getPharmacyaddressId(), other.getPharmacyaddressId()) && 
			ObjectUtil.isEqual(getPatientid(), other.getPatientid()) && 
			ObjectUtil.isEqual(getFeedback(), other.getFeedback()) && 
			ObjectUtil.isEqual(getRating(), other.getRating()) && 
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
		result = result + (pharmacyratingid!= null ? pharmacyratingid.hashCode() : 0);

		return result;
	}

	public void validate(boolean validatePk){
		resetValidationMessages();

		List<ValidationMessage> list = new ArrayList<ValidationMessage>();
		if(validatePk){
		if(this.pharmacyratingid == null){
			 list.add(new ValidationMessage("Field " + FIELD_pharmacyratingid+  " cannot be null"));
		}

		}
		if((this.pharmacyaddressId == null)){
			 list.add(new ValidationMessage("Field " + FIELD_pharmacyaddressId+  " cannot be null."));
		}
		if((this.patientid == null)){
			 list.add(new ValidationMessage("Field " + FIELD_patientid+  " cannot be null."));
		}
		if((this.rating == null)){
			 list.add(new ValidationMessage("Field " + FIELD_rating+  " cannot be null."));
		}
		if((this.feedback != null) && (this.feedback.length()>2000)){
			 list.add(new ValidationMessage("Field " + FIELD_feedback+  " cannot be longer than: " + 2000));
		}
		if(this.active == null){
			 list.add(new ValidationMessage("Field " + FIELD_active+  " cannot be null"));
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
		
		this.lastUpdatedBy = AppThreadLocal.getUserName();
		this.lastUpdatedTs = AppThreadLocal.getRequestStartDate();
	}

	@Override
	public String toString(){
		StringBuilder str = new StringBuilder();
		str.append("pharmacyratingid = " + pharmacyratingid + "\n");
		str.append("pharmacyaddressId = " + pharmacyaddressId + "\n");
		str.append("rating = " + rating + "\n");
		str.append("patientid = " + patientid + "\n");
		str.append("feedback = " + feedback + "\n");
		str.append("active = " + active + "\n");
		str.append("lastUpdatedBy = " + lastUpdatedBy + "\n");
		str.append("lastUpdatedTs = " + lastUpdatedTs + "\n");
		return str.toString();
	}

	@Transient
	@Override
	public final boolean isKeyNull(){
		return (pharmacyratingid == null); 
	}

	@Transient
	@Override
	public final List<KeyField> getKeyField(){
		List<KeyField> list = new ArrayList<KeyField>();
		list.add(new KeyField(FIELD_pharmacyratingid, getPharmacyratingid()));
		return list;
	}

	@Transient
	@Override
	public Long getId(){
		return getPharmacyratingid();
	}

	@Override
	public Object clone() throws CloneNotSupportedException {
		Pharmacyratings pharmacyratings = new Pharmacyratings();
		pharmacyratings.setFromDB(false);
		pharmacyratings.setPharmacyaddressId(getPharmacyaddressId());
		pharmacyratings.setRating(getRating());
		pharmacyratings.setPatientid(getPatientid());
		pharmacyratings.setFeedback(getFeedback());
		pharmacyratings.setActive(getActive());
		//afterClone(pharmacyratings);
		return pharmacyratings;
	}
}