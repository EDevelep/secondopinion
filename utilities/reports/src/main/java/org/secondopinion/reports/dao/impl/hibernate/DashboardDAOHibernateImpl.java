package org.secondopinion.reports.dao.impl.hibernate;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.secondopinion.dao.exception.DataAccessException;
import org.secondopinion.reports.dao.DashboardReportsDAO;
import org.secondopinion.reports.dao.DashboardRuleAssociationDAO;
import org.secondopinion.reports.dao.ReportDAO;
import org.secondopinion.reports.dto.Dashboard;
import org.secondopinion.reports.dto.DashboardReports;
import org.secondopinion.reports.dto.DashboardRuleAssociation;
import org.secondopinion.reports.dto.Report;
import org.springframework.transaction.annotation.Transactional;

public class DashboardDAOHibernateImpl extends BaseDashboardDAOHibernate{
	
	private DashboardReportsDAO dashboardReportsDAO;
	private DashboardRuleAssociationDAO dashboardRuleAssociationDAO;
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
		
		if(dashBoardReports == null || dashBoardReports.size() == 0){
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

	@Override
	@Transactional
	public void removeReport(int dashboardId, Long dashboardReportsId) {
		DashboardReports dashboardReports = dashboardReportsDAO.findById(dashboardReportsId);
		
		if(dashboardReports.getDashboardId() != dashboardId){
			new IllegalArgumentException("Report doesnot belong to the dashboard");
		}
		
		dashboardReportsDAO.delete(dashboardReports);
	}

	@Override
	@Transactional(readOnly=true)
	public List<Dashboard> findDashboardsAssociateForReport(Long reportsId) {
		return findByCrieria(Restrictions.eq(DashboardReports.FIELD_reportId, reportsId));
	}

	@Override
	@Transactional(readOnly=true)
	public List<Dashboard> getDashboardsByRole(List<Integer> roleIds, Integer companyId) {

		List<DashboardRuleAssociation>  defaultList =  dashboardRuleAssociationDAO.getDefaultDashboardsByRoles(roleIds);
		
		List<DashboardRuleAssociation>  list =  dashboardRuleAssociationDAO.getDashboardsByRole(roleIds, companyId);
		
		if(list != null && list.size() >0){
			defaultList.addAll(defaultList);
		}
		
		return retrieveDashboards(list);
	}

	@Transactional(readOnly=true)
	private List<Dashboard> retrieveDashboards(List<DashboardRuleAssociation> list) {
		List<Integer> dashboardIds = new ArrayList<>();
		list.stream().forEach(t->dashboardIds.add(t.getDashboardId()));
		
		return findByCrieria(Restrictions.in(Dashboard.FIELD_dashboardId, dashboardIds));
	}

	@Override
	@Transactional(readOnly=true)
	public List<Dashboard> getDefaultDashboardsByRole(Integer roleId) {
		List<DashboardRuleAssociation>  list = dashboardRuleAssociationDAO.getDefaultDashboardsByRole(roleId);
		
		return retrieveDashboards(list);
	}

	public DashboardRuleAssociationDAO getDashboardRuleAssociationDAO() {
		return dashboardRuleAssociationDAO; 
	}

	public void setDashboardRuleAssociationDAO(DashboardRuleAssociationDAO dashboardRuleAssociationDAO) {
		this.dashboardRuleAssociationDAO = dashboardRuleAssociationDAO;
	}
}