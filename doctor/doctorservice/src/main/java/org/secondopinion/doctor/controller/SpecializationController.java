package org.secondopinion.doctor.controller;

import java.util.List;

import org.secondopinion.doctor.dto.Specialization;
import org.secondopinion.doctor.exception.DoctorServerException;
import org.secondopinion.doctor.service.SpecializationService;
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
@RequestMapping("/specialization")
public class SpecializationController {

	@Autowired
	private SpecializationService specializationService;
	
	@GetMapping("/all")
	public ResponseEntity<Response<List<Specialization>>> fetchAllSpecialization() {
		try {
			List<Specialization> drugsList = specializationService.fetchAllSpecialization();
			return new ResponseEntity<>(new Response<>(drugsList, StatusEnum.SUCCESS,
					"Specialization data fetched successfully"), HttpStatus.OK);
		} catch (IllegalArgumentException e) {
			return new ResponseEntity<>(new Response<>(null, StatusEnum.FAILURE, e.getMessage()),
					HttpStatus.BAD_REQUEST);
		} catch (Exception e) {
			throw new DoctorServerException(e.getMessage(), e);
		}
	}

	@GetMapping("/{name}")
	public ResponseEntity<Response<Specialization>> fetchSpecializationByName(@PathVariable("name") String name) {
		try {
			Specialization drug = specializationService.fetchSpecializationByName(name);
			return new ResponseEntity<>(
					new Response<>(drug, StatusEnum.SUCCESS, "Specialization data fetched successfully"),
					HttpStatus.OK);
		} catch (IllegalArgumentException e) {
			return new ResponseEntity<>(new Response<>(null, StatusEnum.FAILURE, e.getMessage()),
					HttpStatus.BAD_REQUEST);
		} catch (Exception e) {
			throw new DoctorServerException(e.getMessage(), e);
		}
	}

	@GetMapping("/id/{specializationId}")
	public ResponseEntity<Response<Specialization>> fetchSpecializationById(@PathVariable("specializationId") Long specializationId) {
		try {
			Specialization drug = specializationService.fetchSpecializationById(specializationId);
			return new ResponseEntity<>(
					new Response<>(drug, StatusEnum.SUCCESS, "Specialization data fetched successfully"),
					HttpStatus.OK);
		} catch (IllegalArgumentException e) {
			return new ResponseEntity<>(new Response<>(null, StatusEnum.FAILURE, e.getMessage()),
					HttpStatus.BAD_REQUEST);
		} catch (Exception e) {
			throw new DoctorServerException(e.getMessage(), e);
		}
	}

	@GetMapping("/list")
	public ResponseEntity<Response<List<Specialization>>> fetchSpecializationByIds(@RequestParam("list") List<Long> drugIds) {
		try {
			List<Specialization> drugList = specializationService.fetchSpecializationByIds(drugIds);
			return new ResponseEntity<>(
					new Response<>(drugList, StatusEnum.SUCCESS, "Specialization data fetched successfully"),
					HttpStatus.OK);
		} catch (IllegalArgumentException e) {
			return new ResponseEntity<>(new Response<>(null, StatusEnum.FAILURE, e.getMessage()),
					HttpStatus.BAD_REQUEST);
		} catch (Exception e) {
			throw new DoctorServerException(e.getMessage(), e);   
		}
	}
}
