package org.secondopinion.diagnosticcenter.dao.impl.hibernate;

import java.util.List;
import java.util.Map;


import org.hibernate.Criteria;
import org.hibernate.criterion.Criterion;
import org.secondopinion.dao.hibernate.BaseHibernateDAO;
import org.secondopinion.diagnosticcenter.dao.PatientpaymentdetailsDAO;
import org.secondopinion.diagnosticcenter.dto.Patientpaymentdetails;

/**
 * @author Generated Code - Please DO NOT MODIFY.
 * */
public abstract class BasePatientpaymentdetailsDAOHibernate extends BaseHibernateDAO<Patientpaymentdetails, Long> implements PatientpaymentdetailsDAO {
	private static final String TABLE_CLASS = "Patientpaymentdetails";
	
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
	public Class<Patientpaymentdetails> getTableClass() {
		return Patientpaymentdetails.class;
	}
	
		
//	/* (non-Javadoc)
//	 * @see com.vcube.dataaccess.dao.hibernate.IHibernateDAO#getCriteria()
//	 */
//	public Criteria getCriteria() {
//		return super.getCriteria(Patientpaymentdetails.class);
//	}
}
