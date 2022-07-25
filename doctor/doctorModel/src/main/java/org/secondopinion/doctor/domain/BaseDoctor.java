package org.secondopinion.doctor.domain;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.Date;
import org.secondopinion.utils.threadlocal.AppThreadLocal;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonFormat.Shape;

import org.secondopinion.doctor.dto.Doctor;
import org.secondopinion.domain.BaseDomainObject;
import org.secondopinion.domain.KeyField;
import org.secondopinion.domain.validation.ValidationMessage;
import org.secondopinion.enums.PrimaryContactEnum;
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

@MappedSuperclass
public abstract class BaseDoctor extends BaseDomainObject<Long> {

	public static final String FIELD_doctorId = "doctorId";
	public static final String FIELD_firstName = "firstName";
	public static final String FIELD_lastName = "lastName";
	public static final String FIELD_middleName = "middleName";
	public static final String FIELD_specialization = "specialization";
	public static final String FIELD_emailId = "emailId";
	public static final String FIELD_password = "password";
	public static final String FIELD_lastLogin = "lastLogin";
	public static final String FIELD_cellNumber = "cellNumber";
	public static final String FIELD_homeNumber = "homeNumber";
	public static final String FIELD_officeNumber = "officeNumber";
	public static final String FIELD_primaryContact = "primaryContact";
	public static final String FIELD_medicalLicenceNumber = "medicalLicenceNumber";
	public static final String FIELD_totalExperience = "totalExperience";
	public static final String FIELD_dateOfExpiry = "dateOfExpiry";
	public static final String FIELD_activatedDate = "activatedDate";
	public static final String FIELD_active = "active";
	public static final String FIELD_isprofilecompleted= "isprofilecompleted";
	public static final String FIELD_locked = "locked";
	public static final String FIELD_retry = "retry";
	public static final String FIELD_gender = "gender";
	public static final String FIELD_type = "type";
	public static final String FIELD_averagerating = "averagerating";
	public static final String FIELD_userName = "userName";
	
	private Long doctorId;
	private String firstName;
	private String lastName;
	private String middleName;
	private String specialization;
	private String emailId;
	private String password;
	private Date lastLogin;
	private String cellNumber;

	private String officeNumber;
	private String homeNumber;
	private String primaryContact;
	private String medicalLicenceNumber;
	private Integer totalExperience;
	private Date dateOfExpiry;
	private String type;
	private Date activatedDate;
	private Character active;
	private Character locked;
	private Integer retry;
	private String gender;
	private Double averagerating;
	private String userName;
	private Character isprofilecompleted;

	public void setDoctorId(Long _doctorId) {
		this.doctorId = _doctorId;
	}

	@Id
	@Column(name = "doctorId")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Long getDoctorId() {
		return this.doctorId;
	}

	public void setFirstName(String _firstName) {
		this.firstName = _firstName;
	}
	 public void setUserName(String _userName) {
		    this.userName = _userName;
		  }

		  @NotNull
		  @Length(max = 95)
		  @Column(name = "userName")
		  public String getUserName() {
		    return this.userName;
		  }

	@NotNull
	@Length(max = 150)
	@Column(name = "firstName")
	public String getFirstName() {
		return this.firstName;
	}

	public void setLastName(String _lastName) {
		this.lastName = _lastName;
	}

	@Length(max = 150)
	@Column(name = "lastName")
	public String getLastName() {
		return this.lastName;
	}

	public void setMiddleName(String _middleName) {
		this.middleName = _middleName;
	}

	@Length(max = 45)
	@Column(name = "middleName")
	public String getMiddleName() {
		return this.middleName;
	}

	public void setSpecialization(String _specialization) {
		this.specialization = _specialization;
	}

	@Length(max = 150)
	@Column(name = "specialization")
	public String getSpecialization() {
		return this.specialization;
	}

	public void setEmailId(String _emailId) {
		this.emailId = _emailId;
	}

