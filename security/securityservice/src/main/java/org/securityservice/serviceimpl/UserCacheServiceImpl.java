package org.securityservice.serviceimpl;

import java.util.Objects;
import java.util.UUID;

import org.apache.commons.lang3.StringUtils;
import org.secondopinion.utils.AES;
import org.secondopinion.utils.JSONHelper;
import org.secondopinion.utils.StringUtil;
import org.securityservice.exception.TokenHandlerException;
import org.securityservice.jwt.CacheSession;
import org.securityservice.jwt.JwtTokenProvider;
import org.securityservice.jwt.UserInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;

@Service
public class UserCacheServiceImpl implements UserCacheService  {

	private static final Logger logger = LoggerFactory.getLogger(UserCacheServiceImpl.class);

	@Autowired
	private JwtTokenProvider jwtTokenProvider;

	/**
	 * cacheing the username
	 */
	@Override
	public String cacheingUser(UserInfo userInfo) throws TokenHandlerException {
		logger.info("Create the session id for the user {} ", userInfo.getUserName());
		try {

			String aesValue = /* AES.encrypt */(UUID.randomUUID().toString());
			String  uuid =  aesValue.replaceAll("\\+", "");
			String token = jwtTokenProvider.generateToken(uuid);
			userInfo.setJwtToken(token);
			getCacheSession().setTokenCacheMap(uuid, userInfo);
			return uuid;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}

	}

	/**
	 * validating the uuid
	 */
	@Override
	public boolean validatingCachedUser(String uuid) throws TokenHandlerException {
		logger.info("validating the Cached User for the session : {}" , uuid);
		if(StringUtils.isEmpty(uuid )) {
			return false;
		}
		String token = getCacheSession().getJwtToken(uuid);
		if(StringUtils.isEmpty(token )) {
			return false;
		}
		return !jwtTokenProvider.isTokenExpired(token);//need to add the logic here to verify the locations
	}

	/**
	 * regenerating the uuid
	 */
	@Override
	public String regenerateToken(String expiringUUID) throws TokenHandlerException {
		logger.info("regenerate the Token for the existing session : {}",expiringUUID);
		if(StringUtil.isNullOrEmpty(expiringUUID)) {
			throw new IllegalArgumentException("invalid sessionid");
		}
		String expiringToken = getCacheSession().getJwtToken(expiringUUID);
		if(StringUtil.isNullOrEmpty(expiringToken)) {
			throw new IllegalArgumentException("invalid sessionid");
		}
		boolean isExpired = jwtTokenProvider.isTokenExpired(expiringToken);//return true if expired, return false if not expired
		if(!isExpired) {
			return expiringUUID;
		}
		UserInfo userinfo = getCacheSession().getUserInfo(expiringToken);
		if(Objects.isNull(userinfo)) {
			throw new IllegalArgumentException("Invalid sessionid");
		}
		return cacheingUser( userinfo) ;

	}

	/**
	 * validate and generate new uuid
	 */
	@Override
	public String validateAndGenerateOnExpiry(String uuid) throws TokenHandlerException {
		boolean isUUIDValidAndExpired = validatingCachedUser(uuid);//return true if not expired, return false if it expired

		return isUUIDValidAndExpired ? uuid : regenerateToken(uuid);
	}

	/**
	 * remove the session id
	 */
	@Override
	public boolean removeToken(String uuid) throws TokenHandlerException {
		logger.info("removing the session from cache : {} " , uuid );
		getCacheSession().removeUUID(uuid);

		return Objects.isNull(getCacheSession().getJwtToken(uuid) );
	}

	public JwtTokenProvider getJwtTokenProvider() {
		return jwtTokenProvider;
	}

	public void setJwtTokenProvider(JwtTokenProvider jwtTokenProvider) {
		this.jwtTokenProvider = jwtTokenProvider;
	}

	private CacheSession getCacheSession() {
		return CacheSession.getInstance();
	}

	@Override
	public String getUserNameByUUID(String uuid) {
		if(StringUtils.isEmpty(uuid)) {
			new IllegalArgumentException("invalid session");
		}

		UserInfo userInfo = getCacheSession().getUserInfo(uuid);
		if(Objects.isNull(userInfo )) {
			new IllegalArgumentException("invalid session");
		}
		String jwtToken = userInfo.getJwtToken();
		if(StringUtils.isEmpty(jwtToken)) {
			new IllegalArgumentException("invalid session");
		}
		Gson gson = JSONHelper.getGsonWithDateTime();
		return gson.toJson(userInfo);
	}

	public static void main(String[] args) {
		String aaes = "SgqGiZXJbn9kxc67HBBgTOz7O6iZdtafyUy6N9ziB3G57+eSSCSQLRUnzhazGkM+";
		String  uuid =  aaes.replaceAll("\\+", "");
		System.out.println(uuid);

	}
}
