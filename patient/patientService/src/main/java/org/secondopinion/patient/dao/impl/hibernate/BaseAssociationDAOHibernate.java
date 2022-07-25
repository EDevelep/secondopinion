package org.secondopinion.patient.dao.impl.hibernate;

import org.secondopinion.dao.hibernate.BaseHibernateDAO;
import org.secondopinion.patient.dao.AssociationDAO;
import org.secondopinion.patient.dto.Association;

/**
 * @author Generated Code - Please DO NOT MODIFY.
 */
public abstract class BaseAssociationDAOHibernate extends BaseHibernateDAO<Association, Long>
		implements AssociationDAO {
	private static final String TABLE_CLASS = "Association";

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.secondopinion.dataaccess.dao.hibernate.BaseHibernateDAO#getTableClassName
	 * ()
	 */
	@Override
	public String getTableClassName() {
		return TABLE_CLASS;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.secondopinion.dataaccess.dao.hibernate.BaseHibernateDAO#getTableClass()
	 */
	@Override
	public Class<Association> getTableClass() {
		return Association.class;
	}

//	/* (non-Javadoc)
//	 * @see org.secondopinion.dataaccess.dao.hibernate.IHibernateDAO#getCriteria()
//	 */
//	public Criteria getCriteria() {
//		return super.getCriteria(Association.class);
//	}
}
