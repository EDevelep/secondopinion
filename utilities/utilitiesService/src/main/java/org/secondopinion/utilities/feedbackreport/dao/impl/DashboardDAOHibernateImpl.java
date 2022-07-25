package org.secondopinion.utilities.feedbackreport.dao.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.secondopinion.dao.exception.DataAccessException;
import org.secondopinion.utilities.feedbackreport.dao.DashboardReportsDAO;
import org.secondopinion.utilities.feedbackreport.dao.ReportDAO;
import org.secondopinion.utilities.feedbackreport.dto.Dashboard;
import org.secondopinion.utilities.feedbackreport.dto.DashboardReports;
import org.secondopinion.utilities.feedbackreport.dto.Report;

@Repository
public class DashboardDAOHibernateImpl extends BaseDashboardDAOHibernate{
	
	@Autowired
	private DashboardReportsDAO dashboardReportsDAO;
	@Autowired
	private ReportDAO reportDAO;

	public void setReportDAO(ReportDAO reportDAO) {
		this.reportDAO = reportDAO;
	}
	
	public ReportDAO getReportDAO() {
		return reportDAO;
	}
	
	/**
	 * @return the dashboardReportsDAO
	 */
	public DashboardReportsDAO getDashboardReportsDAO() {
		return dashboardReportsDAO;
	}

	/**
	 * @param dashboardReportsDAO the dashboardReportsDAO to set
	 */
	public void setDashboardReportsDAO(DashboardReportsDAO dashboardReportsDAO) {
		this.dashboardReportsDAO = dashboardReportsDAO;
	}

	@Override
	public List<Dashboard> getAllDefaultDashboards() {
		List<Criterion> criterions = new ArrayList<>();
		criterions.add(Restrictions.eq(Dashboard.FIELD_defaultDashboard, 'Y'));
		
		return findByCrieria(criterions, Order.desc(Dashboard.FIELD_dashboardId));
	}

	@Override
	public List<Dashboard> getDashboards(int companyId) {
		List<Criterion> criterions = new ArrayList<>();
		criterions.add(Restrictions.eq(Dashboard.FIELD_companyId, companyId));
		
		return findByCrieria(criterions, Order.desc(Dashboard.FIELD_dashboardId));
	}
	
	@Override
	public void save(Dashboard dashboard) throws DataAccessException {
		super.save(dashboard);
		
		if(dashboard.hasReportsAssociated()){
			dashboard.setDashboardIdForReports();
			dashboardReportsDAO.save(dashboard.getDashboardReports());
		}
	}
	
	@Override
	@Transactional(readOnly=true)
	public Dashboard findById(Integer dashboardId) {
		Dashboard dashboard = super.findById(dashboardId);
		
		List<DashboardReports> dashBoardReports = getDashboardReports(dashboardId);
		dashboard.setDashboardReports(dashBoardReports);
		
		return dashboard;
	}

	public List<DashboardReports> getDashboardReports(Integer dashboardId) {
		List<DashboardReports> dashBoardReports = dashboardReportsDAO.getReportsForDashboard(dashboardId);
		
		if(CollectionUtils.isEmpty(dashBoardReports )){
			return dashBoardReports;
		}
		
		Map<Long, DashboardReports> map = new HashMap<>();
		for(DashboardReports report : dashBoardReports){
			map.put(report.getReportId(), report);
		}
		
		List<Report> reports = reportDAO.findByIds(map.keySet());
		
		for(Report report : reports){
			map.get(report.getReportId()).setReportName(report.getName());
		}
		return dashBoardReports;
	}

	
}