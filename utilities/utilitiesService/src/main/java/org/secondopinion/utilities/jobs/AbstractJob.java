package org.secondopinion.utilities.jobs;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import org.secondopinion.utilities.jobs.dao.JobDefDAO;
import org.secondopinion.utilities.jobs.dao.JobInfoDAO;
import org.secondopinion.utilities.jobs.dto.FlowOutput;
import org.secondopinion.utilities.jobs.dto.JobDef;
import org.secondopinion.utilities.jobs.dto.JobDependency;
import org.secondopinion.utilities.jobs.dto.JobInfo;
import org.secondopinion.utilities.jobs.dto.JobSetting;
import org.secondopinion.utilities.jobs.dto.JobInfo.JOB_STATUS;
import org.secondopinion.utilities.jobs.task.VCJobOutputProcessTask;
import org.secondopinion.utils.DateUtil;
import org.secondopinion.utils.PerfHelper;
import org.secondopinion.utils.StringUtil;

@Service
public abstract class AbstractJob implements IJob {
	private static final Logger Log = LoggerFactory.getLogger(AbstractJob.class);

	
	@Autowired
	private JobInfoDAO jobInfoDAO;
	@Autowired
	private JobDefDAO jobDefDAO;
	
	private JobSetting jobSetting;
	private PerfHelper ph;
	private String jobBean;
	private VCJobOutputProcessTask jobOutputProcessTask;

	private JobInfo initialize() {
		ph = new PerfHelper();
		
		Log.debug("JobBean: " + jobBean);
		// String jobBean = System.getProperty("JOB_BEAN");
		JobDef def = jobDefDAO.getJobDefinition(jobBean);

		checkForDependentJobs(def);
		
		JobInfo info = new JobInfo(def.getJobName(), jobBean);
		info.setJobId(def.getJobId());

		jobSetting = new JobSetting();
		jobSetting.setJobParams(def.getJobParamsAsMap());
		
		Log.debug("Saving org.secondopinion.job Info - Initialization complete...");
		jobInfoDAO.save(info);

		return info;
	}

	private void checkForDependentJobs(JobDef def) {
		Log.debug("Checking for org.secondopinion.job dependencies");
		
		List<JobDependency> dependencies = def.getDependencies();
		
		if(dependencies == null ||  dependencies.size() == 0){
			return;
		}
		
		Log.debug("Job has dependencies:" + def.getDependencies());
		//List<Integer> dependentJobIds = new ArrayList<Integer>();
		long totalTimeWaited = 0;
		//long timeCheckStarted = System.currentTimeMillis();
		boolean allJobsPassed = false;
		
		while(!allJobsPassed || (totalTimeWaited == 3600000)){
			allJobsPassed = hasAllDependentJobsCompleted(dependencies);
			
			try{
				if(!allJobsPassed){
					Log.debug("Not all dependent org.secondopinion.job complete - waiting for 60Seconds..");
					Thread.sleep(60000);
				}
			}catch(Exception ex){
				ex.printStackTrace();
			}
			totalTimeWaited = totalTimeWaited + 600000;
		}
		
		if(!allJobsPassed){
			throw new IllegalArgumentException("Job: " + def.toString() + " Could not be executed as on or more of its dependencies did not finish successfully");
		}
	}

	private boolean hasAllDependentJobsCompleted(List<JobDependency> dependencies) {
		Log.debug("Check if the dependent org.secondopinion.job executions are complete - ");
		boolean jobsCompleted = true;
		Date date = DateUtil.getDate();
		for(JobDependency dependency : dependencies){
			JobInfo jobInfo =  jobInfoDAO.getSuccessfulJobExecutionInfo(dependency.getDependentJobId(), date);
			
			if(jobInfo == null){
				jobsCompleted = false;
				break;
			}
		}
		
		Log.debug("Dependent org.secondopinion.job execution completed - " + jobsCompleted);
		
		return jobsCompleted;
	}

	public abstract List<FlowOutput> executeJob(JobInfo info, Map<String, String> jobParams);

	public final JobInfo execute() {
		JobInfo info = initialize();

		info.setPreProcessStart();
		
		ExecutorService service = Executors.newFixedThreadPool(1);
		try{
			Future<JobInfo> future  = service.submit(new JobExecuteTask(info));
	
			try {
				future.get();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}finally{
			service.shutdown();
		}

		return info;
	}
	
	protected class JobExecuteTask implements Callable<JobInfo>{
		JobInfo info;
		public JobExecuteTask(JobInfo info) {
			this.info = info;
		}

		@Override
		public JobInfo call() throws Exception {
			// TODO Auto-generated method stub
		
			try {
				preProcess(info);
				info.setPreProcessEnd();

				jobInfoDAO.save(info);

				info.setStatus(JOB_STATUS.EXECUTION_PHASE.name());
				List<FlowOutput> output = executeJob(info, jobSetting.getJobParams());

				if(jobOutputProcessTask != null){
					jobOutputProcessTask.process(output);
				}
				jobInfoDAO.save(info);

				info.setPostProcessStart();
				postProcess(info);
				info.setPostProcessEnd();

				info.setEndTime(new Date());
				info.setStatus(JOB_STATUS.SUCCESS.name());
				info.setAudit("JOB_EXECUTION");
			} catch (Throwable t) {
				Log.error(ExceptionUtils.getStackTrace(t));
				t.printStackTrace();
				info.setStatus(JOB_STATUS.FAILURE.name());
				info.setException(StringUtil.crop(ExceptionUtils.getStackTrace(t), 1999));
			} finally {
				info.setTotalTimeTaken((new Long(ph.timeTaken())).intValue());
				jobInfoDAO.save(info);

//				smtpUtil.sendEmail("Job: " + info.getJobName() + " Status: " + info.getStatus(), info.toString());
			}
			
			return info;
		}

		
	}
	
	

	public void preProcess(JobInfo info) {

	}

	public void postProcess(JobInfo info) {

	}

	
	public String getJobBean() {
		return jobBean;
	}

	public void setJobBean(String jobBean) {
		this.jobBean = jobBean;
	}

	public JobInfoDAO getJobInfoDAO() {
		return jobInfoDAO;
	}

	public void setJobInfoDAO(JobInfoDAO jobInfoDAO) {
		this.jobInfoDAO = jobInfoDAO;
	}

	public JobDefDAO getJobDefDAO() {
		return jobDefDAO;
	}

	public void setJobDefDAO(JobDefDAO jobDefDAO) {
		this.jobDefDAO = jobDefDAO;
	}

	/**
	 * @return the jobOutputProcessTask
	 */
	public VCJobOutputProcessTask getJobOutputProcessTask() {
		return jobOutputProcessTask;
	}

	/**
	 * @param jobOutputProcessTask the jobOutputProcessTask to set
	 */
	public void setJobOutputProcessTask(VCJobOutputProcessTask jobOutputProcessTask) {
		this.jobOutputProcessTask = jobOutputProcessTask;
	}

}
