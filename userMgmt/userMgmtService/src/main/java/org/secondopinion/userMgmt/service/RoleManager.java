package org.secondopinion.userMgmt.service;

import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.secondopinion.userMgmt.dto.Privileges;
import org.secondopinion.userMgmt.dto.Roleprivileges;
import org.secondopinion.userMgmt.dto.Roles;
import org.secondopinion.userMgmt.dto.User;
import org.springframework.beans.factory.InitializingBean;

import org.secondopinion.userMgmt.dao.PrivilegesDAO;
import org.secondopinion.userMgmt.dao.RoleprivilegesDAO;
import org.secondopinion.userMgmt.dao.RolesDAO;

public class RoleManager implements InitializingBean{
	
	private RolesDAO roleDAO;
	private PrivilegesDAO privilegeDAO;
	private RoleprivilegesDAO rolePrivilegesDAO;
	
	public PrivilegesDAO getPrivilegeDAO() {
		return privilegeDAO;
	}

	public void setPrivilegeDAO(PrivilegesDAO privilegeDAO) {
		this.privilegeDAO = privilegeDAO;
	}

	public RoleprivilegesDAO getRolePrivilegesDAO() {
		return rolePrivilegesDAO;
	}

	public void setRolePrivilegesDAO(RoleprivilegesDAO rolePrivilegesDAO) {
		this.rolePrivilegesDAO = rolePrivilegesDAO;
	}
	
	private static Set<String> roleAndPrivileges = new HashSet<String>();
	
	private void loadRoles(){
		Collection<Roleprivileges> roleprivileges = rolePrivilegesDAO.findAll();
		
		Map<Integer, Roles> rolesMap = getRolesMap();
		Map<Integer, Privileges> privilegeMap = getPrivilegeMap();
		
		for (Roleprivileges roleprivilege : roleprivileges) {
			Roles role = rolesMap.get(roleprivilege.getRoleId());
			Privileges privilege = privilegeMap.get(roleprivilege.getPrivilegeId());
			
			if(privilege != null){
				roleAndPrivileges.add(role.getRoleId() + "." + privilege.getPrivilegeCode());
			}
		}
		
	}
	
	private Map<Integer, Roles> getRolesMap(){
		Map<Integer, Roles> map = new HashMap<Integer, Roles>();
		Collection<Roles> roles =  roleDAO.findAll();
		
		for(Roles  role : roles){
			map.put(role.getRoleId(), role);
		}
		
		return map;
	}

	private Map<Integer, Privileges> getPrivilegeMap(){
		Map<Integer, Privileges> map = new HashMap<Integer, Privileges>();
		Collection<Privileges>  privileges = privilegeDAO.findAll();
		
		for(Privileges privilege : privileges){
			map.put(privilege.getPrivilegeId(), privilege);
			
		}
		
		return map;
	}
	/**
	 * @return the roleDAO
	 */
	public RolesDAO getRoleDAO() {
		return roleDAO;
	}

	/**
	 * @param roleDAO the roleDAO to set
	 */
	public void setRoleDAO(RolesDAO roleDAO) {
		this.roleDAO = roleDAO;
	}

	@Override
	public void afterPropertiesSet() throws Exception {
		loadRoles();
		
	}
	
	

}
