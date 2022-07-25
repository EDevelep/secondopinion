package org.secondopinion.pharmacy.controller;

import org.secondopinion.pharmacy.dto.Pharmacyfcmtoken;
import org.secondopinion.pharmacy.exception.PharmacyServerException;
import org.secondopinion.pharmacy.service.PharmacyFcmservice;
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
public class PharmacyFcmController {

	@Autowired
	private PharmacyFcmservice pharmacyFcmservice;

	@PostMapping
	public ResponseEntity<Response<Pharmacyfcmtoken>> savepharmacyfcmtoken(@RequestBody Pharmacyfcmtoken pharmacyfcmtoken) {
		try {
			Pharmacyfcmtoken pharmacyfcmtoken1=	pharmacyFcmservice.savepharmacyfcmtoken(pharmacyfcmtoken);

			return new ResponseEntity<>(new Response<>(pharmacyfcmtoken1, StatusEnum.SUCCESS, null), HttpStatus.OK);
		} catch (IllegalArgumentException e) {
			return new ResponseEntity<>(new Response<>(null, StatusEnum.FAILURE, null), HttpStatus.BAD_REQUEST);
		} catch (Exception e) {
			throw new PharmacyServerException(e.getMessage(), e);
		}

	}

}
