package org.secondopinioncaretaker.domain; 

import java.io.Serializable; 
import java.util.ArrayList; 
import java.util.List; 
import java.util.Date; 
import org.secondopinion.utils.threadlocal.AppThreadLocal;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonFormat.Shape;

import org.secondopinion.caretaker.dto.Certification;
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
public abstract class BaseCertification extends BaseDomainObject<Long> {

	public static final String FIELD_certificationId = "certificationId";
	public static final String FIELD_caretakerId = "caretakerId";
	public static final String FIELD_degree = "degree";
	public static final String FIELD_instituteName = "instituteName";
	public static final String FIELD_yearGraduated = "yearGraduated";
	public static final String FIELD_active = "active";
	
	private Long certificationId;
	private Long caretakerId;
	private String degree;
	private String instituteName;
	private Date yearGraduated;
	private Character active;

	public void setCertificationId( Long  _certificationId){
		this.certificationId = _certificationId;
	}
	@Id
 	@Column(name = "certificationId") 
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Long getCertificationId(){
		 return this.certificationId;
	}
	public void setcaretakerId( Long  _caretakerId){
		this.caretakerId = _caretakerId;
	}
	@NotNull 
	@Column (name="caretakerId")
	public Long getcaretakerId(){
		 return this.caretakerId;
	}
	public void setDegree( String  _degree){
		this.degree = _degree;
	}
	@Length(max=400)
	@Column (name="degree")
	public String getDegree(){
		 return this.degree;
	}
	public void setInstituteName( String  _instituteName){
		this.instituteName = _instituteName;
	}
	@Length(max=180)
	@Column (name="instituteName")
	public String getInstituteName(){
		 return this.instituteName;
	}
	
	public void setActive( Character  _active){
		this.active = _active;
	}
	@NotNull 
	@Column (name="active")
	public Character getActive(){
		 return this.active;
	}

	@JsonFormat(pattern="yyyy-MM-dd", shape=Shape.STRING)
	@NotNull 
	@Temporal(TemporalType.DATE)
	@Column (name="yearGraduated")
	public Date getYearGraduated() {
		return yearGraduated;
	}
	public void setYearGraduated(Date yearGraduated) {
		this.yearGraduated = yearGraduated;
	}
	@Override
	public boolean equals(Object o){
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		BaseCertification other = (BaseCertification)o;
		if(
			ObjectUtil.isEqual(getcaretakerId(), other.getcaretakerId()) && 
			ObjectUtil.isEqual(getDegree(), other.getDegree()) && 
			ObjectUtil.isEqual(getInstituteName(), other.getInstituteName()) &&  
			ObjectUtil.isEqual(getActive(), other.getActive()) && 
			ObjectUtil.isEqual(getYearGraduated(), other.getYearGraduated())&&
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
		result = result + (certificationId!= null ? certificationId.hashCode() : 0);

		return result;
	}

	public void validate(boolean validatePk){
		resetValidationMessages();

		List<ValidationMessage> list = new ArrayList<ValidationMessage>();
		if(validatePk){
		if(this.certificationId == null){
			 list.add(new ValidationMessage("Field " + FIELD_certificationId+  " cannot be null"));
		}

		}
		if(this.caretakerId == null){
			 list.add(new ValidationMessage("Field " + FIELD_caretakerId+  " cannot be null"));
		}

		if((this.degree != null) && (this.degree.length()>400)){
			 list.add(new ValidationMessage("Field " + FIELD_degree+  " cannot be longer than: " + 400));
		}

		if((this.instituteName != null) && (this.instituteName.length()>180)){
			 list.add(new ValidationMessage("Field " + FIELD_instituteName+  " cannot be longer than: " + 180));
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
		str.append("certificationId = " + certificationId + "\n");
;
		str.append("caretakerId = " + caretakerId + "\n");
		str.append("degree = " + degree + "\n");
		str.append("instituteName = " + instituteName + "\n");
		str.append("yearGraduated = " + yearGraduated + "\n");
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
		return (certificationId == null); 
	}

	@Transient
	@Override
	public final List<KeyField> getKeyField(){
		List<KeyField> list = new ArrayList<KeyField>();
		list.add(new KeyField(FIELD_certificationId, getCertificationId()));
		return list;
	}

	@Transient
	@Override
	public Long getId(){
		return getCertificationId();
	}

	@Override
	public Object clone() throws CloneNotSupportedException {
		Certification certification = new Certification();
		certification.setFromDB(false);
		certification.setcaretakerId(getcaretakerId());
		certification.setDegree(getDegree());
		certification.setInstituteName(getInstituteName());
		certification.setYearGraduated(getYearGraduated());
		certification.setActive(getActive());
		certification.setCreatedDate(getCreatedDate());
		//afterClone(certification);
		return certification;
	}
}