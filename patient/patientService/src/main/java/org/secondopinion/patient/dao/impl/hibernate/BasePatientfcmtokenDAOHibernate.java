package org.secondopinion.patient.dao.impl.hibernate;

import org.secondopinion.dao.hibernate.BaseHibernateDAO;
import org.secondopinion.patient.dao.PatientfcmtokenDAO;
import org.secondopinion.patient.dto.Patientfcmtoken;

/**
 * @author Generated Code - Please DO NOT MODIFY.
 * */
public abstract class BasePatientfcmtokenDAOHibernate extends BaseHibernateDAO<Patientfcmtoken, Long> implements PatientfcmtokenDAO {
	private static final String TABLE_CLASS = "Patientfcmtoken";
	
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
	public Class<Patientfcmtoken> getTableClass() {
		return Patientfcmtoken.class;
	}
	
		
//	/* (non-Javadoc)
//	 * @see com.vcube.dataaccess.dao.hibernate.IHibernateDAO#getCriteria()
//	 */
//	public Criteria getCriteria() {
//		return super.getCriteria(Patientfcmtoken.class);
//	}
}
