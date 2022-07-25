package org.secondopinion.diagnosticcenter.dao.impl.hibernate;

import java.util.List;
import java.util.Map;



import org.hibernate.Criteria;
import org.hibernate.criterion.Criterion;
import org.secondopinion.dao.hibernate.BaseHibernateDAO;
import org.secondopinion.diagnosticcenter.dao.CarddetailsDAO;
import org.secondopinion.diagnosticcenter.dto.Carddetails;

/**
 * @author Generated Code - Please DO NOT MODIFY.
 * */
public abstract class BaseCarddetailsDAOHibernate extends BaseHibernateDAO<Carddetails, Long> implements CarddetailsDAO {
	private static final String TABLE_CLASS = "Carddetails";
	
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
	public Class<Carddetails> getTableClass() {
		return Carddetails.class;
	}
	
		
//	/* (non-Javadoc)
//	 * @see com.vcube.dataaccess.dao.hibernate.IHibernateDAO#getCriteria()
//	 */
//	public Criteria getCriteria() {
//		return super.getCriteria(Carddetails.class);
//	}
}
