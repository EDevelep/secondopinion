package org.secondopinion.patient.controller;

import java.util.Date;
import java.util.List;

import org.secondopinion.patient.dto.Notificationalerts;
import org.secondopinion.patient.dto.PatientShippingUpdateDTO;
import org.secondopinion.patient.exception.PatientServerException;
import org.secondopinion.patient.service.INotificationalertsService;
import org.secondopinion.request.Response;
import org.secondopinion.request.Response.StatusEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
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

import io.swagger.annotations.Api;

@RestController
@RequestMapping("/notificationalerts")
@Api("/notificationalerts")
public class NotificationalertsController {
	@Autowired
	private INotificationalertsService notificationalertsService;

	@GetMapping("/get/all")
	public ResponseEntity<Response<List<Notificationalerts>>> pushNotificationsToUser(@RequestParam Long userId) {
		try {
			return new ResponseEntity<>(new Response<>(notificationalertsService.getAllNotificationalerts(userId),
					StatusEnum.SUCCESS, null), HttpStatus.OK);

		} catch (IllegalArgumentException e) {
			return new ResponseEntity<>(new Response<>(null, StatusEnum.FAILURE, e.getMessage()),
					HttpStatus.BAD_REQUEST);
		} catch (Exception ex) {

			throw new PatientServerException(ex.getMessage(), ex);
		}

	}

	@GetMapping("/get/statusisview")
	public ResponseEntity<Response<List<Notificationalerts>>> getNotificationsNotificationsStatusIsview(
			@RequestParam Long forUserId) {
		try {
			return new ResponseEntity<>(
					new Response<>(notificationalertsService.getNotificationsNotificationsStatusIsview(forUserId),
							StatusEnum.SUCCESS, null),
					HttpStatus.OK);

		} catch (IllegalArgumentException e) {
			return new ResponseEntity<>(new Response<>(null, StatusEnum.FAILURE, e.getMessage()),
					HttpStatus.BAD_REQUEST);
		} catch (Exception ex) {

			throw new PatientServerException(ex.getMessage(), ex);
		}

	}

	@PostMapping("/updateNotificationalerts/{notificationId}")
	public ResponseEntity<Response<String>> updateNotificationalerts(
			@PathVariable Long notificationId, @RequestBody Notificationalerts notificationalerts) {
		try {
			notificationalertsService.updateNotificationalerts(notificationId, notificationalerts);
			return new ResponseEntity<>(new Response<>("Notification Is Updated", StatusEnum.SUCCESS, null),
					HttpStatus.OK);
		} catch (IllegalArgumentException e) {
			return new ResponseEntity<>(new Response<>(null, StatusEnum.FAILURE, e.getMessage()),
					HttpStatus.BAD_REQUEST);
		} catch (Exception ex) {

			throw new PatientServerException(ex.getMessage(), ex);
		}

	}

	@GetMapping("/getnotifications/fordate/{dateTo}/{patientid}")
	public ResponseEntity<Response<List<Notificationalerts>>> getNotificationsToUserForDate(
			@PathVariable("dateTo") @DateTimeFormat(pattern = "yyyy-MM-dd") Date dateTo,@PathVariable ("patientid") Long patientid) {
		try {
			
			
			List<Notificationalerts> notificationalerts = notificationalertsService.getNotificationsToUserForDate(patientid, dateTo);
			return new ResponseEntity<>(new Response<>(notificationalerts, StatusEnum.SUCCESS, null), HttpStatus.OK);
		} catch (IllegalArgumentException e) {
			return new ResponseEntity<>(new Response<>(null, StatusEnum.FAILURE, e.getMessage()),
					HttpStatus.BAD_REQUEST);
		} catch (Exception ex) {

			throw new PatientServerException(ex.getMessage(), ex);
		}

	}

	@DeleteMapping("/delete/{notificationId}")
	public ResponseEntity<Response<String>> deleteNotificationalerts(@PathVariable Long notificationId) {
		try {
			notificationalertsService.deleteNotificationalerts(notificationId);
			return new ResponseEntity<>(new Response<>(null, StatusEnum.SUCCESS, null), HttpStatus.OK);
		} catch (IllegalArgumentException e) {
			return new ResponseEntity<>(new Response<>(null, StatusEnum.FAILURE, e.getMessage()),
					HttpStatus.BAD_REQUEST);
		} catch (Exception ex) {
			throw new PatientServerException(ex.getMessage(), ex);
		}

	}
	@PostMapping("/saveNotification")
	public ResponseEntity<Response<String>> saveNotification(@RequestBody PatientShippingUpdateDTO notificationalerts) {
		try {
			notificationalertsService.saveNotification(notificationalerts);
			return new ResponseEntity<>(new Response<>(null, StatusEnum.SUCCESS, null), HttpStatus.OK);

		} catch (IllegalArgumentException e) {
			return new ResponseEntity<>(new Response<>(null, StatusEnum.FAILURE, e.getMessage()),
					HttpStatus.BAD_REQUEST);
		} catch (Exception ex) {

			throw new PatientServerException(ex.getMessage(), ex);
		}

	}

}
