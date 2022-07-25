package org.secondopinion.patient.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Date;

import org.secondopinion.utils.threadlocal.AppThreadLocal;
import org.secondopinion.domain.BaseDomainObject;
import org.secondopinion.domain.KeyField;
import org.secondopinion.domain.validation.ValidationMessage;
import org.secondopinion.patient.dto.Medicaltestprescription;
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
public abstract class BaseMedicaltestprescription extends BaseDomainObject<Long> {

	public static final String FIELD_medicalTestPrescriptionId = "medicalTestPrescriptionId";
	public static final String FIELD_prescriptionId = "prescriptionId";
	public static final String FIELD_patientId = "patientId";
	public static final String FIELD_active = "active";
	public static final String FIELD_createdBy = "createdBy";
	public static final String FIELD_createdDate = "createdDate";
	public static final String FIELD_lastUpdatedBy = "lastUpdatedBy";
	public static final String FIELD_lastUpdatedTs = "lastUpdatedTs";

	private Long medicalTestPrescriptionId;
	private Long prescriptionId;
	private Long patientId;
	private Character active;

	public void setMedicalTestPrescriptionId(Long _medicalTestPrescriptionId) {
		this.medicalTestPrescriptionId = _medicalTestPrescriptionId;
	}

	@Id
	@Column(name = "medicalTestPrescriptionId")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Long getMedicalTestPrescriptionId() {
		return this.medicalTestPrescriptionId;
	}

	public void setPrescriptionId(Long _prescriptionId) {
		this.prescriptionId = _prescriptionId;
	}

	@NotNull
	@Column(name = "prescriptionId")
	public Long getPrescriptionId() {
		return this.prescriptionId;
	}

	public void setPatientId(Long _patientId) {
		this.patientId = _patientId;
	}

	@NotNull
	@Column(name = "patientId")
	public Long getPatientId() {
		return this.patientId;
	}

	public void setActive(Character _active) {
		this.active = _active;
	}

	@Column(name = "active")
	public Character getActive() {
		return this.active;
	}

	public void setCreatedBy(String _createdBy) {
		this.createdBy = _createdBy;
	}

	@Length(max = 45)
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
		BaseMedicaltestprescription other = (BaseMedicaltestprescription) o;
		if (ObjectUtil.isEqual(getPrescriptionId(), other.getPrescriptionId())
				&& ObjectUtil.isEqual(getPatientId(), other.getPatientId())
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
		result = result + (medicalTestPrescriptionId != null ? medicalTestPrescriptionId.hashCode() : 0);

		return result;
	}

	public void validate(boolean validatePk) {
		resetValidationMessages();

		List<ValidationMessage> list = new ArrayList<ValidationMessage>();
		if (validatePk) {
			if (this.medicalTestPrescriptionId == null) {
				list.add(new ValidationMessage("Field " + FIELD_medicalTestPrescriptionId + " cannot be null"));
			}

		}
		if (this.prescriptionId == null) {
			list.add(new ValidationMessage("Field " + FIELD_prescriptionId + " cannot be null"));
		}

		if (this.patientId == null) {
			list.add(new ValidationMessage("Field " + FIELD_patientId + " cannot be null"));
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
		str.append("medicalTestPrescriptionId = " + medicalTestPrescriptionId + "\n");
		;
		str.append("prescriptionId = " + prescriptionId + "\n");
		str.append("patientId = " + patientId + "\n");
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
		return (medicalTestPrescriptionId == null);
	}

	@Transient
	@Override
	public final List<KeyField> getKeyField() {
		List<KeyField> list = new ArrayList<KeyField>();
		list.add(new KeyField(FIELD_medicalTestPrescriptionId, getMedicalTestPrescriptionId()));
		return list;
	}

	@Transient
	@Override
	public Long getId() {
		return getMedicalTestPrescriptionId();
	}

	@Override
	public Object clone() throws CloneNotSupportedException {
		Medicaltestprescription medicaltestprescription = new Medicaltestprescription();
		medicaltestprescription.setFromDB(false);
		medicaltestprescription.setPrescriptionId(getPrescriptionId());
		medicaltestprescription.setPatientId(getPatientId());
		medicaltestprescription.setActive(getActive());
		medicaltestprescription.setCreatedDate(getCreatedDate());
		// afterClone(medicaltestprescription);
		return medicaltestprescription;
	}
}