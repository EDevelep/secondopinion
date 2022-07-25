package org.secondopinion.utilities.jobs.dao.impl;

import java.util.List;

import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import org.secondopinion.utilities.jobs.dto.JobParams;
@Repository
public class JobParamsDAOHibernateImpl extends BaseJobParamsDAOHibernate{

	@Override
	public List<JobParams> getJobParams(Integer jobId) {
		Criterion criterion = Restrictions.eq(JobParams.FIELD_jobId, jobId);
		
		return findByCrieria(criterion);
	}
}