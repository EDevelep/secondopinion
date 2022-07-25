package org.secondopinion.doctor.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.Date;

import org.secondopinion.doctor.dto.Schedulehours;
import org.secondopinion.domain.BaseDomainObject;
import org.secondopinion.domain.KeyField;
import org.secondopinion.domain.validation.ValidationMessage;
import org.secondopinion.utils.ObjectUtil;
import org.secondopinion.utils.threadlocal.AppThreadLocal;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonFormat.Shape;

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

@MappedSuperclass
public abstract class BaseSchedulehours extends BaseDomainObject<Long> {

	public static final String FIELD_scheduleHoursId = "scheduleHoursId";
	public static final String FIELD_scheduleId = "scheduleId";
	public static final String FIELD_scheduleStatus = "scheduleStatus";
	public static final String FIELD_fromTime = "fromTime";
	public static final String FIELD_toTime = "toTime";
	public static final String FIELD_timeOfDay = "timeOfDay";
	public static final String FIELD_associateEntityType = "associateEntityType";
	public static final String FIELD_associateEntityId = "associateEntityId";
	public static final String FIELD_associateEntityName = "associateEntityName";
	public static final String FIELD_active = "active";

	private Long scheduleHoursId;
	private Long scheduleId;
	@JsonFormat(pattern = "HH:mm", shape = Shape.STRING)
	private Date toTime;
	@JsonFormat(pattern = "HH:mm", shape = Shape.STRING)
	private Date fromTime;
	private Integer timeOfDay;
	private String scheduleStatus;
	private Character active;
	private Long associateEntityId;
	private String associateEntityType;
	private String associateEntityName;

	public void setScheduleHoursId(Long _scheduleHoursId) {
		this.scheduleHoursId = _scheduleHoursId;
	}

	@Id
	@Column(name = "scheduleHoursId")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Long getScheduleHoursId() {
		return this.scheduleHoursId;
	}

	public void setScheduleId(Long _scheduleId) {
		this.scheduleId = _scheduleId;
	}

	@NotNull
	@Column(name = "scheduleId")
	public Long getScheduleId() {
		return this.scheduleId;
	}

	public void setFromTime(Date _fromTime) {
		this.fromTime = _fromTime;
	}

	@JsonFormat(pattern = "HH:mm", shape = Shape.STRING)
	@NotNull
	@Column(name = "fromTime")
	@Temporal(TemporalType.TIME)
	public Date getFromTime() {
		return this.fromTime;
	}

	public void setToTime(Date _toTime) {
		this.toTime = _toTime;
	}

	@JsonFormat(pattern = "HH:mm", shape = Shape.STRING)
	@NotNull
	@Temporal(TemporalType.TIME)
	@Column(name = "toTime")
	public Date getToTime() {
		return this.toTime;
	}

	public void setTimeOfDay(Integer _timeOfDay) {
		this.timeOfDay = _timeOfDay;
	}

	@Column(name = "timeOfDay")
	public Integer getTimeOfDay() {
		return this.timeOfDay;
	}

	@Column(name = "scheduleStatus")
	public String getScheduleStatus() {
		return scheduleStatus;
	}

	public void setScheduleStatus(String scheduleStatus) {
		this.scheduleStatus = scheduleStatus;
	}

	public void setActive(Character _active) {
		this.active = _active;
	}

	@Column(name = "active")
	public Character getActive() {
		return this.active;
	}

	@Column(name = "associateEntityId")
	public Long getAssociateEntityId() {
		return associateEntityId;
	}

	public void setAssociateEntityId(Long associateEntityId) {
		this.associateEntityId = associateEntityId;
	}

	@Column(name = "associateEntityType")
	public String getAssociateEntityType() {
		return associateEntityType;
	}

	public void setAssociateEntityType(String associateEntityType) {
		this.associateEntityType = associateEntityType;
	}
	@Column(name = "associateEntityName")
	public String getAssociateEntityName() {
		return associateEntityName;
	}

