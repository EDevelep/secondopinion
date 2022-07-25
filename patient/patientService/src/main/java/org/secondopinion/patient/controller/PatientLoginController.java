package org.secondopinion.patient.controller;

import java.util.Arrays;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.secondopinion.common.dto.AuthenticationDTO;
import org.secondopinion.configurations.AppProperties;
import org.secondopinion.enums.SecondOpinionModuleEnum;
import org.secondopinion.filter.UserContextHelper;
import org.secondopinion.patient.dto.User;
import org.secondopinion.patient.dto.UserResponseDTO;
import org.secondopinion.patient.exception.PatientServerException;
import org.secondopinion.patient.service.impl.UserService;
import org.secondopinion.request.Response;
import org.secondopinion.request.Response.StatusEnum;
import org.secondopinion.ssclient.CachedSessionToken;
import org.secondopinion.utils.UserMgmtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;

@RestController
@RequestMapping("/")
@Api("/")
public class PatientLoginController {

	@Autowired
	private UserService userService;

	@Autowired
	private AppProperties appProperties;

	@Autowired
	private HttpServletRequest httpServletRequest;

	@PostMapping("/login")
	public ResponseEntity<Response<UserResponseDTO>> loginAsPatient(@RequestBody AuthenticationDTO authenticationDTO) {

		Response<UserResponseDTO> response = new Response<>();
		HttpStatus httpStatus = HttpStatus.OK;

		try {
			UserResponseDTO user = userService.getUser(authenticationDTO.getUserName(),
					authenticationDTO.getPassword());
			if (user != null) {

				String sessionId = CachedSessionToken.createSessionId(appProperties.getSsu().getCreateTokenUri(),
						user.getUserName(), SecondOpinionModuleEnum.PATIENT.name(), user.getUserId(),
						Arrays.asList("PATIENT"));

				UserContextHelper.updatetUserContextForUnRegisterAPI(user.getUserName(),
						SecondOpinionModuleEnum.PATIENT.name(), user.getUserId());

				response.setUserSession(sessionId);
				response.setMessage("");
				response.setData(user);

				userService.updateLastLoginTime(user.getUserId());
			} else {
				httpStatus = HttpStatus.UNAUTHORIZED;
				response.setStatus("FAILURE");
				response.setMessage("Invalid user credentials");

				UserContextHelper.updatetUserContextForUnRegisterAPI(authenticationDTO.getUserName(),
						SecondOpinionModuleEnum.PATIENT.name(), -999L);
			}
			return new ResponseEntity<>(response, httpStatus);
		} catch (IllegalArgumentException e) {
			return new ResponseEntity<>(new Response<>(null, StatusEnum.FAILURE, e.getMessage()),
					HttpStatus.BAD_REQUEST);
		} catch (Exception e) {
			throw new PatientServerException(e.getMessage(), e);
		}
	}

	@SuppressWarnings("unchecked")
	@PostMapping("/logoutaspatient")
	public ResponseEntity<Response<String>> logoutAsPatient(@RequestParam Long forUserId) {
		Response<String> response = new Response<>();
		try {
			String uuid = httpServletRequest.getHeader(UserMgmtUtil.AUTHORIZATION);
			CachedSessionToken.removeSessionId(appProperties.getSsu().getRemoveTokenUri(), uuid);
			response.setMessage("successfully loggedout");
			response.setStatus(StatusEnum.SUCCESS.name());
			Map<String, Object> requestObjectMap = (Map<String, Object>) httpServletRequest
					.getAttribute(UserMgmtUtil.REQUEST_URL_MAP);
			requestObjectMap.remove(UserMgmtUtil.AUTHORIZATION);
			return new ResponseEntity<>(new Response<>(null, StatusEnum.SUCCESS, "Successfully loggedout"),
					HttpStatus.OK);
		} catch (IllegalArgumentException e) {
			return new ResponseEntity<>(new Response<>(null, StatusEnum.FAILURE, e.getMessage()),
					HttpStatus.BAD_REQUEST);
		} catch (Exception e) {
			throw new PatientServerException(e.getMessage(), e);
		}

	}
}
