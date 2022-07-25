package org.secondopinion.patient.dto;

import java.math.BigDecimal;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonFormat.Shape;

public class AppointmentSearchRequest {
	private String appointmentFor;
	private ForUser forUser;
	private String referenceEntityName;
	private Long referenceEntityId;
	private String appointmentStatus;
	@JsonFormat(pattern = "yyyy-MM-dd", shape = Shape.STRING)
	private Date appointmentDate;
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", shape = Shape.STRING)
	private Date fromDate;
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", shape = Shape.STRING)
	private Date toDate;
	private String ailment;
	private BigDecimal amountPaid;
	private Integer pageNum;
	private Integer maxResults;
	private String sortBy;
	private String sortOrder;
	
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

	public String getSortBy() {
		return sortBy;
	}

	public void setSortBy(String sortBy) {
		this.sortBy = sortBy;
	}

	public String getSortOrder() {
		return sortOrder;
	}

	public void setSortOrder(String sortOrder) {
		this.sortOrder = sortOrder;
	}

	public BigDecimal getAmountPaid() {
		return amountPaid;
	}

	public void setAmountPaid(BigDecimal amountPaid) {
		this.amountPaid = amountPaid;
	}

	public String getAilment() {
		return ailment;
	}

	public String getAppointmentStatus() {
		return appointmentStatus;
	}

	public void setAppointmentStatus(String appointmentStatus) {
		this.appointmentStatus = appointmentStatus;
	}

	public void setAilment(String ailment) {
		this.ailment = ailment;
	}

	public String getAppointmentFor() {
		return appointmentFor;
	}

	public void setAppointmentFor(String appointmentFor) {
		this.appointmentFor = appointmentFor;
	}

	public String getReferenceEntityName() {
		return referenceEntityName;
	}

	public void setReferenceEntityName(String referenceEntityName) {
		this.referenceEntityName = referenceEntityName;
	}

	
	
	@JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSXXX", shape = Shape.STRING)
	public Date getFromDate() {
		return fromDate;
	}

	public void setFromDate(Date fromDate) {
		this.fromDate = fromDate;
	}
	@JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSXXX", shape = Shape.STRING)
	public Date getToDate() {
		return toDate;
	}

	public void setToDate(Date toDate) {
		this.toDate = toDate;
	}

	public ForUser getForUser() {
		return forUser;
	}

	public void setForUser(ForUser forUser) {
		this.forUser = forUser;
	}
	public Long getReferenceEntityId() {
		return referenceEntityId;
	}
	public void setReferenceEntityId(Long referenceEntityId) {
		this.referenceEntityId = referenceEntityId;
	}

	

}
