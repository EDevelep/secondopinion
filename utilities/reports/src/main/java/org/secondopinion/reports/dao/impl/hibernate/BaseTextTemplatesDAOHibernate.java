package org.secondopinion.reports.dao.impl.hibernate;

import org.secondopinion.dao.hibernate.BaseHibernateDAO;
import org.secondopinion.reports.dao.TextTemplatesDAO;
import org.secondopinion.reports.dto.TextTemplates;

/**
 * @author Generated Code - Please DO NOT MODIFY.
 * */
public abstract class BaseTextTemplatesDAOHibernate extends BaseHibernateDAO<TextTemplates, Long> implements TextTemplatesDAO {
	private static final String TABLE_CLASS = "TextTemplates";
	
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
	public Class<TextTemplates> getTableClass() {
		return TextTemplates.class;
	}
	
//	/* (non-Javadoc)
//	 * @see com.vcube.dataaccess.dao.hibernate.IHibernateDAO#getCriteria()
//	 */
//	public Criteria getCriteria() {
//		return super.getCriteria(TextTemplates.class);
//	}
}
