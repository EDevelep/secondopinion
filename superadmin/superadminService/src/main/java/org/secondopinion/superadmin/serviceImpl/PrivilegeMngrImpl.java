package org.secondopinion.superadmin.serviceImpl;

import java.awt.print.Book;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

import org.secondopinion.superadmin.dao.MenuDAO;
import org.secondopinion.superadmin.dao.RolemenuprivilageDAO;
import org.secondopinion.superadmin.dao.RolesDAO;
import org.secondopinion.superadmin.dto.Menu;
import org.secondopinion.superadmin.dto.Rolemenuprivilage;
import org.secondopinion.superadmin.dto.Roles;
import org.secondopinion.superadmin.service.IPrivilageMngr;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;

public class PrivilegeMngrImpl implements IPrivilageMngr, InitializingBean{

	@Autowired
	private RolesDAO rolesDAO;
	
	@Autowired
	private MenuDAO menuDAO;
	
	@Autowired
	private RolemenuprivilageDAO rolemenuprivilageDAO;
	
	private Map<String, List<String>> roleToMenuMap = new ConcurrentHashMap<String, List<String>>();
	
	
	@Override
	public void afterPropertiesSet() throws Exception {
		loadRoleToMenuMap();
		
	}

	private void loadRoleToMenuMap() {
		Collection<Roles> roles =  rolesDAO.findAll();
		Collection<Menu> menus = menuDAO.findAll();
		
		/*
		 * Ma Collection<Rolemenuprivilage> roleMenuPrivs =
		 * rolemenuprivilageDAO.findAll();
		 * 
		 * 
		 * roleMenuPrivs.stream().forEach(n->{ if });
		 */
	}

}
