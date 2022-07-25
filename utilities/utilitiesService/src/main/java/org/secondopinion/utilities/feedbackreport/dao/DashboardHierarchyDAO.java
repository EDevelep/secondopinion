package org.secondopinion.utilities.feedbackreport.dao;

import java.util.Collection;
import java.util.List;
import java.util.Objects;

import org.secondopinion.common.dto.DAOResult;
import org.secondopinion.dao.exception.DataAccessException;
import org.secondopinion.utilities.feedbackreport.dao.impl.DashboardDAOHibernateImpl;
import org.secondopinion.utilities.feedbackreport.dto.Dashboard;
import org.secondopinion.utilities.feedbackreport.dto.DashboardReports;
import org.springframework.util.CollectionUtils;

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
		Dashboard dashboard = null;//igniteDAO.findById(key);
		
		if(Objects.isNull(dashboard )){
			dashboard = hibDAO.findById(key);
			
			//igniteDAO.save(dashboard);
		}else{
			
			if(Objects.isNull(dashboard.getDashboardReports())){
				List<DashboardReports> dashBoardReports = hibDAO.getDashboardReports(key);
				dashboard.setDashboardReports(dashBoardReports);
				
				//igniteDAO.save(dashboard);
			}
			
		}
		return dashboard;
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
		List<Dashboard> defaultDashboards = null;//igniteDAO.getAllDefaultDashboards();
		
		if(CollectionUtils.isEmpty(defaultDashboards)){
			defaultDashboards = hibDAO.getAllDefaultDashboards();
			//igniteDAO.save(defaultDashboards);
		}
		return defaultDashboards;
	}

	@Override
	public List<Dashboard> getDashboards(int intValue) {
		List<Dashboard> defaultDashboards = getAllDefaultDashboards();
		
		List<Dashboard> dashboards = null;//igniteDAO.getDashboards(intValue);
		
		if(Objects.isNull(dashboards)){
			dashboards = hibDAO.getDashboards(intValue);
			
			if(!CollectionUtils.isEmpty(dashboards )){
				defaultDashboards.addAll(dashboards);
			}
		}else{
			defaultDashboards.addAll(dashboards);
		}
		
		return defaultDashboards;
	}

//	public DashboardIgniteDAO getIgniteDAO() {
//		return igniteDAO;
//	}
//
//	public void setIgniteDAO(DashboardIgniteDAO igniteDAO) {
//		this.igniteDAO = igniteDAO;
//	}

	public DashboardDAOHibernateImpl getHibDAO() {
		return hibDAO;
	}

	public void setHibDAO(DashboardDAOHibernateImpl hibDAO) {
		this.hibDAO = hibDAO;
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