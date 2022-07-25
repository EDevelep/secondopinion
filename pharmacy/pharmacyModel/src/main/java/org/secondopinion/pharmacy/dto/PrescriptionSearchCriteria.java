package org.secondopinion.pharmacy.dto;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonFormat.Shape;

public class PrescriptionSearchCriteria {

	private Long prescriptionFillRequestId;
	private String patientName;
	private String doctorName;
	private Long pharmacyId;
	private String medicineName;
	private Long prescriptionPriceId;
	private Long appointmentId;
	private Date invoiceCreatedDate;
	private Date requestSentTimeMin;
	private Date requestSentTimeMax;
	private Date deliveredOnMin;
	private Date deliveredOnMax;
	public Long getPrescriptionFillRequestId() {
		return prescriptionFillRequestId;
	}
	public void setPrescriptionFillRequestId(Long prescriptionFillRequestId) {
		this.prescriptionFillRequestId = prescriptionFillRequestId;
	}
	public String getPatientName() {
		return patientName;
	}
	public void setPatientName(String patientName) {
		this.patientName = patientName;
	}
	public String getDoctorName() {
		return doctorName;
	}
	public void setDoctorName(String doctorName) {
		this.doctorName = doctorName;
	}
	public Long getPharmacyId() {
		return pharmacyId;
	}
	public void setPharmacyId(Long pharmacyId) {
		this.pharmacyId = pharmacyId;
	}
	public String getMedicineName() {
		return medicineName;
	}
	public void setMedicineName(String medicineName) {
		this.medicineName = medicineName;
	}
	public Long getPrescriptionPriceId() {
		return prescriptionPriceId;
	}
	public void setPrescriptionPriceId(Long prescriptionPriceId) {
		this.prescriptionPriceId = prescriptionPriceId;
	}
	public Long getAppointmentId() {
		return appointmentId;
	}
	public void setAppointmentId(Long appointmentId) {
		this.appointmentId = appointmentId;
	}
	@JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSXXX", shape = Shape.STRING)
	public Date getInvoiceCreatedDate() {
		return invoiceCreatedDate;
	}
	public void setInvoiceCreatedDate(Date invoiceCreatedDate) {
		this.invoiceCreatedDate = invoiceCreatedDate;
	}
	@JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSXXX", shape = Shape.STRING)
	public Date getRequestSentTimeMin() {
		return requestSentTimeMin;
	}
	public void setRequestSentTimeMin(Date requestSentTimeMin) {
		this.requestSentTimeMin = requestSentTimeMin;
	}
	@JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSXXX", shape = Shape.STRING)
	public Date getRequestSentTimeMax() {
		return requestSentTimeMax;
	}
	public void setRequestSentTimeMax(Date requestSentTimeMax) {
		this.requestSentTimeMax = requestSentTimeMax;
	}
	@JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSXXX", shape = Shape.STRING)
	public Date getDeliveredOnMin() {
		return deliveredOnMin;
	}
	public void setDeliveredOnMin(Date deliveredOnMin) {
		this.deliveredOnMin = deliveredOnMin;
	}
	@JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSXXX", shape = Shape.STRING)
	public Date getDeliveredOnMax() {
		return deliveredOnMax;
	}
	public void setDeliveredOnMax(Date deliveredOnMax) {
		this.deliveredOnMax = deliveredOnMax;
	}
	
	
}
