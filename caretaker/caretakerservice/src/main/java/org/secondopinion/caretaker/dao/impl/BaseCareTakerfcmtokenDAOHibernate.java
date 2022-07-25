package org.secondopinion.caretaker.dao.impl;

import org.secondopinion.caretaker.dao.CareTakerfcmtokenDAO;
import org.secondopinion.caretaker.dto.Caretakerfcmtoken;
import org.secondopinion.dao.hibernate.BaseHibernateDAO;

/**
 * @author Generated Code - Please DO NOT MODIFY.
 * */
public abstract class BaseCareTakerfcmtokenDAOHibernate extends BaseHibernateDAO<Caretakerfcmtoken, Long> implements CareTakerfcmtokenDAO {
	private static final String TABLE_CLASS = "Doctorfcmtoken";
	
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
	public Class<Caretakerfcmtoken> getTableClass() {
		return Caretakerfcmtoken.class;
	}
	
}
