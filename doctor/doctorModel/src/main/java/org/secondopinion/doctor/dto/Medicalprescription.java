package org.secondopinion.doctor.dto;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonFormat.Shape;


public class Medicalprescription {

	public static final String FIELD_medicalPrescriptionId = "medicalPrescriptionId";
	public static final String FIELD_appointmentId = "appointmentId";
	public static final String FIELD_patientId = "patientId";
	public static final String FIELD_medicine = "medicine";
	public static final String FIELD_type = "type";
	public static final String FIELD_power = "power";
	public static final String FIELD_medicineUsage = "medicineUsage";
	public static final String FIELD_numberOfDays = "numberOfDays";
	public static final String FIELD_notes = "notes";
	public static final String FIELD_numberofrefills = "numberofrefills";
	public static final String FIELD_enddate = "enddate";
	public static final String FIELD_createdBy = "createdBy";
	public static final String FIELD_createdDate = "createdDate";
	public static final String FIELD_lastUpdatedBy = "lastUpdatedBy";
	public static final String FIELD_lastUpdatedTs = "lastUpdatedTs";
	public static final String FIELD_numberOfUnits = "numberOfUnits";

	private Long medicalPrescriptionId;
	private Long appointmentId;
	private Long patientId;
	private Integer numberofrefills;
	private String medicine;
	private String type;
	private String power;
	private String medicineUsage;
	private String notes;
	private Integer numberOfDays;
	private byte[] precripationimage;
	private Long numberOfUnits;
	@JsonFormat(pattern = "yyyy-MM-dd", shape = Shape.STRING)
	private  Date enddate;
	private String createdBy;
	private Date createdDate;
	public Long getMedicalPrescriptionId() {
		return medicalPrescriptionId;
	}
	public void setMedicalPrescriptionId(Long medicalPrescriptionId) {
		this.medicalPrescriptionId = medicalPrescriptionId;
	}
	public Long getAppointmentId() {
		return appointmentId;
	}
	public void setAppointmentId(Long appointmentId) {
		this.appointmentId = appointmentId;
	}
	public Long getPatientId() {
		return patientId;
	}
	public void setPatientId(Long patientId) {
		this.patientId = patientId;
	}
	public Integer getNumberofrefills() {
		return numberofrefills;
	}
	public void setNumberofrefills(Integer numberofrefills) {
		this.numberofrefills = numberofrefills;
	}
	public String getMedicine() {
		return medicine;
	}
	public void setMedicine(String medicine) {
		this.medicine = medicine;
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
	public byte[] getPrecripationimage() {
		return precripationimage;
	}
	public void setPrecripationimage(byte[] precripationimage) {
		this.precripationimage = precripationimage;
	}
	public Long getNumberOfUnits() {
		return numberOfUnits;
	}
	public void setNumberOfUnits(Long numberOfUnits) {
		this.numberOfUnits = numberOfUnits;
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