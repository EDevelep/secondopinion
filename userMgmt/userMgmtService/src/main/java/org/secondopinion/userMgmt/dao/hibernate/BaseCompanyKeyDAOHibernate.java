package org.secondopinion.userMgmt.dao.hibernate;

import org.secondopinion.userMgmt.dto.CompanyKey;

import org.secondopinion.dao.hibernate.BaseHibernateDAO;
import org.secondopinion.userMgmt.dao.CompanyKeyDAO;

/**
 * @author Generated Code - Please DO NOT MODIFY.
 * */
public abstract class BaseCompanyKeyDAOHibernate extends BaseHibernateDAO<CompanyKey, Long> implements CompanyKeyDAO {
	private static final String TABLE_CLASS = "CompanyKey";
	
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
	public Class<CompanyKey> getTableClass() {
		return CompanyKey.class;
	}
	
//	/* (non-Javadoc)
//	 * @see org.secondopinion.dataaccess.dao.hibernate.IHibernateDAO#getCriteria()
//	 */
//	public Criteria getCriteria() {
//		return super.getCriteria(CompanyKey.class);
//	}
}
