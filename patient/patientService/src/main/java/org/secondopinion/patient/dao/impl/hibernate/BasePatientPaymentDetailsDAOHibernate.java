package org.secondopinion.patient.dao.impl.hibernate;

import org.secondopinion.dao.hibernate.BaseHibernateDAO;
import org.secondopinion.patient.dao.PatientPaymentDetailsDAO;
import org.secondopinion.patient.dto.PatientPaymentDetails;

/**
 * @author Generated Code - Please DO NOT MODIFY.
 * */
public abstract class BasePatientPaymentDetailsDAOHibernate extends BaseHibernateDAO<PatientPaymentDetails, Long> implements PatientPaymentDetailsDAO {
	private static final String TABLE_CLASS = "PatientPaymentDetails";
	
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
	public Class<PatientPaymentDetails> getTableClass() {
		return PatientPaymentDetails.class;
	}
	
		
//	/* (non-Javadoc)
//	 * @see com.vcube.dataaccess.dao.hibernate.IHibernateDAO#getCriteria()
//	 */
//	public Criteria getCriteria() {
//		return super.getCriteria(Customerdetails.class);
//	}
}
