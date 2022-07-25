package org.secondopinion.patient.dto;

public class PatientPaymentDetailsDTO {

	private String patientName;
	private Long patientId;
	private String orderId;
	private Double amount;
	private String aliment;
	public String getAliment() {
		return aliment;
	}
	public void setAliment(String aliment) {
		this.aliment = aliment;
	}
	public String getPatientName() {
		return patientName;
	}
	public void setPatientName(String patientName) {
		this.patientName = patientName;
	}
	public Long getPatientId() {
		return patientId;
	}
	public void setPatientId(Long patientId) {
		this.patientId = patientId;
	}
	public String getOrderId() {
		return orderId;
	}
	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}
	public Double getAmount() {
		return amount;
	}
	public void setAmount(Double amount) {
		this.amount = amount;
	}
}
