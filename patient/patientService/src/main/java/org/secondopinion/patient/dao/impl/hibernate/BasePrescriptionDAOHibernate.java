package org.secondopinion.patient.dao.impl.hibernate;

import java.util.List;
import java.util.Map;



import org.hibernate.Criteria;
import org.hibernate.criterion.Criterion;
import org.secondopinion.dao.hibernate.BaseHibernateDAO;
import org.secondopinion.patient.dao.PrescriptionDAO;
import org.secondopinion.patient.dto.Prescription;

/**
 * @author Generated Code - Please DO NOT MODIFY.
 * */
public abstract class BasePrescriptionDAOHibernate extends BaseHibernateDAO<Prescription, Long> implements PrescriptionDAO {
	private static final String TABLE_CLASS = "Prescription";
	
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
	public Class<Prescription> getTableClass() {
		return Prescription.class;
	}
	
		
//	/* (non-Javadoc)
//	 * @see com.vcube.dataaccess.dao.hibernate.IHibernateDAO#getCriteria()
//	 */
//	public Criteria getCriteria() {
//		return super.getCriteria(Prescription.class);
//	}
}
