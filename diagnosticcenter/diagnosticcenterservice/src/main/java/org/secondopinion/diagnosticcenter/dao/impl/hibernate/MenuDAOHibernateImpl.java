package org.secondopinion.diagnosticcenter.dao.impl.hibernate;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;
import org.secondopinion.diagnosticcenter.dao.SubmenuDAO;
import org.secondopinion.diagnosticcenter.dto.Menu;
import org.secondopinion.diagnosticcenter.dto.MenuSearch;
import org.secondopinion.diagnosticcenter.dto.MenuSearchDTO;
import org.secondopinion.diagnosticcenter.dto.Submenu;
import org.secondopinion.diagnosticcenter.request.dto.MenuDTO;
import org.secondopinion.diagnosticcenter.request.dto.SubMenuDTO;
import org.secondopinion.request.Response;
import org.secondopinion.utils.ObjectUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import io.jsonwebtoken.lang.Collections;
import io.netty.util.internal.StringUtil;

@Repository
public class MenuDAOHibernateImpl extends BaseMenuDAOHibernate {
	private static final String GET_ALL_MENUS = "select menuId, menuName from menu where diagnosticCenterAddressId = :DIAGNOSTIC_CENTER_ADDRESS_ID and active='Y'";
	private static final String GET_ALL_SUB_MENUS = "select subMenuId, menuId, serviceName, serviceType,price,availableFrom,available,collectiontype from submenu where diagnosticCenterAddressId = :DIAGNOSTIC_CENTER_ADDRESS_ID and active='Y'";

	@Autowired
	private SubmenuDAO submenuDAO;

	@Override
	@Transactional
	public void save(Menu menu) {
		if (Objects.isNull(menu.getMenuId())) {
			menu.setActive('Y');
		}
		menu.setActive('Y');
		super.save(menu);

	}

	@Override
	@Transactional
	public List<Submenu> getMenuItems(Long diagnosticCenterId, Long menuId) {

		Menu menu = findDiagnosticcentermenuBymenuId(menuId);

		if (!ObjectUtil.isEqual(menu.getDiagnosticCenterAddressId(), diagnosticCenterId)) {
			throw new IllegalArgumentException("Invalid Request - Menu does not belong to diagnostic center");
		}
		// active to every where
		return submenuDAO.findSubmenuBymenuId(menuId);

	}

	@Override
	@Transactional
	public List<Menu> finddMenuByiagnosticCenterAddressId(Long diagnosticCenterAddressId) {
		List<Criterion> criterions = new ArrayList<>();
		criterions.add(Restrictions.eq(Menu.FIELD_diagnosticCenterAddressId, diagnosticCenterAddressId));
		criterions.add(Restrictions.eq(Submenu.FIELD_active, 'Y'));
		return findByCrieria(criterions);
	}

	@Override
	@Transactional(readOnly = true)
	public List<Menu> searchMenu(MenuSearchDTO menuSearch) {
		List<Criterion> criterions = new ArrayList<>();
		if (Objects.nonNull(menuSearch.getDiagnosticCenterAddressId())) {
			criterions.add(
					Restrictions.eq(Menu.FIELD_diagnosticCenterAddressId, menuSearch.getDiagnosticCenterAddressId()));
		}
		if (!StringUtil.isNullOrEmpty(menuSearch.getMenuName())) {
			criterions.add(Restrictions.ilike(Menu.FIELD_menuName, menuSearch.getMenuName(), MatchMode.ANYWHERE));
		}
		criterions.add(Restrictions.eq(Menu.FIELD_active, 'Y'));
		return findByCrieria(criterions);
	}

	@Override
	@Transactional
	public Menu findDiagnosticcentermenuBymenuId(Long menuId) {
		List<Criterion> criterions = new ArrayList<>();
		criterions.add(Restrictions.eq(Menu.FIELD_menuId, menuId));
		criterions.add(Restrictions.eq(Menu.FIELD_active, 'Y'));

		List<Menu> diagnosticcentermenus = findByCrieria(criterions);
		if (Collections.isEmpty(diagnosticcentermenus)) {
			return null;
		}
		return diagnosticcentermenus.get(0);
	}

	@Override
	@Transactional(readOnly = true)
	public Response<List<Menu>> searchmenuitem(MenuSearch menuSearch) {
		List<Criterion> criterions = new ArrayList<>();

		if (Objects.nonNull(menuSearch.getMenuName())) {
			criterions.add(Restrictions.eq(Menu.FIELD_menuName, menuSearch.getMenuName()));
		}

		if (Objects.nonNull(menuSearch.getActive())) {
			criterions.add(Restrictions.eq(Menu.FIELD_active, menuSearch.getActive()));
		}

		if (Objects.nonNull(menuSearch.getDiagnosticeCenterAddressId())) {
			criterions.add(
					Restrictions.eq(Menu.FIELD_diagnosticCenterAddressId, menuSearch.getDiagnosticeCenterAddressId()));
		}

		return findByCrieria(criterions, null, menuSearch.getPageNumber(), menuSearch.getMaxresult());
	}

	@Override
	@Transactional
	public void createMenuWithSubMenu(Menu menu) {
		if (Objects.isNull(menu.getMenuId())) {
			menu.setActive('Y');
		}
		super.save(menu);

		menu.getSubMenuItems().forEach(n -> {
			n.setMenuId(menu.getMenuId());
		});
		menu.getSubMenuItems().forEach(n -> {
			n.setActive(menu.getActive());
		});
		submenuDAO.save(menu.getSubMenuItems());

	}

	@Override
	public List<MenuDTO> getAllMenuAndSubItems(Long diagnosticCenterAddressId) {
		Map<String, Object> params = new HashMap<>();
		params.put("DIAGNOSTIC_CENTER_ADDRESS_ID", diagnosticCenterAddressId);
		List<MenuDTO> menus = findBySqlQuery(GET_ALL_MENUS, params, MenuDTO.class);

		List<SubMenuDTO> subMenuDTOs = findBySqlQuery(GET_ALL_SUB_MENUS, params, SubMenuDTO.class);

		Map<BigInteger, MenuDTO> menuMap = menus.stream()
				.collect(Collectors.toMap(MenuDTO::getMenuId, Function.identity()));

		
		subMenuDTOs.stream().forEach(n -> {
			if (menuMap.containsKey(n.getMenuId())) {
				menuMap.get(n.getMenuId()).addSubMenu(n);
			}
		});

		
		return menus;
	}

}