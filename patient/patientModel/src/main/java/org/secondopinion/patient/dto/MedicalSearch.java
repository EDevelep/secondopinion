package org.secondopinion.patient.dto;

public class MedicalSearch {
	private ForUser forUser;
	private Long appointmentId;
	private Long userId;
	private String testName;
	private String testType;
	private String notes;

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

	public String getTestName() {
		return testName;
	}

	public void setTestName(String testName) {
		this.testName = testName;
	}

	public String getTestType() {
		return testType;
	}

	public void setTestType(String testType) {
		this.testType = testType;
	}

	public String getNotes() {
		return notes;
	}

	public void setNotes(String notes) {
		this.notes = notes;
	}

	public ForUser getForUser() {
		return forUser;
	}

	public void setForUser(ForUser forUser) {
		this.forUser = forUser;
	}
}
