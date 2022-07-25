package org.secondopinion.reports.dao.impl.hibernate;

import org.secondopinion.dao.hibernate.BaseHibernateDAO;
import org.secondopinion.reports.dao.ReportDAO;
import org.secondopinion.reports.dto.Report;

/**
 * @author Generated Code - Please DO NOT MODIFY.
 * */
public abstract class BaseReportDAOHibernate extends BaseHibernateDAO<Report, Long> implements ReportDAO {
	private static final String TABLE_CLASS = "Report";
	
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
	public Class<Report> getTableClass() {
		return Report.class;
	}
	
	
//	/* (non-Javadoc)
//	 * @see com.vcube.dataaccess.dao.hibernate.IHibernateDAO#getCriteria()
//	 */
//	public Criteria getCriteria() {
//		return super.getCriteria(Report.class);
//	}
}
