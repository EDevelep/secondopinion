package org.secondopinion.patient.dao.impl.hibernate;

import org.secondopinion.patient.dao.AppointmentDAO;
import org.secondopinion.dao.hibernate.BaseHibernateDAO;
import org.secondopinion.patient.dto.Appointment;

/**
 * @author Generated Code - Please DO NOT MODIFY.
 * */
public abstract class BaseAppointmentDAOHibernate extends BaseHibernateDAO<Appointment, Long> implements AppointmentDAO {
	private static final String TABLE_CLASS = "Appointment";
	
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
	public Class<Appointment> getTableClass() {
		return Appointment.class;
	}
	
		
//	/* (non-Javadoc)
//	 * @see org.secondopinion.dataaccess.dao.hibernate.IHibernateDAO#getCriteria()
//	 */
//	public Criteria getCriteria() {
//		return super.getCriteria(Appointment.class);
//	}
}
