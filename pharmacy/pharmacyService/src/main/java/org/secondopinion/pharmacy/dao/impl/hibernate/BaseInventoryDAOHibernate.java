package org.secondopinion.pharmacy.dao.impl.hibernate;

import java.util.List;
import java.util.Map;



import org.hibernate.Criteria;
import org.hibernate.criterion.Criterion;
import org.secondopinion.dao.hibernate.BaseHibernateDAO;
import org.secondopinion.pharmacy.dao.InventoryDAO;
import org.secondopinion.pharmacy.dto.Inventory;

/**
 * @author Generated Code - Please DO NOT MODIFY.
 * */
public abstract class BaseInventoryDAOHibernate extends BaseHibernateDAO<Inventory, Long> implements InventoryDAO {
	private static final String TABLE_CLASS = "Inventory";
	
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
	public Class<Inventory> getTableClass() {
		return Inventory.class;
	}
	
		
//	/* (non-Javadoc)
//	 * @see com.vcube.dataaccess.dao.hibernate.IHibernateDAO#getCriteria()
//	 */
//	public Criteria getCriteria() {
//		return super.getCriteria(Inventory.class);
//	}
}
