package org.secondopinion.diagnosticcenter.dao;

import java.util.List;

import org.secondopinion.dao.IDAO;
import org.secondopinion.diagnosticcenter.dto.Submenu;
import org.secondopinion.diagnosticcenter.dto.SubmenuDTO;
import org.secondopinion.diagnosticcenter.dto.SubmenuSearch;
import org.secondopinion.request.Response;

public interface SubmenuDAO extends IDAO<Submenu,Long >{

	

	List<Submenu> findSubmenuBymenuId(Long menuId);

	Response<List<Submenu>> searchSubmenu(SubmenuSearch menuSearch);

	List<SubmenuDTO> getAllDiagnosticcenterIdBySearchCritieria(List<String> serviceName);

	
	

	

	
}