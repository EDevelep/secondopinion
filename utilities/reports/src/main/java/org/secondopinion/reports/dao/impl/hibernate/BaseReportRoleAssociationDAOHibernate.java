package org.secondopinion.reports.dao.impl.hibernate;

import org.secondopinion.dao.hibernate.BaseHibernateDAO;
import org.secondopinion.reports.dao.ReportRoleAssociationDAO;
import org.secondopinion.reports.dto.ReportRoleAssociation;

/**
 * @author Generated Code - Please DO NOT MODIFY.
 * */
public abstract class BaseReportRoleAssociationDAOHibernate extends BaseHibernateDAO<ReportRoleAssociation, Long> implements ReportRoleAssociationDAO {
	private static final String TABLE_CLASS = "ReportRoleAssociation";
	
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
	public Class<ReportRoleAssociation> getTableClass() {
		return ReportRoleAssociation.class;
	}
}