package org.secondopinion.diagnosticcenter.dao.impl.hibernate;

import org.secondopinion.dao.hibernate.BaseHibernateDAO;
import org.secondopinion.diagnosticcenter.dao.PrivilegeDAO;
import org.secondopinion.diagnosticcenter.dto.Privilege;

/**
 * @author Generated Code - Please DO NOT MODIFY.
 * */
public abstract class BasePrivilegeDAOHibernate extends BaseHibernateDAO<Privilege, Long> implements PrivilegeDAO {
	private static final String TABLE_CLASS = "Privilege";
	
	
	
	/* (non-Javadoc)
	 * @see com.vcube.dataaccess.dao.hibernate.BaseHibernateDAO#getTableClassName()
	 */
	@Override
	public String getTableClassName() {
		return TABLE_CLASS;
	}
	
	/* (non-Javadoc)
	 * @see com.vcube.dataaccess.dao.hibernate.BaseHibernateDAO#getTableClass()
	 */
	@Override
	public Class<Privilege> getTableClass() {
		return Privilege.class;
	}
	
		
//	/* (non-Javadoc)
//	 * @see com.vcube.dataaccess.dao.hibernate.IHibernateDAO#getCriteria()
//	 */
//	public Criteria getCriteria() {
//		return super.getCriteria(Privilege.class);
//	}
}
