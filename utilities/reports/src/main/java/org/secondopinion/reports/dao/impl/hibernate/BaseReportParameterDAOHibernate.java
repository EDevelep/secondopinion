package org.secondopinion.reports.dao.impl.hibernate;

import org.secondopinion.dao.hibernate.BaseHibernateDAO;
import org.secondopinion.reports.dao.ReportParameterDAO;
import org.secondopinion.reports.dto.ReportParameter;

/**
 * @author Generated Code - Please DO NOT MODIFY.
 * */
public abstract class BaseReportParameterDAOHibernate extends BaseHibernateDAO<ReportParameter, Long> implements ReportParameterDAO {
	private static final String TABLE_CLASS = "ReportParameter";
	
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
	public Class<ReportParameter> getTableClass() {
		return ReportParameter.class;
	}
	
	
//	/* (non-Javadoc)
//	 * @see com.vcube.dataaccess.dao.hibernate.IHibernateDAO#getCriteria()
//	 */
//	public Criteria getCriteria() {
//		return super.getCriteria(ReportParameter.class);
//	}
}
