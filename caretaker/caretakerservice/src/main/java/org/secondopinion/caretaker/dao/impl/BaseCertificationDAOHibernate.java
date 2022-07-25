package org.secondopinion.caretaker.dao.impl;
import org.secondopinion.caretaker.dao.CertificationDAO;
import org.secondopinion.caretaker.dto.Certification;
import org.secondopinion.dao.hibernate.BaseHibernateDAO;


/**
 * @author Generated Code - Please DO NOT MODIFY.
 * */
public abstract class BaseCertificationDAOHibernate extends BaseHibernateDAO<Certification, Long> implements CertificationDAO {
	private static final String TABLE_CLASS = "Certification";
	
	
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
	public Class<Certification> getTableClass() {
		return Certification.class;
	}
	
}
