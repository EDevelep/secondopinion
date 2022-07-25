package org.secondopinion.doctor.daoimpl;

import org.secondopinion.dao.hibernate.BaseHibernateDAO;
import org.secondopinion.doctor.dao.AssociationDAO;
import org.secondopinion.doctor.dto.Association;

/**
 * @author Generated Code - Please DO NOT MODIFY.
 * */
public abstract class BaseAssociationDAOHibernate extends BaseHibernateDAO<Association, Long> implements AssociationDAO {
	private static final String TABLE_CLASS = "Association";
	
	
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
	public Class<Association> getTableClass() {
		return Association.class;
	}
	
}
