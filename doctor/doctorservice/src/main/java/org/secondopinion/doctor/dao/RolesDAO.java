package org.secondopinion.doctor.dao; 

import java.util.List;

import org.secondopinion.dao.IDAO;
import org.secondopinion.doctor.dto.Roles;


public interface RolesDAO extends IDAO<Roles,Integer >{
	List<Roles> getAllAvailableRoles(Integer doctorId);
	
	List<Roles> getRoles(String uName);
	
	Roles getRoleByName(String role);

}