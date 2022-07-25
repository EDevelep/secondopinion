package org.secondopinion.pharmacy.dao; 

import java.util.List;

import org.secondopinion.dao.IDAO;
import org.secondopinion.pharmacy.dto.Userrole;

public interface UserroleDAO extends IDAO<Userrole,Long >{

	List<Userrole> getByRoleIdAndUserId(Integer roleId, Long pharmacyUserId);

}