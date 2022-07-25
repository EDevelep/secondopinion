package org.secondopinion.reports.dao; 

import java.util.List;

import org.secondopinion.dao.IDAO;
import org.secondopinion.reports.dto.ReportRoleAssociation;

public interface ReportRoleAssociationDAO extends IDAO<ReportRoleAssociation,Long >{

	List<Long> getReportsForRoleIds(List<Integer> roleIds, Integer companyId);

//	List<Long> getDefaultReportsForRoleId(Integer roleId);
}