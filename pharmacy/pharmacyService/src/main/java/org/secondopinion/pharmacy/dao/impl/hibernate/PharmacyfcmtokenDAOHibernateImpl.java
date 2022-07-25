package org.secondopinion.pharmacy.dao.impl.hibernate;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
import org.secondopinion.pharmacy.dto.Pharmacyfcmtoken;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

@Repository
public class PharmacyfcmtokenDAOHibernateImpl extends BasePharmacyfcmtokenDAOHibernate{

	@Override
	@Transactional
	public void save(Pharmacyfcmtoken dbpharmacyfcmtoken) {
		dbpharmacyfcmtoken.setActive('Y');
		super.save(dbpharmacyfcmtoken);
	}

	@Override
	@Transactional(readOnly=true)
	public List<Pharmacyfcmtoken> getByPharmacyAddressId(Long pharmacyAddressId) {
		List<Criterion> criteria = new ArrayList<>();
		criteria.add(Restrictions.eq(Pharmacyfcmtoken.FIELD_pharmacyaddressId, pharmacyAddressId));
		criteria.add(Restrictions.eq(Pharmacyfcmtoken.FIELD_active, 'Y'));
		return findByCrieria(criteria);
	}

	@Override
	public Pharmacyfcmtoken getByPharmacyAddressAnduserId(Long pharmacyuserId, Long pharmacyAddressId) {
		List<Criterion> criteria = new ArrayList<>();
		criteria.add(Restrictions.eq(Pharmacyfcmtoken.FIELD_pharmacyuserId, pharmacyuserId));
		criteria.add(Restrictions.eq(Pharmacyfcmtoken.FIELD_pharmacyaddressId, pharmacyAddressId));
		List<Pharmacyfcmtoken> pharmacyfcmtokens = findByCrieria(criteria);
		if(CollectionUtils.isEmpty(pharmacyfcmtokens)) {
			return null;
		}
		return pharmacyfcmtokens.get(0);
	}
}