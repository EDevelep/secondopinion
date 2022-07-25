package org.secondopinion.pharmacy.dto;

import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonFormat.Shape;

public class FillPrescriptionRequestDTO {
	private Long prescriptionFillRequestId;
	private String invoicestatus;
	private Long invoiceId;
	private Long patientId;
	private String patientName;
	private Long doctorId;
	private String doctorName;
	private String doctorSpecialization;
	private Long pharmacyaddressId;
	private Long patientAppointmentId;
	private Long doctorAppointmentId;
	private Date appointmentDate;
	private String documentLocation;
	private String documentName;
	private boolean refill;
	private boolean prescriptionUploaded;
	private String fromModule;
	private List<Medication> medications;
	private Shippingaddress shippingAddress;
	private String pharmacyName;
	private Long medicalPrescriptionId;
	public String getPharmacyName() {
		return pharmacyName;
	}
	public void setPharmacyName(String pharmacyName) {
		this.pharmacyName = pharmacyName;
	}

	public Long getInvoiceId() {
		return invoiceId;
	}
	public void setInvoiceId(Long invoiceId) {
		this.invoiceId = invoiceId;
	}

	public Long getPatientId() {
		return patientId;
	}
	public void setPatientId(Long patientId) {
		this.patientId = patientId;
	}

	public String getPatientName() {
		return patientName;
	}
	public void setPatientName(String patientName) {
		this.patientName = patientName;
	}

	public Long getPharmacyaddressId() {
		return pharmacyaddressId;
	}
	public void setPharmacyaddressId(Long pharmacyaddressId) {
		this.pharmacyaddressId = pharmacyaddressId;
	}

	public Long getPatientAppointmentId() {
		return patientAppointmentId;
	}
	public void setPatientAppointmentId(Long patientAppointmentId) {
		this.patientAppointmentId = patientAppointmentId;
	}
	
	public Long getDoctorAppointmentId() {
		return doctorAppointmentId;
	}
	public void setDoctorAppointmentId(Long doctorAppointmentId) {
		this.doctorAppointmentId = doctorAppointmentId;
	}
	
	public boolean isRefill() {
		return refill;
	}
	public void setRefill(boolean refill) {
		this.refill = refill;
	}

	public boolean isPrescriptionUploaded() {
		return prescriptionUploaded;
	}
	public void setPrescriptionUploaded(boolean prescriptionUploaded) {
		this.prescriptionUploaded = prescriptionUploaded;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd", shape = Shape.STRING)
	public Date getAppointmentDate() {
		return appointmentDate;
	}
	public void setAppointmentDate(Date appointmentDate) {
		this.appointmentDate = appointmentDate;
	}

	public String getDocumentLocation() {
		return documentLocation;
	}
	public void setDocumentLocation(String documentLocation) {
		this.documentLocation = documentLocation;
	}

	public String getDocumentName() {
		return documentName;
	}
	public void setDocumentName(String documentName) {
		this.documentName = documentName;
	}

	public List<Medication> getMedications() {
		return medications;
	}
	public void setMedications(List<Medication> medications) {
		this.medications = medications;
	}

	public String getFromModule() {
		return fromModule;
	}
	public void setFromModule(String fromModule) {
		this.fromModule = fromModule;
	}

	public Shippingaddress getShippingAddress() {
		return shippingAddress;
	}
	public void setShippingAddress(Shippingaddress shippingAddress) {
		this.shippingAddress = shippingAddress;
	}

	public String getInvoicestatus() {
		return invoicestatus;
	}
	public void setInvoicestatus(String invoicestatus) {
		this.invoicestatus = invoicestatus;
	}

	public Long getPrescriptionFillRequestId() {
		return prescriptionFillRequestId;
	}
	public void setPrescriptionFillRequestId(Long prescriptionFillRequestId) {
		this.prescriptionFillRequestId = prescriptionFillRequestId;
	}

	public Long getDoctorId() {
		return doctorId;
	}
	public void setDoctorId(Long doctorId) {
		this.doctorId = doctorId;
	}

	public String getDoctorName() {
		return doctorName;
	}
	public void setDoctorName(String doctorName) {
		this.doctorName = doctorName;
	}

	public String getDoctorSpecialization() {
		return doctorSpecialization;
	}
	public void setDoctorSpecialization(String doctorSpecialization) {
		this.doctorSpecialization = doctorSpecialization;
	}
	public Long getMedicalPrescriptionId() {
		return medicalPrescriptionId;
	}
	public void setMedicalPrescriptionId(Long medicalPrescriptionId) {
		this.medicalPrescriptionId = medicalPrescriptionId;
	}

	
}
