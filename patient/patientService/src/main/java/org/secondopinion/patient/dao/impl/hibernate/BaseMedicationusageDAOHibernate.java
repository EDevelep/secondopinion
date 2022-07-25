package org.secondopinion.patient.dao.impl.hibernate;

import java.util.List;
import java.util.Map;



import org.hibernate.Criteria;
import org.hibernate.criterion.Criterion;
import org.secondopinion.dao.hibernate.BaseHibernateDAO;
import org.secondopinion.patient.dao.MedicationusageDAO;
import org.secondopinion.patient.dto.Medicationusage;

/**
 * @author Generated Code - Please DO NOT MODIFY.
 * */
public abstract class BaseMedicationusageDAOHibernate extends BaseHibernateDAO<Medicationusage, Long> implements MedicationusageDAO {
	private static final String TABLE_CLASS = "Medicationusage";
	
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
	public Class<Medicationusage> getTableClass() {
		return Medicationusage.class;
	}
	
		
//	/* (non-Javadoc)
//	 * @see com.vcube.dataaccess.dao.hibernate.IHibernateDAO#getCriteria()
//	 */
//	public Criteria getCriteria() {
//		return super.getCriteria(Medicationusage.class);
//	}
}
