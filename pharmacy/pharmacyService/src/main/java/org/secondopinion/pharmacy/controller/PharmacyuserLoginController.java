package org.secondopinion.pharmacy.controller;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import javax.servlet.http.HttpServletRequest;
import org.secondopinion.common.dto.AuthenticationDTO;
import org.secondopinion.configurations.AppProperties;
import org.secondopinion.enums.SecondOpinionModuleEnum;
import org.secondopinion.filter.UserContextHelper;
import org.secondopinion.pharmacy.dto.Pharmacyuser;
import org.secondopinion.pharmacy.exception.PharmacyServerException;
import org.secondopinion.pharmacy.service.IPharmacyuserLoginService;
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

@RestController
@RequestMapping("/user")
public class PharmacyuserLoginController {

	@Autowired
	private IPharmacyuserLoginService pharmacyuserLoginService;

	@Autowired
	private AppProperties appProperties;

	@Autowired
	private HttpServletRequest httpServletRequest;

	@PostMapping("/login")
	public ResponseEntity<Response<Pharmacyuser>> loginAsPharmacy(@RequestBody AuthenticationDTO authenticationDTO) {
		String sessionId = null;
		try {
			Pharmacyuser pharmacyuser = pharmacyuserLoginService.login(authenticationDTO.getUserName(),
					authenticationDTO.getPassword());
			String message = "Pharmacy data retrieved successfully";
			StatusEnum status = StatusEnum.SUCCESS;
			if (pharmacyuser != null) {
				pharmacyuser.setPassword("");
				List<String> roleNames = pharmacyuser.getRoles().stream().map(role -> role.getRoleName().toUpperCase())
						.collect(Collectors.toList());
				sessionId = CachedSessionToken.createSessionId(appProperties.getSsu().getCreateTokenUri(),
						authenticationDTO.getUserName(), SecondOpinionModuleEnum.PHARMACY.name(), pharmacyuser.getId(),
						roleNames);

				UserContextHelper.updatetUserContextForUnRegisterAPI(authenticationDTO.getUserName(),
						SecondOpinionModuleEnum.PHARMACY.name(), pharmacyuser.getId());
			} else {
				UserContextHelper.updatetUserContextForUnRegisterAPI(authenticationDTO.getUserName(),
						SecondOpinionModuleEnum.PHARMACY.name(), -999L);
				status = StatusEnum.FAILURE;
				message = "Invalid user credentials";
			}

			return new ResponseEntity<>(new Response<>(pharmacyuser, status, message, sessionId), HttpStatus.OK);
		} catch (IllegalArgumentException e) {
			return new ResponseEntity<>(new Response<>(null, StatusEnum.FAILURE, e.getMessage()),
					HttpStatus.BAD_REQUEST);
		} catch (Exception e) {
			throw new PharmacyServerException(e.getMessage(), e);
		}
	}

	@SuppressWarnings("unchecked")
	@PostMapping("/logoutaspharmacy")
	public ResponseEntity<Response<String>> logoutAsPharmacy(@RequestParam Long pharmacyId) {
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
			throw new PharmacyServerException(e.getMessage(), e);
		}

	}

}
