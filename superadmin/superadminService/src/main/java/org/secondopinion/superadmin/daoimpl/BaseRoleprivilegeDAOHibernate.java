package org.secondopinion.superadmin.daoimpl;

import java.util.List;
import java.util.Map;

import org.secondopinion.dao.hibernate.BaseHibernateDAO;
import org.secondopinion.superadmin.dao.RoleprivilegeDAO;
import org.secondopinion.superadmin.dto.Roleprivilege;



/**
 * @author Generated Code - Please DO NOT MODIFY.
 * */
public abstract class BaseRoleprivilegeDAOHibernate extends BaseHibernateDAO<Roleprivilege, Integer> implements RoleprivilegeDAO {
	private static final String TABLE_CLASS = "Roleprivilege";
	
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
	public Class<Roleprivilege> getTableClass() {
		return Roleprivilege.class;
	}
	
		
//	/* (non-Javadoc)
//	 * @see com.vcube.dataaccess.dao.hibernate.IHibernateDAO#getCriteria()
//	 */
//	public Criteria getCriteria() {
//		return super.getCriteria(Roleprivilege.class);
//	}
}
