package org.secondopinion.doctor.daoimpl;

import org.secondopinion.dao.hibernate.BaseHibernateDAO;
import org.secondopinion.doctor.dao.BasescheduleDAO;
import org.secondopinion.doctor.dto.Baseschedule;

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
