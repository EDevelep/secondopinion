package org.secondopinion.patient.controller;

import java.util.List;

import javax.validation.Valid;

import org.secondopinion.patient.dto.Ailments;
import org.secondopinion.patient.exception.PatientServerException;
import org.secondopinion.patient.service.IAilments;
import org.secondopinion.request.Response;
import org.secondopinion.request.Response.StatusEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;

@RestController
@RequestMapping("/ailments")
@Api("/ailments")
public class AilmentsController {

	@Autowired
	private IAilments ailmentservice;

	@PostMapping("/save")
	public ResponseEntity<Response<String>> saveAilment(@RequestBody @Valid Ailments ailments, @RequestParam Long forUserId ) {
		try {
			ailmentservice.saveAilment(ailments);
			return new ResponseEntity<>(new Response<>(null, StatusEnum.SUCCESS, null), HttpStatus.OK);
		} catch (IllegalArgumentException e) {
			return new ResponseEntity<>(new Response<>(null, StatusEnum.FAILURE, e.getMessage()), HttpStatus.BAD_REQUEST);
		} catch (Exception e) {
			throw new PatientServerException(e.getMessage(), e);
		}
		
	}

	@PostMapping("/get/patient/")
	public ResponseEntity<Response<List<Ailments>>> getailment(@RequestParam Long forUserId) {
		try {
			return new ResponseEntity<>(new Response<>(ailmentservice.getaliment(forUserId), StatusEnum.SUCCESS, null), HttpStatus.OK);
		} catch (IllegalArgumentException e) {
			return new ResponseEntity<>(new Response<>(null, StatusEnum.FAILURE, e.getMessage()), HttpStatus.BAD_REQUEST);
		} catch (Exception e) {
			throw new PatientServerException(e.getMessage(), e);
		}
		
	}

}
