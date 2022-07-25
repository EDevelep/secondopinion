package org.secondopinion.patient.dao.impl.hibernate;

import java.util.List;
import java.util.Map;



import org.hibernate.Criteria;
import org.hibernate.criterion.Criterion;
import org.secondopinion.dao.hibernate.BaseHibernateDAO;
import org.secondopinion.patient.dao.MedicaltestprescriptionDAO;
import org.secondopinion.patient.dto.Medicaltestprescription;

/**
 * @author Generated Code - Please DO NOT MODIFY.
 * */
public abstract class BaseMedicaltestprescriptionDAOHibernate extends BaseHibernateDAO<Medicaltestprescription, Long> implements MedicaltestprescriptionDAO {
	private static final String TABLE_CLASS = "Medicaltestprescription";
	
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
	public Class<Medicaltestprescription> getTableClass() {
		return Medicaltestprescription.class;
	}
	
		
//	/* (non-Javadoc)
//	 * @see com.vcube.dataaccess.dao.hibernate.IHibernateDAO#getCriteria()
//	 */
//	public Criteria getCriteria() {
//		return super.getCriteria(Medicaltestprescription.class);
//	}
}
