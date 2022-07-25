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
import org.secondopinion.diagnosticcenter.dto.Diagnosticcenter;
import org.secondopinion.domain.BaseDomainObject;
import org.secondopinion.domain.KeyField;
import org.secondopinion.domain.validation.ValidationMessage;
import org.secondopinion.utils.ObjectUtil;
import org.secondopinion.utils.StringUtil;
import org.secondopinion.utils.threadlocal.AppThreadLocal;

@MappedSuperclass
public abstract class BaseDiagnosticcenter extends BaseDomainObject<Long> {

	public static final String FIELD_diagnosticcenterId = "diagnosticcenterId";
	public static final String FIELD_name = "name";
	public static final String FIELD_details = "details";
	public static final String FIELD_newlyRegistered = "newlyRegistered";
	public static final String FIELD_primaryUser = "primaryUser";
	public static final String FIELD_cellNumber = "cellNumber";
	public static final String FIELD_primaryUserEmailId = "primaryUserEmailId";
	public static final String FIELD_primaryDataCenterAddressId = "primaryDataCenterAddressId";
	public static final String FIELD_validationId = "validationId";
	public static final String FIELD_verifiedBy = "verifiedBy";
	public static final String FIELD_verifiedByUserName = "verifiedByUserName";
	public static final String FIELD_websiteURL = "websiteURL";
	public static final String FIELD_averagerating = "averagerating";
	public static final String FIELD_profilePic = "profilePic";
	public static final String FIELD_active = "active";
	public static final String FIELD_diagnosticCenteradminId = "diagnosticCenteradminId";
	public static final String FIELD_report = "report";

	private Long diagnosticcenterId;
	private String name;
	private String details;
	private String newlyRegistered;
	private Long primaryUser;
	private String primaryUserEmailId;
	private Long primaryDataCenterAddressId;
	private String validationId;
	private String cellNumber;
	private Long verifiedBy;
	private String verifiedByUserName;
	private Double averagerating;
	private String websiteURL;
	private Character active;
	private byte[] profilePic;
	private String branchName;
	private String branchCode;
	private Long diagnosticCenteradminId;
	private byte[] report;

	public void setDiagnosticcenterId(Long _diagnosticcenterId) {
		this.diagnosticcenterId = _diagnosticcenterId;
	}

	@Id
	@Column(name = "diagnosticcenterId")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Long getDiagnosticcenterId() {
		return this.diagnosticcenterId;
	}

	public void setName(String _name) {
		this.name = _name;
	}

	@NotNull
	@Length(max = 1000)
	@Column(name = "name")
	public String getName() {
		return this.name;
	}

	public void setDetails(String _details) {
		this.details = _details;
	}

	@Length(max = 65535)
	@Column(name = "details")
	public String getDetails() {
		return this.details;
	}

	public void setNewlyRegistered(String _newlyRegistered) {
		this.newlyRegistered = _newlyRegistered;
	}

	@Length(max = 4)
	@Column(name = "newlyRegistered")
	public String getNewlyRegistered() {
		return this.newlyRegistered;
	}

	public void setPrimaryUser(Long _primaryUser) {
		this.primaryUser = _primaryUser;
	}

	@Column(name = "primaryUser")
	public Long getPrimaryUser() {
		return this.primaryUser;
	}

	public void setPrimaryUserEmailId(String _primaryUserEmailId) {
		this.primaryUserEmailId = _primaryUserEmailId;
	}

	@Length(max = 1000)
	@Column(name = "primaryUserEmailId")
	public String getPrimaryUserEmailId() {
		return this.primaryUserEmailId;
	}

	public void setPrimaryDataCenterAddressId(Long _primaryDataCenterAddressId) {
		this.primaryDataCenterAddressId = _primaryDataCenterAddressId;
	}

	@Column(name = "primaryDataCenterAddressId")
	public Long getPrimaryDataCenterAddressId() {
		return this.primaryDataCenterAddressId;
	}

	public void setValidationId(String _validationId) {
		this.validationId = _validationId;
	}

	@Length(max = 1000)
	@Column(name = "validationId")
	public String getValidationId() {
		return this.validationId;
	}

	public void setVerifiedBy(Long _verifiedBy) {
		this.verifiedBy = _verifiedBy;
	}

	@Column(name = "verifiedBy")
	public Long getVerifiedBy() {
		return this.verifiedBy;
	}

	public void setVerifiedByUserName(String _verifiedByUserName) {
		this.verifiedByUserName = _verifiedByUserName;
	}

