package org.secondopinion.pharmacy.dao.impl.hibernate;

import java.util.ArrayList;

import java.util.List;

import org.hibernate.criterion.Criterion;

import org.hibernate.criterion.Restrictions;

import org.secondopinion.pharmacy.dto.Personaldetail;

import org.springframework.stereotype.Repository;

@Repository
public class PersonaldetailDAOHibernateImpl extends BasePersonaldetailDAOHibernate {

	@Override
	public Personaldetail findPersonalDetailBypharmacyId(Long pharmacyuserId) {
		List<Criterion> criterions = new ArrayList<>();
		criterions.add(Restrictions.eq(Personaldetail.FIELD_pharmacyuserId, pharmacyuserId));
		criterions.add(Restrictions.eq(Personaldetail.FIELD_active, 'Y'));
		List<Personaldetail> personaldetails = findByCrieria(criterions);
		if (personaldetails != null & personaldetails.size() > 0) {
			return personaldetails.get(0);
		}
		return null;

	}

}