package org.secondopinion.pharmacy.dto;

import java.util.Date;

import org.secondopinion.enums.InvoiceStatusEnum;
import org.secondopinion.enums.InvoiceTypeEnum;

public class PatientInvoiceUpdateDTO {
	private Long entityInvoiceId;
	private Double amount;
	private String invoicestatus;
	private String invoicetype;
	private Long patientid;

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
	public Date getEndDate() {
		return endDate;
	}
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	public Long getEntityInvoiceId() {
		return entityInvoiceId;
	}
	public void setEntityInvoiceId(Long entityInvoiceId) {
		this.entityInvoiceId = entityInvoiceId;
	}
	public Double getAmount() {
		return amount;
	}
	public void setAmount(Double amount) {
		this.amount = amount;
	}
	public String getInvoicestatus() {
		return invoicestatus;
	}
	public void setInvoicestatus(String invoicestatus) {
		this.invoicestatus = invoicestatus;
	}
	public String getInvoicetype() {
		return invoicetype;
	}
	public void setInvoicetype(String invoicetype) {
		this.invoicetype = invoicetype;
	}
	public Long getPatientid() {
		return patientid;
	}
	public void setPatientid(Long patientid) {
		this.patientid = patientid;
	}
	public Double getDiscount() {
		return discount;
	}
	public void setDiscount(Double discount) {
		this.discount = discount;
	}

	public static PatientInvoiceUpdateDTO buildPatientInvoiceUpdateDTO(Invoice invoice, InvoiceStatusEnum invoiceStatusEnum) {
		PatientInvoiceUpdateDTO patientInvoiceUpdateDTO = new PatientInvoiceUpdateDTO();
		patientInvoiceUpdateDTO.setEntityInvoiceId(invoice.getInvoiceId());
		patientInvoiceUpdateDTO.setPatientid(invoice.getPatientid());
		patientInvoiceUpdateDTO.setInvoicetype(InvoiceTypeEnum.PHARMACY.name());
		patientInvoiceUpdateDTO.setAmount(invoice.getTotal());
		patientInvoiceUpdateDTO.setDiscount(invoice.getDiscount());
		patientInvoiceUpdateDTO.setInvoicestatus(invoiceStatusEnum.name());
		return patientInvoiceUpdateDTO;
	}

}
