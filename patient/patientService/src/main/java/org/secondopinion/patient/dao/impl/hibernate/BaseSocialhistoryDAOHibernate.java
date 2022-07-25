package org.secondopinion.patient.dao.impl.hibernate;

import org.secondopinion.dao.hibernate.BaseHibernateDAO;
import org.secondopinion.patient.dao.SocialhistoryDAO;
import org.secondopinion.patient.dto.Socialhistory;

/**
 * @author Generated Code - Please DO NOT MODIFY.
 * */
public abstract class BaseSocialhistoryDAOHibernate extends BaseHibernateDAO<Socialhistory , Long> implements SocialhistoryDAO {
	private static final String TABLE_CLASS = "Socialhistory ";
	
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
	public Class<Socialhistory > getTableClass() {
		return Socialhistory .class;
	}
	
		
//	/* (non-Javadoc)
//	 * @see com.vcube.dataaccess.dao.hibernate.IHibernateDAO#getCriteria()
//	 */
//	public Criteria getCriteria() {
//		return super.getCriteria(Socialhistory .class);
//	}
}
