package org.secondopinion.userMgmt.controller;

import java.util.Arrays;

import org.secondopinion.common.dto.AuthenticationDTO;
import org.secondopinion.configurations.AppProperties;
import org.secondopinion.request.Response;
import org.secondopinion.request.Response.StatusEnum;
import org.secondopinion.ssclient.CachedSessionToken;
import org.secondopinion.userMgmt.dto.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import org.secondopinion.userMgmt.service.UserService;

@RestController
@RequestMapping("/userLoginWebservice")
public class UserLoginRsWebservice {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private AppProperties appProperties;
	
	@PostMapping(path = "/userLoginService")
	public ResponseEntity<Response<User>> userLogin(@RequestBody AuthenticationDTO authenticationDTO) {
		Response<User> response = new Response<>();
		HttpStatus httpStatus = HttpStatus.OK;
		try{
			User user = getUserService().getUser(authenticationDTO.getUserName(), authenticationDTO.getPassword());
			if(user != null){
				
				user.setPassword("");
				String sessionId = CachedSessionToken.createSessionId(appProperties.getSsu().getCreateTokenUri(), authenticationDTO.getUserName(), 
						 "USER_MGMT", user.getId(), Arrays.asList("HUBVANTAGE_ADMIN"));//UserContextMngr.registerUser(user);
				response.setUserSession(sessionId);
				response.setData(user);
				userService.updateLastLoginTime(user.getUserId());
			}else{
				httpStatus = HttpStatus.UNAUTHORIZED;
				response.setStatus("FAILURE");
				response.setMessage("Invalid user credentials");
			}
			return new ResponseEntity<>(response, httpStatus);
		}catch(Exception ex){
			return new ResponseEntity<>(new Response<>(null, StatusEnum.FAILURE, ex.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	
	public UserService getUserService() {
		return userService;
	}

	public void setUserService(UserService userService) {
		this.userService = userService;
	}


	public AppProperties getAppProperties() {
		return appProperties;
	}


	public void setAppProperties(AppProperties appProperties) {
		this.appProperties = appProperties;
	}
	
	
}
