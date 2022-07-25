package org.secondopinion.pharmacy.dao.impl.hibernate;

import java.util.List;
import java.util.Map;


import org.hibernate.Criteria;
import org.hibernate.criterion.Criterion;
import org.secondopinion.dao.hibernate.BaseHibernateDAO;
import org.secondopinion.pharmacy.dao.RoletofunctionDAO;
import org.secondopinion.pharmacy.dto.Roletofunction;

/**
 * @author Generated Code - Please DO NOT MODIFY.
 * */
public abstract class BaseRoletofunctionDAOHibernate extends BaseHibernateDAO<Roletofunction, Integer> implements RoletofunctionDAO {
	private static final String TABLE_CLASS = "Roletofunction";
	
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
	public Class<Roletofunction> getTableClass() {
		return Roletofunction.class;
	}
	
		
//	/* (non-Javadoc)
//	 * @see com.vcube.dataaccess.dao.hibernate.IHibernateDAO#getCriteria()
//	 */
//	public Criteria getCriteria() {
//		return super.getCriteria(Roletofunction.class);
//	}
}
