package org.secondopinion.doctor.daoimpl;

import org.secondopinion.dao.hibernate.BaseHibernateDAO;
import org.secondopinion.doctor.dao.NotificationalertsDAO;
import org.secondopinion.doctor.dto.Notificationalerts;

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
