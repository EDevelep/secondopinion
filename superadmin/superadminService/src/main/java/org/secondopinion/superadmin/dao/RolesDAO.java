package org.secondopinion.superadmin.dao;

import java.util.List;
import org.secondopinion.dao.IDAO;
import org.secondopinion.superadmin.dto.Roles;



public interface RolesDAO extends IDAO<Roles, Integer> {
  List<Roles> getAllAvailableRoles(Integer doctorId);

  List<Roles> getRoles(String uName);

  Roles getRoleByName(String role);

  List<Roles> getByUserroles(List<Integer> roleIds);
  // Roles getByRoleName(String name);

}
