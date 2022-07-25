package org.secondopinion.diagnosticcenter.controller;


import java.util.List;

import org.secondopinion.diagnosticcenter.dto.Diagnosticcenterratings;
import org.secondopinion.diagnosticcenter.dto.DiagnosticcenterratingsDTO;
import org.secondopinion.diagnosticcenter.exception.DiagnosticCenterServerException;
import org.secondopinion.diagnosticcenter.service.IRatingsDiagnosticcenter;
import org.secondopinion.request.Response;
import org.secondopinion.request.Response.StatusEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/ratings")
public class DiagnosticcenterRatingsController {

	@Autowired
	private IRatingsDiagnosticcenter iRatingsDiagnosticcenter;

	
	@PostMapping("/save")
	public ResponseEntity<Response<String>> saveRatings(@RequestBody Diagnosticcenterratings ratings) {
		try {
			iRatingsDiagnosticcenter.saveRatings(ratings);
			return new ResponseEntity<>(new Response<>(null, StatusEnum.SUCCESS, null), HttpStatus.OK);
		} catch (IllegalArgumentException e) {
			return new ResponseEntity<>(new Response<>(null, StatusEnum.FAILURE, null), HttpStatus.BAD_REQUEST);
		} catch (Exception e) {
			throw new DiagnosticCenterServerException(e.getMessage(), e);
		}

	}

	@GetMapping("/getpharmacyratings")
	public ResponseEntity<Response<List<Diagnosticcenterratings>>> getRatingByid(
			@RequestBody DiagnosticcenterratingsDTO diagnosticcenterratingsDTO) {
		try {
			Response<List<Diagnosticcenterratings>> ratings = iRatingsDiagnosticcenter
					.getRatingsBydiagnosticcenterSerchCritera(diagnosticcenterratingsDTO);
			return new ResponseEntity<>(ratings, HttpStatus.OK);
		} catch (IllegalArgumentException e) {
			return new ResponseEntity<>(new Response<>(null, StatusEnum.FAILURE, e.getMessage()),
					HttpStatus.BAD_REQUEST);
		} catch (Exception e) {
			throw new DiagnosticCenterServerException(e.getMessage(), e);
		}

	}

	@PutMapping("/update")
	public ResponseEntity<Response<String>> updateRatings(
			@RequestBody Diagnosticcenterratings diagnosticcenterratings) {
		try {
			iRatingsDiagnosticcenter.updateRatings(diagnosticcenterratings);
			return new ResponseEntity<>(new Response<>(null, StatusEnum.SUCCESS, null), HttpStatus.OK);
		} catch (IllegalArgumentException e) {
			return new ResponseEntity<>(new Response<>(null, StatusEnum.FAILURE, e.getMessage()),
					HttpStatus.BAD_REQUEST);
		} catch (Exception e) {
			throw new DiagnosticCenterServerException(e.getMessage(), e);
		}

	}

}
