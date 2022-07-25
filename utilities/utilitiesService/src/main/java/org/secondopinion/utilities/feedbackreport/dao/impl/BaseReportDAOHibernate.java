package org.secondopinion.utilities.feedbackreport.dao.impl;

import org.secondopinion.dao.hibernate.BaseHibernateDAO;
import org.secondopinion.utilities.feedbackreport.dao.ReportDAO;
import org.secondopinion.utilities.feedbackreport.dto.Report;

/**
 * @author Generated Code - Please DO NOT MODIFY.
 * */
public abstract class BaseReportDAOHibernate extends BaseHibernateDAO<Report, Long> implements ReportDAO {
	private static final String TABLE_CLASS = "Report";
	
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
	public Class<Report> getTableClass() {
		return Report.class;
	}
	
	
//	/* (non-Javadoc)
//	 * @see org.secondopinion.dataaccess.dao.hibernate.IHibernateDAO#getCriteria()
//	 */
//	public Criteria getCriteria() {
//		return super.getCriteria(Report.class);
//	}
}
