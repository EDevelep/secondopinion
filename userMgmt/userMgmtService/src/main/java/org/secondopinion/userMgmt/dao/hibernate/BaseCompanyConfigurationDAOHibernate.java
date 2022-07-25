package org.secondopinion.userMgmt.dao.hibernate;

import org.secondopinion.userMgmt.dto.CompanyConfiguration;

import org.secondopinion.dao.hibernate.BaseHibernateDAO;
import org.secondopinion.userMgmt.dao.CompanyConfigurationDAO;

/**
 * @author Generated Code - Please DO NOT MODIFY.
 * */
public abstract class BaseCompanyConfigurationDAOHibernate extends BaseHibernateDAO<CompanyConfiguration, Integer> implements CompanyConfigurationDAO {
	private static final String TABLE_CLASS = "CompanyConfiguration";
	
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
	public Class<CompanyConfiguration> getTableClass() {
		return CompanyConfiguration.class;
	}
	
	
//	/* (non-Javadoc)
//	 * @see org.secondopinion.dataaccess.dao.hibernate.IHibernateDAO#getCriteria()
//	 */
//	public Criteria getCriteria() {
//		return super.getCriteria(CompanyConfiguration.class);
//	}
}
