package org.secondopinion.utilities.feedbackreport.dao.impl;

import java.util.List;
import java.util.Map;

import org.hibernate.criterion.Criterion;

import org.secondopinion.dao.hibernate.BaseHibernateDAO;
import org.secondopinion.utilities.feedbackreport.dao.ReportRoleAssociationDAO;
import org.secondopinion.utilities.feedbackreport.dto.ReportRoleAssociation;

/**
 * @author Generated Code - Please DO NOT MODIFY.
 * */
public abstract class BaseReportRoleAssociationDAOHibernate extends BaseHibernateDAO<ReportRoleAssociation, Long> implements ReportRoleAssociationDAO {
	private static final String TABLE_CLASS = "ReportRoleAssociation";
	
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
	public Class<ReportRoleAssociation> getTableClass() {
		return ReportRoleAssociation.class;
	}
	
}
