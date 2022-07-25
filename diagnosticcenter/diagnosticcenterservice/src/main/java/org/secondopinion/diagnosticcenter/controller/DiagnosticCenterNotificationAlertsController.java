package org.secondopinion.diagnosticcenter.controller;

import java.util.Date;
import java.util.List;

import org.secondopinion.diagnosticcenter.dto.Notificationalerts;
import org.secondopinion.diagnosticcenter.exception.DiagnosticCenterServerException;
import org.secondopinion.diagnosticcenter.service.IDiagnosticCenterNotificationalertsService;

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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/notificationalerts")
public class DiagnosticCenterNotificationAlertsController {
	@Autowired
	private IDiagnosticCenterNotificationalertsService notificationalertsService;

	
	@GetMapping("/get/all/{diagnoticCenterAddressId}")
	public ResponseEntity<Response<List<Notificationalerts>>> sendNotificationalertstofierbase(
			@PathVariable Long diagnoticCenterAddressId) {
		try {
			List<Notificationalerts> notificationalerts = notificationalertsService
					.getAllNotificationalerts(diagnoticCenterAddressId);

			return new ResponseEntity<>(new Response<>(notificationalerts, StatusEnum.SUCCESS, null), HttpStatus.OK);
		} catch (IllegalArgumentException e) {
			return new ResponseEntity<>(new Response<>(null, StatusEnum.FAILURE, e.getMessage()),
					HttpStatus.BAD_REQUEST);
		} catch (Exception e) {
			throw new DiagnosticCenterServerException(e.getMessage(), e);
		}

	}

	@GetMapping("/getnotificationalertsfordate/{diagnoticCenterAddressId}/{dateTo}")
	public ResponseEntity<Response<List<Notificationalerts>>> getNotificationalertsForWeek(
			@PathVariable Long diagnoticCenterAddressId,@PathVariable("dateTo") @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") Date dateTo) {
		try {
			List<Notificationalerts> notificationalerts = notificationalertsService
					.getNotificationalertsForDate(diagnoticCenterAddressId,dateTo);

			return new ResponseEntity<>(new Response<>(notificationalerts, StatusEnum.SUCCESS, null), HttpStatus.OK);
		} catch (IllegalArgumentException e) {
			return new ResponseEntity<>(new Response<>(null, StatusEnum.FAILURE, e.getMessage()),
					HttpStatus.BAD_REQUEST);
		} catch (Exception e) {
			throw new DiagnosticCenterServerException(e.getMessage(), e);
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
			throw new DiagnosticCenterServerException(e.getMessage(), e);
		}

	}

	@PostMapping("/frompatient")
	public ResponseEntity<Response<String>> sendNotificationalertstoTheDCForMedicalReportByPatient(
			@RequestBody NotificationAlert alertsUtilDTO) {
		try {
			notificationalertsService.sendNotificationalerts(alertsUtilDTO);

			return new ResponseEntity<>(new Response<>(null, StatusEnum.SUCCESS, null), HttpStatus.OK);
		} catch (IllegalArgumentException e) {
			return new ResponseEntity<>(new Response<>(null, StatusEnum.FAILURE, null), HttpStatus.BAD_REQUEST);
		} catch (Exception e) {
			throw new DiagnosticCenterServerException(e.getMessage(), e);
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

			throw new DiagnosticCenterServerException(ex.getMessage(), ex);
		}

	}

}
