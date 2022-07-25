package org.secondopinion.patient.dao.impl.hibernate;

import org.secondopinion.dao.hibernate.BaseHibernateDAO;
import org.secondopinion.patient.dao.QuestionairreDAO;
import org.secondopinion.patient.dto.Questionairre;

/**
 * @author Generated Code - Please DO NOT MODIFY.
 * */
public abstract class BaseQuestionairreDAOHibernate extends BaseHibernateDAO<Questionairre , Long> implements QuestionairreDAO {
	private static final String TABLE_CLASS = "Questionairre ";
	
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
	public Class<Questionairre > getTableClass() {
		return Questionairre .class;
	}
	
		
//	/* (non-Javadoc)
//	 * @see com.vcube.dataaccess.dao.hibernate.IHibernateDAO#getCriteria()
//	 */
//	public Criteria getCriteria() {
//		return super.getCriteria(Questionairre .class);
//	}
}
