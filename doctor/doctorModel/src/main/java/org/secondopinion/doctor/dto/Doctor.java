package org.secondopinion.doctor.dto;

import java.util.List;

import javax.persistence.Entity;

import javax.persistence.Table;
import javax.persistence.Transient;

import org.apache.commons.lang3.StringUtils;
import org.secondopinion.doctor.domain.BaseDoctor;

@Entity
@Table(name = "doctor")
public class Doctor extends BaseDoctor {

	//type doctor nutricton
	private List<Feedetails> feedetails;
	private List<Association> associations;
	private List<DoctorAddress> doctorAddresses; 
	private List<Certification> certifications;
	private Personaldetail personaldetail;
	private String uiHostURL;
	

    @Transient
	public List<Certification> getCertifications() {
		return certifications;
	}

	public void setCertifications(List<Certification> certifications) {
		this.certifications = certifications;
	}

	@Transient
	public Personaldetail getPersonaldetail() {
		return personaldetail;
	}
	public void setPersonaldetail(Personaldetail personaldetail) {
		this.personaldetail = personaldetail;
	}

	@Transient
	public String getUiHostURL() {
		return uiHostURL;
	}
	public void setUiHostURL(String uiHostURL) {
		this.uiHostURL = uiHostURL;
	}

	@Transient
	public List<Feedetails> getFeedetails() {
		return feedetails;
	}
	public void setFeedetails(List<Feedetails> feedetails) {
		this.feedetails = feedetails;
	}
	
	@Transient
	public List<Association> getAssociations() {
		return associations;
	}
	public void setAssociations(List<Association> associations) {
		this.associations = associations;
	}

	@Transient
	public List<DoctorAddress> getDoctorAddresses() {
		return doctorAddresses;
	}
	public void setDoctorAddresses(List<DoctorAddress> doctorAddresses) {
		this.doctorAddresses = doctorAddresses;
	}
	
	public static Doctor buildForUpdate(Doctor dbDoctor, Doctor uiDoctor) {
		
		
		dbDoctor.setFirstName(uiDoctor.getFirstName());
		dbDoctor.setLastName(uiDoctor.getLastName());
		dbDoctor.setMiddleName(uiDoctor.getMiddleName());
		dbDoctor.setEmailId(StringUtils.isEmpty(uiDoctor.getEmailId()) ? dbDoctor.getEmailId() : uiDoctor.getEmailId());
		dbDoctor.setCellNumber(uiDoctor.getCellNumber());
		dbDoctor.setHomeNumber(uiDoctor.getHomeNumber());
		dbDoctor.setOfficeNumber(uiDoctor.getOfficeNumber());
		dbDoctor.setPrimaryContact(uiDoctor.getPrimaryContact());
		dbDoctor.setMedicalLicenceNumber(uiDoctor.getMedicalLicenceNumber());
		dbDoctor.setTotalExperience(uiDoctor.getTotalExperience());;
		dbDoctor.setDateOfExpiry(uiDoctor.getDateOfExpiry());
		dbDoctor.setSpecialization(uiDoctor.getSpecialization());
		dbDoctor.setGender(uiDoctor.getGender());
		
		dbDoctor.setDoctorAddresses(uiDoctor.getDoctorAddresses());
		dbDoctor.setAssociations(uiDoctor.getAssociations());
		dbDoctor.setFeedetails(uiDoctor.getFeedetails());
		dbDoctor.setCertifications(uiDoctor.getCertifications());
		dbDoctor.setPersonaldetail(uiDoctor.getPersonaldetail());
		return dbDoctor;
	}

	
}