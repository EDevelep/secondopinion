package org.secondopinioncaretaker.domain; 

import java.io.Serializable; 
import java.util.ArrayList; 
import java.util.List; 
import java.util.Date;  
import org.secondopinion.utils.threadlocal.AppThreadLocal;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonFormat.Shape;

import org.secondopinion.caretaker.dto.Personaldetail;
import org.secondopinion.domain.BaseDomainObject;
import org.secondopinion.domain.KeyField; 
import org.secondopinion.domain.validation.ValidationMessage; 
import org.secondopinion.utils.StringUtil; 
import org.secondopinion.utils.ObjectUtil; 

import javax.persistence.Column; 
import javax.persistence.MappedSuperclass;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull; 
import javax.persistence.GeneratedValue; 
import javax.persistence.GenerationType; 
import javax.persistence.Id; 
import javax.persistence.Transient;
import org.hibernate.validator.constraints.Length; 
@SuppressWarnings({ "serial"})
@MappedSuperclass 
public abstract class BasePersonaldetail extends BaseDomainObject<Long> {

	public static final String FIELD_personalDetailId = "personalDetailId";
	public static final String FIELD_caretakerId = "caretakerId";
	public static final String FIELD_DOB = "DOB";
	public static final String FIELD_highestDegree = "highestDegree";
	public static final String FIELD_ethinicity = "ethinicity";
	public static final String FIELD_nationality = "nationality";
	public static final String FIELD_emergencyContactNumber = "emergencyContactNumber";
	public static final String FIELD_nativelanguage = "nativelanguage";
	public static final String FIELD_preferredlanguages = "preferredlanguages";
	public static final String FIELD_profilePic = "profilePic";
	public static final String FIELD_caretakerSignature = "caretakerSignature";
	public static final String FIELD_active = "active";
	
	private Long personalDetailId;
	private Long caretakerId;
	@JsonFormat(pattern = "yyyy-MM-dd", shape = Shape.STRING)
	private Date DOB;
	private String highestDegree;
	private String ethinicity;
	private String nationality;
	private String emergencyContactNumber;
	private String nativelanguage;
	private String preferredlanguages;
	private Byte[] profilePic;
	private Byte[] caretakerSignature;
	private Character active;

