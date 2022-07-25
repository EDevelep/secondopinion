package org.secondopinion.patient.dto;

import java.math.BigInteger;
import java.util.Date;

public class UserDTO {
	private BigInteger userId;
	private String userName;
	private String firstName;
	private String lastName;
	private String middleName;
	private String emailId;
	private String password;
	private String cellNumber;
	private String officeNumber;
	private String homeNumber;
	private String primaryContact;// it should be a enum values home, cell, office
	private String emergencycontact;// name and number need validation
	private Character active;
	private Integer retry;
	private Character locked;
	private Date activatedDate;
	private BigInteger operatedByUser;
	private Date lastLogin;
	private String nativelanguage;
	private String preferredlanguages;
	
	
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
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
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
	public Character getActive() {
		return active;
	}
	public void setActive(Character active) {
		this.active = active;
	}
	public Integer getRetry() {
		return retry;
	}
	public void setRetry(Integer retry) {
		this.retry = retry;
	}
	public Character getLocked() {
		return locked;
	}
	public BigInteger getUserId() {
		return userId;
	}
	public void setUserId(BigInteger userId) {
		this.userId = userId;
	}
	public BigInteger getOperatedByUser() {
		return operatedByUser;
	}
	public void setOperatedByUser(BigInteger operatedByUser) {
		this.operatedByUser = operatedByUser;
	}
	public void setLocked(Character locked) {
		this.locked = locked;
	}
	public Date getActivatedDate() {
		return activatedDate;
	}
	public void setActivatedDate(Date activatedDate) {
		this.activatedDate = activatedDate;
	}
	
	public Date getLastLogin() {
		return lastLogin;
	}
	public void setLastLogin(Date lastLogin) {
		this.lastLogin = lastLogin;
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
