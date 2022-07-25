package org.secondopinion.pharmacy.dao.impl.hibernate;

import org.secondopinion.dao.hibernate.BaseHibernateDAO;
import org.secondopinion.pharmacy.dao.PharmacyfcmtokenDAO;
import org.secondopinion.pharmacy.dto.Pharmacyfcmtoken;

/**
 * @author Generated Code - Please DO NOT MODIFY.
 * */
public abstract class BasePharmacyfcmtokenDAOHibernate extends BaseHibernateDAO<Pharmacyfcmtoken, Long> implements PharmacyfcmtokenDAO {
	private static final String TABLE_CLASS = "Pharmacyfcmtoken";
	
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
	public Class<Pharmacyfcmtoken> getTableClass() {
		return Pharmacyfcmtoken.class;
	}
	
		
//	/* (non-Javadoc)
//	 * @see com.vcube.dataaccess.dao.hibernate.IHibernateDAO#getCriteria()
//	 */
//	public Criteria getCriteria() {
//		return super.getCriteria(Doctorfcmtoken.class);
//	}
}
