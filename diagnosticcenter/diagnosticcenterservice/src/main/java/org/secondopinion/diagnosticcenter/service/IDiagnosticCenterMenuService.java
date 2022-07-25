package org.secondopinion.diagnosticcenter.service;

import java.util.List;

import org.secondopinion.diagnosticcenter.dto.Menu;
import org.secondopinion.diagnosticcenter.dto.MenuSearch;
import org.secondopinion.diagnosticcenter.dto.MenuSearchDTO;
import org.secondopinion.diagnosticcenter.dto.Package;
import org.secondopinion.diagnosticcenter.dto.PackageSearch;
import org.secondopinion.diagnosticcenter.dto.Packagemenu;
import org.secondopinion.diagnosticcenter.dto.PackagemenuSearchDTO;
import org.secondopinion.diagnosticcenter.dto.Packagesubmenu;
import org.secondopinion.diagnosticcenter.dto.PackagesubmenuSearchDTO;
import org.secondopinion.diagnosticcenter.dto.Submenu;
import org.secondopinion.diagnosticcenter.dto.SubmenuSearch;
import org.secondopinion.diagnosticcenter.request.dto.MenuDTO;
import org.secondopinion.request.Response;

public interface IDiagnosticCenterMenuService {

	// Accept list of Menus
	public void createMenu(Menu menu);
	public void createMenuWithSubMenu(Menu menu);
	public void addSubMenuToMenu(Submenu submenu);
	public List<MenuDTO> getAllMenuAndSubItems(Long diagnosticCenterAddressId);
	public void updateMenu(Menu menu);

	public void updateSubMenu(Submenu submenu);

	public void deleteMenu(Long menuId);
	public void deletePackage(Long packageID);
	public void deleteSubMenu(Long subMenuId);

	public void createPackage(Package package1);

	public void updatePackage(Package package1);

	public void createPackagemenu(Packagemenu packagemenu);

	public void updatePackagMenu(Packagemenu packagemenu);

	public void deletePackagMenu(Long packageMenuId);

	Response<List<Package>> searchPackageitem(PackageSearch packageSearch);

	Response<List<Menu>> searchmenuitem(MenuSearch menuSearch);

	Response<List<Packagemenu>> searchPackagesmenu(PackagemenuSearchDTO packagesubmenuSearchDTO);

	Response<List<Packagesubmenu>> searchPackagesubmenu(PackagesubmenuSearchDTO packagesubmenuSearchDTO);

	Response<List<Submenu>> searchSubmenu(SubmenuSearch menuSearch);

	// public List<Package> searchPackage(PackageSearchDTO packageSearch);
	public List<Menu> getAllMenuItem(Long diagnosticCenterId);

	public List<Package> getPackages(Long diagnosticCenterId);

	public List<Menu> search(MenuSearchDTO menuSearch);

	public List<Menu> getMenuByDiagnosticenterAddressId(Long diagnosticCenterAddressId);

	public List<Submenu> getSubMenuByMenuId(Long menuId);
	

}
