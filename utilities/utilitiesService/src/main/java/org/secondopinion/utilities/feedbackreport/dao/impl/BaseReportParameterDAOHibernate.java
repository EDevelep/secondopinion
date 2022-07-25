package org.secondopinion.utilities.feedbackreport.dao.impl;

import org.secondopinion.dao.hibernate.BaseHibernateDAO;
import org.secondopinion.utilities.feedbackreport.dao.ReportParameterDAO;
import org.secondopinion.utilities.feedbackreport.dto.ReportParameter;

/**
 * @author Generated Code - Please DO NOT MODIFY.
 * */
public abstract class BaseReportParameterDAOHibernate extends BaseHibernateDAO<ReportParameter, Long> implements ReportParameterDAO {
	private static final String TABLE_CLASS = "ReportParameter";
	
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
	public Class<ReportParameter> getTableClass() {
		return ReportParameter.class;
	}
	
	
//	/* (non-Javadoc)
//	 * @see org.secondopinion.dataaccess.dao.hibernate.IHibernateDAO#getCriteria()
//	 */
//	public Criteria getCriteria() {
//		return super.getCriteria(ReportParameter.class);
//	}
}
