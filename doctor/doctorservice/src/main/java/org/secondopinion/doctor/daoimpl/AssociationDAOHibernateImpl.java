package org.secondopinion.doctor.daoimpl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Objects;

import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
import org.secondopinion.doctor.domain.BaseAssociation;
import org.secondopinion.doctor.dto.Association;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

@Repository
public class AssociationDAOHibernateImpl extends BaseAssociationDAOHibernate{

	@Override
	@Transactional(readOnly = true)
	public List<Association> findassociationById(Long entityId) {
		List<Criterion> criterions = new ArrayList<>();
		criterions.add(Restrictions.eq(BaseAssociation.FIELD_entityId, entityId));
		criterions.add(Restrictions.eq(BaseAssociation.FIELD_active, 'Y'));
		List<Association> associations=findByCrieria(criterions);
		
		return associations;
	}
	
	@Override
	@Transactional
	public void save(Association association) {
		
		if(Objects.isNull(association.getAssociationId())) {
			association.setActive('Y');
		}
		
		super.save(association);
	}
	
	@Override
	@Transactional(readOnly = true)
	public Collection<Association> findAssocaiationByDoctorId(Long doctorId) {
		List<Criterion> criterions = new ArrayList<>();
		criterions.add(Restrictions.eq(BaseAssociation.FIELD_doctorId, doctorId));
		criterions.add(Restrictions.eq(BaseAssociation.FIELD_active, 'Y'));
		return findByCrieria(criterions);
	}
}