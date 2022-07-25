package org.secondopinion.patient.controller;


import org.secondopinion.patient.dto.Patientfcmtoken;
import org.secondopinion.patient.exception.PatientServerException;
import org.secondopinion.patient.service.Patientfcmservice;
import org.secondopinion.request.Response;
import org.secondopinion.request.Response.StatusEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/fcm/token")
public class PatientFcmController {
	
	@Autowired
	private Patientfcmservice patientfcmservice;
	
	@PostMapping
	public ResponseEntity<Response<Patientfcmtoken>> saveFcmToken(@RequestBody  Patientfcmtoken patientFcmToken) {
		try {
			return new ResponseEntity<>(new Response<>(patientfcmservice.savePatientfcmToken(patientFcmToken), 
					StatusEnum.SUCCESS, null), HttpStatus.OK);
			

		} catch (IllegalArgumentException e) {
			return new ResponseEntity<>(new Response<>(null, StatusEnum.FAILURE, e.getMessage()), HttpStatus.BAD_REQUEST);
		} catch (Exception e) {
			throw new PatientServerException(e.getMessage(), e);
		}
		
	}

}
