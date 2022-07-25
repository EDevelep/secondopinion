package org.secondopinion.filter.interceptors;

import java.util.Map;
import java.util.Objects;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.secondopinion.request.Response;
import org.secondopinion.request.Response.StatusEnum;
import org.secondopinion.utils.UserMgmtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.http.server.ServletServerHttpRequest;
import org.springframework.http.server.ServletServerHttpResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;



@ControllerAdvice
public class CustomResponseBodyAdviceAdapter implements ResponseBodyAdvice<Object> {
    
    @Autowired
    LoggingService loggingService;
    
    /**
     * Determine whether URLs need to be intercepted
     * @param uri
     * @return
     */
    private boolean ignoring(String uri) {
        for (String string : UserMgmtUtil.ignores) {
            if (uri.contains(string)) {
                return true;
            }
        }
        return false;
    }
    
   /* @Autowired
    private ObjectWriter objectWriter;*/
    
    @Override
    public boolean supports(MethodParameter methodParameter, Class<? extends HttpMessageConverter<?>> aClass) {
        return true;
    }
    
    @SuppressWarnings("unchecked")
	@Override
    public Object beforeBodyWrite(Object body, MethodParameter methodParameter, MediaType mediaType,
            Class<? extends HttpMessageConverter<?>> aClass, ServerHttpRequest serverHttpRequest, ServerHttpResponse serverHttpResponse) {
    	if (this.ignoring(serverHttpRequest.getURI().toString())) {
            return body;
        }
    	Response<Object> response = null;
    	if(body instanceof Response) {
    		response = (Response<Object>) body;
    	} else {
    		response = new Response<>();
    		response.setData(body);
    	}
    	
    	if (serverHttpRequest instanceof ServletServerHttpRequest && serverHttpResponse instanceof ServletServerHttpResponse) {
        	
        	HttpServletResponse httpServletResponse = ((ServletServerHttpResponse) serverHttpResponse).getServletResponse();
        	HttpServletRequest httpServletRequest = ((ServletServerHttpRequest) serverHttpRequest).getServletRequest();
        	
        	Map<String, Object> requestObjectMap = (Map<String, Object>) httpServletRequest.getAttribute(UserMgmtUtil.REQUEST_URL_MAP);
        			
        	Object sessionId = requestObjectMap.get(UserMgmtUtil.AUTHORIZATION);
        	if(Objects.isNull(sessionId)) {
        		if(StringUtils.isEmpty(response.getUserSession())) {
        			if(StringUtils.isEmpty(response.getStatus())) {
        				response.setStatus(StatusEnum.FAILURE.name());
        			}
            		if(StringUtils.isEmpty(response.getMessage())) {
            			response.setMessage("Session Id not exists");
            		}
            	}
        	} else {
        		response.setUserSession(String.valueOf(sessionId));
        	}
        	Object data = response.getData();
        	response.setData(null);
        	requestObjectMap.put(UserMgmtUtil.RESPONSE_BODY, response);
        	loggingService.logResponse(httpServletRequest, httpServletResponse , requestObjectMap);
        	response.setData(data);
        }
        
        return response;
    }
}
