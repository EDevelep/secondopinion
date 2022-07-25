package org.secondopinion.diagnosticcenter.dao.impl.hibernate;

import org.secondopinion.dao.hibernate.BaseHibernateDAO;
import org.secondopinion.diagnosticcenter.dao.DiagnosticcenterfcmtokenDAO;
import org.secondopinion.diagnosticcenter.dto.Diagnosticcenterfcmtoken;


/**
 * @author Generated Code - Please DO NOT MODIFY.
 * */
public abstract class BaseDiagnosticcenterfcmtokenDAOHibernate extends BaseHibernateDAO<Diagnosticcenterfcmtoken, Long> implements DiagnosticcenterfcmtokenDAO {
	private static final String TABLE_CLASS = "Diagnosticcenterfcmtoken";
	
	
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
	public Class<Diagnosticcenterfcmtoken> getTableClass() {
		return Diagnosticcenterfcmtoken.class;
	}
	
}
