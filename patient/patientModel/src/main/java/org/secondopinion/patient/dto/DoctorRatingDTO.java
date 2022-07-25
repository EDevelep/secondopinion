package org.secondopinion.patient.dto;

public class DoctorRatingDTO {
	private Long doctorid;
	private Long patientid;
	private Character active;
	private Long appointmentid;
	private Double rating;
	private String feedback;
	public Long getDoctorid() {
		return doctorid;
	}
	public void setDoctorid(Long doctorid) {
		this.doctorid = doctorid;
	}
	public Long getPatientid() {
		return patientid;
	}
	public void setPatientid(Long patientid) {
		this.patientid = patientid;
	}
	public Character getActive() {
		return active;
	}
	public void setActive(Character active) {
		this.active = active;
	}
	public Long getAppointmentid() {
		return appointmentid;
	}
	public void setAppointmentid(Long appointmentid) {
		this.appointmentid = appointmentid;
	}
	public Double getRating() {
		return rating;
	}
	public void setRating(Double rating) {
		this.rating = rating;
	}
	public String getFeedback() {
		return feedback;
	}
	public void setFeedback(String feedback) {
		this.feedback = feedback;
	}
}
