package org.secondopinion.pharmacy.dao.impl.hibernate;

import org.secondopinion.dao.hibernate.BaseHibernateDAO;
import org.secondopinion.pharmacy.dao.PharmacyratingsDAO;
import org.secondopinion.pharmacy.dto.Pharmacyratings;

/**
 * @author Generated Code - Please DO NOT MODIFY.
 * */
public abstract class BasePharmacyratingsDAOHibernate extends BaseHibernateDAO<Pharmacyratings, Long> implements PharmacyratingsDAO {
	private static final String TABLE_CLASS = "Pharmacyratings";
	
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
	public Class<Pharmacyratings> getTableClass() {
		return Pharmacyratings.class;
	}
	
		
//	/* (non-Javadoc)
//	 * @see com.vcube.dataaccess.dao.hibernate.IHibernateDAO#getCriteria()
//	 */
//	public Criteria getCriteria() {
//		return super.getCriteria(Pharmacyratings.class);
//	}
}
