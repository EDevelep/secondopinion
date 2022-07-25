package org.secondopinion.diagnosticcenter.dao; 

import java.util.List;

import org.secondopinion.dao.IDAO;
import org.secondopinion.diagnosticcenter.dto.Userrole;

public interface UserroleDAO extends IDAO<Userrole,Long >{
	

	List<Userrole> getByRoleIdAndUserId(Integer roleId, Long diagnosticcenterUserId);
}