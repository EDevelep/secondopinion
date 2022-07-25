package org.secondopinion.doctor.daoimpl;
import org.secondopinion.dao.hibernate.BaseHibernateDAO;
import org.secondopinion.doctor.dao.InvoiceDAO;
import org.secondopinion.doctor.dto.Invoice;

/**
 * @author Generated Code - Please DO NOT MODIFY.
 * */
public abstract class BaseInvoiceDAOHibernate extends BaseHibernateDAO<Invoice, Long> implements InvoiceDAO {
	private static final String TABLE_CLASS = "Invoice";
	
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
	public Class<Invoice> getTableClass() {
		return Invoice.class;
	}
}
