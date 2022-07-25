package org.secondopinion.patient.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Date;
import org.secondopinion.utils.threadlocal.AppThreadLocal;
import org.secondopinion.domain.BaseDomainObject;
import org.secondopinion.domain.KeyField;
import org.secondopinion.domain.validation.ValidationMessage;
import org.secondopinion.patient.dto.Medicalprescription;
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
public abstract class BaseMedicalprescription extends BaseDomainObject<Long> {

	public static final String FIELD_medicalPrescriptionId = "medicalPrescriptionId";
	public static final String FIELD_patientId = "patientId";
	public static final String FIELD_documentLocation = "documentLocation";
	public static final String FIELD_documentName = "documentName";
	public static final String FIELD_active = "active";
	public static final String FIELD_numberofrefills = "numberofrefills";
	public static final String FIELD_numberOfUnits = "numberOfUnits";
	public static final String FIELD_lastfilereqtime = "lastfilereqtime";
	public static final String FIELD_nextfilrequestdate = "nextfilrequestdate";
	public static final String FIELD_prescriptionId = "prescriptionId";
	public static final String FIELD_createdBy = "createdBy";
	public static final String FIELD_createdDate = "createdDate";
	public static final String FIELD_lastUpdatedBy = "lastUpdatedBy";
	public static final String FIELD_lastUpdatedTs = "lastUpdatedTs";

	private Long medicalPrescriptionId;
	private Long patientId;
	private Long prescriptionId;
	private String documentLocation;
	private String documentName;
	private Character active;
	private Integer numberofrefills;
	private Long numberOfUnits;
	private Date lastfilereqtime;
	private Date nextfilrequestdate;

	public void setMedicalPrescriptionId(Long _medicalPrescriptionId) {
		this.medicalPrescriptionId = _medicalPrescriptionId;
	}

	@Id
	@Column(name = "medicalPrescriptionId")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Long getMedicalPrescriptionId() {
		return this.medicalPrescriptionId;
	}

	@NotNull
	@Column(name = "patientId")
	public Long getPatientId() {
		return patientId;
	}

