package org.secondopinion.diagnosticcenter.dao.impl.hibernate;

import org.secondopinion.dao.hibernate.BaseHibernateDAO;
import org.secondopinion.diagnosticcenter.dao.DiagnosticcenterNotificationalertsDAO;
import org.secondopinion.diagnosticcenter.dto.Notificationalerts;



/**
 * @author Generated Code - Please DO NOT MODIFY.
 * */
public abstract class BaseNotificationalertsDAOHibernate extends BaseHibernateDAO<Notificationalerts, Long> implements DiagnosticcenterNotificationalertsDAO {
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
