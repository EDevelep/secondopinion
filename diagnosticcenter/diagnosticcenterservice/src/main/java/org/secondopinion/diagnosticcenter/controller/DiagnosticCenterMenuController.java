package org.secondopinion.diagnosticcenter.controller;

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
import org.secondopinion.diagnosticcenter.exception.DiagnosticCenterServerException;
import org.secondopinion.diagnosticcenter.request.dto.MenuDTO;
import org.secondopinion.diagnosticcenter.service.IDiagnosticCenterMenuService;
import org.secondopinion.request.Response;
import org.secondopinion.request.Response.StatusEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/menu")
public class DiagnosticCenterMenuController {

	@Autowired
	private IDiagnosticCenterMenuService diagnosticCenterMenuService;

	@PostMapping(value = "/submenu/save")
	public ResponseEntity<Response<String>> addSubMenuToMenu(@RequestBody Submenu submenu) {
		try {
			diagnosticCenterMenuService.addSubMenuToMenu(submenu);
			return new ResponseEntity<>(new Response<>(null, StatusEnum.SUCCESS, "SubMenu Save  successfully."),
					HttpStatus.OK);
		} catch (IllegalArgumentException e) {
			return new ResponseEntity<>(new Response<>(null, StatusEnum.FAILURE, e.getMessage()),
					HttpStatus.BAD_REQUEST);
		} catch (Exception e) {
			throw new DiagnosticCenterServerException(e.getMessage(), e);
		}
	}

	@PutMapping(value = "/updateSubMenu")
	public ResponseEntity<Response<String>> updateSubMenu(@RequestBody Submenu submenu) {
		try {
			diagnosticCenterMenuService.updateSubMenu(submenu);
			return new ResponseEntity<>(new Response<>(null, StatusEnum.SUCCESS, "Submenu Update  successfully."),
					HttpStatus.OK);
		} catch (IllegalArgumentException e) {
			return new ResponseEntity<>(new Response<>(null, StatusEnum.FAILURE, e.getMessage()),
					HttpStatus.BAD_REQUEST);
		} catch (Exception e) {
			throw new DiagnosticCenterServerException(e.getMessage(), e);
		}
	}

	@PostMapping(value = "/saveMenu")
	public ResponseEntity<Response<String>> createMenu(@RequestBody Menu menu) {
		try {
			diagnosticCenterMenuService.createMenu(menu);
			return new ResponseEntity<>(new Response<>(null, StatusEnum.SUCCESS, "Menu Save  successfully."),
					HttpStatus.OK);
		} catch (IllegalArgumentException e) {
			return new ResponseEntity<>(new Response<>(null, StatusEnum.FAILURE, e.getMessage()),
					HttpStatus.BAD_REQUEST);
		} catch (Exception e) {
			throw new DiagnosticCenterServerException(e.getMessage(), e);
		}
	}

	@PostMapping(value = "/createMenuWithSubMenu")
	public ResponseEntity<Response<String>> createMenuWithSubMenu(@RequestBody Menu menu) {
		try {
			diagnosticCenterMenuService.createMenuWithSubMenu(menu);
			return new ResponseEntity<>(new Response<>(null, StatusEnum.SUCCESS, "Menu Save  successfully."),
					HttpStatus.OK);
		} catch (IllegalArgumentException e) {
			return new ResponseEntity<>(new Response<>(null, StatusEnum.FAILURE, e.getMessage()),
					HttpStatus.BAD_REQUEST);
		} catch (Exception e) {
			throw new DiagnosticCenterServerException(e.getMessage(), e);
		}
	}

	@PostMapping(value = "/createpackage")
	public ResponseEntity<Response<String>> createPackage(@RequestBody Package package1) {
		try {
			diagnosticCenterMenuService.createPackage(package1);
			return new ResponseEntity<>(new Response<>(null, StatusEnum.SUCCESS, "Package Save  successfully."),
					HttpStatus.OK);
		} catch (IllegalArgumentException e) {
			return new ResponseEntity<>(new Response<>(null, StatusEnum.FAILURE, e.getMessage()),
					HttpStatus.BAD_REQUEST);
		} catch (Exception e) {
			throw new DiagnosticCenterServerException(e.getMessage(), e);
		}
	}

