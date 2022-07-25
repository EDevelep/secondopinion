package org.secondopinion.utilities.jobs.dao.impl;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
import org.secondopinion.utilities.jobs.domain.BaseLookupstatic;
import org.secondopinion.utilities.jobs.dto.Lookupstatic;
import org.springframework.stereotype.Repository;

@Repository
public class LookupstaticDAOHibernateImpl extends BaseLookupstaticDAOHibernate{

	@Override
	@Transactional
	public List<Lookupstatic> getDiagnosticcenterTest(String type, String module) {
		List<Criterion> criterions = new ArrayList<>();
		criterions.add(Restrictions.eq(BaseLookupstatic.FIELD_lookupType, type));
		criterions.add(Restrictions.eq(BaseLookupstatic.FIELD_datasetType, module));
		return findByCrieria(criterions);
	}
}