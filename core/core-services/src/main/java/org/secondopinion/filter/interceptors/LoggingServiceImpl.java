package org.secondopinion.filter.interceptors;

import java.io.PrintWriter;
import java.io.StringWriter;

import java.util.Date;
import java.util.Enumeration;
import java.util.Map;
import java.util.Objects;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.http.HttpHeaders;
import org.json.simple.JSONObject;
import org.secondopinion.batch.BatchManager;
import org.secondopinion.batch.IBatchDequeCallBack;
import org.secondopinion.common.dto.ExceptionIndexDTO;
import org.secondopinion.common.dto.RequestDTO;
import org.secondopinion.utils.EmailUtil;
import org.secondopinion.utils.JSONHelper;
import org.secondopinion.utils.MailProperties;
import org.secondopinion.utils.UserMgmtUtil;
import org.secondopinion.utils.threadlocal.AppThreadLocal;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.support.RequestContextUtils;

@Service
public class LoggingServiceImpl implements LoggingService, InitializingBean {

	private static final Logger LOGGER = LoggerFactory.getLogger(LoggingServiceImpl.class);

	@Autowired
	private HttpServletRequest servletRequest;

	@Autowired
	private RequestService requestService;

	@Autowired
	private IBatchDequeCallBack<RequestDTO> requestInfoBatchDeque;

	private BatchManager<RequestDTO> batchManager;

	@Override
	public void afterPropertiesSet() throws Exception {
		batchManager = new BatchManager<RequestDTO>(100, requestInfoBatchDeque, 500);
	}

	@SuppressWarnings("unchecked")
	@Override
	public void logRequest(HttpServletRequest httpServletRequest, Object body) {
		Map<String, Object> requestObjectMap = (Map<String, Object>) httpServletRequest
				.getAttribute(UserMgmtUtil.REQUEST_URL_MAP);
		requestObjectMap.put(UserMgmtUtil.REQUEST_BODY, body);
		requestObjectMap.put(UserMgmtUtil.REQUEST_TIMEZONE, RequestContextUtils.getTimeZone(httpServletRequest));
		requestObjectMap.put(UserMgmtUtil.START_TIME, System.currentTimeMillis());
		httpServletRequest.setAttribute(UserMgmtUtil.REQUEST_URL_MAP, requestObjectMap);
	}

	@Override
	public void logResponse(HttpServletRequest httpServletRequest, HttpServletResponse servletResponse,
			Map<String, Object> requestObjectMap) {

		String responseMapAsString = JSONHelper.getGsonWithDate().toJson(requestObjectMap);
		JSONObject requestDTO2 = JSONHelper.buidJSONObject(responseMapAsString);
		LOGGER.info(responseMapAsString);
		RequestDTO requestDTO = new RequestDTO();
		String REQUEST_URL = (String) requestDTO2.get("REQUEST_URL");
		requestDTO.setRequestEndTime(new Date());
		requestDTO.setRequestUrl(REQUEST_URL);
		requestDTO.setRequestStartTime(AppThreadLocal.getRequestStartDate());
		requestDTO.setIpAddress(httpServletRequest.getRemoteAddr());
		requestDTO.setModuleType(AppThreadLocal.getModuleType());
		requestDTO.setUserId(AppThreadLocal.getUserId());
		requestDTO.setUserName(AppThreadLocal.getUserName());
		requestDTO.setTotalTimeTaken(System.currentTimeMillis() - AppThreadLocal.getRequestStartDate().getTime());
		batchManager.add(requestDTO);

	}

	private HttpHeaders getHeaders(HttpServletRequest httpServletRequest) {
		HttpHeaders headers = new HttpHeaders();
		for (Enumeration<?> names = httpServletRequest.getHeaderNames(); names.hasMoreElements();) {
			String name = (String) names.nextElement();
			for (Enumeration<?> values = httpServletRequest.getHeaders(name); values.hasMoreElements();) {
				headers.add(name, (String) values.nextElement());
			}
		}
		return headers;
	}

	@SuppressWarnings("unchecked")
	@Override
	public void sendErrorMessageMail(MailProperties mailProperties, String module, String emailids, Exception ex) {
		ExceptionIndexDTO exceptionIndexDTO = new ExceptionIndexDTO();
		Map<String, Object> requestObjectMap = (Map<String, Object>) servletRequest
				.getAttribute(UserMgmtUtil.REQUEST_URL_MAP);

		Long startTime = (Long) requestObjectMap.get(UserMgmtUtil.START_TIME);
		if (Objects.isNull(startTime)) {
			startTime = System.currentTimeMillis();
		}
		Long endTime = System.currentTimeMillis() - startTime;

		requestObjectMap.remove(UserMgmtUtil.START_TIME);
		requestObjectMap.put(UserMgmtUtil.EXECUTION_TIME, endTime + ("(ms)"));

		String responseMapAsString = JSONHelper.getGsonWithDate().toJson(requestObjectMap);
		JSONObject requestDTO2 = JSONHelper.buidJSONObject(responseMapAsString);
		String REQUEST_URL = (String) requestDTO2.get("REQUEST_URL");
		StringBuilder body = new StringBuilder();
		body.append(responseMapAsString);
		body.append(System.lineSeparator());
		body.append(System.lineSeparator());
		body.append("                                                     ");
		body.append("=====================================================");
		body.append("                                                     ");
		body.append(System.lineSeparator());
		body.append(System.lineSeparator());
		StringWriter sw = new StringWriter();
		ex.printStackTrace(new PrintWriter(sw));
		body.append(sw.toString());

		exceptionIndexDTO.setIpAddress(servletRequest.getRemoteAddr());
		exceptionIndexDTO.setModuleType(module);
		exceptionIndexDTO.setUserId(AppThreadLocal.getUserId());
		exceptionIndexDTO.setUserName(AppThreadLocal.getUserName());
		exceptionIndexDTO.setMessage(body.toString());
		exceptionIndexDTO.setRequestUrl(REQUEST_URL);
		// requestService.saveElasticSearchExceptionObject(exceptionIndexDTO);
		EmailUtil.sendErrorMessageMail(mailProperties, module, emailids, body.toString());

	}

}
