package org.secondopinion.utilities.jobs.dao; 

import java.util.Date;
import java.util.List;

import org.secondopinion.dao.IDAO;
import org.secondopinion.utilities.jobs.dto.JobInfo;

public interface JobInfoDAO extends IDAO<JobInfo,Long >{
	
	List<JobInfo> getJobsExecutionInfo(Date onDate);
	
	List<JobInfo> getJobsExecutionInfo(Date fromDate, Date toDate);
	

	List<JobInfo> getJobsExecutionInfo(String beanId);
	
	JobInfo getJobExecutionInfo(long jobInfoId);
	
	JobInfo getSuccessfulJobExecutionInfo(Integer jobInfoId, Date onDate);

}