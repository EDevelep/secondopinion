package org.secondopinion.diagnosticcenter.dao.impl.hibernate;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
import org.secondopinion.dao.exception.DataAccessException;
import org.secondopinion.diagnosticcenter.dao.PackagesubmenuDAO;
import org.secondopinion.diagnosticcenter.dto.Package;
import org.secondopinion.diagnosticcenter.dto.Packagemenu;
import org.secondopinion.diagnosticcenter.dto.PackagemenuSearchDTO;
import org.secondopinion.diagnosticcenter.dto.Packagesubmenu;
import org.secondopinion.diagnosticcenter.dto.PackagesubmenuSearchDTO;
import org.secondopinion.request.Response;
import org.secondopinion.utils.ObjectUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import io.jsonwebtoken.lang.Collections;

@Repository
public class PackagemenuDAOHibernateImpl extends BasePackagemenuDAOHibernate {
	
	

	@Autowired
	private PackagesubmenuDAO packagesubmenuDAO;

	@Override
	@Transactional
	public void save(Packagemenu packagemenu) throws DataAccessException {
		packagemenu.setActive('Y');
		super.save(packagemenu);
		packagemenu.getPackageSubMenuItems().forEach(n -> {
			n.setPackageMenuId(packagemenu.getPackageMenuId());
		});
		packagemenu.setActive('Y');
		packagesubmenuDAO.save(packagemenu.getPackageSubMenuItems());
	}

	@Override
	@Transactional
	public List<Packagesubmenu> getMenuItems(Long packageId, Long packageMenuId) {
		Packagemenu packagemenu = findById(packageMenuId);

		if (!ObjectUtil.isEqual(packagemenu.getPackageId(), packageId)) {
			throw new RuntimeException("Invalid Request - Packagemenu does not belong to diagnostic center");
		}
		return packagesubmenuDAO.findByProperty(Packagesubmenu.FIELD_packageMenuId, packageMenuId);
	}

	@Override
	@Transactional
	public Packagemenu findBypackageMenuId(Long packageMenuId) {
		List<Criterion> criterions = new ArrayList<>();
		criterions.add(Restrictions.eq(Packagemenu.FIELD_packageMenuId, packageMenuId));
		criterions.add(Restrictions.eq(Packagemenu.FIELD_active, 'Y'));
		List<Packagemenu> packagemenus = findByCrieria(criterions);
		if (Collections.isEmpty(packagemenus)) {
			return null;
		}
		return packagemenus.get(0);
	}

	@Override
	@Transactional
	public Response<List<Packagemenu>> searchPackagesmenu(PackagemenuSearchDTO packagemenuSearchDTO) {

		List<Criterion> criterions = new ArrayList<>();

		if (Objects.nonNull(packagemenuSearchDTO.getMenuName())) {
			criterions.add(Restrictions.eq(Packagemenu.FIELD_menuName, packagemenuSearchDTO.getMenuName()));
		}

		if (Objects.nonNull(packagemenuSearchDTO.getPackageId())) {
			criterions.add(Restrictions.eq(Packagemenu.FIELD_packageId, packagemenuSearchDTO.getPackageId()));
		}

		if (Objects.nonNull(packagemenuSearchDTO.getMenuId())) {
			criterions.add(Restrictions.eq(Packagemenu.FIELD_menuId, packagemenuSearchDTO.getMenuId()));
		}

		if (Objects.nonNull(packagemenuSearchDTO.getMenuName())) {
			criterions.add(Restrictions.eq(Packagemenu.FIELD_menuName, packagemenuSearchDTO.getMenuName()));
		}
		criterions.add(Restrictions.eq(Packagemenu.FIELD_active,'Y'));
		return findByCrieria(criterions, null, packagemenuSearchDTO.getPagenum(), packagemenuSearchDTO.getMaxresult());

	}

}