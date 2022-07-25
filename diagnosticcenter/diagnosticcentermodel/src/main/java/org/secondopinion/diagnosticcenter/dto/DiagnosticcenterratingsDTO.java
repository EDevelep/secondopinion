package org.secondopinion.diagnosticcenter.dto;

public class DiagnosticcenterratingsDTO {
	private Long diagnosticcenterratingid;
	private Long diagnosticcenterid;
	private Long patientid;
	private Double rating;
	private String feedback;
	private int Pagenumber;
	private int Maxresult;
	

	public Long getDiagnosticcenterratingid() {
		return diagnosticcenterratingid;
	}
	public void setDiagnosticcenterratingid(Long diagnosticcenterratingid) {
		this.diagnosticcenterratingid = diagnosticcenterratingid;
	}
	public Long getDiagnosticcenterid() {
		return diagnosticcenterid;
	}
	public void setDiagnosticcenterid(Long diagnosticcenterid) {
		this.diagnosticcenterid = diagnosticcenterid;
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
