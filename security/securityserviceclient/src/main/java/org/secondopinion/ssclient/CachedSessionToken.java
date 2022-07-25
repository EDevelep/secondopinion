package org.secondopinion.ssclient;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;


import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.secondopinion.ssclient.RestUtil.RequestMethodType;

import com.google.gson.Gson;



public class CachedSessionToken {

	private static final Logger logger = Logger.getLogger(CachedSessionToken.class);
	
	private CachedSessionToken() {
		
	}
	
	/**
	 * 
	 * @param userName
	 * @return
	 * @throws Exception
	 */
	public static String createSessionId(String createTokenUri, 
			String userName, String moduleType, Long userId, 
			List<String> roles) throws Exception {
		
		
		logger.info("Create session id for the user : " + userName+":"+createTokenUri);
		if(StringUtils.isEmpty(userName) || StringUtils.isEmpty(moduleType) 
				|| Objects.isNull(userId) || roles == null || roles.isEmpty()) {
			throw new IllegalArgumentException("Arguments[Username, moduleType, userId, roles] can not be empty");
		}
		
		Map<String, Object> userInfoMap = new HashMap<>();
		userInfoMap.put("userId", userId);
		userInfoMap.put("userName", userName);
		userInfoMap.put("moduleType", moduleType);
		userInfoMap.put("roles", roles);
		
		Gson gson = new Gson();
        String gsonString = gson.toJson(userInfoMap);
		
		Map<String, String> params = new HashMap<>();
		params.put("userinfojson", gsonString);
		
		return RestUtil.securityServiceRequest(createTokenUri, RequestMethodType.POST, params);
	}
	
	/**
	 * 
	 * @param userName
	 * @return
	 * @throws Exception
	 */
	public static String getUserInfoBySessionId( String getUserNameUri, String uuid) throws Exception {
		logger.info("Get username from by sessionId : " + uuid);
		
		if(StringUtils.isEmpty(uuid)) {
			throw new IllegalArgumentException("Arguments[uuid] can not be empty");
		}
		
		String createTokenUrl = String.format(getUserNameUri, uuid);

		return RestUtil.securityServiceRequest(createTokenUrl, RequestMethodType.GET, null);
	}

	/**
	 * 
	 * @param uuid
	 * @return
	 * @throws Exception
	 */
	public static Boolean validateSessionId(String validateTokenUri, String uuid) throws Exception {
		logger.info("validate session id : " + uuid);
		if(StringUtils.isEmpty(uuid)) {
			throw new IllegalArgumentException("Arguments[session id] can not be empty");
		}
		
		String validateTokenUrl = String.format(validateTokenUri, uuid);
		return RestUtil.securityServiceRequest(validateTokenUrl, RequestMethodType.POST, null);
	}
	
	/**
	 * 
	 * @param uuid
	 * @param username
	 * @return
	 * @throws Exception
	 */
	public static String regenerateSessionId( String regenerateTokenUri, String uuid) throws Exception {
		logger.info("regenerate session id for the user sessionid" + uuid);
		if(StringUtils.isEmpty(uuid)) {
			throw new IllegalArgumentException("Arguments[session id] should not empty");
		}
		String regenerateTokenUrl = String.format(regenerateTokenUri, uuid);
		return RestUtil.securityServiceRequest(regenerateTokenUrl, RequestMethodType.POST, null);
	}
	
	/**
	 * 
	 * @param uuid
	 * @return
	 * @throws Exception
	 */
	public static Boolean removeSessionId( String removeTokenUri, String uuid) throws Exception {
		logger.info("remove session id : " + uuid);
		if(StringUtils.isEmpty(uuid) ) {
			throw new IllegalArgumentException("Argument[session id] should not empty");
		}
		String removeTokenUrl = String.format(removeTokenUri, uuid);
		return RestUtil.securityServiceRequest(removeTokenUrl, RequestMethodType.POST, null);
	}
	
	/**
	 * 
	 * @param uuid
	 * @param username
	 * @return
	 * @throws Exception
	 */
	public static String validateAndGenerateOnExpiry( String vaidateNgenerateTokenUri, String uuid) throws Exception {
		logger.info("validate session id  and generate the new session id for the user sessionid" + uuid);
		if(StringUtils.isEmpty(uuid)) {
			throw new IllegalArgumentException("Arguments[session id] should not empty");
		}
		String validateAndGenerateTokenUrl = String.format(vaidateNgenerateTokenUri, uuid);
		return RestUtil.securityServiceRequest(validateAndGenerateTokenUrl, RequestMethodType.POST, null);
	}
	
}
