package org.secondopinion.diagnosticcenter.dao.impl.hibernate;

import org.secondopinion.dao.hibernate.BaseHibernateDAO;
import org.secondopinion.diagnosticcenter.dao.PatientrequestDAO;
import org.secondopinion.diagnosticcenter.dto.Patientrequest;

/**
 * @author Generated Code - Please DO NOT MODIFY.
 * */
public abstract class BasePatientrequestDAOHibernate extends BaseHibernateDAO<Patientrequest, Integer> implements PatientrequestDAO {
	private static final String TABLE_CLASS = "Patientrequest";
	
	
	
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
	public Class<Patientrequest> getTableClass() {
		return Patientrequest.class;
	}
	
		
//	/* (non-Javadoc)
//	 * @see com.vcube.dataaccess.dao.hibernate.IHibernateDAO#getCriteria()
//	 */
//	public Criteria getCriteria() {
//		return super.getCriteria(Patientrequest.class);
//	}
}
