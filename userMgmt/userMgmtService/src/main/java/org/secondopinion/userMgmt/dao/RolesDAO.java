package org.secondopinion.userMgmt.dao; 

import java.util.List;

import org.secondopinion.userMgmt.dto.Roles;

import org.secondopinion.dao.IDAO;

public interface RolesDAO extends IDAO<Roles,Integer >{
	List<Roles> getAllAvailableRoles(Integer companyId);
	
	List<Roles> getUserRoles(String userName);
	
	Roles getRoleByName(String role);

}