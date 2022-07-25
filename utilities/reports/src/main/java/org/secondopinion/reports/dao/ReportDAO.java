package org.secondopinion.reports.dao; 

import java.util.Collection;
import java.util.List;
import java.util.Map;

import org.secondopinion.dao.IDAO;
import org.secondopinion.reports.dto.Report;
import org.secondopinion.reports.dto.ReportRoleAssociation;

public interface ReportDAO extends IDAO<Report,Long >{
	List<Report> getAllReportDefs(Long companyId);
	
	Report getReportDef(Long reportId);
	
	List<Object[]> executeReportQuery(String query, Map<String, Object> params);
	
	List<Map<String, Object>> executeGraphQuery(String query, Map<String, Object> params);

	Report findByNameAndCompany(Long companyId, String reportName);
	
	Report findByName(String reportName);

	List<Report> findReportsForCompany(int intValue);

	List<Report> findByIds(Collection<Long> keySet);

	List<Report> getReportByRole(List<Integer> roleIds, Integer companyId);

//	List<Report> getDefaultReportsForRoleId(Integer roleId);

	void saveRoleAssociation(ReportRoleAssociation reportRoleAssociation);
}