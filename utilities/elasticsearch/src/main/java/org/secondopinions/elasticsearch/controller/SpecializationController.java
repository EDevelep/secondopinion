package org.secondopinions.elasticsearch.controller;

import java.util.List;

import org.secondopinions.elasticsearch.dto.Response;
import org.secondopinions.elasticsearch.dto.Response.StatusEnum;
import org.secondopinions.elasticsearch.model.Specialization;
import org.secondopinions.elasticsearch.serviceImpl.SpecializationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import lombok.Delegate;

@RestController
@Api("/")
@CrossOrigin(origins = "*")
@RequestMapping(value = "/specializationController")
public class SpecializationController {

	@Autowired
	private SpecializationService specializationService;

	@PostMapping("/saveSpecialization")
	public ResponseEntity<Response<String>> saveSpecialization(@RequestBody Specialization specialization) {
		try {
			specializationService.saveSpecialization(specialization);
			return new ResponseEntity<>(
					new Response<>("Specialization Saved ", StatusEnum.SUCCESS, "Specialization Saved"), HttpStatus.OK);
		} catch (IllegalArgumentException e) {
			return new ResponseEntity<>(new Response<>(null, StatusEnum.FAILURE, e.getMessage()),
					HttpStatus.BAD_REQUEST);
		}

	}

	@PostMapping("/searchSpecialization")
	public ResponseEntity<Response<List<String>>> searchSpecialization(@RequestParam String specialization) {
		try {
			List<String> dbspecialization = specializationService.searchSpecialization(specialization);
			return new ResponseEntity<>(new Response<>(dbspecialization, StatusEnum.SUCCESS, "Specialization get "),
					HttpStatus.OK);
		} catch (IllegalArgumentException e) {
			return new ResponseEntity<>(new Response<>(null, StatusEnum.FAILURE, e.getMessage()),
					HttpStatus.BAD_REQUEST);
		}

	}

	@PostMapping("/findAll")
	public ResponseEntity<Response<List<Specialization>>> findAll() {
		try {
			List<Specialization> specialization = specializationService.findAll();
			return new ResponseEntity<>(new Response<>(specialization, StatusEnum.SUCCESS, "Specialization get "),
					HttpStatus.OK);
		} catch (IllegalArgumentException e) {
			return new ResponseEntity<>(new Response<>(null, StatusEnum.FAILURE, e.getMessage()),
					HttpStatus.BAD_REQUEST);
		}

	}

	@DeleteMapping("/delete")
	public ResponseEntity<Response<String>> delete(String id) {
		try {
			specializationService.delete(id);
			return new ResponseEntity<>(new Response<>("Delted ", StatusEnum.SUCCESS, "Specialization get "),
					HttpStatus.OK);
		} catch (IllegalArgumentException e) {
			return new ResponseEntity<>(new Response<>(null, StatusEnum.FAILURE, e.getMessage()),
					HttpStatus.BAD_REQUEST);
		}

	}

}
