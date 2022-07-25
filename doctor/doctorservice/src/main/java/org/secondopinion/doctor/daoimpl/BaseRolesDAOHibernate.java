package org.secondopinion.doctor.daoimpl;

import org.secondopinion.dao.hibernate.BaseHibernateDAO;
import org.secondopinion.doctor.dao.RolesDAO;
import org.secondopinion.doctor.dto.Roles;

/**
 * @author Generated Code - Please DO NOT MODIFY.
 * */
public abstract class BaseRolesDAOHibernate extends BaseHibernateDAO<Roles, Integer> implements RolesDAO {
	private static final String TABLE_CLASS = "Roles";
	
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
	public Class<Roles> getTableClass() {
		return Roles.class;
	}
	
}
