package org.secondopinion.patient.controller;

import java.util.List;

import org.secondopinion.patient.dto.CountrycodeDTO;
import org.secondopinion.patient.exception.PatientServerException;
import org.secondopinion.patient.service.impl.CountryCodeService;
import org.secondopinion.request.Response;
import org.secondopinion.request.Response.StatusEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/countrycodecontroller")
public class CountryCodeController {
	@Autowired
	private CountryCodeService countryCodeService;

	@GetMapping("/getCountrycode")
	public ResponseEntity<Response<List<CountrycodeDTO>>> getCountrycode(@RequestParam ("postalcode") String postalcode) {
		try {
			return new ResponseEntity<>(
					new Response<>(countryCodeService.getCountrycode(postalcode), StatusEnum.SUCCESS, null),
					HttpStatus.OK);
		} catch (IllegalArgumentException e) {
			return new ResponseEntity<>(new Response<>(null, StatusEnum.FAILURE, e.getMessage()),
					HttpStatus.BAD_REQUEST);
		} catch (Exception e) {
			throw new PatientServerException(e.getMessage(), e);
		}

	}
}
