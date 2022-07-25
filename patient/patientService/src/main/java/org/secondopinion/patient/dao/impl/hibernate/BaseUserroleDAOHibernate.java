package org.secondopinion.patient.dao.impl.hibernate;

import org.secondopinion.dao.hibernate.BaseHibernateDAO;
import org.secondopinion.patient.dao.UserroleDAO;
import org.secondopinion.patient.dto.Userrole;

/**
 * @author Generated Code - Please DO NOT MODIFY.
 */
public abstract class BaseUserroleDAOHibernate extends BaseHibernateDAO<Userrole, Long>
    implements UserroleDAO {
  private static final String TABLE_CLASS = "Userrole";

  /*
   * (non-Javadoc)
   * 
   * @see com.vcube.dataaccess.dao.hibernate.BaseHibernateDAO#getTableClassName()
   */
  @Override
  public String getTableClassName() {
    return TABLE_CLASS;
  }

  /*
   * (non-Javadoc)
   * 
   * @see com.vcube.dataaccess.dao.hibernate.BaseHibernateDAO#getTableClass()
   */
  @Override
  public Class<Userrole> getTableClass() {
    return Userrole.class;
  }


  // /* (non-Javadoc)
  // * @see com.vcube.dataaccess.dao.hibernate.IHibernateDAO#getCriteria()
  // */
  // public Criteria getCriteria() {
  // return super.getCriteria(Userrole.class);
  // }
}
