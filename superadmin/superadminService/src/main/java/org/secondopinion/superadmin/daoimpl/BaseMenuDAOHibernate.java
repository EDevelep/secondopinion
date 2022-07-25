package org.secondopinion.superadmin.daoimpl;

import java.util.List;
import java.util.Map;



import org.hibernate.Criteria;
import org.hibernate.criterion.Criterion;
import org.secondopinion.dao.hibernate.BaseHibernateDAO;
import org.secondopinion.superadmin.dao.MenuDAO;
import org.secondopinion.superadmin.dto.Menu;

/**
 * @author Generated Code - Please DO NOT MODIFY.
 * */
public abstract class BaseMenuDAOHibernate extends BaseHibernateDAO<Menu, Integer> implements MenuDAO {
	private static final String TABLE_CLASS = "Menu";
	
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
//		return super.getCriteria(Menu.class);
//	}
}
