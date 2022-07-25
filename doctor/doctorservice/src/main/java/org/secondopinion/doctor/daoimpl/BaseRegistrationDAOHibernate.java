package org.secondopinion.doctor.daoimpl;

import org.secondopinion.dao.hibernate.BaseHibernateDAO;
import org.secondopinion.doctor.dao.RegistrationDAO;
import org.secondopinion.doctor.dto.Registration;

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
	
}
