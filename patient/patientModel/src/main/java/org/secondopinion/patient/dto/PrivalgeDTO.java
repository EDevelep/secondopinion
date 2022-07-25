package org.secondopinion.patient.dto;


public class PrivalgeDTO {
	private Long relationShipId;
	private Character approved;
	private Character accessToRecords;
	private Character accessToPaymentDetails;
	private Character accessToPrescription;
	private Character accessToInsurance;
	private Character accessToPersonalDetails;
	private Character accessToVitals;
	private Character accessToAppoitment;
	private Character accessToProfile;
	private Character accessToNotifaction;
	

	public Character getApproved() {
		return approved;
	}

	public void setApproved(Character approved) {
		this.approved = approved;
	}

	public Character getAccessToRecords() {
		return accessToRecords;
	}

	public void setAccessToRecords(Character accessToRecords) {
		this.accessToRecords = accessToRecords;
	}

	public Character getAccessToPaymentDetails() {
		return accessToPaymentDetails;
	}

	public void setAccessToPaymentDetails(Character accessToPaymentDetails) {
		this.accessToPaymentDetails = accessToPaymentDetails;
	}

	public Character getAccessToPrescription() {
		return accessToPrescription;
	}

	public void setAccessToPrescription(Character accessToPrescription) {
		this.accessToPrescription = accessToPrescription;
	}

	public Character getAccessToInsurance() {
		return accessToInsurance;
	}

	public void setAccessToInsurance(Character accessToInsurance) {
		this.accessToInsurance = accessToInsurance;
	}

	public Character getAccessToPersonalDetails() {
		return accessToPersonalDetails;
	}

	public void setAccessToPersonalDetails(Character accessToPersonalDetails) {
		this.accessToPersonalDetails = accessToPersonalDetails;
	}

	public Long getRelationShipId() {
		return relationShipId;
	}

	public void setRelationShipId(Long relationShipId) {
		this.relationShipId = relationShipId;
	}

	public Character getAccessToVitals() {
		return accessToVitals;
	}

	public void setAccessToVitals(Character accessToVitals) {
		this.accessToVitals = accessToVitals;
	}

	public Character getAccessToAppoitment() {
		return accessToAppoitment;
	}

	public void setAccessToAppoitment(Character accessToAppoitment) {
		this.accessToAppoitment = accessToAppoitment;
	}

	public Character getAccessToProfile() {
		return accessToProfile;
	}

	public void setAccessToProfile(Character accessToProfile) {
		this.accessToProfile = accessToProfile;
	}

	public Character getAccessToNotifaction() {
		return accessToNotifaction;
	}

	public void setAccessToNotifaction(Character accessToNotifaction) {
		this.accessToNotifaction = accessToNotifaction;
	}

	public static PrivalgeDTO buildPrivalge(Relationship relationship) {
		PrivalgeDTO privalgeDTO = new PrivalgeDTO();
		privalgeDTO.setAccessToInsurance(relationship.getAccessToInsurance());
		privalgeDTO.setAccessToPaymentDetails(relationship.getAccessToPaymentDetails());
		privalgeDTO.setAccessToPersonalDetails(relationship.getAccessToPersonalDetails());
		privalgeDTO.setAccessToPrescription(relationship.getAccessToPrescription());
		privalgeDTO.setAccessToRecords(relationship.getAccessToRecords());
		privalgeDTO.setAccessToVitals(relationship.getAccessToVitals());
		privalgeDTO.setAccessToAppoitment(relationship.getAccessToAppoitment());
		privalgeDTO.setAccessToNotifaction(relationship.getAccessToNotifaction());
		privalgeDTO.setAccessToProfile(relationship.getAccessToProfile());
		return privalgeDTO;
	}

	public static PrivalgeDTO buildPrivalgeUpdate(Relationship relationship, PrivalgeDTO privalgeDTO) {
	
		return null;
	}

}