	@NotNull
	@Length(max = 250)
	@Column(name = "emailId")
	public String getEmailId() {
		return this.emailId;
	}

	public void setPassword(String _password) {
		this.password = _password;
	}

	@NotNull
	@Length(max = 250)
	@Column(name = "password")
	public String getPassword() {
		return this.password;
	}

	public void setLastLogin(Date _lastLogin) {
		this.lastLogin = _lastLogin;
	}

	@JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSXXX", shape = Shape.STRING)
	@Column(name = "lastLogin")
	public Date getLastLogin() {
		return this.lastLogin;
	}

	public void setCellNumber(String _cellNumber) {
		this.cellNumber = _cellNumber;
	}

	@NotNull
	@Length(max = 45)
	@Column(name = "cellNumber")
	public String getCellNumber() {
		return this.cellNumber;
	}

	public void setOfficeNumber(String _officeNumber) {
		this.officeNumber = _officeNumber;
	}

	@Length(max = 45)
	@Column(name = "officeNumber")
	public String getOfficeNumber() {
		return this.officeNumber;
	}

	public void setHomeNumber(String _homeNumber) {
		this.homeNumber = _homeNumber;
	}

	@Length(max = 45)
	@Column(name = "homeNumber")
	public String getHomeNumber() {
		return this.homeNumber;
	}

	public void setPrimaryContact(String _primaryContact) {
		this.primaryContact = _primaryContact;
	}

	@NotNull
	@Length(max = 45)
	@Column(name = "primaryContact")
	public String getPrimaryContact() {
		return this.primaryContact;
	}

	public void setMedicalLicenceNumber(String _medicalLicenceNumber) {
		this.medicalLicenceNumber = _medicalLicenceNumber;
	}

	@Length(max = 250)
	@Column(name = "medicalLicenceNumber")
	public String getMedicalLicenceNumber() {
		return this.medicalLicenceNumber;
	}

	@Column(name = "isprofilecompleted")
	public Character getIsprofilecompleted() {
		return isprofilecompleted;
	}

	public void setIsprofilecompleted(Character isprofilecompleted) {
		this.isprofilecompleted = isprofilecompleted;
	}

	public void setTotalExperience(Integer _totalExperience) {
		this.totalExperience = _totalExperience;
	}

	@Column(name = "totalExperience")
	public Integer getTotalExperience() {
		return this.totalExperience;
	}

	public void setDateOfExpiry(Date _dateOfExpiry) {
		this.dateOfExpiry = _dateOfExpiry;
	}

	@JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSXXX", shape = Shape.STRING)
	@Column(name = "dateOfExpiry")
	public Date getDateOfExpiry() {
		return this.dateOfExpiry;
	}

	public void setActivatedDate(Date _activatedDate) {
		this.activatedDate = _activatedDate;
	}

	@JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSXXX", shape = Shape.STRING)
	@Column(name = "activatedDate")
	public Date getActivatedDate() {
		return this.activatedDate;
	}

	public void setActive(Character _active) {
		this.active = _active;
	}

	@Column(name = "active")
	public Character getActive() {
		return this.active;
	}

	public void setLocked(Character _locked) {
		this.locked = _locked;
	}

	@Column(name = "locked")
	public Character getLocked() {
		return this.locked;
	}

	public void setGender(String _gender) {
		this.gender = _gender;
	}

	@NotNull
	@Length(max = 45)
	@Column(name = "gender")
	public String getGender() {
		return this.gender;
	}

	public void setAveragerating(Double _averagerating) {
		this.averagerating = _averagerating;
	}

	@Column(name = "averagerating")
	public Double getAveragerating() {
		return this.averagerating;
	}

	public void setRetry(Integer _retry) {
		this.retry = _retry;
	}

	@Column(name = "retry")
	public Integer getRetry() {
		return this.retry;
	}

