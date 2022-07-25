package org.secondopinion.patient.dto;

public class UploadMedicalReportRequest {

	private ForUser forUser;
	private Medicalreports medicalreports;

	public final ForUser getForUser() {
		return forUser;
	}

	public final void setForUser(ForUser forUser) {
		this.forUser = forUser;
	}

	public  final Medicalreports getMedicalreports() {
		return medicalreports;
	}

	public final void setMedicalreports(Medicalreports medicalreports) {
		this.medicalreports = medicalreports;
	}
}
