package org.secondopinion.pharmacy.dto;

public class PharmacyUserUpadteDTO {
	private String emailId;
	private String firstName;
	private String lastName;
	private String middleName;
	private Long pharmacyUserId;
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

	public Long getPharmacyUserId() {
		return pharmacyUserId;
	}

	public void setPharmacyUserId(Long pharmacyUserId) {
		this.pharmacyUserId = pharmacyUserId;
	}

	public Integer getRoleId() {
		return roleId;
	}

	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
	}

	
}
