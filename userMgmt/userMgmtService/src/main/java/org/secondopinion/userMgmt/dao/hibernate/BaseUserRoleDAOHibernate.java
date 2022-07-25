package org.secondopinion.userMgmt.dao.hibernate;

import org.secondopinion.userMgmt.dto.UserRole;

import org.secondopinion.dao.hibernate.BaseHibernateDAO;
import org.secondopinion.userMgmt.dao.UserRoleDAO;

/**
 * @author Generated Code - Please DO NOT MODIFY.
 * */
public abstract class BaseUserRoleDAOHibernate extends BaseHibernateDAO<UserRole, Integer> implements UserRoleDAO {
	private static final String TABLE_CLASS = "UserRole";
	
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
	public Class<UserRole> getTableClass() {
		return UserRole.class;
	}
	
//	/* (non-Javadoc)
//	 * @see org.secondopinion.dataaccess.dao.hibernate.IHibernateDAO#getCriteria()
//	 */
//	public Criteria getCriteria() {
//		return super.getCriteria(UserRole.class);
//	}
}
