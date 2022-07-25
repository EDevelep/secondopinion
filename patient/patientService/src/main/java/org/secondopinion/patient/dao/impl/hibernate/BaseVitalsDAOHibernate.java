package org.secondopinion.patient.dao.impl.hibernate;

import org.secondopinion.patient.dao.VitalsDAO;
import org.secondopinion.dao.hibernate.BaseHibernateDAO;
import org.secondopinion.patient.dto.Vitals;

/**
 * @author Generated Code - Please DO NOT MODIFY.
 * */
public abstract class BaseVitalsDAOHibernate extends BaseHibernateDAO<Vitals, Long> implements VitalsDAO {
	private static final String TABLE_CLASS = "Vitals";
	
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
	public Class<Vitals> getTableClass() {
		return Vitals.class;
	}
	
		
//	/* (non-Javadoc)
//	 * @see org.secondopinion.dataaccess.dao.hibernate.IHibernateDAO#getCriteria()
//	 */
//	public Criteria getCriteria() {
//		return super.getCriteria(Vitals.class);
//	}
}
