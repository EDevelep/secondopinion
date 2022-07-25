package org.secondopinion.diagnosticcenter.dto;

public class DiagnosticcenteruserUpdateDTO {
	private String emailId;
	private String firstName;
	private String lastName;
	private String middleName;
	private Long diagnosticCenterUserId;
	private Integer roleId;
	public String getEmailId() {
		return emailId;
	}
	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getMiddleName() {
		return middleName;
	}
	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	}
	public Integer getRoleId() {
		return roleId;
	}
	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
	}
	public Long getDiagnosticCenterUserId() {
		return diagnosticCenterUserId;
	}
	public void setDiagnosticCenterUserId(Long diagnosticCenterUserId) {
		this.diagnosticCenterUserId = diagnosticCenterUserId;
	}
}
