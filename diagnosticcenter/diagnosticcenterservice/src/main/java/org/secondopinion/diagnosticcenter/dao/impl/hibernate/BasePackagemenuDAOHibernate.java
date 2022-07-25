package org.secondopinion.diagnosticcenter.dao.impl.hibernate;

import org.secondopinion.dao.hibernate.BaseHibernateDAO;
import org.secondopinion.diagnosticcenter.dao.PackagemenuDAO;
import org.secondopinion.diagnosticcenter.dto.Packagemenu;

/**
 * @author Generated Code - Please DO NOT MODIFY.
 * */
public abstract class BasePackagemenuDAOHibernate extends BaseHibernateDAO<Packagemenu, Long> implements PackagemenuDAO {
	private static final String TABLE_CLASS = "Packagemenu";
	
	
	
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
	public Class<Packagemenu> getTableClass() {
		return Packagemenu.class;
	}
	
		
//	/* (non-Javadoc)
//	 * @see com.vcube.dataaccess.dao.hibernate.IHibernateDAO#getCriteria()
//	 */
//	public Criteria getCriteria() {
//		return super.getCriteria(Packagemenu.class);
//	}
}
