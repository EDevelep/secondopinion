package org.secondopinion.diagnosticcenter.dao.impl.hibernate;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
import org.secondopinion.diagnosticcenter.dto.Role;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class RoleDAOHibernateImpl extends BaseRoleDAOHibernate{

	
	@Override
	@Transactional(readOnly=true)
	public Role getByRoleName(String rolename) {
		
		return findOneByProperty(Role.FIELD_roleName, rolename);
	}

	@Override
	@Transactional(readOnly=true)
	public List<Role> getByUserroles(List<Integer> roleIds) {
		List<Criterion> criterions = new ArrayList<>();
		criterions.add(Restrictions.in(Role.FIELD_roleId, roleIds));
		criterions.add(Restrictions.eq(Role.FIELD_active, 'Y'));
		
		return findByCrieria(criterions);
	}
}