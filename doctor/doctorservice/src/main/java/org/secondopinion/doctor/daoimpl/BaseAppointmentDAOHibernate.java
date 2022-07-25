package org.secondopinion.doctor.daoimpl;

import org.secondopinion.dao.hibernate.BaseHibernateDAO;
import org.secondopinion.doctor.dao.AppointmentDAO;
import org.secondopinion.doctor.dto.Appointment;

/**
 * @author Generated Code - Please DO NOT MODIFY.
 */
public abstract class BaseAppointmentDAOHibernate extends BaseHibernateDAO<Appointment, Long>
		implements AppointmentDAO {
	private static final String TABLE_CLASS = "Appointment";

	
	/*
	 * (non-Javadoc)
	 * 
	 * @see org.secondopinion.dataaccess.dao.hibernate.BaseHibernateDAO#getTableClassName()
	 */
	@Override
	public String getTableClassName() {
		return TABLE_CLASS;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.secondopinion.dataaccess.dao.hibernate.BaseHibernateDAO#getTableClass()
	 */
	@Override
	public Class<Appointment> getTableClass() {
		return Appointment.class;
	}
}
