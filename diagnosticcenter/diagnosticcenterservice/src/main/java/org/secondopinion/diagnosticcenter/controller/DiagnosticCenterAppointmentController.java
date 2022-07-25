package org.secondopinion.diagnosticcenter.controller;

import java.util.List;
import java.util.Map;

import org.secondopinion.diagnosticcenter.dto.Appointment;
import org.secondopinion.diagnosticcenter.dto.AppointmentSearchRequest;
import org.secondopinion.diagnosticcenter.dto.UpdateDiagnosticCenterAddressAppointmentStatus;
import org.secondopinion.diagnosticcenter.dto.ViewAppointments;
import org.secondopinion.diagnosticcenter.dto.ViewAppointments.ViewAppointmentEnum;
import org.secondopinion.diagnosticcenter.exception.DiagnosticCenterServerException;
import org.secondopinion.diagnosticcenter.request.dto.AppointmentRequestDTO;
import org.secondopinion.diagnosticcenter.request.dto.PatientConfirmRequestDTO;
import org.secondopinion.diagnosticcenter.service.DiagnosticCenterAppointmentService;
import org.secondopinion.diagnosticcenter.service.IDiagnosticCenterUserService;
import org.secondopinion.request.Response;
import org.secondopinion.request.Response.StatusEnum;
import org.secondopinion.utils.JSONHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.google.gson.Gson;

@RestController
@RequestMapping("/appointment")
public class DiagnosticCenterAppointmentController {

	@Autowired
	private DiagnosticCenterAppointmentService diagnosticCenterAppointmentService;

	@Autowired
	private IDiagnosticCenterUserService iDiagnosticCenterUserService;

	@PostMapping("/createappointmentfordiagnosticcenteruser")
	public ResponseEntity<Response<String>> createAppointmentForDiagnosticCenterUser(
			@RequestBody AppointmentRequestDTO appointmentRequest) {
		try {

			iDiagnosticCenterUserService.createAppointment(appointmentRequest);
			return new ResponseEntity<>(
					new Response<>(null, StatusEnum.SUCCESS, "Diagnosticcenteruser Appointment Created"),
					HttpStatus.OK);
		} catch (IllegalArgumentException e) {
			return new ResponseEntity<>(new Response<>(null, StatusEnum.FAILURE, e.getMessage()),
					HttpStatus.BAD_REQUEST);
		} catch (Exception e) {
			throw new DiagnosticCenterServerException(e.getMessage(), e);
		}

	}

	@PostMapping("/cancelappointmentfordiagnosticcenterUser")
	public ResponseEntity<Response<String>> cancelAppointmentForDiagnosticCenterUser(
			@RequestBody AppointmentRequestDTO appointmentRequest) {
		try {

			iDiagnosticCenterUserService.cancelAppointment(appointmentRequest);
			return new ResponseEntity<>(
					new Response<>(null, StatusEnum.SUCCESS, "Diagnosticcenteruser Appointment Cancel"), HttpStatus.OK);
		} catch (IllegalArgumentException e) {
			return new ResponseEntity<>(new Response<>(null, StatusEnum.FAILURE, e.getMessage()),
					HttpStatus.BAD_REQUEST);
		} catch (Exception e) {
			throw new DiagnosticCenterServerException(e.getMessage(), e);
		}

	}

	@PostMapping("/rescheduleAppointmentForDiagnosticCenterUser")
	public ResponseEntity<Response<String>> rescheduleAppointmentForDiagnosticCenterUser(
			@RequestBody AppointmentRequestDTO appointmentRequest) {
		try {

			iDiagnosticCenterUserService.rescheduleAppointment(appointmentRequest);
			return new ResponseEntity<>(
					new Response<>(null, StatusEnum.SUCCESS, "Diagnosticcenteruser Appointment Confirm"),
					HttpStatus.OK);
		} catch (IllegalArgumentException e) {
			return new ResponseEntity<>(new Response<>(null, StatusEnum.FAILURE, e.getMessage()),
					HttpStatus.BAD_REQUEST);
		} catch (Exception e) {
			throw new DiagnosticCenterServerException(e.getMessage(), e);
		}

	}

	@PostMapping("/confirmAppointmentForDiagnosticCenterUser")
	public ResponseEntity<Response<String>> confirmAppointmentForDiagnosticCenterUser(
			@RequestBody PatientConfirmRequestDTO appointmentRequest) {
		try {

			iDiagnosticCenterUserService.confirmAppointment(appointmentRequest);

		} catch (IllegalArgumentException e) {
			return new ResponseEntity<>(new Response<>(null, StatusEnum.FAILURE, e.getMessage()),
					HttpStatus.BAD_REQUEST);
		} catch (Exception e) {
			throw new DiagnosticCenterServerException(e.getMessage(), e);
		}
		return null;

	}

	@PostMapping("/save/newrequest")
	public ResponseEntity<Response<String>> saveAppointment(@RequestBody Appointment appointment) {
		try {
			Gson gson = JSONHelper.getGsonWithDate();
			return new ResponseEntity<>(new Response<>(
					gson.toJson(diagnosticCenterAppointmentService.bookAppointemntWithDiagnosticCenter(appointment)),
					StatusEnum.SUCCESS, null), HttpStatus.OK);
		} catch (IllegalArgumentException e) {
			return new ResponseEntity<>(new Response<>(null, StatusEnum.FAILURE, e.getMessage()),
					HttpStatus.BAD_REQUEST);
		} catch (Exception e) {
			throw new DiagnosticCenterServerException(e.getMessage(), e);
		}

	}

