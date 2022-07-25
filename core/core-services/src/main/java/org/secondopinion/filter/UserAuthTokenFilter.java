package org.secondopinion.filter;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.lang3.StringUtils;
import org.codehaus.jettison.json.JSONObject;
import org.secondopinion.configurations.AppProperties;
import org.secondopinion.ssclient.CachedSessionToken;
import org.secondopinion.utils.StringUtil;
import org.secondopinion.utils.UserMgmtUtil;
import org.secondopinion.utils.threadlocal.AppThreadLocal;
import org.secondopinion.utils.threadlocal.RequestInfoBean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

/**
 * 
 * @author vishnue
 *
 */
@Component
@Order(1)
public class UserAuthTokenFilter extends OncePerRequestFilter {

	private static final Logger logger = LoggerFactory.getLogger(UserAuthTokenFilter.class);

	@Autowired
	private AppProperties appProperties;

	@Override
	protected void doFilterInternal(HttpServletRequest httpRequest, HttpServletResponse httpResponse,
			FilterChain filterChain) throws ServletException, IOException {
		String invalidRequestMessage = "Invalid Request";
		Map<String, Object> requestObjectMap = new HashMap<>();
		Long startTime = System.currentTimeMillis();
		try {
			requestObjectMap.put(UserMgmtUtil.START_TIME, startTime);
			requestObjectMap.put(UserMgmtUtil.REQUEST_URL, httpRequest.getRequestURL());
			httpRequest.setAttribute(UserMgmtUtil.REQUEST_URL_MAP, requestObjectMap);

			String notAuthorizedApis = appProperties.getNotAuthorizedApis();

			List<String> notAuthorizedApisWithComma = StringUtil.trimEmptySpaceAndSplitWithDelimeter(notAuthorizedApis);
			Arrays.stream(UserMgmtUtil.ignores).forEach(notAuthorizedApisWithComma::add);

			setUserContextForUnRegisterAPI();

			for (String notAuthorizedApi : notAuthorizedApisWithComma) {
				if (httpRequest.getRequestURI().contains(notAuthorizedApi)) {
					filterChain.doFilter(httpRequest, httpResponse);

					return;
				}
			}

			String sessionId = getJwt(httpRequest);
			if (StringUtils.isEmpty(sessionId)) {
				throw new SessionHandlerException(invalidRequestMessage);
			}
			String newSessionId = CachedSessionToken
					.validateAndGenerateOnExpiry(appProperties.getSsu().getValidateAndGenerateTokenUri(), sessionId);

			if (StringUtil.isNullOrEmpty(newSessionId)) {
				throw new SessionHandlerException(invalidRequestMessage);
			}

			setUserContext(newSessionId);

			requestObjectMap.put(UserMgmtUtil.AUTHORIZATION, newSessionId);

			httpRequest.setAttribute(UserMgmtUtil.REQUEST_URL_MAP, requestObjectMap);

			filterChain.doFilter(httpRequest, httpResponse);

		} catch (SessionHandlerException se) {
			logger.error("Can NOT set user authentication -> Message: {}", se.getMessage(), se);
			httpResponse.sendError(HttpServletResponse.SC_UNAUTHORIZED, invalidRequestMessage);
			PrintWriter out = httpResponse.getWriter();
			out.print(invalidRequestMessage);

		} catch (Exception e) {
			logger.error("Exception occured for the API : " + httpRequest.getRequestURI(), e);
			httpResponse.sendError(HttpServletResponse.SC_BAD_REQUEST, e.getMessage());
			PrintWriter out = httpResponse.getWriter();
			out.print(invalidRequestMessage);
		}
	}

	private void setUserContext(String sessionId) {
		try {
			String userInfo = CachedSessionToken.getUserInfoBySessionId(appProperties.getSsu().getUserinfoUri(),
					sessionId);

			JSONObject jsonObject = new JSONObject(userInfo);

			RequestInfoBean requestInfoBean = new RequestInfoBean();
			requestInfoBean.setRequestStartDate(new Date());
			requestInfoBean.setUserId(jsonObject.getLong("userId"));
			requestInfoBean.setUserName(jsonObject.getString("userName"));
			requestInfoBean.setSessionId(sessionId);
			requestInfoBean.setModuleType(jsonObject.getString("moduleType"));

			AppThreadLocal.reset();
			AppThreadLocal.setUser(requestInfoBean);

		} catch (Exception e) {
			throw new RuntimeException(e.getMessage(), e);
		}

	}

	private void setUserContextForUnRegisterAPI() {
		try {

			RequestInfoBean requestInfoBean = new RequestInfoBean();
			requestInfoBean.setRequestStartDate(new Date());
			requestInfoBean.setUserId(0L);
			requestInfoBean.setUserName("SYSTEM");
//			requestInfoBean.setModuleType(jsonObject.getString("moduleType"));

			AppThreadLocal.reset();
			AppThreadLocal.setUser(requestInfoBean);

		} catch (Exception e) {
			throw new RuntimeException(e.getMessage(), e);
		}

	}

	private String getJwt(HttpServletRequest request) {
		return request.getHeader(UserMgmtUtil.AUTHORIZATION);
	}
}
