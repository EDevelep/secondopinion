package org.secondopinion.patient.dto;

public class PatientRatingsDTO {

	private Long doctorid;
	private Long patientid;
	private Long appointmentid;
	private Long pharmacyaddressId;
	private Long diagnosticcenterId;
	private Double rating;
	private String feedback;
	private String ratingType;
	private String patientname;
	
	public Long getDiagnosticcenterId() {
		return diagnosticcenterId;
	}
	public void setDiagnosticcenterId(Long diagnosticcenterId) {
		this.diagnosticcenterId = diagnosticcenterId;
	}
	public Long getPharmacyaddressId() {
		return pharmacyaddressId;
	}
	public void setPharmacyaddressId(Long pharmacyaddressId) {
		this.pharmacyaddressId = pharmacyaddressId;
	}
	public Long getPatientid() {
		return patientid;
	}
	public void setPatientid(Long patientid) {
		this.patientid = patientid;
	}
	public Long getDoctorid() {
		return doctorid;
	}
	public void setDoctorid(Long doctorid) {
		this.doctorid = doctorid;
	}
	
	public Double getRating() {
		return rating;
	}
	public void setRating(Double rating) {
		this.rating = rating;
	}
	public Long getAppointmentid() {
		return appointmentid;
	}
	public String getRatingType() {
		return ratingType;
	}
	public void setRatingType(String ratingType) {
		this.ratingType = ratingType;
	}
	public String getPatientname() {
		return patientname;
	}
	public void setPatientname(String patientname) {
		this.patientname = patientname;
	}
	public void setAppointmentid(Long appointmentid) {
		this.appointmentid = appointmentid;
	}

	public String getFeedback() {
		return feedback;
	}
	public void setFeedback(String feedback) {
		this.feedback = feedback;
	}
	
}
