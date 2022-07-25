package org.secondopinion.diagnosticcenter.dao.impl.hibernate;

import org.secondopinion.dao.hibernate.BaseHibernateDAO;
import org.secondopinion.diagnosticcenter.dao.PackagesubmenuDAO;
import org.secondopinion.diagnosticcenter.dto.Packagesubmenu;

/**
 * @author Generated Code - Please DO NOT MODIFY.
 * */
public abstract class BasePackagesubmenuDAOHibernate extends BaseHibernateDAO<Packagesubmenu, Long> implements PackagesubmenuDAO {
	private static final String TABLE_CLASS = "Packagesubmenu";
	
	
	
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
	public Class<Packagesubmenu> getTableClass() {
		return Packagesubmenu.class;
	}
	
		
//	/* (non-Javadoc)
//	 * @see com.vcube.dataaccess.dao.hibernate.IHibernateDAO#getCriteria()
//	 */
//	public Criteria getCriteria() {
//		return super.getCriteria(Packagesubmenu.class);
//	}
}
