package org.secondopinion.doctor.daoimpl;

import org.secondopinion.dao.hibernate.BaseHibernateDAO;
import org.secondopinion.doctor.dao.DoctorratingsDAO;
import org.secondopinion.doctor.dto.Doctorratings;

/**
 * @author Generated Code - Please DO NOT MODIFY.
 * */
public abstract class BaseDoctorratingsDAOHibernate extends BaseHibernateDAO<Doctorratings, Long> implements DoctorratingsDAO {
	private static final String TABLE_CLASS = "Doctorratings";
	
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
	public Class<Doctorratings> getTableClass() {
		return Doctorratings.class;
	}
	
		
}
