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
import org.secondopinion.pharmacy.dto.Pharmacy;
import org.secondopinion.utils.ObjectUtil;
import org.secondopinion.utils.StringUtil;
import org.secondopinion.utils.threadlocal.AppThreadLocal;

@MappedSuperclass
public abstract class BasePharmacy extends BaseDomainObject<Long> {

	public static final String FIELD_pharmacyId = "pharmacyId";
	public static final String FIELD_pharmacyaddressId = "pharmacyaddressId";
	public static final String FIELD_primaryUser = "primaryUser";
	public static final String FIELD_name = "name";
	public static final String FIELD_licenseNumber = "licenseNumber";
	public static final String FIELD_phoneNumber = "phoneNumber";
	public static final String FIELD_secondaryPhoneNumber = "secondaryPhoneNumber";
	public static final String FIELD_emailId = "emailId";
	public static final String FIELD_active = "active";
	public static final String FIELD_profilePic = "profilePic";
	public static final String FIELD_branchName = "branchName";
	public static final String FIELD_branchCode = "branchCode";
	public static final String FIELD_averagerating = "averagerating";
	public static final String FIELD_pharmacyAdminId = "pharmacyAdminId";
	public static final String FIELD_activatedDate = "activatedDate";
	
	private Long pharmacyId;
	private Long pharmacyaddressId;
	private Long primaryUser;
	private String name;
	private String licenseNumber;
	private String branchName;
	private String branchCode;
	private String phoneNumber;
	private String secondaryPhoneNumber;
	private String emailId;
	private Character active;
	private Double averagerating;
	private byte[] profilePic;
	private Long pharmacyAdminId;
	private Date activatedDate;
	public void setPharmacyId(Long _pharmacyId) {
		this.pharmacyId = _pharmacyId;
	}

	@Id
	@Column(name = "pharmacyId")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Long getPharmacyId() {
		return this.pharmacyId;
	}

	public void setPharmacyaddressId(Long pharmacyaddressId) {
		this.pharmacyaddressId = pharmacyaddressId;
	}

	@Column(name = "pharmacyaddressId")
	public Long getPharmacyaddressId() {
		return pharmacyaddressId;
	}

	public void setName(String _name) {
		this.name = _name;
	}

	public void setPrimaryUser(Long _primaryUser) {
		this.primaryUser = _primaryUser;
	}

	@Column(name = "primaryUser")
	public Long getPrimaryUser() {
		return this.primaryUser;
	}

	@NotNull
	@Length(max = 250)
	@Column(name = "name")
	public String getName() {
		return this.name;
	}

	public void setLicenseNumber(String _licenseNumber) {
		this.licenseNumber = _licenseNumber;
	}

	@NotNull
	@Length(max = 45)
	@Column(name = "licenseNumber")
	public String getLicenseNumber() {
		return this.licenseNumber;
	}

	public void setPhoneNumber(String _phoneNumber) {
		this.phoneNumber = _phoneNumber;
	}

	@NotNull
	@Length(max = 45)
	@Column(name = "phoneNumber")
	public String getPhoneNumber() {
		return this.phoneNumber;
	}

	public void setSecondaryPhoneNumber(String _secondaryPhoneNumber) {
		this.secondaryPhoneNumber = _secondaryPhoneNumber;
	}

