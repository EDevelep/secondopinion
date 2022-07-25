package org.secondopinion.utilities.feedbackreport.dao.impl;

import org.secondopinion.dao.hibernate.BaseHibernateDAO;
import org.secondopinion.utilities.feedbackreport.dao.FeedbackLoopDAO;
import org.secondopinion.utilities.feedbackreport.dto.FeedbackLoop;

/**
 * @author Generated Code - Please DO NOT MODIFY.
 * */
public abstract class BaseFeedbackLoopDAOHibernate extends BaseHibernateDAO<FeedbackLoop, Long> implements FeedbackLoopDAO {
	private static final String TABLE_CLASS = "FeedbackLoop";
	
	/* (non-Javadoc)
	 * @see org.secondopinion.dataaccess.dao.hibernate.BaseHibernateDAO#getTableClassName()
	 */
	@Override
	public String getTableClassName() {
		return TABLE_CLASS;
	}
	
	/* (non-Javadoc)
	 * @see org.secondopinion.dataaccess.dao.hibernate.BaseHibernateDAO#getTableClass()
	 */
	@Override
	public Class<FeedbackLoop> getTableClass() {
		return FeedbackLoop.class;
	}
	
//	/* (non-Javadoc)
//	 * @see org.secondopinion.dataaccess.dao.hibernate.IHibernateDAO#getCriteria()
//	 */
//	public Criteria getCriteria() {
//		return super.getCriteria(FeedbackLoop.class);
//	}
}
