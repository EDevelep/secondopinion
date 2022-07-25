package org.secondopinion.utilities.feedbackreport.dao;
//package org.secondopinion.utilities.feedbackreport.dao;
//
//import java.util.List;
//
//import org.secondopinion.cache.ignite.IgniteCacheManager;
//import org.secondopinion.utilities.feedbackreport.dto.Dashboard;
//
//public class DashboardIgniteDAO  extends AbstractIgniteCacheDAO<Integer, Dashboard	> implements DashboardDAO{
//
//	public DashboardIgniteDAO(String className, String cacheName, IgniteCacheManager cacheManager, long expiryInMillis) {
//		super(className, cacheName, cacheManager, expiryInMillis);
//	}
//	
//	
//	@Override
//	public List<Dashboard> getAllDefaultDashboards() {
//		return query(Dashboard.FIELD_companyId + " = -1", new Object[]{});
//	}
//
//	@Override
//	public List<Dashboard> getDashboards(int intValue) {
//		List<Dashboard> dashboards = query(Dashboard.FIELD_companyId + " = ?", new Object[]{intValue});
//		
//		return dashboards;
//	}
//
//	@Override
//	public Class<Integer> getKeyClass() {
//		return Integer.class;
//	}
//
//	
//}
