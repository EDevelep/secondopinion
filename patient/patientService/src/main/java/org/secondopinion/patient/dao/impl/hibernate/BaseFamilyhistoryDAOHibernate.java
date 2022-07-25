package org.secondopinion.patient.dao.impl.hibernate;

import org.secondopinion.dao.hibernate.BaseHibernateDAO;
import org.secondopinion.patient.dao.FamilyhistoryDAO;
import org.secondopinion.patient.dto.Familyhistory;

/**
 * @author Generated Code - Please DO NOT MODIFY.
 * */
public abstract class BaseFamilyhistoryDAOHibernate extends BaseHibernateDAO<Familyhistory, Long> implements FamilyhistoryDAO {
	private static final String TABLE_CLASS = "Familyhistory";
	
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
	public Class<Familyhistory> getTableClass() {
		return Familyhistory.class;
	}
	
		
//	/* (non-Javadoc)
//	 * @see com.vcube.dataaccess.dao.hibernate.IHibernateDAO#getCriteria()
//	 */
//	public Criteria getCriteria() {
//		return super.getCriteria(Familyhistory.class);
//	}
}
