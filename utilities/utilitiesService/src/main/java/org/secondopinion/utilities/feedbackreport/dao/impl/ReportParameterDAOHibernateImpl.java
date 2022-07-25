package org.secondopinion.utilities.feedbackreport.dao.impl; 


import java.util.List;

import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import org.secondopinion.utilities.feedbackreport.dto.ReportParameter;
@Repository
public class ReportParameterDAOHibernateImpl extends BaseReportParameterDAOHibernate{

	@Override
	@Transactional(readOnly=true)
	public List<ReportParameter> getReportParameters(Long reportId) {
		Criterion criteria = Restrictions.eq(ReportParameter.FIELD_reportId, reportId);
		return findByCrieria(criteria);
	}
}