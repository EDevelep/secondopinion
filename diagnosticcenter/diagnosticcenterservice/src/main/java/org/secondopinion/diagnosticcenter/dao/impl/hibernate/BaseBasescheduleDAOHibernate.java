package org.secondopinion.diagnosticcenter.dao.impl.hibernate;

import org.secondopinion.dao.hibernate.BaseHibernateDAO;
import org.secondopinion.diagnosticcenter.dao.BasescheduleDAO;
import org.secondopinion.diagnosticcenter.dto.Baseschedule;

/**
 * @author Generated Code - Please DO NOT MODIFY.
 * */
public abstract class BaseBasescheduleDAOHibernate extends BaseHibernateDAO<Baseschedule, Long> implements BasescheduleDAO {
	private static final String TABLE_CLASS = "Baseschedule";
	
	
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
	public Class<Baseschedule> getTableClass() {
		return Baseschedule.class;
	}
	
		
//	/* (non-Javadoc)
//	 * @see com.vcube.dataaccess.dao.hibernate.IHibernateDAO#getCriteria()
//	 */
//	public Criteria getCriteria() {
//		return super.getCriteria(Baseschedule.class);
//	}
}
