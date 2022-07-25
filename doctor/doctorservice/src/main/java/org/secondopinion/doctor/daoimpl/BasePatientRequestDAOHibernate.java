package org.secondopinion.doctor.daoimpl;

import org.secondopinion.dao.hibernate.BaseHibernateDAO;
import org.secondopinion.doctor.dao.PatientRequestDAO;
import org.secondopinion.doctor.dto.PatientRequest;

/**
 * @author Generated Code - Please DO NOT MODIFY.
 * */
public abstract class BasePatientRequestDAOHibernate extends BaseHibernateDAO<PatientRequest, Long> implements PatientRequestDAO {
	private static final String TABLE_CLASS = "Doctorpatientassociation";
	
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
	public Class<PatientRequest> getTableClass() {
		return PatientRequest.class;
	}
	
}
