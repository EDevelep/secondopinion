package org.secondopinion.patient.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Date;
import org.secondopinion.utils.threadlocal.AppThreadLocal;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonFormat.Shape;

import org.secondopinion.domain.BaseDomainObject;
import org.secondopinion.domain.KeyField;
import org.secondopinion.domain.validation.ValidationMessage;
import org.secondopinion.patient.dto.Medicalinsurance;
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

@SuppressWarnings({ "serial" })
@MappedSuperclass
public abstract class BaseMedicalinsurance extends BaseDomainObject<Long> {

	public static final String FIELD_medicalInsuranceId = "medicalInsuranceId";
	public static final String FIELD_userId = "userId";
	public static final String FIELD_policyNumber = "policyNumber";
	public static final String FIELD_policyType = "policyType";
	public static final String FIELD_validUpTo = "validUpTo";
	public static final String FIELD_providerName = "providerName";
	public static final String FIELD_isPrimaryHolder = "isPrimaryHolder";
	public static final String FIELD_active = "active";
	public static final String FIELD_createdBy = "createdBy";
	public static final String FIELD_createdDate = "createdDate";
	public static final String FIELD_lastUpdatedBy = "lastUpdatedBy";
	public static final String FIELD_lastUpdatedTs = "lastUpdatedTs";

	private Long medicalInsuranceId;
	private Long userId;
	private String policyNumber;
	private String policyType;
	private Date validUpTo;
	private String providerName;
	private Character isPrimaryHolder;
	private Character active;

	public void setMedicalInsuranceId(Long _medicalInsuranceId) {
		this.medicalInsuranceId = _medicalInsuranceId;
	}

	@Id
	@Column(name = "medicalInsuranceId")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Long getMedicalInsuranceId() {
		return this.medicalInsuranceId;
	}

	public void setUserId(Long _userId) {
		this.userId = _userId;
	}

	@NotNull
	@Column(name = "userId")
	public Long getUserId() {
		return this.userId;
	}

	public void setPolicyNumber(String _policyNumber) {
		this.policyNumber = _policyNumber;
	}

	@NotNull
	@Length(max = 45)
	@Column(name = "policyNumber")
	public String getPolicyNumber() {
		return this.policyNumber;
	}

	public void setPolicyType(String _policyType) {
		this.policyType = _policyType;
	}

	@NotNull
	@Length(max = 45)
	@Column(name = "policyType")
	public String getPolicyType() {
		return this.policyType;
	}

	public void setValidUpTo(Date _validUpTo) {
		this.validUpTo = _validUpTo;
	}

	@JsonFormat(pattern = "yyyy-MM-dd", shape = Shape.STRING)
	@Column(name = "validUpTo")
	public Date getValidUpTo() {
		return this.validUpTo;
	}

	public void setProviderName(String _providerName) {
		this.providerName = _providerName;
	}

	@NotNull
	@Length(max = 200)
	@Column(name = "providerName")
	public String getProviderName() {
		return this.providerName;
	}

	public void setIsPrimaryHolder(Character _isPrimaryHolder) {
		this.isPrimaryHolder = _isPrimaryHolder;
	}

	@Column(name = "isPrimaryHolder")
	public Character getIsPrimaryHolder() {
		return this.isPrimaryHolder;
	}

	public void setActive(Character _active) {
		this.active = _active;
	}

	@NotNull
	@Column(name = "active")
	public Character getActive() {
		return this.active;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		BaseMedicalinsurance other = (BaseMedicalinsurance) o;
		if (ObjectUtil.isEqual(getUserId(), other.getUserId())
				&& ObjectUtil.isEqual(getPolicyNumber(), other.getPolicyNumber())
				&& ObjectUtil.isEqual(getPolicyType(), other.getPolicyType())
				&& ObjectUtil.isEqual(getValidUpTo(), other.getValidUpTo())
				&& ObjectUtil.isEqual(getProviderName(), other.getProviderName())
				&& ObjectUtil.isEqual(getIsPrimaryHolder(), other.getIsPrimaryHolder())
				&& ObjectUtil.isEqual(getActive(), other.getActive())
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
		result = result + (medicalInsuranceId != null ? medicalInsuranceId.hashCode() : 0);

		return result;
	}

	public void validate(boolean validatePk) {
		resetValidationMessages();

		List<ValidationMessage> list = new ArrayList<ValidationMessage>();
		if (validatePk) {
			if (this.medicalInsuranceId == null) {
				list.add(new ValidationMessage("Field " + FIELD_medicalInsuranceId + " cannot be null"));
			}

		}
		if (this.userId == null) {
			list.add(new ValidationMessage("Field " + FIELD_userId + " cannot be null"));
		}

		if (StringUtil.isNullOrEmpty(this.policyNumber)) {
			list.add(new ValidationMessage("Field " + FIELD_policyNumber + " cannot be null"));
		}

		if ((this.policyNumber != null) && (this.policyNumber.length() > 45)) {
			list.add(new ValidationMessage("Field " + FIELD_policyNumber + " cannot be longer than: " + 45));
		}

		if (StringUtil.isNullOrEmpty(this.policyType)) {
			list.add(new ValidationMessage("Field " + FIELD_policyType + " cannot be null"));
		}

		if ((this.policyType != null) && (this.policyType.length() > 45)) {
			list.add(new ValidationMessage("Field " + FIELD_policyType + " cannot be longer than: " + 45));
		}

		if (StringUtil.isNullOrEmpty(this.providerName)) {
			list.add(new ValidationMessage("Field " + FIELD_providerName + " cannot be null"));
		}

		if ((this.providerName != null) && (this.providerName.length() > 200)) {
			list.add(new ValidationMessage("Field " + FIELD_providerName + " cannot be longer than: " + 200));
		}

		if (this.active == null) {
			list.add(new ValidationMessage("Field " + FIELD_active + " cannot be null"));
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
		str.append("medicalInsuranceId = " + medicalInsuranceId + "\n");
		;
		str.append("userId = " + userId + "\n");
		str.append("policyNumber = " + policyNumber + "\n");
		str.append("policyType = " + policyType + "\n");
		str.append("validUpTo = " + validUpTo + "\n");
		str.append("providerName = " + providerName + "\n");
		str.append("isPrimaryHolder = " + isPrimaryHolder + "\n");
		str.append("active = " + active + "\n");
		str.append("lastUpdatedBy = " + lastUpdatedBy + "\n");
		str.append("lastUpdatedTs = " + lastUpdatedTs + "\n");
		return str.toString();
	}

	@Transient
	@Override
	public final boolean isKeyNull() {
		return (medicalInsuranceId == null);
	}

	@Transient
	@Override
	public final List<KeyField> getKeyField() {
		List<KeyField> list = new ArrayList<KeyField>();
		list.add(new KeyField(FIELD_medicalInsuranceId, getMedicalInsuranceId()));
		return list;
	}

	@Transient
	@Override
	public Long getId() {
		return getMedicalInsuranceId();
	}

	@Override
	public Object clone() throws CloneNotSupportedException {
		Medicalinsurance medicalinsurance = new Medicalinsurance();
		medicalinsurance.setFromDB(false);
		medicalinsurance.setUserId(getUserId());
		medicalinsurance.setPolicyNumber(getPolicyNumber());
		medicalinsurance.setPolicyType(getPolicyType());
		medicalinsurance.setValidUpTo(getValidUpTo());
		medicalinsurance.setProviderName(getProviderName());
		medicalinsurance.setIsPrimaryHolder(getIsPrimaryHolder());
		medicalinsurance.setActive(getActive());
		// afterClone(medicalinsurance);
		return medicalinsurance;
	}
}