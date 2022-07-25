package org.secondopinion.userMgmt.dao.hibernate;

import org.secondopinion.userMgmt.dto.UserProfilePic;

import org.secondopinion.dao.hibernate.BaseHibernateDAO;
import org.secondopinion.userMgmt.dao.UserProfilePicDAO;

/**
 * @author Generated Code - Please DO NOT MODIFY.
 * */
public abstract class BaseUserProfilePicDAOHibernate extends BaseHibernateDAO<UserProfilePic, Integer> implements UserProfilePicDAO {
	private static final String TABLE_CLASS = "UserProfilePic";
	
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
	public Class<UserProfilePic> getTableClass() {
		return UserProfilePic.class;
	}
	
//	/* (non-Javadoc)
//	 * @see org.secondopinion.dataaccess.dao.hibernate.IHibernateDAO#getCriteria()
//	 */
//	public Criteria getCriteria() {
//		return super.getCriteria(UserRole.class);
//	}
}
