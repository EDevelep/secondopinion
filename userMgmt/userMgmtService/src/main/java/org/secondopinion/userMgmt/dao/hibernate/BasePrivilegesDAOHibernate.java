package org.secondopinion.userMgmt.dao.hibernate;

import org.secondopinion.userMgmt.dto.Privileges;

import org.secondopinion.dao.hibernate.BaseHibernateDAO;
import org.secondopinion.userMgmt.dao.PrivilegesDAO;

/**
 * @author Generated Code - Please DO NOT MODIFY.
 * */
public abstract class BasePrivilegesDAOHibernate extends BaseHibernateDAO<Privileges, Integer> implements PrivilegesDAO {
	private static final String TABLE_CLASS = "Privileges";
	
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
	public Class<Privileges> getTableClass() {
		return Privileges.class;
	}
	
//	/* (non-Javadoc)
//	 * @see org.secondopinion.dataaccess.dao.hibernate.IHibernateDAO#getCriteria()
//	 */
//	public Criteria getCriteria() {
//		return super.getCriteria(Privileges.class);
//	}
}
