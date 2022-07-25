package org.secondopinion.pharmacy.dto;

import java.util.Date;

public class PrescriptionPriceUpdateDTO {
	private Long prescriptionPriceId;
	private Long patientId;
	private Long patientAppointmentId;
	private Long medicalPrescriptionId;
	private String medicine;
	private String type;
	private String power;
	private String dosage;
	private Long quantity;
	private Double unitPrice;
	private Double cgst;
	private Double sgst;
	private Double discount;
	private Double totalPrice;
	private Integer morning;
	private Integer afternoon;
	private Integer evening;
	private String instructions;
	private Date endDate;
	private Integer numberOfDays;
	public Integer getNumberOfDays() {
		return numberOfDays;
	}
	public void setNumberOfDays(Integer numberOfDays) {
		this.numberOfDays = numberOfDays;
	}
	public Long getPrescriptionPriceId() {
		return prescriptionPriceId;
	}
	public void setPrescriptionPriceId(Long prescriptionPriceId) {
		this.prescriptionPriceId = prescriptionPriceId;
	}
	public Long getPatientId() {
		return patientId;
	}
	public void setPatientId(Long patientId) {
		this.patientId = patientId;
	}
	public Long getPatientAppointmentId() {
		return patientAppointmentId;
	}
	public void setPatientAppointmentId(Long patientAppointmentId) {
		this.patientAppointmentId = patientAppointmentId;
	}
	public Long getMedicalPrescriptionId() {
		return medicalPrescriptionId;
	}
	public void setMedicalPrescriptionId(Long medicalPrescriptionId) {
		this.medicalPrescriptionId = medicalPrescriptionId;
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
	public String getDosage() {
		return dosage;
	}
	public void setDosage(String dosage) {
		this.dosage = dosage;
	}
	public Long getQuantity() {
		return quantity;
	}
	public void setQuantity(Long quantity) {
		this.quantity = quantity;
	}
	public Double getUnitPrice() {
		return unitPrice;
	}
	public void setUnitPrice(Double unitPrice) {
		this.unitPrice = unitPrice;
	}
	public Double getCgst() {
		return cgst;
	}
	public void setCgst(Double cgst) {
		this.cgst = cgst;
	}
	public Double getSgst() {
		return sgst;
	}
	public void setSgst(Double sgst) {
		this.sgst = sgst;
	}
	public Double getDiscount() {
		return discount;
	}
	public void setDiscount(Double discount) {
		this.discount = discount;
	}
	public Double getTotalPrice() {
		return totalPrice;
	}
	public void setTotalPrice(Double totalPrice) {
		this.totalPrice = totalPrice;
	}
	public Integer getMorning() {
		return morning;
	}
	public void setMorning(Integer morning) {
		this.morning = morning;
	}
	public Integer getAfternoon() {
		return afternoon;
	}
	public void setAfternoon(Integer afternoon) {
		this.afternoon = afternoon;
	}
	public Integer getEvening() {
		return evening;
	}
	public void setEvening(Integer evening) {
		this.evening = evening;
	}
	public String getInstructions() {
		return instructions;
	}
	public void setInstructions(String instructions) {
		this.instructions = instructions;
	}
	public Date getEndDate() {
		return endDate;
	}
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

}
