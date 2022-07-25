package org.secondopinion.pharmacy.controller;

import java.util.List;

import org.secondopinion.pharmacy.dto.Druglookup;
import org.secondopinion.pharmacy.exception.PharmacyServerException;
import org.secondopinion.pharmacy.service.IDrugsRateCardLookService;
import org.secondopinion.request.Response;
import org.secondopinion.request.Response.StatusEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/drug")
public class DrugsRateCardLookController {

	@Autowired
	private IDrugsRateCardLookService drugsRateCardLookService;

	@GetMapping("/all")
	public ResponseEntity<Response<List<Druglookup>>> fetchAllDrugs() {
		try {
			List<Druglookup> drugsList = drugsRateCardLookService.fetchAllDrugs();
			return new ResponseEntity<>(
					new Response<>(drugsList, StatusEnum.SUCCESS, "Drugs data fetched successfully"), HttpStatus.OK);
		} catch (IllegalArgumentException e) {
			return new ResponseEntity<>(new Response<>(null, StatusEnum.FAILURE, e.getMessage()),
					HttpStatus.BAD_REQUEST);
		} catch (Exception e) {
			throw new PharmacyServerException(e.getMessage(), e);
		}
	}

	@GetMapping("/{name}")
	public ResponseEntity<Response<Druglookup>> fetchDrugByName(@PathVariable("name") String name) {
		try {
			Druglookup drug = drugsRateCardLookService.fetchDrugByName(name);
			return new ResponseEntity<>(new Response<>(drug, StatusEnum.SUCCESS, "Drug data fetched successfully"),
					HttpStatus.OK);
		} catch (IllegalArgumentException e) {
			return new ResponseEntity<>(new Response<>(null, StatusEnum.FAILURE, e.getMessage()),
					HttpStatus.BAD_REQUEST);
		} catch (Exception e) {
			throw new PharmacyServerException(e.getMessage(), e);
		}
	}

	@GetMapping("/fetchDrugByPharmacyId/{pharmacyId}")
	public ResponseEntity<Response<List<Druglookup>>> fetchDrugByPharmacyId(
			@PathVariable("pharmacyId") Long pharmacyId) {
		try {
			List<Druglookup> drug = drugsRateCardLookService.fetchDrugByPharmacyId(pharmacyId);
			return new ResponseEntity<>(new Response<>(drug, StatusEnum.SUCCESS, "Drug data fetched successfully"),
					HttpStatus.OK);
		} catch (IllegalArgumentException e) {
			return new ResponseEntity<>(new Response<>(null, StatusEnum.FAILURE, e.getMessage()),
					HttpStatus.BAD_REQUEST);
		} catch (Exception e) {
			throw new PharmacyServerException(e.getMessage(), e);
		}
	}

	@GetMapping("/id/{drugId}")
	public ResponseEntity<Response<Druglookup>> fetchDrugById(@PathVariable("drugId") Long drugId) {
		try {
			Druglookup drug = drugsRateCardLookService.fetchDrugById(drugId);
			return new ResponseEntity<>(new Response<>(drug, StatusEnum.SUCCESS, "Drug data fetched successfully"),
					HttpStatus.OK);
		} catch (IllegalArgumentException e) {
			return new ResponseEntity<>(new Response<>(null, StatusEnum.FAILURE, e.getMessage()),
					HttpStatus.BAD_REQUEST);
		} catch (Exception e) {
			throw new PharmacyServerException(e.getMessage(), e);
		}
	}

	@GetMapping("/list")
	public ResponseEntity<Response<List<Druglookup>>> fetchAllDrugsByIds(@RequestParam("list") List<Long> drugIds) {
		try {
			List<Druglookup> drugList = drugsRateCardLookService.fetchDrugByIds(drugIds);
			return new ResponseEntity<>(new Response<>(drugList, StatusEnum.SUCCESS, "Drugs data fetched successfully"),
					HttpStatus.OK);
		} catch (IllegalArgumentException e) {
			return new ResponseEntity<>(new Response<>(null, StatusEnum.FAILURE, e.getMessage()),
					HttpStatus.BAD_REQUEST);
		} catch (Exception e) {
			throw new PharmacyServerException(e.getMessage(), e);
		}
	}

}
