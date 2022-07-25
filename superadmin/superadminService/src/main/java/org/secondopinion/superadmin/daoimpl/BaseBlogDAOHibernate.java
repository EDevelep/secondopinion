package org.secondopinion.superadmin.daoimpl;

import java.util.List;
import java.util.Map;



import org.hibernate.Criteria;
import org.hibernate.criterion.Criterion;
import org.secondopinion.dao.hibernate.BaseHibernateDAO;
import org.secondopinion.superadmin.dao.BlogDAO;
import org.secondopinion.superadmin.dto.Blog;

/**
 * @author Generated Code - Please DO NOT MODIFY.
 * */
public abstract class BaseBlogDAOHibernate extends BaseHibernateDAO<Blog, Long> implements BlogDAO {
	private static final String TABLE_CLASS = "Blog";
	
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
	public Class<Blog> getTableClass() {
		return Blog.class;
	}
	
		
//	/* (non-Javadoc)
//	 * @see com.vcube.dataaccess.dao.hibernate.IHibernateDAO#getCriteria()
//	 */
//	public Criteria getCriteria() {
//		return super.getCriteria(Blog.class);
//	}
}
