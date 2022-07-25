package org.secondopinion.pharmacy.domain;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;
import org.secondopinion.domain.BaseDomainObject;
import org.secondopinion.domain.KeyField;
import org.secondopinion.domain.validation.ValidationMessage;
import org.secondopinion.pharmacy.dto.Prescriptionfillrequest;
import org.secondopinion.utils.ObjectUtil;
import org.secondopinion.utils.StringUtil;
import org.secondopinion.utils.threadlocal.AppThreadLocal;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonFormat.Shape;

@MappedSuperclass
public abstract class BasePrescriptionfillrequest extends BaseDomainObject<Long> {
//pharmacyaddressId
	public static final String FIELD_prescriptionFillRequestId = "prescriptionFillRequestId";
	public static final String FIELD_pharmacyaddressId = "pharmacyaddressId";
	public static final String FIELD_patientId = "patientId";
	public static final String FIELD_patientName = "patientName";
	public static final String FIELD_doctorId = "doctorId";
	public static final String FIELD_doctorName = "doctorName";
	public static final String FIELD_patientAppointmentId = "patientAppointmentId";
	public static final String FIELD_doctorAppointmentId = "doctorAppointmentId";
	public static final String FIELD_documentLocation = "documentLocation";
	public static final String FIELD_documentName = "documentName";
	public static final String FIELD_fromModule = "fromModule";
	public static final String FIELD_refill = "refill";
	public static final String FIELD_newRequest = "newRequest";
	public static final String FIELD_requestSentTime = "requestSentTime";
	public static final String FIELD_prescriptionUploaded = "prescriptionUploaded";
	public static final String FIELD_filledBy = "filledBy";
	public static final String FIELD_filledOn = "filledOn";
	public static final String FIELD_deliveredOn = "deliveredOn";
	public static final String FIELD_deliveryType = "deliveryType";
	public static final String FIELD_active = "active";

	private Long prescriptionFillRequestId;
	private Long pharmacyaddressId;
	private Long patientId;
	private String patientName;
	private Long patientAppointmentId;
	private Long doctorAppointmentId;
	private Long doctorId;
	private String doctorName;
	private String documentLocation;
	private String documentName;
	private String fromModule;
	private Character refill;
	private Character newRequest;
	private Date requestSentTime;
	private Character prescriptionUploaded;
	private Long filledBy;
	private Date filledOn;
	private Date deliveredOn;
	private String deliveryType;
	private Character active;

	public void setPrescriptionFillRequestId(Long _prescriptionFillRequestId) {
		this.prescriptionFillRequestId = _prescriptionFillRequestId;
	}

	@Id
	@Column(name = "prescriptionFillRequestId")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Long getPrescriptionFillRequestId() {
		return this.prescriptionFillRequestId;
	}

	public void setPharmacyaddressId(Long _pharmacyaddressId) {
		this.pharmacyaddressId = _pharmacyaddressId;
	}

	@NotNull
	@Column(name = "pharmacyaddressId")
	public Long getPharmacyaddressId() {
		return this.pharmacyaddressId;
	}

	public void setPatientId(Long _patientId) {
		this.patientId = _patientId;
	}

	@NotNull
	@Column(name = "patientId")
	public Long getPatientId() {
		return this.patientId;
	}

	public void setPatientName(String _patientName) {
		this.patientName = _patientName;
	}

	@Length(max = 250)
	@Column(name = "patientName")
	public String getPatientName() {
		return this.patientName;
	}

	public void setPatientAppointmentId(Long _patientAppointmentId) {
		this.patientAppointmentId = _patientAppointmentId;
	}

	@NotNull
	@Column(name = "patientAppointmentId")
	public Long getPatientAppointmentId() {
		return this.patientAppointmentId;
	}

	public void setDoctorAppointmentId(Long _doctorAppointmentId) {
		this.doctorAppointmentId = _doctorAppointmentId;
	}

	@NotNull
	@Column(name = "doctorAppointmentId")
	public Long getDoctorAppointmentId() {
		return this.doctorAppointmentId;
	}

	public void setDoctorId(Long _doctorId) {
		this.doctorId = _doctorId;
	}

	@Column(name = "doctorId")
	public Long getDoctorId() {
		return this.doctorId;
	}

	public void setDoctorName(String _doctorName) {
		this.doctorName = _doctorName;
	}

