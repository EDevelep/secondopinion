package org.secondopinion.utilities.jobs.dao.impl;

import java.util.List;

import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import org.secondopinion.dao.exception.DataAccessException;
import org.secondopinion.utilities.jobs.dao.JobDependencyDAO;
import org.secondopinion.utilities.jobs.dao.JobParamsDAO;
import org.secondopinion.utilities.jobs.dto.JobDef;

@Repository
public class JobDefDAOHibernateImpl extends BaseJobDefDAOHibernate{
	@Autowired
	private JobDependencyDAO jobDependencyDAO;
	@Autowired
	private JobParamsDAO jobParamsDAO;

	@Override
	@Transactional(readOnly=true)
	public JobDef getJobDefinition(String beanid) {
		Criterion criterion = Restrictions.eq(JobDef.FIELD_jobBeanId, beanid);
		List<JobDef> jobDefs = findByCrieria(criterion);
		
		if(jobDefs != null && jobDefs.size()>0){
			JobDef jobDef = jobDefs.get(0);
			jobDef.setDependencies(jobDependencyDAO.getJobDependencies(jobDef.getJobId()));
			jobDef.setJobParams(jobParamsDAO.getJobParams(jobDef.getJobId()));
			
			return jobDef;
		}
		
		return null;
	}

	@Override
	@Transactional(readOnly=true)
	public List<JobDef> getAllJobDefs() {
		return findAll();
	}

	/**
	 * @return the jobDependencyDAO
	 */
	public JobDependencyDAO getJobDependencyDAO() {
		return jobDependencyDAO;
	}

	/**
	 * @param jobDependencyDAO the jobDependencyDAO to set
	 */
	public void setJobDependencyDAO(JobDependencyDAO jobDependencyDAO) {
		this.jobDependencyDAO = jobDependencyDAO;
	}

	/**
	 * @return the jobParamsDAO
	 */
	public JobParamsDAO getJobParamsDAO() {
		return jobParamsDAO;
	}

	/**
	 * @param jobParamsDAO the jobParamsDAO to set
	 */
	public void setJobParamsDAO(JobParamsDAO jobParamsDAO) {
		this.jobParamsDAO = jobParamsDAO;
	}
	
	@Override
	@Transactional
	public void save(JobDef jobDef) throws DataAccessException {
		save(jobDef);
		
		if(jobDef.getJobParams() !=null || jobDef.getParams().size()>0){
			jobDef.setJobIdForParams();
			jobParamsDAO.save(jobDef.getJobParams());
		}
		
		if(jobDef.getDependencies() !=null || jobDef.getDependencies().size()>0){
			jobDef.setJobIdForDepencencies();
			jobDependencyDAO.save(jobDef.getDependencies());
		}
	}
}