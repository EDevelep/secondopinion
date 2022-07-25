package org.secondopinion.utilities.jobs.dao.impl;

import java.util.List;
import java.util.Map;

import org.hibernate.criterion.Criterion;

import org.secondopinion.dao.hibernate.BaseHibernateDAO;
import org.secondopinion.utilities.jobs.dao.JobInfoDAO;
import org.secondopinion.utilities.jobs.dto.JobInfo;

/**
 * @author Generated Code - Please DO NOT MODIFY.
 * */
public abstract class BaseJobInfoDAOHibernate extends BaseHibernateDAO<JobInfo, Long> implements JobInfoDAO {
	private static final String TABLE_CLASS = "JobInfo";
	
	/* (non-Javadoc)
	 * @see org.secondopinion.utilities.dataaccess.dao.hibernate.BaseHibernateDAO#getTableClassName()
	 */
	@Override
	public String getTableClassName() {
		return TABLE_CLASS;
	}
	
	/* (non-Javadoc)
	 * @see org.secondopinion.utilities.dataaccess.dao.hibernate.BaseHibernateDAO#getTableClass()
	 */
	@Override
	public Class<JobInfo> getTableClass() {
		return JobInfo.class;
	}

//	/* (non-Javadoc)
//	 * @see org.secondopinion.utilities.dataaccess.dao.hibernate.IHibernateDAO#getCriteria()
//	 */
//	public Criteria getCriteria() {
//		return super.getCriteria(JobInfo.class);
//	}
}
