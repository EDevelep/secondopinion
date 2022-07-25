package org.secondopinion.utilities.feedbackreport.dao.impl; 


import java.util.Collection;
import java.util.List;
import java.util.Map;

import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import org.secondopinion.dao.exception.DataAccessException;
import org.secondopinion.utilities.feedbackreport.dao.ReportParameterDAO;
import org.secondopinion.utilities.feedbackreport.dto.Report;
import org.secondopinion.utilities.feedbackreport.dto.ReportParameter;
@Repository
public class ReportDAOHibernateImpl extends BaseReportDAOHibernate{
	private ReportParameterDAO reportParameterDAO;

	/**
	 * @return the reportParameterDAO
	 */
	public ReportParameterDAO getReportParameterDAO() {
		return reportParameterDAO;
	}

	/**
	 * @param reportParameterDAO the reportParameterDAO to set
	 */
	public void setReportParameterDAO(ReportParameterDAO reportParameterDAO) {
		this.reportParameterDAO = reportParameterDAO;
	}
	
	@Override
	@Transactional
	public void save(Report report) throws DataAccessException {
		super.save(report);
		
		if(report.getReportParameters() != null && report.getReportParameters().size()>0){
			for(ReportParameter parameter : report.getReportParameters()){
				parameter.setReportId(report.getReportId());
			}
			
			reportParameterDAO.save(report.getReportParameters());
		}
	}

	@Override
	@Transactional(readOnly=true)
	public List<Report> getAllReportDefs(Long companyId) {
		Criterion criteria = Restrictions.eq(Report.FIELD_companyId, companyId);
		List<Report> reports = findByCrieria(criteria);
		
		return reports;
	}

	@Override
	@Transactional(readOnly=true)
	public Report getReportDef(Long reportId) {
		Report report =  findById(reportId);
		setReportParameters(report);
		
		return report;
	}

	@Override
	@Transactional(readOnly=true)
	public List<Object[]> executeReportQuery(String query, Map<String, Object> params) {
		return queryUsingSQL(query, params);
	}

	@Override
	@Transactional(readOnly=true)
	public List<Map<String, Object>> executeGraphQuery(String query, Map<String, Object> params) {
		return findBySqlQuery(query, params);
	}

	@Override
	@Transactional(readOnly=true)
	public Report findByNameAndCompany(Long companyId, String reportName) {
		Criterion criteria = Restrictions.eq(Report.FIELD_companyId, companyId);
		Criterion andCriteria = Restrictions.and(criteria, Restrictions.eq(Report.FIELD_name, reportName));
		List<Report> reports = findByCrieria(andCriteria);
		
		if(reports.size() >0){
			Report report = reports.get(0);
			
			setReportParameters(report);
			
			return report;
		}
		
		return null;
	}

	@Transactional(readOnly=true)
	private void setReportParameters(Report report) {
		List<ReportParameter> parameters = reportParameterDAO.getReportParameters(report.getReportId());
		report.setReportParameters(parameters);
	}

	@Override
	@Transactional(readOnly=true)
	public List<Report> findReportsForCompany(int companyId) {
		Criterion criteria = Restrictions.eq(Report.FIELD_companyId, companyId);
		return findByCrieria(criteria);
	}

	@Override
	@Transactional(readOnly=true)
	public List<Report> findByIds(Collection<Long> keys) {
		Criterion criteria = Restrictions.in(Report.FIELD_reportId, keys);
		return findByCrieria(criteria);
	}

	@Override
	@Transactional(readOnly=true)
	public Report findByName(String reportName) {
		Criterion criteria = Restrictions.eq(Report.FIELD_name, reportName);
		List<Report> reports = findByCrieria(criteria);
		
		if(reports.size() >0){
			Report report = reports.get(0);
			
			setReportParameters(report);
			
			return report;
		}
		
		return null;
	}
}