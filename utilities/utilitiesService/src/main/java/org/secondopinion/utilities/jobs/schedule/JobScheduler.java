package org.secondopinion.utilities.jobs.schedule;

import java.text.ParseException;
import java.util.Collection;
import java.util.UUID;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.Trigger;
import org.quartz.impl.triggers.CronTriggerImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.scheduling.quartz.MethodInvokingJobDetailFactoryBean;
import org.springframework.stereotype.Component;

import org.secondopinion.utilities.jobs.AbstractJob;
import org.secondopinion.utilities.jobs.IJob;
import org.secondopinion.utilities.jobs.dao.JobDefDAO;
import org.secondopinion.utilities.jobs.dto.JobDef;
import org.secondopinion.utils.StringUtil;

@Component
public class JobScheduler {
	private static Logger Log = LoggerFactory.getLogger(JobScheduler.class);
	@Autowired
	private Scheduler scheduler;
	@Autowired
	private JobDefDAO jobDefDAO;
	@Autowired
	private ApplicationContext applicationContext;
	
	public void scheduleJobs(){
		scheduleJobs(jobDefDAO.getAllJobDefs()); 
	}
	
	public void scheduleJobs(Collection<JobDef> jobDefs) {
		for(JobDef def : jobDefs){
			try {
				
				if(StringUtil.isNullOrEmpty(def.getSchedule())){
					Log.info("Unable to scheduling org.secondopinion.utilities.job: " + def.getJobBeanId());
					continue;
				}
				
				Log.debug("Scheduling Job: " + def.getJobBeanId());
				JobDetail jobDetail = buildJobDetail(def);
				
				// create CRON Trigger
				CronTriggerImpl cronTrigger = new CronTriggerImpl();
				
				cronTrigger.setName("CRON000" + def.getJobId());
				cronTrigger.setCronExpression(def.getSchedule());

				scheduler.scheduleJob(jobDetail, (Trigger)cronTrigger);
			} catch (SchedulerException | ParseException e) {
				e.printStackTrace();
			}
		}
		
		try {
			scheduler.start();
		} catch (SchedulerException e) {
			// TODO Auto-generated catch block
			Log.error("Error Scheduling jobs: " + e.getMessage() + "\n" + ExceptionUtils.getStackTrace(e));
		}
	}

	private JobDetail buildJobDetail(JobDef def) {
		try {
//			JobBuilder.newJob(JobDef.class)
//            .withIdentity(UUID.randomUUID().toString(), "email-jobs")
//            .withDescription("Send Email Job")
//            .usingJobData(jobDataMap)
//            .storeDurably()
//            .build();
			MethodInvokingJobDetailFactoryBean jobDetail = new MethodInvokingJobDetailFactoryBean();
//			AbstractJob job = SpringFactoryHelper.getBean(def.getJobBeanId());
			
			String jobName = def.getJobName();
			IJob job = (IJob) applicationContext.getBean(jobName);
			((AbstractJob) job).setJobBean(jobName);
			
			jobDetail.setTargetObject(job);
			jobDetail.setTargetMethod("execute");
			jobDetail.setName(def.getJobName());
			jobDetail.setConcurrent(false);
			jobDetail.afterPropertiesSet();
			return jobDetail.getObject();
		} catch (ClassNotFoundException | NoSuchMethodException e) {
			throw new IllegalArgumentException("Error building jobDetail: " + e.getMessage(), e);
		}
	}

	public Scheduler getScheduler() {
		return scheduler;
	}

	public void setScheduler(Scheduler scheduler) {
		this.scheduler = scheduler;
	}

	public JobDefDAO getJobDefDAO() {
		return jobDefDAO;
	}

	public void setJobDefDAO(JobDefDAO jobDefDAO) {
		this.jobDefDAO = jobDefDAO;
	}
	

}
