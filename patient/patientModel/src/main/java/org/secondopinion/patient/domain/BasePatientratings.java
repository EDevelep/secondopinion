package org.secondopinion.patient.domain; 

import java.io.Serializable; 
import java.util.ArrayList; 
import java.util.List; 
import java.util.Date; 
import org.secondopinion.utils.threadlocal.AppThreadLocal;
import org.secondopinion.domain.BaseDomainObject;
import org.secondopinion.domain.KeyField; 
import org.secondopinion.domain.validation.ValidationMessage;
import org.secondopinion.patient.dto.Patientratings;
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
public abstract class BasePatientratings extends BaseDomainObject<Long> {

	public static final String FIELD_patientratingid = "patientratingid";
	public static final String FIELD_patientid = "patientid";
	public static final String FIELD_ratingFor = "ratingFor";
	public static final String FIELD_referenceId = "referenceId"; //Appointment Id
	public static final String FIELD_patientname = "patientname";
	public static final String FIELD_rating = "rating";
	public static final String FIELD_feedback = "feedback";
	public static final String FIELD_active = "active";
	public static final String FIELD_doctorId = "doctorId";
	public static final String FIELD_createdBy = "createdBy";
	public static final String FIELD_createdDate = "createdDate";
	public static final String FIELD_ratingType = "ratingType";
	public static final String FIELD_lastUpdatedBy = "lastUpdatedBy";
	public static final String FIELD_lastUpdatedTs = "lastUpdatedTs";

	private Long patientratingid;
	private Long patientid;
	private String ratingFor;
	private Long referenceId;
	private Long doctorId;
	private String patientname;
	private Double rating;
	private String feedback;
	private Character active;
	private String ratingType;
	public void setPatientratingid( Long  _patientratingid){
		this.patientratingid = _patientratingid;
	}
	@Id
 	@Column(name = "patientratingid") 
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Long getPatientratingid(){
		 return this.patientratingid;
	}
	public void setPatientid( Long  _patientid){
		this.patientid = _patientid;
	}
	@Column (name="patientid")
	public Long getPatientid(){
		 return this.patientid;
	}
	public void setRatingFor( String  _ratingFor){
		this.ratingFor = _ratingFor;
	}
	@NotNull 
	@Length(max=45)
	@Column (name="ratingFor")
	public String getRatingFor(){
		 return this.ratingFor;
	}
	public void setReferenceId( Long  _referenceId){
		this.referenceId = _referenceId;
	}
	@NotNull 
	@Column (name="referenceId")
	public Long getReferenceId(){
		 return this.referenceId;
	}
	public void setPatientname( String  _patientname){
		this.patientname = _patientname;
	}
	@Length(max=45)
	@Column (name="patientname")
	public String getPatientname(){
		 return this.patientname;
	}
	public void setRating( Double  _rating){
		this.rating = _rating;
	}
	@Column (name="rating")
	public Double getRating(){
		 return this.rating;
	}
	public void setFeedback( String  _feedback){
		this.feedback = _feedback;
	}
	@Length(max=500)
	@Column (name="feedback")
	public String getFeedback(){
		 return this.feedback;
	}
	public void setActive( Character  _active){
		this.active = _active;
	}
	@NotNull 
	@Column (name="active")
	public Character getActive(){
		 return this.active;
	}
	
