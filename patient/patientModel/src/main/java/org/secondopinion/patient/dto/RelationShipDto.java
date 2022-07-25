package org.secondopinion.patient.dto;

import java.math.BigInteger;
import java.util.Date;

public class RelationShipDto {

	private BigInteger userId;
	private BigInteger relationUserId;
	private BigInteger relationshipId; 
	private String relationship;
	private String firstName;
	private String lastName;
	private String middleName;
	private String userName;
	private String emailId;
	private Character verified;
	private Date verifiedOn;
	private Character approved;
	private Date approvedOn;
	private Character active;
	private String cellNumber;
	private String addedBy;
	private  Character addedbyUser ;

	public Character getAddedbyUser() {
		return addedbyUser;
	}

	public void setAddedbyUser(Character addedbyUser) {
		this.addedbyUser = addedbyUser;
	}

	public String getAddedBy() {
		return addedBy;
	}

	public void setAddedBy(String addedBy) {
		this.addedBy = addedBy;
	}

	public BigInteger getUserId() {
		return userId;
	}

	public void setUserId(BigInteger userId) {
		this.userId = userId;
	}

	public BigInteger getRelationUserId() {
		return relationUserId;
	}

	public void setRelationUserId(BigInteger relationUserId) {
		this.relationUserId = relationUserId;
	}

	public String getRelationship() {
		return relationship;
	}

	public void setRelationship(String relationship) {
		this.relationship = relationship;
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

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getEmailId() {
		return emailId;
	}

	public Character getActive() {
		return active;
	}

	public void setActive(Character active) {
		this.active = active;
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

	public Character getVerified() {
		return verified;
	}

	public void setVerified(Character verified) {
		this.verified = verified;
	}

	public Date getVerifiedOn() {
		return verifiedOn;
	}

	public void setVerifiedOn(Date verifiedOn) {
		this.verifiedOn = verifiedOn;
	}

	public Character getApproved() {
		return approved;
	}

	public void setApproved(Character approved) {
		this.approved = approved;
	}

	public Date getApprovedOn() {
		return approvedOn;
	}

	public void setApprovedOn(Date approvedOn) {
		this.approvedOn = approvedOn;
	}

	public String getCellNumber() {
		return cellNumber;
	}

	public BigInteger getRelationshipId() {
		return relationshipId;
	}

	public void setRelationshipId(BigInteger relationshipId) {
		this.relationshipId = relationshipId;
	}

	public void setCellNumber(String cellNumber) {
		this.cellNumber = cellNumber;
	}

}
