package org.secondopinion.patient.dao.impl.hibernate;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
import org.secondopinion.patient.dto.Familyhistory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class FamilyhistoryDAOHibernateImpl extends BaseFamilyhistoryDAOHibernate {

	@Override
	@Transactional
	public void save(Familyhistory familyhistory) {
		if(Objects.isNull(familyhistory.getFamilyhistoryid())) {
			familyhistory.setActive('Y');
		}
		super.save(familyhistory);
	}

	
	@Override
	@Transactional
	public List<Familyhistory> findPersonalsymptomsByUserId(Long userid) {
		List<Criterion> criterions = new ArrayList<>();
		criterions.add(Restrictions.eq(Familyhistory.FIELD_userId, userid));
		criterions.add(Restrictions.eq(Familyhistory.FIELD_active, 'Y'));
		return findByCrieria(criterions);
	}
}