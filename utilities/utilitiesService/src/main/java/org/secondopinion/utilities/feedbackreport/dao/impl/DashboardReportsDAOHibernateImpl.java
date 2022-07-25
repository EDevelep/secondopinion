package org.secondopinion.utilities.feedbackreport.dao.impl;

import java.util.List;

import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import org.secondopinion.utilities.feedbackreport.dto.DashboardReports;

@Repository
public class DashboardReportsDAOHibernateImpl extends BaseDashboardReportsDAOHibernate{

	@Override
	public List<DashboardReports> getReportsForDashboard(Integer dashboardId) {
		Criterion criterion = Restrictions.eq(DashboardReports.FIELD_dashboardId, dashboardId);
		return findByCrieria(criterion);
	}
}