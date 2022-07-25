package org.secondopinion.patient.dto;

public class MedicalInsuranceRequest {

	private Medicalinsurance medicalinsurance;
	private ForUser forUser;
	
	
	public final Medicalinsurance getMedicalinsurance() {
		return medicalinsurance;
	}
	public final void setMedicalinsurance(Medicalinsurance medicalinsurance) {
		this.medicalinsurance = medicalinsurance;
	}
	public final ForUser getForUser() {
		return forUser;
	}
	public final void setForUser(ForUser forUser) {
		this.forUser = forUser;
	}
	
	
}
