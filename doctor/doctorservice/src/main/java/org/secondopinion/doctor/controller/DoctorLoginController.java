package org.secondopinion.doctor.controller;

import java.util.Arrays;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.secondopinion.common.dto.AuthenticationDTO;
import org.secondopinion.configurations.AppProperties;
import org.secondopinion.doctor.dto.Doctor;
import org.secondopinion.doctor.exception.DoctorServerException;
import org.secondopinion.doctor.service.IDoctorLoginService;
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
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DoctorLoginController {

	@Autowired
	private IDoctorLoginService doctorLoginService;

	@Autowired
	private HttpServletRequest httpServletRequest;

	@Autowired
	private AppProperties appProperties;

	@PostMapping("/login")
	public ResponseEntity<Response<Doctor>> loginAsDoctor(@RequestBody AuthenticationDTO authenticationDTO) {
		Response<Doctor> response = new Response<>();
		try {
			Doctor doctor = doctorLoginService.login(authenticationDTO.getUserName(), authenticationDTO.getPassword(),authenticationDTO.getType());
			response.setMessage("Doctor data retrieved successfully");
			if (doctor != null) {
				doctor.setPassword("");
				response.setData(doctor);
				String sessionId = CachedSessionToken.createSessionId(appProperties.getSsu().getCreateTokenUri(), authenticationDTO.getUserName(),
					SecondOpinionModuleEnum.DOCTOR.name(), doctor.getDoctorId(), Arrays.asList("DOCTOR"));
				response.setUserSession(sessionId);
				
				UserContextHelper.updatetUserContextForUnRegisterAPI(authenticationDTO.getUserName(), SecondOpinionModuleEnum.DOCTOR.name(), doctor.getDoctorId());
				
			} else {
				UserContextHelper.updatetUserContextForUnRegisterAPI(authenticationDTO.getUserName(), SecondOpinionModuleEnum.DOCTOR.name(), -999L);
				response.setStatus("FAILURE");
				response.setMessage("Invalid user credentials");
			}
			return new ResponseEntity<>(response, HttpStatus.OK);
		} catch (IllegalArgumentException e) {
			return new ResponseEntity<>(new Response<>(null, StatusEnum.FAILURE, e.getMessage()), HttpStatus.BAD_REQUEST);
		} catch (Exception e) {
			throw new DoctorServerException(e.getMessage(), e);
		}
		
	}

	@PostMapping("/logoutasdoctor")
	public ResponseEntity<Response<String>> logoutAsDoctor() {
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
			return new ResponseEntity<>(new Response<>(null, StatusEnum.SUCCESS, "Successfully loggedout"), HttpStatus.OK);
		} catch (IllegalArgumentException e) {
			return new ResponseEntity<>(new Response<>(null, StatusEnum.FAILURE, e.getMessage()), HttpStatus.BAD_REQUEST);
		} catch (Exception e) {
			throw new DoctorServerException(e.getMessage(), e);
		}
		
	}

}