	@PutMapping(value = "/updateMenu")
	public ResponseEntity<Response<String>> updateMenu(@RequestBody Menu menu) {
		try {
			diagnosticCenterMenuService.updateMenu(menu);
			return new ResponseEntity<>(new Response<>(null, StatusEnum.SUCCESS, "Menu Update  successfully."),
					HttpStatus.OK);
		} catch (IllegalArgumentException e) {
			return new ResponseEntity<>(new Response<>(null, StatusEnum.FAILURE, e.getMessage()),
					HttpStatus.BAD_REQUEST);
		} catch (Exception e) {
			throw new DiagnosticCenterServerException(e.getMessage(), e);
		}
	}

	@PutMapping(value = "/updatePackage")
	public ResponseEntity<Response<String>> updatePackage(@RequestBody Package package1) {
		try {
			diagnosticCenterMenuService.updatePackage(package1);
			return new ResponseEntity<>(new Response<>(null, StatusEnum.SUCCESS, "Menu Save  successfully."),
					HttpStatus.OK);
		} catch (IllegalArgumentException e) {
			return new ResponseEntity<>(new Response<>(null, StatusEnum.FAILURE, e.getMessage()),
					HttpStatus.BAD_REQUEST);
		} catch (Exception e) {
			throw new DiagnosticCenterServerException(e.getMessage(), e);
		}
	}

	@PutMapping(value = "/updatePackagMenu")
	public ResponseEntity<Response<String>> updatePackagMenu(@RequestBody Packagemenu packagemenu) {
		try {
			diagnosticCenterMenuService.updatePackagMenu(packagemenu);
			return new ResponseEntity<>(new Response<>(null, StatusEnum.SUCCESS, "Packagemenu Update  successfully."),
					HttpStatus.OK);
		} catch (IllegalArgumentException e) {
			return new ResponseEntity<>(new Response<>(null, StatusEnum.FAILURE, e.getMessage()),
					HttpStatus.BAD_REQUEST);
		} catch (Exception e) {
			throw new DiagnosticCenterServerException(e.getMessage(), e);
		}
	}

	@GetMapping(value = "/getAllMenuItem/{diagnosticCenterId}")
	public ResponseEntity<Response<List<Menu>>> getAllMenuItem(
			@PathVariable("diagnosticCenterId") Long diagnosticCenterId) {
		try {
			List<Menu> menu = diagnosticCenterMenuService.getAllMenuItem(diagnosticCenterId);
			return new ResponseEntity<>(new Response<>(menu, StatusEnum.SUCCESS, " Get AllMenuItem  successfully."),
					HttpStatus.OK);
		} catch (IllegalArgumentException e) {
			return new ResponseEntity<>(new Response<>(null, StatusEnum.FAILURE, e.getMessage()),
					HttpStatus.BAD_REQUEST);
		} catch (Exception e) {
			throw new DiagnosticCenterServerException(e.getMessage(), e);
		}
	}

	@GetMapping(value = "/packages/get/{diagnosticCenterId}")
	public ResponseEntity<Response<List<Package>>> getPackages(
			@PathVariable("diagnosticCenterId") Long diagnosticCenterId) {
		try {
			List<Package> pcakage = diagnosticCenterMenuService.getPackages(diagnosticCenterId);
			return new ResponseEntity<>(new Response<>(pcakage, StatusEnum.SUCCESS, " Get Packages  successfully."),
					HttpStatus.OK);
		} catch (IllegalArgumentException e) {
			return new ResponseEntity<>(new Response<>(null, StatusEnum.FAILURE, e.getMessage()),
					HttpStatus.BAD_REQUEST);
		} catch (Exception e) {
			throw new DiagnosticCenterServerException(e.getMessage(), e);
		}
	}

