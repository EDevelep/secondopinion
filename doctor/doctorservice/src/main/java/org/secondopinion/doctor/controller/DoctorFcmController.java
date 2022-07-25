package org.secondopinion.doctor.controller;


import org.secondopinion.doctor.dto.Doctorfcmtoken;
import org.secondopinion.doctor.exception.DoctorServerException;
import org.secondopinion.doctor.service.Doctorfcmservice;
import org.secondopinion.request.Response;
import org.secondopinion.request.Response.StatusEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/fcm/token")
public class DoctorFcmController {

  @Autowired
  private Doctorfcmservice doctorfcmservice;

  @PostMapping
  public ResponseEntity<Response<Doctorfcmtoken>> saveFcmToken(
      @RequestBody Doctorfcmtoken doctorFcmToken) {
    try {
      return new ResponseEntity<>(
          new Response<>(doctorfcmservice.saveDoctorfcmToken(doctorFcmToken), StatusEnum.SUCCESS,
              null),
          HttpStatus.OK);


    } catch (IllegalArgumentException e) {
      return new ResponseEntity<>(new Response<>(null, StatusEnum.FAILURE, e.getMessage()),
          HttpStatus.BAD_REQUEST);
    } catch (Exception e) {
      throw new DoctorServerException(e.getMessage(), e);
    }

  }

}
