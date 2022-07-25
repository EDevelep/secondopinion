package org.secondopinions.elasticsearch.controller;

import org.secondopinions.elasticsearch.dto.PatientDTO;
import org.secondopinions.elasticsearch.dto.Response;
import org.secondopinions.elasticsearch.dto.Response.StatusEnum;
import org.secondopinions.elasticsearch.model.Patient;
import org.secondopinions.elasticsearch.serviceImpl.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import io.swagger.annotations.Api;

@RestController
@Api("/")
@CrossOrigin(origins = "*")
@RequestMapping
public class PatientController {



  @Autowired
  private PatientService patientService;

  @PostMapping("/savePatient")
  public ResponseEntity<Response<Patient>> savePatient(@RequestBody PatientDTO patientDTO) {
    try {
      Patient patient = patientService.save(patientDTO);
      return new ResponseEntity<>(new Response<>(patient, StatusEnum.SUCCESS, null), HttpStatus.OK);
    } catch (IllegalArgumentException e) {
      return new ResponseEntity<>(new Response<>(null, StatusEnum.FAILURE, e.getMessage()),
          HttpStatus.BAD_REQUEST);
    }

  }

}
