package org.secondopinion.diagnosticcenter.serviceimpl;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import javax.validation.constraints.AssertTrue;

import org.junit.Test;
import org.secondopinion.diagnosticcenter.dto.Menu;
import org.secondopinion.diagnosticcenter.dto.Package;
import org.secondopinion.diagnosticcenter.dto.Packagemenu;
import org.secondopinion.diagnosticcenter.dto.Submenu;
import org.secondopinion.diagnosticcenter.request.dto.MenuDTO;
import org.secondopinion.diagnosticcenter.service.IDiagnosticCenterMenuService;
import org.secondopinion.diagnosticcenter.test.DiagnosticcenterServiceApplicationTest;
import org.springframework.beans.factory.annotation.Autowired;

public class DiagnosticCenterMenuServiceTest extends DiagnosticcenterServiceApplicationTest {

	@Autowired
	private IDiagnosticCenterMenuService diagnosticCenterMenuService;

	@Test
	public void createMenu() {
		Menu menu = new Menu();
		menu.setMenuName("test by ");
		menu.setDiagnosticCenterAddressId(1L);
		Submenu submenu = new Submenu();
		submenu.setServiceName("Full body Checkup");
		submenu.setServiceType("paid");
		submenu.setPrice(400.00);
		submenu.setDiagnosticCenterAddressId(2L);
		submenu.setAvailable("Y");
		menu.setSubMenuItems(Arrays.asList(submenu));
		diagnosticCenterMenuService.createMenu(menu);
	}
	

	@Test
	public void createPackage() {
		Package package1=new Package();
		Packagemenu packagemenu=new Packagemenu();
		packagemenu.setMenuId(1L);
		packagemenu.setMenuName("test");
		package1.setActive('Y');
		package1.setAvailableFrom(new Date());
		package1.setDiagnosticCenterAddressId(2L);
		package1.setDescription("test");
		package1.setDiscount(50.00);
		package1.setPrice(300.00);
		package1.setPackageName("full body checkup");
		package1.setStatus("Active");
		package1.setMenuItems(Arrays.asList(packagemenu));
		diagnosticCenterMenuService.createPackage(package1);
	}
	@Test
	public void getAllMenuAndSubItems() {
		Long diagnosticCenterAddressId = 2L;
		List<MenuDTO> list = diagnosticCenterMenuService.getAllMenuAndSubItems(diagnosticCenterAddressId);
		System.out.println(list);
	}
	
	@Test
	public void getPackages() {
		Long diagnosticCenterId = 12L;
		 List<Package> list = diagnosticCenterMenuService.getPackages(diagnosticCenterId);
		System.out.println(list);
	}
}
