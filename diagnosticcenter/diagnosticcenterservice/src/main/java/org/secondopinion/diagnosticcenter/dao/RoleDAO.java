package org.secondopinion.diagnosticcenter.dao; 

import java.util.List;

import org.secondopinion.dao.IDAO;
import org.secondopinion.diagnosticcenter.dto.Role;

public interface RoleDAO extends IDAO<Role,Integer >{
	

	Role getByRoleName(String name);

	List<Role> getByUserroles(List<Integer> roleIds);
}