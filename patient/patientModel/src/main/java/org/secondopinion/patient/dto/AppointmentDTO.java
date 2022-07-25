package org.secondopinion.patient.dto;

public class AppointmentDTO {
	private Appointment appointment;
	private ForUser forUser;
	public Appointment getAppointment() {
		return appointment;
	}
	public void setAppointment(Appointment appointment) {
		this.appointment = appointment;
	}
	public ForUser getForUser() {
		return forUser;
	}
	public void setForUser(ForUser forUser) {
		this.forUser = forUser;
	}

	private String paymentId;
	public String getPaymentId() {
		return paymentId;
	}
	public void setPaymentId(String paymentId) {
		this.paymentId = paymentId;
	}
}
