package org.secondopinion.utilities.jobs.dto;

import java.util.List;

public class RunJobStatusDTO {
	protected Boolean isSuccess;
	protected List<String> successfullyStartedJobs;
	protected List<String> unsuccessfullyStartedJobs;
	
	public RunJobStatusDTO(Boolean isSuccess,
			List<String> successfullyStartedJobs,
			List<String> unsuccessfullyStartedJobs) {
		this.isSuccess = isSuccess;
		this.successfullyStartedJobs = successfullyStartedJobs;
		this.unsuccessfullyStartedJobs = unsuccessfullyStartedJobs;
	}
	
	public Boolean getIsSuccess() {
		return isSuccess;
	}
	public void setIsSuccess(Boolean isSuccess) {
		this.isSuccess = isSuccess;
	}
	public List<String> getSuccessfullyStartedJobs() {
		return successfullyStartedJobs;
	}
	public void setSuccessfullyStartedJobs(List<String> successfullyStartedJobs) {
		this.successfullyStartedJobs = successfullyStartedJobs;
	}
	public List<String> getUnsuccessfullyStartedJobs() {
		return unsuccessfullyStartedJobs;
	}
	public void setUnsuccessfullyStartedJobs(List<String> unsuccessfullyStartedJobs) {
		this.unsuccessfullyStartedJobs = unsuccessfullyStartedJobs;
	}

	@Override
	public String toString() {
		return "RunJobStatusDTO [isSuccess=" + isSuccess + ", successfullyStartedJobs=" + successfullyStartedJobs
				+ ", unsuccessfullyStartedJobs=" + unsuccessfullyStartedJobs + "]";
	}
}
