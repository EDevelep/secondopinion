package org.secondopinion.diagnosticcenter.request.dto;

public class PatientConfirmRequestDTO {

	private Long diagnosticCenterAddressId;
	private Long patientUserId;
	private Long scheduleId;
	
	public Long getDiagnosticCenterAddressId() {
		return diagnosticCenterAddressId;
	}

	public void setDiagnosticCenterAddressId(Long diagnosticCenterAddressId) {
		this.diagnosticCenterAddressId = diagnosticCenterAddressId;
	}

	public Long getPatientUserId() {
		return patientUserId;
	}

	public void setPatientUserId(Long patientUserId) {
		this.patientUserId = patientUserId;
	}

	public Long getScheduleId() {
		return scheduleId;
	}

	public void setScheduleId(Long scheduleId) {
		this.scheduleId = scheduleId;
	}

}
