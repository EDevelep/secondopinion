package org.secondopinion.doctor.daoimpl;

import java.util.List;
import java.util.Map;



import org.hibernate.Criteria;
import org.hibernate.criterion.Criterion;
import org.secondopinion.dao.hibernate.BaseHibernateDAO;
import org.secondopinion.doctor.dao.StaticdataDAO;
import org.secondopinion.doctor.dto.Staticdata;

/**
 * @author Generated Code - Please DO NOT MODIFY.
 * */
public abstract class BaseStaticdataDAOHibernate extends BaseHibernateDAO<Staticdata, Long> implements StaticdataDAO {
	private static final String TABLE_CLASS = "Staticdata";
	
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
	public Class<Staticdata> getTableClass() {
		return Staticdata.class;
	}
	
		
//	/* (non-Javadoc)
//	 * @see com.vcube.dataaccess.dao.hibernate.IHibernateDAO#getCriteria()
//	 */
//	public Criteria getCriteria() {
//		return super.getCriteria(Staticdata.class);
//	}
}
