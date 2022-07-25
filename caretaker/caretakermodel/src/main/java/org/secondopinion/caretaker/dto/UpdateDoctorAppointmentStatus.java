package org.secondopinion.caretaker.dto;

import org.secondopinion.enums.AppointmentStatusEnum;

public class UpdateDoctorAppointmentStatus {
	private Long caretakerId;
	private AppointmentStatusEnum appointmentStatus;
	private Long scheduleHoursId;
	private String reason;
	private Long appointmentId;
	
	public Long getCaretakerId() {
		return caretakerId;
	}
	public void setCaretakerId(Long caretakerId) {
		this.caretakerId = caretakerId;
	}
	public AppointmentStatusEnum getAppointmentStatus() {
		return appointmentStatus;
	}
	public void setAppointmentStatus(AppointmentStatusEnum appointmentStatus) {
		this.appointmentStatus = appointmentStatus;
	}
	public Long getScheduleHoursId() {
		return scheduleHoursId;
	}
	public void setScheduleHoursId(Long scheduleHoursId) {
		this.scheduleHoursId = scheduleHoursId;
	}
	public String getReason() {
		return reason;
	}
	public void setReason(String reason) {
		this.reason = reason;
	}
	public Long getAppointmentId() {
		return appointmentId;
	}
	public void setAppointmentId(Long appointmentId) {
		this.appointmentId = appointmentId;
	}
}
