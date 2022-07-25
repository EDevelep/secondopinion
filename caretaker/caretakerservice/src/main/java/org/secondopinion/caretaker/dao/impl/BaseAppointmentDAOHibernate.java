package org.secondopinion.caretaker.dao.impl;

import org.secondopinion.caretaker.dao.AppointmentDAO;
import org.secondopinion.caretaker.dto.Appointment;
import org.secondopinion.dao.hibernate.BaseHibernateDAO;

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
