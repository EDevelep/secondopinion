package org.secondopinion.diagnosticcenter.dao.impl.hibernate;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
import org.secondopinion.diagnosticcenter.dto.Userrole;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class UserroleDAOHibernateImpl extends BaseUserroleDAOHibernate{

	
	@Override
	public List<Userrole> getByRoleIdAndUserId(Integer roleId, Long diagnosticcenterUserId) {
		List<Criterion> criterions = new ArrayList<>();
		if(Objects.nonNull(roleId)) {
			criterions.add(Restrictions.eq(Userrole.FIELD_roleId, roleId));
		}
		if(Objects.nonNull(diagnosticcenterUserId)) {
			criterions.add(Restrictions.eq(Userrole.FIELD_diagnosticcenterUserId, diagnosticcenterUserId));
		}
		
		return findByCrieria(criterions);
	}
	
	@Override
	@Transactional
   public void save(Userrole userRole) {
	   if(Objects.isNull(userRole.getUserRoleId())) {
		   userRole.setActive('Y');
	   }
	   super.save(userRole);
   }
}