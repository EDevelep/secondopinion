package org.secondopinion.doctor.daoimpl;

import org.secondopinion.dao.hibernate.BaseHibernateDAO;
import org.secondopinion.doctor.dao.DoctorAddressDAO;
import org.secondopinion.doctor.dto.DoctorAddress;

public abstract class BaseDoctorAddressDAOHibernateImpl extends BaseHibernateDAO<DoctorAddress, Long> implements DoctorAddressDAO {
		private static final String TABLE_CLASS = "DoctorAddress";
		
		
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
		public Class<DoctorAddress> getTableClass() {
			return DoctorAddress.class;
		}
		
}
