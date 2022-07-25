package org.secondopinion.diagnosticcenter.controller;


import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;

import org.secondopinion.common.dto.AuthenticationDTO;
import org.secondopinion.configurations.AppProperties;
import org.secondopinion.diagnosticcenter.dto.Diagnosticcenteruser;
import org.secondopinion.diagnosticcenter.exception.DiagnosticCenterServerException;
import org.secondopinion.diagnosticcenter.service.IDiagnosticCenterUserService;
import org.secondopinion.enums.SecondOpinionModuleEnum;
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
public class DiagnosticCenterUserLoginController {

	@Autowired
	private IDiagnosticCenterUserService diagnosticCenterUserService;
	@Autowired
	private AppProperties appProperties;

	
	
	@Autowired
	private HttpServletRequest httpServletRequest;

	@PostMapping("/login")
	public ResponseEntity<Response<Diagnosticcenteruser>> loginAsDiagnosticcenter(
			@RequestBody AuthenticationDTO authenticationDTO) {
		String sessionId = null;
		try {
			Diagnosticcenteruser diagnosticcenteruser = diagnosticCenterUserService.login(authenticationDTO.getUserName(), authenticationDTO.getPassword());
			String message = "Diagnosticcenteruser data retrieved successfully";
			StatusEnum status = StatusEnum.SUCCESS;
			if (diagnosticcenteruser != null) {
				diagnosticcenteruser.setPassword("");
				List<String> roleNames = diagnosticcenteruser.getRoles().stream().map(role -> role.getRoleName().toUpperCase()).collect(Collectors.toList());
				sessionId = CachedSessionToken.createSessionId(
						appProperties.getSsu().getCreateTokenUri(), diagnosticcenteruser.getDiagnosticName(), 
						SecondOpinionModuleEnum.DIAGNOSTIC_CENTER.name(), diagnosticcenteruser.getId(), roleNames);
			} else {
				status = StatusEnum.FAILURE;
				message = "Invalid user credentials";
			}
			return new ResponseEntity<>(	new Response<>(diagnosticcenteruser, status, message, sessionId),HttpStatus.OK);
		} catch (IllegalArgumentException e) {
			return new ResponseEntity<>(new Response<>(null, StatusEnum.FAILURE, e.getMessage()),HttpStatus.BAD_REQUEST);
		} catch (Exception e) {
			throw new DiagnosticCenterServerException(e.getMessage(), e);
		}
	}
	@SuppressWarnings("unchecked")
	@PostMapping("/logoutasdiagnosticcenter")
	public ResponseEntity<Response<String>> logoutAsDiagnosticcenter() {
		Response<String> response = new Response<>();
		try {
			String uuid = httpServletRequest.getHeader(UserMgmtUtil.AUTHORIZATION);
			CachedSessionToken.removeSessionId(appProperties.getSsu().getRemoveTokenUri(), uuid);
			response.setMessage("successfully loggedout");
			response.setStatus(StatusEnum.SUCCESS.name());
			Map<String, Object> requestObjectMap = (Map<String, Object>) httpServletRequest
					.getAttribute(UserMgmtUtil.REQUEST_URL_MAP);
			requestObjectMap.remove(UserMgmtUtil.AUTHORIZATION);
			return new ResponseEntity<>(new Response<>(null, StatusEnum.SUCCESS, "Successfully loggedout"), HttpStatus.OK);
		} catch (IllegalArgumentException e) {
			return new ResponseEntity<>(new Response<>(null, StatusEnum.FAILURE, e.getMessage()), HttpStatus.BAD_REQUEST);
		} catch (Exception e) {
			throw new DiagnosticCenterServerException(e.getMessage(), e);
		}
		
	}

}
