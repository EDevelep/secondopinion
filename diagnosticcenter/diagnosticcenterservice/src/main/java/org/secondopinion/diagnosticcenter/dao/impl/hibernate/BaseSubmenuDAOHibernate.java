package org.secondopinion.diagnosticcenter.dao.impl.hibernate;

import java.util.List;
import java.util.Map;



import org.hibernate.Criteria;
import org.hibernate.criterion.Criterion;
import org.secondopinion.dao.hibernate.BaseHibernateDAO;
import org.secondopinion.diagnosticcenter.dao.SubmenuDAO;
import org.secondopinion.diagnosticcenter.dto.Submenu;

/**
 * @author Generated Code - Please DO NOT MODIFY.
 * */
public abstract class BaseSubmenuDAOHibernate extends BaseHibernateDAO<Submenu, Long> implements SubmenuDAO {
	private static final String TABLE_CLASS = "Submenu";
	
	
	
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
	public Class<Submenu> getTableClass() {
		return Submenu.class;
	}
	
		
//	/* (non-Javadoc)
//	 * @see com.vcube.dataaccess.dao.hibernate.IHibernateDAO#getCriteria()
//	 */
//	public Criteria getCriteria() {
//		return super.getCriteria(Submenu.class);
//	}
}
