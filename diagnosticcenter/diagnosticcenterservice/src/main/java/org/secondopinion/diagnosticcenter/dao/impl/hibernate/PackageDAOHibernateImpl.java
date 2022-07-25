package org.secondopinion.diagnosticcenter.dao.impl.hibernate;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;
import org.secondopinion.diagnosticcenter.dao.PackagemenuDAO;
import org.secondopinion.diagnosticcenter.dto.Packagemenu;
import org.secondopinion.request.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import io.netty.util.internal.StringUtil;

import org.secondopinion.diagnosticcenter.dto.Package;
import org.secondopinion.diagnosticcenter.dto.PackageSearch;
import org.secondopinion.diagnosticcenter.dto.PackageSearchDTO;;
@Repository
public class PackageDAOHibernateImpl extends BasePackageDAOHibernate{
	
	
	
	@Autowired
	private PackagemenuDAO packagemenuDAO;
	
	@Override
	@Transactional
	public void save(Package packaged) {
		packaged.setActive('Y');
		super.save(packaged);
		packaged.getMenuItems().forEach(n ->{n.setPackageId(packaged.getPackageId());});
		packagemenuDAO.save(packaged.getMenuItems());
	}
	

	@Override
	@Transactional
	public Package getPackageDetails(Long diagnosticCenterId, Long packageId) {
		Package package1 = findById(packageId);
		
		package1.setMenuItems(packagemenuDAO.findByProperty(Packagemenu.FIELD_packageId, packageId));
		return package1;
	}


	@Override
	@Transactional(readOnly = true)
	public List<Package> findPackageBydiagnosticCenterAddressId(Long diagnosticCenterAddressId) {
		List<Criterion> criterions = new ArrayList<>();
		if(Objects.nonNull(diagnosticCenterAddressId)) {
			criterions.add(Restrictions.eq(Package.FIELD_diagnosticCenterAddressId, diagnosticCenterAddressId));
		}
		
		criterions.add(Restrictions.eq(Package.FIELD_active, 'Y'));
		return findByCrieria(criterions);
	}


	@Override
	@Transactional(readOnly = true)
	public List<Package> searchPackage(PackageSearchDTO packageSearch) {
		List<Criterion> criterions = new ArrayList<>();
		if(Objects.nonNull(packageSearch.getDiagnosticCenterAddressId())) {
			criterions.add(Restrictions.eq(Package.FIELD_diagnosticCenterAddressId, packageSearch.getDiagnosticCenterAddressId()));
		}
		if(!StringUtil.isNullOrEmpty(packageSearch.getPackageName())) {
			criterions.add(Restrictions.ilike(Package.FIELD_packageName, packageSearch.getPackageName(), MatchMode.ANYWHERE));
		}
		criterions.add(Restrictions.eq(Package.FIELD_active, 'Y'));
		return findByCrieria(criterions);
	}


	@Override
	@Transactional
	public Response<List<Package>> searchPackageitem(PackageSearch packageSearch) {
		
        List<Criterion> criterions = new ArrayList<>();
		
		if(Objects.nonNull(packageSearch.getDiagnosticCenterAddressId())) {
			criterions.add(Restrictions.eq(Package.FIELD_diagnosticCenterAddressId, packageSearch.getDiagnosticCenterAddressId()));
		}
		
		if(Objects.nonNull(packageSearch.getPackageName())) {
			criterions.add(Restrictions.eq(Package.FIELD_packageName, packageSearch.getPackageName()));
		}
		
		if(Objects.nonNull(packageSearch.getDescription())) {
			criterions.add(Restrictions.eq(Package.FIELD_description, packageSearch.getDescription()));
		}
		
		if(Objects.nonNull(packageSearch.getPrice())) {
			criterions.add(Restrictions.eq(Package.FIELD_price, packageSearch.getPrice()));
		}
		
		
		return findByCrieria(criterions,null,packageSearch.getPagenumber(),packageSearch.getMaxresult());
	}
	
	@Override
	@Transactional
	public void deletePackage(Package packaged) {
		super.save(packaged);
		
	}
}