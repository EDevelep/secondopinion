package org.secondopinion.pharmacy.dao.impl.hibernate;

import org.secondopinion.dao.hibernate.BaseHibernateDAO;
import org.secondopinion.pharmacy.dao.PharmacyDAO;
import org.secondopinion.pharmacy.dto.Pharmacy;

/**
 * @author Generated Code - Please DO NOT MODIFY.
 * */
public abstract class BasePharmacyDAOHibernate extends BaseHibernateDAO<Pharmacy, Long> implements PharmacyDAO {
	private static final String TABLE_CLASS = "Pharmacy";
	
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
	public Class<Pharmacy> getTableClass() {
		return Pharmacy.class;
	}
	
		
//	/* (non-Javadoc)
//	 * @see com.vcube.dataaccess.dao.hibernate.IHibernateDAO#getCriteria()
//	 */
//	public Criteria getCriteria() {
//		return super.getCriteria(Pharmacy.class);
//	}
}
