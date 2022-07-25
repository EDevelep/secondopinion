package org.secondopinion.userMgmt.dto;

import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.secondopinion.utils.StringUtil;


public class UserContextMngr {
	private static ConcurrentHashMap<String, String> userContextMap = new ConcurrentHashMap<String, String>();
	private static ConcurrentHashMap<String, UserInfo> userSessionContextMap = new ConcurrentHashMap<String, UserInfo>();
	private static final ThreadLocal<UserContextInfo> userThreadLocal = new ThreadLocal<UserContextInfo>();
		
	private static Logger LOG = LoggerFactory.getLogger(UserContextMngr.class);
	
	private static class UserInfo{
		static final long EXPIRY_TIME = 45*60*1000;
		User user;
		
		long lastAccessTime;
		
		UserInfo(User _user){
			this.user = _user;
			this.lastAccessTime = System.currentTimeMillis();
		}
		
		void setLastAccessTime(){
			lastAccessTime = System.currentTimeMillis();
		}
				
		boolean hasExpired(){
			return !(lastAccessTime + EXPIRY_TIME >= System.currentTimeMillis());
		}
	}
	
	public static String registerUser(User user){
		String uuid = UUID.randomUUID().toString();
		
		userContextMap.putIfAbsent(user.getUserName(), uuid);
		
		uuid = userContextMap.get(user.getUserName());
		
		userSessionContextMap.put(uuid, new UserInfo(user));
		
		LOG.trace("User session ID added to session:" + uuid);
		LOG.trace("User session ID added to session:" + uuid  + ":" + userSessionContextMap.get(uuid));
		return uuid;
	}
	
	public static String getUser(String uuid){
		LOG.trace(uuid);
		UserInfo userInfo = userSessionContextMap.get(uuid);
		LOG.trace("User session ID received :" + uuid + " userName: " + userInfo.user.getUserName());
		return userInfo.user.getUserName();
	}
	
	public static User getUserObj(String uuid){
		LOG.trace(uuid);
		UserInfo userInfo = userSessionContextMap.get(uuid);
		LOG.trace("User session ID received :" + uuid + " userName: " + userInfo.user.getUserName());
		return userInfo.user;
	}
	
	public static boolean userContextExists(String uuid){
		LOG.trace(uuid);
		UserInfo userInfo = userSessionContextMap.get(uuid);
		
		if (userInfo == null || userInfo.hasExpired()){
			invalidateUserSession(uuid);
			new IllegalArgumentException("You have passed Idle time - Please re-login");
		}else{
			userInfo.setLastAccessTime();
		}
		
		return !(userInfo == null);
	}
	
	public static Integer getCompanyId(String uuid) {
		UserInfo userInfo = userSessionContextMap.get(uuid);
		LOG.trace("User session ID received :" + uuid + " userName: " + userInfo.user.getCompanyId());
		return userInfo.user.getCompanyId();
	}
	
	public static void invalidateUserSession(String uuidorUserName){
		UserInfo userInfo = userSessionContextMap.get(uuidorUserName);
		if(userInfo == null) {
			uuidorUserName = userContextMap.get(uuidorUserName);
			userInfo = userSessionContextMap.get(uuidorUserName);
		}
		userSessionContextMap.remove(uuidorUserName);
		try{
			userContextMap.remove(userInfo.user.getUserName());
		}catch(Exception ex){
			LOG.error(ex.getMessage(), ex);
		}
	}
	
	public static void setUserThreadLocal(String uuid) {
		if(!StringUtil.isNullOrEmpty(uuid)){
			userThreadLocal.remove();
			
			UserContextInfo usrCtx = new UserContextInfo(uuid, getUser(uuid));
			userThreadLocal.set(usrCtx);
		}
    }

    public static void unsetUserThreadLocal() {
    	userThreadLocal.remove();
    }

    public static UserContextInfo getUserThreadLocal() {
        return userThreadLocal.get();
    }
    
    public static String getUser() {
    	UserContextInfo info = getUserThreadLocal();
    	if(info != null){
    		return info.getUserName();
    	}
        return null;
    }
    
    public static Integer getCompanyId() {
		UserContextInfo info = getUserThreadLocal();
		
		if(info == null){
			return null;
		}
		
		String uuid = info.getUuid();
		Integer companyId = getCompanyId(uuid);
		return companyId;
	}
    
    public static class UserContextInfo{
    	private String uuid;
    	private String userName;
//    	private RequestInfo requestInfo;

    	public UserContextInfo(String uuid, String userName) {
			this.uuid = uuid;
			this.userName = userName;
			
		}
    	
    	public String getUuid() {
			return uuid;
		}
    	
    	public String getUserName() {
			return userName;
		}
    }
}
