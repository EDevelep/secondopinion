package org.secondopinion.userMgmt.dao.hibernate;

import org.secondopinion.userMgmt.dto.Roles;

import org.secondopinion.dao.hibernate.BaseHibernateDAO;
import org.secondopinion.userMgmt.dao.RolesDAO;

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
	

	
//	/* (non-Javadoc)
//	 * @see org.secondopinion.dataaccess.dao.hibernate.IHibernateDAO#getCriteria()
//	 */
//	public Criteria getCriteria() {
//		return super.getCriteria(Roles.class);
//	}
}
