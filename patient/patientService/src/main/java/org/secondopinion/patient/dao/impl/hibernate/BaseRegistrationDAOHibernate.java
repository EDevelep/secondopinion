package org.secondopinion.patient.dao.impl.hibernate;

import org.secondopinion.patient.dao.RegistrationDAO;
import org.secondopinion.dao.hibernate.BaseHibernateDAO;
import org.secondopinion.patient.dto.Registration;

/**
 * @author Generated Code - Please DO NOT MODIFY.
 * */
public abstract class BaseRegistrationDAOHibernate extends BaseHibernateDAO<Registration, Long> implements RegistrationDAO {
	private static final String TABLE_CLASS = "Registration";
	
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
	public Class<Registration> getTableClass() {
		return Registration.class;
	}
	
		
//	/* (non-Javadoc)
//	 * @see org.secondopinion.dataaccess.dao.hibernate.IHibernateDAO#getCriteria()
//	 */
//	public Criteria getCriteria() {
//		return super.getCriteria(Registration.class);
//	}
}
