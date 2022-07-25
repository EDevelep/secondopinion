package org.secondopinion.reports.dao.impl.hibernate;

import java.util.List;

import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
import org.secondopinion.reports.dto.DashboardRuleAssociation;
import org.springframework.transaction.annotation.Transactional;

public class DashboardRuleAssociationDAOHibernateImpl extends BaseDashboardRuleAssociationDAOHibernate{

	@Override
	@Transactional(readOnly=true)
	public List<DashboardRuleAssociation> getDashboardsByRole(List<Integer> roleIds, Integer companyId) {
		Criterion companyCritrion = Restrictions.eq(DashboardRuleAssociation.FIELD_companyId, companyId);
		
		Criterion rolesCritrion = Restrictions.in(DashboardRuleAssociation.FIELD_roleId, roleIds);
		
		return findByCrieria(companyCritrion, rolesCritrion);
	}

	@Override
	@Transactional(readOnly=true)
	public List<DashboardRuleAssociation> getDefaultDashboardsByRole(Integer roleId) {
		Criterion roleCritrion = Restrictions.eq(DashboardRuleAssociation.FIELD_roleId, roleId);
		
		Criterion defaultCritrion = Restrictions.eq(DashboardRuleAssociation.FIELD_isDefault, 'Y');
		
		return findByCrieria(roleCritrion, defaultCritrion);
	}

	@Override
	@Transactional(readOnly=true)
	public List<DashboardRuleAssociation> getDefaultDashboardsByRoles(List<Integer> roleIds) {
		Criterion roleCritrion = Restrictions.in(DashboardRuleAssociation.FIELD_roleId, roleIds);
		
		Criterion defaultCritrion = Restrictions.eq(DashboardRuleAssociation.FIELD_isDefault, 'Y');
		
		return findByCrieria(roleCritrion, defaultCritrion);
	}
	
}