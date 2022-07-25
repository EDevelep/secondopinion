package org.secondopinion.patient.dto;


public class UserResponceDTO {
	private Long userId;
	private String userName;
	private String firstName;
	private String lastName;
	private String middleName;
	private String emailId;

	private String cellNumber;
	private String officeNumber;
	private String homeNumber;
	private String primaryContact;
	private String emergencycontact;
	private String status;
	private String nativelanguage;
	private String preferredlanguages;
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
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
	public String getEmailId() {
		return emailId;
	}
	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}
	public String getCellNumber() {
		return cellNumber;
	}
	public void setCellNumber(String cellNumber) {
		this.cellNumber = cellNumber;
	}
	public String getOfficeNumber() {
		return officeNumber;
	}
	public void setOfficeNumber(String officeNumber) {
		this.officeNumber = officeNumber;
	}
	public String getHomeNumber() {
		return homeNumber;
	}
	public void setHomeNumber(String homeNumber) {
		this.homeNumber = homeNumber;
	}
	public String getPrimaryContact() {
		return primaryContact;
	}
	public void setPrimaryContact(String primaryContact) {
		this.primaryContact = primaryContact;
	}
	public String getEmergencycontact() {
		return emergencycontact;
	}
	public void setEmergencycontact(String emergencycontact) {
		this.emergencycontact = emergencycontact;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getNativelanguage() {
		return nativelanguage;
	}
	public void setNativelanguage(String nativelanguage) {
		this.nativelanguage = nativelanguage;
	}
	public String getPreferredlanguages() {
		return preferredlanguages;
	}
	public void setPreferredlanguages(String preferredlanguages) {
		this.preferredlanguages = preferredlanguages;
	}

}
