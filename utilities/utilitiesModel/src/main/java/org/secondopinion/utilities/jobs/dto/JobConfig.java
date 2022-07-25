package org.secondopinion.utilities.jobs.dto;


public class JobConfig {
	private int jobId;
	private String jobName;
	private JobSetting jobSetting;
	
	public JobConfig(int jobId, String jobName, JobSetting jobSetting) {
		this.jobId = jobId;
		this.jobName = jobName;
		this.jobSetting = jobSetting;
	}

	public int getJobId() {
		return jobId;
	}

	public JobSetting getJobSetting() {
		return jobSetting;
	}

	public String getJobName() {
		return jobName;
	}
}

