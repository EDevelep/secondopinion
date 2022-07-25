package org.secondopinion.diagnosticcenter.dao.impl.hibernate;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
import org.secondopinion.diagnosticcenter.dto.Packagemenu;
import org.secondopinion.diagnosticcenter.dto.Packagesubmenu;
import org.secondopinion.diagnosticcenter.dto.PackagesubmenuSearchDTO;
import org.secondopinion.request.Response;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class PackagesubmenuDAOHibernateImpl extends BasePackagesubmenuDAOHibernate{
	
	
	public void save(Packagesubmenu packagesubmenu) {
		
		if(Objects.isNull(packagesubmenu.getPackageSubMenuId())) {
			packagesubmenu.setActive('Y');
		}
		packagesubmenu.setActive('Y');
		super.save(packagesubmenu);
	}

	@Override
	@Transactional
	public Response<List<Packagesubmenu>> searchPackagesubmenu(PackagesubmenuSearchDTO packagesubmenuSearchDTO) {
		List<Criterion> criterions = new ArrayList<>();

		if (Objects.nonNull(packagesubmenuSearchDTO.getServiceName())) {
			criterions.add(Restrictions.eq(Packagesubmenu.FIELD_serviceName, packagesubmenuSearchDTO.getServiceName()));
		}

		if (Objects.nonNull(packagesubmenuSearchDTO.getPackageMenuId())) {
			criterions.add(Restrictions.eq(Packagesubmenu.FIELD_packageMenuId, packagesubmenuSearchDTO.getPackageMenuId()));
		}

		if (Objects.nonNull(packagesubmenuSearchDTO.getSubMenuId())) {
			criterions.add(Restrictions.eq(Packagesubmenu.FIELD_subMenuId, packagesubmenuSearchDTO.getSubMenuId()));
		}

		criterions.add(Restrictions.eq(Packagesubmenu.FIELD_active,'Y'));
		return findByCrieria(criterions, null, packagesubmenuSearchDTO.getPageNum(), packagesubmenuSearchDTO.getMaxresult());
	}
}