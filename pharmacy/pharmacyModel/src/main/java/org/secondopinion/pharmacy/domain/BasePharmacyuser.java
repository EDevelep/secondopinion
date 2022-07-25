package org.secondopinion.pharmacy.domain;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

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
import org.secondopinion.enums.PrimaryContactEnum;
import org.secondopinion.pharmacy.dto.Pharmacyuser;
import org.secondopinion.utils.ObjectUtil;
import org.secondopinion.utils.StringUtil;
import org.secondopinion.utils.threadlocal.AppThreadLocal;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonFormat.Shape;

@MappedSuperclass
public abstract class BasePharmacyuser extends BaseDomainObject<Long> {
//pharmacyaddressId
	public static final String FIELD_pharmacyUserId = "pharmacyUserId";
	public static final String FIELD_pharmacyaddressId = "pharmacyaddressId";
	public static final String FIELD_name = "pharmacyName";
	public static final String FIELD_firstName = "firstName";
	public static final String FIELD_lastName = "lastName";
	public static final String FIELD_middleName = "middleName";
	public static final String FIELD_emailId = "emailId";
	public static final String FIELD_password = "password";
	public static final String FIELD_cellNumber = "cellNumber";
	public static final String FIELD_homeNumber = "homeNumber";
	public static final String FIELD_officeNumber = "officeNumber";
	public static final String FIELD_primaryContact = "primaryContact";
	public static final String FIELD_active = "active";
	public static final String FIELD_locked = "locked";
	public static final String FIELD_retry = "retry";
	public static final String FIELD_lastLogin = "lastLogin";
	public static final String FIELD_otp = "otp";
	public static final String FIELD_emailotp = "emailotp";
	public static final String FIELD_userName = "userName";
	public static final String FIELD_verificationId = "verificationId";

	private Long pharmacyUserId;
	private Long pharmacyaddressId;
	private String firstName;
	private String lastName;
	private String middleName;
	private String emailId;
	private String password;
	private String cellNumber;
	private String officeNumber;
	private String homeNumber;
	private String primaryContact;
	private Character active;
	private Character locked;
	private Date lastLogin;
	private Integer retry;
	private String verificationId;
	private Integer otp;
	private Integer emailotp;
	private String pharmacyName;
	public String userName;

	public void setPharmacyUserId(Long _pharmacyUserId) {
		this.pharmacyUserId = _pharmacyUserId;
	}

	@Id
	@Column(name = "pharmacyUserId")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Long getPharmacyUserId() {
		return this.pharmacyUserId;
	}

	public void setPharmacyaddressId(Long _pharmacyaddressId) {
		this.pharmacyaddressId = _pharmacyaddressId;
	}

	@NotNull
	@Column(name = "pharmacyaddressId")
	public Long getPharmacyaddressId() {
		return this.pharmacyaddressId;
	}

	public void setFirstName(String _firstName) {
		this.firstName = _firstName;
	}

	@Length(max = 45)
	@Column(name = "firstName")
	public String getFirstName() {
		return this.firstName;
	}

	public void setLastName(String _lastName) {
		this.lastName = _lastName;
	}

	@Length(max = 45)
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

	public void setEmailId(String _emailId) {
		this.emailId = _emailId;
	}

	@NotNull
	@Length(max = 45)
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

	public void setCellNumber(String _cellNumber) {
		this.cellNumber = _cellNumber;
	}

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

	public void setActive(Character _active) {
		this.active = _active;
	}

	@Column(name = "active")
	public Character getActive() {
		return this.active;
	}

	public void setLastLogin(Date _lastLogin) {
		this.lastLogin = _lastLogin;
	}

	@JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSXXX", shape = Shape.STRING)
	@Column(name = "lastLogin")
	public Date getLastLogin() {
		return this.lastLogin;
	}

	public void setVerificationId(String verificationId) {
		this.verificationId = verificationId;
	}

	@Length(max = 255)
	@Column(name = "verificationId")
	public String getVerificationId() {
		return verificationId;
	}

	public void setRetry(Integer _retry) {
		this.retry = _retry;
	}

