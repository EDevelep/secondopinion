package org.secondopinion.doctor.daoimpl;

import org.secondopinion.dao.hibernate.BaseHibernateDAO;
import org.secondopinion.doctor.dao.PersonaldetailDAO;
import org.secondopinion.doctor.dto.Personaldetail;

/**
 * @author Generated Code - Please DO NOT MODIFY.
 * */
public abstract class BasePersonaldetailDAOHibernate extends BaseHibernateDAO<Personaldetail, Long> implements PersonaldetailDAO {
	private static final String TABLE_CLASS = "Personaldetail";
	
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
	public Class<Personaldetail> getTableClass() {
		return Personaldetail.class;
	}
	
}
