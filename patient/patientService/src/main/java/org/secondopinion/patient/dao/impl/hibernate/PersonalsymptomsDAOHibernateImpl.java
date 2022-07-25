package org.secondopinion.patient.dao.impl.hibernate;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
import org.secondopinion.dao.exception.DataAccessException;
import org.secondopinion.patient.domain.BasePersonalsymptoms;
import org.secondopinion.patient.dto.Personalsymptoms;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class PersonalsymptomsDAOHibernateImpl extends BasePersonalsymptomsDAOHibernate {

	

	
	@Override
	@Transactional
	public void save( Personalsymptoms personalsymptoms) throws DataAccessException {
		if(Objects.isNull(personalsymptoms.getPersonalsymptomsid())) {
			personalsymptoms.setActive('Y');
		}
		super.save(personalsymptoms);
	}
	
	
	
	@Override
	@Transactional
	public List<Personalsymptoms> findFamilyhistoryByUserId(Long userid) {
		List<Criterion> criterions = new ArrayList<>();
		criterions.add(Restrictions.eq(BasePersonalsymptoms.FIELD_userId, userid));
		criterions.add(Restrictions.eq(BasePersonalsymptoms.FIELD_active, 'Y'));
		return findByCrieria(criterions);
	}
	
	
	
}