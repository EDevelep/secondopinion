package org.secondopinion.utilities.jobs.dto;


public class FeedbackRequestDTO {

	private Long userId;
	private Long clientId;
	private String status;
	private boolean isAllToView;
	private String patientName;
	/**
	 * @return the userId
	 */
	public Long getUserId() {
		return userId;
	}
	/**
	 * @param userId the userId to set
	 */
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	/**
	 * @return the clientId
	 */
	public Long getClientId() {
		return clientId;
	}
	/**
	 * @param clientId the clientId to set
	 */
	public void setClientId(Long clientId) {
		this.clientId = clientId;
	}
	/**
	 * @return the status
	 */
	public String getStatus() {
		return status;
	}
	/**
	 * @param status the status to set
	 */
	public void setStatus(String status) {
		this.status = status;
	}
	
	public boolean isAllToView() {
		return isAllToView;
	}
	public void setAllToView(boolean isAllToView) {
		this.isAllToView = isAllToView;
	}
	public String getPatientName() {
		return patientName;
	}
	public void setPatientName(String patientName) {
		this.patientName = patientName;
	}
	@Override
	public String toString() {
		return "FeedbackRequestDTO [userId=" + userId + ", clientId=" + clientId + ", status=" + status
				+ ", isAllToView=" + isAllToView + "]";
	}
	
	
}