	@PostMapping(value = "/searchMenu")
	public ResponseEntity<Response<List<Menu>>> searchMenu(@RequestBody MenuSearchDTO menuSearch) {
		try {
			List<Menu> menu = diagnosticCenterMenuService.search(menuSearch);
			return new ResponseEntity<>(new Response<>(menu, StatusEnum.SUCCESS, " Menu Packages  successfully."),
					HttpStatus.OK);
		} catch (IllegalArgumentException e) {
			return new ResponseEntity<>(new Response<>(null, StatusEnum.FAILURE, e.getMessage()),
					HttpStatus.BAD_REQUEST);
		} catch (Exception e) {
			throw new DiagnosticCenterServerException(e.getMessage(), e);
		}
	}

	@PostMapping(value = "/searchmenuitem")
	public ResponseEntity<Response<List<Menu>>> searchMenuitem(@RequestBody MenuSearch menuSearch) {
		try {
			Response<List<Menu>> menu = diagnosticCenterMenuService.searchmenuitem(menuSearch);
			return new ResponseEntity<>(menu, HttpStatus.OK);
		} catch (IllegalArgumentException e) {
			return new ResponseEntity<>(new Response<>(null, StatusEnum.FAILURE, e.getMessage()),
					HttpStatus.BAD_REQUEST);
		} catch (Exception e) {
			throw new DiagnosticCenterServerException(e.getMessage(), e);
		}
	}

	@PostMapping(value = "/searchPackageitem")
	public ResponseEntity<Response<List<Package>>> searchPackageitem(@RequestBody PackageSearch packageSearch) {
		try {
			Response<List<Package>> package1 = diagnosticCenterMenuService.searchPackageitem(packageSearch);
			return new ResponseEntity<>(package1, HttpStatus.OK);
		} catch (IllegalArgumentException e) {
			return new ResponseEntity<>(new Response<>(null, StatusEnum.FAILURE, e.getMessage()),
					HttpStatus.BAD_REQUEST);
		} catch (Exception e) {
			throw new DiagnosticCenterServerException(e.getMessage(), e);
		}
	}

	@PostMapping(value = "/searchPackagesubmenu")
	public ResponseEntity<Response<List<Packagesubmenu>>> searchPackagesubmenu(
			@RequestBody PackagesubmenuSearchDTO packagesubmenuSearchDTO) {
		try {
			Response<List<Packagesubmenu>> package1 = diagnosticCenterMenuService
					.searchPackagesubmenu(packagesubmenuSearchDTO);
			return new ResponseEntity<>(package1, HttpStatus.OK);
		} catch (IllegalArgumentException e) {
			return new ResponseEntity<>(new Response<>(null, StatusEnum.FAILURE, e.getMessage()),
					HttpStatus.BAD_REQUEST);
		} catch (Exception e) {
			throw new DiagnosticCenterServerException(e.getMessage(), e);
		}
	}

	@PostMapping(value = "/searchSubmenu")
	public ResponseEntity<Response<List<Submenu>>> searchSubmenu(@RequestBody SubmenuSearch submenuSearch) {
		try {
			Response<List<Submenu>> submenu = diagnosticCenterMenuService.searchSubmenu(submenuSearch);
			return new ResponseEntity<>(submenu, HttpStatus.OK);
		} catch (IllegalArgumentException e) {
			return new ResponseEntity<>(new Response<>(null, StatusEnum.FAILURE, e.getMessage()),
					HttpStatus.BAD_REQUEST);
		} catch (Exception e) {
			throw new DiagnosticCenterServerException(e.getMessage(), e);
		}
	}

	@PostMapping(value = "/searchPackagesmenu")
	public ResponseEntity<Response<List<Packagemenu>>> searchPackagesmenu(
			@RequestBody PackagemenuSearchDTO packagemenuSearchDTO) {
		try {
			Response<List<Packagemenu>> packagemenu = diagnosticCenterMenuService
					.searchPackagesmenu(packagemenuSearchDTO);
			return new ResponseEntity<>(packagemenu, HttpStatus.OK);
		} catch (IllegalArgumentException e) {
			return new ResponseEntity<>(new Response<>(null, StatusEnum.FAILURE, e.getMessage()),
					HttpStatus.BAD_REQUEST);
		} catch (Exception e) {
			throw new DiagnosticCenterServerException(e.getMessage(), e);
		}
	}

