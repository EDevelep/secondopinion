package org.secondopinion.userMgmt.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.secondopinion.configurations.AppProperties;
import org.secondopinion.request.Response;
import org.secondopinion.ssclient.CachedSessionToken;

@RestController
@RequestMapping("/userLogoutWebservice")
public class UserLogoutRsWebservice {
	private static Logger LOG = LoggerFactory.getLogger(UserLogoutRsWebservice.class);
	
	@Autowired
	private AppProperties appProperties;

	@RequestMapping(path = "/userLogoutService/{sessionId}", method = RequestMethod.GET, produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<Response<String>> userLogout(@PathVariable("sessionId") String sessionId) {
		
		LOG.debug("Logout service initiated" + sessionId);
		Response<String> response = new Response<>();
		HttpStatus httpStatus =  HttpStatus.OK;
		String status = "SUCCESS";
		String message = "Logged out successfully";
		try{
			CachedSessionToken.removeSessionId(appProperties.getSsu().getRemoveTokenUri(), sessionId);
		} catch (Exception e) {
			message = "Error while logging out the user" + sessionId;
			httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
			LOG.error(message);
			status = "FAILURE";
		}

		response.setStatus(status);
		response.setMessage(message);
		
		 return new ResponseEntity<>(response, httpStatus);
	}

	public AppProperties getAppProperties() {
		return appProperties;
	}

	public void setAppProperties(AppProperties appProperties) {
		this.appProperties = appProperties;
	}
	
	
}