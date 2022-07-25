package org.secondopinion.utilities.feedbackreport.service.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.secondopinion.userMgmt.dto.UserContextMngr;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import org.secondopinion.utilities.feedbackreport.dao.DashboardDAO;
import org.secondopinion.utilities.feedbackreport.dao.ReportDAO;
import org.secondopinion.utilities.feedbackreport.dto.Dashboard;
import org.secondopinion.utilities.feedbackreport.dto.Report;
import org.secondopinion.utilities.feedbackreport.dto.ReportData;
import org.secondopinion.utilities.feedbackreport.dto.ReportKey;
import org.secondopinion.utils.JSONHelper;
import org.secondopinion.utils.StringUtil;
//import org.secondopinion.utils.transpose.Transpose;

@Service
public class ReportService {
	
	private static Logger LOG = LoggerFactory.getLogger(ReportService.class);
	
	@Autowired
	private ReportDAO reportDAO;
//	@Autowired
//	private ReportDataIgniteDAO reportIgniteDAO;
	@Autowired
	private DashboardDAO dashboardDAO;

	/**
	 * @return the reportDAO
	 */
	public ReportDAO getReportDAO() {
		return reportDAO;
	}

	/**
	 * @param reportDAO the reportDAO to set
	 */
	public void setReportDAO(ReportDAO reportDAO) {
		this.reportDAO = reportDAO;
	}
	
	public ReportData executeReport(Long reportId){
		Report report =  reportDAO.getReportDef(reportId);
		Integer companyId = UserContextMngr.getCompanyId();
		
		return executeReport(companyId, report);
	}
	
	public Object[] getCount(Long reportId){
		Report report =  reportDAO.getReportDef(reportId);
		Integer companyId = UserContextMngr.getCompanyId();
		
		return getCount(companyId, report);
	}
	
	private Object[] getCount(Integer companyId, Report report){
		ReportKey key = prepareReportKey(companyId, report);
		
		ReportData data = getCounts(report, key);
		return data.getCountsData().get(1);
	}

	private ReportData getCounts(Report report, ReportKey key) {
		ReportData data = null;
				//reportIgniteDAO.findById(key);
		
		if(data == null || data.hasPassedElapsedTime()){
			LOG.info("***Replacing data for the report: " + key);
			List<Object[]> counts = getTransposedData(report);
			data = new ReportData(key, report);
			data.setCountsData(counts);
//			reportIgniteDAO.save(data );
		}
		return data;
	}

	private ReportKey prepareReportKey(Integer companyId, Report report) {
//		Integer companyId = UserContextMngr.getCompanyId();
		String user = null;
		
		if(report.isUserSpecific()){
			user = UserContextMngr.getUser();
		}
		
		return new ReportKey(companyId, user, report.getReportId());
	}

/*	public Object[] getCount(Long companyId, String reportName) {
		Report report = reportDAO.findByNameAndCompany(companyId, reportName);
		
		return getCount(companyId.intValue(), report);
	}
*/		
	private ReportData executeReport(Integer companyId, Report report) {
		ReportKey key = prepareReportKey(companyId, report);
		
		ReportData reportData = null;
				//reportIgniteDAO.findById(key);
		
		if(reportData == null || reportData.hasPassedElapsedTime()){
			report.setCompanyId(new Long(key.getCompanyId()));
			reportData = new ReportData(key, report);
			
			if(report.isGraph()){
				
				if(!StringUtil.isNullOrEmpty(report.getTransposeData())){
					List<Object[]> transposedData = getTransposedData(report);
					//Table table = GraphUtil.convertToTableMultiRow(transposedData);
					//reportData.setData(JSONHelper.getGsonWithDate().toJson(table));
				}else{
					String query = report.createQuery();
					Map<String, Object> params = report.prepareReportParams();
					List<Map<String, Object>> data = reportDAO.executeGraphQuery(query, params );
					
					//Table table = GraphUtil.getTableRepresentationMultiRow(data);
					//reportData.setData(JSONHelper.getGsonWithDate().toJson(table));
				}
			}else if(report.isCounts()){
				//ReportData countsData = getCounts(report, key);
				//reportData.setCountsData(countsData.getCountsData());
			}else {
				List<Object[]> transposedData = getTransposedData(report);
				reportData.setData(JSONHelper.getGsonWithDate().toJson(transposedData));
			}
			LOG.info("***Replacing report data: " + key);
			
//			reportIgniteDAO.save(reportData);
		}
		return reportData;
	}

