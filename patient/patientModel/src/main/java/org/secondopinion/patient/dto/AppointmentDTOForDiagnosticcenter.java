package org.secondopinion.patient.dto;

import java.util.List;

public class AppointmentDTOForDiagnosticcenter {
	private List<Appointment> appointment;
	private ForUser forUser;
	private String paymentId;
	public List<Appointment> getAppointment() {
		return appointment;
	}
	public void setAppointment(List<Appointment> appointment) {
		this.appointment = appointment;
	}
	public ForUser getForUser() {
		return forUser;
	}
	public void setForUser(ForUser forUser) {
		this.forUser = forUser;
	}
	public String getPaymentId() {
		return paymentId;
	}
	public void setPaymentId(String paymentId) {
		this.paymentId = paymentId;
	}
	
	
}
