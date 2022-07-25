package org.secondopinion.reports.service;

import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.TimeZone;

import org.secondopinion.reports.dao.DashboardDAO;
import org.secondopinion.reports.dao.ReportDAO;
import org.secondopinion.reports.dto.Dashboard;
import org.secondopinion.reports.dto.Report;
import org.secondopinion.reports.dto.ReportRoleAssociation;
import org.secondopinion.reports.graph.GraphUtil;
import org.secondopinion.reports.graph.data.Table;
//import org.secondopinion.reports.service.report.ReportDataIgniteDAO;
import org.secondopinion.reports.service.report.dto.ReportData;
import org.secondopinion.reports.service.report.dto.ReportKey;
import org.secondopinion.reports.util.ExcelExporter;
import org.secondopinion.reports.utils.transpose.Transpose;
import org.secondopinion.utils.JSONHelper;
import org.secondopinion.utils.StringUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.annotation.Transactional;

public class ReportService {

	private static Logger LOG = LoggerFactory.getLogger(ReportService.class);

	private ReportDAO reportDAO;
//	private ReportDataIgniteDAO reportIgniteDAO;
	private DashboardDAO dashboardDAO;
//	private UserDAO userDAO;

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

	public ReportData executeReport(Long reportId, TimeZone timeZone) {
		Report report = reportDAO.getReportDef(reportId);
		Integer companyId = UserContextMngr.getCompanyId();

		return executeReport(companyId, report, timeZone);
	}

	public ReportData executeReport(Long reportId, Map<String, Object> params, TimeZone timeZone) {
		Report report = reportDAO.getReportDef(reportId);
		Integer companyId = UserContextMngr.getCompanyId();

		report.setParams(params);
		return executeReport(companyId, report, timeZone);
	}

	public Object[] getCount(Long reportId, TimeZone timeZone) {
		Report report = reportDAO.getReportDef(reportId);
		Integer companyId = UserContextMngr.getCompanyId();

		return getCount(companyId, report, timeZone);
	}

	private Object[] getCount(Integer companyId, Report report, TimeZone timeZone) {
		ReportKey key = prepareReportKey(companyId, report);

		ReportData data = getCounts(report, key, timeZone);
		return data.getCountsData().get(1);
	}

	private ReportData getCounts(Report report, ReportKey key,  TimeZone timeZone) {
//		ReportData data = reportIgniteDAO.findById(key);

//		if (data == null || data.hasPassedElapsedTime()) {
			LOG.info("***Replacing data for the report: " + key);
			List<Object[]> counts = getTransposedData(report, timeZone);
			ReportData data = new ReportData(key, report);
			data.setCountsData(counts);
//			reportIgniteDAO.save(data );
//		}
		return data;
	}

	private ReportKey prepareReportKey(Integer companyId, Report report) {
//		Integer companyId = UserContextMngr.getCompanyId();
		String user = null;

		if (report.isUserSpecific()) {
			user = UserContextMngr.getUser();
		}

		return new ReportKey(companyId, user, report.getReportId());
	}

	private ReportData executeReport(Integer companyId, Report report, TimeZone timezone) {
		ReportKey key = prepareReportKey(companyId, report);

//		ReportData reportData = reportDAO.findById(key);

//		if (reportData == null || reportData.hasPassedElapsedTime()) {
			report.setCompanyId(new Long(companyId));
			ReportData reportData = new ReportData(key, report);

			if (report.isGraph()) {

				if (!StringUtil.isNullOrEmpty(report.getTransposeData())) {
					List<Object[]> transposedData = getTransposedData(report, timezone);
					Table table = GraphUtil.convertToTableMultiRow(transposedData);
					reportData.setData(JSONHelper.getGsonWithDate().toJson(table));
				} else {
					String query = report.createQuery();
					Map<String, Object> params = report.prepareReportParams(timezone);
					List<Map<String, Object>> data = reportDAO.executeGraphQuery(query, params);

					Table table = GraphUtil.getTableRepresentationMultiRow(data);
					reportData.setData(JSONHelper.getGsonWithDate().toJson(table));
				}
			} else if (report.isCounts()) {
				ReportData countsData = getCounts(report, key, timezone);
				reportData.setCountsData(countsData.getCountsData());
			} else {
				List<Object[]> transposedData = getTransposedData(report, timezone);
				reportData.setData(JSONHelper.getGsonWithDate().toJson(transposedData));
			}
			LOG.info("***Replacing report data: " + key);

//		}
		return reportData;
	}

