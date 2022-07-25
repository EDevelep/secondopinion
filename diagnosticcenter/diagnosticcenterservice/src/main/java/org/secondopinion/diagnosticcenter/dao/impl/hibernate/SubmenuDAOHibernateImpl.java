package org.secondopinion.diagnosticcenter.dao.impl.hibernate;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
import org.secondopinion.diagnosticcenter.dto.Submenu;
import org.secondopinion.diagnosticcenter.dto.SubmenuDTO;
import org.secondopinion.diagnosticcenter.dto.SubmenuSearch;
import org.secondopinion.request.Response;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
@Repository
public class SubmenuDAOHibernateImpl extends BaseSubmenuDAOHibernate {
	private static final String SUB_MENU_SQL = " select  sc.menuId,sc.serviceName, sc.diagnosticCenterAddressId,sc.price from submenu sc where serviceName  in(:serviceName)";
	@Override
	@Transactional
	public void save(Submenu submenu) {
		if(Objects.isNull(submenu.getSubMenuId())) {
			submenu.setActive('Y');
		}
		submenu.setActive('Y');
		super.save(submenu);
	}

	@Override
	@Transactional
	public List<Submenu> findSubmenuBymenuId(Long menuId) {
		List<Criterion> criterions = new ArrayList<>();
		criterions.add(Restrictions.eq(Submenu.FIELD_menuId, menuId));
		criterions.add(Restrictions.eq(Submenu.FIELD_active, 'Y'));
		return findByCrieria(criterions);
	}

	@Override
	@Transactional
	public Response<List<Submenu>> searchSubmenu(SubmenuSearch menuSearch) {
		List<Criterion> criterions = new ArrayList<>();
		
		if(Objects.nonNull(menuSearch.getAvailable())) {
			criterions.add(Restrictions.eq(Submenu.FIELD_available,menuSearch.getAvailable()));
		}
		

		if(Objects.nonNull(menuSearch.getDiagnosticCenterAddressId())) {
			criterions.add(Restrictions.eq(Submenu.FIELD_diagnosticCenterAddressId,menuSearch.getDiagnosticCenterAddressId()));
		}
		
		if(Objects.nonNull(menuSearch.getServiceName())) {
			criterions.add(Restrictions.eq(Submenu.FIELD_serviceName,menuSearch.getServiceName()));
		}
		
		if(Objects.nonNull(menuSearch.getServiceType())) {
			criterions.add(Restrictions.eq(Submenu.FIELD_serviceType,menuSearch.getServiceType()));
		}
		
		if(Objects.nonNull(menuSearch.getPrice())) {
			criterions.add(Restrictions.eq(Submenu.FIELD_price,menuSearch.getPrice()));
		}
		
		if(Objects.nonNull(menuSearch.getMenuid())) {
			criterions.add(Restrictions.eq(Submenu.FIELD_menuId,menuSearch.getMenuid()));
		}
		criterions.add(Restrictions.eq(Submenu.FIELD_active, 'Y'));
		
		return findByCrieria(criterions,  null, menuSearch.getPageNumber(), menuSearch.getMaxresult());
		
	}

	@Override
	@Transactional
	public List<SubmenuDTO> getAllDiagnosticcenterIdBySearchCritieria(List<String> serviceName) {
	
		Map<String, Object> params = new HashMap<String, Object>();

		params.put("serviceName", serviceName);
		List<SubmenuDTO> submenuDTOs = findBySqlQuery(SUB_MENU_SQL, params, SubmenuDTO.class);
		
		return submenuDTOs;
	}


}