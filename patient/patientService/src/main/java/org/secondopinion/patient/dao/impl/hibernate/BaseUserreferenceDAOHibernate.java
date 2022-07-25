package org.secondopinion.patient.dao.impl.hibernate;

import java.util.List;
import java.util.Map;



import org.hibernate.Criteria;
import org.hibernate.criterion.Criterion;
import org.secondopinion.dao.hibernate.BaseHibernateDAO;
import org.secondopinion.patient.dao.UserreferenceDAO;
import org.secondopinion.patient.dto.Userreference;

/**
 * @author Generated Code - Please DO NOT MODIFY.
 * */
public abstract class BaseUserreferenceDAOHibernate extends BaseHibernateDAO<Userreference, Long> implements UserreferenceDAO {
	private static final String TABLE_CLASS = "Userreference";
	
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
	public Class<Userreference> getTableClass() {
		return Userreference.class;
	}
	
		
//	/* (non-Javadoc)
//	 * @see com.vcube.dataaccess.dao.hibernate.IHibernateDAO#getCriteria()
//	 */
//	public Criteria getCriteria() {
//		return super.getCriteria(Userreference.class);
//	}
}
