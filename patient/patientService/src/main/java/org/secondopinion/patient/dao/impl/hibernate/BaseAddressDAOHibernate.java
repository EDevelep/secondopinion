package org.secondopinion.patient.dao.impl.hibernate;

import org.secondopinion.patient.dao.AddressDAO;
import org.secondopinion.dao.hibernate.BaseHibernateDAO;
import org.secondopinion.patient.dto.Address;

/**
 * @author Generated Code - Please DO NOT MODIFY.
 * */
public abstract class BaseAddressDAOHibernate extends BaseHibernateDAO<Address, Long> implements AddressDAO {
	private static final String TABLE_CLASS = "Address";
	
	/* (non-Javadoc)
	 * @see org.secondopinion.dataaccess.dao.hibernate.BaseHibernateDAO#getTableClassName()
	 */
	@Override
	public String getTableClassName() {
		return TABLE_CLASS;
	}
	
	/* (non-Javadoc)
	 * @see org.secondopinion.dataaccess.dao.hibernate.BaseHibernateDAO#getTableClass()
	 */
	@Override
	public Class<Address> getTableClass() {
		return Address.class;
	}
	
		
//	/* (non-Javadoc)
//	 * @see org.secondopinion.dataaccess.dao.hibernate.IHibernateDAO#getCriteria()
//	 */
//	public Criteria getCriteria() {
//		return super.getCriteria(Address.class);
//	}
}
