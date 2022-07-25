package org.secondopinion.utilities.jobs.dao.impl;

import java.util.List;

import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import org.secondopinion.utilities.jobs.dto.JobDependency;
@Repository
public class JobDependencyDAOHibernateImpl extends BaseJobDependencyDAOHibernate{
	
	@Transactional
	@Override
	public List<JobDependency> getJobDependencies(Integer jobId){
		Criterion criterion = Restrictions.eq(JobDependency.FIELD_jobId, jobId);
		
		return findByCrieria(criterion);
	}
}