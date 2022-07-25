package org.secondopinion.doctor.daoimpl;

import org.secondopinion.dao.hibernate.BaseHibernateDAO;
import org.secondopinion.doctor.dao.AppointmentnotesDAO;
import org.secondopinion.doctor.dto.Appointmentnotes;

/**
 * @author Generated Code - Please DO NOT MODIFY.
 * */
public abstract class BaseAppointmentnotesDAOHibernate extends BaseHibernateDAO<Appointmentnotes, Long> implements AppointmentnotesDAO {
	private static final String TABLE_CLASS = "Appointmentnotes";
	
	
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
	public Class<Appointmentnotes> getTableClass() {
		return Appointmentnotes.class;
	}
	
		
}
