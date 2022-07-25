package org.secondopinion.patient.dto;

import java.util.List;

public class VitalsRequest {
	private List<Vitals> vitals;
	private ForUser forUser;
	public List<Vitals> getVitals() {
		return vitals;
	}
	public void setVitals(List<Vitals> vitals) {
		this.vitals = vitals;
	}
	public ForUser getForUser() {
		return forUser;
	}
	public void setForUser(ForUser forUser) {
		this.forUser = forUser;
	}
	
	
	
	
	
}
