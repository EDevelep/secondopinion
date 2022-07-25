package org.secondopinion.doctor.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.Date;

import org.secondopinion.utils.ObjectUtil;
import org.secondopinion.utils.threadlocal.AppThreadLocal;
import org.secondopinion.doctor.dto.Appointmentnotes;
import org.secondopinion.domain.BaseDomainObject;
import org.secondopinion.domain.KeyField;
import org.secondopinion.domain.validation.ValidationMessage;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.validation.constraints.NotNull;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Transient;
import org.hibernate.validator.constraints.Length;

@MappedSuperclass
public abstract class BaseAppointmentnotes extends BaseDomainObject<Long> {

	public static final String FIELD_appointmentNotesId = "appointmentNotesId";
	public static final String FIELD_appointmentId = "appointmentId";
	public static final String FIELD_notes = "notes";
	public static final String FIELD_active = "active";
	public static final String FIELD_createdBy = "createdBy";
	public static final String FIELD_createdDate = "createdDate";
	public static final String FIELD_lastUpdatedBy = "lastUpdatedBy";
	public static final String FIELD_lastUpdatedTs = "lastUpdatedTs";

	private Long appointmentNotesId;
	private String appointmentId;
	private String notes;
	private Character active;

	public void setAppointmentNotesId(Long _appointmentNotesId) {
		this.appointmentNotesId = _appointmentNotesId;
	}

	@Id
	@Column(name = "appointmentNotesId")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Long getAppointmentNotesId() {
		return this.appointmentNotesId;
	}

	public void setAppointmentId(String _appointmentId) {
		this.appointmentId = _appointmentId;
	}

	@Length(max = 45)
	@Column(name = "appointmentId")
	public String getAppointmentId() {
		return this.appointmentId;
	}

	public void setNotes(String _notes) {
		this.notes = _notes;
	}

	@Length(max = 21845)
	@Column(name = "notes")
	public String getNotes() {
		return this.notes;
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
		BaseAppointmentnotes other = (BaseAppointmentnotes) o;
		if (ObjectUtil.isEqual(getAppointmentId(), other.getAppointmentId())
				&& ObjectUtil.isEqual(getNotes(), other.getNotes())
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
		result = result + (appointmentNotesId != null ? appointmentNotesId.hashCode() : 0);

		return result;
	}

	public void validate(boolean validatePk) {
		resetValidationMessages();

		List<ValidationMessage> list = new ArrayList<ValidationMessage>();
		if (validatePk) {
			if (this.appointmentNotesId == null) {
				list.add(new ValidationMessage("Field " + FIELD_appointmentNotesId + " cannot be null"));
			}

		}
		if ((this.appointmentId != null) && (this.appointmentId.length() > 45)) {
			list.add(new ValidationMessage("Field " + FIELD_appointmentId + " cannot be longer than: " + 45));
		}

		if ((this.notes != null) && (this.notes.length() > 21845)) {
			list.add(new ValidationMessage("Field " + FIELD_notes + " cannot be longer than: " + 21845));
		}

		if (this.active == null) {
			list.add(new ValidationMessage("Field " + FIELD_active + " cannot be null"));
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
		str.append("appointmentNotesId = " + appointmentNotesId + "\n");
		;
		str.append("appointmentId = " + appointmentId + "\n");
		str.append("notes = " + notes + "\n");
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
		return (appointmentNotesId == null);
	}

	@Transient
	@Override
	public final List<KeyField> getKeyField() {
		List<KeyField> list = new ArrayList<KeyField>();
		list.add(new KeyField(FIELD_appointmentNotesId, getAppointmentNotesId()));
		return list;
	}

	@Transient
	@Override
	public Long getId() {
		return getAppointmentNotesId();
	}

	@Override
	public Object clone() throws CloneNotSupportedException {
		Appointmentnotes appointmentnotes = new Appointmentnotes();
		appointmentnotes.setFromDB(false);
		appointmentnotes.setAppointmentId(getAppointmentId());
		appointmentnotes.setNotes(getNotes());
		appointmentnotes.setActive(getActive());
		appointmentnotes.setCreatedDate(getCreatedDate());
		// afterClone(appointmentnotes);
		return appointmentnotes;
	}
}