	@PostMapping("/view/self")
	public ResponseEntity<Response<Map<ViewAppointmentEnum, Response<List<Appointment>>>>> myAppointments(
			@RequestBody ViewAppointments viewAppointments) {

		try {
			Map<ViewAppointmentEnum, Response<List<Appointment>>> appointments = diagnosticCenterAppointmentService
					.myAppointments(viewAppointments);
			return new ResponseEntity<>(new Response<>(appointments, StatusEnum.SUCCESS, null), HttpStatus.OK);
		} catch (IllegalArgumentException e) {
			return new ResponseEntity<>(new Response<>(null, StatusEnum.FAILURE, e.getMessage()),
					HttpStatus.BAD_REQUEST);
		} catch (Exception e) {
			throw new DiagnosticCenterServerException(e.getMessage(), e);
		}

	}

	@PostMapping("/update/status/frompatient/{entityAppointmentId}/{status}")
	public ResponseEntity<Response<Appointment>> updateAppointmentFromPatientAppointmentStatus(
			@PathVariable Long entityAppointmentId, @PathVariable String status) {

		try {
			diagnosticCenterAppointmentService.updateAppointmentStatusUponPatientRejectsTheRequest(entityAppointmentId,
					status);
			return new ResponseEntity<>(new Response<>(null, StatusEnum.SUCCESS, null), HttpStatus.OK);
		} catch (IllegalArgumentException e) {
			return new ResponseEntity<>(new Response<>(null, StatusEnum.FAILURE, e.getMessage()),
					HttpStatus.BAD_REQUEST);
		} catch (Exception e) {
			throw new DiagnosticCenterServerException(e.getMessage(), e);
		}

	}

	@PostMapping("/request/update/appointmentstatus")
	public ResponseEntity<Response<String>> updateAppointmentRequestsUponDCChoice(
			@RequestBody UpdateDiagnosticCenterAddressAppointmentStatus appointment) {
		try {
			diagnosticCenterAppointmentService.updateAppointmentRequestsUponDCChoice(appointment);
			return new ResponseEntity<>(new Response<>(null, StatusEnum.SUCCESS, null), HttpStatus.OK);
		} catch (IllegalArgumentException e) {
			return new ResponseEntity<>(new Response<>(null, StatusEnum.FAILURE, e.getMessage()),
					HttpStatus.BAD_REQUEST);
		} catch (Exception e) {
			throw new DiagnosticCenterServerException(e.getMessage(), e);
		}

	}

	@PostMapping("/search")
	public ResponseEntity<Response<List<Appointment>>> getAllAppointmentBySearchCritieria(
			@RequestBody AppointmentSearchRequest appointmentDTO) {

		try {
			Response<List<Appointment>> appointments = diagnosticCenterAppointmentService
					.getAllAppointmentBySearchCritieria(appointmentDTO);
			return new ResponseEntity<>(appointments, HttpStatus.OK);
		} catch (IllegalArgumentException e) {
			return new ResponseEntity<>(new Response<>(null, StatusEnum.FAILURE, e.getMessage()),
					HttpStatus.BAD_REQUEST);
		} catch (Exception e) {
			throw new DiagnosticCenterServerException(e.getMessage(), e);
		}

	}

	@DeleteMapping(path = "/delete/{appointmentId}")
	public ResponseEntity<Response<String>> deletetappointment(@PathVariable Long appointmentId) {

		try {
			diagnosticCenterAppointmentService.deletAppointment(appointmentId);
			return new ResponseEntity<>(new Response<>(null, StatusEnum.SUCCESS, null), HttpStatus.OK);
		} catch (IllegalArgumentException e) {
			return new ResponseEntity<>(new Response<>(null, StatusEnum.FAILURE, e.getMessage()),
					HttpStatus.BAD_REQUEST);
		} catch (Exception e) {
			throw new DiagnosticCenterServerException(e.getMessage(), e);
		}

	}

	@PostMapping(path = "/upload/reports/topatient", consumes = { MediaType.APPLICATION_FORM_URLENCODED_VALUE,
			MediaType.MULTIPART_FORM_DATA_VALUE, MediaType.APPLICATION_OCTET_STREAM_VALUE })
	public ResponseEntity<Response<String>> uploadReportsToThePatient(
			@RequestParam(value = "appointmentId", required = true) Long appointmentId,
			@RequestParam(value = "medicalreport", required = true) MultipartFile medicalreport) {

		try {
			diagnosticCenterAppointmentService.uploadReportsToThePatient(appointmentId, medicalreport);
			return new ResponseEntity<>(new Response<>("Medical Report uploaded successfully", StatusEnum.SUCCESS,
					"Medical Report uploaded successfully"), HttpStatus.OK);
		} catch (IllegalArgumentException e) {
			return new ResponseEntity<>(new Response<>(null, StatusEnum.FAILURE, e.getMessage()),
					HttpStatus.BAD_REQUEST);
		} catch (Exception e) {
			throw new DiagnosticCenterServerException(e.getMessage(), e);
		}

	}

}