	private List<Object[]> getTransposedData(Report report, TimeZone timeZone) {
		String query = report.createQuery();

		LOG.debug("Report Name: " + report.getName() + ", Query: " + query);

		Map<String, Object> params = report.prepareReportParams(timeZone);
		List<Object[]> data = reportDAO.executeReportQuery(query, params);

		if (StringUtil.isNullOrEmpty(report.getTransposeData())) {
			List<Object[]> dataWithHeaders = new ArrayList<>();
			String cols = report.getColumns();

			if (report.isAliasesProvided()) {
				cols = report.getColumnAliases();
			}

			if (report.getCount() != null && report.getCount() == 'Y') {
				cols = cols + "," + "Totals";
			}
			dataWithHeaders.add(cols.split(","));
			if (data != null && data.size() > 0) {
				dataWithHeaders.addAll(data);
			}
			return dataWithHeaders;
		}

		if (report.getTransposeWithTotals() != null && !report.isGraph()) {
			return Transpose.transpose(data, report.getTransposeColIndex(), report.getKeyColArray(),
					report.getKeyColIndices(), report.getValueColumnIndex());// index);
		} else {
			return Transpose.transposeWithTotals(data, report.getTransposeColIndex(), report.getKeyColArray(),
					report.getKeyColIndices(), report.getValueColumnIndex());
		}
	}

//	//@CompanyLevelSecurity
	@Transactional(readOnly = true)
	public ReportData executeReport(Long companyId, String reportName, TimeZone timeZone) {
		Report report = reportDAO.findByName(reportName);

		return executeReport(companyId.intValue(), report, timeZone);
	}

	@Transactional(readOnly = true)
	public Collection<Report> getReports(String userId) {
		return reportDAO.findAll();
	}

	@Transactional(readOnly = true)
	// @CompanyLevelSecurity
	public List<Report> getReports(Long companyId, String userId) {
		return reportDAO.findReportsForCompany(companyId.intValue());
	}

	@Transactional
	public void saveReport(Report report) {
		reportDAO.save(report);
	}

	@Transactional(readOnly = true)
	public Report getReport(String userId, Long reportId) {
		return reportDAO.getReportDef(reportId);
	}

	/**
	 * @return the reportIgniteDAO
	 */
	/*
	 * public ReportDataIgniteDAO getReportIgniteDAO() { return reportIgniteDAO; }
	 * 
	 *//**
		 * @param reportIgniteDAO the reportIgniteDAO to set
		 *//*
			 * public void setReportIgniteDAO(ReportDataIgniteDAO reportIgniteDAO) {
			 * this.reportIgniteDAO = reportIgniteDAO; }
			 */

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

	@Transactional(readOnly = true)
	public List<Dashboard> getDefaultDashboards() {
		return dashboardDAO.getAllDefaultDashboards();
	}

	@Transactional(readOnly = true)
	public List<Dashboard> getDashboards(Long companyId) {
		return dashboardDAO.getDashboards(companyId.intValue());
	}

	@Transactional
	public void saveDashboard(Dashboard dashboard) {
		dashboardDAO.save(dashboard);
	}

	@Transactional(readOnly = true)
	public Dashboard getDashboard(int dashboardId) {
		return dashboardDAO.findById(dashboardId);
	}

	@Transactional
	public void removeReportFromDashBoard(int dashboardId, Long dashboardReportsId) {
		dashboardDAO.removeReport(dashboardId, dashboardReportsId);
	}

	@Transactional
	public void dropReport(Long reportsId) {
		List<Dashboard> dashboards = dashboardDAO.findDashboardsAssociateForReport(reportsId);

		if (dashboards != null && dashboards.size() > 0) {
			new IllegalArgumentException("Report is associated to dashboards");
		}

		Report report = reportDAO.findById(reportsId);
		reportDAO.delete(report);

	}

	public OutputStream exportReport(Long reportId, TimeZone timeZone) {
		Report report = getReport(UserContextMngr.getUser(), reportId);

		if (report.isStats()) {
			List<Object[]> data = getTransposedData(report, timeZone);
			return ExcelExporter.convertToCsvStream(data);
		}

		return null;
	}

	@Transactional(readOnly = true)
	public List<Report> getReportsForUser(String userId) {
		List<Integer> roleIds  = UserContextMngr.getUserRoles(userId);

		return getReportsByRole(roleIds);
	}

	@Transactional(readOnly = true)
	public List<Report> getReportsByRole(List<Integer> roleIds) {
		return reportDAO.getReportByRole(roleIds, UserContextMngr.getCompanyId());
	}

	@Transactional(readOnly = true)
	public List<Dashboard> getDashboarsForUser(String userId) {
		List<Integer> roleIds = UserContextMngr.getUserRoles(userId);

		return getDashboardsByRole(roleIds);
	}

	@Transactional(readOnly = true)
	public List<Dashboard> getDashboardsByRole(List<Integer> roleIds) {
		return dashboardDAO.getDashboardsByRole(roleIds, UserContextMngr.getCompanyId());
	}

	@Transactional
	public void associateReportToRole(Long reportId, int roleId) {
		ReportRoleAssociation rra = new ReportRoleAssociation();
		rra.setCompanyId(UserContextMngr.getCompanyId());
		rra.setReportId(reportId);
		rra.setRoleId(roleId);

		reportDAO.saveRoleAssociation(rra);
	}


}