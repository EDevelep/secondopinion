package org.secondopinion.patient.dao.impl.hibernate;

import org.secondopinion.patient.dao.MedicalinsuranceDAO;
import org.secondopinion.dao.hibernate.BaseHibernateDAO;
import org.secondopinion.patient.dto.Medicalinsurance;

/**
 * @author Generated Code - Please DO NOT MODIFY.
 * */
public abstract class BaseMedicalinsuranceDAOHibernate extends BaseHibernateDAO<Medicalinsurance, Long> implements MedicalinsuranceDAO {
	private static final String TABLE_CLASS = "Medicalinsurance";
	
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
	public Class<Medicalinsurance> getTableClass() {
		return Medicalinsurance.class;
	}
	
		
//	/* (non-Javadoc)
//	 * @see org.secondopinion.dataaccess.dao.hibernate.IHibernateDAO#getCriteria()
//	 */
//	public Criteria getCriteria() {
//		return super.getCriteria(Medicalinsurance.class);
//	}
}
