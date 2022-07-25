package org.secondopinion.diagnosticcenter.dao.impl.hibernate;

import org.secondopinion.dao.hibernate.BaseHibernateDAO;
import org.secondopinion.diagnosticcenter.dao.CouponDAO;
import org.secondopinion.diagnosticcenter.dto.Coupon;

/**
 * @author Generated Code - Please DO NOT MODIFY.
 * */
public abstract class BaseCouponDAOHibernate extends BaseHibernateDAO<Coupon, Long> implements CouponDAO {
	private static final String TABLE_CLASS = "Coupon";
	
	
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
	public Class<Coupon> getTableClass() {
		return Coupon.class;
	}
	
		
//	/* (non-Javadoc)
//	 * @see com.vcube.dataaccess.dao.hibernate.IHibernateDAO#getCriteria()
//	 */
//	public Criteria getCriteria() {
//		return super.getCriteria(Coupon.class);
//	}
}
