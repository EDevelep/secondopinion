package org.secondopinion.reports.dao; 

import java.util.List;

import org.secondopinion.dao.IDAO;
import org.secondopinion.reports.dto.DashboardReports;

public interface DashboardReportsDAO extends IDAO<DashboardReports,Long >{

	List<DashboardReports> getReportsForDashboard(Integer dashboardId);
}