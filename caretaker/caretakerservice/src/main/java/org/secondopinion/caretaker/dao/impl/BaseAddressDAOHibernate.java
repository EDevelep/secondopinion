package org.secondopinion.caretaker.dao.impl;

import java.util.List;
import java.util.Map;



import org.hibernate.Criteria;
import org.hibernate.criterion.Criterion;
import org.secondopinion.caretaker.dao.AddressDAO;
import org.secondopinion.caretaker.dto.Address;
import org.secondopinion.dao.hibernate.BaseHibernateDAO;


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
