package org.secondopinion.caretaker.dao.impl;


import org.secondopinion.caretaker.dao.FeedetailsDAO;
import org.secondopinion.caretaker.dto.Feedetails;
import org.secondopinion.dao.hibernate.BaseHibernateDAO;


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
