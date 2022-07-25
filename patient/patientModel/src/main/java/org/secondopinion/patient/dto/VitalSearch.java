package org.secondopinion.patient.dto;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonFormat.Shape;

public class VitalSearch {
	private Long vitalId;
	private String vitalValue;
	private Date recordedDate;
	private ForUser forUser;
	
	public ForUser getForUser() {
		return forUser;
	}

	public void setForUser(ForUser forUser) {
		this.forUser = forUser;
	}

	public Long getVitalId() {
		return vitalId;
	}

	public void setVitalId(Long vitalId) {
		this.vitalId = vitalId;
	}

	public String getVitalValue() {
		return vitalValue;
	}

	public void setVitalValue(String vitalValue) {
		this.vitalValue = vitalValue;
	}
	@JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSXXX", shape = Shape.STRING)
	public Date getRecordedDate() {
		return recordedDate;
	}

	public void setRecordedDate(Date recordedDate) {
		this.recordedDate = recordedDate;
	}

}
