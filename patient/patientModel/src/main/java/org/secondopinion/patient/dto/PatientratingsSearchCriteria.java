package org.secondopinion.patient.dto;

import org.secondopinion.patient.dto.Patientratings.RatingForEnum;

public class PatientratingsSearchCriteria {
	private Long patientratingid;
	private Long patientid;
	private RatingForEnum ratingForEnum;
	private Long referenceId;
	private Double rating;
	private String feedback;
	private Integer pageNum;
	private Integer maxResults;
	
	public Long getPatientratingid() {
		return patientratingid;
	}
	public void setPatientratingid(Long patientratingid) {
		this.patientratingid = patientratingid;
	}
	public Long getPatientid() {
		return patientid;
	}
	public void setPatientid(Long patientid) {
		this.patientid = patientid;
	}
	
	
	public RatingForEnum getRatingForEnum() {
		return ratingForEnum;
	}
	public void setRatingForEnum(RatingForEnum ratingForEnum) {
		this.ratingForEnum = ratingForEnum;
	}
	
	public Long getReferenceId() {
		return referenceId;
	}
	public void setReferenceId(Long referenceId) {
		this.referenceId = referenceId;
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
	public Integer getPageNum() {
		return pageNum;
	}
	public void setPageNum(Integer pageNum) {
		this.pageNum = pageNum;
	}
	public Integer getMaxResults() {
		return maxResults;
	}
	public void setMaxResults(Integer maxResults) {
		this.maxResults = maxResults;
	}

	
}
