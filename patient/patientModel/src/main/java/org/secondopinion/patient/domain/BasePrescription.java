package org.secondopinion.patient.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.Date;

import org.secondopinion.utils.threadlocal.AppThreadLocal;
import org.secondopinion.domain.BaseDomainObject;
import org.secondopinion.domain.KeyField;
import org.secondopinion.domain.validation.ValidationMessage;
import org.secondopinion.patient.dto.Prescription;
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
public abstract class BasePrescription extends BaseDomainObject<Long> {

	public static final String FIELD_prescriptionId = "prescriptionId";
	public static final String FIELD_patientId = "patientId";
	public static final String FIELD_doctorId = "doctorId";
	public static final String FIELD_doctorName = "doctorName";
	public static final String FIELD_doctorAppointmentId = "doctorAppointmentId";
	public static final String FIELD_patientAppointmentId = "patientAppointmentId";
	public static final String FIELD_appointmentDate = "appointmentDate";
	public static final String FIELD_documentLocation = "documentLocation";
	public static final String FIELD_documentName = "documentName";
	public static final String FIELD_containsMedicalPrescription = "containsMedicalPrescription";
	public static final String FIELD_containsTestPrescription = "containsTestPrescription";
	public static final String FIELD_prescriptionFill = "prescriptionFill";
	public static final String FIELD_active = "active";
	public static final String FIELD_createdBy = "createdBy";
	public static final String FIELD_createdDate = "createdDate";
	public static final String FIELD_lastUpdatedBy = "lastUpdatedBy";
	public static final String FIELD_lastUpdatedTs = "lastUpdatedTs";

	private Long prescriptionId;
	private Long patientId;
	private Long doctorId;
	private String doctorName;
	private Long doctorAppointmentId;
	private Long patientAppointmentId;
	private Date appointmentDate;
	private String documentLocation;
	private String documentName;
	private Character containsMedicalPrescription;
	private Character containsTestPrescription;
	private Character prescriptionFill;
	private Character active;
	private Character prescriptioncontainsImage;

	public void setPrescriptionId(Long _prescriptionId) {
		this.prescriptionId = _prescriptionId;
	}

	@Id
	@Column(name = "prescriptionId")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
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

	public void setDoctorId(Long _doctorId) {
		this.doctorId = _doctorId;
	}

	@NotNull
	@Column(name = "doctorId")
	public Long getDoctorId() {
		return this.doctorId;
	}

	public void setDoctorName(String _doctorName) {
		this.doctorName = _doctorName;
	}

	@NotNull
	@Length(max = 100)
	@Column(name = "doctorName")
	public String getDoctorName() {
		return this.doctorName;
	}

	public void setDoctorAppointmentId(Long _doctorAppointmentId) {
		this.doctorAppointmentId = _doctorAppointmentId;
	}

	@Column(name = "doctorAppointmentId")
	public Long getDoctorAppointmentId() {
		return this.doctorAppointmentId;
	}

	public void setPatientAppointmentId(Long _patientAppointmentId) {
		this.patientAppointmentId = _patientAppointmentId;
	}

	@Column(name = "patientAppointmentId")
	public Long getPatientAppointmentId() {
		return this.patientAppointmentId;
	}

	public void setAppointmentDate(Date _appointmentDate) {
		this.appointmentDate = _appointmentDate;
	}

