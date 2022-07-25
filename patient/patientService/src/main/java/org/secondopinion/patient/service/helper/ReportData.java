package org.secondopinion.patient.service.helper;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TimeZone;

import javax.persistence.Transient;




public class ReportData {
	public enum REPORT_TYPE {
		COUNTS, GRAPH, STATS;
	}

	private Map<String, Object> params;
	private static List<ReportData> reportDatas;
	private Long userId;
	private String vitalValue;
	private Long reportId;
	private String reportName;
	private String type;
	private String graphType;
	private String header;

	public ReportData(Long userId, Long reportId, String reportName, String type, String graphType, String header,
			String data, List<Object[]> countsData, long initializedTime, String vitalValue,
			Map<String, Object> params) {
		super();
		this.userId = userId;
		this.reportId = reportId;
		this.reportName = reportName;
		this.type = type;
		this.graphType = graphType;
		this.header = header;
		this.data = data;
		this.countsData = countsData;
		this.initializedTime = initializedTime;
		this.vitalValue = vitalValue;
		this.params = params;
	}

	public ReportData() {

	}

	private String data;
	private List<Object[]> countsData;
	private long initializedTime;

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public Long getReportId() {
		return reportId;
	}

	public void setReportId(Long reportId) {
		this.reportId = reportId;
	}

	public String getReportName() {
		return reportName;
	}

	public void setReportName(String reportName) {
		this.reportName = reportName;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getGraphType() {
		return graphType;
	}

	public void setGraphType(String graphType) {
		this.graphType = graphType;
	}

	public String getHeader() {
		return header;
	}

	public void setHeader(String header) {
		this.header = header;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

	public List<Object[]> getCountsData() {
		return countsData;
	}

	public void setCountsData(List<Object[]> countsData) {
		this.countsData = countsData;
	}

	public long getElapsedTime() {
		return System.currentTimeMillis() - initializedTime;
	}

	public boolean hasPassedElapsedTime() {
		return (getElapsedTime() > 30000);
	}

	public String getVitalValue() {
		return vitalValue;
	}

	public void setVitalValue(String vitalValue) {
		this.vitalValue = vitalValue;
	}

	public long getInitializedTime() {
		return initializedTime;
	}

	public List<ReportData> getReportDatas() {
		return reportDatas;
	}

	public void setReportDatas(List<ReportData> reportDatas) {
		this.reportDatas = reportDatas;
	}

	public Map<String, Object> getParams() {
		return params;
	}

	public void setInitializedTime(long initializedTime) {
		this.initializedTime = initializedTime;
	}

	public static Map<String, Object> prepareReportParams(TimeZone timezone) {
		Map<String, Object> params = new HashMap<>();

		for (ReportData parameter : reportDatas) {
			String paramName = parameter.getVitalValue();
			Long userId=parameter.getUserId();
			if (!params.containsKey(paramName)) {
				PARAMETER param = PARAMETER.valueOf(parameter.getVitalValue());
				Object paramVal = param.getParamValue(parameter, timezone);

				params.put(paramName, paramVal);
			}
		}
		return params;
	}

	public void setParams(Map<String, Object> params) {
		this.params = params;
	}

}