	private List<Object[]> getTransposedData(Report report) {
		String query = report.createQuery();
		
		LOG.debug("Report Name: " + report.getName() + ", Query: " + query);
		
		Map<String, Object> params = report.prepareReportParams();
		List<Object[]> data = reportDAO.executeReportQuery(query, params);
		
		if(StringUtil.isNullOrEmpty(report.getTransposeData())){
			List<Object[]> dataWithHeaders = new ArrayList<>();
			String cols = report.getColumns();
			if (report.getCount()!=null &&  report.getCount() == 'Y'){
				cols = cols + ","+"Totals";
			}
			dataWithHeaders.add(cols.split(","));
			if(data != null && data.size()>0){
				dataWithHeaders.addAll(data);
			}
			return dataWithHeaders;
		}
		
		if (report.getTransposeWithTotals()!= null && !report.isGraph()){
			//return Transpose.transpose(data, report.getTransposeColIndex(), report.getKeyColIndices(), report.getValueColumnIndex());//index);
		}else{
			//return Transpose.transposeWithTotals(data, report.getTransposeColIndex(), report.getKeyColIndices(), report.getValueColumnIndex());
		}
		return null;
	}
	
//	@CompanyLevelSecurity
	@Transactional(readOnly=true)
	public ReportData executeReport(Long companyId, String reportName){
		Report report = reportDAO.findByName(reportName);
		
		return executeReport(companyId.intValue(), report);
	}

	@Transactional(readOnly=true)
	public Collection<Report> getReports(String userId) {
		return reportDAO.findAll();
	}

	@Transactional(readOnly=true)
//	@CompanyLevelSecurity
	public List<Report> getReports(Long companyId, String userId) {
		return reportDAO.findReportsForCompany(companyId.intValue());
	}

	@Transactional
	public void saveReport(Report report) {
		reportDAO.save(report);
	}

	@Transactional(readOnly=true)
	public Report getReport(String userId, Long reportId) {
		return reportDAO.getReportDef(reportId);
	}

	/**
	 * @return the reportIgniteDAO
	 */
//	public ReportDataIgniteDAO getReportIgniteDAO() {
//		return reportIgniteDAO;
//	}

	/**
	 * @param reportIgniteDAO the reportIgniteDAO to set
	 */
//	public void setReportIgniteDAO(ReportDataIgniteDAO reportIgniteDAO) {
//		this.reportIgniteDAO = reportIgniteDAO;
//	}

	/**
	 * @return the dashboardDAO
	 */
	public DashboardDAO getDashboardDAO() {
		return dashboardDAO;
	}

	/**
	 * @param dashboardDAO the dashboardDAO to set
	 */
	public void setDashboardDAO(DashboardDAO dashboardDAO) {
		this.dashboardDAO = dashboardDAO;
	}
	
	@Transactional(readOnly=true)
	public List<Dashboard> getDefaultDashboards(){
		return dashboardDAO.getAllDefaultDashboards();
	}
	
	@Transactional(readOnly=true)
	public List<Dashboard> getDashboards(Long companyId){
		return dashboardDAO.getDashboards(companyId.intValue());
	}
	
	@Transactional
	public void saveDashboard(Dashboard dashboard){
		dashboardDAO.save(dashboard);
	}
	
	@Transactional(readOnly=true)
	public Dashboard getDashboard(int dashboardId){
		return dashboardDAO.findById(dashboardId);
	}
}