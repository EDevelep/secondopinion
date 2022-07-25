package org.secondopinion.utilities.feedbackreport.dao; 

import java.util.List;

import org.secondopinion.dao.IDAO;
import org.secondopinion.utilities.feedbackreport.dto.DashboardReports;

public interface DashboardReportsDAO extends IDAO<DashboardReports,Long >{

	List<DashboardReports> getReportsForDashboard(Integer dashboardId);
}