	@Column(name = "retry")
	public Integer getRetry() {
		return this.retry;
	}

	public void setLocked(Character _locked) {
		this.locked = _locked;
	}

	@Column(name = "locked")
	public Character getLocked() {
		return this.locked;
	}

	public void setOtp(Integer _otp) {
		this.otp = _otp;
	}

	@Column(name = "otp")
	public Integer getOtp() {
		return this.otp;
	}

	@Column(name = "emailotp")
	public Integer getEmailotp() {
		return emailotp;
	}
	@Column(name = "userName")
	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public void setEmailotp(Integer emailotp) {
		this.emailotp = emailotp;
	}

	@Length(max = 45)
	@Column(name = "pharmacyName")
	public String getPharmacyName() {
		return pharmacyName;
	}

	public void setPharmacyName(String pharmacyName) {
		this.pharmacyName = pharmacyName;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		BasePharmacyuser other = (BasePharmacyuser) o;
		if (ObjectUtil.isEqual(getPharmacyaddressId(), other.getPharmacyaddressId())
				&& ObjectUtil.isEqual(getFirstName(), other.getFirstName())
				&& ObjectUtil.isEqual(getLastName(), other.getLastName())
				&& ObjectUtil.isEqual(getMiddleName(), other.getMiddleName())
				&& ObjectUtil.isEqual(getEmailId(), other.getEmailId())
				&& ObjectUtil.isEqual(getPassword(), other.getPassword())
				&& ObjectUtil.isEqual(getCellNumber(), other.getCellNumber())
				&& ObjectUtil.isEqual(getOfficeNumber(), other.getOfficeNumber())
				&& ObjectUtil.isEqual(getHomeNumber(), other.getHomeNumber())
				&& ObjectUtil.isEqual(getPrimaryContact(), other.getPrimaryContact())
				&& ObjectUtil.isEqual(getActive(), other.getActive())
				&& ObjectUtil.isEqual(getRetry(), other.getRetry())
				&& ObjectUtil.isEqual(getLocked(), other.getLocked())
				&& ObjectUtil.isEqual(getLastLogin(), other.getLastLogin())
				&& ObjectUtil.isEqual(getOtp(), other.getOtp())
				&& ObjectUtil.isEqual(getEmailotp(), other.getEmailotp())
				&& ObjectUtil.isEqual(getVerificationId(), other.getVerificationId())
				&& ObjectUtil.isEqual(getUserName(), other.getUserName())
				&& ObjectUtil.isEqual(getLastUpdatedBy(), other.getLastUpdatedBy())
				&& ObjectUtil.isEqual(getLastUpdatedTs(), other.getLastUpdatedTs())) {
			return true;
		}
		return false;
	}

	@Override
	public int hashCode() {
		int result = 0;
		result = result + (pharmacyUserId != null ? pharmacyUserId.hashCode() : 0);

		return result;
	}

