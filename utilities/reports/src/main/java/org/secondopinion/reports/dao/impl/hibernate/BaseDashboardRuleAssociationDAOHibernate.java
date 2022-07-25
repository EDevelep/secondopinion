package org.secondopinion.reports.dao.impl.hibernate;

import org.secondopinion.dao.hibernate.BaseHibernateDAO;
import org.secondopinion.reports.dao.DashboardRuleAssociationDAO;
import org.secondopinion.reports.dto.DashboardRuleAssociation;

/**
 * @author Generated Code - Please DO NOT MODIFY.
 * */
public abstract class BaseDashboardRuleAssociationDAOHibernate extends BaseHibernateDAO<DashboardRuleAssociation, Long> implements DashboardRuleAssociationDAO {
	private static final String TABLE_CLASS = "DashboardRuleAssociation";
	
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
	public Class<DashboardRuleAssociation> getTableClass() {
		return DashboardRuleAssociation.class;
	}
	
}
