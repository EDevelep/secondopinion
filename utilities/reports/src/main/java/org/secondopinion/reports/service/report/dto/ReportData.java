package org.secondopinion.reports.service.report.dto;

import java.util.List;

import org.secondopinion.domain.BaseDomainObject;
import org.secondopinion.domain.KeyField;
import org.secondopinion.reports.dto.Report;

public class ReportData extends BaseDomainObject<ReportKey>{
	private int companyId;
	private String userId;
	private Long reportId;
	private String reportName;
	private String type;
	private String graphType;
	private String header;
	
	private String data;
	private List<Object[]> countsData;
	private long initializedTime;
	
	public ReportData(ReportKey reportKey, Report report) {
		this.companyId = reportKey.getCompanyId();
		this.userId = reportKey.getUserId();
		this.reportId = reportKey.getReportId();
		this.type = report.getType();
		this.graphType = report.getGraphType();
		this.reportName = report.getName();
		this.initializedTime = System.currentTimeMillis();
	}
	
	public String getType() {
		return type;
	}
	
	public String getGraphType() {
		return graphType;
	}
	
	@Override
	public boolean isKeyNull() {
		return false;
	}

	@Override
	public ReportKey getId() {
		return new ReportKey(companyId, userId, reportId);
	}

	@Override
	public List<KeyField> getKeyField() {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * @return the data
	 */
	public String getData() {
		return data;
	}

	/**
	 * @param data the data to set
	 */
	public void setData(String data) {
		this.data = data;
	}

	/**
	 * @return the countsData
	 */
	public List<Object[]> getCountsData() {
		return countsData;
	}

	/**
	 * @param countsData the countsData to set
	 */
	public void setCountsData(List<Object[]> objectData) {
		this.countsData = objectData;
	}

	public String getReportName() {
		return reportName;
	}

	public void setReportName(String reportName) {
		this.reportName = reportName;
	}

	public String getHeader() {
		return header;
	}

	public void setHeader(String header) {
		this.header = header;
	}

	public long getElapsedTime(){
		return System.currentTimeMillis() - initializedTime;
	}

	public boolean hasPassedElapsedTime() {
		return (getElapsedTime() > 30000);
	}
}
