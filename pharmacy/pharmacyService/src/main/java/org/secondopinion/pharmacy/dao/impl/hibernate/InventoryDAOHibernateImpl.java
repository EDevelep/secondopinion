package org.secondopinion.pharmacy.dao.impl.hibernate;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
import org.secondopinion.pharmacy.dto.Inventory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class InventoryDAOHibernateImpl extends BaseInventoryDAOHibernate {

	@Override
	@Transactional
	public List<Inventory> getAllInventoryByPharmacyId(Long pharmacyId) {
		List<Criterion> criterions = new ArrayList<>();
		criterions.add(Restrictions.eq(Inventory.FIELD_pharmacyId, pharmacyId));
		criterions.add(Restrictions.eq(Inventory.FIELD_active, 'Y'));
		return findByCrieria(criterions);
	}
}