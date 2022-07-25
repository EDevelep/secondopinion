package org.secondopinion.pharmacy.dao.impl.hibernate;

import java.util.List;
import java.util.Map;


import org.hibernate.Criteria;
import org.hibernate.criterion.Criterion;
import org.secondopinion.dao.hibernate.BaseHibernateDAO;
import org.secondopinion.pharmacy.dao.AddressDAO;
import org.secondopinion.pharmacy.dto.Address;

/**
 * @author Generated Code - Please DO NOT MODIFY.
 * */
public abstract class BaseAddressDAOHibernate extends BaseHibernateDAO<Address, Long> implements AddressDAO {
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
	public Class<Address> getTableClass() {
		return Address.class;
	}
	
		
//	/* (non-Javadoc)
//	 * @see com.vcube.dataaccess.dao.hibernate.IHibernateDAO#getCriteria()
//	 */
//	public Criteria getCriteria() {
//		return super.getCriteria(Address.class);
//	}
}