	@Length(max = 250)
	@Column(name = "doctorName")
	public String getDoctorName() {
		return this.doctorName;
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

	public void setFromModule(String fromModule) {
		this.fromModule = fromModule;
	}

	@Length(max = 45)
	@Column(name = "fromModule")
	public String getFromModule() {
		return fromModule;
	}

	public void setRefill(Character refill) {
		this.refill = refill;
	}

	@NotNull
	@Column(name = "refill")
	public Character getRefill() {
		return this.refill;
	}

	public void setNewRequest(Character _newRequest) {
		this.newRequest = _newRequest;
	}

	@NotNull
	@Column(name = "newRequest")
	public Character getNewRequest() {
		return this.newRequest;
	}

	public void setRequestSentTime(Date _requestSentTime) {
		this.requestSentTime = _requestSentTime;
	}

	@NotNull
	@Column(name = "requestSentTime")
	public Date getRequestSentTime() {
		return this.requestSentTime;
	}

	public void setPrescriptionUploaded(Character _prescriptionUploaded) {
		this.prescriptionUploaded = _prescriptionUploaded;
	}

	@Column(name = "prescriptionUploaded")
	public Character getPrescriptionUploaded() {
		return this.prescriptionUploaded;
	}

	public void setFilledBy(Long _filledBy) {
		this.filledBy = _filledBy;
	}

	@Column(name = "filledBy")
	public Long getFilledBy() {
		return this.filledBy;
	}

	public void setFilledOn(Date _filledOn) {
		this.filledOn = _filledOn;
	}

	@Column(name = "filledOn")
	public Date getFilledOn() {
		return this.filledOn;
	}

	public void setDeliveredOn(Date _deliveredOn) {
		this.deliveredOn = _deliveredOn;
	}

	@Column(name = "deliveredOn")
	public Date getDeliveredOn() {
		return this.deliveredOn;
	}

	public void setDeliveryType(String _deliveryType) {
		this.deliveryType = _deliveryType;
	}

	@Length(max = 45)
	@Column(name = "deliveryType")
	public String getDeliveryType() {
		return this.deliveryType;
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
		BasePrescriptionfillrequest other = (BasePrescriptionfillrequest) o;
		if (ObjectUtil.isEqual(getPharmacyaddressId(), other.getPharmacyaddressId())
				&& ObjectUtil.isEqual(getPatientId(), other.getPatientId())
				&& ObjectUtil.isEqual(getPatientName(), other.getPatientName())
				&& ObjectUtil.isEqual(getPatientAppointmentId(), other.getPatientAppointmentId())
				&& ObjectUtil.isEqual(getDoctorAppointmentId(), other.getDoctorAppointmentId())
				&& ObjectUtil.isEqual(getDoctorId(), other.getDoctorId())
				&& ObjectUtil.isEqual(getDoctorName(), other.getDoctorName())
				&& ObjectUtil.isEqual(getDocumentLocation(), other.getDocumentLocation())
				&& ObjectUtil.isEqual(getDocumentName(), other.getDocumentName())
				&& ObjectUtil.isEqual(getFromModule(), other.getFromModule())
				&& ObjectUtil.isEqual(getRefill(), other.getRefill())
				&& ObjectUtil.isEqual(getNewRequest(), other.getNewRequest())
				&& ObjectUtil.isEqual(getRequestSentTime(), other.getRequestSentTime())
				&& ObjectUtil.isEqual(getPrescriptionUploaded(), other.getPrescriptionUploaded())
				&& ObjectUtil.isEqual(getFilledBy(), other.getFilledBy())
				&& ObjectUtil.isEqual(getFilledOn(), other.getFilledOn())
				&& ObjectUtil.isEqual(getActive(), other.getActive())
				&& ObjectUtil.isEqual(getDeliveredOn(), other.getDeliveredOn())
				&& ObjectUtil.isEqual(getDeliveryType(), other.getDeliveryType())
				&& ObjectUtil.isEqual(getLastUpdatedBy(), other.getLastUpdatedBy())
				&& ObjectUtil.isEqual(getLastUpdatedTs(), other.getLastUpdatedTs())) {
			return true;
		}
		return false;
	}

	@Override
	public int hashCode() {
		int result = 0;
		result = result + (prescriptionFillRequestId != null ? prescriptionFillRequestId.hashCode() : 0);

		return result;
	}

	public void validate(boolean validatePk) {
		resetValidationMessages();

		List<ValidationMessage> list = new ArrayList<ValidationMessage>();
		if (validatePk) {
			if (this.prescriptionFillRequestId == null) {
				list.add(new ValidationMessage("Field " + FIELD_prescriptionFillRequestId + " cannot be null"));
			}

		}
		if (this.pharmacyaddressId == null) {
			list.add(new ValidationMessage("Field " + FIELD_pharmacyaddressId + " cannot be null"));
		}

		if (this.patientId == null) {
			list.add(new ValidationMessage("Field " + FIELD_patientId + " cannot be null"));
		}

		if ((this.patientName != null) && (this.patientName.length() > 250)) {
			list.add(new ValidationMessage("Field " + FIELD_patientName + " cannot be longer than: " + 250));
		}

		if (this.doctorAppointmentId == null) {
			list.add(new ValidationMessage("Field " + FIELD_doctorAppointmentId + " cannot be null"));
		}
		if (this.patientAppointmentId == null) {
			list.add(new ValidationMessage("Field " + FIELD_patientAppointmentId + " cannot be null"));
		}

		if ((this.doctorName != null) && (this.doctorName.length() > 250)) {
			list.add(new ValidationMessage("Field " + FIELD_doctorName + " cannot be longer than: " + 250));
		}
		if ((this.documentLocation != null) && (this.documentLocation.length() > 255)) {
			list.add(new ValidationMessage("Field " + FIELD_documentLocation + " cannot be longer than: " + 255));
		}

		if ((this.documentName != null) && (this.documentName.length() > 255)) {
			list.add(new ValidationMessage("Field " + FIELD_documentName + " cannot be longer than: " + 255));
		}
		if (this.fromModule == null) {
			list.add(new ValidationMessage("Field " + FIELD_fromModule + " cannot be null"));
		}

		if ((this.fromModule != null) && (this.fromModule.length() > 45)) {
			list.add(new ValidationMessage("Field " + FIELD_fromModule + " cannot be longer than: " + 45));
		}
		if (this.refill == null) {
			list.add(new ValidationMessage("Field " + FIELD_refill + " cannot be null"));
		}

		if (this.newRequest == null) {
			list.add(new ValidationMessage("Field " + FIELD_newRequest + " cannot be null"));
		}

		if (this.requestSentTime == null) {
			list.add(new ValidationMessage("Field " + FIELD_requestSentTime + " cannot be null"));
		}

		if ((this.deliveryType != null) && (this.deliveryType.length() > 45)) {
			list.add(new ValidationMessage("Field " + FIELD_deliveryType + " cannot be longer than: " + 45));
		}
		if (this.active == null) {
			list.add(new ValidationMessage("Field " + FIELD_active + " cannot be null"));
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
		this.lastUpdatedTs = AppThreadLocal.getRequestStartDate();
	}

	@Override
	public String toString() {
		StringBuilder str = new StringBuilder();
		str.append("prescriptionFillRequestId = " + prescriptionFillRequestId + "\n");
		;
		str.append("pharmacyaddressId = " + pharmacyaddressId + "\n");
		str.append("patientId = " + patientId + "\n");
		str.append("patientName = " + patientName + "\n");
		str.append("patientAppointmentId = " + patientAppointmentId + "\n");
		str.append("doctorAppointmentId = " + doctorAppointmentId + "\n");
		str.append("doctorId = " + doctorId + "\n");
		str.append("doctorName = " + doctorName + "\n");
		str.append("documentLocation = " + documentLocation + "\n");
		str.append("documentName = " + documentName + "\n");
		str.append("fromModule = " + fromModule + "\n");
		str.append("isRefill = " + refill + "\n");
		str.append("newRequest = " + newRequest + "\n");
		str.append("requestSentTime = " + requestSentTime + "\n");
		str.append("prescriptionUploaded = " + prescriptionUploaded + "\n");
		str.append("filledBy = " + filledBy + "\n");
		str.append("filledOn = " + filledOn + "\n");
		str.append("deliveredOn = " + deliveredOn + "\n");
		str.append("deliveryType = " + deliveryType + "\n");
		str.append("active = " + active + "\n");
		str.append("lastUpdatedBy = " + lastUpdatedBy + "\n");
		str.append("lastUpdatedTs = " + lastUpdatedTs + "\n");
		return str.toString();
	}

	@Transient
	@Override
	public final boolean isKeyNull() {
		return (prescriptionFillRequestId == null);
	}

	@Transient
	@Override
	public final List<KeyField> getKeyField() {
		List<KeyField> list = new ArrayList<KeyField>();
		list.add(new KeyField(FIELD_prescriptionFillRequestId, getPrescriptionFillRequestId()));
		return list;
	}

	@Transient
	@Override
	public Long getId() {
		return getPrescriptionFillRequestId();
	}

	@Override
	public Object clone() throws CloneNotSupportedException {
		Prescriptionfillrequest prescriptionfillrequest = new Prescriptionfillrequest();
		prescriptionfillrequest.setFromDB(false);
		prescriptionfillrequest.setPharmacyaddressId(getPharmacyaddressId());
		prescriptionfillrequest.setPatientId(getPatientId());
		prescriptionfillrequest.setPatientName(getPatientName());
		prescriptionfillrequest.setDoctorAppointmentId(getDoctorAppointmentId());
		prescriptionfillrequest.setPatientAppointmentId(getPatientAppointmentId());
		prescriptionfillrequest.setDoctorId(getDoctorId());
		prescriptionfillrequest.setDoctorName(getDoctorName());
		prescriptionfillrequest.setDocumentLocation(getDocumentLocation());
		prescriptionfillrequest.setDocumentName(getDocumentName());
		prescriptionfillrequest.setFromModule(getFromModule());
		prescriptionfillrequest.setRefill(getRefill());
		prescriptionfillrequest.setNewRequest(getNewRequest());
		prescriptionfillrequest.setRequestSentTime(getRequestSentTime());
		prescriptionfillrequest.setPrescriptionUploaded(getPrescriptionUploaded());
		prescriptionfillrequest.setFilledBy(getFilledBy());
		prescriptionfillrequest.setFilledOn(getFilledOn());
		prescriptionfillrequest.setDeliveredOn(getDeliveredOn());
		prescriptionfillrequest.setDeliveryType(getDeliveryType());
		prescriptionfillrequest.setActive(getActive());
		// afterClone(prescriptionfillrequest);
		return prescriptionfillrequest;
	}
}