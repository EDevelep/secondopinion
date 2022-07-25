package org.secondopinion.superadmin.exception;

import org.secondopinion.configurations.AppProperties;
import org.secondopinion.filter.interceptors.LoggingService;
import org.secondopinion.request.Response;
import org.secondopinion.request.Response.StatusEnum;
import org.secondopinion.utils.MailProperties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class SuperAdminServerExceptionHandler {
  private static Logger LOG = LoggerFactory.getLogger(SuperAdminServerExceptionHandler.class);
  @Autowired
  private MailProperties mailProperties;

  @Autowired
  private AppProperties appProperties;

  @Autowired
  private LoggingService loggingService;

  @ExceptionHandler(value = SuperAdminServerException.class)
  public ResponseEntity<Response<String>> handleGenericException(SuperAdminServerException dse,
      Exception ex) {
    Response<String> response = new Response<>();
    response.setMessage(dse.getMessage());
    response.setStatus(StatusEnum.FAILURE.name());
    // sending email to admin user
    loggingService.sendErrorMessageMail(mailProperties, "Exception in Patient",
        appProperties.getSmtp().getErrorMessageEmail(), ex);
    LOG.error(dse.getMessage(), ex);
    return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
  }

  @ExceptionHandler(value = IllegalArgumentException.class)
  public ResponseEntity<Response<String>> handleIllegalRequestException(
      IllegalArgumentException dse) {
    Response<String> response = new Response<>();
    response.setMessage(dse.getMessage());
    response.setStatus(StatusEnum.FAILURE.name());

    return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
  }

}
//
