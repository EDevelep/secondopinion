package org.secondopinion.caretaker.dao.impl;

import org.secondopinion.caretaker.dao.ScheduleDAO;
import org.secondopinion.caretaker.dto.Schedule;
import org.secondopinion.dao.hibernate.BaseHibernateDAO;

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
