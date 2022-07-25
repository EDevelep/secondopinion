package org.secondopinion.utilities.jobs.dto;

import java.util.Map;

public class JobSetting {
	private String jobName;
	private boolean sendStartEmail;
	private boolean sendEndEmail;
	private boolean sendFailureEmail;

	private Map<String, String> jobParams;
	
	public String getJobName() {
		return jobName;
	}

	public void setJobName(String jobName) {
		this.jobName = jobName;
	}

	public boolean isSendStartEmail() {
		return sendStartEmail;
	}

	public void setSendStartEmail(boolean sendStartEmail) {
		this.sendStartEmail = sendStartEmail;
	}

	public boolean isSendEndEmail() {
		return sendEndEmail;
	}

	public void setSendEndEmail(boolean sendEndEmail) {
		this.sendEndEmail = sendEndEmail;
	}

	public boolean isSendFailureEmail() {
		return sendFailureEmail;
	}

	public void setSendFailureEmail(boolean sendErrorEmail) {
		this.sendFailureEmail = sendErrorEmail;
	}

	public Map<String, String> getJobParams() {
		return jobParams;
	}

	public void setJobParams(Map<String, String> jobParams) {
		this.jobParams = jobParams;
	}
}