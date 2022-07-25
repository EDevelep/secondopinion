package org.secondopinion.configurations;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

import javax.servlet.http.HttpServletRequest;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.secondopinion.ssclient.CachedSessionToken;
import org.secondopinion.utils.DateUtil;
import org.secondopinion.utils.JSONHelper;
import org.secondopinion.utils.UserMgmtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.support.RequestContextUtils;

@Component
public class UtilComponent {

	@Autowired
	private HttpServletRequest httpServletRequest;
	
	
	@Autowired
	private AppProperties appProperties;
	
	public  TimeZone getTimeZone() {
		return RequestContextUtils.getTimeZone(httpServletRequest);
	}
	
	public Date getCurrentDate() {
		return DateUtil.getDate(getTimeZone());
	}
	
	public LocalTime getCurrentTime() {
		return DateUtil.getCurrentLocalTime(getTimeZone());
	}

	public LocalDate getCurrentLocalDate() {
		return DateUtil.getCurrentLocalDate(getTimeZone());
	}
	
	public List<String> getRoles() {
		String sessionId = httpServletRequest.getHeader(UserMgmtUtil.AUTHORIZATION);
		String userinfo;
		try {
			List<String> roleNames = new ArrayList<>();
			userinfo = CachedSessionToken.getUserInfoBySessionId(appProperties.getSsu().getUserinfoUri(), sessionId);
			JSONObject jsonObject = JSONHelper.buidJSONObject(userinfo);
			JSONArray jsonArray = (JSONArray) jsonObject.get("roles");
			
			for (int i = 0; i < jsonArray.size(); i++) {
				roleNames.add((String) jsonArray.get(i));
			}
			
			return roleNames;
		} catch (Exception e) {
			throw new RuntimeException(e.getMessage(), e);
		}
	}
	
	public List<String> getUser() {
		String sessionId = httpServletRequest.getHeader(UserMgmtUtil.AUTHORIZATION);
		String userinfo;
		try {
			List<String> userNames = new ArrayList<>();
			userinfo = CachedSessionToken.getUserInfoBySessionId(appProperties.getSsu().getUserinfoUri(), sessionId);
			JSONObject jsonObject = JSONHelper.buidJSONObject(userinfo);
			JSONArray jsonArray = (JSONArray) jsonObject.get("name");
			
			for (int i = 0; i < jsonArray.size(); i++) {
				userNames.add((String) jsonArray.get(i));
			}
			
			return userNames;
		} catch (Exception e) {
			throw new RuntimeException(e.getMessage(), e);
		}
	}
}
