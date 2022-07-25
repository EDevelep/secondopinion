package org.secondopinion.patient.controller;

import java.util.List;

import org.secondopinion.patient.dto.Patientpreference;
import org.secondopinion.patient.exception.PatientServerException;
import org.secondopinion.patient.service.IPatientpreferenceService;
import org.secondopinion.request.Response;
import org.secondopinion.request.Response.StatusEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/patientpreference")
public class PatientpreferenceController {

	@Autowired
	private IPatientpreferenceService iPatientpreferenceService;

	@PostMapping("/save")
	public ResponseEntity<Response<String>> savePatientpreference(@RequestBody Patientpreference patientpreference) {
		try {
			iPatientpreferenceService.savePatientpreference(patientpreference);
			return new ResponseEntity<>(new Response<>(null, StatusEnum.SUCCESS, null), HttpStatus.OK);
		} catch (IllegalArgumentException e) {
			return new ResponseEntity<>(new Response<>(null, StatusEnum.FAILURE, e.getMessage()),
					HttpStatus.BAD_REQUEST);
		} catch (Exception e) {
			throw new PatientServerException(e.getMessage(), e);
		}

	}

	@GetMapping("/getPatientpreference/{patientid}")
	public ResponseEntity<Response<List<Patientpreference>>> getPatientpreference(
			@PathVariable("patientid") Long patientid) {
		try {

			List<Patientpreference> patientpreferences = iPatientpreferenceService.getPatientpreference(patientid);
			return new ResponseEntity<>(
					new Response<>(patientpreferences, StatusEnum.SUCCESS, "Patientpreference fetched successfully"),
					HttpStatus.OK);

		} catch (IllegalArgumentException e) {
			return new ResponseEntity<>(new Response<>(null, StatusEnum.FAILURE, e.getMessage()),
					HttpStatus.BAD_REQUEST);
		} catch (Exception e) {
			throw new PatientServerException(e.getMessage(), e);
		}

	}

	@GetMapping("/getPatientpreferenceForpharmacy/{pharmacyid}")
	public ResponseEntity<Response<List<Patientpreference>>> getPatientpreferenceForpharmacy(
			@PathVariable("pharmacyid") Long pharmacyid) {
		try {

			List<Patientpreference> patientpreferences = iPatientpreferenceService
					.getPatientpreferenceForpharmacy(pharmacyid);
			return new ResponseEntity<>(
					new Response<>(patientpreferences, StatusEnum.SUCCESS, "Patientpreference fetched successfully"),
					HttpStatus.OK);

		} catch (IllegalArgumentException e) {
			return new ResponseEntity<>(new Response<>(null, StatusEnum.FAILURE, e.getMessage()),
					HttpStatus.BAD_REQUEST);
		} catch (Exception e) {
			throw new PatientServerException(e.getMessage(), e);
		}

	}
}
