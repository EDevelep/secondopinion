package org.secondopinion.utilities.jobs.dao.impl;


import org.secondopinion.dao.hibernate.BaseHibernateDAO;
import org.secondopinion.utilities.jobs.dao.JobParamsDAO;
import org.secondopinion.utilities.jobs.dto.JobParams;

/**
 * @author Generated Code - Please DO NOT MODIFY.
 * */
public abstract class BaseJobParamsDAOHibernate extends BaseHibernateDAO<JobParams, Integer> implements JobParamsDAO {
	private static final String TABLE_CLASS = "JobParams";
	
	/* (non-Javadoc)
	 * @see org.secondopinion.utilitiesdataaccess.dao.hibernate.BaseHibernateDAO#getTableClassName()
	 */
	@Override
	public String getTableClassName() {
		return TABLE_CLASS;
	}
	
	/* (non-Javadoc)
	 * @see org.secondopinion.utilitiesdataaccess.dao.hibernate.BaseHibernateDAO#getTableClass()
	 */
	@Override
	public Class<JobParams> getTableClass() {
		return JobParams.class;
	}
	
//	/* (non-Javadoc)
//	 * @see org.secondopinion.utilitiesdataaccess.dao.hibernate.IHibernateDAO#getCriteria()
//	 */
//	public Criteria getCriteria() {
//		return super.getCriteria(JobParams.class);
//	}
}
