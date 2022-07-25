package org.secondopinion.patient.dto;

import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.apache.commons.lang3.StringUtils;
import org.secondopinion.patient.domain.BaseUser;
import org.secondopinion.utils.DateUtil;

@Entity
@Table(name = "user")
public class User extends BaseUser {

	private Personaldetail personaldetail;
	private List<Relationship> relationship;
	private List<Address> address;
	private String emailVerificationLink;
	private List<Vitals> vitals;
	private List<Medicalreports> medicalreports;
	private List<Medicalprescription> medicalprescription;
	private List<Appointment> appointment;
	private List<Ailments> ailments;
	private String uiHostURL;
	private Date dob;
	private String relationshipName;
	
	@Transient
	public String getRelationshipName() {
		return relationshipName;
	}

	public void setRelationshipName(String relationshipName) {
		this.relationshipName = relationshipName;
	}

	private char relationShipExit;
	@Transient
	public char getRelationShipExit() {
		return relationShipExit;
	}

	public void setRelationShipExit(char relationShipExit) {
		this.relationShipExit = relationShipExit;
	}

	@Transient
	public List<Vitals> getVitals() {
		return vitals;
	}

	public void setVitals(List<Vitals> vitals) {
		this.vitals = vitals;
	}

	@Transient
	public List<Appointment> getAppointment() {
		return appointment;
	}

	public void setAppointment(List<Appointment> appointment) {
		this.appointment = appointment;
	}

	@Transient
	public List<Medicalreports> getMedicalreports() {
		return medicalreports;
	}

	public void setMedicalreports(List<Medicalreports> medicalreports) {
		this.medicalreports = medicalreports;
	}

	@Transient
	public List<Medicalprescription> getMedicalprescription() {
		return medicalprescription;
	}

	public void setMedicalprescription(List<Medicalprescription> medicalprescription) {
		this.medicalprescription = medicalprescription;
	}

	@Transient
	public List<Ailments> getAilments() {
		return ailments;
	}

	public void setAilments(List<Ailments> ailments) {
		this.ailments = ailments;
	}

	@Transient
	public String getEmailVerificationLink() {
		return emailVerificationLink;
	}

	public void setEmailVerificationLink(String emailVerificationLink) {
		this.emailVerificationLink = emailVerificationLink;
	}

	@Transient
	public Personaldetail getPersonaldetail() {
		return personaldetail;
	}

	public void setPersonaldetail(Personaldetail personaldetail) {
		this.personaldetail = personaldetail;
	}

	@Transient
	public List<Address> getAddress() {
		return address;
	}

	public void setAddress(List<Address> address) {
		this.address = address;
	}

	@Transient
	public String getUiHostURL() {
		return uiHostURL;
	}
	public void setUiHostURL(String uiHostURL) {
		this.uiHostURL = uiHostURL;
	}
	
	@Transient
	public List<Relationship> getRelationship() {
		return relationship;
	}
	@Transient
	public Date getDob() {
		return dob;
	}
	@Transient
	public void setDob(Date dob) {
		this.dob = dob;
	}

	public void setRelationship(List<Relationship> relationship) {
		this.relationship = relationship;
	}

	public static User buildForUpdate(User dbUser, User uiUser) {

		dbUser.setFirstName(StringUtils.isEmpty(uiUser.getFirstName()) ? dbUser.getFirstName() :uiUser.getFirstName());
		dbUser.setLastName(StringUtils.isEmpty(uiUser.getLastName()) ? dbUser.getLastName() :uiUser.getLastName());
		dbUser.setMiddleName(StringUtils.isEmpty(uiUser.getMiddleName()) ? dbUser.getMiddleName() :uiUser.getMiddleName());
		dbUser.setEmailId(StringUtils.isEmpty(uiUser.getEmailId()) ? dbUser.getEmailId() : uiUser.getEmailId());
		dbUser.setFirstName(StringUtils.isEmpty(uiUser.getFirstName()) ? dbUser.getFirstName() : uiUser.getFirstName());
		dbUser.setAddress(uiUser.getAddress());
		dbUser.setPersonaldetail(uiUser.getPersonaldetail());
		dbUser.setEmergencycontact(StringUtils.isEmpty(uiUser.getEmergencycontact()) ? dbUser.getEmergencycontact() : uiUser.getEmergencycontact());
		dbUser.setCellNumber(StringUtils.isEmpty(uiUser.getCellNumber()) ? dbUser.getCellNumber() : uiUser.getCellNumber());
		dbUser.setHomeNumber(StringUtils.isEmpty(uiUser.getHomeNumber()) ? dbUser.getHomeNumber() : uiUser.getHomeNumber());
		dbUser.setOfficeNumber(StringUtils.isEmpty(uiUser.getOfficeNumber()) ? dbUser.getOfficeNumber() : uiUser.getOfficeNumber());
		dbUser.setPrimaryContact(uiUser.getPrimaryContact());
		dbUser.setPreferredlanguages(StringUtils.isEmpty(uiUser.getPreferredlanguages()) ? dbUser.getPreferredlanguages() : uiUser.getPreferredlanguages());
		dbUser.setNativelanguage(StringUtils.isEmpty(uiUser.getNativelanguage()) ? dbUser.getNativelanguage() : uiUser.getNativelanguage());
		return dbUser;
	}

	public void markAsActive() {
		setActive('Y');
		setActivatedDate(DateUtil.getDate());
	}

	@Transient
	public String getFullName() {
		return getFirstName() + " " + getLastName();
	}

}