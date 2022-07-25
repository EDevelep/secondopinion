package org.secondopinion.diagnosticcenter.dto; 


import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.secondopinion.diagnosticcenter.domain.BaseAppointment; 



@Entity 
@Table (name="appointment")
public class Appointment extends BaseAppointment{
	
	private Patientpaymentdetails patientpaymentdetails;
	private Long referenceEntityId;
	private String referenceEntityName;
	private Long userId;
	@Transient
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	@Transient
	public Long getReferenceEntityId() {
		return referenceEntityId;
	}
	public void setReferenceEntityId(Long referenceEntityId) {
		this.referenceEntityId = referenceEntityId;
	}
	
	@Transient
	public String getReferenceEntityName() {
		return referenceEntityName;
	}
	public void setReferenceEntityName(String referenceEntityName) {
		this.referenceEntityName = referenceEntityName;
	}
	@Transient
	public Patientpaymentdetails getPatientpaymentdetails() {
		return patientpaymentdetails;
	}
	public void setPatientpaymentdetails(Patientpaymentdetails patientpaymentdetails) {
		this.patientpaymentdetails = patientpaymentdetails;
	}
	
	
}