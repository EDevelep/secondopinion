package org.secondopinion.diagnosticcenter.dao;

import java.util.List;

import org.secondopinion.dao.IDAO;
import org.secondopinion.diagnosticcenter.dto.Menu;
import org.secondopinion.diagnosticcenter.dto.MenuSearch;
import org.secondopinion.diagnosticcenter.dto.MenuSearchDTO;
import org.secondopinion.diagnosticcenter.dto.Submenu;
import org.secondopinion.diagnosticcenter.request.dto.MenuDTO;
import org.secondopinion.request.Response;

public interface MenuDAO extends IDAO<Menu, Long> {
	void createMenuWithSubMenu(Menu menu);
	List<Menu> searchMenu(MenuSearchDTO menuSearch);

	public List<Submenu> getMenuItems(Long diagnosticCenterId, Long menuId);

	List<Menu> finddMenuByiagnosticCenterAddressId(Long diagnosticCenterAddressId);
	public List<MenuDTO> getAllMenuAndSubItems(Long diagnosticCenterAddressId);
	Menu findDiagnosticcentermenuBymenuId(Long menuId);

	Response<List<Menu>> searchmenuitem(MenuSearch menuSearch);

	
}