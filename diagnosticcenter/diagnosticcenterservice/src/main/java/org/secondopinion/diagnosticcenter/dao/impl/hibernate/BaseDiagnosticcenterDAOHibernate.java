package org.secondopinion.diagnosticcenter.dao.impl.hibernate;

import org.secondopinion.dao.hibernate.BaseHibernateDAO;
import org.secondopinion.diagnosticcenter.dao.DiagnosticcenterDAO;
import org.secondopinion.diagnosticcenter.dto.Diagnosticcenter;

/**
 * @author Generated Code - Please DO NOT MODIFY.
 * */
public abstract class BaseDiagnosticcenterDAOHibernate extends BaseHibernateDAO<Diagnosticcenter, Long> implements DiagnosticcenterDAO {
	private static final String TABLE_CLASS = "Diagnosticcenter";
	
	
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
	public Class<Diagnosticcenter> getTableClass() {
		return Diagnosticcenter.class;
	}
	
		
//	/* (non-Javadoc)
//	 * @see com.vcube.dataaccess.dao.hibernate.IHibernateDAO#getCriteria()
//	 */
//	public Criteria getCriteria() {
//		return super.getCriteria(Diagnosticcenter.class);
//	}
}
