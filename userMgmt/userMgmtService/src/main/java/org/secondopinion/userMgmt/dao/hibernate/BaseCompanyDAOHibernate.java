package org.secondopinion.userMgmt.dao.hibernate;

import org.secondopinion.userMgmt.dto.Company;

import org.secondopinion.dao.hibernate.BaseHibernateDAO;
import org.secondopinion.userMgmt.dao.CompanyDAO;

/**
 * @author Generated Code - Please DO NOT MODIFY.
 * */
public abstract class BaseCompanyDAOHibernate extends BaseHibernateDAO<Company, Integer> implements CompanyDAO {
	private static final String TABLE_CLASS = "Company";
	
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
	public Class<Company> getTableClass() {
		return Company.class;
	}
		
//	/* (non-Javadoc)
//	 * @see org.secondopinion.dataaccess.dao.hibernate.IHibernateDAO#getCriteria()
//	 */
//	public Criteria getCriteria() {
//		return super.getCriteria(Company.class);
//	}
}
