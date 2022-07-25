package org.securityservice.serviceimpl;

import org.securityservice.exception.TokenHandlerException;
import org.securityservice.jwt.UserInfo;

public interface UserCacheService {

	String cacheingUser(UserInfo userInfo) throws TokenHandlerException;
	
	boolean validatingCachedUser(String uuid) throws TokenHandlerException;

	String regenerateToken(String uuid) throws TokenHandlerException;

	boolean removeToken(String uuid) throws TokenHandlerException;

	String validateAndGenerateOnExpiry(String uuid) throws TokenHandlerException;

	String getUserNameByUUID(String uuid);

}
