package org.secondopinion.superadmin.daoimpl;

import java.util.List;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
import org.secondopinion.superadmin.dto.Userrole;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class UserroleDAOHibernateImpl extends BaseUserroleDAOHibernate {

  @Override
  @Transactional
  public List<Userrole> getByRoleIdAndUserId(Long userId) {
    Criterion criterion = Restrictions.eq(Userrole.FIELD_userId, userId);

    List<Userrole> roles = findByCrieria(criterion);

    return roles;
  }
}
