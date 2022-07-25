package org.secondopinion.utilities.jobs.dao.impl;

import java.util.List;
import java.util.Map;

import org.hibernate.criterion.Criterion;

import org.secondopinion.dao.hibernate.BaseHibernateDAO;
import org.secondopinion.utilities.jobs.dao.JobDependencyDAO;
import org.secondopinion.utilities.jobs.dto.JobDependency;

/**
 * @author Generated Code - Please DO NOT MODIFY.
 * */
public abstract class BaseJobDependencyDAOHibernate extends BaseHibernateDAO<JobDependency, Integer> implements JobDependencyDAO {
	private static final String TABLE_CLASS = "JobDependency";
	
	/* (non-Javadoc)
	 * @see org.secondopinion.dataaccess.dao.hibernate.BaseHibernateDAO#getTableClassName()
	 */
	@Override
	public String getTableClassName() {
		return TABLE_CLASS;
	}
	
	/* (non-Javadoc)
	 * @see org.secondopinion.dataaccess.dao.hibernate.BaseHibernateDAO#getTableClass()
	 */
	@Override
	public Class<JobDependency> getTableClass() {
		return JobDependency.class;
	}
}
