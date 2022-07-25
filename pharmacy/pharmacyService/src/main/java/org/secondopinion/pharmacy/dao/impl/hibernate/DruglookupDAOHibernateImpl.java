package org.secondopinion.pharmacy.dao.impl.hibernate; 


import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
import org.secondopinion.pharmacy.dto.Druglookup;
import org.secondopinion.pharmacy.dto.Invoice;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

@Repository
public class DruglookupDAOHibernateImpl extends BaseDruglookupDAOHibernate{
	
	@Override
	@Transactional
	public void save(Druglookup druglookup) {
		if(Objects.isNull(druglookup.getDrugid())) {
			druglookup.setActive('Y');
		}
		
		super.save(druglookup);
	}

	@Override
	public List<Druglookup> fetchDrugByPharmacyId(Long pharmacyId) {
		List<Criterion> criterions = new ArrayList<>();
		criterions.add(Restrictions.eq(Druglookup.FIELD_pharmacyId,pharmacyId));
		criterions.add(Restrictions.eq(Druglookup.FIELD_active, 'Y'));
		return findByCrieria(criterions);
	
	}
}