package org.secondopinion.userMgmt.dao.hibernate;

import org.secondopinion.userMgmt.dto.CompanyTemplate;

import org.secondopinion.dao.hibernate.BaseHibernateDAO;
import org.secondopinion.userMgmt.dao.CompanyTemplateDAO;

/**
 * @author Generated Code - Please DO NOT MODIFY.
 * */
public abstract class BaseCompanyTemplateDAOHibernate extends BaseHibernateDAO<CompanyTemplate, Long> implements CompanyTemplateDAO {
	private static final String TABLE_CLASS = "CompanyTemplate";
	
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
	public Class<CompanyTemplate> getTableClass() {
		return CompanyTemplate.class;
	}
	
//	/* (non-Javadoc)
//	 * @see org.secondopinion.dataaccess.dao.hibernate.IHibernateDAO#getCriteria()
//	 */
//	public Criteria getCriteria() {
//		return super.getCriteria(CompanyTemplate.class);
//	}
}
