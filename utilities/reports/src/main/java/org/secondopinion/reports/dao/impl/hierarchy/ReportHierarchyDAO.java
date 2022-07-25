package org.secondopinion.reports.dao.impl.hierarchy;

import java.util.Collection;
import java.util.List;
import java.util.Map;

import org.secondopinion.common.dto.DAOResult;
import org.secondopinion.dao.exception.DataAccessException;
import org.secondopinion.reports.dao.ReportDAO;
import org.secondopinion.reports.dao.impl.hibernate.ReportDAOHibernateImpl;
//import org.secondopinion.reports.dao.impl.ignite.ReportIgniteDAO;
import org.secondopinion.reports.dto.Report;
import org.secondopinion.reports.dto.ReportRoleAssociation;
import org.springframework.transaction.annotation.Transactional;

public class ReportHierarchyDAO implements ReportDAO{

	private ReportDAOHibernateImpl reportHibernateDAO;
	//private ReportIgniteDAO reportIgniteDAO;
	
	@Override
	public Collection<Report> findAll() {
		return reportHibernateDAO.findAll();
	}

	@Override
	public Report findById(Long key) {
		/*Report report = reportIgniteDAO.findById(key);
		
		if(report == null){
			report = reportHibernateDAO.findById(key);
			
			reportIgniteDAO.save(report);
		}
		return report;*/
		return reportHibernateDAO.findById(key);
	}

	@Override
	@Transactional
	public void save(Report report) {
		reportHibernateDAO.save(report);
		//reportIgniteDAO.save(report);
	}

	@Override
	@Transactional
	public void save(Collection<Report> reports) {
		reportHibernateDAO.save(reports);
		//reportIgniteDAO.save(reports);
	}

	@Override
	public DAOResult<Report, Long> saveWithRetry(Collection<Report> types) {
		//new IllegalArgumentException("Method is not implemented");
		return null;
	}

	@Override
	@Transactional
	public void delete(Report report) throws DataAccessException {
		//reportIgniteDAO.delete(report);
		reportHibernateDAO.delete(report);
	}

	@Override
	@Transactional
	public void delete(Collection<Report> reports) throws DataAccessException {
		//reportIgniteDAO.delete(reports);
		reportHibernateDAO.delete(reports);
	}

	@Override
	public List<Report> getAllReportDefs(Long companyId) {
		/*List<Report> reports = reportIgniteDAO.getAllReportDefs(companyId);
		
		if(reports == null){
			reports = reportHibernateDAO.getAllReportDefs(companyId);
			
			reportIgniteDAO.save(reports);
		}
		return reports;*/
		return reportHibernateDAO.getAllReportDefs(companyId);
	}

	@Override
	public Report getReportDef(Long reportId) {
		/*Report report = reportIgniteDAO.getReportDef(reportId);
		
		if(report == null){
		Report report = reportHibernateDAO.getReportDef(reportId);
			//reportIgniteDAO.save(report);
		}else{
			if(report.getReportParameters() == null || report.getReportParameters().size()==0){
				report.setReportParameters(reportHibernateDAO.getReportParameterDAO().getReportParameters(reportId));
				reportIgniteDAO.save(report);
			}
		}
				
		return report;*/
		return reportHibernateDAO.getReportDef(reportId);
	}

	@Override
	public List<Object[]> executeReportQuery(String query, Map<String, Object> params) {
		return reportHibernateDAO.executeReportQuery(query, params);
	}

	@Override
	public List<Map<String, Object>> executeGraphQuery(String query, Map<String, Object> params) {
		return reportHibernateDAO.executeGraphQuery(query, params);
	}

	@Override
	public Report findByNameAndCompany(Long companyId, String reportName) {
		/*Report report = reportIgniteDAO.findByNameAndCompany(companyId, reportName);
		
		if(report == null){
		Report report = 	reportHibernateDAO.findByNameAndCompany(companyId, reportName);
			reportIgniteDAO.save(report);
		}
		return report;*/
		return reportHibernateDAO.findByNameAndCompany(companyId, reportName);
	}

	@Override
	public Report findByName(String reportName) {
		/*Report report = reportIgniteDAO.findByName(reportName);
		
		if(report == null){
		 report = 	reportHibernateDAO.findByName(reportName);
			reportIgniteDAO.save(report);
		}
		return report;*/
		return reportHibernateDAO.findByName(reportName);
	}
	
	@Override
	public List<Report> findReportsForCompany(int companyId) {
		/*List<Report> reports = reportIgniteDAO.findReportsForCompany(companyId);
		
		if(reports == null){
			reports = reportHibernateDAO.findReportsForCompany(companyId);
			
			reportIgniteDAO.save(reports);
		}
		return reports;*/
		return reportHibernateDAO.findReportsForCompany(companyId);
	}

	@Override
	public List<Report> findByIds(Collection<Long> companyIds) {
		/*List<Report> reports = reportIgniteDAO.findByIds(companyIds);
		
		if(reports == null){
			reports = reportHibernateDAO.findByIds(companyIds);
			
			reportIgniteDAO.save(reports);
		}
		return reports;*/
		return reportHibernateDAO.findByIds(companyIds);
	}

	public ReportDAOHibernateImpl getReportHibernateDAO() {
		return reportHibernateDAO;
	}

	public void setReportHibernateDAO(ReportDAOHibernateImpl reportHibernateDAO) {
		this.reportHibernateDAO = reportHibernateDAO;
	}

	/*public ReportIgniteDAO getReportIgniteDAO() {
		return reportIgniteDAO;
	}

	public void setReportIgniteDAO(ReportIgniteDAO reportsIgniteDAO) {
		this.reportIgniteDAO = reportsIgniteDAO;
	}*/

	@Override
	public List<Report> getReportByRole(List<Integer> roleIds, Integer companyId) {
		return reportHibernateDAO.getReportByRole(roleIds, companyId);
	}

//	@Override
//	public List<Report> getDefaultReportsForRoleId(Integer roleId) {
//		return reportHibernateDAO.getDefaultReportsForRoleId(roleId);
//	}

	@Override
	public void saveRoleAssociation(ReportRoleAssociation reportRoleAssociation) {
		reportHibernateDAO.saveRoleAssociation(reportRoleAssociation);
		
	}

@Override
public List<Report> findByProperty(String propertyName, Object propertyValue) {
	// TODO Auto-generated method stub
	return null;
}

@Override
public Report findOneByProperty(String propertyName, Object propertyValue) {
	// TODO Auto-generated method stub
	return null;
}

@Override
public List<Report> findByPropertyValues(String propertyName, List propertyValue) {
	// TODO Auto-generated method stub
	return null;
}

}
