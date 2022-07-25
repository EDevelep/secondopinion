package org.secondopinion.doctor.daoimpl;


import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
import org.secondopinion.doctor.domain.BaseSpecialization;
import org.secondopinion.doctor.dto.Specialization;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

@Repository
public class SpecializationDAOHibernateImpl extends BaseSpecializationDAOHibernate{

	@Override
	@Transactional(readOnly = true)
	public Specialization findspecializationById(Long specializationId) {
		List<Criterion> criterions = new ArrayList<>();
		criterions.add(Restrictions.eq(BaseSpecialization.FIELD_specializationId, specializationId));
		criterions.add(Restrictions.eq(BaseSpecialization.FIELD_active, 'Y'));
		List<Specialization> associations=findByCrieria(criterions);
		if(CollectionUtils.isEmpty(associations)) return null;
		return associations.get(0);
	}


	
	
	@Override
	@Transactional(readOnly = true)
	public List<Specialization> findSpecializationsByDoctorId(Long doctorId) {
		List<Criterion> criterions = new ArrayList<>();
		criterions.add(Restrictions.eq(BaseSpecialization.FIELD_doctorId, doctorId));
		criterions.add(Restrictions.eq(BaseSpecialization.FIELD_active, 'Y'));
	
		return findByCrieria(criterions);
	}
	
	@Override
	@Transactional
	public void save(Specialization specialization) {
		
		if(Objects.isNull(specialization.getSpecializationId())) {
			specialization.setActive('Y');
		}
		
		super.save(specialization);
	}
}