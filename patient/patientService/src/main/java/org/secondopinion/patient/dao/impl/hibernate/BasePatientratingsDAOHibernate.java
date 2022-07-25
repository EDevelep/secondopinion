package org.secondopinion.patient.dao.impl.hibernate;

import org.secondopinion.dao.hibernate.BaseHibernateDAO;
import org.secondopinion.patient.dao.PatientratingsDAO;
import org.secondopinion.patient.dto.Patientratings;

/**
 * @author Generated Code - Please DO NOT MODIFY.
 * */
public abstract class BasePatientratingsDAOHibernate extends BaseHibernateDAO<Patientratings, Long> implements PatientratingsDAO {
	private static final String TABLE_CLASS = "Patientratings";
	
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
	public Class<Patientratings> getTableClass() {
		return Patientratings.class;
	}
	
		
//	/* (non-Javadoc)
//	 * @see com.vcube.dataaccess.dao.hibernate.IHibernateDAO#getCriteria()
//	 */
//	public Criteria getCriteria() {
//		return super.getCriteria(Patientratings.class);
//	}
}
