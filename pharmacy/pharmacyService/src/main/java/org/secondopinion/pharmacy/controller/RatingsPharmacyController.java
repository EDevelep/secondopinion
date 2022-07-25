package org.secondopinion.pharmacy.controller;

import java.util.List;

import org.secondopinion.pharmacy.dto.Pharmacyratings;
import org.secondopinion.pharmacy.dto.PharmacyratingsDTO;
import org.secondopinion.pharmacy.exception.PharmacyServerException;
import org.secondopinion.pharmacy.service.IRatingsPharmacy;
import org.secondopinion.request.Response;
import org.secondopinion.request.Response.StatusEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/ratings")
public class RatingsPharmacyController {

	@Autowired
	private IRatingsPharmacy iRatingsPharmacy;

	@PostMapping("/save")
	public ResponseEntity<Response<String>> saveRatings(@RequestBody Pharmacyratings ratings) {
		try {
			iRatingsPharmacy.saveRatings(ratings);
			return new ResponseEntity<>(new Response<>(null, StatusEnum.SUCCESS, null), HttpStatus.OK);
		} catch (IllegalArgumentException e) {
			return new ResponseEntity<>(new Response<>(null, StatusEnum.FAILURE, null), HttpStatus.BAD_REQUEST);
		} catch (Exception e) {
			throw new PharmacyServerException(e.getMessage(), e);
		}

	}

	@GetMapping("/getpharmacyratings")
	public ResponseEntity<Response<List<Pharmacyratings>>> getRatingByid(
			@RequestBody PharmacyratingsDTO pharmacyratingsDTO) {
		try {
			Response<List<Pharmacyratings>> ratings = iRatingsPharmacy
					.getRatingsByPharmacySerchCritera(pharmacyratingsDTO);
			return new ResponseEntity<>(ratings, HttpStatus.OK);
		} catch (IllegalArgumentException e) {
			return new ResponseEntity<>(new Response<>(null, StatusEnum.FAILURE, e.getMessage()),
					HttpStatus.BAD_REQUEST);
		} catch (Exception e) {
			throw new PharmacyServerException(e.getMessage(), e);
		}

	}

	@PutMapping("/update")
	public ResponseEntity<Response<String>> updateRatings(@RequestBody Pharmacyratings paharmacyratings) {
		try {
			iRatingsPharmacy.updateRatings(paharmacyratings);
			return new ResponseEntity<>(new Response<>(null, StatusEnum.SUCCESS, null), HttpStatus.OK);
		} catch (IllegalArgumentException e) {
			return new ResponseEntity<>(new Response<>(null, StatusEnum.FAILURE, e.getMessage()),
					HttpStatus.BAD_REQUEST);
		} catch (Exception e) {
			throw new PharmacyServerException(e.getMessage(), e);
		}

	}

}
