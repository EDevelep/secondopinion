package org.secondopinion.doctor.daoimpl;

import org.secondopinion.dao.hibernate.BaseHibernateDAO;
import org.secondopinion.doctor.dao.DoctorfcmtokenDAO;
import org.secondopinion.doctor.dto.Doctorfcmtoken;

/**
 * @author Generated Code - Please DO NOT MODIFY.
 * */
public abstract class BaseDoctorfcmtokenDAOHibernate extends BaseHibernateDAO<Doctorfcmtoken, Long> implements DoctorfcmtokenDAO {
	private static final String TABLE_CLASS = "Doctorfcmtoken";
	
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
	public Class<Doctorfcmtoken> getTableClass() {
		return Doctorfcmtoken.class;
	}
	
}
