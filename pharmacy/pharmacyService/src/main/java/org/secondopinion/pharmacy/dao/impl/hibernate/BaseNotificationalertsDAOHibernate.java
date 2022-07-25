package org.secondopinion.pharmacy.dao.impl.hibernate;

import org.secondopinion.dao.hibernate.BaseHibernateDAO;
import org.secondopinion.pharmacy.dao.NotificationalertsDAO;
import org.secondopinion.pharmacy.dto.Notificationalerts;

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
	
		
//	/* (non-Javadoc)
//	 * @see com.vcube.dataaccess.dao.hibernate.IHibernateDAO#getCriteria()
//	 */
//	public Criteria getCriteria() {
//		return super.getCriteria(Notificationalerts.class);
//	}
}
