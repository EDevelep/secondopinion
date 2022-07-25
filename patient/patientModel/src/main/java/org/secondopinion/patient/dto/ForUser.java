package org.secondopinion.patient.dto;

import java.util.Date;

import org.secondopinion.utils.ObjectUtil;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonFormat.Shape;

public class ForUser {
	private Long userId;//doctorid or diagnostic centerid, or hospital id, or clinic id or pharmacyid, or patientid(when self)
	private Long forUserId;//patientid
	private RELATIONSHIP_TYPE relationType;
	private Date vitalsByDate;
	private Date date;    
	private String newregister;
	
	public ForUser() {
		
	}
	
	public ForUser(Long userId, Long forUserId, RELATIONSHIP_TYPE relationType, Date vitalsByDate, Date date) {
		super();
		this.userId = userId;
		this.forUserId = forUserId;
		this.relationType = relationType;
		this.vitalsByDate = vitalsByDate;
		this.date = date;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}
	public String getNewregister() {
		return newregister;
	}

	public void setNewregister(String newregister) {
		this.newregister = newregister;
	}

	public Long getForUserId() {
		return forUserId;
	}

	public void setForUserId(Long forUserId) {
		this.forUserId = forUserId;
	}

	public RELATIONSHIP_TYPE getRelationType() {
		return relationType;
	}

	public void setRelationType(RELATIONSHIP_TYPE relationType) {
		this.relationType = relationType;
	}

	public boolean isCallForSelf() {
		return ObjectUtil.isEqual(forUserId, userId);
	}

	@JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSXXX", shape = Shape.STRING)
	public Date getVitalsByDate() {
		return vitalsByDate;
	}

	public void setVitalsByDate(Date vitalsByDate) {
		this.vitalsByDate = vitalsByDate;
	}
	@JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSXXX", shape = Shape.STRING)
	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}
}
