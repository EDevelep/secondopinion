package org.secondopinion.patient.dao.impl.hibernate;

import org.secondopinion.patient.dao.PersonaldetailDAO;
import org.secondopinion.dao.hibernate.BaseHibernateDAO;
import org.secondopinion.patient.dto.Personaldetail;

/**
 * @author Generated Code - Please DO NOT MODIFY.
 * */
public abstract class BasePersonaldetailDAOHibernate extends BaseHibernateDAO<Personaldetail, Long> implements PersonaldetailDAO {
	private static final String TABLE_CLASS = "Personaldetail";
	
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
	public Class<Personaldetail> getTableClass() {
		return Personaldetail.class;
	}
	
		
//	/* (non-Javadoc)
//	 * @see org.secondopinion.dataaccess.dao.hibernate.IHibernateDAO#getCriteria()
//	 */
//	public Criteria getCriteria() {
//		return super.getCriteria(Personaldetail.class);
//	}
}
