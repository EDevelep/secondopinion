package org.secondopinion.patient.dao.impl.hibernate;

import org.secondopinion.patient.dao.AilmentsDAO;
import org.secondopinion.dao.hibernate.BaseHibernateDAO;
import org.secondopinion.patient.dto.Ailments;

/**
 * @author Generated Code - Please DO NOT MODIFY.
 * */
public abstract class BaseAilmentsDAOHibernate extends BaseHibernateDAO<Ailments, Long> implements AilmentsDAO {
	private static final String TABLE_CLASS = "Ailments";
	
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
	public Class<Ailments> getTableClass() {
		return Ailments.class;
	}
	
		
//	/* (non-Javadoc)
//	 * @see org.secondopinion.dataaccess.dao.hibernate.IHibernateDAO#getCriteria()
//	 */
//	public Criteria getCriteria() {
//		return super.getCriteria(Ailments.class);
//	}
}
