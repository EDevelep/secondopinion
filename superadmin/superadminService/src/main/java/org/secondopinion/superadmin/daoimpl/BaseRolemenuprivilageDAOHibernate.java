package org.secondopinion.superadmin.daoimpl;

import org.secondopinion.dao.hibernate.BaseHibernateDAO;
import org.secondopinion.superadmin.dao.RolemenuprivilageDAO;
import org.secondopinion.superadmin.dto.Rolemenuprivilage;

/**
 * @author Generated Code - Please DO NOT MODIFY.
 * */
public abstract class BaseRolemenuprivilageDAOHibernate extends BaseHibernateDAO<Rolemenuprivilage, Integer> implements RolemenuprivilageDAO {
	private static final String TABLE_CLASS = "Rolemenuprivilage";
	
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
	public Class<Rolemenuprivilage> getTableClass() {
		return Rolemenuprivilage.class;
	}
	
		
//	/* (non-Javadoc)
//	 * @see com.vcube.dataaccess.dao.hibernate.IHibernateDAO#getCriteria()
//	 */
//	public Criteria getCriteria() {
//		return super.getCriteria(Rolemenuprivilage.class);
//	}
}
