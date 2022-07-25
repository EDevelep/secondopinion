package org.secondopinion.diagnosticcenter.dao.impl.hibernate;

import org.secondopinion.dao.hibernate.BaseHibernateDAO;
import org.secondopinion.diagnosticcenter.dao.DiagnosticcenteraddressDAO;
import org.secondopinion.diagnosticcenter.dto.Diagnosticcenteraddress;

/**
 * @author Generated Code - Please DO NOT MODIFY.
 * */
public abstract class BaseDiagnosticcenteraddressDAOHibernate extends BaseHibernateDAO<Diagnosticcenteraddress, Long> implements DiagnosticcenteraddressDAO {
	private static final String TABLE_CLASS = "Diagnosticcenteraddress";
	
	
	
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
	public Class<Diagnosticcenteraddress> getTableClass() {
		return Diagnosticcenteraddress.class;
	}
	
		
//	/* (non-Javadoc)
//	 * @see com.vcube.dataaccess.dao.hibernate.IHibernateDAO#getCriteria()
//	 */
//	public Criteria getCriteria() {
//		return super.getCriteria(Diagnosticcenteraddress.class);
//	}
}
