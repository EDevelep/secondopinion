package org.secondopinion.reports.dao.impl.hibernate;

import org.secondopinion.dao.hibernate.BaseHibernateDAO;
import org.secondopinion.reports.dao.CompanyTemplateDAO;
import org.secondopinion.reports.dto.CompanyTemplate;

/**
 * @author Generated Code - Please DO NOT MODIFY.
 * */
public abstract class BaseCompanyTemplateDAOHibernate extends BaseHibernateDAO<CompanyTemplate, Long> implements CompanyTemplateDAO {
	private static final String TABLE_CLASS = "CompanyTemplate";
	
	/* (non-Javadoc)
	 * @see com.vcube.dataaccess.dao.hibernate.BaseHibernateDAO#getTableClassName()
	 */
	@Override
	public String getTableClassName() {
		return TABLE_CLASS;
	}
	
	/* (non-Javadoc)
	 * @see com.vcube.dataaccess.dao.hibernate.BaseHibernateDAO#getTableClass()
	 */
	@Override
	public Class<CompanyTemplate> getTableClass() {
		return CompanyTemplate.class;
	}
	
//	/* (non-Javadoc)
//	 * @see com.vcube.dataaccess.dao.hibernate.IHibernateDAO#getCriteria()
//	 */
//	public Criteria getCriteria() {
//		return super.getCriteria(CompanyTemplate.class);
//	}
}