	public void setPersonalDetailId( Long  _personalDetailId){
		this.personalDetailId = _personalDetailId;
	}
	@Id
 	@Column(name = "personalDetailId") 
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Long getPersonalDetailId(){
		 return this.personalDetailId;
	}
	public void setCaretakerId( Long  _caretakerId){
		this.caretakerId = _caretakerId;
	}
	@NotNull 
	@Column (name="caretakerId")
	public Long getCaretakerId(){
		 return this.caretakerId;
	}
	public void setDOB( Date  _DOB){
		this.DOB = _DOB;
	}
	@JsonFormat(pattern = "yyyy-MM-dd", shape = Shape.STRING)
	@Temporal(TemporalType.DATE)
	@Column (name="DOB")
	public Date getDOB(){
		 return this.DOB;
	}
	public void setHighestDegree( String  _highestDegree){
		this.highestDegree = _highestDegree;
	}
	@Length(max=100)
	@Column (name="highestDegree")
	public String getHighestDegree(){
		 return this.highestDegree;
	}
	public void setEthinicity( String  _ethinicity){
		this.ethinicity = _ethinicity;
	}
	@Length(max=45)
	@Column (name="ethinicity")
	public String getEthinicity(){
		 return this.ethinicity;
	}
	public void setNationality( String  _nationality){
		this.nationality = _nationality;
	}
	@Length(max=45)
	@Column (name="nationality")
	public String getNationality(){
		 return this.nationality;
	}
	public void setEmergencyContactNumber( String  _emergencyContactNumber){
		this.emergencyContactNumber = _emergencyContactNumber;
	}
	@Length(max=45)
	@Column (name="emergencyContactNumber")
	public String getEmergencyContactNumber(){
		 return this.emergencyContactNumber;
	}
	public void setNativelanguage( String  _nativelanguage){
		this.nativelanguage = _nativelanguage;
	}
	@Length(max=255)
	@Column (name="nativelanguage")
	public String getNativelanguage(){
		 return this.nativelanguage;
	}
	public void setPreferredlanguages( String  _preferredlanguages){
		this.preferredlanguages = _preferredlanguages;
	}
	@Length(max=255)
	@Column (name="preferredlanguages")
	public String getPreferredlanguages(){
		 return this.preferredlanguages;
	}
	public void setProfilePic( Byte[]  _profilePic){
		this.profilePic = _profilePic;
	}
	@Column (name="profilePic")
	public Byte[] getProfilePic(){
		 return this.profilePic;
	}
	public void setCaretakerSignature( Byte[]  _caretakerSignature){
		this.caretakerSignature = _caretakerSignature;
	}
	@Column (name="caretakerSignature")
	public Byte[] getCaretakerSignature(){
		 return this.caretakerSignature;
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
		BasePersonaldetail other = (BasePersonaldetail)o;
		if(
			ObjectUtil.isEqual(getCaretakerId(), other.getCaretakerId()) && 
			ObjectUtil.isEqual(getDOB(), other.getDOB()) && 
			ObjectUtil.isEqual(getHighestDegree(), other.getHighestDegree()) && 
			ObjectUtil.isEqual(getEthinicity(), other.getEthinicity()) && 
			ObjectUtil.isEqual(getNationality(), other.getNationality()) && 
			ObjectUtil.isEqual(getEmergencyContactNumber(), other.getEmergencyContactNumber()) && 
			ObjectUtil.isEqual(getNativelanguage(), other.getNativelanguage()) && 
			ObjectUtil.isEqual(getPreferredlanguages(), other.getPreferredlanguages()) && 
			ObjectUtil.isEqual(getProfilePic(), other.getProfilePic()) && 
			ObjectUtil.isEqual(getCaretakerSignature(), other.getCaretakerSignature()) && 
			ObjectUtil.isEqual(getActive(), other.getActive()) && 
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
		result = result + (personalDetailId!= null ? personalDetailId.hashCode() : 0);

		return result;
	}

	public void validate(boolean validatePk){
		resetValidationMessages();

		List<ValidationMessage> list = new ArrayList<ValidationMessage>();
		if(validatePk){
		if(this.personalDetailId == null){
			 list.add(new ValidationMessage("Field " + FIELD_personalDetailId+  " cannot be null"));
		}

		}
		if(this.caretakerId == null){
			 list.add(new ValidationMessage("Field " + FIELD_caretakerId+  " cannot be null"));
		}

		if((this.highestDegree != null) && (this.highestDegree.length()>100)){
			 list.add(new ValidationMessage("Field " + FIELD_highestDegree+  " cannot be longer than: " + 100));
		}

		if((this.ethinicity != null) && (this.ethinicity.length()>45)){
			 list.add(new ValidationMessage("Field " + FIELD_ethinicity+  " cannot be longer than: " + 45));
		}

		if((this.nationality != null) && (this.nationality.length()>45)){
			 list.add(new ValidationMessage("Field " + FIELD_nationality+  " cannot be longer than: " + 45));
		}

		if((this.emergencyContactNumber != null) && (this.emergencyContactNumber.length()>45)){
			 list.add(new ValidationMessage("Field " + FIELD_emergencyContactNumber+  " cannot be longer than: " + 45));
		}

		if((this.nativelanguage != null) && (this.nativelanguage.length()>255)){
			 list.add(new ValidationMessage("Field " + FIELD_nativelanguage+  " cannot be longer than: " + 255));
		}

		if((this.preferredlanguages != null) && (this.preferredlanguages.length()>255)){
			 list.add(new ValidationMessage("Field " + FIELD_preferredlanguages+  " cannot be longer than: " + 255));
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
		str.append("personalDetailId = " + personalDetailId + "\n");
;
		str.append("caretakerId = " + caretakerId + "\n");
		str.append("DOB = " + DOB + "\n");
		str.append("highestDegree = " + highestDegree + "\n");
		str.append("ethinicity = " + ethinicity + "\n");
		str.append("nationality = " + nationality + "\n");
		str.append("emergencyContactNumber = " + emergencyContactNumber + "\n");
		str.append("nativelanguage = " + nativelanguage + "\n");
		str.append("preferredlanguages = " + preferredlanguages + "\n");
		str.append("profilePic = " + profilePic + "\n");
		str.append("caretakerSignature = " + caretakerSignature + "\n");
		str.append("active = " + active + "\n");
		str.append("createdBy = " + createdBy + "\n");
		str.append("createdDate = " + createdDate + "\n");
		str.append("lastUpdatedBy = " + lastUpdatedBy + "\n");
		str.append("lastUpdatedTs = " + lastUpdatedTs + "\n");
		return str.toString();
	}

	@Transient
	@Override
	public final boolean isKeyNull(){
		return (personalDetailId == null); 
	}

	@Transient
	@Override
	public final List<KeyField> getKeyField(){
		List<KeyField> list = new ArrayList<KeyField>();
		list.add(new KeyField(FIELD_personalDetailId, getPersonalDetailId()));
		return list;
	}

	@Transient
	@Override
	public Long getId(){
		return getPersonalDetailId();
	}

	@Override
	public Object clone() throws CloneNotSupportedException {
		Personaldetail personaldetail = new Personaldetail();
		personaldetail.setFromDB(false);
		personaldetail.setCaretakerId(getCaretakerId());
		personaldetail.setDOB(getDOB());
		personaldetail.setHighestDegree(getHighestDegree());
		personaldetail.setEthinicity(getEthinicity());
		personaldetail.setNationality(getNationality());
		personaldetail.setEmergencyContactNumber(getEmergencyContactNumber());
		personaldetail.setNativelanguage(getNativelanguage());
		personaldetail.setPreferredlanguages(getPreferredlanguages());
		personaldetail.setProfilePic(getProfilePic());
		personaldetail.setCaretakerSignature(getCaretakerSignature());
		personaldetail.setActive(getActive());
		personaldetail.setCreatedDate(getCreatedDate());
		//afterClone(personaldetail);
		return personaldetail;
	}
}