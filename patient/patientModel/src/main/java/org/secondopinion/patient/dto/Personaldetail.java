package org.secondopinion.patient.dto; 


import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.secondopinion.patient.domain.BasePersonaldetail; 

@Entity 
@Table (name="personaldetail")
public class Personaldetail extends BasePersonaldetail{
	
	private String emailId;
	private String cellNumber;
	private String patientName;
	private String prefferedDoctor;
	
	@Transient
	public String getEmailId() {
		return emailId;
	}
	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}
	@Transient
	public String getCellNumber() {
		return cellNumber;
	}
	public void setCellNumber(String cellNumber) {
		this.cellNumber = cellNumber;
	}
	
	@Transient
	public String getPatientName() {
		return patientName;
	}
	public void setPatientName(String patientName) {
		this.patientName = patientName;
	}
	@Transient
	public String getPrefferedDoctor() {
		return prefferedDoctor;
	}
	public void setPrefferedDoctor(String prefferedDoctor) {
		this.prefferedDoctor = prefferedDoctor;
	}
	
	
}