	public void validate(boolean validatePk) {
		resetValidationMessages();

		List<ValidationMessage> list = new ArrayList<ValidationMessage>();
		if (validatePk) {
			if (this.pharmacyUserId == null) {
				list.add(new ValidationMessage("Field " + FIELD_pharmacyUserId + " cannot be null"));
			}

		}
		if (this.pharmacyaddressId == null) {
			list.add(new ValidationMessage("Field " + FIELD_pharmacyaddressId + " cannot be null"));
		}

		if ((this.firstName != null) && (this.firstName.length() > 45)) {
			list.add(new ValidationMessage("Field " + FIELD_firstName + " cannot be longer than: " + 45));
		}

		if ((this.lastName != null) && (this.lastName.length() > 45)) {
			list.add(new ValidationMessage("Field " + FIELD_lastName + " cannot be longer than: " + 45));
		}

		if ((this.middleName != null) && (this.middleName.length() > 45)) {
			list.add(new ValidationMessage("Field " + FIELD_middleName + " cannot be longer than: " + 45));
		}

		if (StringUtil.isNullOrEmpty(this.emailId)) {
			list.add(new ValidationMessage("Field " + FIELD_emailId + " cannot be null"));
		}

		if ((this.emailId != null) && (this.emailId.length() > 45)) {
			list.add(new ValidationMessage("Field " + FIELD_emailId + " cannot be longer than: " + 45));
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

		if (StringUtil.isNullOrEmpty(this.verificationId)) {
			list.add(new ValidationMessage("Field " + FIELD_verificationId + " cannot be null. "));
		}
		if ((this.verificationId != null) && this.verificationId.length() > 255) {
			list.add(new ValidationMessage("Field " + FIELD_verificationId + " cannot be longer than: " + 255));
		}
		if (this.active == null) {
			list.add(new ValidationMessage("Field " + FIELD_active + " cannot be null"));
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
		}
		this.lastUpdatedBy = AppThreadLocal.getUserName();
		this.lastUpdatedTs = AppThreadLocal.getRequestStartDate();
	}

	@Override
	public String toString() {
		StringBuilder str = new StringBuilder();
		str.append("pharmacyUserId = " + pharmacyUserId + "\n");
		;
		str.append("pharmacyaddressId = " + pharmacyaddressId + "\n");
		str.append("firstName = " + firstName + "\n");
		str.append("lastName = " + lastName + "\n");
		str.append("middleName = " + middleName + "\n");
		str.append("emailId = " + emailId + "\n");
		str.append("password = " + password + "\n");
		str.append("cellNumber = " + cellNumber + "\n");
		str.append("officeNumber = " + officeNumber + "\n");
		str.append("homeNumber = " + homeNumber + "\n");
		str.append("primaryContact = " + primaryContact + "\n");
		str.append("retry = " + retry + "\n");
		str.append("active = " + active + "\n");
		str.append("locked = " + locked + "\n");
		str.append("otp = " + otp + "\n");
		str.append("emailotp = " + emailotp + "\n");
		str.append("lastLogin = " + lastLogin + "\n");
		str.append("verificationId = " + verificationId + "\n");
		str.append("pharmacyName = " + pharmacyName + "\n");
		str.append("userName = " + userName + "\n");
		str.append("lastUpdatedBy = " + lastUpdatedBy + "\n");
		str.append("lastUpdatedTs = " + lastUpdatedTs + "\n");
		return str.toString();
	}

	@Transient
	@Override
	public final boolean isKeyNull() {
		return (pharmacyUserId == null);
	}

	@Transient
	@Override
	public final List<KeyField> getKeyField() {
		List<KeyField> list = new ArrayList<KeyField>();
		list.add(new KeyField(FIELD_pharmacyUserId, getPharmacyUserId()));
		return list;
	}

	@Transient
	@Override
	public Long getId() {
		return getPharmacyUserId();
	}

	@Override
	public Object clone() throws CloneNotSupportedException {
		Pharmacyuser pharmacyuser = new Pharmacyuser();
		pharmacyuser.setFromDB(false);
		pharmacyuser.setPharmacyaddressId(getPharmacyaddressId());
		pharmacyuser.setFirstName(getFirstName());
		pharmacyuser.setLastName(getLastName());
		pharmacyuser.setMiddleName(getMiddleName());
		pharmacyuser.setEmailId(getEmailId());
		pharmacyuser.setPassword(getPassword());
		pharmacyuser.setCellNumber(getCellNumber());
		pharmacyuser.setOfficeNumber(getOfficeNumber());
		pharmacyuser.setHomeNumber(getHomeNumber());
		pharmacyuser.setPrimaryContact(getPrimaryContact());
		pharmacyuser.setActive(getActive());
		pharmacyuser.setLocked(getLocked());
		pharmacyuser.setRetry(getRetry());
		pharmacyuser.setLastLogin(getLastLogin());
		pharmacyuser.setOtp(getOtp());
		pharmacyuser.setEmailotp(getEmailotp());
		pharmacyuser.setPharmacyName(getPharmacyName());
		pharmacyuser.setUserName(getUserName());
		pharmacyuser.setVerificationId(getVerificationId());
		// afterClone(pharmacyuser);
		return pharmacyuser;
	}
}