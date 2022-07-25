package org.secondopinion.patient.dto;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonFormat.Shape;

public class MedicalprescriptionSearchCriteria {

	private Long forUserId;
	private Boolean isOnlyActive;
	private String medicineName;
	@JsonFormat(pattern = "yyyy-MM-dd", shape = Shape.STRING)
	private Date fromDate;
	@JsonFormat(pattern = "yyyy-MM-dd", shape = Shape.STRING)
	private Date toDate;

	public String getMedicineName() {
		return medicineName;
	}

	public void setMedicineName(String medicineName) {
		this.medicineName = medicineName;
	}
	@JsonFormat(pattern = "yyyy-MM-dd", shape = Shape.STRING)
	public Date getFromDate() {
		return fromDate;
	}

	public void setFromDate(Date fromDate) {
		this.fromDate = fromDate;
	}
	@JsonFormat(pattern = "yyyy-MM-dd", shape = Shape.STRING)
	public Date getToDate() {
		return toDate;
	}

	public void setToDate(Date toDate) {
		this.toDate = toDate;
	}

	private Long medicalTestPrescriptionId;
	private Integer pageNum;
	private Integer maxResults;

	public Long getForUserId() {
		return forUserId;
	}

	public void setForUserId(Long forUserId) {
		this.forUserId = forUserId;
	}

	public Boolean getIsOnlyActive() {
		return isOnlyActive;
	}

	public void setIsOnlyActive(Boolean isOnlyActive) {
		this.isOnlyActive = isOnlyActive;
	}



	public Long getMedicalTestPrescriptionId() {
		return medicalTestPrescriptionId;
	}

	public void setMedicalTestPrescriptionId(Long medicalTestPrescriptionId) {
		this.medicalTestPrescriptionId = medicalTestPrescriptionId;
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
