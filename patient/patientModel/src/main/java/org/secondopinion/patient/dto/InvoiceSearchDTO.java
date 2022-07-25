package org.secondopinion.patient.dto;

import org.secondopinion.enums.InvoiceStatusEnum;
import org.secondopinion.enums.InvoiceTypeEnum;

public class InvoiceSearchDTO {

	private Long patientid;
	private String patientname;
	private Long doctorId;
	private String doctorName;
	private Long patientAppointmentId;
	private Long doctorAppointmentId;
	private Long pharmacyaddressId;
	private String pharmacyName;
	private Long medicalPrescriptionId;
	private Long prescriptionFillRequestId;
	private InvoiceStatusEnum invoiceStatusEnum;
	private InvoiceTypeEnum invoiceTypeEnum;
	private Long entityInvoiceId;
	private int pageNum;
	private int maxResults;
	private Long patientInvoiceId;
	public Long getPatientid() {
		return patientid;
	}
	public void setPatientid(Long patientid) {
		this.patientid = patientid;
	}
	public String getPatientname() {
		return patientname;
	}
	public void setPatientname(String patientname) {
		this.patientname = patientname;
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
	
	public Long getPharmacyaddressId() {
		return pharmacyaddressId;
	}
	public void setPharmacyaddressId(Long pharmacyaddressId) {
		this.pharmacyaddressId = pharmacyaddressId;
	}
	public String getPharmacyName() {
		return pharmacyName;
	}
	public void setPharmacyName(String pharmacyName) {
		this.pharmacyName = pharmacyName;
	}
	public Long getPrescriptionFillRequestId() {
		return prescriptionFillRequestId;
	}
	public void setPrescriptionFillRequestId(Long prescriptionFillRequestId) {
		this.prescriptionFillRequestId = prescriptionFillRequestId;
	}
	public Long getMedicalPrescriptionId() {
		return medicalPrescriptionId;
	}
	public void setMedicalPrescriptionId(Long medicalPrescriptionId) {
		this.medicalPrescriptionId = medicalPrescriptionId;
	}
	public InvoiceStatusEnum getInvoiceStatusEnum() {
		return invoiceStatusEnum;
	}
	public void setInvoiceStatusEnum(InvoiceStatusEnum invoiceStatusEnum) {
		this.invoiceStatusEnum = invoiceStatusEnum;
	}
	public InvoiceTypeEnum getInvoiceTypeEnum() {
		return invoiceTypeEnum;
	}
	public void setInvoiceTypeEnum(InvoiceTypeEnum invoiceTypeEnum) {
		this.invoiceTypeEnum = invoiceTypeEnum;
	}
	public Long getEntityInvoiceId() {
		return entityInvoiceId;
	}
	public void setEntityInvoiceId(Long entityInvoiceId) {
		this.entityInvoiceId = entityInvoiceId;
	}
	public Long getPatientInvoiceId() {
		return patientInvoiceId;
	}
	public void setPatientInvoiceId(Long patientInvoiceId) {
		this.patientInvoiceId=patientInvoiceId;
	}
	public int getPageNum() {
		return pageNum;
	}
	public void setPageNum(int pageNum) {
		this.pageNum = pageNum;
	}
	public int getMaxResults() {
		return maxResults;
	}
	public void setMaxResults(int maxResults) {
		this.maxResults = maxResults;
	}
	
	
	
}