	@GetMapping(value = "/getAllMenuAndSubItems/{diagnosticCenterAddressId}")
	public ResponseEntity<Response<List<MenuDTO>>> getAllMenuAndSubItems(
			@PathVariable("diagnosticCenterAddressId") Long diagnosticCenterAddressId) {
		try {
			List<MenuDTO> menuDTO = diagnosticCenterMenuService.getAllMenuAndSubItems(diagnosticCenterAddressId);
			return new ResponseEntity<>(new Response<>(menuDTO, StatusEnum.SUCCESS, " Get Menu successfully."),
					HttpStatus.OK);
		} catch (IllegalArgumentException e) {
			return new ResponseEntity<>(new Response<>(null, StatusEnum.FAILURE, e.getMessage()),
					HttpStatus.BAD_REQUEST);
		} catch (Exception e) {
			throw new DiagnosticCenterServerException(e.getMessage(), e);
		}
	}

	
	@DeleteMapping(value = "/deleteMenu/{menuId}")
	public ResponseEntity<Response<String>> deleteMenu(@PathVariable("menuId") Long menuId) {
		try {
			diagnosticCenterMenuService.deleteMenu(menuId);
			return new ResponseEntity<>(
					new Response<>("Delete successfully", StatusEnum.SUCCESS, " Delete successfully"), HttpStatus.OK);
		} catch (IllegalArgumentException e) {
			return new ResponseEntity<>(new Response<>(null, StatusEnum.FAILURE, e.getMessage()),
					HttpStatus.BAD_REQUEST);
		} catch (Exception e) {
			throw new DiagnosticCenterServerException(e.getMessage(), e);
		}
	}

	@DeleteMapping(value = "/deletePackage/{packageID}")
	public ResponseEntity<Response<String>> deletePackage(@PathVariable("packageID") Long packageId) {
		try {
			diagnosticCenterMenuService.deletePackage(packageId);
			return new ResponseEntity<>(
					new Response<>("Delete successfully", StatusEnum.SUCCESS, " Delete successfully"), HttpStatus.OK);
		} catch (IllegalArgumentException e) {
			return new ResponseEntity<>(new Response<>(null, StatusEnum.FAILURE, e.getMessage()),
					HttpStatus.BAD_REQUEST);
		} catch (Exception e) {
			throw new DiagnosticCenterServerException(e.getMessage(), e);
		}
	}
	
	@DeleteMapping(value = "/deleteSubMenu/{subMenuId}")
	public ResponseEntity<Response<String>> deleteSubMenu(@PathVariable("subMenuId") Long subMenuId) {
		try {
			diagnosticCenterMenuService.deleteSubMenu(subMenuId);
			return new ResponseEntity<>(
					new Response<>("Delete successfully", StatusEnum.SUCCESS, " Delete successfully"), HttpStatus.OK);
		} catch (IllegalArgumentException e) {
			return new ResponseEntity<>(new Response<>(null, StatusEnum.FAILURE, e.getMessage()),
					HttpStatus.BAD_REQUEST);
		} catch (Exception e) {
			throw new DiagnosticCenterServerException(e.getMessage(), e);
		}
	}
	
	@DeleteMapping(value = "/deletePackagMenu/{packageMenuId}")
	public ResponseEntity<Response<String>> deletePackagMenu(@PathVariable("packageMenuId") Long packageMenuId) {
		try {
			diagnosticCenterMenuService.deletePackagMenu(packageMenuId);
			return new ResponseEntity<>(
					new Response<>("Delete successfully", StatusEnum.SUCCESS, " Delete successfully"), HttpStatus.OK);
		} catch (IllegalArgumentException e) {
			return new ResponseEntity<>(new Response<>(null, StatusEnum.FAILURE, e.getMessage()),
					HttpStatus.BAD_REQUEST);
		} catch (Exception e) {
			throw new DiagnosticCenterServerException(e.getMessage(), e);
		}
	}
}