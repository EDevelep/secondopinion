package org.secondopinions.elasticsearch.controller;

import java.util.List;

import org.secondopinions.elasticsearch.dto.GeoLocationDTO;
import org.secondopinions.elasticsearch.dto.PharmacyDTO;
import org.secondopinions.elasticsearch.dto.Response;
import org.secondopinions.elasticsearch.dto.Response.StatusEnum;
import org.secondopinions.elasticsearch.model.Pharmacy;
import org.secondopinions.elasticsearch.serviceImpl.PharmacyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@Api("/")
@CrossOrigin(origins = "*")
@RequestMapping(value = "/elasticsearchPharmacyController")
public class PharmacyController {

	
	
	@Autowired
	private PharmacyService pharmacyService;
	
	
	@GetMapping("/find/pharmacy/byName/{pharmacyName}")
	public ResponseEntity<Response<List<Pharmacy>>> findPharmacyByName(
			@PathVariable("pharmacyName") String pharmacyName) {
		try {
			List<Pharmacy> medicineDTO = pharmacyService.search(pharmacyName);
			return new ResponseEntity<>(new Response<>(medicineDTO, StatusEnum.SUCCESS, null), HttpStatus.OK);
		} catch (IllegalArgumentException e) {
			return new ResponseEntity<>(new Response<>(null, StatusEnum.FAILURE, e.getMessage()),
					HttpStatus.BAD_REQUEST);
		}

	}
	
	@PostMapping("/getLocationMembers")
	public ResponseEntity<Response<List<Pharmacy>>> getLocationMembers(@RequestBody  GeoLocationDTO geoLocationDTO) {
		try {
			List<Pharmacy> medicineDTO = pharmacyService.getLocationMembers(geoLocationDTO);
			return new ResponseEntity<>(new Response<>(medicineDTO, StatusEnum.SUCCESS, null), HttpStatus.OK);
		} catch (IllegalArgumentException e) {
			return new ResponseEntity<>(new Response<>(null, StatusEnum.FAILURE, e.getMessage()),
					HttpStatus.BAD_REQUEST);
		}

	}
	
	@PostMapping("/savePharmacy")
	public ResponseEntity<Response<String>> savePharmacy(@RequestBody  Pharmacy pharmacy) {
		try {
			 pharmacyService.save(pharmacy);
			return new ResponseEntity<>(new Response<>("Pharmacy Saved ", StatusEnum.SUCCESS, null), HttpStatus.OK);
		} catch (IllegalArgumentException e) {
			return new ResponseEntity<>(new Response<>(null, StatusEnum.FAILURE, e.getMessage()),
					HttpStatus.BAD_REQUEST);
		}

	}

}
