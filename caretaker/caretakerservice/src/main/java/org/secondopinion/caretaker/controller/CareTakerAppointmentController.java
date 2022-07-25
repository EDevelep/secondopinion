
package org.secondopinion.caretaker.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;

import org.secondopinion.caretaker.dto.Appointment;
import org.secondopinion.caretaker.dto.AppointmentSearchRequest;
import org.secondopinion.caretaker.dto.UpdateDoctorAppointmentStatus;
import org.secondopinion.caretaker.dto.ViewAppointments;
import org.secondopinion.caretaker.dto.ViewAppointments.ViewAppointmentEnum;
import org.secondopinion.caretaker.exception.CareTakerServerException;
import org.secondopinion.caretaker.service.ICaretakerAppointmentService;
import org.secondopinion.request.Response;
import org.secondopinion.request.Response.StatusEnum;
import org.secondopinion.utils.JSONHelper;

import io.swagger.annotations.Api;

@RestController
@RequestMapping("/appointment")
@Api("/appointment")
public class CareTakerAppointmentController {

	@Autowired
	private ICaretakerAppointmentService appointmentService;

	@GetMapping("/id/{appointmentId}")
	public ResponseEntity<Response<Appointment>> appointmentById(@PathVariable("appointmentId") Long appointmentId) {

		try {
			Appointment appointment = appointmentService.appointmentDetails(appointmentId);
			return new ResponseEntity<>(new Response<>(appointment, StatusEnum.SUCCESS, null), HttpStatus.OK);
		} catch (IllegalArgumentException e) {
			return new ResponseEntity<>(new Response<>(null, StatusEnum.FAILURE, e.getMessage()),
					HttpStatus.BAD_REQUEST);
		} catch (Exception e) {
			throw new CareTakerServerException(e.getMessage(), e);
		}

	}

	@PostMapping("/view/caretaker")
	public ResponseEntity<Response<Map<ViewAppointmentEnum, Response<List<Appointment>>>>> myAppointments(
			@RequestBody ViewAppointments viewAppointments) {

		try {
			Map<ViewAppointmentEnum, Response<List<Appointment>>> appointments = appointmentService
					.myAppointments(viewAppointments);
			return new ResponseEntity<>(new Response<>(appointments, StatusEnum.SUCCESS, null), HttpStatus.OK);
		} catch (IllegalArgumentException e) {
			return new ResponseEntity<>(new Response<>(null, StatusEnum.FAILURE, e.getMessage()),
					HttpStatus.BAD_REQUEST);
		} catch (Exception e) {
			throw new CareTakerServerException(e.getMessage(), e);
		}

	}

	@PostMapping("/save/newrequest")
	public ResponseEntity<Response<String>> saveAppointment(@RequestBody Appointment appointment) {
		Gson gson = JSONHelper.getGsonWithDate();
		try {
			return new ResponseEntity<>(new Response<>(gson.toJson(appointmentService.saveAppointment(appointment)),
					StatusEnum.SUCCESS, null), HttpStatus.OK);
		} catch (IllegalArgumentException e) {
			return new ResponseEntity<>(new Response<>(null, StatusEnum.FAILURE, e.getMessage()),
					HttpStatus.BAD_REQUEST);
		} catch (Exception e) {
			throw new CareTakerServerException(e.getMessage(), e);
		}

	}

	@PutMapping("/update")
	public ResponseEntity<Response<String>> updateAppointment(@RequestBody Appointment appointment) {
		Gson gson = JSONHelper.getGsonWithDate();
		try {
			return new ResponseEntity<>(new Response<>(gson.toJson(appointmentService.updateAppointment(appointment)),
					StatusEnum.SUCCESS, null), HttpStatus.OK);
		} catch (IllegalArgumentException e) {
			return new ResponseEntity<>(new Response<>(null, StatusEnum.FAILURE, e.getMessage()),
					HttpStatus.BAD_REQUEST);
		} catch (Exception e) {
			throw new CareTakerServerException(e.getMessage(), e);
		}

	}

	@PostMapping("/update/status/frompatient/{entityAppointmentId}/{status}")
	public ResponseEntity<Response<Appointment>> updateAppointmentFromPatientAppointmentStatus(
			@PathVariable Long entityAppointmentId, @PathVariable String status) {

		try {
			appointmentService.updateAppointmentStatusUponPatientRejectsTheRequest(entityAppointmentId, status);
			return new ResponseEntity<>(new Response<>(null, StatusEnum.SUCCESS, null), HttpStatus.OK);
		} catch (IllegalArgumentException e) {
			return new ResponseEntity<>(new Response<>(null, StatusEnum.FAILURE, e.getMessage()),
					HttpStatus.BAD_REQUEST);
		} catch (Exception e) {
			throw new CareTakerServerException(e.getMessage(), e);
		}

	}

///appointment/request/update/appointmentstatus
	@PostMapping("/request/update/appointmentstatus")
	public ResponseEntity<Response<String>> updateAppointmentRequestsUponDoctorChoice(
			@RequestBody UpdateDoctorAppointmentStatus appointment) {
		try {
			appointmentService.updateAppointmentRequestsUponCaretakerChoice(appointment);
			return new ResponseEntity<>(new Response<>(null, StatusEnum.SUCCESS, null), HttpStatus.OK);
		} catch (IllegalArgumentException e) {
			return new ResponseEntity<>(new Response<>(null, StatusEnum.FAILURE, e.getMessage()),
					HttpStatus.BAD_REQUEST);
		} catch (Exception e) {
			throw new CareTakerServerException(e.getMessage(), e);
		}

	}

	@PostMapping("/search")
	public ResponseEntity<Response<List<Appointment>>> getAllAppointmentBySearchCritieria(
			@RequestBody AppointmentSearchRequest appointmentSearchRequest) {

		try {
			Response<List<Appointment>> appointments = appointmentService
					.getAllAppointmentBySearchCritieria(appointmentSearchRequest);
			return new ResponseEntity<>(appointments, HttpStatus.OK);
		} catch (IllegalArgumentException e) {
			return new ResponseEntity<>(new Response<>(null, StatusEnum.FAILURE, e.getMessage()),
					HttpStatus.BAD_REQUEST);
		} catch (Exception e) {
			throw new CareTakerServerException(e.getMessage(), e);
		}

	}

}
