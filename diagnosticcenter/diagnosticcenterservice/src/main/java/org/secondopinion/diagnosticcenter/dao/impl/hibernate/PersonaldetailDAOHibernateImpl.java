package org.secondopinion.diagnosticcenter.dao.impl.hibernate;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


import org.hibernate.criterion.Criterion;

import org.hibernate.criterion.Restrictions;
import org.secondopinion.diagnosticcenter.dto.Personaldetail;
import org.springframework.cglib.core.CollectionUtils;
import org.springframework.stereotype.Repository;


@Repository
public class PersonaldetailDAOHibernateImpl extends BasePersonaldetailDAOHibernate{

	@Override
	public Personaldetail findPersonalDetailBydiagnosticcenterId(Long diagnosticcenteruserId) {
		List<Criterion> criterions = new ArrayList<>();
		criterions.add(Restrictions.eq(Personaldetail.FIELD_diagnosticcenteruserId, diagnosticcenteruserId));
		criterions.add(Restrictions.eq(Personaldetail.FIELD_active, 'Y'));
		
		List<Personaldetail> personaldetails=findByCrieria(criterions);
		if(org.springframework.util.CollectionUtils.isEmpty(personaldetails)) {
			return  null;
		}
		return personaldetails.get(0);
	}

	

	
}