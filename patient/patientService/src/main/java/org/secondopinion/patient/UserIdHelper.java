package org.secondopinion.patient;

import java.util.TimeZone;

import javax.servlet.http.HttpServletRequest;
import org.codehaus.jettison.json.JSONObject;

import org.secondopinion.configurations.AppProperties;
import org.secondopinion.ssclient.CachedSessionToken;
import org.secondopinion.utils.UserMgmtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.support.RequestContextUtils;

@Component
public class UserIdHelper {

	
	@Autowired
	private HttpServletRequest httpServletRequest;

	@Autowired
	private AppProperties appProperties;

	public TimeZone getTimeZone() {
		return RequestContextUtils.getTimeZone(httpServletRequest);
	}

	public Long getUserId() {
		String sessionId = httpServletRequest.getHeader(UserMgmtUtil.AUTHORIZATION);
		String userinfo;
		Long userId = null;
		try {

			userinfo = CachedSessionToken.getUserInfoBySessionId(appProperties.getSsu().getUserinfoUri(), sessionId);

			JSONObject jsonObject = new JSONObject(userinfo);

			userId = jsonObject.getLong("userId");

		} catch (Exception e) {
			throw new RuntimeException(e.getMessage());
		}
		return userId;
	}

}
