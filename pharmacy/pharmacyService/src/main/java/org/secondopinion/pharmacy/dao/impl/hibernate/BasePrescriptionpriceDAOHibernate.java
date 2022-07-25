package org.secondopinion.pharmacy.dao.impl.hibernate;

import org.secondopinion.dao.hibernate.BaseHibernateDAO;
import org.secondopinion.pharmacy.dao.PrescriptionpriceDAO;
import org.secondopinion.pharmacy.dto.Prescriptionprice;

/**
 * @author Generated Code - Please DO NOT MODIFY.
 * */
public abstract class BasePrescriptionpriceDAOHibernate extends BaseHibernateDAO<Prescriptionprice, Long> implements PrescriptionpriceDAO {
	private static final String TABLE_CLASS = "Prescriptionprice";
	
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
	public Class<Prescriptionprice> getTableClass() {
		return Prescriptionprice.class;
	}
	
		
//	/* (non-Javadoc)
//	 * @see com.vcube.dataaccess.dao.hibernate.IHibernateDAO#getCriteria()
//	 */
//	public Criteria getCriteria() {
//		return super.getCriteria(Prescriptionprice.class);
//	}
}
