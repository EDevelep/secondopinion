/*package org.secondopinion.reports.service.report;

import org.secondopinion.reports.dao.impl.ignite.AbstractIgniteCacheDAO;
import org.secondopinion.reports.service.report.dto.ReportData;
import org.secondopinion.reports.service.report.dto.ReportKey;

import com.vcube.cache.ignite.IgniteCacheManager;

public class ReportDataIgniteDAO extends AbstractIgniteCacheDAO<ReportKey, ReportData>{
	
	public ReportDataIgniteDAO(String className, String cacheName, IgniteCacheManager cacheManager) {
		super(className, cacheName, cacheManager);
	}
	
	public ReportDataIgniteDAO(String className, String cacheName, IgniteCacheManager cacheManager, long expiryInMillis){
		super(className, cacheName, cacheManager, expiryInMillis);
	}

	@Override
	public Class<ReportKey> getKeyClass() {
		return ReportKey.class;
	}
}*/