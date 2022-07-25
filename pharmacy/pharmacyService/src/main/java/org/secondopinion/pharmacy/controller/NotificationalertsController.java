package org.secondopinion.pharmacy.controller;

import java.util.Date;
import java.util.List;

import org.secondopinion.pharmacy.dto.Notificationalerts;
import org.secondopinion.pharmacy.exception.PharmacyServerException;
import org.secondopinion.pharmacy.service.INotificationalertsService;
import org.secondopinion.request.Response;
import org.secondopinion.request.Response.StatusEnum;
import org.secondopinion.utils.NotificationAlert;
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
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/notificationalerts")
public class NotificationalertsController {
	@Autowired
	private INotificationalertsService notificationalertsService;

	@GetMapping("/get/all/{pharmacyaddressId}")
	public ResponseEntity<Response<List<Notificationalerts>>> sendNotificationalertstofierbase(
			@PathVariable Long pharmacyaddressId) {
		try {
			List<Notificationalerts> notificationalerts = notificationalertsService
					.getAllNotificationalerts(pharmacyaddressId);

			return new ResponseEntity<>(new Response<>(notificationalerts, StatusEnum.SUCCESS, null), HttpStatus.OK);
		} catch (IllegalArgumentException e) {
			return new ResponseEntity<>(new Response<>(null, StatusEnum.FAILURE, e.getMessage()),
					HttpStatus.BAD_REQUEST);
		} catch (Exception e) {
			throw new PharmacyServerException(e.getMessage(), e);
		}

	}

	@GetMapping("/getnotificationalertsfordate/{pharmacyaddressId}/{dateTo}")
	public ResponseEntity<Response<List<Notificationalerts>>> getNotificationalertsForDate(
			@PathVariable Long pharmacyaddressId,
			@PathVariable("dateTo") @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") Date dateTo) {
		try {
			List<Notificationalerts> notificationalerts = notificationalertsService
					.getNotificationalertsForDate(pharmacyaddressId, dateTo);

			return new ResponseEntity<>(new Response<>(notificationalerts, StatusEnum.SUCCESS, null), HttpStatus.OK);
		} catch (IllegalArgumentException e) {
			return new ResponseEntity<>(new Response<>(null, StatusEnum.FAILURE, e.getMessage()),
					HttpStatus.BAD_REQUEST);
		} catch (Exception e) {
			throw new PharmacyServerException(e.getMessage(), e);
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
		} catch (Exception e) {
			throw new PharmacyServerException(e.getMessage(), e);
		}

	}

	@PostMapping("/frompatient")
	public ResponseEntity<Response<String>> sendNotificationalertstoTheDoctorForMedicalReportByPatient(
			@RequestBody NotificationAlert alertsUtilDTO) {
		try {
			notificationalertsService.sendNotificationalertstoThePharmacyFromPatient(alertsUtilDTO);

			return new ResponseEntity<>(new Response<>(null, StatusEnum.SUCCESS, null), HttpStatus.OK);
		} catch (IllegalArgumentException e) {
			return new ResponseEntity<>(new Response<>(null, StatusEnum.FAILURE, null), HttpStatus.BAD_REQUEST);
		} catch (Exception e) {
			throw new PharmacyServerException(e.getMessage(), e);
		}

	}

	@PostMapping("/updateNotificationalerts/{notificationId}")
	public ResponseEntity<Response<String>> updateNotificationalerts(@PathVariable Long notificationId,
			@RequestBody Notificationalerts notificationalerts) {
		try {
			notificationalertsService.updateNotificationalerts(notificationId, notificationalerts);
			return new ResponseEntity<>(new Response<>("Notification Is Updated", StatusEnum.SUCCESS, null),
					HttpStatus.OK);
		} catch (IllegalArgumentException e) {
			return new ResponseEntity<>(new Response<>(null, StatusEnum.FAILURE, e.getMessage()),
					HttpStatus.BAD_REQUEST);
		} catch (Exception ex) {

			throw new PharmacyServerException(ex.getMessage(), ex);
		}

	}

}
