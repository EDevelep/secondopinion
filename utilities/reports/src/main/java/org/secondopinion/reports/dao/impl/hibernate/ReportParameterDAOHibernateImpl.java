package org.secondopinion.reports.dao.impl.hibernate; 


import java.util.List;

import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
import org.secondopinion.reports.dto.ReportParameter;
import org.springframework.transaction.annotation.Transactional;

public class ReportParameterDAOHibernateImpl extends BaseReportParameterDAOHibernate{

	@Override
	@Transactional(readOnly=true)
	public List<ReportParameter> getReportParameters(Long reportId) {
		Criterion criteria = Restrictions.eq(ReportParameter.FIELD_reportId, reportId);
		return findByCrieria(criteria);
	}
}