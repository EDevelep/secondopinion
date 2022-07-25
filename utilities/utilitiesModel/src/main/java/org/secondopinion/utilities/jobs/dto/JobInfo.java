package org.secondopinion.utilities.jobs.dto; 


import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.secondopinion.utilities.jobs.domain.BaseJobInfo;




@Entity 
@Table (name="jobinfo")
public class JobInfo extends BaseJobInfo{
	

	public enum JOB_STATUS{
		INITIALIZED, PRE_PROCESS, EXECUTION_PHASE, POST_PROCESS, SUCCESS, FAILURE
	}
	
	private Map<String, String> taskInfo = new HashMap<String, String>(); 

	private StringBuilder executionInfo = new StringBuilder();
	
	public JobInfo() {
	}
	
	public JobInfo(String jobName, String jobBeanId) {
		this.setJobName(jobName);
		this.setJobBeanId(jobBeanId);
		this.setExecutionDate(new Date());
		this.setStartTime(new Date());
	}

	public void addToNumberProcessed(int numProcessed){
		Long _numberProcessed = getNumberProcessed();
		if(_numberProcessed == null){
			_numberProcessed = 0L;
		}
		this.setNumberProcessed(_numberProcessed + numProcessed);
//		this.setNumberProcessed(this.getNumberProcessed() + numProcessed);
	}
	
	public void addToNumberSuccessfullyProcessed(int numProcessed){
		Long _numberProcessed = getNumberSuccessfullyProcessed();
		if(_numberProcessed == null){
			_numberProcessed = 0L;
		}
		this.setNumberSuccessfullyProcessed(_numberProcessed + numProcessed);
//		this.setNumberSuccessfullyProcessed(getNumberSuccessfullyProcessed() + numProcessed);
	}
	
	public void addToNumberFailedProcessing(int numFailed){
		Long _numFailed = getNumberFailed();
		if(_numFailed == null){
			_numFailed = 0L;
		}
		this.setNumberFailed(_numFailed + numFailed);
		this.setNumberFailed(this.getNumberFailed() + numFailed);
	}
	
	public void setPreProcessStart() {
		this.setPreProcessStartTime(new Date());
		this.setStatus(JOB_STATUS.PRE_PROCESS.name());
	}
	
	public void setPreProcessEnd() {
		this.setPreProcessEndTime(new Date());
	}
	
	public void setPostProcessStart() {
		this.setPostProcessStartTime(new Date());
		this.setStatus(JOB_STATUS.POST_PROCESS.name());
	}
	
	public void setPostProcessEnd() {
		this.setPostProcessEndTime(new Date());
	}

	public void addToTotalProcessed(int batchTotal){
		Long _numberProcessed = getNumberProcessed();
		if(_numberProcessed == null){
			_numberProcessed = 0L;
		}
		this.setNumberProcessed(_numberProcessed + batchTotal);
	}

	@Override
	@Transient
	public Map<String, Object> getParams() {
		Map<String, Object> params =  new HashMap<String, Object>();

		params.put("jobInfoId", getJobInfoId());
		params.put("jobId", getJobId());
		params.put("jobName", getJobName());
		params.put("jobBeanId", getJobBeanId());
		params.put("status", getStatus());
		params.put("executionDate", getExecutionDate());
		params.put("preProcessStartTime", getPreProcessStartTime());
		params.put("preProcessEndTime", getPreProcessEndTime());
		params.put("startTime", getStartTime());
		params.put("endTime", getEndTime());
		params.put("postProcessStartTime", getPostProcessStartTime());
		params.put("postProcessEndTime", getPostProcessEndTime());
		params.put("numberProcessed", getNumberProcessed());
		params.put("numberSuccessfullyProcessed", getNumberSuccessfullyProcessed());
		params.put("numberFailed", getNumberFailed());
		params.put("totalTimeTaken", getTotalTimeTaken());
		params.put("exception", getException());
		params.put("lastUpdatedBy", getLastUpdatedBy());
		params.put("lastUpdatedTs", getLastUpdatedTs());

		return params;
	}

}