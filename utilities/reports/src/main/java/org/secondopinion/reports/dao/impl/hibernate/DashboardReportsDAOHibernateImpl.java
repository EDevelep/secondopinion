package org.secondopinion.reports.dao.impl.hibernate;

import java.util.List;

import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
import org.secondopinion.reports.dto.DashboardReports;
import org.springframework.transaction.annotation.Transactional;

public class DashboardReportsDAOHibernateImpl extends BaseDashboardReportsDAOHibernate{

	@Override
	@Transactional(readOnly=true)
	public List<DashboardReports> getReportsForDashboard(Integer dashboardId) {
		Criterion criterion = Restrictions.eq(DashboardReports.FIELD_dashboardId, dashboardId);
		return findByCrieria(criterion);
	}
}