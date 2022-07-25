package org.secondopinion.utilities.jobs.service;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import org.secondopinion.utilities.jobs.AbstractJob;
import org.secondopinion.utilities.jobs.IJob;
import org.secondopinion.utilities.jobs.dao.JobDefDAO;
import org.secondopinion.utilities.jobs.dao.JobInfoDAO;
import org.secondopinion.utilities.jobs.dto.JobDef;
import org.secondopinion.utilities.jobs.dto.JobInfo;
import org.secondopinion.utilities.jobs.dto.RunJobStatusDTO;


@Service
public class JobService {
	static Logger Log = LoggerFactory.getLogger(JobService.class);
	
	@Autowired
	private JobDefDAO jobDefDAO;
	@Autowired
	private JobInfoDAO jobInfoDAO;
	@Autowired
	private ApplicationContext applicationContext;
	
	
	public List<JobDef> getAllJobDefs(){
		return jobDefDAO.getAllJobDefs();
	}

	private JobInfo executeJob(String beanName) {
		Log.debug("Executing job " + beanName);
		IJob job = (IJob) applicationContext.getBean(beanName);
		((AbstractJob) job).setJobBean(beanName);
		return job.execute();
	}
	
	
	public RunJobStatusDTO executeJobRest(String jobBeanId){
		
		boolean isSuccess = true;
		List<String> successfullyStartedJobs = new ArrayList<String>();
		List<String> unsuccessfullyStartedJobs = new ArrayList<String>();
		

		try {
			System.out.println("Will execute job " + jobBeanId);
			JobInfo jobInfo = executeJob(jobBeanId);
			if(jobInfo.getStatus().equals("FAILURE")) {
				throw new IllegalArgumentException("Job execution for job " + jobBeanId + " failed");
			}
			successfullyStartedJobs.add(jobBeanId);
		}
		catch(Throwable t) {
			t.printStackTrace();
			isSuccess = false;
			unsuccessfullyStartedJobs.add(jobBeanId);
		}

		RunJobStatusDTO runJobStatusDTO = new RunJobStatusDTO(isSuccess,successfullyStartedJobs, unsuccessfullyStartedJobs );
		return runJobStatusDTO;
	}

	public List<JobInfo> getJobsExecutionInfo(String beanId) {
		return jobInfoDAO.getJobsExecutionInfo(beanId);
	}
	
	/**
	 * @return the applicationContextProvider
	 */
	public ApplicationContext getApplicationContext() {
		return applicationContext;
	}

	/**
	 * @param applicationContextProvider the applicationContextProvider to set
	 */
	public void setApplicationContext(ApplicationContext applicationContext) {
		this.applicationContext = applicationContext;
	}

	/**
	 * @return the jobInfoDAO
	 */
	public JobInfoDAO getJobInfoDAO() {
		return jobInfoDAO;
	}

	/**
	 * @param jobInfoDAO the jobInfoDAO to set
	 */
	public void setJobInfoDAO(JobInfoDAO jobInfoDAO) {
		this.jobInfoDAO = jobInfoDAO;
	}

	public JobDefDAO getJobDefDAO() {
		return jobDefDAO;
	}

	public void setJobDefDAO(JobDefDAO jobDefDAO) {
		this.jobDefDAO = jobDefDAO;
	}

	public JobDef getJobDef(String jobBean) {
		return jobDefDAO.getJobDefinition(jobBean);
	}

	@Transactional
	public void saveJobDef(JobDef jobDef) {
		jobDefDAO.save(jobDef);
	}
}