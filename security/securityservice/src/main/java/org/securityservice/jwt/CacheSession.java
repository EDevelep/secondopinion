package org.securityservice.jwt;

import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;

public class CacheSession {

	private CacheSession() {
		
	}
	
	private static final CacheSession INSTANCE = new CacheSession();
	
	private static ConcurrentHashMap<String, UserInfo> tokenCacheMap =  new ConcurrentHashMap<>();
	
	
	public void setTokenCacheMap(String uuid, UserInfo userInfo) {
		tokenCacheMap.putIfAbsent(uuid, userInfo);
	}
	
	public UserInfo getUserInfo(String uuid) {
		return tokenCacheMap.get(uuid);
	}
	public void removeUUID(String expiringUUID) {
		tokenCacheMap.remove(expiringUUID);
	}
	
	public static CacheSession getInstance() {
		return INSTANCE;
	}

	public String getJwtToken(String uuid) {
		UserInfo userInfo = getUserInfo(uuid );
		if(Objects.isNull(userInfo )) {
			return null;
		}
		return userInfo.getJwtToken();
		
	}
	
	
	
	
	
}