	public void setPatientId(Long patientId) {
		this.patientId = patientId;
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

	@Column(name = "active")
	public Character getActive() {
		return this.active;
	}

	public void setNumberofrefills(Integer _numberofrefills) {
		this.numberofrefills = _numberofrefills;
	}

	@Column(name = "numberofrefills")
	public Integer getNumberofrefills() {
		return this.numberofrefills;
	}

	public void setNumberOfUnits(Long _numberOfUnits) {
		this.numberOfUnits = _numberOfUnits;
	}

	@Column(name = "numberOfUnits")
	public Long getNumberOfUnits() {
		return this.numberOfUnits;
	}

	public void setLastfilereqtime(Date _lastfilereqtime) {
		this.lastfilereqtime = _lastfilereqtime;
	}

	@Column(name = "lastfilereqtime")
	public Date getLastfilereqtime() {
		return this.lastfilereqtime;
	}

	@Column(name = "nextfilrequestdate")
	public Date getNextfilrequestdate() {
		return nextfilrequestdate;
	}

	public void setNextfilrequestdate(Date nextfilrequestdate) {
		this.nextfilrequestdate = nextfilrequestdate;
	}

	@Column(name = "prescriptionId")
	public Long getPrescriptionId() {
		return prescriptionId;
	}

	public void setPrescriptionId(Long prescriptionId) {
		this.prescriptionId = prescriptionId;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		BaseMedicalprescription other = (BaseMedicalprescription) o;
		if (ObjectUtil.isEqual(getPatientId(), other.getPatientId())

				&& ObjectUtil.isEqual(getDocumentLocation(), other.getDocumentLocation())
				&& ObjectUtil.isEqual(getDocumentName(), other.getDocumentName())
				&& ObjectUtil.isEqual(getActive(), other.getActive())
				&& ObjectUtil.isEqual(getNumberofrefills(), other.getNumberofrefills())
				&& ObjectUtil.isEqual(getNumberOfUnits(), other.getNumberOfUnits())
				&& ObjectUtil.isEqual(getLastfilereqtime(), other.getLastfilereqtime())
				&& ObjectUtil.isEqual(getNextfilrequestdate(), other.getNextfilrequestdate())
				&& ObjectUtil.isEqual(getPrescriptionId(), other.getPrescriptionId())
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
		result = result + (medicalPrescriptionId != null ? medicalPrescriptionId.hashCode() : 0);

		return result;
	}

	public void validate(boolean validatePk) {
		resetValidationMessages();

		List<ValidationMessage> list = new ArrayList<ValidationMessage>();
		if (validatePk) {
			if (this.medicalPrescriptionId == null) {
				list.add(new ValidationMessage("Field " + FIELD_medicalPrescriptionId + " cannot be null"));
			}

		}
		if (this.patientId == null) {
			list.add(new ValidationMessage("Field " + FIELD_patientId + " cannot be null"));
		}

		if ((this.documentLocation != null) && (this.documentLocation.length() > 255)) {
			list.add(new ValidationMessage("Field " + FIELD_documentLocation + " cannot be longer than: " + 255));
		}

		if ((this.documentName != null) && (this.documentName.length() > 255)) {
			list.add(new ValidationMessage("Field " + FIELD_documentName + " cannot be longer than: " + 255));
		}

		if (this.isFromDB()) {
			if ((this.createdBy != null) && (this.createdBy.length() > 45)) {
				list.add(new ValidationMessage("Field " + FIELD_createdBy + " cannot be longer than: " + 45));
			}
		}
		if (this.isFromDB()) {
			if ((this.lastUpdatedBy != null) && (this.lastUpdatedBy.length() > 255)) {
				list.add(new ValidationMessage("Field " + FIELD_lastUpdatedBy + " cannot be longer than: " + 255));
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
		str.append("medicalPrescriptionId = " + medicalPrescriptionId + "\n");
		;
		str.append("patientId = " + patientId + "\n");
		str.append("documentLocation = " + documentLocation + "\n");
		str.append("documentName = " + documentName + "\n");
		str.append("active = " + active + "\n");
		str.append("numberofrefills = " + numberofrefills + "\n");
		str.append("numberOfUnits = " + numberOfUnits + "\n");
		str.append("lastfilereqtime = " + lastfilereqtime + "\n");
		str.append("nextfilrequestdate = " + nextfilrequestdate + "\n");
		str.append("prescriptionId = " + prescriptionId + "\n");
		str.append("createdBy = " + createdBy + "\n");
		str.append("createdDate = " + createdDate + "\n");
		str.append("lastUpdatedBy = " + lastUpdatedBy + "\n");
		str.append("lastUpdatedTs = " + lastUpdatedTs + "\n");
		return str.toString();
	}

	@Transient
	@Override
	public final boolean isKeyNull() {
		return (medicalPrescriptionId == null);
	}

	@Transient
	@Override
	public final List<KeyField> getKeyField() {
		List<KeyField> list = new ArrayList<KeyField>();
		list.add(new KeyField(FIELD_medicalPrescriptionId, getMedicalPrescriptionId()));
		return list;
	}

	@Transient
	@Override
	public Long getId() {
		return getMedicalPrescriptionId();
	}

	@Override
	public Object clone() throws CloneNotSupportedException {
		Medicalprescription medicalprescription = new Medicalprescription();
		medicalprescription.setFromDB(false);
		medicalprescription.setPatientId(getPatientId());
		medicalprescription.setDocumentLocation(getDocumentLocation());
		medicalprescription.setDocumentName(getDocumentName());
		medicalprescription.setActive(getActive());
		medicalprescription.setNumberofrefills(getNumberofrefills());
		medicalprescription.setNumberOfUnits(getNumberOfUnits());
		medicalprescription.setLastfilereqtime(getLastfilereqtime());
		medicalprescription.setNextfilrequestdate(getNextfilrequestdate());
		medicalprescription.setPrescriptionId(getPrescriptionId());
		medicalprescription.setCreatedDate(getCreatedDate());
		// afterClone(medicalprescription);
		return medicalprescription;
	}
}