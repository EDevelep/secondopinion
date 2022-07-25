package org.secondopinion.pharmacy.dao.impl.hibernate;

import org.secondopinion.dao.hibernate.BaseHibernateDAO;
import org.secondopinion.pharmacy.dao.PharmacyuserDAO;
import org.secondopinion.pharmacy.dto.Pharmacyuser;

/**
 * @author Generated Code - Please DO NOT MODIFY.
 * */
public abstract class BasePharmacyuserDAOHibernate extends BaseHibernateDAO<Pharmacyuser, Long> implements PharmacyuserDAO {
	private static final String TABLE_CLASS = "Pharmacyuser";
	
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
	public Class<Pharmacyuser> getTableClass() {
		return Pharmacyuser.class;
	}
	
		
//	/* (non-Javadoc)
//	 * @see com.vcube.dataaccess.dao.hibernate.IHibernateDAO#getCriteria()
//	 */
//	public Criteria getCriteria() {
//		return super.getCriteria(Pharmacyuser.class);
//	}
}
