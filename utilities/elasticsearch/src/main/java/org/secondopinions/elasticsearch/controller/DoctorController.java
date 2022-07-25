package org.secondopinions.elasticsearch.controller;

import java.util.List;

import org.secondopinions.elasticsearch.dto.Response;
import org.secondopinions.elasticsearch.dto.Response.StatusEnum;
import org.secondopinions.elasticsearch.model.Doctor;
import org.secondopinions.elasticsearch.serviceImpl.DoctorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
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
@RequestMapping(value = "/elasticsearchDoctorController")
public class DoctorController {

	@Autowired
	private DoctorService doctorService;

	@PostMapping("/saveDoctor")
	public ResponseEntity<Response<String>> saveDoctor(@RequestBody Doctor doctorModel) {
		try {
			doctorService.saveDoctor(doctorModel);
			return new ResponseEntity<>(new Response<>("Doctor Saved Successfuly.", StatusEnum.SUCCESS, null),
					HttpStatus.OK);
		} catch (IllegalArgumentException e) {
			return new ResponseEntity<>(new Response<>(null, StatusEnum.FAILURE, e.getMessage()),
					HttpStatus.BAD_REQUEST);
		}

	}

	@GetMapping("/find/bydoctorspecialization/{doctorspecialization}")
	public ResponseEntity<Response<List<Doctor>>> findDoctorspecialization(
			@PathVariable("doctorspecialization") String doctorspecialization) {
		try {
			List<Doctor> doctor = doctorService.search(doctorspecialization);
			return new ResponseEntity<>(new Response<>(doctor, StatusEnum.SUCCESS, null), HttpStatus.OK);
		} catch (IllegalArgumentException e) {
			return new ResponseEntity<>(new Response<>(null, StatusEnum.FAILURE, e.getMessage()),
					HttpStatus.BAD_REQUEST);
		}

	}

	@GetMapping("/searchDocotor/{firstName}")
	public ResponseEntity<Response<List<Doctor>>> searchDocotor(@PathVariable("firstName") String firstName) {
		try {
			List<Doctor> doctor = doctorService.searchDocotor(firstName);
			return new ResponseEntity<>(new Response<>(doctor, StatusEnum.SUCCESS, null), HttpStatus.OK);
		} catch (IllegalArgumentException e) {
			return new ResponseEntity<>(new Response<>(null, StatusEnum.FAILURE, e.getMessage()),
					HttpStatus.BAD_REQUEST);
		}

	}

	@DeleteMapping("/delete")
	public ResponseEntity<Response<String>> delete(String id) {
		try {
			doctorService.delete(id);
			return new ResponseEntity<>(new Response<>("Delted ", StatusEnum.SUCCESS, "Delted "), HttpStatus.OK);
		} catch (IllegalArgumentException e) {
			return new ResponseEntity<>(new Response<>(null, StatusEnum.FAILURE, e.getMessage()),
					HttpStatus.BAD_REQUEST);
		}

	}

}
