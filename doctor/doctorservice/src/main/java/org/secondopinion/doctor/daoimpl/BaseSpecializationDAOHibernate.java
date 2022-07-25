package org.secondopinion.doctor.daoimpl;

import org.secondopinion.dao.hibernate.BaseHibernateDAO;
import org.secondopinion.doctor.dao.SpecializationDAO;
import org.secondopinion.doctor.dto.Specialization;

/**
 * @author Generated Code - Please DO NOT MODIFY.
 * */
public abstract class BaseSpecializationDAOHibernate extends BaseHibernateDAO<Specialization, Long> implements SpecializationDAO {
	private static final String TABLE_CLASS = "Specialization";
	
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
	public Class<Specialization> getTableClass() {
		return Specialization.class;
	}
}
