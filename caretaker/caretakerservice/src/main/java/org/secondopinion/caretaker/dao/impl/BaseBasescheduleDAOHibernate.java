package org.secondopinion.caretaker.dao.impl;

import org.secondopinion.caretaker.dao.BasescheduleDAO;
import org.secondopinion.caretaker.dto.Baseschedule;
import org.secondopinion.dao.hibernate.BaseHibernateDAO;

/**
 * @author Generated Code - Please DO NOT MODIFY.
 * */
public abstract class BaseBasescheduleDAOHibernate extends BaseHibernateDAO<Baseschedule, Long> implements BasescheduleDAO {
	private static final String TABLE_CLASS = "Baseschedule";
	
	
	
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
	public Class<Baseschedule> getTableClass() {
		return Baseschedule.class;
	}
	
}
