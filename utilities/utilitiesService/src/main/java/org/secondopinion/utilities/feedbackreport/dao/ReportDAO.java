package org.secondopinion.utilities.feedbackreport.dao; 

import java.util.Collection;
import java.util.List;
import java.util.Map;

import org.secondopinion.dao.IDAO;
import org.secondopinion.utilities.feedbackreport.dto.Report;

public interface ReportDAO extends IDAO<Report,Long >{
	List<Report> getAllReportDefs(Long companyId);
	
	Report getReportDef(Long reportId);
	
	List<Object[]> executeReportQuery(String query, Map<String, Object> params);
	
	List<Map<String, Object>> executeGraphQuery(String query, Map<String, Object> params);

	Report findByNameAndCompany(Long companyId, String reportName);
	
	Report findByName(String reportName);

	List<Report> findReportsForCompany(int intValue);

	List<Report> findByIds(Collection<Long> keySet);
}