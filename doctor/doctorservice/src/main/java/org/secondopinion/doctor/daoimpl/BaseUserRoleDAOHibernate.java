package org.secondopinion.doctor.daoimpl;

import org.secondopinion.dao.hibernate.BaseHibernateDAO;
import org.secondopinion.doctor.dao.UserRoleDAO;
import org.secondopinion.doctor.dto.UserRole;

/**
 * @author Generated Code - Please DO NOT MODIFY.
 */
public abstract class BaseUserRoleDAOHibernate extends BaseHibernateDAO<UserRole, Integer> implements UserRoleDAO {
	private static final String TABLE_CLASS = "UserRole";

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.secondopinion.dataaccess.dao.hibernate.BaseHibernateDAO#getTableClassName()
	 */
	@Override
	public String getTableClassName() {
		return TABLE_CLASS;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.secondopinion.dataaccess.dao.hibernate.BaseHibernateDAO#getTableClass()
	 */
	@Override
	public Class<UserRole> getTableClass() {
		return UserRole.class;
	}
}
