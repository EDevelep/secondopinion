/*package org.secondopinion.reports.dao.impl.ignite;

import java.util.Collection;
import java.util.List;
import java.util.Map;

import org.secondopinion.reports.dao.ReportDAO;
import org.secondopinion.reports.dto.Report;
import org.secondopinion.reports.dto.ReportRoleAssociation;

import com.vcube.cache.ignite.IgniteCacheManager;

public class ReportIgniteDAO extends AbstractIgniteCacheDAO<Long, Report> implements ReportDAO{

	public ReportIgniteDAO(String className, String cacheName, IgniteCacheManager cacheManager) {
		super(className, cacheName, cacheManager);
	}

	@Override
	public List<Report> getAllReportDefs(Long companyId) {
		List<Report> reports = query(Report.FIELD_companyId + " = ?", new Object[]{companyId});
		
		return reports;
	}

	@Override
	public Report getReportDef(Long reportId) {
		return findById(reportId);
	}

	@Override
	public List<Object[]> executeReportQuery(String query, Map<String, Object> params) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Map<String, Object>> executeGraphQuery(String query, Map<String, Object> params) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Report findByNameAndCompany(Long companyId, String reportName) {
		List<Report> reports = query(Report.FIELD_companyId + " = ? and " + Report.FIELD_name + " = ?", new Object[]{companyId, reportName});
		
		if(reports !=null && reports.size()>0){
			return reports.get(0);
		}
		
		return null;
	}

	@Override
	public Report findByName(String reportName) {
		List<Report> reports = query(Report.FIELD_name + " = ?", new Object[]{reportName});
		
		if(reports !=null && reports.size()>0){
			return reports.get(0);
		}
		
		return null;
	}

	
	@Override
	public List<Report> findReportsForCompany(int companyId) {
		List<Report> reports = query(Report.FIELD_companyId + " = ?", new Object[]{companyId});
		
		return reports;	}

	@Override
	public List<Report> findByIds(Collection<Long> keySet) {
		return null;
	}

	@Override
	public Class<Long> getKeyClass() {
		return Long.class;
	}

	@Override
	public List<Report> getReportByRole(List<Integer> roleIds, Integer companyId) {
		new IllegalArgumentException("Operation is not supported in this implementation");
	}

	@Override
	public void saveRoleAssociation(ReportRoleAssociation reportRoleAssociation) {
		
	}

//	@Override
//	public List<Report> getDefaultReportsForRoleId(Integer roleId) {
//		// TODO Auto-generated method stub
//		return null;
//	}

}
*/