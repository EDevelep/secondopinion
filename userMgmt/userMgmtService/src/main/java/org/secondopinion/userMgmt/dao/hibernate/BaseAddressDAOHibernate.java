package org.secondopinion.userMgmt.dao.hibernate;

import org.secondopinion.userMgmt.dto.Address;

import org.secondopinion.dao.hibernate.BaseHibernateDAO;
import org.secondopinion.userMgmt.dao.AddressDAO;

/**
 * @author Generated Code - Please DO NOT MODIFY.
 * */
public abstract class BaseAddressDAOHibernate extends BaseHibernateDAO<Address, Integer> implements AddressDAO {
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
}
