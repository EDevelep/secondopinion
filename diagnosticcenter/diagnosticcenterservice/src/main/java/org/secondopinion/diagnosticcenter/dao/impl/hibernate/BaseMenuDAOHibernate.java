package org.secondopinion.diagnosticcenter.dao.impl.hibernate;

import java.util.List;
import java.util.Map;



import org.hibernate.Criteria;
import org.hibernate.criterion.Criterion;
import org.secondopinion.dao.hibernate.BaseHibernateDAO;
import org.secondopinion.diagnosticcenter.dao.MenuDAO;
import org.secondopinion.diagnosticcenter.dto.Menu;

/**
 * @author Generated Code - Please DO NOT MODIFY.
 * */
public abstract class BaseMenuDAOHibernate extends BaseHibernateDAO<Menu, Long> implements MenuDAO {
	private static final String TABLE_CLASS = "Diagnosticcentermenu";
	
	
	
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
	public Class<Menu> getTableClass() {
		return Menu.class;
	}
	
		
//	/* (non-Javadoc)
//	 * @see com.vcube.dataaccess.dao.hibernate.IHibernateDAO#getCriteria()
//	 */
//	public Criteria getCriteria() {
//		return super.getCriteria(Diagnosticcentermenu.class);
//	}
}
