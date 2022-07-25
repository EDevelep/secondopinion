package org.secondopinion.diagnosticcenter.dao.impl.hibernate;

import org.secondopinion.dao.hibernate.BaseHibernateDAO;
import org.secondopinion.diagnosticcenter.dao.UseraddressDAO;
import org.secondopinion.diagnosticcenter.dto.Useraddress;

/**
 * @author Generated Code - Please DO NOT MODIFY.
 * */
public abstract class BaseUseraddressDAOHibernate extends BaseHibernateDAO<Useraddress, Long> implements UseraddressDAO {
	private static final String TABLE_CLASS = "Useraddress";
	
	
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
	public Class<Useraddress> getTableClass() {
		return Useraddress.class;
	}
	
		
//	/* (non-Javadoc)
//	 * @see com.vcube.dataaccess.dao.hibernate.IHibernateDAO#getCriteria()
//	 */
//	public Criteria getCriteria() {
//		return super.getCriteria(Useraddress.class);
//	}
}
