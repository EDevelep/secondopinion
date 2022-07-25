package org.securityservice.exception;

import java.time.LocalDateTime;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExceptionHandlerAdvice {
	
	private static Logger LOG = LoggerFactory.getLogger(ExceptionHandlerAdvice.class);
	
	@ExceptionHandler(value = TokenHandlerException.class)
	public ResponseEntity<CustomResponse<String>> handleGenericTokenException(TokenHandlerException tke, Exception ex) {
		CustomResponse<String> error = new CustomResponse<String>(null, "TOKEN_ERROR", tke.getErrorMessage(), HttpStatus.UNAUTHORIZED.value());
		error.setTimestamp(LocalDateTime.now().toString());
		LOG.error(tke.getMessage(), ex);
		return new ResponseEntity<>(error, HttpStatus.UNAUTHORIZED);
	}
}
