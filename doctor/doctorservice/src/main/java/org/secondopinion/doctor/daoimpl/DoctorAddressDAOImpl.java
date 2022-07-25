package org.secondopinion.doctor.daoimpl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Objects;

import org.secondopinion.doctor.domain.BaseDoctorAddress;
import org.secondopinion.doctor.dto.DoctorAddress;

import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

@Repository
public class DoctorAddressDAOImpl extends BaseDoctorAddressDAOHibernateImpl {

	@Override
	@Transactional(readOnly = true)
	public DoctorAddress findDoctorAddressById(Long addressId) {
		List<Criterion> criterions = new ArrayList<>();
		criterions.add(Restrictions.eq(BaseDoctorAddress.FIELD_addressId,addressId));
		criterions.add(Restrictions.eq(BaseDoctorAddress.FIELD_active, 'Y'));
		List<DoctorAddress> doctorAddresses=findByCrieria(criterions);
		if(CollectionUtils.isEmpty(doctorAddresses)) return null;
		return doctorAddresses.get(0);
	}
	


	@Override
	@Transactional(readOnly = true)
	public Collection<DoctorAddress> findDoctorAddressBydoctorId(Long doctorId) {
		List<Criterion> criterions = new ArrayList<>();
		criterions.add(Restrictions.eq(BaseDoctorAddress.FIELD_doctorId,doctorId));
		criterions.add(Restrictions.eq(BaseDoctorAddress.FIELD_active, 'Y'));
		return findByCrieria(criterions);
	}

	@Override
	@Transactional
	public void save(DoctorAddress doctorAddress) {
		
		if(Objects.isNull(doctorAddress.getAddressId())) {
			doctorAddress.setActive('Y');
		}
		
		super.save(doctorAddress);
	}
}
