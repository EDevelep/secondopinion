package org.secondopinion.caretaker.dao.impl;

import org.secondopinion.caretaker.dao.NotificationalertsDAO;
import org.secondopinion.caretaker.dto.Notificationalerts;
import org.secondopinion.dao.hibernate.BaseHibernateDAO;


/**
 * @author Generated Code - Please DO NOT MODIFY.
 * */
public abstract class BaseNotificationalertsDAOHibernate extends BaseHibernateDAO<Notificationalerts, Long> implements NotificationalertsDAO {
	private static final String TABLE_CLASS = "Notificationalerts";
	
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
	public Class<Notificationalerts> getTableClass() {
		return Notificationalerts.class;
	}
	
}
