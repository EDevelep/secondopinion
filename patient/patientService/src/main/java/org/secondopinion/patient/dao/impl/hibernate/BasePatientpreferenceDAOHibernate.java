package org.secondopinion.patient.dao.impl.hibernate;


import org.secondopinion.dao.hibernate.BaseHibernateDAO;
import org.secondopinion.patient.dao.PatientpreferenceDAO;
import org.secondopinion.patient.dto.Patientpreference;

/**
 * @author Generated Code - Please DO NOT MODIFY.
 * */
public abstract class BasePatientpreferenceDAOHibernate extends BaseHibernateDAO<Patientpreference, Long> implements PatientpreferenceDAO {
	private static final String TABLE_CLASS = "Patientpreference";
	
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
	public Class<Patientpreference> getTableClass() {
		return Patientpreference.class;
	}
	
		
//	/* (non-Javadoc)
//	 * @see com.vcube.dataaccess.dao.hibernate.IHibernateDAO#getCriteria()
//	 */
//	public Criteria getCriteria() {
//		return super.getCriteria(Patientpreference.class);
//	}
}
