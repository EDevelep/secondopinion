package org.secondopinion.patient.dto;

import java.util.Date;

public class PrescriptionSearch {
	private ForUser forUser;
	private Date apptmentDate;
	private Integer pageNum;
	private Integer maxResults;
	
	public ForUser getForUser() {
		return forUser;
	}
	public void setForUser(ForUser forUser) {
		this.forUser = forUser;
	}
	public Date getApptmentDate() {
		return apptmentDate;
	}
	public void setApptmentDate(Date apptmentDate) {
		this.apptmentDate = apptmentDate;
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
