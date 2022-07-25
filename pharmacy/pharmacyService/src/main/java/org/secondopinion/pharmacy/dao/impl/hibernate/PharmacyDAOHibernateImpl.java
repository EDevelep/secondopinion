package org.secondopinion.pharmacy.dao.impl.hibernate;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
import org.hibernate.type.StandardBasicTypes;
import org.hibernate.type.Type;
import org.secondopinion.common.dto.RatingsDTO;

import org.secondopinion.pharmacy.dao.PharmacyDAO;

import org.secondopinion.pharmacy.domain.BasePharmacy;

import org.secondopinion.pharmacy.dto.Pharmacy;
import org.secondopinion.pharmacy.dto.PharmacySearchRequest;
import org.secondopinion.pharmacy.dto.Pharmacyratings;
import org.secondopinion.request.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

@Repository
public class PharmacyDAOHibernateImpl extends BasePharmacyDAOHibernate {

	@Override
	@Transactional(readOnly = true)
	public Pharmacy readByPharmacyId(Long pharmacyId) {
		List<Criterion> criterions = new ArrayList<>();
		criterions.add(Restrictions.eq(Pharmacy.FIELD_pharmacyId, pharmacyId));
		criterions.add(Restrictions.eq(Pharmacy.FIELD_active, 'Y'));

		return getPharmacyFromList(findByCrieria(criterions));
	}

	private Pharmacy getPharmacyFromList(List<Pharmacy> pharamcies) {
		if (CollectionUtils.isEmpty(pharamcies)) {
			return null;
		}
		return pharamcies.get(0);
	}

	@Override
	@Transactional(readOnly = true)
	public Pharmacy readByEmailId(String emailId) {
		List<Criterion> criterions = new ArrayList<>();
		criterions.add(Restrictions.eq(Pharmacy.FIELD_emailId, emailId));
		criterions.add(Restrictions.eq(Pharmacy.FIELD_active, 'Y'));

		return getPharmacyFromList(findByCrieria(criterions));
	}

	@Override
	@Transactional(readOnly = true)
	public Pharmacy readByLicenceNumber(String licenseNumber) {
		List<Criterion> criterions = new ArrayList<>();
		criterions.add(Restrictions.eq(BasePharmacy.FIELD_licenseNumber, licenseNumber));
		criterions.add(Restrictions.eq(Pharmacy.FIELD_active, 'Y'));

		return getPharmacyFromList(findByCrieria(criterions));
	}

	@Override
	@Transactional
	public void save(Pharmacy pharmacy) {
		if (Objects.isNull(pharmacy.getPharmacyId())) {
			pharmacy.setActive('Y');
		}
		super.save(pharmacy);
	}

	@Override
	public void updatepharmacy(Long pharmacyid, RatingsDTO values) {

		Pharmacy pharmacy = findById(pharmacyid);
		pharmacy.setAveragerating(values.getAverage());
		save(pharmacy);

	}

	@Override
	@Transactional
	public List<Pharmacy> readByPharmacyByaddrssId(List<Long> pharmacyaddressIds) {
		List<Criterion> criterions = new ArrayList<>();
		criterions.add(Restrictions.in(Pharmacy.FIELD_pharmacyaddressId, pharmacyaddressIds));
		criterions.add(Restrictions.eq(Pharmacy.FIELD_active, 'Y'));
		return findByCrieria(criterions);
	}

	@Override
	@Transactional(readOnly = true)
	public Response<List<Pharmacy>> getAllPharmacyBySearchCritieria(PharmacySearchRequest pharmacySearchRequest) {
		List<Criterion> criterions = new ArrayList<>();

		if (Objects.nonNull(pharmacySearchRequest.getPharmacyId())) {
			criterions.add(Restrictions.eq(BasePharmacy.FIELD_pharmacyId, pharmacySearchRequest.getPharmacyId()));
		}

		if (Objects.nonNull(pharmacySearchRequest.getLicenseNumber())) {
			criterions.add(Restrictions.eq(BasePharmacy.FIELD_licenseNumber, pharmacySearchRequest.getLicenseNumber()));
		}
		if (Objects.nonNull(pharmacySearchRequest.getAddressId())) {
			criterions.add(Restrictions.eq(BasePharmacy.FIELD_pharmacyaddressId, pharmacySearchRequest.getAddressId()));
		}
		if (Objects.nonNull(pharmacySearchRequest.getPharmacyName())) {
			criterions.add(Restrictions.eq(BasePharmacy.FIELD_name, pharmacySearchRequest.getPharmacyName()));
		}

		return findByCrieria(criterions, null, pharmacySearchRequest.getPagenumber(),
				pharmacySearchRequest.getMaxresult());
	}

	@Override
	@Transactional
	public Pharmacy readByphonenumber(String phone) {
		List<Criterion> criterions = new ArrayList<>();
		criterions.add(Restrictions.eq(Pharmacy.FIELD_phoneNumber, phone));
		List<Pharmacy> pharmacies = findByCrieria(criterions);
		if (pharmacies != null & pharmacies.size() > 0) {
			return pharmacies.get(0);
		}
		return null;
	}

	@Override
	@Transactional
	public List<Pharmacy> getAssoctedPharmcy(Long pharmacyadminId) {
		List<Criterion> criterions = new ArrayList<>();
		criterions.add(Restrictions.eq(Pharmacy.FIELD_pharmacyAdminId, pharmacyadminId));
		List<Pharmacy> pharmacies = findByCrieria(criterions);
		if (pharmacies != null & pharmacies.size() > 0) {
			return pharmacies;
		}
		return null;
	
	}
}