	@Column(name = "type")
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		BaseDoctor other = (BaseDoctor) o;
		if (ObjectUtil.isEqual(getFirstName(), other.getFirstName())
				&& ObjectUtil.isEqual(getLastName(), other.getLastName())
				&&ObjectUtil.isEqual(getUserName(), other.getUserName())
				&& ObjectUtil.isEqual(getMiddleName(), other.getMiddleName())
				&& ObjectUtil.isEqual(getSpecialization(), other.getSpecialization())
				&& ObjectUtil.isEqual(getEmailId(), other.getEmailId())
				&& ObjectUtil.isEqual(getPassword(), other.getPassword())
				&& ObjectUtil.isEqual(getLastLogin(), other.getLastLogin())
				&& ObjectUtil.isEqual(getCellNumber(), other.getCellNumber())
				&& ObjectUtil.isEqual(getOfficeNumber(), other.getOfficeNumber())
				&& ObjectUtil.isEqual(getHomeNumber(), other.getHomeNumber())
				&& ObjectUtil.isEqual(getPrimaryContact(), other.getPrimaryContact())
				&& ObjectUtil.isEqual(getMedicalLicenceNumber(), other.getMedicalLicenceNumber())
				&& ObjectUtil.isEqual(getTotalExperience(), other.getTotalExperience())
				&& ObjectUtil.isEqual(getDateOfExpiry(), other.getDateOfExpiry())
				&& ObjectUtil.isEqual(getActivatedDate(), other.getActivatedDate())
				&& ObjectUtil.isEqual(getActive(), other.getActive())
				&& ObjectUtil.isEqual(getLocked(), other.getLocked())
				&& ObjectUtil.isEqual(getGender(), other.getGender())
				&& ObjectUtil.isEqual(getRetry(), other.getRetry())
				&& ObjectUtil.isEqual(getAveragerating(), other.getAveragerating())
				&& ObjectUtil.isEqual(getIsprofilecompleted(), other.getIsprofilecompleted())
				&& ObjectUtil.isEqual(getType(), other.getType())
				&& ObjectUtil.isEqual(getCreatedBy(), other.getCreatedBy())
				&& ObjectUtil.isEqual(getCreatedDate(), other.getCreatedDate())
				&& ObjectUtil.isEqual(getLastUpdatedBy(), other.getLastUpdatedBy())
				&& ObjectUtil.isEqual(getLastUpdatedTs(), other.getLastUpdatedTs())) {
			return true;
		}
		return false;
	}

	@Override
	public int hashCode() {
		int result = 0;
		result = result + (doctorId != null ? doctorId.hashCode() : 0);

		return result;
	}

	public void validate(boolean validatePk) {
		resetValidationMessages();

		List<ValidationMessage> list = new ArrayList<ValidationMessage>();
		if (validatePk) {
			if (this.doctorId == null) {
				list.add(new ValidationMessage("Field " + FIELD_doctorId + " cannot be null"));
			}

		}
		if (StringUtil.isNullOrEmpty(this.firstName)) {
			list.add(new ValidationMessage("Field " + FIELD_firstName + " cannot be null"));
		}

		if ((this.firstName != null) && (this.firstName.length() > 150)) {
			list.add(new ValidationMessage("Field " + FIELD_firstName + " cannot be longer than: " + 150));
		}

		if ((this.lastName != null) && (this.lastName.length() > 150)) {
			list.add(new ValidationMessage("Field " + FIELD_lastName + " cannot be longer than: " + 150));
		}
		if (StringUtil.isNullOrEmpty(this.userName)) {
		      list.add(new ValidationMessage("Field " + FIELD_userName + " cannot be null"));
		    }

		    if ((this.userName != null) && (this.userName.length() > 95)) {
		      list.add(new ValidationMessage("Field " + FIELD_userName + " cannot be longer than: " + 95));
		    }
		if ((this.middleName != null) && (this.middleName.length() > 45)) {
			list.add(new ValidationMessage("Field " + FIELD_middleName + " cannot be longer than: " + 45));
		}

		if ((this.specialization != null) && (this.specialization.length() > 150)) {
			list.add(new ValidationMessage("Field " + FIELD_specialization + " cannot be longer than: " + 150));
		}

		if (StringUtil.isNullOrEmpty(this.emailId)) {
			list.add(new ValidationMessage("Field " + FIELD_emailId + " cannot be null"));
		}

		if ((this.emailId != null) && (this.emailId.length() > 250)) {
			list.add(new ValidationMessage("Field " + FIELD_emailId + " cannot be longer than: " + 250));
		}

		if (StringUtil.isNullOrEmpty(this.password)) {
			list.add(new ValidationMessage("Field " + FIELD_password + " cannot be null"));
		}

		if ((this.password != null) && (this.password.length() > 250)) {
			list.add(new ValidationMessage("Field " + FIELD_password + " cannot be longer than: " + 250));
		}
		if (StringUtil.isNullOrEmpty(this.cellNumber)) {
			list.add(new ValidationMessage("Field " + FIELD_cellNumber + " cannot be null"));
		}
		if ((this.cellNumber != null) && (this.cellNumber.length() > 45)) {
			list.add(new ValidationMessage("Field " + FIELD_cellNumber + " cannot be longer than: " + 45));
		}

		if ((this.officeNumber != null) && (this.officeNumber.length() > 45)) {
			list.add(new ValidationMessage("Field " + FIELD_officeNumber + " cannot be longer than: " + 45));
		}

		if ((this.homeNumber != null) && (this.homeNumber.length() > 45)) {
			list.add(new ValidationMessage("Field " + FIELD_homeNumber + " cannot be longer than: " + 45));
		}

		if (StringUtil.isNullOrEmpty(this.primaryContact)) {
			list.add(new ValidationMessage("Field " + FIELD_primaryContact + " cannot be null"));
		}
		List<String> pcEnums = Arrays.stream(PrimaryContactEnum.values()).map(pc -> pc.name())
				.collect(Collectors.toList());
		if (StringUtil.isNullOrEmpty(this.primaryContact) || !pcEnums.contains(this.primaryContact)) {
			list.add(new ValidationMessage(
					"Field " + FIELD_primaryContact + " either can not be null or it should be in " + pcEnums));
		}

		if ((this.primaryContact != null) && (this.primaryContact.length() > 45)) {
			list.add(new ValidationMessage("Field " + FIELD_primaryContact + " cannot be longer than: " + 45));
		}
		if (StringUtil.isNullOrEmpty(this.specialization)) {
			list.add(new ValidationMessage("Field " + FIELD_specialization + " cannot be null: "));
		}
		if ((this.medicalLicenceNumber != null) && (this.medicalLicenceNumber.length() > 250)) {
			list.add(new ValidationMessage("Field " + FIELD_medicalLicenceNumber + " cannot be longer than: " + 250));
		}

		if (StringUtil.isNullOrEmpty(this.gender)) {
			list.add(new ValidationMessage("Field " + FIELD_gender + " cannot be null"));
		}

		if ((this.gender != null) && (this.gender.length() > 45)) {
			list.add(new ValidationMessage("Field " + FIELD_gender + " cannot be longer than: " + 45));
		}

		if (this.isFromDB()) {
			if ((this.createdBy != null) && (this.createdBy.length() > 45)) {
				list.add(new ValidationMessage("Field " + FIELD_createdBy + " cannot be longer than: " + 45));
			}
		}

		if (this.isFromDB()) {
			if ((this.lastUpdatedBy != null) && (this.lastUpdatedBy.length() > 45)) {
				list.add(new ValidationMessage("Field " + FIELD_lastUpdatedBy + " cannot be longer than: " + 45));
			}
		}
		if (list.size() > 0)
			setHasValidationErrors(true);

		setValidationMessages(list);

	}

	@Override
	public final void setAuditFields() {
		if (!isFromDB()) {
			this.createdBy = AppThreadLocal.getUserName();
		}
		this.lastUpdatedBy = AppThreadLocal.getUserName();
		this.lastUpdatedTs = AppThreadLocal.getRequestStartDate();
	}

	@Override
	public String toString() {
		StringBuilder str = new StringBuilder();
		str.append("doctorId = " + doctorId + "\n");
		;
		str.append("firstName = " + firstName + "\n");
		str.append("lastName = " + lastName + "\n");
		str.append("middleName = " + middleName + "\n");
		str.append("specialization = " + specialization + "\n");
		str.append("emailId = " + emailId + "\n");
		str.append("password = " + password + "\n");
		str.append("lastLogin = " + lastLogin + "\n");
		str.append("cellNumber = " + cellNumber + "\n");
		str.append("officeNumber = " + officeNumber + "\n");
		str.append("homeNumber = " + homeNumber + "\n");
		str.append("primaryContact = " + primaryContact + "\n");
		str.append("medicalLicenceNumber = " + medicalLicenceNumber + "\n");
		str.append("totalExperience = " + totalExperience + "\n");
		str.append("dateOfExpiry = " + dateOfExpiry + "\n");
		str.append("activatedDate = " + activatedDate + "\n");
		str.append("active = " + active + "\n");
		str.append("locked = " + locked + "\n");
		str.append("isprofilecompleted = " + isprofilecompleted + "\n");
		str.append("retry = " + retry + "\n");
		str.append("gender = " + gender + "\n");
		str.append("averagerating = " + averagerating + "\n");
		str.append("userName = " + userName + "\n");
		str.append("type = " + type + "\n");
		str.append("createdBy = " + createdBy + "\n");
		str.append("createdDate = " + createdDate + "\n");
		str.append("lastUpdatedBy = " + lastUpdatedBy + "\n");
		str.append("lastUpdatedTs = " + lastUpdatedTs + "\n");
		return str.toString();
	}

	@Transient
	@Override
	public final boolean isKeyNull() {
		return (doctorId == null);
	}

	@Transient
	@Override
	public final List<KeyField> getKeyField() {
		List<KeyField> list = new ArrayList<KeyField>();
		list.add(new KeyField(FIELD_doctorId, getDoctorId()));
		return list;
	}

	@Transient
	@Override
	public Long getId() {
		return getDoctorId();
	}

	@Override
	public Object clone() throws CloneNotSupportedException {
		Doctor doctor = new Doctor();
		doctor.setFromDB(false);
		doctor.setFirstName(getFirstName());
		doctor.setLastName(getLastName());
		doctor.setMiddleName(getMiddleName());
		doctor.setSpecialization(getSpecialization());
		doctor.setEmailId(getEmailId());
		doctor.setPassword(getPassword());
		doctor.setLastLogin(getLastLogin());
		doctor.setCellNumber(getCellNumber());
		doctor.setOfficeNumber(getOfficeNumber());
		doctor.setHomeNumber(getHomeNumber());
		doctor.setPrimaryContact(getPrimaryContact());
		doctor.setMedicalLicenceNumber(getMedicalLicenceNumber());
		doctor.setTotalExperience(getTotalExperience());
		doctor.setDateOfExpiry(getDateOfExpiry());
		doctor.setActivatedDate(getActivatedDate());
		doctor.setActive(getActive());
		doctor.setLocked(getLocked());
		doctor.setGender(getGender());
		doctor.setAveragerating(getAveragerating());
		doctor.setRetry(getRetry());
		doctor.setIsprofilecompleted(getIsprofilecompleted());
		doctor.setUserName(getUserName());
		doctor.setType(getType());
		doctor.setCreatedDate(getCreatedDate());
		// afterClone(doctor);
		return doctor;
	}
}