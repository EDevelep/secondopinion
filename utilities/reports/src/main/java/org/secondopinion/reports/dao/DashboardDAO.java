package org.secondopinion.reports.dao; 

import java.util.List;

import org.secondopinion.dao.IDAO;
import org.secondopinion.reports.dto.Dashboard;

public interface DashboardDAO extends IDAO<Dashboard,Integer >{

	List<Dashboard> getAllDefaultDashboards();

	List<Dashboard> getDashboards(int intValue);

	void removeReport(int dashboardId, Long dashboardReportsId);

	List<Dashboard> findDashboardsAssociateForReport(Long reportsId);

	List<Dashboard> getDashboardsByRole(List<Integer> roleIds, Integer companyId);
	
	List<Dashboard> getDefaultDashboardsByRole(Integer roleId);
}