package org.secondopinion.reports.dto;


/**
 * @author Ram Swarna
 *
 */
public class ReportParam {
	private String paramName;
	private Object paramValue;

	public String getParamName() {
		return paramName;
	}

	public void setParamName(String paramName) {
		this.paramName = paramName;
	}

	public Object getParamValue() {
		return paramValue;
	}

	public void setParamValue(Object paramValue) {
		this.paramValue = paramValue;
	}
}