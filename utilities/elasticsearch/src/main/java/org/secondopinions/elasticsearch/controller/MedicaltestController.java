package org.secondopinions.elasticsearch.controller;

import java.util.List;

import javax.annotation.PostConstruct;

import org.secondopinions.elasticsearch.dto.Response;
import org.secondopinions.elasticsearch.dto.Response.StatusEnum;
import org.secondopinions.elasticsearch.model.Doctor;
import org.secondopinions.elasticsearch.model.Medicaltest;
import org.secondopinions.elasticsearch.serviceImpl.MedicaltestService;
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

@RestController
@Api("/")
@CrossOrigin(origins = "*")
@RequestMapping(value = "/medicaltestController")
public class MedicaltestController {

	@Autowired
	private MedicaltestService medicaltestService;

	@GetMapping("/searchmedicaltests/{testName}")
	public ResponseEntity<Response<List<Medicaltest>>> searchMedicalTests(@PathVariable("testName") String testName) {
		try {
			List<Medicaltest> doctor = medicaltestService.search(testName);
			return new ResponseEntity<>(new Response<>(doctor, StatusEnum.SUCCESS, null), HttpStatus.OK);
		} catch (IllegalArgumentException e) {
			return new ResponseEntity<>(new Response<>(null, StatusEnum.FAILURE, e.getMessage()),
					HttpStatus.BAD_REQUEST);
		}

	}

	@PostMapping("/savemedicaltest")
	public ResponseEntity<Response<String>> saveMedicalTests(@RequestBody Medicaltest medicaltest) {
		try {
			 medicaltestService.saveMedicalTests(medicaltest);
			return new ResponseEntity<>(new Response<>("Medicaltest is Saved ", StatusEnum.SUCCESS, null), HttpStatus.OK);
		} catch (IllegalArgumentException e) {
			return new ResponseEntity<>(new Response<>(null, StatusEnum.FAILURE, e.getMessage()),
					HttpStatus.BAD_REQUEST);
		}

	}
}
