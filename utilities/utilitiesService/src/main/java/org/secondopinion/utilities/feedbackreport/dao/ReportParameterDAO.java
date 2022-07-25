package org.secondopinion.utilities.feedbackreport.dao; 

import java.util.List;

import org.secondopinion.dao.IDAO;
import org.secondopinion.utilities.feedbackreport.dto.ReportParameter;

public interface ReportParameterDAO extends IDAO<ReportParameter,Long >{
	List<ReportParameter> getReportParameters(Long reportId);
}