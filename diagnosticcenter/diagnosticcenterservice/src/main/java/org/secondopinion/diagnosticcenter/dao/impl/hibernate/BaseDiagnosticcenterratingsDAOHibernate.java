package org.secondopinion.diagnosticcenter.dao.impl.hibernate;

import org.secondopinion.dao.hibernate.BaseHibernateDAO;
import org.secondopinion.diagnosticcenter.dao.DiagnosticcenterratingsDAO;
import org.secondopinion.diagnosticcenter.dto.Diagnosticcenterratings;


/**
 * @author Generated Code - Please DO NOT MODIFY.
 * */
public abstract class BaseDiagnosticcenterratingsDAOHibernate extends BaseHibernateDAO<Diagnosticcenterratings, Long> implements DiagnosticcenterratingsDAO {
	private static final String TABLE_CLASS = "Diagnosticcenterratings";
	
	
	
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
	public Class<Diagnosticcenterratings> getTableClass() {
		return Diagnosticcenterratings.class;
	}
	
		
//	/* (non-Javadoc)
//	 * @see com.vcube.dataaccess.dao.hibernate.IHibernateDAO#getCriteria()
//	 */
//	public Criteria getCriteria() {
//		return super.getCriteria(Pharmacyratings.class);
//	}
}
