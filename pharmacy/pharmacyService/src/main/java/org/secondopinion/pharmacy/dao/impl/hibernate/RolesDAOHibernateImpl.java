package org.secondopinion.pharmacy.dao.impl.hibernate;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
import org.secondopinion.pharmacy.dto.Roles;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

@Repository
public class RolesDAOHibernateImpl extends BaseRolesDAOHibernate{

	@Override
	@Transactional(readOnly=true)
	public Roles getByRoleName(String name) {
		List<Criterion> criterions = new ArrayList<>();
		criterions.add(Restrictions.eq(Roles.FIELD_roleName, name));
		criterions.add(Restrictions.eq(Roles.FIELD_active, 'Y'));
		
		List<Roles> roles = findByCrieria(criterions);
		if(CollectionUtils.isEmpty(roles)) {
			return null;
		}
		return roles.get(0);
	}

	@Override
	@Transactional(readOnly=true)
	public List<Roles> getByUserroles(List<Integer> roleIds) {
		List<Criterion> criterions = new ArrayList<>();
		criterions.add(Restrictions.in(Roles.FIELD_roleId, roleIds));
		criterions.add(Restrictions.eq(Roles.FIELD_active, 'Y'));
		
		return findByCrieria(criterions);
	}
	
	@Override
	@Transactional
	public void save(Roles roles) {
		if(Objects.isNull(roles.getRoleId())) {
			roles.setActive('Y');
		}
		super.save(roles);
	}
}