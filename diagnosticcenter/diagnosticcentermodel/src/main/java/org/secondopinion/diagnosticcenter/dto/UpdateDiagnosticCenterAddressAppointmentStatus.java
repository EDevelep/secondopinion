package org.secondopinion.diagnosticcenter.dto;

import org.secondopinion.enums.AppointmentStatusEnum;

public class UpdateDiagnosticCenterAddressAppointmentStatus {

	private Long diagnosticCenterAddressId;
	private AppointmentStatusEnum appointmentStatus;
	private Long scheduleHoursId;
	private String reason;
	private Long appointmentId;
	
	
	public Long getDiagnosticCenterAddressId() {
		return diagnosticCenterAddressId;
	}
	public void setDiagnosticCenterAddressId(Long diagnosticCenterAddressId) {
		this.diagnosticCenterAddressId = diagnosticCenterAddressId;
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
