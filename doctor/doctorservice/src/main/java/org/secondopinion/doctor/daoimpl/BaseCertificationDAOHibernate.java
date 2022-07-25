package org.secondopinion.doctor.daoimpl;
import org.secondopinion.dao.hibernate.BaseHibernateDAO;
import org.secondopinion.doctor.dao.CertificationDAO;
import org.secondopinion.doctor.dto.Certification;

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
