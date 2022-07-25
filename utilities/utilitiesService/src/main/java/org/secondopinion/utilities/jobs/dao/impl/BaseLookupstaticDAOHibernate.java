package org.secondopinion.utilities.jobs.dao.impl;


import org.secondopinion.dao.hibernate.BaseHibernateDAO;
import org.secondopinion.utilities.jobs.dao.LookupstaticDAO;
import org.secondopinion.utilities.jobs.dto.Lookupstatic;



/**
 * @author Generated Code - Please DO NOT MODIFY.
 * */
public abstract class BaseLookupstaticDAOHibernate extends BaseHibernateDAO<Lookupstatic, Long> implements LookupstaticDAO {
	private static final String TABLE_CLASS = "Lookupstatic";
	
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
	public Class<Lookupstatic> getTableClass() {
		return Lookupstatic.class;
	}
	
		
//	/* (non-Javadoc)
//	 * @see org.secondopinion.dataaccess.dao.hibernate.IHibernateDAO#getCriteria()
//	 */
//	public Criteria getCriteria() {
//		return super.getCriteria(Lookupstatic.class);
//	}
}
