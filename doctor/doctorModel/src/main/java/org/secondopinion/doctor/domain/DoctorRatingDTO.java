package org.secondopinion.doctor.domain;

import javax.persistence.Column;

public class DoctorRatingDTO {

	private Long doctorid;
	private Long patientid;
	private Long appointmentid;
	private Double rating;
	private String feedback;
	private int pagenumber;
	private int maxresult;
	private Character active;
	
	public void setActive( Character  _active){
		this.active = _active;
	}
	@Column (name="active")
	public Character getActive(){
		return this.active;
	}
	
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
	public int getPagenumber() {
		return pagenumber;
	}
	public void setPagenumber(int pagenumber) {
		this.pagenumber = pagenumber;
	}
	public int getMaxresult() {
		return maxresult;
	}
	public void setMaxresult(int maxresult) {
		this.maxresult = maxresult;
	}
}
