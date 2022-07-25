package org.secondopinion.reports.dto; 


import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.secondopinion.reports.domain.BaseDashboardReports; 

@SuppressWarnings({ "serial"})
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