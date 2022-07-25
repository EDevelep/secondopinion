package org.secondopinion.userMgmt.dao.hibernate;

import java.sql.Timestamp;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
import org.hibernate.type.StandardBasicTypes;
import org.hibernate.type.Type;
import org.secondopinion.userMgmt.dto.Roles;
import org.springframework.stereotype.Component;

@Component
public class RolesDAOHibernateImpl extends BaseRolesDAOHibernate{
	
	@Override
	public List<Roles> getAllAvailableRoles(Integer companyId) {
		return findAll();
	}
	
	private final String user_roles = "select a.* from roles a, userrole b where a.roleId = b.roleId and b.userId = :USER_ID";

	@Override
	public List<Roles> getUserRoles(String userName) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("USER_ID", userName);
		List<Map<String, Object>> objects= findBySqlQueryMapTransformer(user_roles, params, buildScalarMapForRoles());
		
		List<Roles> roles = new LinkedList<Roles>();
		for (Iterator<Map<String, Object>> iterator = objects.iterator(); iterator.hasNext();) {
			Map<String, Object> roleMap = iterator.next();
			Roles role = new Roles();
			role.setRoleId((Integer) roleMap.get(Roles.FIELD_roleId));
			role.setLastUpdatedBy((String) roleMap.get(Roles.FIELD_lastUpdatedBy));
			
			role.setCreatedDate((Date) roleMap.get(Roles.FIELD_createdDate));
			role.setCreatedBy((String) roleMap.get(Roles.FIELD_createdBy));
			
			
			role.setRoleName((String) roleMap.get(Roles.FIELD_roleName));
			role.setActive((String) roleMap.get(Roles.FIELD_active));
			role.setLastUpdatedTs((Timestamp) roleMap.get(Roles.FIELD_lastUpdatedTs));
			roles.add(role);
		}
		return roles;
	}

	@Override
	public Roles getRoleByName(String role) {
		Criterion criterion = Restrictions.eq(Roles.FIELD_roleName, role);
		
		List<Roles> roles = findByCrieria(criterion);
		
		if(roles != null && roles.size()>0){
			return roles.get(0);
		}
		return null;
	}

	private Map<String, Type> buildScalarMapForRoles() {
		Map<String, Type> scalarMapping = new HashMap<>();
		scalarMapping.put(Roles.FIELD_roleId, StandardBasicTypes.INTEGER);
		scalarMapping.put(Roles.FIELD_roleName, StandardBasicTypes.STRING);
		scalarMapping.put(Roles.FIELD_active, StandardBasicTypes.STRING);
		scalarMapping.put(Roles.FIELD_createdBy, StandardBasicTypes.STRING);
		scalarMapping.put(Roles.FIELD_createdDate, StandardBasicTypes.DATE);
		scalarMapping.put(Roles.FIELD_lastUpdatedBy, StandardBasicTypes.STRING);
		scalarMapping.put(Roles.FIELD_lastUpdatedTs, StandardBasicTypes.TIMESTAMP);
		return scalarMapping;
	}
	
}