package org.secondopinion.patient.dao.impl.hibernate;

import org.secondopinion.dao.hibernate.BaseHibernateDAO;
import org.secondopinion.patient.dao.MedicalprescriptionDAO;
import org.secondopinion.patient.dto.Medicalprescription;

/**
 * @author Generated Code - Please DO NOT MODIFY.
 * */
public abstract class BaseMedicalprescriptionDAOHibernate extends BaseHibernateDAO<Medicalprescription, Long> implements MedicalprescriptionDAO {
	private static final String TABLE_CLASS = "Medicalprescription";
	
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
	public Class<Medicalprescription> getTableClass() {
		return Medicalprescription.class;
	}
	
		
//	/* (non-Javadoc)
//	 * @see com.vcube.dataaccess.dao.hibernate.IHibernateDAO#getCriteria()
//	 */
//	public Criteria getCriteria() {
//		return super.getCriteria(Medicalprescription.class);
//	}
}
