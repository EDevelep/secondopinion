package org.secondopinion.diagnosticcenter.domain; 

import java.io.Serializable; 
import java.util.ArrayList; 
import java.util.List; 
import java.util.Date; 
import org.secondopinion.utils.threadlocal.AppThreadLocal;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonFormat.Shape;

import org.secondopinion.diagnosticcenter.dto.Personaldetail;
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
	public static final String FIELD_diagnosticcenteruserId = "diagnosticcenteruserId";
	public static final String FIELD_DOB = "DOB";
	public static final String FIELD_height = "height";
	public static final String FIELD_eyeColor = "eyeColor";
	public static final String FIELD_hairColor = "hairColor";
	public static final String FIELD_highestDegree = "highestDegree";
	public static final String FIELD_specialization = "specialization";
	public static final String FIELD_ethinicity = "ethinicity";
	public static final String FIELD_nationality = "nationality";
	public static final String FIELD_bloodgroup = "bloodgroup";
	public static final String FIELD_gender = "gender";
	public static final String FIELD_martialstatus = "martialstatus";
	public static final String FIELD_profilePic = "profilePic";
	public static final String FIELD_active = "active";
	public static final String FIELD_preferredlanguages = "preferredlanguages";
	
	private Long personalDetailId;
	private Long diagnosticcenteruserId;
	@JsonFormat(pattern = "yyyy-MM-dd", shape = Shape.STRING)
	private Date DOB;
	private String height;
	private String eyeColor;
	private String hairColor;
	private String highestDegree;
	private String specialization;
	private String ethinicity;
	private String nationality;
	private String bloodgroup;
	private String gender;
	private String martialstatus;
	private byte[] profilePic;
	private Character active;
	private String preferredlanguages;

	public void setPersonalDetailId( Long  _personalDetailId){
		this.personalDetailId = _personalDetailId;
	}
	@Id
 	@Column(name = "personalDetailId") 
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Long getPersonalDetailId(){
		 return this.personalDetailId;
	}
	public void setdiagnosticcenteruserId( Long  _diagnosticcenteruserId){
		this.diagnosticcenteruserId = _diagnosticcenteruserId;
	}
	@NotNull 
	@Column (name="diagnosticcenteruserId")
	public Long getdiagnosticcenteruserId(){
		 return this.diagnosticcenteruserId;
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
	public void setHeight( String  _height){
		this.height = _height;
	}
	@Length(max=45)
	@Column (name="height")
	public String getHeight(){
		 return this.height;
	}
	public void setEyeColor( String  _eyeColor){
		this.eyeColor = _eyeColor;
	}
	@Length(max=45)
	@Column (name="eyeColor")
	public String getEyeColor(){
		 return this.eyeColor;
	}
	public void setHairColor( String  _hairColor){
		this.hairColor = _hairColor;
	}
	@Length(max=45)
	@Column (name="hairColor")
	public String getHairColor(){
		 return this.hairColor;
	}
	public void setHighestDegree( String  _highestDegree){
		this.highestDegree = _highestDegree;
	}
	@Length(max=100)
	@Column (name="highestDegree")
	public String getHighestDegree(){
		 return this.highestDegree;
	}
	public void setSpecialization( String  _specialization){
		this.specialization = _specialization;
	}
	@Length(max=150)
	@Column (name="specialization")
	public String getSpecialization(){
		 return this.specialization;
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
	public void setBloodgroup( String  _bloodgroup){
		this.bloodgroup = _bloodgroup;
	}
	@Length(max=255)
	@Column (name="bloodgroup")
	public String getBloodgroup(){
		 return this.bloodgroup;
	}
	public void setGender( String  _gender){
		this.gender = _gender;
	}
	@Length(max=255)
	@Column (name="gender")
	public String getGender(){
		 return this.gender;
	}
	public void setMartialstatus( String  _martialstatus){
		this.martialstatus = _martialstatus;
	}
	@Length(max=255)
	@Column (name="martialstatus")
	public String getMartialstatus(){
		 return this.martialstatus;
	}
	public void setProfilePic( byte[]  _profilePic){
		this.profilePic = _profilePic;
	}
	@Column (name="profilePic")
	public byte[] getProfilePic(){
		 return this.profilePic;
	}
	public void setActive( Character  _active){
		this.active = _active;
	}
	@NotNull 
	@Column (name="active")
	public Character getActive(){
		 return this.active;
	}
	public void setPreferredlanguages( String  _preferredlanguages){
		this.preferredlanguages = _preferredlanguages;
	}
	@Length(max=255)
	@Column (name="preferredlanguages")
	public String getPreferredlanguages(){
		 return this.preferredlanguages;
	}

	@Override
	public boolean equals(Object o){
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		BasePersonaldetail other = (BasePersonaldetail)o;
		if(
			ObjectUtil.isEqual(getdiagnosticcenteruserId(), other.getdiagnosticcenteruserId()) && 
			ObjectUtil.isEqual(getDOB(), other.getDOB()) && 
			ObjectUtil.isEqual(getHeight(), other.getHeight()) && 
			ObjectUtil.isEqual(getEyeColor(), other.getEyeColor()) && 
			ObjectUtil.isEqual(getHairColor(), other.getHairColor()) && 
			ObjectUtil.isEqual(getHighestDegree(), other.getHighestDegree()) && 
			ObjectUtil.isEqual(getSpecialization(), other.getSpecialization()) && 
			ObjectUtil.isEqual(getEthinicity(), other.getEthinicity()) && 
			ObjectUtil.isEqual(getNationality(), other.getNationality()) && 
			ObjectUtil.isEqual(getBloodgroup(), other.getBloodgroup()) && 
			ObjectUtil.isEqual(getGender(), other.getGender()) && 
			ObjectUtil.isEqual(getMartialstatus(), other.getMartialstatus()) && 
			ObjectUtil.isEqual(getProfilePic(), other.getProfilePic()) && 
			ObjectUtil.isEqual(getActive(), other.getActive()) && 
			ObjectUtil.isEqual(getPreferredlanguages(), other.getPreferredlanguages()) && 
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
		if(this.diagnosticcenteruserId == null){
			 list.add(new ValidationMessage("Field " + FIELD_diagnosticcenteruserId +" cannot be null"));
		}

		if((this.height != null) && (this.height.length()>25)){
			 list.add(new ValidationMessage("Field " + FIELD_height+  " cannot be longer than: " + 25));
		}

		if((this.eyeColor != null) && (this.eyeColor.length()>45)){
			 list.add(new ValidationMessage("Field " + FIELD_eyeColor+  " cannot be longer than: " + 45));
		}

		if((this.hairColor != null) && (this.hairColor.length()>45)){
			 list.add(new ValidationMessage("Field " + FIELD_hairColor+  " cannot be longer than: " + 45));
		}

		if((this.highestDegree != null) && (this.highestDegree.length()>100)){
			 list.add(new ValidationMessage("Field " + FIELD_highestDegree+  " cannot be longer than: " + 100));
		}

		if((this.specialization != null) && (this.specialization.length()>150)){
			 list.add(new ValidationMessage("Field " + FIELD_specialization+  " cannot be longer than: " + 150));
		}

		if((this.ethinicity != null) && (this.ethinicity.length()>45)){
			 list.add(new ValidationMessage("Field " + FIELD_ethinicity+  " cannot be longer than: " + 45));
		}

		if((this.nationality != null) && (this.nationality.length()>45)){
			 list.add(new ValidationMessage("Field " + FIELD_nationality+  " cannot be longer than: " + 45));
		}

		if((this.bloodgroup != null) && (this.bloodgroup.length()>255)){
			 list.add(new ValidationMessage("Field " + FIELD_bloodgroup+  " cannot be longer than: " + 255));
		}

		if((this.gender != null) && (this.gender.length()>255)){
			 list.add(new ValidationMessage("Field " + FIELD_gender+  " cannot be longer than: " + 255));
		}

		if((this.martialstatus != null) && (this.martialstatus.length()>255)){
			 list.add(new ValidationMessage("Field " + FIELD_martialstatus+  " cannot be longer than: " + 255));
		}

		if(this.active == null){
			 list.add(new ValidationMessage("Field " + FIELD_active+  " cannot be null"));
		}

		if((this.preferredlanguages != null) && (this.preferredlanguages.length()>255)){
			 list.add(new ValidationMessage("Field " + FIELD_preferredlanguages+  " cannot be longer than: " + 255));
		}

	if(this.isFromDB()){
		if((this.createdBy != null) && (this.createdBy.length()>255)){
			 list.add(new ValidationMessage("Field " + FIELD_createdBy+  " cannot be longer than: " + 255));
		}
	}
	if(this.isFromDB()){
		if((this.lastUpdatedBy != null) && (this.lastUpdatedBy.length()>255)){
			 list.add(new ValidationMessage("Field " + FIELD_lastUpdatedBy+  " cannot be longer than: " + 255));
		}
	}
		if(this.isFromDB()){
			if(this.lastUpdatedTs == null){
				 list.add(new ValidationMessage("Field " + FIELD_lastUpdatedTs+  " cannot be null"));
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
		str.append("diagnosticcenteruserId = " + FIELD_diagnosticcenteruserId + "\n");
		str.append("DOB = " + DOB + "\n");
		str.append("height = " + height + "\n");
		str.append("eyeColor = " + eyeColor + "\n");
		str.append("hairColor = " + hairColor + "\n");
		str.append("highestDegree = " + highestDegree + "\n");
		str.append("specialization = " + specialization + "\n");
		str.append("ethinicity = " + ethinicity + "\n");
		str.append("nationality = " + nationality + "\n");
		str.append("bloodgroup = " + bloodgroup + "\n");
		str.append("gender = " + gender + "\n");
		str.append("martialstatus = " + martialstatus + "\n");
		str.append("profilePic = " + profilePic + "\n");
		str.append("active = " + active + "\n");
		str.append("preferredlanguages = " + preferredlanguages + "\n");
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
		personaldetail.setdiagnosticcenteruserId(getdiagnosticcenteruserId());;
		personaldetail.setDOB(getDOB());
		personaldetail.setHeight(getHeight());
		personaldetail.setEyeColor(getEyeColor());
		personaldetail.setHairColor(getHairColor());
		personaldetail.setHighestDegree(getHighestDegree());
		personaldetail.setSpecialization(getSpecialization());
		personaldetail.setEthinicity(getEthinicity());
		personaldetail.setNationality(getNationality());
		personaldetail.setBloodgroup(getBloodgroup());
		personaldetail.setGender(getGender());
		personaldetail.setMartialstatus(getMartialstatus());
		personaldetail.setProfilePic(getProfilePic());
		personaldetail.setActive(getActive());
		personaldetail.setPreferredlanguages(getPreferredlanguages());
		personaldetail.setCreatedDate(getCreatedDate());
		//afterClone(personaldetail);
		return personaldetail;
	}
}