package org.secondopinion.caretaker.controller;

import java.util.Arrays;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.secondopinion.caretaker.dto.Caretaker;
import org.secondopinion.caretaker.exception.CareTakerServerException;
import org.secondopinion.caretaker.service.CareTakerLoginService;
import org.secondopinion.common.dto.AuthenticationDTO;
import org.secondopinion.configurations.AppProperties;
import org.secondopinion.enums.SecondOpinionModuleEnum;
import org.secondopinion.filter.UserContextHelper;
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
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CareTakerLoginController {

	@Autowired
	private CareTakerLoginService careTakerLoginService;

	@Autowired
	private HttpServletRequest httpServletRequest;

	@Autowired
	private AppProperties appProperties;

	@PostMapping("/login")
	public ResponseEntity<Response<Caretaker>> loginAsCareTaker(@RequestBody AuthenticationDTO authenticationDTO) {
		Response<Caretaker> response = new Response<>();
		try {
			Caretaker caretaker = careTakerLoginService.login(authenticationDTO.getUserName(),
					authenticationDTO.getPassword());
			response.setMessage("CareTaker data retrieved successfully");
			if (caretaker != null) {
				caretaker.setPassword("");
				response.setData(caretaker);
				
				String sessionId = CachedSessionToken.createSessionId(appProperties.getSsu().getCreateTokenUri(),
						authenticationDTO.getUserName(), SecondOpinionModuleEnum.CARETAKER.name(),
						caretaker.getCaretakerId(), Arrays.asList("CARETAKER"));
				
				UserContextHelper.updatetUserContextForUnRegisterAPI(authenticationDTO.getUserName(), SecondOpinionModuleEnum.CARETAKER.name(), caretaker.getCaretakerId());
				
				response.setUserSession(sessionId);

			} else {
				UserContextHelper.updatetUserContextForUnRegisterAPI(authenticationDTO.getUserName(), SecondOpinionModuleEnum.CARETAKER.name(), -999L);
				response.setStatus("FAILURE");
				response.setMessage("Invalid user credentials");
			}
			return new ResponseEntity<>(response, HttpStatus.OK);
		} catch (IllegalArgumentException e) {
			return new ResponseEntity<>(new Response<>(null, StatusEnum.FAILURE, e.getMessage()),
					HttpStatus.BAD_REQUEST);
		} catch (Exception e) {
			throw new CareTakerServerException(e.getMessage(), e);
		}

	}

	@PostMapping("/logout")
	public ResponseEntity<Response<String>> logoutAaCareTaker() {
		Response<String> response = new Response<>();
		try {
			String uuid = httpServletRequest.getHeader(UserMgmtUtil.AUTHORIZATION);
			httpServletRequest.removeAttribute(UserMgmtUtil.AUTHORIZATION);
			CachedSessionToken.removeSessionId(appProperties.getSsu().getRemoveTokenUri(), uuid);
			response.setMessage("successfully loggedout");

			response.setStatus(StatusEnum.SUCCESS.name());
			@SuppressWarnings("unchecked")
			Map<String, Object> requestObjectMap = (Map<String, Object>) httpServletRequest
					.getAttribute(UserMgmtUtil.REQUEST_URL_MAP);
			requestObjectMap.remove(UserMgmtUtil.AUTHORIZATION);
			return new ResponseEntity<>(new Response<>(null, StatusEnum.SUCCESS, "Successfully loggedout"),
					HttpStatus.OK);
		} catch (IllegalArgumentException e) {
			return new ResponseEntity<>(new Response<>(null, StatusEnum.FAILURE, e.getMessage()),
					HttpStatus.BAD_REQUEST);
		} catch (Exception e) {
			throw new CareTakerServerException(e.getMessage(), e);
		}

	}

}
