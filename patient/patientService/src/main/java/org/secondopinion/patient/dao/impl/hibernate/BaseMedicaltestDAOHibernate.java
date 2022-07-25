package org.secondopinion.patient.dao.impl.hibernate;

import org.secondopinion.dao.hibernate.BaseHibernateDAO;
import org.secondopinion.patient.dao.MedicaltestDAO;
import org.secondopinion.patient.dto.Medicaltest;

/**
 * @author Generated Code - Please DO NOT MODIFY.
 * */
public abstract class BaseMedicaltestDAOHibernate extends BaseHibernateDAO<Medicaltest, Long> implements MedicaltestDAO {
	private static final String TABLE_CLASS = "Medicaltest";
	
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
	public Class<Medicaltest> getTableClass() {
		return Medicaltest.class;
	}
	
		
//	/* (non-Javadoc)
//	 * @see com.vcube.dataaccess.dao.hibernate.IHibernateDAO#getCriteria()
//	 */
//	public Criteria getCriteria() {
//		return super.getCriteria(Medicaltest.class);
//	}
}
