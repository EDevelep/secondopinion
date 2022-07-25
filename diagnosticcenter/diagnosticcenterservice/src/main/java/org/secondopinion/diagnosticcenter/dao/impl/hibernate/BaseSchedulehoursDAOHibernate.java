package org.secondopinion.diagnosticcenter.dao.impl.hibernate;


import org.secondopinion.dao.hibernate.BaseHibernateDAO;
import org.secondopinion.diagnosticcenter.dao.SchedulehoursDAO;
import org.secondopinion.diagnosticcenter.dto.Schedulehours;

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
	
		
//	/* (non-Javadoc)
//	 * @see org.secondopinion.dataaccess.dao.hibernate.IHibernateDAO#getCriteria()
//	 */
//	public Criteria getCriteria() {
//		return super.getCriteria(Schedulehours.class);
//	}
}
