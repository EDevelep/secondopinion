package org.secondopinion.pharmacy.dao.impl.hibernate;

import org.secondopinion.dao.hibernate.BaseHibernateDAO;
import org.secondopinion.pharmacy.dao.PharmacyaddressDAO;
import org.secondopinion.pharmacy.dto.Pharmacyaddress;


/**
 * @author Generated Code - Please DO NOT MODIFY.
 * */
public abstract class BasePharmacyaddressDAOHibernate extends BaseHibernateDAO<Pharmacyaddress, Long> implements PharmacyaddressDAO  {
	private static final String TABLE_CLASS = "Address";
	
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
	public Class<Pharmacyaddress> getTableClass() {
		return Pharmacyaddress.class;
	}
	
		
//	/* (non-Javadoc)
//	 * @see com.vcube.dataaccess.dao.hibernate.IHibernateDAO#getCriteria()
//	 */
//	public Criteria getCriteria() {
//		return super.getCriteria(Address.class);
//	}
}