	@Column (name="doctorId")
	public Long getDoctorId() {
		return doctorId;
	}
	public void setDoctorId(Long doctorId) {
		this.doctorId = doctorId;
	}
	@Column (name="ratingType")
	public String getRatingType() {
		return ratingType;
	}
	public void setRatingType(String ratingType) {
		this.ratingType = ratingType;
	}
	@Override
	public boolean equals(Object o){
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		BasePatientratings other = (BasePatientratings)o;
		if(
			ObjectUtil.isEqual(getPatientid(), other.getPatientid()) && 
			ObjectUtil.isEqual(getRatingFor(), other.getRatingFor()) && 
			ObjectUtil.isEqual(getReferenceId(), other.getReferenceId()) && 
			ObjectUtil.isEqual(getPatientname(), other.getPatientname()) && 
			ObjectUtil.isEqual(getRating(), other.getRating()) && 
			ObjectUtil.isEqual(getFeedback(), other.getFeedback()) && 
			ObjectUtil.isEqual(getActive(), other.getActive()) && 
			ObjectUtil.isEqual(getDoctorId(), other.getDoctorId()) &&
			ObjectUtil.isEqual(getRatingType(), other.getRatingType()) && 
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
		result = result + (patientratingid!= null ? patientratingid.hashCode() : 0);

		return result;
	}

	public void validate(boolean validatePk){
		resetValidationMessages();

		List<ValidationMessage> list = new ArrayList<ValidationMessage>();
		if(validatePk){
		if(this.patientratingid == null){
			 list.add(new ValidationMessage("Field " + FIELD_patientratingid+  " cannot be null"));
		}

		}
		if( StringUtil.isNullOrEmpty(this.ratingFor)){
			 list.add(new ValidationMessage("Field " + FIELD_ratingFor+  " cannot be null"));
		}

		if((this.ratingFor != null) && (this.ratingFor.length()>45)){
			 list.add(new ValidationMessage("Field " + FIELD_ratingFor+  " cannot be longer than: " + 45));
		}

		if(this.referenceId == null){
			 list.add(new ValidationMessage("Field " + FIELD_referenceId+  " cannot be null"));
		}

		if((this.patientname != null) && (this.patientname.length()>45)){
			 list.add(new ValidationMessage("Field " + FIELD_patientname+  " cannot be longer than: " + 45));
		}

		if((this.feedback != null) && (this.feedback.length()>500)){
			 list.add(new ValidationMessage("Field " + FIELD_feedback+  " cannot be longer than: " + 500));
		}

		if(this.active == null){
			 list.add(new ValidationMessage("Field " + FIELD_active+  " cannot be null"));
		}

	if(this.isFromDB()){
		if((this.createdBy != null) && (this.createdBy.length()>45)){
			 list.add(new ValidationMessage("Field " + FIELD_createdBy+  " cannot be longer than: " + 45));
		}
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
			this.createdBy = AppThreadLocal.getUserName();
		}
		this.lastUpdatedBy = AppThreadLocal.getUserName();
		this.lastUpdatedTs = AppThreadLocal.getRequestStartDate();
	}

	@Override
	public String toString(){
		StringBuilder str = new StringBuilder();
		str.append("patientratingid = " + patientratingid + "\n");
;
		str.append("patientid = " + patientid + "\n");
		str.append("ratingFor = " + ratingFor + "\n");
		str.append("referenceId = " + referenceId + "\n");
		str.append("patientname = " + patientname + "\n");
		str.append("doctorId = " + doctorId + "\n");
		str.append("rating = " + rating + "\n");
		str.append("feedback = " + feedback + "\n");
		str.append("active = " + active + "\n");
		str.append("ratingType = " + ratingType + "\n");
		str.append("createdBy = " + createdBy + "\n");	
		str.append("createdDate = " + createdDate + "\n");
		str.append("lastUpdatedBy = " + lastUpdatedBy + "\n");
		str.append("lastUpdatedTs = " + lastUpdatedTs + "\n");
		return str.toString();
	}

	@Transient
	@Override
	public final boolean isKeyNull(){
		return (patientratingid == null); 
	}

	@Transient
	@Override
	public final List<KeyField> getKeyField(){
		List<KeyField> list = new ArrayList<KeyField>();
		list.add(new KeyField(FIELD_patientratingid, getPatientratingid()));
		return list;
	}

	@Transient
	@Override
	public Long getId(){
		return getPatientratingid();
	}

	@Override
	public Object clone() throws CloneNotSupportedException {
		Patientratings patientratings = new Patientratings();
		patientratings.setFromDB(false);
		patientratings.setPatientid(getPatientid());
		patientratings.setRatingFor(getRatingFor());
		patientratings.setReferenceId(getReferenceId());
		patientratings.setPatientname(getPatientname());
		patientratings.setRating(getRating());
		patientratings.setFeedback(getFeedback());
		patientratings.setDoctorId(getDoctorId());
		patientratings.setActive(getActive());
		patientratings.setCreatedDate(getCreatedDate());
		patientratings.setRatingType(getRatingType());
		//afterClone(patientratings);
		return patientratings;
	}
}