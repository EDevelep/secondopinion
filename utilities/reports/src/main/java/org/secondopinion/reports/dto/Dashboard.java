package org.secondopinion.reports.dto; 


import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.secondopinion.reports.domain.BaseDashboard; 

@Entity 
@Table (name="dashboard")
public class Dashboard extends BaseDashboard{
	private List<DashboardReports> dashboardReports;

	/**
	 * @return the dashboardReports
	 */
	@Transient
	public List<DashboardReports> getDashboardReports() {
		return dashboardReports;
	}

	/**
	 * @param dashboardReports the dashboardReports to set
	 */
	public void setDashboardReports(List<DashboardReports> dashboardReports) {
		this.dashboardReports = dashboardReports;
	}
	
	@Transient
	public boolean hasReportsAssociated(){
		return !(dashboardReports == null || dashboardReports.size() == 0);
	}
	
	public void setDashboardIdForReports(){
		if(hasReportsAssociated()){
			for(DashboardReports report : dashboardReports){
				report.setDashboardId(getDashboardId());
			}
		}
	}
	
}