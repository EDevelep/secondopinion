package org.secondopinion.reports.dao.impl.hierarchy;

import java.util.Collection;
import java.util.List;

import org.secondopinion.common.dto.DAOResult;
import org.secondopinion.dao.exception.DataAccessException;
import org.secondopinion.reports.dao.DashboardDAO;
import org.secondopinion.reports.dao.impl.hibernate.DashboardDAOHibernateImpl;
//import org.secondopinion.reports.dao.impl.ignite.DashboardIgniteDAO;
import org.secondopinion.reports.dto.Dashboard;
import org.secondopinion.reports.dto.DashboardReports;

public class DashboardHierarchyDAO implements DashboardDAO{
	//private DashboardIgniteDAO igniteDAO;
	private DashboardDAOHibernateImpl hibDAO;
	
	@Override
	public Collection<Dashboard> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Dashboard findById(Integer key) {
		/*Dashboard dashboard = igniteDAO.findById(key);
		
		if(dashboard == null){
			dashboard = hibDAO.findById(key);
			
			igniteDAO.save(dashboard);
		}else{
			
			if(dashboard.getDashboardReports() == null){
				List<DashboardReports> dashBoardReports = hibDAO.getDashboardReports(key);
				dashboard.setDashboardReports(dashBoardReports);
				
				igniteDAO.save(dashboard);
			}
			
		}
		return dashboard;*/
		return hibDAO.findById(key);
	}

	@Override
	public void save(Dashboard type) {
		hibDAO.save(type);
		//igniteDAO.save(type);
	}

	@Override
	public void save(Collection<Dashboard> types) {
		hibDAO.save(types);
		//igniteDAO.save(types);
	}

	@Override
	public DAOResult<Dashboard, Integer> saveWithRetry(Collection<Dashboard> types) {
		 DAOResult<Dashboard, Integer> result = hibDAO.saveWithRetry(types);
		 
		 //igniteDAO.save(result.getSuccess());
		 
		 return result;
	}

	@Override
	public void delete(Dashboard object) throws DataAccessException {
		hibDAO.delete(object);
		//igniteDAO.delete(object);
		
	}

	@Override
	public void delete(Collection<Dashboard> object) throws DataAccessException {
		hibDAO.delete(object);
		//igniteDAO.delete(object);
	}

	@Override
	public List<Dashboard> getAllDefaultDashboards() {
		/*List<Dashboard> defaultDashboards = igniteDAO.getAllDefaultDashboards();
		
		if(defaultDashboards == null || defaultDashboards.size()==0){
			defaultDashboards = hibDAO.getAllDefaultDashboards();
			igniteDAO.save(defaultDashboards);
		}
		return defaultDashboards;*/
		return hibDAO.getAllDefaultDashboards();
	}

	@Override
	public List<Dashboard> getDashboards(int intValue) {
		List<Dashboard> defaultDashboards = getAllDefaultDashboards();
		
		//List<Dashboard> dashboards = igniteDAO.getDashboards(intValue);
		
		//if(dashboards == null){
			List<Dashboard> dashboards = hibDAO.getDashboards(intValue);
			
			if(dashboards != null && dashboards.size()>0){
				defaultDashboards.addAll(dashboards);
			}
		/*}else{
			defaultDashboards.addAll(dashboards);
		}*/
		
		return defaultDashboards;
	}

	/*public DashboardIgniteDAO getIgniteDAO() {
		return igniteDAO;
	}

	public void setIgniteDAO(DashboardIgniteDAO igniteDAO) {
		this.igniteDAO = igniteDAO;
	}*/

	public DashboardDAOHibernateImpl getHibDAO() {
		return hibDAO;
	}

	public void setHibDAO(DashboardDAOHibernateImpl hibDAO) {
		this.hibDAO = hibDAO;
	}

	@Override
	public void removeReport(int dashboardId, Long dashboardReportsId) {
		hibDAO.removeReport(dashboardId, dashboardReportsId);
		
		Dashboard dashboard = hibDAO.findById(dashboardId);
		
		
		//igniteDAO.save(dashboard);
	}

	@Override
	public List<Dashboard> findDashboardsAssociateForReport(Long reportsId) {
		return hibDAO.findDashboardsAssociateForReport(reportsId);
	}

	@Override
	public List<Dashboard> getDashboardsByRole(List<Integer> roleIds, Integer companyId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Dashboard> getDefaultDashboardsByRole(Integer roleId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Dashboard> findByProperty(String propertyName, Object propertyValue) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Dashboard findOneByProperty(String propertyName, Object propertyValue) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Dashboard> findByPropertyValues(String propertyName, List propertyValue) {
		// TODO Auto-generated method stub
		return null;
	}
}