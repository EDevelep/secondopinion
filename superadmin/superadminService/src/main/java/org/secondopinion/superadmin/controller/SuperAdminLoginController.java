package org.secondopinion.superadmin.controller;

import java.util.Arrays;

import javax.servlet.http.HttpServletRequest;

import org.secondopinion.common.dto.AuthenticationDTO;
import org.secondopinion.configurations.AppProperties;
import org.secondopinion.enums.SecondOpinionModuleEnum;

import org.secondopinion.request.Response;
import org.secondopinion.request.Response.StatusEnum;
import org.secondopinion.ssclient.CachedSessionToken;
import org.secondopinion.superadmin.dto.User;
import org.secondopinion.superadmin.exception.SuperAdminServerException;
import org.secondopinion.superadmin.service.SuperAdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;

@RestController
public class SuperAdminLoginController {
	@Autowired
	private AppProperties appProperties;

	@Autowired
	private HttpServletRequest httpServletRequest;

	@Autowired
	private SuperAdminService superAdminService;

	@PostMapping("/login")
	public ResponseEntity<Response<User>> loginAsPatient(@RequestBody AuthenticationDTO authenticationDTO) {

		Response<User> response = new Response<>();
		HttpStatus httpStatus = HttpStatus.OK;

		try {
			User user = superAdminService.loginAsSuperAdmin(authenticationDTO.getUserName(),
					authenticationDTO.getPassword());
			if (user != null) {
				user.setPassword("");
				String sessionId = CachedSessionToken.createSessionId(appProperties.getSsu().getCreateTokenUri(),
						user.getUserName(), SecondOpinionModuleEnum.SUPERADMIN.name(), user.getUserId(),
						Arrays.asList("PATIENT"));
				response.setUserSession(sessionId);
				response.setMessage("");
				response.setData(user);

			} else {
				httpStatus = HttpStatus.UNAUTHORIZED;
				response.setStatus("FAILURE");
				response.setMessage("Invalid user credentials");
			}
			return new ResponseEntity<>(response, httpStatus);
		} catch (IllegalArgumentException e) {
			return new ResponseEntity<>(new Response<>(null, StatusEnum.FAILURE, e.getMessage()),
					HttpStatus.BAD_REQUEST);
		} catch (Exception e) {
			throw new SuperAdminServerException(e.getMessage(), e);
		}
	}

}
