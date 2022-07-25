package org.secondopinion.diagnosticcenter.dao.impl.hibernate;

import org.secondopinion.dao.hibernate.BaseHibernateDAO;
import org.secondopinion.diagnosticcenter.dao.PackageDAO;

/**
 * @author Generated Code - Please DO NOT MODIFY.
 * */
public abstract class BasePackageDAOHibernate extends BaseHibernateDAO<org.secondopinion.diagnosticcenter.dto.Package, Long> implements PackageDAO {
	private static final String TABLE_CLASS = "Package";
	
	
	
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
	public Class<org.secondopinion.diagnosticcenter.dto.Package> getTableClass() {
		return org.secondopinion.diagnosticcenter.dto.Package.class;
	}
	
		
//	/* (non-Javadoc)
//	 * @see com.vcube.dataaccess.dao.hibernate.IHibernateDAO#getCriteria()
//	 */
//	public Criteria getCriteria() {
//		return super.getCriteria(Package.class);
//	}
}
