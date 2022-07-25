package org.secondopinion.pharmacy.dao.impl.hibernate;

import org.secondopinion.dao.hibernate.BaseHibernateDAO;
import org.secondopinion.pharmacy.dao.PrescriptionfillrequestDAO;
import org.secondopinion.pharmacy.dto.Prescriptionfillrequest;

/**
 * @author Generated Code - Please DO NOT MODIFY.
 * */
public abstract class BasePrescriptionfillrequestDAOHibernate extends BaseHibernateDAO<Prescriptionfillrequest, Long> implements PrescriptionfillrequestDAO {
	private static final String TABLE_CLASS = "Prescriptionfillrequest";
	
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
	public Class<Prescriptionfillrequest> getTableClass() {
		return Prescriptionfillrequest.class;
	}
	
		
//	/* (non-Javadoc)
//	 * @see com.vcube.dataaccess.dao.hibernate.IHibernateDAO#getCriteria()
//	 */
//	public Criteria getCriteria() {
//		return super.getCriteria(Prescriptionfillrequest.class);
//	}
}