	@NotNull
	@Length(max = 45)
	@Column(name = "secondaryPhoneNumber")
	public String getSecondaryPhoneNumber() {
		return this.secondaryPhoneNumber;
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

	public void setActive(Character _active) {
		this.active = _active;
	}

	@NotNull
	@Column(name = "active")
	public Character getActive() {
		return this.active;
	}

	public void setAveragerating(Double _averagerating) {
		this.averagerating = _averagerating;
	}

	@Column(name = "averagerating")
	public Double getAveragerating() {
		return this.averagerating;
	}

	@Column(name = "profilePic")
	public byte[] getProfilePic() {
		return profilePic;
	}

	public void setProfilePic(byte[] profilePic) {
		this.profilePic = profilePic;
	}

	@Column(name = "branchName")
	public String getBranchName() {
		return branchName;
	}

	public void setBranchName(String branchName) {
		this.branchName = branchName;
	}

	@Column(name = "branchCode")
	public String getBranchCode() {
		return branchCode;
	}

	public void setBranchCode(String branchCode) {
		this.branchCode = branchCode;
	}

	@Column(name = "pharmacyAdminId")
	public Long getPharmacyAdminId() {
		return pharmacyAdminId;
	}

	public void setPharmacyAdminId(Long pharmacyAdminId) {
		this.pharmacyAdminId = pharmacyAdminId;
	}

	@Column(name = "activatedDate")
	public Date getActivatedDate() {
		return activatedDate;
	}

	public void setActivatedDate(Date activatedDate) {
		this.activatedDate = activatedDate;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		BasePharmacy other = (BasePharmacy) o;
		if (ObjectUtil.isEqual(getPharmacyaddressId(), other.getPharmacyaddressId())
				&& ObjectUtil.isEqual(getPrimaryUser(), other.getPrimaryUser())
				&& ObjectUtil.isEqual(getName(), other.getName())
				&& ObjectUtil.isEqual(getLicenseNumber(), other.getLicenseNumber())
				&& ObjectUtil.isEqual(getPhoneNumber(), other.getPhoneNumber())
				&& ObjectUtil.isEqual(getSecondaryPhoneNumber(), other.getSecondaryPhoneNumber())
				&& ObjectUtil.isEqual(getEmailId(), other.getEmailId())
				&& ObjectUtil.isEqual(getActive(), other.getActive())
				&& ObjectUtil.isEqual(getAveragerating(), other.getAveragerating())
				&& ObjectUtil.isEqual(getProfilePic(), other.getProfilePic())
				&& ObjectUtil.isEqual(getBranchCode(), other.getBranchCode())
				&& ObjectUtil.isEqual(getBranchName(), other.getBranchName())
				&& ObjectUtil.isEqual(getPharmacyAdminId(), other.getPharmacyAdminId())
				&& ObjectUtil.isEqual(getActivatedDate(), other.getActivatedDate())
				&& ObjectUtil.isEqual(getLastUpdatedBy(), other.getLastUpdatedBy())
				&& ObjectUtil.isEqual(getLastUpdatedTs(), other.getLastUpdatedTs())) {
			return true;
		}
		return false;
	}

	@Override
	public int hashCode() {
		int result = 0;
		result = result + (pharmacyId != null ? pharmacyId.hashCode() : 0);

		return result;
	}

	public void validate(boolean validatePk) {
		resetValidationMessages();

		List<ValidationMessage> list = new ArrayList<ValidationMessage>();
		if (validatePk) {
			if (this.pharmacyId == null) {
				list.add(new ValidationMessage("Field " + FIELD_pharmacyId + " cannot be null"));
			}

		}
		if (StringUtil.isNullOrEmpty(this.name)) {
			list.add(new ValidationMessage("Field " + FIELD_name + " cannot be null"));
		}

		if ((this.name != null) && (this.name.length() > 250)) {
			list.add(new ValidationMessage("Field " + FIELD_name + " cannot be longer than: " + 250));
		}

		if (StringUtil.isNullOrEmpty(this.licenseNumber)) {
			list.add(new ValidationMessage("Field " + FIELD_licenseNumber + " cannot be null"));
		}

		if ((this.licenseNumber != null) && (this.licenseNumber.length() > 45)) {
			list.add(new ValidationMessage("Field " + FIELD_licenseNumber + " cannot be longer than: " + 45));
		}

		if (StringUtil.isNullOrEmpty(this.phoneNumber)) {
			list.add(new ValidationMessage("Field " + FIELD_phoneNumber + " cannot be null"));
		}

		if ((this.phoneNumber != null) && (this.phoneNumber.length() > 45)) {
			list.add(new ValidationMessage("Field " + FIELD_phoneNumber + " cannot be longer than: " + 45));
		}

		if ((this.secondaryPhoneNumber != null) && (this.secondaryPhoneNumber.length() > 45)) {
			list.add(new ValidationMessage("Field " + FIELD_secondaryPhoneNumber + " cannot be longer than: " + 45));
		}

		if (StringUtil.isNullOrEmpty(this.emailId)) {
			list.add(new ValidationMessage("Field " + FIELD_emailId + " cannot be null"));
		}

		if ((this.emailId != null) && (this.emailId.length() > 45)) {
			list.add(new ValidationMessage("Field " + FIELD_emailId + " cannot be longer than: " + 45));
		}

		if (this.active == null) {
			list.add(new ValidationMessage("Field " + FIELD_active + " cannot be null"));
		}

		if (this.isFromDB()) {
			if (StringUtil.isNullOrEmpty(this.lastUpdatedBy)) {
				list.add(new ValidationMessage("Field " + FIELD_lastUpdatedBy + " cannot be null"));
			}
		}
		if (this.isFromDB()) {
			if ((this.lastUpdatedBy != null) && (this.lastUpdatedBy.length() > 45)) {
				list.add(new ValidationMessage("Field " + FIELD_lastUpdatedBy + " cannot be longer than: " + 45));
			}
		}
		if (this.isFromDB()) {
			if (this.lastUpdatedTs == null) {
				list.add(new ValidationMessage("Field " + FIELD_lastUpdatedTs + " cannot be null"));
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
		str.append("pharmacyId = " + pharmacyId + "\n");
		str.append("pharmacyaddressId = " + pharmacyaddressId + "\n");
		str.append("primaryUser = " + primaryUser + "\n");
		str.append("name = " + name + "\n");
		str.append("licenseNumber = " + licenseNumber + "\n");
		str.append("phoneNumber = " + phoneNumber + "\n");
		str.append("secondaryPhoneNumber = " + secondaryPhoneNumber + "\n");
		str.append("emailId = " + emailId + "\n");
		str.append("active = " + active + "\n");
		str.append("averagerating = " + averagerating + "\n");
		str.append("profilePic = " + profilePic + "\n");
		str.append("branchCode = " + branchCode + "\n");
		str.append("branchName = " + branchName + "\n");
		str.append("pharmacyAdminId = " + pharmacyAdminId + "\n");
		str.append("activatedDate = " + activatedDate + "\n");
		str.append("lastUpdatedBy = " + lastUpdatedBy + "\n");
		str.append("lastUpdatedTs = " + lastUpdatedTs + "\n");
		return str.toString();
	}

	@Transient
	@Override
	public final boolean isKeyNull() {
		return (pharmacyId == null);
	}

	@Transient
	@Override
	public final List<KeyField> getKeyField() {
		List<KeyField> list = new ArrayList<KeyField>();
		list.add(new KeyField(FIELD_pharmacyId, getPharmacyId()));
		return list;
	}

	@Transient
	@Override
	public Long getId() {
		return getPharmacyId();
	}

	@Override
	public Object clone() throws CloneNotSupportedException {
		Pharmacy pharmacy = new Pharmacy();
		pharmacy.setFromDB(false);
		pharmacy.setPharmacyaddressId(getPharmacyaddressId());
		pharmacy.setPrimaryUser(getPrimaryUser());
		pharmacy.setName(getName());
		pharmacy.setLicenseNumber(getLicenseNumber());
		pharmacy.setPhoneNumber(getPhoneNumber());
		pharmacy.setSecondaryPhoneNumber(getSecondaryPhoneNumber());
		pharmacy.setEmailId(getEmailId());
		pharmacy.setProfilePic(getProfilePic());
		pharmacy.setActive(getActive());
		pharmacy.setAveragerating(getAveragerating());
		pharmacy.setBranchCode(getBranchCode());
		pharmacy.setBranchName(getBranchName());
		pharmacy.setActivatedDate(getActivatedDate());
		pharmacy.setPharmacyAdminId(getPharmacyAdminId());
		// afterClone(pharmacy);
		return pharmacy;
	}
}