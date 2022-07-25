package org.secondopinion.utilities.feedbackreport.dto;

import java.io.Serializable;

public class ReportKey implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -1623342523219435225L;
	private int companyId;
	private String userId;
	private Long reportId;
	
	public ReportKey(int companyId, String userId, Long reportId) {
		super();
		this.companyId = companyId;
		this.userId = userId;
		this.reportId = reportId;
	}
	
	public int getCompanyId() {
		return companyId;
	}
	
	public String getUserId() {
		return userId;
	}
	
	public Long getReportId() {
		return reportId;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + companyId;
		result = prime * result + ((reportId == null) ? 0 : reportId.hashCode());
		result = prime * result + ((userId == null) ? 0 : userId.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ReportKey other = (ReportKey) obj;
		if (companyId != other.companyId)
			return false;
		if (reportId == null) {
			if (other.reportId != null)
				return false;
		} else if (!reportId.equals(other.reportId))
			return false;
		if (userId == null) {
			if (other.userId != null)
				return false;
		} else if (!userId.equals(other.userId))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "ReportKey [companyId=" + companyId + ", userId=" + userId + ", reportId=" + reportId + "]";
	}
	
}