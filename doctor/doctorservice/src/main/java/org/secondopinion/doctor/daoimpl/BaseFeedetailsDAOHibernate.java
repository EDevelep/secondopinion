package org.secondopinion.doctor.daoimpl;


import org.secondopinion.dao.hibernate.BaseHibernateDAO;
import org.secondopinion.doctor.dao.FeedetailsDAO;
import org.secondopinion.doctor.dto.Feedetails;

/**
 * @author Generated Code - Please DO NOT MODIFY.
 * */
public abstract class BaseFeedetailsDAOHibernate extends BaseHibernateDAO<Feedetails, Long> implements FeedetailsDAO {
	private static final String TABLE_CLASS = "Feedetails";
	
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
	public Class<Feedetails> getTableClass() {
		return Feedetails.class;
	}
	
}
