package org.secondopinion.patient.dao.impl.hibernate;

import org.secondopinion.patient.dao.MedicalreportsDAO;
import org.secondopinion.dao.hibernate.BaseHibernateDAO;
import org.secondopinion.patient.dto.Medicalreports;

/**
 * @author Generated Code - Please DO NOT MODIFY.
 * */
public abstract class BaseMedicalreportsDAOHibernate extends BaseHibernateDAO<Medicalreports, Long> implements MedicalreportsDAO {
	private static final String TABLE_CLASS = "Medicalreports";
	
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
	public Class<Medicalreports> getTableClass() {
		return Medicalreports.class;
	}
	
		
//	/* (non-Javadoc)
//	 * @see org.secondopinion.dataaccess.dao.hibernate.IHibernateDAO#getCriteria()
//	 */
//	public Criteria getCriteria() {
//		return super.getCriteria(Medicalreports.class);
//	}
}
