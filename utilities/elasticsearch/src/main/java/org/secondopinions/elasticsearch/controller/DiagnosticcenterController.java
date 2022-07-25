package org.secondopinions.elasticsearch.controller;

import java.util.List;

import org.secondopinions.elasticsearch.dto.GeoLocationDTO;
import org.secondopinions.elasticsearch.dto.Response;
import org.secondopinions.elasticsearch.dto.Response.StatusEnum;
import org.secondopinions.elasticsearch.model.Diagnosticcenter;
import org.secondopinions.elasticsearch.model.DiagnosticcenterTest;
import org.secondopinions.elasticsearch.serviceImpl.DiagnosticcenterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import io.swagger.annotations.Api;

@RestController
@Api("/")
@CrossOrigin(origins = "*")
@RequestMapping(value = "/elasticsearchDiagnosticcenterController")
public class DiagnosticcenterController {
  @Autowired
  private DiagnosticcenterService diagnosticcenterService;

  @GetMapping("/find/diagnosticcenter/ByName/{diagnosticcenterName}")
  public ResponseEntity<Response<List<Diagnosticcenter>>> findDiagnosticcenterByName(
      @PathVariable("diagnosticcenterName") String diagnosticcenterName) {
    try {
      List<Diagnosticcenter> medicineDTO = diagnosticcenterService.search(diagnosticcenterName);
      return new ResponseEntity<>(new Response<>(medicineDTO, StatusEnum.SUCCESS, null),
          HttpStatus.OK);
    } catch (IllegalArgumentException e) {
      return new ResponseEntity<>(new Response<>(null, StatusEnum.FAILURE, e.getMessage()),
          HttpStatus.BAD_REQUEST);
    }

  }


  @GetMapping("/searchDiagnosticcenterTest/{keywords}")
  public ResponseEntity<Response<List<DiagnosticcenterTest>>> searchDiagnosticcenterTest(
      @PathVariable("keywords") String keywords) {
    try {
      List<DiagnosticcenterTest> medicineDTO =
          diagnosticcenterService.searchDiagnosticcenterTest(keywords);
      return new ResponseEntity<>(new Response<>(medicineDTO, StatusEnum.SUCCESS, null),
          HttpStatus.OK);
    } catch (IllegalArgumentException e) {
      return new ResponseEntity<>(new Response<>(null, StatusEnum.FAILURE, e.getMessage()),
          HttpStatus.BAD_REQUEST);
    }

  }

  @DeleteMapping("/deleteByDiagnosticcenterId/{diagnosticcenterId}")
  public ResponseEntity<Response<String>> deleteByDiagnosticcenterId(
      @PathVariable("diagnosticcenterId") Long diagnosticcenterId) {
    try {
      diagnosticcenterService.deleteByDiagnosticcenterId(diagnosticcenterId);
      return new ResponseEntity<>(new Response<>("Data deleted", StatusEnum.SUCCESS, null),
          HttpStatus.OK);
    } catch (IllegalArgumentException e) {
      return new ResponseEntity<>(new Response<>(null, StatusEnum.FAILURE, e.getMessage()),
          HttpStatus.BAD_REQUEST);
    }

  }

  @PostMapping("/saveDiagnosticcenter")
  public ResponseEntity<Response<String>> saveDiagnosticcenter(
      @RequestBody Diagnosticcenter diagnosticcenter) {
    try {
      diagnosticcenterService.save(diagnosticcenter);
      return new ResponseEntity<>(new Response<>("Diagnosticcenter save", StatusEnum.SUCCESS, null),
          HttpStatus.OK);
    } catch (IllegalArgumentException e) {
      return new ResponseEntity<>(new Response<>(null, StatusEnum.FAILURE, e.getMessage()),
          HttpStatus.BAD_REQUEST);
    }

  }

  @PostMapping("/saveDiagnosticcenterTest")
  public ResponseEntity<Response<String>> saveDiagnosticcenterTest(
      @RequestBody DiagnosticcenterTest diagnosticcenter) {
    try {
      diagnosticcenterService.save(diagnosticcenter);
      return new ResponseEntity<>(
          new Response<>("Diagnosticcenter Test save", StatusEnum.SUCCESS, null), HttpStatus.OK);
    } catch (IllegalArgumentException e) {
      return new ResponseEntity<>(new Response<>(null, StatusEnum.FAILURE, e.getMessage()),
          HttpStatus.BAD_REQUEST);
    }

  }

  @PostMapping("/getLocationMembers")
  public ResponseEntity<Response<List<Diagnosticcenter>>> getLocationMembers(
      @RequestBody GeoLocationDTO geoLocationDTO) {
    try {
      List<Diagnosticcenter> medicineDTO =
          diagnosticcenterService.getLocationMembers(geoLocationDTO);
      return new ResponseEntity<>(new Response<>(medicineDTO, StatusEnum.SUCCESS, null),
          HttpStatus.OK);
    } catch (IllegalArgumentException e) {
      return new ResponseEntity<>(new Response<>(null, StatusEnum.FAILURE, e.getMessage()),
          HttpStatus.BAD_REQUEST);
    }

  }
}
