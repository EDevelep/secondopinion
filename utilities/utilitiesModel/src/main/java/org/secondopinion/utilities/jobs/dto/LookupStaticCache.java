package org.secondopinion.utilities.jobs.dto;

import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;


public class LookupStaticCache {
	private static LookupStaticCache instance = null;

	// private constructor restricted to this class itself
	private LookupStaticCache() {

	}
	private ConcurrentHashMap<LookupType, Map<Long, Lookupstatic>> lookupStaticCacheMap = new ConcurrentHashMap<LookupType, Map<Long, Lookupstatic>>();
	// static method to create instance of Singleton class
	public static LookupStaticCache getInstance() {
		
		if (Objects.isNull(instance )) {
			instance = new LookupStaticCache();
		}
		
		return instance;
	}
	


	public  ConcurrentHashMap<LookupType, Map<Long, Lookupstatic>> getLookupStaticCacheMap() {
		return lookupStaticCacheMap;
	}


	public void putListOfLookupstaticCache(LookupType lookupType, Map<Long, Lookupstatic> lookupStaticMap) {
		lookupStaticCacheMap.put(lookupType, lookupStaticMap);
	}
}
