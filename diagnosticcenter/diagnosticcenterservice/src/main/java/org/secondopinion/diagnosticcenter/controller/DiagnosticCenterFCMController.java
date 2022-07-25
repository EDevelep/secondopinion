package org.secondopinion.diagnosticcenter.controller;


import org.secondopinion.diagnosticcenter.dto.Diagnosticcenterfcmtoken;
import org.secondopinion.diagnosticcenter.exception.DiagnosticCenterServerException;
import org.secondopinion.diagnosticcenter.service.DiagnosticcenterFcmservice;
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
public class DiagnosticCenterFCMController {

	@Autowired
	private DiagnosticcenterFcmservice diagnosticcenterFcmservice;

	
	@PostMapping("/save")
	public ResponseEntity<Response<Diagnosticcenterfcmtoken>> savediagnosticcenterfcmtoken(@RequestBody Diagnosticcenterfcmtoken diagnosticcenterfcmtoken) {
		try {
			Diagnosticcenterfcmtoken diagnosticcenterfcmtoken1=	diagnosticcenterFcmservice.savediagnosticcenterfcmtoken(diagnosticcenterfcmtoken);

			return new ResponseEntity<>(new Response<>(diagnosticcenterfcmtoken1, StatusEnum.SUCCESS, null), HttpStatus.OK);
		} catch (IllegalArgumentException e) {
			return new ResponseEntity<>(new Response<>(null, StatusEnum.FAILURE, null), HttpStatus.BAD_REQUEST);
		} catch (Exception e) {
			throw new DiagnosticCenterServerException(e.getMessage(), e);
		}

	}

}
