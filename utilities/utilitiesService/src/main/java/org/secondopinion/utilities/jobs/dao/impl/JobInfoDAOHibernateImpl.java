package org.secondopinion.utilities.jobs.dao.impl; 


import java.util.Date;
import java.util.List;

import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import org.secondopinion.utilities.jobs.dto.JobInfo;

@Repository
public class JobInfoDAOHibernateImpl extends BaseJobInfoDAOHibernate{

	@Override
	@Transactional
	public List<JobInfo> getJobsExecutionInfo(Date onDate) {
		Criterion criterion = Restrictions.eq(JobInfo.FIELD_executionDate, onDate);
		return findByCrieria(criterion);
	}

	@Override
	@Transactional
	public List<JobInfo> getJobsExecutionInfo(Date fromDate, Date toDate) {
		Criterion criterion = Restrictions.between(JobInfo.FIELD_executionDate, fromDate, toDate);
		return findByCrieria(criterion);
	}


	@Override
	@Transactional
	public List<JobInfo> getJobsExecutionInfo(String beanId) {
		Criterion criterion = Restrictions.eq(JobInfo.FIELD_jobBeanId, beanId);
		return findByCrieria(criterion);
	}

	@Override
	@Transactional
	public JobInfo getJobExecutionInfo(long jobInfoId) {
		return findById(jobInfoId);
	}

	@Override
	@Transactional
	public JobInfo getSuccessfulJobExecutionInfo(Integer jobId, Date onDate) {
		Criterion criterion = Restrictions.eq(JobInfo.FIELD_jobId, jobId);
		Criterion andCriterion = Restrictions.and(criterion, Restrictions.eq(JobInfo.FIELD_executionDate, onDate));
		List<JobInfo> jobInfos = findByCrieria(andCriterion);
		
		if(jobInfos != null && jobInfos.size() > 0){
			return jobInfos.get(0);
		}
		
		return null;
	}

}