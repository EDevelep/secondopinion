package org.secondopinion.caretaker.dao.impl;

import java.util.List;
import java.util.Map;



import org.hibernate.Criteria;
import org.hibernate.criterion.Criterion;
import org.secondopinion.caretaker.dao.PersonaldetailDAO;
import org.secondopinion.caretaker.dto.Personaldetail;
import org.secondopinion.dao.hibernate.BaseHibernateDAO;

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
	
		
//	/* (non-Javadoc)
//	 * @see com.vcube.dataaccess.dao.hibernate.IHibernateDAO#getCriteria()
//	 */
//	public Criteria getCriteria() {
//		return super.getCriteria(Personaldetail.class);
//	}
}
