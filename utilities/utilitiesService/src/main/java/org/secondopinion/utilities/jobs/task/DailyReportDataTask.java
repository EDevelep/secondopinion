package org.secondopinion.utilities.jobs.task;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.secondopinion.userMgmt.dto.Company;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import org.secondopinion.utilities.feedbackreport.dto.ReportData;
import org.secondopinion.utilities.feedbackreport.service.impl.ReportService;
import org.secondopinion.utilities.jobs.dto.FlowInfo;
import org.secondopinion.utils.JSONHelper;

@Component
public class DailyReportDataTask extends VCBaseTask<List<Company>, List<EmailReportData>>{
	private static Logger LOG = LoggerFactory.getLogger(DailyReportDataTask.class);
	/**
	 * TODO:
	 * 	1. Total number of requirements raised
	 *  2. Requirements by client
	 *  3. Total number of Submission raised
	 *  4. Submissions by recruiter
	 */
	private String submissionsCountReport;
	private String requirementsCountReport;
	private String submissionsReport;
	private String requirementsReport;
	@Autowired
	private ReportService reportService;
	public ReportService getReportService() {
		return reportService;
	}

	public void setReportService(ReportService reportService) {
		this.reportService = reportService;
	}

	public String getSubmissionsCountReport() {
		return submissionsCountReport;
	}

	public void setSubmissionsCountReport(String submissionsCountReport) {
		this.submissionsCountReport = submissionsCountReport;
	}

	public String getRequirementsCountReport() {
		return requirementsCountReport;
	}

	public void setRequirementsCountReport(String requirementsCountReport) {
		this.requirementsCountReport = requirementsCountReport;
	}

	public String getSubmissionsReport() {
		return submissionsReport;
	}

	public void setSubmissionsReport(String submissionsReport) {
		this.submissionsReport = submissionsReport;
	}

	public String getRequirementsReport() {
		return requirementsReport;
	}

	public void setRequirementsReport(String requirementsReport) {
		this.requirementsReport = requirementsReport;
	}

	
	
	@Override
	public List<EmailReportData> execute(List<Company> input, FlowInfo fi) {
		
		List<EmailReportData> list = new ArrayList<>();
		
		input.parallelStream().forEach(t -> list.add(getReportData(t)));
		
		return list;
	}
	
	public EmailReportData getReportData(Company company){
		EmailReportData emailReportData = new EmailReportData(company);
		
		Long companyId = new Long(company.getCompanyId());
		emailReportData.setSubmissionsCount(getCount(companyId, submissionsCountReport));
		emailReportData.setRequirementsCount(getCount(companyId, requirementsCountReport));
		emailReportData.setSubmissionData(getData(companyId, submissionsReport));
		emailReportData.setRequirementData(getData(companyId, requirementsReport));
		
		return emailReportData;
	}

	private int getCount(Long companyId, String reportName) {
		ReportData data = reportService.executeReport(companyId, reportName);
		
		List<Object[]>  objs = data.getCountsData();
		LOG.debug("ReportName: " +  reportName + " data: " + objs );
		
		BigInteger val = (BigInteger) objs.get(1)[1];
		return val.intValue();
	}
	
	private List<List<Object>> getData(Long companyId, String reportName) {
		ReportData data = reportService.executeReport(companyId, reportName);
		
		List<List<Object>>  objs = JSONHelper.getGsonWithDate().fromJson(data.getData(), List.class) ;
		
		return objs;
	}
}
