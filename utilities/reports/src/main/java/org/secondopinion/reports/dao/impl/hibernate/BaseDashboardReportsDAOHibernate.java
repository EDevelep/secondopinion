package org.secondopinion.reports.dao.impl.hibernate;

import org.secondopinion.dao.hibernate.BaseHibernateDAO;
import org.secondopinion.reports.dao.DashboardReportsDAO;
import org.secondopinion.reports.dto.DashboardReports;

/**
 * @author Generated Code - Please DO NOT MODIFY.
 * */
public abstract class BaseDashboardReportsDAOHibernate extends BaseHibernateDAO<DashboardReports, Long> implements DashboardReportsDAO {
	private static final String TABLE_CLASS = "DashboardReports";
	
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
	public Class<DashboardReports> getTableClass() {
		return DashboardReports.class;
	}
	
	
//	/* (non-Javadoc)
//	 * @see com.vcube.dataaccess.dao.hibernate.IHibernateDAO#getCriteria()
//	 */
//	public Criteria getCriteria() {
//		return super.getCriteria(DashboardReports.class);
//	}
}
