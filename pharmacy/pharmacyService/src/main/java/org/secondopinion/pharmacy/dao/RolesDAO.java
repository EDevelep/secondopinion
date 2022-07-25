package org.secondopinion.pharmacy.dao; 

import java.util.List;

import org.secondopinion.dao.IDAO;
import org.secondopinion.pharmacy.dto.Roles;

public interface RolesDAO extends IDAO<Roles,Integer >{

	Roles getByRoleName(String name);

	List<Roles> getByUserroles(List<Integer> roleIds);
}