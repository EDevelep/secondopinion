package org.secondopinion.patient;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.List;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.codehaus.jettison.json.JSONObject;
import org.secondopinion.configurations.AppProperties;
import org.secondopinion.enums.SecondOpinionModuleEnum;
import org.secondopinion.patient.dao.RelationshipDAO;
import org.secondopinion.patient.dto.RELATIONSHIP_TYPE;
import org.secondopinion.patient.dto.Relationship;
import org.secondopinion.ssclient.CachedSessionToken;
import org.secondopinion.utils.ObjectUtil;
import org.secondopinion.utils.StringUtil;
import org.secondopinion.utils.UserMgmtUtil;
import org.secondopinion.utils.threadlocal.AppThreadLocal;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
@Order(2)
public class ValidateForUserFilter implements Filter {

	private static final Logger logger = LoggerFactory.getLogger(ValidateForUserFilter.class);

	@Autowired
	private AppProperties appProperties;

	@Autowired
	private RelationshipDAO relationshipDAO;

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		String notAuthorizedApis = appProperties.getNotAuthorizedApis();
		HttpServletResponse httpServletResponse = (HttpServletResponse) response;
		HttpServletRequest httpServletRequest = (HttpServletRequest) request;
		
		logger.info("CORSFilter HTTP Request: {}", httpServletRequest.getMethod());
		List<String> notAuthorizedApisWithComma = StringUtil.trimEmptySpaceAndSplitWithDelimeter(notAuthorizedApis);
		Arrays.stream(UserMgmtUtil.ignores).forEach(notAuthorizedApisWithComma::add);
		
		// notAuthorizedApisWithComma.add("/doctor/newrequest");
		for (String notAuthorizedApi : notAuthorizedApisWithComma) {
			if (httpServletRequest.getRequestURI().contains(notAuthorizedApi)) {
				chain.doFilter(httpServletRequest, httpServletResponse);
				return;
			}
		}
		
		try {

			// TODO: What happens on invoice for Pharmacy?
			if (SecondOpinionModuleEnum.PHARMACY.name().equals(AppThreadLocal.getModuleType())) {
				chain.doFilter(httpServletRequest, httpServletResponse);
				return;
			}

			if (SecondOpinionModuleEnum.DIAGNOSTIC_CENTER.name().equals(AppThreadLocal.getModuleType())) {
				chain.doFilter(httpServletRequest, httpServletResponse);
				return;
			}
			
			Long userId =  AppThreadLocal.getUserId();//jsonObject.getLong("userId");
			String forUserIdString = httpServletRequest.getHeader(UserMgmtUtil.FORUSERID);
			
			if (StringUtils.isEmpty(forUserIdString)) {
				throw new IllegalArgumentException(
						"Invalid information -- Needed details are missing to continue with the request. Please contact support!!!");
			}

			if (!StringUtil.equalsIgnoreCase(SecondOpinionModuleEnum.PATIENT.name(), AppThreadLocal.getModuleType())) {
				validateUserRelationship(Long.parseLong(forUserIdString), userId, AppThreadLocal.getModuleType());
			} else {
				validateUserRelationship(userId, Long.parseLong(forUserIdString), AppThreadLocal.getModuleType());
			}

			chain.doFilter(request, response);
		} catch (Exception e) {
			httpServletResponse.sendError(HttpServletResponse.SC_BAD_REQUEST, e.getMessage());
			PrintWriter out = httpServletResponse.getWriter();
			out.print("Invalid Request: " + e.getMessage());
		}
	}

	public void validateUserRelationship(Long userId, Long forUserId, String moduleName) throws IllegalAccessException {

		RELATIONSHIP_TYPE relationship_TYPE = RELATIONSHIP_TYPE.valueOf(moduleName);
		if (!ObjectUtil.isEqual(forUserId, userId)) {
			Relationship relationship = null;
			if (relationship_TYPE == RELATIONSHIP_TYPE.PATIENT) {
				relationship = relationshipDAO.checkRelationshipExists(userId, forUserId);
			} else {
				relationship = relationshipDAO.checkRelationshipExists(userId, forUserId, relationship_TYPE);
			}
			if (relationship == null) {
				throw new IllegalAccessException(
						" Relationship between entities missing - Cannot continue with the request. Please contact support!!!");
			}
		}
	}

	@Override
	public void destroy() {

	}

}
