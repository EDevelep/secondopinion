package org.secondopinion.reports.dao.impl.hibernate;

import org.secondopinion.dao.hibernate.BaseHibernateDAO;
import org.secondopinion.reports.dao.DashboardDAO;
import org.secondopinion.reports.dto.Dashboard;

/**
 * @author Generated Code - Please DO NOT MODIFY.
 * */
public abstract class BaseDashboardDAOHibernate extends BaseHibernateDAO<Dashboard, Integer> implements DashboardDAO {
	private static final String TABLE_CLASS = "Dashboard";
	
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
	public Class<Dashboard> getTableClass() {
		return Dashboard.class;
	}
	
//	/* (non-Javadoc)
//	 * @see com.vcube.dataaccess.dao.hibernate.IHibernateDAO#getCriteria()
//	 */
//	public Criteria getCriteria() {
//		return super.getCriteria(Dashboard.class);
//	}
}
