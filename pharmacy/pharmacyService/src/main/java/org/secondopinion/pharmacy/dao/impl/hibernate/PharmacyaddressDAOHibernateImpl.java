package org.secondopinion.pharmacy.dao.impl.hibernate;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
import org.secondopinion.pharmacy.dto.Pharmacyaddress;
import org.secondopinion.request.Response;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class PharmacyaddressDAOHibernateImpl  extends BasePharmacyaddressDAOHibernate  {

	@Override
	@Transactional(readOnly = true)
	public Response<List<Pharmacyaddress>> readAllAddressesOfPharmacy(Long pharmacyId, Integer pageNum, Integer maxResults) {
		List<Criterion> criterions = new ArrayList<>();
		criterions.add(Restrictions.eq(Pharmacyaddress.FIELD_pharmacyId, pharmacyId));
		return findByCrieria(criterions, null, pageNum, maxResults);
	}

	@Override
	@Transactional(readOnly = true)
	public List<Pharmacyaddress> readByAddresseIdAndPharmacyIdAndActve(Long pharmacyId, Long addressId, Character active) {
		List<Criterion> criterions = new ArrayList<>();
		criterions.add(Restrictions.eq(Pharmacyaddress.FIELD_pharmacyId, pharmacyId));
		criterions.add(Restrictions.eq(Pharmacyaddress.FIELD_pharmacyaddressId, addressId));
		if (Objects.nonNull(active)) {
			criterions.add(Restrictions.eq(Pharmacyaddress.FIELD_active, active));
		}

		return findByCrieria(criterions);
	}

	@Override
	@Transactional
	public void save(Pharmacyaddress address) {
		if (Objects.isNull(address.getPharmacyaddressId())) {
			address.setActive('Y');
		}

		super.save(address);
	}

	@Override
	@Transactional
	public void deleteAddressesOfPharmacy(Pharmacyaddress address) {
		address.setActive('N');
		super.save(address);
	}

	@Override
	@Transactional
	public List<Pharmacyaddress> getByIds(List<Long> pharmacyaddressIds) {
		List<Criterion> criterions = new ArrayList<>();
		criterions.add(Restrictions.eq(Pharmacyaddress.FIELD_pharmacyaddressId, pharmacyaddressIds));
		criterions.add(Restrictions.eq(Pharmacyaddress.FIELD_active, 'Y'));

		return findByCrieria(criterions);
	}

	@Override
	@Transactional
	public List<Pharmacyaddress> getBypharmacyIds(List<Long> pharmacyId) {
		List<Criterion> criterions = new ArrayList<>();
		criterions.add(Restrictions.in(Pharmacyaddress.FIELD_pharmacyId, pharmacyId));
		//criterions.add(Restrictions.eq(Pharmacyaddress.FIELD_active, 'Y'));
		List<Pharmacyaddress> pharmacyaddresses=findByCrieria(criterions);
		if(pharmacyaddresses!=null & pharmacyaddresses.size()>0) {
			return  pharmacyaddresses;
		}
		return null;
	}
}