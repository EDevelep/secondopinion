package org.secondopinion.patient.dao.impl.hibernate;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
import org.secondopinion.patient.dto.Ailments;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class AilmentsDAOHibernateImpl extends BaseAilmentsDAOHibernate{

	

	@Override
	@Transactional
	public void save(Ailments ailments ) {
		if(Objects.isNull(ailments.getAilmentId())) {
			ailments.setActive('Y');
		}
		super.save(ailments);
	}
		
	
	
	@Override
	public List<Ailments> getalimentByUserId(Long userID) {
		List<Criterion> criterions = new ArrayList<>();
		criterions.add(Restrictions.eq(Ailments.FIELD_userId, userID));
		criterions.add(Restrictions.eq(Ailments.FIELD_active, 'Y'));
		return findByCrieria(criterions);
	}
}