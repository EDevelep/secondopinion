package org.secondopinion.utilities.feedbackreport.dao.impl;

import org.secondopinion.dao.hibernate.BaseHibernateDAO;
import org.secondopinion.utilities.feedbackreport.dao.FeedbackPngDAO;
import org.secondopinion.utilities.feedbackreport.dto.FeedbackPng;

/**
 * @author Generated Code - Please DO NOT MODIFY.
 * */
public abstract class BaseFeedbackPngDAOHibernate extends BaseHibernateDAO<FeedbackPng, Integer> implements FeedbackPngDAO {
	private static final String TABLE_CLASS = "FeedbackPng";
	
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
	public Class<FeedbackPng> getTableClass() {
		return FeedbackPng.class;
	}
	
//	/* (non-Javadoc)
//	 * @see org.secondopinion.dataaccess.dao.hibernate.IHibernateDAO#getCriteria()
//	 */
//	public Criteria getCriteria() {
//		return super.getCriteria(UserRole.class);
//	}
}
