package org.secondopinion.pharmacy.dto;

public class PharmacyratingsDTO {
	private Long pharmacyratingid;
	private Long pharmacyid;
	private Long patientid;
	private Double rating;
	private String feedback;
	private int Pagenumber;
	private int Maxresult;
	
	public Long getPharmacyratingid() {
		return pharmacyratingid;
	}
	public void setPharmacyratingid(Long pharmacyratingid) {
		this.pharmacyratingid = pharmacyratingid;
	}
	public Long getPharmacyid() {
		return pharmacyid;
	}
	public void setPharmacyid(Long pharmacyid) {
		this.pharmacyid = pharmacyid;
	}
	public Long getPatientid() {
		return patientid;
	}
	public void setPatientid(Long patientid) {
		this.patientid = patientid;
	}
	public Double getRating() {
		return rating;
	}
	public int getPagenumber() {
		return Pagenumber;
	}
	public void setPagenumber(int pagenumber) {
		Pagenumber = pagenumber;
	}
	public int getMaxresult() {
		return Maxresult;
	}
	public void setMaxresult(int maxresult) {
		Maxresult = maxresult;
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
