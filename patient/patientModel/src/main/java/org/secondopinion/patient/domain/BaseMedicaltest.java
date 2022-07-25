package org.secondopinion.patient.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Date;
import org.secondopinion.utils.threadlocal.AppThreadLocal;
import org.secondopinion.domain.BaseDomainObject;
import org.secondopinion.domain.KeyField;
import org.secondopinion.domain.validation.ValidationMessage;
import org.secondopinion.patient.dto.Medicaltest;
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
public abstract class BaseMedicaltest extends BaseDomainObject<Long> {

	public static final String FIELD_medicalTestId = "medicalTestId";
	public static final String FIELD_patientId = "patientId";
	public static final String FIELD_medicalTestPrescriptionId = "medicalTestPrescriptionId";
	public static final String FIELD_testName = "testName";
	public static final String FIELD_testType = "testType";
	public static final String FIELD_notes = "notes";
	public static final String FIELD_documentLocation = "documentLocation";
	public static final String FIELD_documentName = "documentName";
	public static final String FIELD_active = "active";
	public static final String FIELD_instructions = "instructions";
	public static final String FIELD_createdBy = "createdBy";
	public static final String FIELD_createdDate = "createdDate";
	public static final String FIELD_lastUpdatedBy = "lastUpdatedBy";
	public static final String FIELD_lastUpdatedTs = "lastUpdatedTs";

	private Long medicalTestId;
	private Long patientId;
	private Long medicalTestPrescriptionId;
	private String testName;
	private String testType;
	private String notes;
	private String documentLocation;
	private String documentName;
	private Character active;
	private String instructions;

	public void setMedicalTestId(Long _medicalTestId) {
		this.medicalTestId = _medicalTestId;
	}

	@Id
	@Column(name = "medicalTestId")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Long getMedicalTestId() {
		return this.medicalTestId;
	}

	public void setPatientId(Long _patientId) {
		this.patientId = _patientId;
	}

	@NotNull
	@Column(name = "patientId")
	public Long getPatientId() {
		return this.patientId;
	}

	@NotNull
	@Column(name = "medicalTestPrescriptionId")
	public Long getMedicalTestPrescriptionId() {
		return medicalTestPrescriptionId;
	}

	public void setMedicalTestPrescriptionId(Long medicalTestPrescriptionId) {
		this.medicalTestPrescriptionId = medicalTestPrescriptionId;
	}

	public void setTestName(String _testName) {
		this.testName = _testName;
	}

	@Length(max = 250)
	@Column(name = "testName")
	public String getTestName() {
		return this.testName;
	}

	public void setTestType(String _testType) {
		this.testType = _testType;
	}

	@Length(max = 100)
	@Column(name = "testType")
	public String getTestType() {
		return this.testType;
	}

	public void setNotes(String _notes) {
		this.notes = _notes;
	}

	@Length(max = 21845)
	@Column(name = "notes")
	public String getNotes() {
		return this.notes;
	}

	public void setDocumentLocation(String _documentLocation) {
		this.documentLocation = _documentLocation;
	}

	@Length(max = 255)
	@Column(name = "documentLocation")
	public String getDocumentLocation() {
		return this.documentLocation;
	}

	public void setDocumentName(String _documentName) {
		this.documentName = _documentName;
	}

	@Length(max = 255)
	@Column(name = "documentName")
	public String getDocumentName() {
		return this.documentName;
	}

	public void setActive(Character _active) {
		this.active = _active;
	}

	@NotNull
	@Column(name = "active")
	public Character getActive() {
		return this.active;
	}

	public void setInstructions(String _instructions) {
		this.instructions = _instructions;
	}

