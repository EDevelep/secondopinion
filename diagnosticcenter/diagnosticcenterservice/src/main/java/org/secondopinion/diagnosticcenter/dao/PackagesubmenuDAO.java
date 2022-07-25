package org.secondopinion.diagnosticcenter.dao; 

import java.util.List;

import org.secondopinion.dao.IDAO;
import org.secondopinion.diagnosticcenter.dto.Packagesubmenu;
import org.secondopinion.diagnosticcenter.dto.PackagesubmenuSearchDTO;
import org.secondopinion.request.Response;

public interface PackagesubmenuDAO extends IDAO<Packagesubmenu,Long >{

	
	Response<List<Packagesubmenu>> searchPackagesubmenu(PackagesubmenuSearchDTO packagesubmenuSearchDTO);
}