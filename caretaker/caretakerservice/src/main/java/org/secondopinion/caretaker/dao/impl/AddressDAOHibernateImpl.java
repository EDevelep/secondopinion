package org.secondopinion.caretaker.dao.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
import org.secondopinion.caretaker.dto.Address;
import org.springframework.stereotype.Repository;

@Repository
public class AddressDAOHibernateImpl extends BaseAddressDAOHibernate {

	@Override
	public Collection<Address> findCaretakerAddressBycaretakerId(Long caretakerId) {
		List<Criterion> criterions = new ArrayList<>();
		criterions.add(Restrictions.eq(Address.FIELD_caretakerId, caretakerId));
		criterions.add(Restrictions.eq(Address.FIELD_active, 'Y'));
		return findByCrieria(criterions);
	}
}