package org.secondopinion.patient.dto;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonFormat.Shape;

public class MedcalReportSearchRequest {

	private Long medicalTestId;
	private ForUser forUser;
	private String reportName;
	private String alignment;
	private Date reportTakenOn;
	private Date appointmentDate;
	private Date maxappointmentdate;
	private Integer pageNum;
	private Integer maxResults;

	@JsonFormat(pattern = "yyyy-MM-dd", shape = Shape.STRING)
	public Date getMaxappointmentdate() {
		return maxappointmentdate;
	}

	public void setMaxappointmentdate(Date maxappointmentdate) {
		this.maxappointmentdate = maxappointmentdate;
	}

	public Long getMedicalTestId() {
		return medicalTestId;
	}

	public void setMedicalTestId(Long medicalTestId) {
		this.medicalTestId = medicalTestId;
	}

	public String getReportName() {
		return reportName;
	}

	public void setReportName(String reportName) {
		this.reportName = reportName;
	}

	public String getAlignment() {
		return alignment;
	}

	public void setAlignment(String alignment) {
		this.alignment = alignment;
	}
	@JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSXXX", shape = Shape.STRING)
	public Date getReportTakenOn() {
		return reportTakenOn;
	}

	public void setReportTakenOn(Date reportTakenOn) {
		this.reportTakenOn = reportTakenOn;
	}

	public ForUser getForUser() {
		return forUser;
	}

	public void setForUser(ForUser forUser) {
		this.forUser = forUser;
	}
	@JsonFormat(pattern = "yyyy-MM-dd", shape = Shape.STRING)
	public Date getAppointmentDate() {
		return appointmentDate;
	}

	public void setAppointmentDate(Date appointmentDate) {
		this.appointmentDate = appointmentDate;
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
