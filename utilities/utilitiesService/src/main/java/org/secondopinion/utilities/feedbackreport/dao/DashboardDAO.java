package org.secondopinion.utilities.feedbackreport.dao; 

import java.util.List;

import org.secondopinion.dao.IDAO;
import org.secondopinion.utilities.feedbackreport.dto.Dashboard;

public interface DashboardDAO extends IDAO<Dashboard,Integer >{

	List<Dashboard> getAllDefaultDashboards();

	List<Dashboard> getDashboards(int intValue);
}