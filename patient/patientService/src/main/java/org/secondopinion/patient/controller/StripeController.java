
package org.secondopinion.patient.controller;

import java.util.Collection;

import org.secondopinion.patient.dto.Carddetails;
import org.secondopinion.patient.dto.PatientPaymentDetails;
import org.secondopinion.patient.dto.PatientPaymentDetailsDTO;
import org.secondopinion.patient.exception.PatientServerException;
import org.secondopinion.patient.service.IPatientPaymentDetailsService;
import org.secondopinion.request.Response;
import org.secondopinion.request.Response.StatusEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/payment")
public class StripeController {

	@Autowired
	private IPatientPaymentDetailsService customerService;

	@PostMapping("/charge")
	public ResponseEntity<Response<String>> chargeThePatient(@RequestBody PatientPaymentDetails patientPaymentDetails) {
		try {

			String cardStatus = customerService.chargeThePatient(patientPaymentDetails);
			return new ResponseEntity<>(new Response<>(cardStatus, StatusEnum.SUCCESS, cardStatus),
					HttpStatus.ACCEPTED);
		} catch (IllegalArgumentException e) {
			return new ResponseEntity<>(new Response<>(null, StatusEnum.FAILURE, e.getMessage()),
					HttpStatus.BAD_REQUEST);
		} catch (Exception e) {
			throw new PatientServerException(e.getMessage(), e);
		}

	}
	
	@PostMapping("/chargethepatientforinvoice")
	public ResponseEntity<Response<PatientPaymentDetailsDTO>> chargeThePatientForInvoice(@RequestBody PatientPaymentDetailsDTO patientPaymentDetails) {
		try {

			PatientPaymentDetailsDTO cardStatus = customerService.chargeThePatientForInvoice(patientPaymentDetails);
			return new ResponseEntity<>(new Response<>(cardStatus, StatusEnum.SUCCESS, "OrderId Get "),
					HttpStatus.ACCEPTED);
		} catch (IllegalArgumentException e) {
			return new ResponseEntity<>(new Response<>(null, StatusEnum.FAILURE, e.getMessage()),
					HttpStatus.BAD_REQUEST);
		} catch (Exception e) {
			throw new PatientServerException(e.getMessage(), e);
		}

	}

	@PostMapping("/carddetails")
	public ResponseEntity<Response<String>> saveCard(@RequestBody Carddetails carddetails) {
		try {
			customerService.save(carddetails);
			return new ResponseEntity<>(new Response<>(null, StatusEnum.SUCCESS, null), HttpStatus.OK);
		} catch (IllegalArgumentException e) {
			return new ResponseEntity<>(new Response<>(null, StatusEnum.FAILURE, e.getMessage()),
					HttpStatus.BAD_REQUEST);
		} catch (Exception e) {
			throw new PatientServerException(e.getMessage(), e);
		}

	}

	@PostMapping("/carddetails/update")
	public ResponseEntity<Response<String>> updateCard(@RequestBody Carddetails carddetails) {
		try {
			customerService.save(carddetails);
			return new ResponseEntity<>(new Response<>(null, StatusEnum.SUCCESS, null), HttpStatus.OK);
		} catch (IllegalArgumentException e) {
			return new ResponseEntity<>(new Response<>(null, StatusEnum.FAILURE, e.getMessage()),
					HttpStatus.BAD_REQUEST);
		} catch (Exception e) {
			throw new PatientServerException(e.getMessage(), e);
		}

	}

	@DeleteMapping("/carddetails/delete/{cardId}")
	public ResponseEntity<Response<String>> deleteAddress(@PathVariable Long cardId) {
		try {
			customerService.deletecardDetails(cardId);
			return new ResponseEntity<>(new Response<>(null, StatusEnum.SUCCESS, null), HttpStatus.OK);
		} catch (IllegalArgumentException e) {
			return new ResponseEntity<>(new Response<>(null, StatusEnum.FAILURE, e.getMessage()),
					HttpStatus.BAD_REQUEST);
		} catch (Exception e) {
			throw new PatientServerException(e.getMessage(), e);
		}

	}

	@GetMapping("/carddetails/get/{cardId}")
	public ResponseEntity<Response<Carddetails>> getcardDetailsById(@PathVariable("cardId") Long cardId) {
		try {
			Carddetails carddetails = customerService.getcardDetailsById(cardId);
			return new ResponseEntity<>(new Response<>(carddetails, StatusEnum.SUCCESS, null), HttpStatus.OK);
		} catch (IllegalArgumentException e) {
			return new ResponseEntity<>(new Response<>(null, StatusEnum.FAILURE, e.getMessage()),
					HttpStatus.BAD_REQUEST);
		} catch (Exception e) {
			throw new PatientServerException(e.getMessage(), e);
		}

	}

	@GetMapping(path = "/carddetails/get/user")
	public ResponseEntity<Response<Collection<Carddetails>>> getCardDetailsbyUserID(@RequestParam Long UserId) {
		try {

			Collection<Carddetails> carddetails = customerService.getCardDetailsbyUserID(UserId);
			return new ResponseEntity<>(new Response<>(carddetails, StatusEnum.SUCCESS, null), HttpStatus.OK);
		} catch (IllegalArgumentException e) {
			return new ResponseEntity<>(new Response<>(null, StatusEnum.FAILURE, e.getMessage()),
					HttpStatus.BAD_REQUEST);
		} catch (Exception e) {
			throw new PatientServerException(e.getMessage(), e);
		}

	}
}
