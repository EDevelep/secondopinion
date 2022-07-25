package org.secondopinion.diagnosticcenter.dao.impl.hibernate;

import org.secondopinion.dao.hibernate.BaseHibernateDAO;
import org.secondopinion.diagnosticcenter.dao.DiagnosticcenteruserDAO;
import org.secondopinion.diagnosticcenter.dto.Diagnosticcenteruser;

/**
 * @author Generated Code - Please DO NOT MODIFY.
 * */
public abstract class BaseDiagnosticcenteruserDAOHibernate extends BaseHibernateDAO<Diagnosticcenteruser, Long> implements DiagnosticcenteruserDAO {
	private static final String TABLE_CLASS = "Diagnosticcenteruser";
	
	
	
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
	public Class<Diagnosticcenteruser> getTableClass() {
		return Diagnosticcenteruser.class;
	}
	
		
//	/* (non-Javadoc)
//	 * @see com.vcube.dataaccess.dao.hibernate.IHibernateDAO#getCriteria()
//	 */
//	public Criteria getCriteria() {
//		return super.getCriteria(Diagnosticcenteruser.class);
//	}
}
