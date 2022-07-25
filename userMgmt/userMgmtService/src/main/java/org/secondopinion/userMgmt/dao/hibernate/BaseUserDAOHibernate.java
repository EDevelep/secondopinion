package org.secondopinion.userMgmt.dao.hibernate;

import org.secondopinion.userMgmt.dto.User;

import org.secondopinion.dao.hibernate.BaseHibernateDAO;
import org.secondopinion.userMgmt.dao.UserDAO;

/**
 * @author Generated Code - Please DO NOT MODIFY.
 * */
public abstract class BaseUserDAOHibernate extends BaseHibernateDAO<User, Long> implements UserDAO {
	private static final String TABLE_CLASS = "User";
	
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
	public Class<User> getTableClass() {
		return User.class;
	}
		
//	/* (non-Javadoc)
//	 * @see org.secondopinion.dataaccess.dao.hibernate.IHibernateDAO#getCriteria()
//	 */
//	public Criteria getCriteria() {
//		return super.getCriteria(User.class);
//	}
}
