package org.secondopinion.pharmacy.dao.impl.hibernate;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
import org.secondopinion.pharmacy.dto.Userrole;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class UserroleDAOHibernateImpl extends BaseUserroleDAOHibernate{

	@Override
	@Transactional(readOnly=true)
	public List<Userrole> getByRoleIdAndUserId(Integer roleId, Long pharmacyUserId) {
		List<Criterion> criterions = new ArrayList<>();
		if(Objects.nonNull(roleId)) {
			criterions.add(Restrictions.eq(Userrole.FIELD_roleId, roleId));
		}
		if(Objects.nonNull(pharmacyUserId)) {
			criterions.add(Restrictions.eq(Userrole.FIELD_pharmacyUserId, pharmacyUserId));
		}
		criterions.add(Restrictions.eq(Userrole.FIELD_active, 'Y'));
		return findByCrieria(criterions);
	}

	@Override
	@Transactional
	public void save(Userrole userrole) {
		if(Objects.isNull(userrole.getUserRoleId())) {
			userrole.setActive('Y');
		}
		super.save(userrole);
	}
}