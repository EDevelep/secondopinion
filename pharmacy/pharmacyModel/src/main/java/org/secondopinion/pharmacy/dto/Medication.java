package org.secondopinion.pharmacy.dto;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonFormat.Shape;


public class Medication {


	private Long medicationId;
	private Long patientId;
	private Long medicalPrescriptionId;
	private Long numberofrefills;
	private String medicineName;
	private String type;
	private String power;
	private String medicineUsage;
	private String notes;
	private Integer numberOfDays;
	private Long quantity;
	private Date enddate;
	private String createdBy;
	private Date createdDate;
	public Long getMedicationId() {
		return medicationId;
	}
	public void setMedicationId(Long medicationId) {
		this.medicationId = medicationId;
	}
	public Long getPatientId() {
		return patientId;
	}
	public void setPatientId(Long patientId) {
		this.patientId = patientId;
	}
	public Long getMedicalPrescriptionId() {
		return medicalPrescriptionId;
	}
	public void setMedicalPrescriptionId(Long medicalPrescriptionId) {
		this.medicalPrescriptionId = medicalPrescriptionId;
	}
	public Long getNumberofrefills() {
		return numberofrefills;
	}
	public void setNumberofrefills(Long numberofrefills) {
		this.numberofrefills = numberofrefills;
	}
	public String getMedicineName() {
		return medicineName;
	}
	public void setMedicineName(String medicineName) {
		this.medicineName = medicineName;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getPower() {
		return power;
	}
	public void setPower(String power) {
		this.power = power;
	}
	public String getMedicineUsage() {
		return medicineUsage;
	}
	public void setMedicineUsage(String medicineUsage) {
		this.medicineUsage = medicineUsage;
	}
	public String getNotes() {
		return notes;
	}
	public void setNotes(String notes) {
		this.notes = notes;
	}
	public Integer getNumberOfDays() {
		return numberOfDays;
	}
	public void setNumberOfDays(Integer numberOfDays) {
		this.numberOfDays = numberOfDays;
	}
	public Long getQuantity() {
		return quantity;
	}
	public void setQuantity(Long quantity) {
		this.quantity = quantity;
	}
	@JsonFormat(pattern = "yyyy-MM-dd", shape = Shape.STRING)
	public Date getEnddate() {
		return enddate;
	}
	public void setEnddate(Date enddate) {
		this.enddate = enddate;
	}
	public String getCreatedBy() {
		return createdBy;
	}
	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}
	public Date getCreatedDate() {
		return createdDate;
	}
	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}
	
}