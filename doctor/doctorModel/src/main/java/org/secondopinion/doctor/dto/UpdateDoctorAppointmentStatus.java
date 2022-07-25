package org.secondopinion.doctor.dto;

import org.secondopinion.enums.AppointmentStatusEnum;

public class UpdateDoctorAppointmentStatus {

	private Long doctorId;
	private AppointmentStatusEnum appointmentStatus;
	private Long scheduleHoursId;
	private String reason;
	private Long appointmentId;
	
	public Long getDoctorId() {
		return doctorId;
	}
	public void setDoctorId(Long doctorId) {
		this.doctorId = doctorId;
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
