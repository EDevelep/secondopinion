package org.secondopinion.doctor.controller;

import java.util.Date;
import java.util.List;

import org.secondopinion.doctor.dto.Notificationalerts;
import org.secondopinion.doctor.exception.DoctorServerException;
import org.secondopinion.doctor.service.INotificationalertsService;
import org.secondopinion.request.Response;
import org.secondopinion.request.Response.StatusEnum;
import org.secondopinion.utils.NotificationAlert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Temporal;
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
public class NotificationalertsController {
	@Autowired
	private INotificationalertsService notificationalertsService;

	@GetMapping("/get/all/{doctorId}")
	public ResponseEntity<Response<List<Notificationalerts>>> sendNotificationalertstofierbase(
			@PathVariable Long doctorId) {
		try {
			List<Notificationalerts>  notificationalerts =notificationalertsService.getAllNotificationalerts(doctorId);
			
			return new ResponseEntity<>(new Response<>(notificationalerts, StatusEnum.SUCCESS, null), HttpStatus.OK);
		} catch (IllegalArgumentException e) {
			return new ResponseEntity<>(new Response<>(null, StatusEnum.FAILURE, e.getMessage()), HttpStatus.BAD_REQUEST);
		} catch (Exception e) {
			throw new DoctorServerException(e.getMessage(), e);
		}
		
	}
	

	@GetMapping("/getAllNotificationalertsByDate/fordate/{dateTo}/{doctorId}")
	public ResponseEntity<Response<List<Notificationalerts>>> getAllNotificationalertsByDate(@PathVariable("dateTo") @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") Date dateTo,@PathVariable Long doctorId) {
		try {
			List<Notificationalerts>  notificationalerts =notificationalertsService.getAllNotificationalertsByNextWeek(dateTo,doctorId);
			return new ResponseEntity<>(new Response<>(notificationalerts, StatusEnum.SUCCESS, null), HttpStatus.OK);
		} catch (IllegalArgumentException e) {
			return new ResponseEntity<>(new Response<>(null, StatusEnum.FAILURE, e.getMessage()), HttpStatus.BAD_REQUEST);
		} catch (Exception e) {
			throw new DoctorServerException(e.getMessage(), e);
		}
	}
	
	
	@DeleteMapping("/delete/{notificationId}")
	public ResponseEntity<Response<String>> deleteNotificationalerts(@PathVariable Long notificationId) {
		try {
			notificationalertsService.deleteNotificationalerts(notificationId);
			return new ResponseEntity<>(new Response<>(null, StatusEnum.SUCCESS, null), HttpStatus.OK);
		} catch (IllegalArgumentException e) {
			return new ResponseEntity<>(new Response<>(null, StatusEnum.FAILURE, e.getMessage()), HttpStatus.BAD_REQUEST);
		} catch (Exception e) {
			throw new DoctorServerException(e.getMessage(), e);
		}
		
	}
	
	@PostMapping("/frompatient")
	public ResponseEntity<Response<String>> sendNotificationalertstoTheDoctorForMedicalReportByPatient(
			@RequestBody NotificationAlert alertsUtilDTO) {
		try {
			notificationalertsService.sendNotificationToTheDoctorByAppointment(alertsUtilDTO);
			
			return new ResponseEntity<>(new Response<>(null, StatusEnum.SUCCESS, null), HttpStatus.OK);
		} catch (IllegalArgumentException e) {
			return new ResponseEntity<>(new Response<>(null, StatusEnum.FAILURE, null), HttpStatus.BAD_REQUEST);
		} catch (Exception e) {
			throw new DoctorServerException(e.getMessage(), e);
		}
		
	}
	
	@PostMapping("/updateNotificationalerts/{notificationId}")
	public ResponseEntity<Response<String>> updateNotificationalerts(@RequestParam Long forUserId,
			@PathVariable Long notificationId, @RequestBody Notificationalerts notificationalerts) {
		try {
			notificationalertsService.updateNotificationalerts(notificationId, notificationalerts);
			return new ResponseEntity<>(new Response<>("Notification Is Updated", StatusEnum.SUCCESS, null), HttpStatus.OK);
		} catch (IllegalArgumentException e) {
			return new ResponseEntity<>(new Response<>(null, StatusEnum.FAILURE, e.getMessage()),
					HttpStatus.BAD_REQUEST);
		} catch (Exception ex) {

			throw new DoctorServerException(ex.getMessage(), ex);
		}

	}

}
