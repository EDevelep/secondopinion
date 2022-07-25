package org.secondopinion.patient.dto;

import java.util.Objects;

public class FillPrescriptionRequestDTO {

	private Long pharmacyaddressId;
	private String forUserName;
	private Long medicalprescriptionid;
	private Long shippingAddressId;
	private Long patientId;
	private Long diagnosticcenterId;
	private Long medicalTestId;

	public Long getMedicalTestId() {
		return medicalTestId;
	}

	public void setMedicalTestId(Long medicalTestId) {
		this.medicalTestId = medicalTestId;
	}

	public Long getDiagnosticcenterId() {
		return diagnosticcenterId;
	}

	public void setDiagnosticcenterId(Long diagnosticcenterId) {
		this.diagnosticcenterId = diagnosticcenterId;
	}

	public Long getPatientId() {
		return patientId;
	}

	public void setPatientId(Long patientId) {
		this.patientId = patientId;
	}

	public Long getPharmacyaddressId() {
		return pharmacyaddressId;
	}

	public void setPharmacyaddressId(Long pharmacyaddressId) {
		this.pharmacyaddressId = pharmacyaddressId;
	}

	public String getForUserName() {
		return forUserName;
	}

	public void setForUserName(String forUserName) {
		this.forUserName = forUserName;
	}

	public Long getMedicalprescriptionid() {
		return medicalprescriptionid;
	}

	public void setMedicalprescriptionid(Long medicalprescriptionid) {
		this.medicalprescriptionid = medicalprescriptionid;
	}

	public Long getShippingAddressId() {
		return shippingAddressId;
	}

	public void setShippingAddressId(Long shippingAddressId) {
		this.shippingAddressId = shippingAddressId;
	}

	public void validatefillPrescriptionRequestToPharmacy() {
		if (Objects.isNull(this.getMedicalprescriptionid())) {
			throw new IllegalArgumentException("Field medicalprescriptionid can not be null.");
		}
		if (Objects.isNull(this.getPharmacyaddressId())) {
			throw new IllegalArgumentException("Field pharmacyaddressId can not be null.");
		}
		if (Objects.isNull(this.getForUserName())) {
			throw new IllegalArgumentException("Field forUserName can not be null.");
		}
		if (Objects.isNull(this.getShippingAddressId())) {
			throw new IllegalArgumentException("ShippingAddress can not be null.");
		}
	}

}
