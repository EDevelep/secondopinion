package org.secondopinion.doctor.daoimpl;

import java.util.Objects;


import org.secondopinion.doctor.dto.UserRole;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class UserRoleDAOHibernateImpl extends BaseUserRoleDAOHibernate {
	
	
	@Override
	@Transactional
	public void save(UserRole userRole) {
		
		if(Objects.isNull(userRole.getUserRoleId())) {
			userRole.setActive('Y');
		}
		
		super.save(userRole);
	}

}
