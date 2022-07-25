package org.secondopinion.patient.dto;

import java.util.List;

public class ViewAppointments {

	private Long referenceEntityId;
	private List<ViewAppointmentEnum> viewAppointmentEnum;
	private Long patientId;
	private Integer pageNum;
	private Integer maxResults;


	public Integer getMaxResults() {
		return maxResults;
	}

	public void setMaxResults(Integer maxResults) {
		this.maxResults = maxResults;
	}

	public Integer getPageNum() {
		return pageNum;
	}

	public void setPageNum(Integer pageNum) {
		this.pageNum = pageNum;
	}

	public Long getReferenceEntityId() {
		return referenceEntityId;
	}

	public void setReferenceEntityId(Long referenceEntityId) {
		this.referenceEntityId = referenceEntityId;
	}

	public Long getPatientId() {
		return patientId;
	}

	public void setPatientId(Long patientId) {
		this.patientId = patientId;
	}
	
	public List<ViewAppointmentEnum> getViewAppointmentEnum() {
		return viewAppointmentEnum;
	}

	public void setViewAppointmentEnum(List<ViewAppointmentEnum> viewAppointmentEnum) {
		this.viewAppointmentEnum = viewAppointmentEnum;
	}



	public enum ViewAppointmentEnum {
		 PREVIOUS, CURRENT, UPCOMING, CANCELLED, RESCHEDULED,NO_SHOW
	}
}
