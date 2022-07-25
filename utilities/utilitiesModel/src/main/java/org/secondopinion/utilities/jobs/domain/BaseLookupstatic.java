package org.secondopinion.utilities.jobs.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Date;

import org.secondopinion.utils.threadlocal.AppThreadLocal;
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

import org.secondopinion.domain.BaseDomainObject;
import org.secondopinion.domain.KeyField;
import org.secondopinion.domain.validation.ValidationMessage;
import org.secondopinion.utilities.jobs.dto.Lookupstatic;

@SuppressWarnings({ "serial" })
@MappedSuperclass
public abstract class BaseLookupstatic extends BaseDomainObject<Long> {

	public static final String FIELD_lookupId = "lookupId";
	public static final String FIELD_lookupType = "lookupType";
	public static final String FIELD_lookupValue = "lookupValue";
	public static final String FIELD_datasetType = "datasetType";
	public static final String FIELD_lastUpdatedBy = "lastUpdatedBy";
	public static final String FIELD_lastUpdatedTs = "lastUpdatedTs";

	private Long lookupId;
	private String lookupType;
	private String lookupValue;
  private String datasetType;
	
	public void setLookupId(Long _lookupId) {
		this.lookupId = _lookupId;
	}

	@Id
	@Column(name = "lookupId")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Long getLookupId() {
		return this.lookupId;
	}

	public void setLookupType(String _lookupType) {
		this.lookupType = _lookupType;
	}

	@NotNull
	@Length(max = 45)
	@Column(name = "lookupType")
	public String getLookupType() {
		return this.lookupType;
	}

	public void setLookupValue(String _lookupValue) {
		this.lookupValue = _lookupValue;
	}

	@NotNull
	@Length(max = 150)
	@Column(name = "lookupValue")
	public String getLookupValue() {
		return this.lookupValue;
	}
	@Column(name = "datasetType")
	public String getDatasetType() {
		return datasetType;
	}

	public void setDatasetType(String datasetType) {
		this.datasetType = datasetType;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		BaseLookupstatic other = (BaseLookupstatic) o;
		if (ObjectUtil.isEqual(getLookupType(), other.getLookupType())
				&& ObjectUtil.isEqual(getLookupValue(), other.getLookupValue())
				&& ObjectUtil.isEqual(getDatasetType(), other.getDatasetType())
				&& ObjectUtil.isEqual(getLastUpdatedBy(), other.getLastUpdatedBy())
				&& ObjectUtil.isEqual(getLastUpdatedTs(), other.getLastUpdatedTs())) {
			return true;
		}
		return false;
	}

	@Override
	public int hashCode() {
		int result = 0;
		result = result + (lookupId != null ? lookupId.hashCode() : 0);

		return result;
	}

	public void validate(boolean validatePk) {
		resetValidationMessages();

		List<ValidationMessage> list = new ArrayList<ValidationMessage>();
		if (validatePk) {
			if (this.lookupId == null) {
				list.add(new ValidationMessage("Field " + FIELD_lookupId + " cannot be null"));
			}

		}
		if (StringUtil.isNullOrEmpty(this.lookupType)) {
			list.add(new ValidationMessage("Field " + FIELD_lookupType + " cannot be null"));
		}

		if ((this.lookupType != null) && (this.lookupType.length() > 45)) {
			list.add(new ValidationMessage("Field " + FIELD_lookupType + " cannot be longer than: " + 45));
		}

		if (StringUtil.isNullOrEmpty(this.lookupValue)) {
			list.add(new ValidationMessage("Field " + FIELD_lookupValue + " cannot be null"));
		}

		if ((this.lookupValue != null) && (this.lookupValue.length() > 150)) {
			list.add(new ValidationMessage("Field " + FIELD_lookupValue + " cannot be longer than: " + 150));
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
	}

	@Override
	public String toString() {
		StringBuilder str = new StringBuilder();
		str.append("lookupId = " + lookupId + "\n");
		str.append("lookupType = " + lookupType + "\n");
		str.append("lookupValue = " + lookupValue + "\n");
		str.append("datasetType = " + datasetType + "\n");
		str.append("lastUpdatedBy = " + lastUpdatedBy + "\n");
		str.append("lastUpdatedTs = " + lastUpdatedTs + "\n");
		return str.toString();
	}

	@Transient
	@Override
	public final boolean isKeyNull() {
		return (lookupId == null);
	}

	@Transient
	@Override
	public final List<KeyField> getKeyField() {
		List<KeyField> list = new ArrayList<KeyField>();
		list.add(new KeyField(FIELD_lookupId, getLookupId()));
		return list;
	}

	@Transient
	@Override
	public Long getId() {
		return getLookupId();
	}

	@Override
	public Object clone() throws CloneNotSupportedException {
		Lookupstatic lookupstatic = new Lookupstatic();
		lookupstatic.setFromDB(false);
		lookupstatic.setLookupType(getLookupType());
		lookupstatic.setLookupValue(getLookupValue());
		lookupstatic.setDatasetType(getDatasetType());
		lookupstatic.setLastUpdatedTs(getLastUpdatedTs());
		// afterClone(lookupstatic);
		return lookupstatic;
	}
}