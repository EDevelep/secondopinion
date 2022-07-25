package org.secondopinion.caretaker.controller;


import javax.validation.Valid;

import org.secondopinion.caretaker.dto.Caretakerfcmtoken;
import org.secondopinion.caretaker.exception.CareTakerServerException;
import org.secondopinion.caretaker.service.Caretakerfcmservice;
import org.secondopinion.request.Response;
import org.secondopinion.request.Response.StatusEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/fcm/token")
public class CareTakerFcmController {
	
	@Autowired
	private Caretakerfcmservice doctorfcmservice;
	
	@PostMapping
	public ResponseEntity<Response<Caretakerfcmtoken>> saveFcmToken(@RequestBody @Valid Caretakerfcmtoken doctorFcmToken) {
		try {
			return new ResponseEntity<>(new Response<>(doctorfcmservice.saveCaretakerfcmToken(doctorFcmToken), 
					StatusEnum.SUCCESS, null), HttpStatus.OK);
			

		} catch (IllegalArgumentException e) {
			return new ResponseEntity<>(new Response<>(null, StatusEnum.FAILURE, e.getMessage()), HttpStatus.BAD_REQUEST);
		} catch (Exception e) {
			throw new CareTakerServerException(e.getMessage(), e);
		}
		
	}

}
