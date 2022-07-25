package org.secondopinion.patient.dto;

public class PatientRequest {
	private Long patientRequestId;
	private Long doctorId;
	private Long patientId;
	private String alignment;
	private String description;
	
	
	public Long getPatientRequestId() {
		return patientRequestId;
	}
	public void setPatientRequestId(Long patientRequestId) {
		this.patientRequestId = patientRequestId;
	}
	public Long getDoctorId() {
		return doctorId;
	}
	public void setDoctorId(Long doctorId) {
		this.doctorId = doctorId;
	}
	public Long getPatientId() {
		return patientId;
	}
	public void setPatientId(Long patientId) {
		this.patientId = patientId;
	}
	
	public String getAlignment() {
		return alignment;
	}
	public void setAlignment(String alignment) {
		this.alignment = alignment;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
	
}