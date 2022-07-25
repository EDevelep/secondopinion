package org.secondopinion.diagnosticcenter.dao.impl.hibernate;

import java.util.List;
import java.util.Map;


import org.hibernate.Criteria;
import org.hibernate.criterion.Criterion;
import org.secondopinion.dao.hibernate.BaseHibernateDAO;
import org.secondopinion.diagnosticcenter.dao.RegistrationDAO;
import org.secondopinion.diagnosticcenter.dto.Registration;

/**
 * @author Generated Code - Please DO NOT MODIFY.
 * */
public abstract class BaseRegistrationDAOHibernate extends BaseHibernateDAO<Registration, Long> implements RegistrationDAO {
	private static final String TABLE_CLASS = "Registration";
	
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
	public Class<Registration> getTableClass() {
		return Registration.class;
	}
	
		
//	/* (non-Javadoc)
//	 * @see com.vcube.dataaccess.dao.hibernate.IHibernateDAO#getCriteria()
//	 */
//	public Criteria getCriteria() {
//		return super.getCriteria(Registration.class);
//	}
}
