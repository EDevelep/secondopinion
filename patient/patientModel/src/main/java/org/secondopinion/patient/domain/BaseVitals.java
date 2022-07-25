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
import org.secondopinion.patient.dto.Vitals;
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

@SuppressWarnings({ "serial" })
@MappedSuperclass
public abstract class BaseVitals extends BaseDomainObject<Long> {

	
	public static final String FIELD_vitalsId = "vitalsId";
	public static final String FIELD_userId = "userId";
	public static final String FIELD_vitalname = "vitalname";
	public static final String FIELD_vitalValue = "vitalValue";
	public static final String FIELD_recordedDate = "recordedDate";
	public static final String FIELD_active = "active";
	public static final String FIELD_metricType = "metricType";
	public static final String FIELD_createdBy = "createdBy";
	public static final String FIELD_createdDate = "createdDate";
	public static final String FIELD_lastUpdatedBy = "lastUpdatedBy";
	public static final String FIELD_lastUpdatedTs = "lastUpdatedTs";

	private Long vitalsId;
	private Long userId;
	private String vitalname;
	private String vitalValue;
	@JsonFormat(pattern = "yyyy-MM-dd", shape = Shape.STRING)
	private Date recordedDate;
	private Character active;
	private String metricType;
	

	public void setVitalsId(Long _vitalsId) {
		this.vitalsId = _vitalsId;
	}

	@Id
	@Column(name = "vitalsId")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Long getVitalsId() {
		return this.vitalsId;
	}

	public void setUserId(Long _userId) {
		this.userId = _userId;
	}

	@NotNull
	@Column(name = "userId")
	public Long getUserId() {
		return this.userId;
	}

	public void setVitalname(String _vitalname) {
		this.vitalname = _vitalname;
	}

	@NotNull
	@Length(max = 45)
	@Column(name = "vitalname")
	public String getVitalname() {
		return this.vitalname;
	}

	public void setVitalValue(String _vitalValue) {
		this.vitalValue = _vitalValue;
	}

	@NotNull
	@Length(max = 21845)
	@Column(name = "vitalValue")
	public String getVitalValue() {
		return this.vitalValue;
	}

	public void setRecordedDate(Date _recordedDate) {
		this.recordedDate = _recordedDate;
	}
	@JsonFormat(pattern="yyyy-MM-dd", shape=Shape.STRING)
	@Temporal(TemporalType.DATE)
	@NotNull
	@Column(name = "recordedDate")
	public Date getRecordedDate() {
		return this.recordedDate;
	}
@Column(name = "metricType")
	public String getMetricType() {
		return metricType;
	}

	public void setMetricType(String metricType) {
		this.metricType = metricType;
	}

	public void setActive(Character _active) {
		this.active = _active;
	}

	@NotNull
	@Column(name = "active")
	public Character getActive() {
		return this.active;
	}

	public void setCreatedBy(String _createdBy) {
		this.createdBy = _createdBy;
	}

	@Length(max = 255)
	@Column(name = "createdBy")
	public String getCreatedBy() {
		return this.createdBy;
	}

	public void setCreatedDate(Date _createdDate) {
		this.createdDate = _createdDate;
	}

	@Column(name = "createdDate")
	public Date getCreatedDate() {
		return this.createdDate;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		BaseVitals other = (BaseVitals) o;
		if (ObjectUtil.isEqual(getUserId(), other.getUserId())
				&& ObjectUtil.isEqual(getVitalname(), other.getVitalname())
				&& ObjectUtil.isEqual(getVitalValue(), other.getVitalValue())
				&& ObjectUtil.isEqual(getRecordedDate(), other.getRecordedDate())
				&& ObjectUtil.isEqual(getMetricType(), getMetricType())
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
		result = result + (vitalsId != null ? vitalsId.hashCode() : 0);

		return result;
	}

	public void validate(boolean validatePk) {
		resetValidationMessages();

		List<ValidationMessage> list = new ArrayList<ValidationMessage>();
		if (validatePk) {
			if (this.vitalsId == null) {
				list.add(new ValidationMessage("Field " + FIELD_vitalsId + " cannot be null"));
			}

		}
		if (this.userId == null) {
			list.add(new ValidationMessage("Field " + FIELD_userId + " cannot be null"));
		}

		if (StringUtil.isNullOrEmpty(this.vitalname)) {
			list.add(new ValidationMessage("Field " + FIELD_vitalname + " cannot be null"));
		}

		if ((this.vitalname != null) && (this.vitalname.length() > 45)) {
			list.add(new ValidationMessage("Field " + FIELD_vitalname + " cannot be longer than: " + 45));
		}

		if (StringUtil.isNullOrEmpty(this.vitalValue)) {
			list.add(new ValidationMessage("Field " + FIELD_vitalValue + " cannot be null"));
		}

		if ((this.vitalValue != null) && (this.vitalValue.length() > 21845)) {
			list.add(new ValidationMessage("Field " + FIELD_vitalValue + " cannot be longer than: " + 21845));
		}

		if (this.recordedDate == null) {
			list.add(new ValidationMessage("Field " + FIELD_recordedDate + " cannot be null"));
		}

		if (this.active == null) {
			list.add(new ValidationMessage("Field " + FIELD_active + " cannot be null"));
		}

		if (this.isFromDB()) {
			if ((this.createdBy != null) && (this.createdBy.length() > 255)) {
				list.add(new ValidationMessage("Field " + FIELD_createdBy + " cannot be longer than: " + 255));
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
		str.append("vitalsId = " + vitalsId + "\n");
		;
		str.append("userId = " + userId + "\n");
		str.append("vitalname = " + vitalname + "\n");
		str.append("vitalValue = " + vitalValue + "\n");
		str.append("recordedDate = " + recordedDate + "\n");
		str.append("metricType = " + metricType + "\n");
		str.append("active = " + active + "\n");
		str.append("createdBy = " + createdBy + "\n");
		str.append("createdDate = " + createdDate + "\n");
		str.append("lastUpdatedBy = " + lastUpdatedBy + "\n");
		str.append("lastUpdatedTs = " + lastUpdatedTs + "\n");
		return str.toString();
	}

	@Transient
	@Override
	public final boolean isKeyNull() {
		return (vitalsId == null);
	}

	@Transient
	@Override
	public final List<KeyField> getKeyField() {
		List<KeyField> list = new ArrayList<KeyField>();
		list.add(new KeyField(FIELD_vitalsId, getVitalsId()));
		return list;
	}

	@Transient
	@Override
	public Long getId() {
		return getVitalsId();
	}

	@Override
	public Object clone() throws CloneNotSupportedException {
		Vitals vitals = new Vitals();
		vitals.setFromDB(false);
		vitals.setUserId(getUserId());
		vitals.setMetricType(getMetricType());
		vitals.setVitalname(getVitalname());
		vitals.setVitalValue(getVitalValue());
		vitals.setRecordedDate(getRecordedDate());
		vitals.setActive(getActive());
//		vitals.setCreatedDate(getCreatedDate());
		// afterClone(vitals);
		return vitals;
	}
}