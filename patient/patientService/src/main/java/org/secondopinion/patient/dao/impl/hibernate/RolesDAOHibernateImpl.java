package org.secondopinion.patient.dao.impl.hibernate;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
import org.hibernate.type.StandardBasicTypes;
import org.hibernate.type.Type;
import org.secondopinion.patient.domain.BaseRoles;
import org.secondopinion.patient.dto.Roles;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;;

@Repository
public class RolesDAOHibernateImpl extends BaseRolesDAOHibernate {

  @Override
  public List<Roles> getAllAvailableRoles(Integer companyId) {
    return findAll();
  }

  private final String user_roles =
      "select a.* from roles a, userrole b where a.roleId = b.roleId and b.userId = :USER_ID";

  @Override
  public List<Roles> getRoles(String userName) {
    Map<String, Object> params = new HashMap<String, Object>();
    params.put("USER_ID", userName);

    return findBySqlQuery(user_roles, params, null, buildScalarMapForRoles());
  }

  @Override
  @Transactional
  public void save(Roles roles) {

    if (Objects.isNull(roles.getRoleId())) {
      roles.setActive('Y');
    }

    super.save(roles);
  }

  @Override
  public Roles getRoleByName(String role) {
    Criterion criterion = Restrictions.eq(BaseRoles.FIELD_roleName, role);

    List<Roles> roles = findByCrieria(criterion);

    if (roles != null && roles.size() > 0) {
      return roles.get(0);
    }
    return null;
  }

  private Map<String, Type> buildScalarMapForRoles() {
    Map<String, Type> scalarMapping = new HashMap<>();
    scalarMapping.put(BaseRoles.FIELD_roleId, StandardBasicTypes.INTEGER);
    scalarMapping.put(BaseRoles.FIELD_roleName, StandardBasicTypes.STRING);
    scalarMapping.put(BaseRoles.FIELD_active, StandardBasicTypes.STRING);
    scalarMapping.put(BaseRoles.FIELD_createdBy, StandardBasicTypes.STRING);
    scalarMapping.put(BaseRoles.FIELD_createdDate, StandardBasicTypes.DATE);
    scalarMapping.put(BaseRoles.FIELD_lastUpdatedBy, StandardBasicTypes.STRING);
    scalarMapping.put(BaseRoles.FIELD_lastUpdatedTs, StandardBasicTypes.TIMESTAMP);
    return scalarMapping;
  }

}
