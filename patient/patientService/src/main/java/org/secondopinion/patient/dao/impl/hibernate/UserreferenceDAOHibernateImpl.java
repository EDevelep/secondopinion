package org.secondopinion.patient.dao.impl.hibernate;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;

import org.secondopinion.patient.dto.Userreference;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class UserreferenceDAOHibernateImpl extends BaseUserreferenceDAOHibernate {

	@Override
	@Transactional
	public List<Userreference> getallUserreference(Long userid,String referencetype ) {
		List<Criterion> criterions = new ArrayList<>();
		
		criterions.add(Restrictions.eq(Userreference.FIELD_userid, userid));
		criterions.add(Restrictions.eq(Userreference.FIELD_referencetype, referencetype));
		criterions.add(Restrictions.eq(Userreference.FIELD_active, 'Y'));
		return findByCrieria(criterions);

	}
	@Override
	@Transactional
	public List<Userreference> getallUserreferenceDiagnosticcenter(Long userid,String referencetype) {
		List<Criterion> criterions = new ArrayList<>();
		criterions.add(Restrictions.eq(Userreference.FIELD_userid, userid));
		criterions.add(Restrictions.eq(Userreference.FIELD_referencetype, referencetype));
		criterions.add(Restrictions.eq(Userreference.FIELD_active, 'Y'));
		return findByCrieria(criterions);

	}
}