	@Length(max = 250)
	@Column(name = "instructions")
	public String getInstructions() {
		return this.instructions;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		BaseMedicaltest other = (BaseMedicaltest) o;
		if (ObjectUtil.isEqual(getPatientId(), other.getPatientId())
				&& ObjectUtil.isEqual(getMedicalTestPrescriptionId(), other.getMedicalTestPrescriptionId())
				&& ObjectUtil.isEqual(getTestName(), other.getTestName())
				&& ObjectUtil.isEqual(getTestType(), other.getTestType())
				&& ObjectUtil.isEqual(getNotes(), other.getNotes())
				&& ObjectUtil.isEqual(getDocumentLocation(), other.getDocumentLocation())
				&& ObjectUtil.isEqual(getDocumentName(), other.getDocumentName())
				&& ObjectUtil.isEqual(getActive(), other.getActive())
				&& ObjectUtil.isEqual(getInstructions(), other.getInstructions())
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
		result = result + (medicalTestId != null ? medicalTestId.hashCode() : 0);

		return result;
	}

	public void validate(boolean validatePk) {
		resetValidationMessages();

		List<ValidationMessage> list = new ArrayList<ValidationMessage>();
		if (validatePk) {
			if (this.medicalTestId == null) {
				list.add(new ValidationMessage("Field " + FIELD_medicalTestId + " cannot be null"));
			}

		}
		if (this.patientId == null) {
			list.add(new ValidationMessage("Field " + FIELD_patientId + " cannot be null"));
		}

		if (this.medicalTestPrescriptionId == null) {
			list.add(new ValidationMessage("Field " + medicalTestPrescriptionId + " cannot be null"));
		}

		if ((this.testName != null) && (this.testName.length() > 250)) {
			list.add(new ValidationMessage("Field " + FIELD_testName + " cannot be longer than: " + 250));
		}

		if ((this.testType != null) && (this.testType.length() > 100)) {
			list.add(new ValidationMessage("Field " + FIELD_testType + " cannot be longer than: " + 100));
		}

		if ((this.notes != null) && (this.notes.length() > 21845)) {
			list.add(new ValidationMessage("Field " + FIELD_notes + " cannot be longer than: " + 21845));
		}

		if ((this.documentLocation != null) && (this.documentLocation.length() > 255)) {
			list.add(new ValidationMessage("Field " + FIELD_documentLocation + " cannot be longer than: " + 255));
		}

		if ((this.documentName != null) && (this.documentName.length() > 255)) {
			list.add(new ValidationMessage("Field " + FIELD_documentName + " cannot be longer than: " + 255));
		}

		if (this.active == null) {
			list.add(new ValidationMessage("Field " + FIELD_active + " cannot be null"));
		}

		if ((this.instructions != null) && (this.instructions.length() > 250)) {
			list.add(new ValidationMessage("Field " + FIELD_instructions + " cannot be longer than: " + 250));
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
		str.append("medicalTestId = " + medicalTestId + "\n");
		;
		str.append("patientId = " + patientId + "\n");
		str.append("medicalTestPrescriptionId = " + medicalTestPrescriptionId + "\n");
		str.append("testName = " + testName + "\n");
		str.append("testType = " + testType + "\n");
		str.append("notes = " + notes + "\n");
		str.append("documentLocation = " + documentLocation + "\n");
		str.append("documentName = " + documentName + "\n");
		str.append("active = " + active + "\n");
		str.append("instructions = " + instructions + "\n");
		str.append("createdBy = " + createdBy + "\n");
		str.append("createdDate = " + createdDate + "\n");
		str.append("lastUpdatedBy = " + lastUpdatedBy + "\n");
		str.append("lastUpdatedTs = " + lastUpdatedTs + "\n");
		return str.toString();
	}

	@Transient
	@Override
	public final boolean isKeyNull() {
		return (medicalTestId == null);
	}

	@Transient
	@Override
	public final List<KeyField> getKeyField() {
		List<KeyField> list = new ArrayList<KeyField>();
		list.add(new KeyField(FIELD_medicalTestId, getMedicalTestId()));
		return list;
	}

	@Transient
	@Override
	public Long getId() {
		return getMedicalTestId();
	}

	@Override
	public Object clone() throws CloneNotSupportedException {
		Medicaltest medicaltest = new Medicaltest();
		medicaltest.setFromDB(false);
		medicaltest.setPatientId(getPatientId());
		medicaltest.setMedicalTestPrescriptionId(getMedicalTestPrescriptionId());
		medicaltest.setTestName(getTestName());
		medicaltest.setTestType(getTestType());
		medicaltest.setNotes(getNotes());
		medicaltest.setDocumentLocation(getDocumentLocation());
		medicaltest.setDocumentName(getDocumentName());
		medicaltest.setActive(getActive());
		medicaltest.setInstructions(getInstructions());
		medicaltest.setCreatedDate(getCreatedDate());
		// afterClone(medicaltest);
		return medicaltest;
	}
}