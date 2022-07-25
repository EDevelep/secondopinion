package org.secondopinion.patient.dto;

public class AppointmentBookingDTO {
	private ForUser forUser;
	public ForUser getForUser() {
		return forUser;
	}

	public void setForUser(ForUser forUser) {
		this.forUser = forUser;
	}

	private Long appointmentId;
	private Long userId;
	private String appointmentFor;
	private Long referenceEntityId;// dc addres id

	public Long getAppointmentId() {
		return appointmentId;
	}

	public void setAppointmentId(Long appointmentId) {
		this.appointmentId = appointmentId;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getAppointmentFor() {
		return appointmentFor;
	}

	public void setAppointmentFor(String appointmentFor) {
		this.appointmentFor = appointmentFor;
	}

	public Long getReferenceEntityId() {
		return referenceEntityId;
	}

	public void setReferenceEntityId(Long referenceEntityId) {
		this.referenceEntityId = referenceEntityId;
	}

	public String getReferenceEntityName() {
		return referenceEntityName;
	}

	public void setReferenceEntityName(String referenceEntityName) {
		this.referenceEntityName = referenceEntityName;
	}

	public String getPatientName() {
		return patientName;
	}

	public void setPatientName(String patientName) {
		this.patientName = patientName;
	}

	public String getAppointmentStatus() {
		return appointmentStatus;
	}

	public void setAppointmentStatus(String appointmentStatus) {
		this.appointmentStatus = appointmentStatus;
	}

	public Double getAmountPaid() {
		return amountPaid;
	}

	public void setAmountPaid(Double amountPaid) {
		this.amountPaid = amountPaid;
	}

	public String getPaymentId() {
		return paymentId;
	}

	public void setPaymentId(String paymentId) {
		this.paymentId = paymentId;
	}

	private String referenceEntityName;// dc name

	private String patientName;
	private String appointmentStatus;

	private Double amountPaid;
	private String paymentId;

}
