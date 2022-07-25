package org.secondopinion.reports.dao; 

import java.util.List;

import org.secondopinion.dao.IDAO;
import org.secondopinion.reports.dto.ReportParameter;

public interface ReportParameterDAO extends IDAO<ReportParameter,Long >{
	List<ReportParameter> getReportParameters(Long reportId);
}