	@Length(max = 180)
	@Column(name = "verifiedByUserName")
	public String getVerifiedByUserName() {
		return this.verifiedByUserName;
	}

	public void setWebsiteURL(String _websiteURL) {
		this.websiteURL = _websiteURL;
	}

	@Length(max = 1000)
	@Column(name = "websiteURL")
	public String getWebsiteURL() {
		return this.websiteURL;
	}

	public void setActive(Character _active) {
		this.active = _active;
	}

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

	public void setCellNumber(String _cellNumber) {
		this.cellNumber = _cellNumber;
	}

	@Length(max = 45)
	@Column(name = "cellNumber")
	public String getCellNumber() {
		return this.cellNumber;
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

	@Column(name = "diagnosticCenteradminId")
	public Long getDiagnosticCenteradminId() {
		return diagnosticCenteradminId;
	}

	public void setDiagnosticCenteradminId(Long diagnosticCenteradminId) {
		this.diagnosticCenteradminId = diagnosticCenteradminId;
	}
	@Column(name = "report")
	public byte[] getReport() {
		return report;
	}

	public void setReport(byte[] report) {
		this.report = report;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		BaseDiagnosticcenter other = (BaseDiagnosticcenter) o;
		if (ObjectUtil.isEqual(getName(), other.getName()) && ObjectUtil.isEqual(getDetails(), other.getDetails())
				&& ObjectUtil.isEqual(getNewlyRegistered(), other.getNewlyRegistered())
				&& ObjectUtil.isEqual(getPrimaryUser(), other.getPrimaryUser())
				&& ObjectUtil.isEqual(getPrimaryUserEmailId(), other.getPrimaryUserEmailId())
				&& ObjectUtil.isEqual(getPrimaryDataCenterAddressId(), other.getPrimaryDataCenterAddressId())
				&& ObjectUtil.isEqual(getValidationId(), other.getValidationId())
				&& ObjectUtil.isEqual(getVerifiedBy(), other.getVerifiedBy())
				&& ObjectUtil.isEqual(getVerifiedByUserName(), other.getVerifiedByUserName())
				&& ObjectUtil.isEqual(getWebsiteURL(), other.getWebsiteURL())
				&& ObjectUtil.isEqual(getActive(), other.getActive())
				&& ObjectUtil.isEqual(getAveragerating(), other.getAveragerating())
				&& ObjectUtil.isEqual(getCellNumber(), other.getCellNumber())
				&& ObjectUtil.isEqual(getProfilePic(), other.getProfilePic())
				&& ObjectUtil.isEqual(getBranchName(), other.getBranchName())
				&& ObjectUtil.isEqual(getBranchCode(), other.getBranchCode())
				&& ObjectUtil.isEqual(getDiagnosticCenteradminId(), other.getDiagnosticCenteradminId())
				&& ObjectUtil.isEqual(getReport(), other.getReport())
				&& ObjectUtil.isEqual(getLastUpdatedBy(), other.getLastUpdatedBy())
				&& ObjectUtil.isEqual(getLastUpdatedTs(), other.getLastUpdatedTs())) {
			return true;
		}
		return false;
	}

	@Override
	public int hashCode() {
		int result = 0;
		result = result + (diagnosticcenterId != null ? diagnosticcenterId.hashCode() : 0);

		return result;
	}

	public void validate(boolean validatePk) {
		resetValidationMessages();

		List<ValidationMessage> list = new ArrayList<ValidationMessage>();
		if (validatePk) {
			if (this.diagnosticcenterId == null) {
				list.add(new ValidationMessage("Field " + FIELD_diagnosticcenterId + " cannot be null"));
			}

		}
		if (StringUtil.isNullOrEmpty(this.name)) {
			list.add(new ValidationMessage("Field " + FIELD_name + " cannot be null"));
		}

		if ((this.name != null) && (this.name.length() > 1000)) {
			list.add(new ValidationMessage("Field " + FIELD_name + " cannot be longer than: " + 1000));
		}

		if ((this.details != null) && (this.details.length() > 65535)) {
			list.add(new ValidationMessage("Field " + FIELD_details + " cannot be longer than: " + 65535));
		}

		if ((this.newlyRegistered != null) && (this.newlyRegistered.length() > 4)) {
			list.add(new ValidationMessage("Field " + FIELD_newlyRegistered + " cannot be longer than: " + 4));
		}

		if ((this.primaryUserEmailId != null) && (this.primaryUserEmailId.length() > 1000)) {
			list.add(new ValidationMessage("Field " + FIELD_primaryUserEmailId + " cannot be longer than: " + 1000));
		}

		if ((this.validationId != null) && (this.validationId.length() > 1000)) {
			list.add(new ValidationMessage("Field " + FIELD_validationId + " cannot be longer than: " + 1000));
		}

		if ((this.verifiedByUserName != null) && (this.verifiedByUserName.length() > 180)) {
			list.add(new ValidationMessage("Field " + FIELD_verifiedByUserName + " cannot be longer than: " + 180));
		}

		if ((this.websiteURL != null) && (this.websiteURL.length() > 1000)) {
			list.add(new ValidationMessage("Field " + FIELD_websiteURL + " cannot be longer than: " + 1000));
		}

		if (this.active == null) {
			list.add(new ValidationMessage("Field " + FIELD_active + " cannot be null "));
		}

		if (this.isFromDB()) {
			if ((this.lastUpdatedBy != null) && (this.lastUpdatedBy.length() > 100)) {
				list.add(new ValidationMessage("Field " + FIELD_lastUpdatedBy + " cannot be longer than: " + 100));
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
		str.append("diagnosticcenterId = " + diagnosticcenterId + "\n");
		;
		str.append("name = " + name + "\n");
		str.append("details = " + details + "\n");
		str.append("newlyRegistered = " + newlyRegistered + "\n");
		str.append("primaryUser = " + primaryUser + "\n");
		str.append("primaryUserEmailId = " + primaryUserEmailId + "\n");
		str.append("primaryDataCenterAddressId = " + primaryDataCenterAddressId + "\n");
		str.append("validationId = " + validationId + "\n");
		str.append("verifiedBy = " + verifiedBy + "\n");
		str.append("verifiedByUserName = " + verifiedByUserName + "\n");
		str.append("websiteURL = " + websiteURL + "\n");
		str.append("active = " + active + "\n");
		str.append("cellNumber = " + cellNumber + "\n");
		str.append("averagerating = " + averagerating + "\n");
		str.append("branchName = " + branchName + "\n");
		str.append("branchCode = " + branchCode + "\n");
		str.append("profilePic = " + profilePic + "\n");
		str.append("report = " + report + "\n");
		str.append("diagnosticCenteradminId = " + diagnosticCenteradminId + "\n");
		str.append("lastUpdatedBy = " + lastUpdatedBy + "\n");
		str.append("lastUpdatedTs = " + lastUpdatedTs + "\n");
		return str.toString();
	}

	@Transient
	@Override
	public final boolean isKeyNull() {
		return (diagnosticcenterId == null);
	}

	@Transient
	@Override
	public final List<KeyField> getKeyField() {
		List<KeyField> list = new ArrayList<KeyField>();
		list.add(new KeyField(FIELD_diagnosticcenterId, getDiagnosticcenterId()));
		return list;
	}

	@Transient
	@Override
	public Long getId() {
		return getDiagnosticcenterId();
	}

	@Override
	public Object clone() throws CloneNotSupportedException {
		Diagnosticcenter diagnosticcenter = new Diagnosticcenter();
		diagnosticcenter.setFromDB(false);
		diagnosticcenter.setName(getName());
		diagnosticcenter.setDetails(getDetails());
		diagnosticcenter.setNewlyRegistered(getNewlyRegistered());
		diagnosticcenter.setPrimaryUser(getPrimaryUser());
		diagnosticcenter.setPrimaryUserEmailId(getPrimaryUserEmailId());
		diagnosticcenter.setPrimaryDataCenterAddressId(getPrimaryDataCenterAddressId());
		diagnosticcenter.setValidationId(getValidationId());
		diagnosticcenter.setVerifiedBy(getVerifiedBy());
		diagnosticcenter.setVerifiedByUserName(getVerifiedByUserName());
		diagnosticcenter.setWebsiteURL(getWebsiteURL());
		diagnosticcenter.setAveragerating(getAveragerating());
		diagnosticcenter.setActive(getActive());
		diagnosticcenter.setBranchCode(getBranchCode());
		diagnosticcenter.setBranchName(getBranchName());
		diagnosticcenter.setCellNumber(getCellNumber());
		diagnosticcenter.setProfilePic(getProfilePic());
		diagnosticcenter.setReport(getReport());
		diagnosticcenter.setDiagnosticCenteradminId(getDiagnosticCenteradminId());
		// afterClone(diagnosticcenter);
		return diagnosticcenter;
	}
}