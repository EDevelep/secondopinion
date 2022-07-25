package org.secondopinion.patient.dao.impl.hibernate;

import org.secondopinion.dao.hibernate.BaseHibernateDAO;
import org.secondopinion.patient.dao.SurgerydetailsDAO;
import org.secondopinion.patient.dto.Surgerydetails;

/**
 * @author Generated Code - Please DO NOT MODIFY.
 * */
public abstract class BaseSurgerydetailsDAOHibernate extends BaseHibernateDAO<Surgerydetails, Long> implements SurgerydetailsDAO {
	private static final String TABLE_CLASS = "Surgerydetails";
	
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
	public Class<Surgerydetails> getTableClass() {
		return Surgerydetails.class;
	}
	
		
//	/* (non-Javadoc)
//	 * @see com.vcube.dataaccess.dao.hibernate.IHibernateDAO#getCriteria()
//	 */
//	public Criteria getCriteria() {
//		return super.getCriteria(Surgerydetails.class);
//	}
}
