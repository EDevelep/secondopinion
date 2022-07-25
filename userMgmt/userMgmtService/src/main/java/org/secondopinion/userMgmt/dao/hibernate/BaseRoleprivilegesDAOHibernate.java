package org.secondopinion.userMgmt.dao.hibernate;

import org.secondopinion.userMgmt.dto.Roleprivileges;

import org.secondopinion.dao.hibernate.BaseHibernateDAO;
import org.secondopinion.userMgmt.dao.RoleprivilegesDAO;

/**
 * @author Generated Code - Please DO NOT MODIFY.
 * */
public abstract class BaseRoleprivilegesDAOHibernate extends BaseHibernateDAO<Roleprivileges, Integer> implements RoleprivilegesDAO {
	private static final String TABLE_CLASS = "Roleprivileges";
	
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
	public Class<Roleprivileges> getTableClass() {
		return Roleprivileges.class;
	}
	

//	/* (non-Javadoc)
//	 * @see org.secondopinion.dataaccess.dao.hibernate.IHibernateDAO#getCriteria()
//	 */
//	public Criteria getCriteria() {
//		return super.getCriteria(Roleprivileges.class);
//	}
}
