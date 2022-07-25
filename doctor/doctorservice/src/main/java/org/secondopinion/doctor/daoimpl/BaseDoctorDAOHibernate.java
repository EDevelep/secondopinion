package org.secondopinion.doctor.daoimpl;


import org.secondopinion.dao.hibernate.BaseHibernateDAO;
import org.secondopinion.doctor.dao.DoctorDAO;
import org.secondopinion.doctor.dto.Doctor;

/**
 * @author Generated Code - Please DO NOT MODIFY.
 * */
public abstract class BaseDoctorDAOHibernate extends BaseHibernateDAO<Doctor, Long> implements DoctorDAO {
	private static final String TABLE_CLASS = "Doctor";
	
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
	public Class<Doctor> getTableClass() {
		return Doctor.class;
	}
	
}