	public void setAssociateEntityName(String associateEntityName) {
		this.associateEntityName = associateEntityName;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		BaseSchedulehours other = (BaseSchedulehours) o;
		if (ObjectUtil.isEqual(getScheduleId(), other.getScheduleId())
				&& ObjectUtil.isEqual(getFromTime(), other.getFromTime())
				&& ObjectUtil.isEqual(getToTime(), other.getToTime())
				&& ObjectUtil.isEqual(getTimeOfDay(), other.getTimeOfDay())
				&& ObjectUtil.isEqual(getScheduleStatus(), other.getScheduleStatus())
				&& ObjectUtil.isEqual(getActive(), other.getActive())
				&& ObjectUtil.isEqual(getAssociateEntityId(), other.getAssociateEntityId())
				&& ObjectUtil.isEqual(getAssociateEntityType(), other.getAssociateEntityType())
				&& ObjectUtil.isEqual(getAssociateEntityName(), other.getAssociateEntityName())
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
		result = result + (scheduleHoursId != null ? scheduleHoursId.hashCode() : 0);

		return result;
	}

	public void validate(boolean validatePk) {
		resetValidationMessages();

		List<ValidationMessage> list = new ArrayList<ValidationMessage>();
		if (validatePk) {
			if (this.scheduleHoursId == null) {
				list.add(new ValidationMessage("Field " + FIELD_scheduleHoursId + " cannot be null"));
			}

		}
		if (this.scheduleId == null) {
			list.add(new ValidationMessage("Field " + FIELD_scheduleId + " cannot be null"));
		}

		if (this.fromTime == null) {
			list.add(new ValidationMessage("Field " + FIELD_fromTime + " cannot be null"));
		}

		if (this.toTime == null) {
			list.add(new ValidationMessage("Field " + FIELD_toTime + " cannot be null"));
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
	}

	@Override
	public String toString() {
		StringBuilder str = new StringBuilder();
		str.append("scheduleHoursId = " + scheduleHoursId + "\n");
		str.append("scheduleId = " + scheduleId + "\n");
		str.append("from = " + fromTime + "\n");
		str.append("to = " + toTime + "\n");
		str.append("timeOfDay = " + timeOfDay + "\n");
		str.append("scheduleStatus = " + scheduleStatus + "\n");
		str.append("active = " + active + "\n");
		str.append("createdBy = " + createdBy + "\n");
		str.append("associateEntityType = " + associateEntityType + "\n");
		str.append("associateEntityId = " + associateEntityId + "\n");
		str.append("associateEntityName = " + associateEntityName + "\n");
		str.append("createdDate = " + createdDate + "\n");
		str.append("lastUpdatedBy = " + lastUpdatedBy + "\n");
		str.append("lastUpdatedTs = " + lastUpdatedTs + "\n");
		return str.toString();
	}

	@Transient
	@Override
	public final boolean isKeyNull() {
		return (scheduleHoursId == null);
	}

	@Transient
	@Override
	public final List<KeyField> getKeyField() {
		List<KeyField> list = new ArrayList<KeyField>();
		list.add(new KeyField(FIELD_scheduleHoursId, getScheduleHoursId()));
		return list;
	}

	@Transient
	@Override
	public Long getId() {
		return getScheduleHoursId();
	}

	@Override
	public Object clone() throws CloneNotSupportedException {
		Schedulehours schedulehours = new Schedulehours();
		schedulehours.setFromDB(false);
		schedulehours.setScheduleId(getScheduleId());
		schedulehours.setFromTime(getFromTime());
		schedulehours.setToTime(getToTime());
		schedulehours.setTimeOfDay(getTimeOfDay());
		schedulehours.setScheduleStatus(getScheduleStatus());
		schedulehours.setActive(getActive());
		schedulehours.setAssociateEntityId(getAssociateEntityId());
		schedulehours.setAssociateEntityType(getAssociateEntityType());
		schedulehours.setAssociateEntityName(getAssociateEntityName());
		schedulehours.setCreatedDate(getCreatedDate());
		schedulehours.setLastUpdatedTs(getLastUpdatedTs());
		// afterClone(schedulehours);
		return schedulehours;
	}
}