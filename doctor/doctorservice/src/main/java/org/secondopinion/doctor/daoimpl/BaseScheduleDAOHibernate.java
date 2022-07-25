package org.secondopinion.doctor.daoimpl;

import org.secondopinion.dao.hibernate.BaseHibernateDAO;
import org.secondopinion.doctor.dao.ScheduleDAO;
import org.secondopinion.doctor.dto.Schedule;

/**
 * @author Generated Code - Please DO NOT MODIFY.
 * */
public abstract class BaseScheduleDAOHibernate extends BaseHibernateDAO<Schedule, Long> implements ScheduleDAO {
	private static final String TABLE_CLASS = "Schedule";
	
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
	public Class<Schedule> getTableClass() {
		return Schedule.class;
	}
	
}
