package org.secondopinion.utilities.feedbackreport.service.impl;
//package org.secondopinion.utilities.feedbackreport.service;
//
//import org.secondopinion.cache.ignite.IgniteCacheManager;
//import org.secondopinion.utilities.feedbackreport.dao.impl.ignite.AbstractIgniteCacheDAO;
//import org.secondopinion.utilities.feedbackreport.dto.ReportData;
//import org.secondopinion.utilities.feedbackreport.dto.ReportKey;
//
//public class ReportDataIgniteDAO extends AbstractIgniteCacheDAO<ReportKey, ReportData>{
//	
//	public ReportDataIgniteDAO(String className, String cacheName, IgniteCacheManager cacheManager) {
//		super(className, cacheName, cacheManager);
//	}
//	
//	public ReportDataIgniteDAO(String className, String cacheName, IgniteCacheManager cacheManager, long expiryInMillis){
//		super(className, cacheName, cacheManager, expiryInMillis);
//	}
//
//	@Override
//	public Class<ReportKey> getKeyClass() {
//		return ReportKey.class;
//	}
//}