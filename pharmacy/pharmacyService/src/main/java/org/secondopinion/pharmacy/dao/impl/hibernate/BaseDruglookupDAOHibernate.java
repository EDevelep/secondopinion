package org.secondopinion.pharmacy.dao.impl.hibernate;

import org.secondopinion.dao.hibernate.BaseHibernateDAO;
import org.secondopinion.pharmacy.dao.DruglookupDAO;
import org.secondopinion.pharmacy.dto.Druglookup;

/**
 * @author Generated Code - Please DO NOT MODIFY.
 * */
public abstract class BaseDruglookupDAOHibernate extends BaseHibernateDAO<Druglookup, Long> implements DruglookupDAO {
	private static final String TABLE_CLASS = "Druglookup";
	
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
	public Class<Druglookup> getTableClass() {
		return Druglookup.class;
	}
	
		
//	/* (non-Javadoc)
//	 * @see com.vcube.dataaccess.dao.hibernate.IHibernateDAO#getCriteria()
//	 */
//	public Criteria getCriteria() {
//		return super.getCriteria(Druglookup.class);
//	}
}
