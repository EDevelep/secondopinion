package org.securityservice.controller;

import org.secondopinion.utils.StringUtil;
import org.securityservice.exception.CustomResponse;
import org.securityservice.exception.TokenHandlerException;
import org.securityservice.jwt.UserInfo;
import org.securityservice.serviceimpl.UserCacheService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;

@RestController
@RequestMapping("/api/token")
public class TokenRestAPI {

	@Autowired
	private UserCacheService userCacheService;

	/**
	 * 
	 * @param userName
	 * @return
	 */
	@PostMapping("/create")
	public ResponseEntity<CustomResponse<String>> createToken(@RequestParam("userinfojson") String userinfojson) {

		HttpStatus httpStatus = HttpStatus.OK;
		try {
			if (StringUtil.isNullOrEmpty(userinfojson)) {
				throw new IllegalArgumentException("userinfo json can not be null.");
			}

			Gson gson = new Gson();
			UserInfo userInfo = gson.fromJson(userinfojson, UserInfo.class);

			String uuid = userCacheService.cacheingUser(userInfo);
			return new ResponseEntity<>(new CustomResponse<>(uuid, null, null, HttpStatus.OK.value()), httpStatus);
		} catch (Exception ex) {
			throw new TokenHandlerException(ex.getMessage(), ex);
		}

	}

	/**
	 * 
	 * @param userName
	 * @return
	 */
	@GetMapping("/userinfo")
	public ResponseEntity<CustomResponse<String>> getUserInfo(@RequestParam("uuid") String uuid) {

		HttpStatus httpStatus = HttpStatus.OK;
		try {
			String userinfo = userCacheService.getUserNameByUUID(uuid);
			return new ResponseEntity<>(new CustomResponse<>(userinfo, null, null, HttpStatus.OK.value()), httpStatus);
		} catch (Exception ex) {
			throw new TokenHandlerException(ex.getMessage(), ex);
		}

	}

	/**
	 * 
	 * @param uuid
	 * @return
	 */
	@PostMapping("/validate")
	public ResponseEntity<CustomResponse<Boolean>> validateToken(@RequestParam("uuid") String uuid) {

		HttpStatus httpStatus = HttpStatus.OK;
		try {
			Boolean isValid = userCacheService.validatingCachedUser(uuid);
			return new ResponseEntity<>(new CustomResponse<>(isValid, null, null, HttpStatus.OK.value()), httpStatus);
		} catch (Exception ex) {
			throw new TokenHandlerException(ex.getMessage(), ex);
		}

	}

	/**
	 * 
	 * @param uuid
	 * @param userName
	 * @return
	 */
	@PostMapping("/regenerate")
	public ResponseEntity<CustomResponse<String>> regenerate(@RequestParam("uuid") String uuid) {

		HttpStatus httpStatus = HttpStatus.OK;
		try {
			String newUUID = userCacheService.regenerateToken(uuid);
			return new ResponseEntity<>(new CustomResponse<>(newUUID, null, null, HttpStatus.OK.value()), httpStatus);
		} catch (Exception ex) {
			throw new TokenHandlerException(ex.getMessage(), ex);
		}
	}

	/**
	 * 
	 * @param uuid
	 * @return
	 */
	@PostMapping("/remove")
	public ResponseEntity<CustomResponse<Boolean>> removeToken(@RequestParam("uuid") String uuid) {

		HttpStatus httpStatus = HttpStatus.OK;
		try {
			Boolean isRemoved = userCacheService.removeToken(uuid);
			return new ResponseEntity<>(new CustomResponse<>(isRemoved, null, null, HttpStatus.OK.value()), httpStatus);
		} catch (Exception ex) {
			throw new TokenHandlerException(ex.getMessage(), ex);
		}
	}

	/**
	 * 
	 * @param uuid
	 * @param userName
	 * @return
	 */
	@PostMapping("/validateAndGenerateOnExpiry")
	public ResponseEntity<CustomResponse<String>> validateAndGenerateOnExpiry(@RequestParam("uuid") String uuid) {

		HttpStatus httpStatus = HttpStatus.OK;
	
		try {
			String newUUID = userCacheService.validateAndGenerateOnExpiry(uuid);
			return new ResponseEntity<>(new CustomResponse<>(newUUID, null, null, HttpStatus.OK.value()), httpStatus);
		} catch (Exception ex) {
			return new ResponseEntity<>(
					new CustomResponse<>(null, "402", "JWT expired regenerate the Token for the existing session", 402),
					httpStatus);
		}

	}
}
