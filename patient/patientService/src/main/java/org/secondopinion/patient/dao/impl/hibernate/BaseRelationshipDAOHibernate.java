package org.secondopinion.patient.dao.impl.hibernate;

import org.secondopinion.patient.dao.RelationshipDAO;
import org.secondopinion.dao.hibernate.BaseHibernateDAO;
import org.secondopinion.patient.dto.Relationship;

/**
 * @author Generated Code - Please DO NOT MODIFY.
 * */
public abstract class BaseRelationshipDAOHibernate extends BaseHibernateDAO<Relationship, Long> implements RelationshipDAO {
	private static final String TABLE_CLASS = "Relationship";
	
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
	public Class<Relationship> getTableClass() {
		return Relationship.class;
	}
	
		
//	/* (non-Javadoc)
//	 * @see org.secondopinion.dataaccess.dao.hibernate.IHibernateDAO#getCriteria()
//	 */
//	public Criteria getCriteria() {
//		return super.getCriteria(Relationship.class);
//	}
}
