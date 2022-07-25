package org.secondopinion.pharmacy.dao.impl.hibernate;




import org.secondopinion.dao.hibernate.BaseHibernateDAO;
import org.secondopinion.pharmacy.dao.ShippingaddressDAO;
import org.secondopinion.pharmacy.dto.Shippingaddress;

/**
 * @author Generated Code - Please DO NOT MODIFY.
 * */
public abstract class BaseShippingaddressDAOHibernate extends BaseHibernateDAO<Shippingaddress, Long> implements ShippingaddressDAO {
	private static final String TABLE_CLASS = "Shippingaddress";
	
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
	public Class<Shippingaddress> getTableClass() {
		return Shippingaddress.class;
	}
	
		
//	/* (non-Javadoc)
//	 * @see com.vcube.dataaccess.dao.hibernate.IHibernateDAO#getCriteria()
//	 */
//	public Criteria getCriteria() {
//		return super.getCriteria(Shippingaddress.class);
//	}
}
