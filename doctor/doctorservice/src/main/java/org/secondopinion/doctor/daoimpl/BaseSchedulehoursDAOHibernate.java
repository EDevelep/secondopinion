package org.secondopinion.doctor.daoimpl;


import org.secondopinion.dao.hibernate.BaseHibernateDAO;
import org.secondopinion.doctor.dao.SchedulehoursDAO;
import org.secondopinion.doctor.dto.Schedulehours;

/**
 * @author Generated Code - Please DO NOT MODIFY.
 * */
public abstract class BaseSchedulehoursDAOHibernate extends BaseHibernateDAO<Schedulehours, Long> implements SchedulehoursDAO {
	private static final String TABLE_CLASS = "Schedulehours";
	
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
	public Class<Schedulehours> getTableClass() {
		return Schedulehours.class;
	}
	
}
