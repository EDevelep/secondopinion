package org.secondopinion.userMgmt.dao.hibernate;

import java.util.HashMap;
import java.util.Map;

import javax.transaction.Transactional;

import org.secondopinion.userMgmt.dto.UserRole;
import org.springframework.stereotype.Component;

@Component
public class UserRoleDAOHibernateImpl extends BaseUserRoleDAOHibernate{
	
	private static final String deleteRoleSql = "delete from UserRole where userId=:userId and roleId=:roleId";
	
	@Override
	@Transactional
	public void delete(UserRole userRole) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("userId", userRole.getUserId());
		params.put("roleId", userRole.getRoleId());
		
		executeQuery(deleteRoleSql, params);
	}
}