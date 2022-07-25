package org.secondopinion.diagnosticcenter.service.impl;

import java.util.List;
import java.util.Objects;

import org.secondopinion.diagnosticcenter.dao.MenuDAO;
import org.secondopinion.diagnosticcenter.dao.PackageDAO;
import org.secondopinion.diagnosticcenter.dao.PackagemenuDAO;
import org.secondopinion.diagnosticcenter.dao.PackagesubmenuDAO;
import org.secondopinion.diagnosticcenter.dao.SubmenuDAO;
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
import org.secondopinion.diagnosticcenter.service.IDiagnosticCenterMenuService;
import org.secondopinion.request.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class DiagnosticCenterMenuServiceImpl implements IDiagnosticCenterMenuService {

	@Autowired
	private MenuDAO menuDAO;

	@Autowired
	private SubmenuDAO submenuDAO;

	@Autowired
	private PackageDAO packageDAO;

	@Autowired
	private PackagemenuDAO packagemenuDAO;

	@Autowired
	private MenuDAO diagnosticcentermenuDAO;

	@Autowired
	private PackagesubmenuDAO packagesubmenuDAO;

	@Override
	@Transactional
	public void createMenu(Menu menu) {
		menuDAO.save(menu);

	}

	@Override
	@Transactional
	public void createMenuWithSubMenu(Menu menu) {
		menuDAO.save(menu);

	}

	@Override
	@Transactional
	public List<Menu> getMenuByDiagnosticenterAddressId(Long diagnosticCenterAddressId) {

		List<Menu> diagnosticcentermenu = diagnosticcentermenuDAO.finddMenuByiagnosticCenterAddressId(diagnosticCenterAddressId);
		return diagnosticcentermenu;
	}

	@Override
	@Transactional
	public void addSubMenuToMenu(Submenu submenu) {
		submenuDAO.save(submenu);

	}

	@Override
	@Transactional
	public void updateMenu(Menu menu) {
		menuDAO.save(menu);

	}

	@Override
	@Transactional
	public void updateSubMenu(Submenu submenu) {
		submenuDAO.save(submenu);

	}

	@Override
	@Transactional
	public void deleteMenu(Long menuId) {
		Menu menu = menuDAO.findDiagnosticcentermenuBymenuId(menuId);
		if (Objects.isNull(menu)) {
			throw new IllegalArgumentException("Object Should Not Found For given Id");
		}
		menu.setActive('N');
		menuDAO.save(menu);
	}

	@Override
	@Transactional
	public void deleteSubMenu(Long subMenuId) {
		Submenu submenu = submenuDAO.findOneByProperty(Submenu.FIELD_subMenuId, subMenuId);
		if (Objects.isNull(submenu)) {
			throw new IllegalArgumentException("Object Should Not Found For given Id");
		}
		submenu.setActive('N');
		submenuDAO.save(submenu);
	}

	@Override
	@Transactional
	public void createPackage(Package package1) {
		packageDAO.save(package1);

	}

	@Override
	@Transactional
	public void updatePackage(Package package1) {
		packageDAO.save(package1);

	}

	@Override
	@Transactional
	public void updatePackagMenu(Packagemenu packagemenu) {
		packagemenuDAO.save(packagemenu);
	}

	@Override
	@Transactional
	public void createPackagemenu(Packagemenu packagemenu) {
		packagemenuDAO.save(packagemenu);

	}

	@Override
	@Transactional
	public void deletePackagMenu(Long packageMenuId) {
		Packagemenu packagemenu = packagemenuDAO.findBypackageMenuId(packageMenuId);
		if (Objects.isNull(packagemenu)) {
			throw new IllegalArgumentException("Object Should Not Found For given Id");
		}
		packagemenu.setActive('N');
		packagemenuDAO.save(packagemenu);
	}

	@Override
	@Transactional
	public void deletePackage(Long packageID) {
		Package dbPackage = packageDAO.findOneByProperty(Package.FIELD_packageId, packageID);
	
		if (Objects.isNull(dbPackage)) {
			throw new IllegalArgumentException("Object Should Not Found For given Id");
		}
		dbPackage.setActive('N');
		packageDAO.deletePackage(dbPackage);
	}

	@Override
	@Transactional(readOnly = true)
	public List<Menu> getAllMenuItem(Long diagnosticCenterAddressId) {
		return menuDAO.finddMenuByiagnosticCenterAddressId(diagnosticCenterAddressId);
	}

	@Override
	@Transactional(readOnly = true)
	public List<Submenu> getSubMenuByMenuId(Long menuId) {
		return submenuDAO.findSubmenuBymenuId(menuId);
	}

	@Override
	@Transactional(readOnly = true)
	public List<Package> getPackages(Long diagnosticCenterAddressId) {
		return packageDAO.findPackageBydiagnosticCenterAddressId(diagnosticCenterAddressId);
	}

	@Override
	@Transactional(readOnly = true)
	public List<Menu> search(MenuSearchDTO menuSearch) {
		return menuDAO.searchMenu(menuSearch);
	}

	@Override
	@Transactional
	public Response<List<Package>> searchPackageitem(PackageSearch packageSearch) {
		return packageDAO.searchPackageitem(packageSearch);
	}

	@Override
	@Transactional
	public Response<List<Menu>> searchmenuitem(MenuSearch menuSearch) {
		return menuDAO.searchmenuitem(menuSearch);
	}

	@Override
	@Transactional
	public Response<List<Packagemenu>> searchPackagesmenu(PackagemenuSearchDTO packagesubmenuSearchDTO) {
		return packagemenuDAO.searchPackagesmenu(packagesubmenuSearchDTO);

	}

	@Override
	@Transactional
	public Response<List<Submenu>> searchSubmenu(SubmenuSearch menuSearch) {
		return submenuDAO.searchSubmenu(menuSearch);
	}

	@Override
	@Transactional
	public Response<List<Packagesubmenu>> searchPackagesubmenu(PackagesubmenuSearchDTO packagesubmenuSearchDTO) {
		return packagesubmenuDAO.searchPackagesubmenu(packagesubmenuSearchDTO);
	}

	@Override
	@Transactional
	public List<MenuDTO> getAllMenuAndSubItems(Long diagnosticCenterAddressId) {
		return menuDAO.getAllMenuAndSubItems(diagnosticCenterAddressId);
	}

}
