package org.secondopinion.utilities.jobs.dao.impl;

import org.secondopinion.dao.hibernate.BaseHibernateDAO;
import org.secondopinion.utilities.jobs.dao.JobDefDAO;
import org.secondopinion.utilities.jobs.dto.JobDef;

/**
 * @author Generated Code - Please DO NOT MODIFY.
 * */
public abstract class BaseJobDefDAOHibernate extends BaseHibernateDAO<JobDef, Integer> implements JobDefDAO {
	private static final String TABLE_CLASS = "JobDef";
	
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
	public Class<JobDef> getTableClass() {
		return JobDef.class;
	}
	
	
//	/* (non-Javadoc)
//	 * @see org.secondopinion.dataaccess.dao.hibernate.IHibernateDAO#getCriteria()
//	 */
//	public Criteria getCriteria() {
//		return super.getCriteria(JobDef.class);
//	}
}
