package org.secondopinion.filter.interceptors;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.secondopinion.utils.MailProperties;

public interface LoggingService {

	void logRequest(HttpServletRequest request, Object object);

	void logResponse(HttpServletRequest httpServletRequest, HttpServletResponse servletResponse, 
			Map<String, Object> requestObjectMap);
	void sendErrorMessageMail(MailProperties mailProperties, String module, String emailids, Exception ex);

}