	@Column(name = "appointmentDate")
	public Date getAppointmentDate() {
		return this.appointmentDate;
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

	public void setContainsMedicalPrescription(Character _containsMedicalPrescription) {
		this.containsMedicalPrescription = _containsMedicalPrescription;
	}

	@Column(name = "containsMedicalPrescription")
	public Character getContainsMedicalPrescription() {
		return this.containsMedicalPrescription;
	}

	public void setContainsTestPrescription(Character _containsTestPrescription) {
		this.containsTestPrescription = _containsTestPrescription;
	}

	@Column(name = "containsTestPrescription")
	public Character getContainsTestPrescription() {
		return this.containsTestPrescription;
	}
	@Column(name = "prescriptioncontainsImage")
	public Character getPrescriptioncontainsImage() {
		return prescriptioncontainsImage;
	}

	public void setPrescriptioncontainsImage(Character prescriptioncontainsImage) {
		this.prescriptioncontainsImage = prescriptioncontainsImage;
	}
	@Column(name = "prescriptionFill")
	public Character getPrescriptionFill() {
		return prescriptionFill;
	}

	public void setPrescriptionFill(Character prescriptionFill) {
		this.prescriptionFill = prescriptionFill;
	}

	public void setActive(Character _active) {
		this.active = _active;
	}

	@Column(name = "active")
	public Character getActive() {
		return this.active;
	}

	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		BasePrescription other = (BasePrescription) o;
		if (ObjectUtil.isEqual(getPatientId(), other.getPatientId())
				&& ObjectUtil.isEqual(getDoctorId(), other.getDoctorId())
				&& ObjectUtil.isEqual(getDoctorName(), other.getDoctorName())
				&& ObjectUtil.isEqual(getDoctorAppointmentId(), other.getDoctorAppointmentId())
				&& ObjectUtil.isEqual(getPatientAppointmentId(), other.getPatientAppointmentId())
				&& ObjectUtil.isEqual(getAppointmentDate(), other.getAppointmentDate())
				&& ObjectUtil.isEqual(getDocumentLocation(), other.getDocumentLocation())
				&& ObjectUtil.isEqual(getDocumentName(), other.getDocumentName())
				&& ObjectUtil.isEqual(getContainsMedicalPrescription(), other.getContainsMedicalPrescription())
				&& ObjectUtil.isEqual(getContainsTestPrescription(), other.getContainsTestPrescription())
				&& ObjectUtil.isEqual(getPrescriptioncontainsImage(), other.getPrescriptioncontainsImage())
				&& ObjectUtil.isEqual(getActive(), other.getActive())
				&& ObjectUtil.isEqual(getPrescriptionFill(), other.getPrescriptionFill())
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
		result = result + (prescriptionId != null ? prescriptionId.hashCode() : 0);

		return result;
	}

	public void validate(boolean validatePk) {
		resetValidationMessages();

		List<ValidationMessage> list = new ArrayList<ValidationMessage>();
		if (validatePk) {
			if (this.prescriptionId == null) {
				list.add(new ValidationMessage("Field " + FIELD_prescriptionId + " cannot be null"));
			}

		}
		if (this.patientId == null) {
			list.add(new ValidationMessage("Field " + FIELD_patientId + " cannot be null"));
		}

		if (this.doctorId == null) {
			list.add(new ValidationMessage("Field " + FIELD_doctorId + " cannot be null"));
		}

		if (StringUtil.isNullOrEmpty(this.doctorName)) {
			list.add(new ValidationMessage("Field " + FIELD_doctorName + " cannot be null"));
		}

		if ((this.doctorName != null) && (this.doctorName.length() > 100)) {
			list.add(new ValidationMessage("Field " + FIELD_doctorName + " cannot be longer than: " + 100));
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
		str.append("prescriptionId = " + prescriptionId + "\n");
		;
		str.append("patientId = " + patientId + "\n");
		str.append("doctorId = " + doctorId + "\n");
		str.append("doctorName = " + doctorName + "\n");
		str.append("doctorAppointmentId = " + doctorAppointmentId + "\n");
		str.append("patientAppointmentId = " + patientAppointmentId + "\n");
		str.append("appointmentDate = " + appointmentDate + "\n");
		str.append("documentLocation = " + documentLocation + "\n");
		str.append("documentName = " + documentName + "\n");
		str.append("containsMedicalPrescription = " + containsMedicalPrescription + "\n");
		str.append("containsTestPrescription = " + containsTestPrescription + "\n");
		str.append("prescriptioncontainsImage = " + prescriptioncontainsImage + "\n");
		str.append(" prescriptionFill= " + prescriptionFill + "\n");
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
		return (prescriptionId == null);
	}

	@Transient
	@Override
	public final List<KeyField> getKeyField() {
		List<KeyField> list = new ArrayList<KeyField>();
		list.add(new KeyField(FIELD_prescriptionId, getPrescriptionId()));
		return list;
	}

	@Transient
	@Override
	public Long getId() {
		return getPrescriptionId();
	}

	@Override
	public Object clone() throws CloneNotSupportedException {
		Prescription prescription = new Prescription();
		prescription.setFromDB(false);
		prescription.setPatientId(getPatientId());
		prescription.setDoctorId(getDoctorId());
		prescription.setDoctorName(getDoctorName());
		prescription.setDoctorAppointmentId(getDoctorAppointmentId());
		prescription.setPatientAppointmentId(getPatientAppointmentId());
		prescription.setAppointmentDate(getAppointmentDate());
		prescription.setDocumentLocation(getDocumentLocation());
		prescription.setDocumentName(getDocumentName());
		prescription.setContainsMedicalPrescription(getContainsMedicalPrescription());
		prescription.setContainsTestPrescription(getContainsTestPrescription());
		prescription.setPrescriptioncontainsImage(getContainsMedicalPrescription());
		prescription.setPrescriptionFill(getPrescriptionFill());
		prescription.setActive(getActive());
		prescription.setCreatedDate(getCreatedDate());
		// afterClone(prescription);
		return prescription;
	}
}