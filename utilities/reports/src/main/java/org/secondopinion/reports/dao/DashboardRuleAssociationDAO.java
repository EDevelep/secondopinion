package org.secondopinion.reports.dao; 

import java.util.List;

import org.secondopinion.dao.IDAO;
import org.secondopinion.reports.dto.DashboardRuleAssociation;

public interface DashboardRuleAssociationDAO extends IDAO<DashboardRuleAssociation,Long >{

	List<DashboardRuleAssociation> getDashboardsByRole(List<Integer> roleIds, Integer companyId);

	List<DashboardRuleAssociation> getDefaultDashboardsByRole(Integer roleId);
	
	List<DashboardRuleAssociation> getDefaultDashboardsByRoles(List<Integer> roleIds);
}