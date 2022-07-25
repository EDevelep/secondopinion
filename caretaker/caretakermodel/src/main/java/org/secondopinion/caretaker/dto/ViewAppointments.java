package org.secondopinion.caretaker.dto;

import java.util.List;



public class ViewAppointments {
	private Long caretakerId;
	private List<ViewAppointmentEnum> viewAppointmentEnum;
	private Long patientId;
	private Integer pageNum;
	
	private Integer maxResult;

	public Integer getMaxResult() {
		return maxResult;
	}

	public void setMaxResult(Integer maxResult) {
		this.maxResult = maxResult;
	}

	public Long getCaretakerId() {
		return caretakerId;
	}

	public void setCaretakerId(Long caretakerId) {
		this.caretakerId = caretakerId;
	}

	public Long getPatientId() {
		return patientId;
	}

	public void setPatientId(Long patientId) {
		this.patientId = patientId;
	}
	
	public Integer getPageNum() {
		return pageNum;
	}

	public void setPageNum(Integer pageNum) {
		this.pageNum = pageNum;
	}

	public List<ViewAppointmentEnum> getViewAppointmentEnum() {
		return viewAppointmentEnum;
	}

	public void setViewAppointmentEnum(List<ViewAppointmentEnum> viewAppointmentEnum) {
		this.viewAppointmentEnum = viewAppointmentEnum;
	}



	public enum ViewAppointmentEnum {
		 PREVIOUS, CURRENT, UPCOMING, CANCELLED, RESCHEDULED
	}
}
