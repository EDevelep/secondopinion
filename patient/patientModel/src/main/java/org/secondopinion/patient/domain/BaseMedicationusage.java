package org.secondopinion.patient.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Date;
import org.secondopinion.utils.threadlocal.AppThreadLocal;
import org.secondopinion.domain.BaseDomainObject;
import org.secondopinion.domain.KeyField;
import org.secondopinion.domain.validation.ValidationMessage;
import org.secondopinion.patient.dto.Medicationusage;
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
public abstract class BaseMedicationusage extends BaseDomainObject<Long> {

	public static final String FIELD_medicationusageId = "medicationusageId";
	public static final String FIELD_patientId = "patientId";
	public static final String FIELD_medicationId = "medicationId";
	public static final String FIELD_dosageConsumed = "dosageConsumed";
	public static final String FIELD_medacationDate = "medacationDate";
	public static final String FIELD_doseTime = "doseTime";
	public static final String FIELD_active = "active";
	public static final String FIELD_createdBy = "createdBy";
	public static final String FIELD_createdDate = "createdDate";
	public static final String FIELD_lastUpdatedBy = "lastUpdatedBy";
	public static final String FIELD_lastUpdatedTs = "lastUpdatedTs";

	private Long medicationusageId;
	private Long patientId;
	private Long medicationId;
	private Character dosageConsumed;
	private Date medacationDate;
	private String doseTime;
	private Character active;

	public void setMedicationusageId(Long _medicationusageId) {
		this.medicationusageId = _medicationusageId;
	}

	@Id
	@Column(name = "medicationusageId")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Long getMedicationusageId() {
		return this.medicationusageId;
	}

	public void setPatientId(Long _patientId) {
		this.patientId = _patientId;
	}

	@NotNull
	@Column(name = "patientId")
	public Long getPatientId() {
		return this.patientId;
	}

	public void setMedicationId(Long _medicationId) {
		this.medicationId = _medicationId;
	}

	@NotNull
	@Column(name = "medicationId")
	public Long getMedicationId() {
		return this.medicationId;
	}

	public void setActive(Character _active) {
		this.active = _active;
	}

	@Column(name = "active")
	public Character getActive() {
		return this.active;
	}

	@Column(name = "dosageConsumed")
	public Character getDosageConsumed() {
		return dosageConsumed;
	}

	public void setDosageConsumed(Character dosageConsumed) {
		this.dosageConsumed = dosageConsumed;
	}

	@Column(name = "medacationDate")
	public Date getMedacationDate() {
		return medacationDate;
	}

	public void setMedacationDate(Date medacationDate) {
		this.medacationDate = medacationDate;
	}

	@Column(name = "doseTime")
	public String getDoseTime() {
		return doseTime;
	}

	public void setDoseTime(String doseTime) {
		this.doseTime = doseTime;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		BaseMedicationusage other = (BaseMedicationusage) o;
		if (ObjectUtil.isEqual(getPatientId(), other.getPatientId())
				&& ObjectUtil.isEqual(getMedicationId(), other.getMedicationId())
				&& ObjectUtil.isEqual(getDosageConsumed(), other.getDosageConsumed())
				&& ObjectUtil.isEqual(getDoseTime(), other.getDoseTime())
				&& ObjectUtil.isEqual(getMedacationDate(), other.getMedacationDate())
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
		result = result + (medicationusageId != null ? medicationusageId.hashCode() : 0);

		return result;
	}

	public void validate(boolean validatePk) {
		resetValidationMessages();

		List<ValidationMessage> list = new ArrayList<ValidationMessage>();
		if (validatePk) {
			if (this.medicationusageId == null) {
				list.add(new ValidationMessage("Field " + FIELD_medicationusageId + " cannot be null"));
			}

		}
		if (this.patientId == null) {
			list.add(new ValidationMessage("Field " + FIELD_patientId + " cannot be null"));
		}

		if (this.medicationId == null) {
			list.add(new ValidationMessage("Field " + FIELD_medicationId + " cannot be null"));
		}

		if (this.isFromDB()) {
			if ((this.createdBy != null) && (this.createdBy.length() > 45)) {
				list.add(new ValidationMessage("Field " + FIELD_createdBy + " cannot be longer than: " + 45));
			}
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
			this.createdBy = AppThreadLocal.getUserName();
		}
		this.lastUpdatedBy = AppThreadLocal.getUserName();
		this.lastUpdatedTs = AppThreadLocal.getRequestStartDate();
	}

	@Override
	public String toString() {
		StringBuilder str = new StringBuilder();
		str.append("medicationusageId = " + medicationusageId + "\n");
		;
		str.append("patientId = " + patientId + "\n");
		str.append("medicationId = " + medicationId + "\n");
		str.append("dosageConsumed = " + dosageConsumed + "\n");
		str.append("doseTime = " + doseTime + "\n");
		str.append("medacationDate = " + medacationDate + "\n");
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
		return (medicationusageId == null);
	}

	@Transient
	@Override
	public final List<KeyField> getKeyField() {
		List<KeyField> list = new ArrayList<KeyField>();
		list.add(new KeyField(FIELD_medicationusageId, getMedicationusageId()));
		return list;
	}

	@Transient
	@Override
	public Long getId() {
		return getMedicationusageId();
	}

	@Override
	public Medicationusage clone() throws CloneNotSupportedException {
		Medicationusage medicationusage = new Medicationusage();
		medicationusage.setFromDB(false);
		medicationusage.setPatientId(getPatientId());
		medicationusage.setMedicationId(getMedicationId());
		medicationusage.setDosageConsumed(getDosageConsumed());
		medicationusage.setDoseTime(getDoseTime());
		medicationusage.setMedacationDate(getMedacationDate());
		medicationusage.setActive(getActive());
		medicationusage.setCreatedDate(getCreatedDate());
		// afterClone(medicationusage);
		return medicationusage;
	}
}