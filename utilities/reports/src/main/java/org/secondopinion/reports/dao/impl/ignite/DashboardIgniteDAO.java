/*package org.secondopinion.reports.dao.impl.ignite;

import java.util.List;

import org.secondopinion.reports.dao.DashboardDAO;
import org.secondopinion.reports.dto.Dashboard;

import com.vcube.cache.ignite.IgniteCacheManager;

public class DashboardIgniteDAO  extends AbstractIgniteCacheDAO<Integer, Dashboard	> implements DashboardDAO{

	public DashboardIgniteDAO(String className, String cacheName, IgniteCacheManager cacheManager, long expiryInMillis) {
		super(className, cacheName, cacheManager, expiryInMillis);
	}
	
	
	@Override
	public List<Dashboard> getAllDefaultDashboards() {
		return query(Dashboard.FIELD_companyId + " = -1", new Object[]{});
	}

	@Override
	public List<Dashboard> getDashboards(int intValue) {
		List<Dashboard> dashboards = query(Dashboard.FIELD_companyId + " = ?", new Object[]{intValue});
		
		return dashboards;
	}

	@Override
	public Class<Integer> getKeyClass() {
		return Integer.class;
	}


	@Override
	public void removeReport(int dashboardId, Long dashboardReportsId) {
		throw new UnsupportedOperationException("Cache implementation does not support this operation...");
	}


	@Override
	public List<Dashboard> findDashboardsAssociateForReport(Long reportsId) {
		throw new UnsupportedOperationException("Cache implementation does not support this operation...");
	}


	@Override
	public List<Dashboard> getDashboardsByRole(List<Integer> roleIds, Integer companyId) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public List<Dashboard> getDefaultDashboardsByRole(Integer roleId) {
		// TODO Auto-generated method stub
		return null;
	}
}
*/