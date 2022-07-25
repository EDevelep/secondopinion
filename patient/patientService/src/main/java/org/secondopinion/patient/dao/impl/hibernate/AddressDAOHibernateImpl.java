package org.secondopinion.patient.dao.impl.hibernate;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
import org.secondopinion.patient.domain.BaseAddress;
import org.secondopinion.patient.dto.Address;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

@Repository
public class AddressDAOHibernateImpl extends BaseAddressDAOHibernate {
	
	
	@Override
	@Transactional
	public void save( Address address ) {
		if(Objects.isNull(address.getAddressId())) {
			address.setActive('Y');
		}
		super.save(address);
	}

	@Override
	public Address getByUserIdAndAddressId(Long patientId, Long shippingAddressId) {
		List<Criterion> criterions = new ArrayList<>();
		criterions.add(Restrictions.eq(BaseAddress.FIELD_userId, patientId));
		criterions.add(Restrictions.eq(BaseAddress.FIELD_addressId, shippingAddressId));
		criterions.add(Restrictions.eq(BaseAddress.FIELD_active, 'Y'));
		
		List<Address> addresses=findByCrieria(criterions);
		
		if(CollectionUtils.isEmpty(addresses)) {
			return null;
		}
		
		return addresses.get(0);
	}

	@Override
	public List<Address> getAddresByUserId(Long userId) {
		List<Criterion> criterions = new ArrayList<>();
		criterions.add(Restrictions.eq(BaseAddress.FIELD_userId, userId));
		criterions.add(Restrictions.eq(BaseAddress.FIELD_active, 'Y'));
		return findByCrieria(criterions);
	}

	@Override
	public Address getAddresByaddressId(Long addressId) {
		List<Criterion> criterions = new ArrayList<>();
		criterions.add(Restrictions.eq(BaseAddress.FIELD_addressId, addressId));
		criterions.add(Restrictions.eq(BaseAddress.FIELD_active, 'Y'));
		
		List<Address> addresses=findByCrieria(criterions);
		
		if(CollectionUtils.isEmpty(addresses)) {
			return null;
		}
		
		return addresses.get(0);
	}
}