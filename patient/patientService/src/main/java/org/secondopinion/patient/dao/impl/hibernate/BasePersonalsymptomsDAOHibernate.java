package org.secondopinion.patient.dao.impl.hibernate;
import org.secondopinion.dao.hibernate.BaseHibernateDAO;
import org.secondopinion.patient.dao.PersonalsymptomsDAO;
import org.secondopinion.patient.dto.Personalsymptoms;

/**
 * @author Generated Code - Please DO NOT MODIFY.
 * */
public abstract class BasePersonalsymptomsDAOHibernate extends BaseHibernateDAO<Personalsymptoms , Long> implements PersonalsymptomsDAO {
	private static final String TABLE_CLASS = "Personalsymptoms ";
	
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
	public Class<Personalsymptoms > getTableClass() {
		return Personalsymptoms .class;
	}
	
		
//	/* (non-Javadoc)
//	 * @see com.vcube.dataaccess.dao.hibernate.IHibernateDAO#getCriteria()
//	 */
//	public Criteria getCriteria() {
//		return super.getCriteria(Personalsymptoms .class);
//	}
}
