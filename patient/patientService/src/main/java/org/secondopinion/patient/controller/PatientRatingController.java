package org.secondopinion.patient.controller;

import java.util.List;

import org.secondopinion.patient.dto.Patientratings;
import org.secondopinion.patient.dto.PatientratingsSearchCriteria;
import org.secondopinion.patient.exception.PatientServerException;
import org.secondopinion.patient.service.IPatientRating;
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

@RestController
@RequestMapping("/rating")
public class PatientRatingController {

	@Autowired
	private IPatientRating ipatientRating;

	@PostMapping("/save")
	public ResponseEntity<Response<String>> saveRatings(@RequestBody Patientratings ratings) {
		try {
			ipatientRating.savePatientratings(ratings);
			return new ResponseEntity<>(new Response<>(null, StatusEnum.SUCCESS, "Rating saved successfully"), HttpStatus.OK);
		} catch (IllegalArgumentException e) {
			return new ResponseEntity<>(new Response<>(null, StatusEnum.FAILURE, null), HttpStatus.BAD_REQUEST);
		} catch (Exception e) {
			throw new PatientServerException(e.getMessage(), e);
		}

	}

	@PostMapping("/get/bycriteria")
	public ResponseEntity<Response<List<Patientratings>>> getRatingsByCriteria(@RequestBody PatientratingsSearchCriteria ratings) {
		try {
			Response<List<Patientratings>> patientRatings = ipatientRating.getRatingsByCriteria(ratings);
			return new ResponseEntity<>(patientRatings, HttpStatus.OK);
		} catch (IllegalArgumentException e) {
			return new ResponseEntity<>(new Response<>(null, StatusEnum.FAILURE, null), HttpStatus.BAD_REQUEST);
		} catch (Exception e) {
			throw new PatientServerException(e.getMessage(), e);
		}

	}

}
