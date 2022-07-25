package org.secondopinion.diagnosticcenter.request.dto;

import java.util.Date;

import org.secondopinion.diagnosticcenter.dto.Patientpaymentdetails;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonFormat.Shape;

public class AppointmentRequestDTO {

	private Long diagnosticCenterAddressId;
	private Long diagnosticCenterUserId;
	private Long patientUserId;
	private String patientName;
	private String diagnosticCenterName;
	private Long scheduleHourId;
	private Long fromScheduleId;
	private Long toScheduleId;
	private Date appointmentDate;
	private String reason;
	private Patientpaymentdetails patientpaymentdetails;

	public Patientpaymentdetails getPatientpaymentdetails() {
		return patientpaymentdetails;
	}

	public void setPatientpaymentdetails(Patientpaymentdetails patientpaymentdetails) {
		this.patientpaymentdetails = patientpaymentdetails;
	}

	public Long getDiagnosticCenterAddressId() {
		return diagnosticCenterAddressId;
	}

	public void setDiagnosticCenterAddressId(Long diagnosticCenterAddressId) {
		this.diagnosticCenterAddressId = diagnosticCenterAddressId;
	}

	public Long getDiagnosticCenterUserId() {
		return diagnosticCenterUserId;
	}

	public void setDiagnosticCenterUserId(Long diagnosticCenterUserId) {
		this.diagnosticCenterUserId = diagnosticCenterUserId;
	}

	public Long getPatientUserId() {
		return patientUserId;
	}

	public void setPatientUserId(Long patientUserId) {
		this.patientUserId = patientUserId;
	}

	public Long getScheduleHourId() {
		return scheduleHourId;
	}

	public void setScheduleHourId(Long scheduleId) {
		this.scheduleHourId = scheduleId;
	}

	public Long getFromScheduleId() {
		return fromScheduleId;
	}

	public void setFromScheduleId(Long fromScheduleId) {
		this.fromScheduleId = fromScheduleId;
	}

	public Long getToScheduleId() {
		return toScheduleId;
	}

	public void setToScheduleId(Long toScheduleId) {
		this.toScheduleId = toScheduleId;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	public String getPatientName() {
		return patientName;
	}

	public void setPatientName(String patientName) {
		this.patientName = patientName;
	}

	public String getDiagnosticCenterName() {
		return diagnosticCenterName;
	}

	public void setDiagnosticCenterName(String diagnosticCenterName) {
		this.diagnosticCenterName = diagnosticCenterName;
	}

	public Date getAppointmentDate() {
		return appointmentDate;
	}

	public void setAppointmentDate(Date appointmentDate) {
		this.appointmentDate = appointmentDate;
	}
}
