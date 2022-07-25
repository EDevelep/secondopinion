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
import org.secondopinion.patient.dto.Ailments;
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
public abstract class BaseAilments extends BaseDomainObject<Long> {

	public static final String FIELD_ailmentsId = "ailmentsId";
	public static final String FIELD_userId = "userId";
	public static final String FIELD_ailmentId = "ailmentId";
	public static final String FIELD_ailment = "ailment";
	public static final String FIELD_ailmentDetails = "ailmentDetails";
	public static final String FIELD_recordedDate = "recordedDate";
	public static final String FIELD_active = "active";
	public static final String FIELD_createdBy = "createdBy";
	public static final String FIELD_createdDate = "createdDate";
	public static final String FIELD_lastUpdatedTs = "lastUpdatedTs";
	public static final String FIELD_lastUpdatedBy = "lastUpdatedBy";

	private Long ailmentsId;
	private Long userId;
	private Long ailmentId;
	private String ailment;
	private String ailmentDetails;
	private Date recordedDate;
	private Character active;

	public void setAilmentsId(Long _ailmentsId) {
		this.ailmentsId = _ailmentsId;
	}

	@Id
	@Column(name = "ailmentsId")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Long getAilmentsId() {
		return this.ailmentsId;
	}

	public void setUserId(Long _userId) {
		this.userId = _userId;
	}

	@NotNull
	@Column(name = "userId")
	public Long getUserId() {
		return this.userId;
	}

	public void setAilmentId(Long _ailmentId) {
		this.ailmentId = _ailmentId;
	}

	@NotNull
	@Column(name = "ailmentId")
	public Long getAilmentId() {
		return this.ailmentId;
	}

	public void setAilment(String _ailment) {
		this.ailment = _ailment;
	}

	@NotNull
	@Length(max = 250)
	@Column(name = "ailment")
	public String getAilment() {
		return this.ailment;
	}

	public void setAilmentDetails(String _ailmentDetails) {
		this.ailmentDetails = _ailmentDetails;
	}

	@Length(max = 21845)
	@Column(name = "ailmentDetails")
	public String getAilmentDetails() {
		return this.ailmentDetails;
	}

	public void setRecordedDate(Date _recordedDate) {
		this.recordedDate = _recordedDate;
	}

	@JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSXXX", shape = Shape.STRING)
	@Column(name = "recordedDate")
	public Date getRecordedDate() {
		return this.recordedDate;
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
		BaseAilments other = (BaseAilments) o;
		if (ObjectUtil.isEqual(getUserId(), other.getUserId())
				&& ObjectUtil.isEqual(getAilmentId(), other.getAilmentId())
				&& ObjectUtil.isEqual(getAilment(), other.getAilment())
				&& ObjectUtil.isEqual(getAilmentDetails(), other.getAilmentDetails())
				&& ObjectUtil.isEqual(getRecordedDate(), other.getRecordedDate())
				&& ObjectUtil.isEqual(getActive(), other.getActive())
				&& ObjectUtil.isEqual(getLastUpdatedTs(), other.getLastUpdatedTs())
				&& ObjectUtil.isEqual(getLastUpdatedBy(), other.getLastUpdatedBy())) {
			return true;
		}
		return false;
	}

	@Override
	public int hashCode() {
		int result = 0;
		result = result + (ailmentsId != null ? ailmentsId.hashCode() : 0);

		return result;
	}

	public void validate(boolean validatePk) {
		resetValidationMessages();

		List<ValidationMessage> list = new ArrayList<ValidationMessage>();
		if (validatePk) {
			if (this.ailmentsId == null) {
				list.add(new ValidationMessage("Field " + FIELD_ailmentsId + " cannot be null"));
			}

		}
		if (this.userId == null) {
			list.add(new ValidationMessage("Field " + FIELD_userId + " cannot be null"));
		}

		if (this.ailmentId == null) {
			list.add(new ValidationMessage("Field " + FIELD_ailmentId + " cannot be null"));
		}

		if (StringUtil.isNullOrEmpty(this.ailment)) {
			list.add(new ValidationMessage("Field " + FIELD_ailment + " cannot be null"));
		}

		if ((this.ailment != null) && (this.ailment.length() > 250)) {
			list.add(new ValidationMessage("Field " + FIELD_ailment + " cannot be longer than: " + 250));
		}

		if ((this.ailmentDetails != null) && (this.ailmentDetails.length() > 21845)) {
			list.add(new ValidationMessage("Field " + FIELD_ailmentDetails + " cannot be longer than: " + 21845));
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
			this.createdBy = AppThreadLocal.getUserName();
		}
		this.lastUpdatedTs = AppThreadLocal.getRequestStartDate();
		this.lastUpdatedBy = AppThreadLocal.getUserName();
	}

	@Override
	public String toString() {
		StringBuilder str = new StringBuilder();
		str.append("ailmentsId = " + ailmentsId + "\n");
		;
		str.append("userId = " + userId + "\n");
		str.append("ailmentId = " + ailmentId + "\n");
		str.append("ailment = " + ailment + "\n");
		str.append("ailmentDetails = " + ailmentDetails + "\n");
		str.append("recordedDate = " + recordedDate + "\n");
		str.append("active = " + active + "\n");
		str.append("lastUpdatedTs = " + lastUpdatedTs + "\n");
		str.append("lastUpdatedBy = " + lastUpdatedBy + "\n");
		return str.toString();
	}

	@Transient
	@Override
	public final boolean isKeyNull() {
		return (ailmentsId == null);
	}

	@Transient
	@Override
	public final List<KeyField> getKeyField() {
		List<KeyField> list = new ArrayList<KeyField>();
		list.add(new KeyField(FIELD_ailmentsId, getAilmentsId()));
		return list;
	}

	@Transient
	@Override
	public Long getId() {
		return getAilmentsId();
	}

	@Override
	public Object clone() throws CloneNotSupportedException {
		Ailments ailments = new Ailments();
		ailments.setFromDB(false);
		ailments.setUserId(getUserId());
		ailments.setAilmentId(getAilmentId());
		ailments.setAilment(getAilment());
		ailments.setAilmentDetails(getAilmentDetails());
		ailments.setRecordedDate(getRecordedDate());
		ailments.setActive(getActive());
		ailments.setCreatedDate(getCreatedDate());
		// afterClone(ailments);
		return ailments;
	}
}