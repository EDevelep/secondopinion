package org.secondopinion.patient.dto;

import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonFormat.Shape;

public class UserResponseDTO {

	private Long userId;
	private String userName;
	private String firstName;
	private String lastName;
	private String middleName;
	private String emailId;
	private String cellNumber;
	private String officeNumber;
	private String homeNumber;
	private String emergencycontact;
	private Date lastLogin;
	private char relationShipExit;
	private Date activeDate;
	private Personaldetail personaldetail;
	private List<Relationship> relationship;
	private List<Address> address;

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
	
	public Date getLastLogin() {
		return lastLogin;
	}
	
	public void setLastLogin(Date lastLogin) {
		this.lastLogin = lastLogin;
	}

	public char getRelationShipExit() {
		return relationShipExit;
	}

	public void setRelationShipExit(char relationShipExit) {
		this.relationShipExit = relationShipExit;
	}

	public Personaldetail getPersonaldetail() {
		return personaldetail;
	}

	public Date getActiveDate() {
		return activeDate;
	}

	public void setActiveDate(Date activeDate) {
		this.activeDate = activeDate;
	}

	public void setPersonaldetail(Personaldetail personaldetail) {
		this.personaldetail = personaldetail;
	}

	public List<Relationship> getRelationship() {
		return relationship;
	}

	public void setRelationship(List<Relationship> relationship) {
		this.relationship = relationship;
	}

	public List<Address> getAddress() {
		return address;
	}

	public void setAddress(List<Address> address) {
		this.address = address;
	}

	public String getEmergencycontact() {
		return emergencycontact;
	}

	public void setEmergencycontact(String emergencycontact) {
		this.emergencycontact = emergencycontact;
	}

	public static UserResponseDTO builidUserObject(User user) {
		UserResponseDTO userResponseDTO = new UserResponseDTO();
		userResponseDTO.setUserId(user.getUserId());
		userResponseDTO.setEmailId(user.getEmailId());
		userResponseDTO.setFirstName(user.getFirstName());
		userResponseDTO.setLastName(user.getLastName());
		userResponseDTO.setCellNumber(user.getCellNumber());
		userResponseDTO.setMiddleName(user.getMiddleName());
		userResponseDTO.setUserName(user.getUserName());
		userResponseDTO.setLastLogin(user.getLastLogin());
		userResponseDTO.setEmergencycontact(user.getEmergencycontact());
		userResponseDTO.setOfficeNumber(user.getOfficeNumber());
		userResponseDTO.setActiveDate(user.getActivatedDate());
		return userResponseDTO;
	}

}
