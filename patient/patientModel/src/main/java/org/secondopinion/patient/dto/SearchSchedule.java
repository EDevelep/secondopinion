package org.secondopinion.patient.dto;

import java.time.LocalTime;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonFormat.Shape;

public class SearchSchedule {
	private Long appointmentId;
	private Long userId;
	private String appointmentFor;
	@JsonFormat(pattern = "yyyy-MM-dd", shape = Shape.STRING)
	private Date appointmentDate;
	@JsonFormat(pattern="HH:mm:ss", shape=Shape.STRING)
	private Date toTime;
	@JsonFormat(pattern="HH:mm:ss", shape=Shape.STRING)
	private Date fromTime;
	private Integer pageNum;
	private Integer maxResults;

	
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

	public Long getAppointmentId() {
		return appointmentId;
	}

	public void setAppointmentId(Long appointmentId) {
		this.appointmentId = appointmentId;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getAppointmentFor() {
		return appointmentFor;
	}

	public void setAppointmentFor(String appointmentFor) {
		this.appointmentFor = appointmentFor;
	}

	@JsonFormat(pattern = "yyyy-MM-dd", shape = Shape.STRING)
	public Date getAppointmentDate() {
		return appointmentDate;
	}

	public void setAppointmentDate(Date appointmentDate) {
		this.appointmentDate = appointmentDate;
	}
	@JsonFormat(pattern="HH:mm:ss", shape=Shape.STRING)
	public Date getToTime() {
		return toTime;
	}

	public void setToTime(Date toTime) {
		this.toTime = toTime;
	}
	@JsonFormat(pattern="HH:mm:ss", shape=Shape.STRING)
	public Date getFromTime() {
		return fromTime;
	}

	public void setFromTime(Date fromTime) {
		this.fromTime = fromTime;
	}
}
