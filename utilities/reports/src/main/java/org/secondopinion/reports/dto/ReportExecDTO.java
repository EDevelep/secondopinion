package org.secondopinion.reports.dto;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * @author Ram Swarna
 *
 */
public class ReportExecDTO {
	private Long reportId;
	private List<ReportParam> reportParams;

	public Long getReportId() {
		return reportId;
	}

	public void setReportId(Long reportId) {
		this.reportId = reportId;
	}

	public List<ReportParam> getReportParams() {
		return reportParams;
	}

	public void setReportParams(List<ReportParam> reportParams) {
		this.reportParams = reportParams;
	}
	
	public Map<String, Object> getParams(){
		Map<String, Object> params = new HashMap<>();
		for(ReportParam param : reportParams){
			params.put(param.getParamName(), param.getParamValue());
		}
		
		return params;
	}
}
