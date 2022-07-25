package org.secondopinion.doctor.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import org.secondopinion.configurations.AppProperties;
import org.secondopinion.filter.interceptors.LoggingService;
import org.secondopinion.request.Response;
import org.secondopinion.request.Response.StatusEnum;
import org.secondopinion.utils.MailProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class DoctorServerExceptionHandler {

	private static Logger logger = LoggerFactory.getLogger(DoctorServerExceptionHandler.class);
	@Autowired
	private MailProperties mailProperties;
	
	@Autowired
	private AppProperties appProperties;
	
	@Autowired
	private LoggingService loggingService;
	
	@ExceptionHandler(value = DoctorServerException.class)
	public ResponseEntity<Response<String>> handleGenericException(DoctorServerException dse, Exception ex) {
		Response<String> response = new Response<>();
		response.setMessage(dse.getMessage());
		response.setStatus(StatusEnum.FAILURE.name());
		loggingService.sendErrorMessageMail(mailProperties, "Exception in Doctor", appProperties.getSmtp().getErrorMessageEmail(), ex);
		logger.error(dse.getMessage(), ex);
		
		return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
	} 

	@ExceptionHandler(value = IllegalRequestException.class)
	public ResponseEntity<Response<String>> handleIllegalRequestException(IllegalRequestException dse) {
		Response<String> response = new Response<>();
		response.setMessage(dse.getMessage());
		response.setStatus(StatusEnum.FAILURE.name());
		
		return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
	} 
	
	@ExceptionHandler(value = IllegalArgumentException.class)
	public ResponseEntity<Response<String>> handleIllegalRequestException(IllegalArgumentException dse) {
		Response<String> response = new Response<>();
		response.setMessage(dse.getMessage());
		response.setStatus(StatusEnum.FAILURE.name());
		
		return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
	} 

}
