package org.secondopinion.diagnosticcenter.dao; 

import java.util.List;

import org.secondopinion.dao.IDAO;
import org.secondopinion.diagnosticcenter.dto.Packagemenu;
import org.secondopinion.diagnosticcenter.dto.PackagemenuSearchDTO;
import org.secondopinion.diagnosticcenter.dto.Packagesubmenu;
import org.secondopinion.request.Response;

public interface PackagemenuDAO extends IDAO<Packagemenu,Long >{
	
	 List<Packagesubmenu> getMenuItems(Long packageId, Long menuId) ;

	Packagemenu findBypackageMenuId(Long packageMenuId);

	Response<List<Packagemenu>> searchPackagesmenu(PackagemenuSearchDTO packagesubmenuSearchDTO);
}