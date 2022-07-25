package org.secondopinion.utilities.feedbackreport.dto; 


import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.secondopinion.utilities.feedbackreport.domain.BaseDashboardReports;


@Entity 
@Table (name="dashboardreports")
public class DashboardReports extends BaseDashboardReports{
	
	private String reportName;

	public void setReportName(String name) {
		this.reportName = name;
	}
	
	@Transient
	public String getReportName() {
		return reportName;
	}
}