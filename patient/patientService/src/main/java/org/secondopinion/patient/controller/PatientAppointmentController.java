package org.secondopinion.patient.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.secondopinion.patient.UserIdHelper;
import org.secondopinion.patient.dto.Appointment;
import org.secondopinion.patient.dto.AppointmentBookingDTO;
import org.secondopinion.patient.dto.AppointmentDTO;
import org.secondopinion.patient.dto.AppointmentDTOForDiagnosticcenter;
import org.secondopinion.patient.dto.AppointmentSearchRequest;
import org.secondopinion.patient.dto.ForUser;
import org.secondopinion.patient.dto.RELATIONSHIP_TYPE;
import org.secondopinion.patient.dto.Relationship;
import org.secondopinion.patient.dto.UserDTO;
import org.secondopinion.patient.dto.ViewAppointments;
import org.secondopinion.patient.dto.ViewAppointments.ViewAppointmentEnum;
import org.secondopinion.patient.exception.PatientServerException;
import org.secondopinion.patient.service.IPatientAppointmentService;
import org.secondopinion.request.Response;
import org.secondopinion.request.Response.StatusEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.twilio.rest.api.v2010.account.Call;
import com.twilio.rest.video.v1.room.RoomRecording;

@RestController
@RequestMapping("/appointment")
public class PatientAppointmentController {

	@Autowired
	private IPatientAppointmentService appointmentService;
	@Autowired
	private UserIdHelper usHelper;

	@Autowired
	private HttpServletRequest httpServletRequest;

	@GetMapping("/id/{appointmentId}")
	public ResponseEntity<Response<Appointment>> getAppointmentById(@PathVariable Long appointmentId) {
		try {
			return new ResponseEntity<>(
					new Response<>(appointmentService.getAppointmentById(appointmentId), StatusEnum.SUCCESS, "appointment retrieved successfully."),
					HttpStatus.OK);

		} catch (IllegalArgumentException e) {
			return new ResponseEntity<>(new Response<>(null, StatusEnum.FAILURE, e.getMessage()),
					HttpStatus.BAD_REQUEST);
		} catch (Exception e) {
			throw new PatientServerException(e.getMessage(), e);
		}

	}

	@GetMapping("/gettotelpateint/{refrenceEntityId}")
	public ResponseEntity<Response<List<UserDTO>>> getTotelpateint(@PathVariable Long refrenceEntityId) {
		try {
			List<UserDTO> user = appointmentService.getTotelPateint(refrenceEntityId);
			return new ResponseEntity<>(new Response<>(user, StatusEnum.SUCCESS, "patient retrieved successfully."), HttpStatus.OK);
		} catch (IllegalArgumentException e) {
			return new ResponseEntity<>(new Response<>(null, StatusEnum.FAILURE, e.getMessage()),
					HttpStatus.BAD_REQUEST);
		} catch (Exception e) {
			throw new PatientServerException(e.getMessage(), e);
		}

	}

	@PostMapping("/buildInvoicePatientForAppointmentBooking")
	public ResponseEntity<Response<String>> buildInvoicePatientForAppointmentBooking(
			@RequestBody AppointmentBookingDTO appointment) {

		try {

			ForUser forUser = new ForUser();
			forUser.setUserId(forUser.getUserId());
			forUser.setForUserId(new Long(httpServletRequest.getHeader("forUserId")));
			appointment.setForUser(forUser);
			String dbappointment = appointmentService.buildInvoicePatientForAppointmentBooking(appointment);
			return new ResponseEntity<>(new Response<>(dbappointment, StatusEnum.SUCCESS, "Invoice Done..  "),
					HttpStatus.OK);
		} catch (IllegalArgumentException e) {
			return new ResponseEntity<>(new Response<>(null, StatusEnum.FAILURE, "Appointment Booking Fail"),
					HttpStatus.BAD_REQUEST);
		} catch (Exception e) {
			throw new PatientServerException(e.getMessage(), e);
		}

	}

	@PostMapping("/booking/newrequest")
	public ResponseEntity<Response<Appointment>> requestTobookAppointmentWithDoctor(
			@RequestBody AppointmentDTO appointment) {

		try {
			String moduleName = httpServletRequest.getHeader("moduleName");
			RELATIONSHIP_TYPE relationship_TYPE = RELATIONSHIP_TYPE.valueOf(moduleName);

			ForUser forUser = new ForUser();
			forUser.setUserId(forUser.getUserId());
			forUser.setForUserId(new Long(httpServletRequest.getHeader("forUserId")));
			appointment.setForUser(forUser);
			Appointment dbappointment = appointmentService.requestTobookAppointmentWithDoctor(appointment,
					relationship_TYPE);
			return new ResponseEntity<>(new Response<>(dbappointment, StatusEnum.SUCCESS, "Appointment Booked "),
					HttpStatus.OK);
		} catch (IllegalArgumentException e) {
			return new ResponseEntity<>(new Response<>(null, StatusEnum.FAILURE, "Appointment Booking Fail"),
					HttpStatus.BAD_REQUEST);
		} catch (Exception e) {
			throw new PatientServerException(e.getMessage(), e);
		}

	}
	
	
	@PostMapping("/requesttobookappointmentwithdiagnosticcenter")
	public ResponseEntity<Response<Appointment>> requestTobookAppointmentWithDiagnosticcenter(
			@RequestBody AppointmentDTOForDiagnosticcenter appointment) {

		try {
			String moduleName = httpServletRequest.getHeader("moduleName");
			RELATIONSHIP_TYPE relationship_TYPE = RELATIONSHIP_TYPE.valueOf(moduleName);

			ForUser forUser = new ForUser();
			forUser.setUserId(forUser.getUserId());
			forUser.setForUserId(new Long(httpServletRequest.getHeader("forUserId")));
			appointment.setForUser(forUser);
			Appointment dbappointment = appointmentService.requestTobookAppointmentWithDiagnosticecnter(appointment,
					relationship_TYPE);
			return new ResponseEntity<>(new Response<>(dbappointment, StatusEnum.SUCCESS, "Appointment Booked "),
					HttpStatus.OK);
		} catch (IllegalArgumentException e) {
			return new ResponseEntity<>(new Response<>(null, StatusEnum.FAILURE, "Appointment Booking Fail"),
					HttpStatus.BAD_REQUEST);
		} catch (Exception e) {
			throw new PatientServerException(e.getMessage(), e);
		}

	}


	// appointment/update/status/byAppointmentFor
	@PostMapping("/update/status/byAppointmentFor/{entityAppointmentId}")
	public ResponseEntity<Response<Appointment>> updateAppointmentUponDoctorAppointmentStatusTheRequest(
			@RequestBody Appointment appointment, @PathVariable Long entityAppointmentId,
			@RequestParam String appointmentFor) {

		try {
			appointmentService.updateAppointmentUponDoctorAppointmentStatusTheRequest(entityAppointmentId, appointment,
					appointmentFor);
			return new ResponseEntity<>(new Response<>(null, StatusEnum.SUCCESS, null), HttpStatus.OK);
		} catch (IllegalArgumentException e) {
			return new ResponseEntity<>(new Response<>(null, StatusEnum.FAILURE, e.getMessage()),
					HttpStatus.BAD_REQUEST);
		} catch (Exception e) {
			throw new PatientServerException(e.getMessage(), e);
		}

	}

	@PostMapping("/rescheduleAppointmentForDiagnosticCenterUser/status/byAppointmentFor/{entityAppointmentId}")
	public ResponseEntity<Response<Appointment>> rescheduleAppointmentForDiagnosticCenterUser(
			@RequestBody Appointment appointment, @PathVariable Long entityAppointmentId,
			@RequestParam String appointmentFor) {

		try {
			appointmentService.rescheduleAppointmentForDiagnosticCenterUser(entityAppointmentId, appointment,
					appointmentFor);
			return new ResponseEntity<>(new Response<>(null, StatusEnum.SUCCESS, null), HttpStatus.OK);
		} catch (IllegalArgumentException e) {
			return new ResponseEntity<>(new Response<>(null, StatusEnum.FAILURE, e.getMessage()),
					HttpStatus.BAD_REQUEST);
		} catch (Exception e) {
			throw new PatientServerException(e.getMessage(), e);
		}

	}

	@PostMapping("/cancelAppointmentForDiagnosticCenterUser/status/byAppointmentFor/{entityAppointmentId}")
	public ResponseEntity<Response<Appointment>> cancelAppointmentForDiagnosticCenterUser(
			@RequestBody Appointment appointment, @PathVariable Long entityAppointmentId,
			@RequestParam String appointmentFor, @RequestParam Long forUserId) {

		try {
			appointmentService.cancelAppointmentForDiagnosticCenterUser(entityAppointmentId, appointment,
					appointmentFor);
			return new ResponseEntity<>(new Response<>(null, StatusEnum.SUCCESS, null), HttpStatus.OK);
		} catch (IllegalArgumentException e) {
			return new ResponseEntity<>(new Response<>(null, StatusEnum.FAILURE, e.getMessage()),
					HttpStatus.BAD_REQUEST);
		} catch (Exception e) {
			throw new PatientServerException(e.getMessage(), e);
		}

	}

	@PostMapping("/updateAppointmentForDiagnosticCenterUser/status/byAppointmentFor/{entityAppointmentId}")
	public ResponseEntity<Response<Appointment>> updateAppointmentbyDiagnosticCenterUser(
			@RequestBody Appointment appointment, @PathVariable Long entityAppointmentId,
			@RequestParam String appointmentFor) {

		try {

			appointmentService.updateAppointmentbyDiagnosticCenterUser(entityAppointmentId, appointment,
					appointmentFor);

			return new ResponseEntity<>(new Response<>(null, StatusEnum.SUCCESS, null), HttpStatus.OK);
		} catch (IllegalArgumentException e) {
			return new ResponseEntity<>(new Response<>(null, StatusEnum.FAILURE, e.getMessage()),
					HttpStatus.BAD_REQUEST);
		} catch (Exception e) {
			throw new PatientServerException(e.getMessage(), e);
		}

	}

	@GetMapping("/{refrenceEntityId}/{appoitmentfor}")
	public ResponseEntity<Response<List<Appointment>>> getAppointmentFor(
			@PathVariable("refrenceEntityId") Long refrenceEntityId,
			@PathVariable("appoitmentfor") String appoitmentfor, @PathVariable("pageNum") Integer pageNum) {

		try {
			Response<List<Appointment>> appointments = appointmentService.getAppointmentFor(refrenceEntityId,
					appoitmentfor, pageNum);
			return new ResponseEntity<>(appointments, HttpStatus.OK);
		} catch (IllegalArgumentException e) {
			return new ResponseEntity<>(new Response<>(null, StatusEnum.FAILURE, e.getMessage()),
					HttpStatus.BAD_REQUEST);
		} catch (Exception e) {
			throw new PatientServerException(e.getMessage(), e);
		}

	}

	@PostMapping(path = "/search/criteria")
	public ResponseEntity<Response<List<Appointment>>> getAllAppointmentBySearchCritieria(
			@RequestBody AppointmentSearchRequest appointmentSearchRequest) {

		try {
			String moduleName = httpServletRequest.getHeader("moduleName");
			RELATIONSHIP_TYPE relationship_TYPE = RELATIONSHIP_TYPE.valueOf(moduleName);
			Response<List<Appointment>> response = appointmentService.getAllAppointmentBySearchCritieria(
					appointmentSearchRequest, appointmentSearchRequest.getForUser(), relationship_TYPE);

			return new ResponseEntity<>(response, HttpStatus.OK);

		} catch (Exception ex) {
			throw new PatientServerException(ex.getMessage(), ex);
		}

	}

	@PostMapping("/joinroom/{appointmentId}")
	public ResponseEntity<Response<Appointment>> joinroom(@PathVariable Long appointmentId) {
		try {
			return new ResponseEntity<>(
					new Response<>(appointmentService.joinroom(appointmentId), StatusEnum.SUCCESS, null),
					HttpStatus.OK);
		} catch (IllegalArgumentException e) {
			return new ResponseEntity<>(new Response<>(null, StatusEnum.FAILURE, e.getMessage()),
					HttpStatus.BAD_REQUEST);
		} catch (Exception e) {
			throw new PatientServerException(e.getMessage(), e);
		}

	}

	@PostMapping("/reject/self/{appointmentId}")
	public ResponseEntity<Response<String>> patinetRejectedTheRequest(@PathVariable Long appointmentId) {
		try {
			appointmentService.patientRejectedTheRequest(appointmentId);
			return new ResponseEntity<>(new Response<>(null, StatusEnum.SUCCESS, "appointment cancelled by patient."),
					HttpStatus.OK);
		} catch (IllegalArgumentException e) {
			return new ResponseEntity<>(new Response<>(null, StatusEnum.FAILURE, e.getMessage()),
					HttpStatus.BAD_REQUEST);
		} catch (Exception e) {
			throw new PatientServerException(e.getMessage(), e);
		}

	}

	@PostMapping("/reschedule/self/{appointmentId}")
	public ResponseEntity<Response<String>> patinetRescheduleTheRequest(@PathVariable Long appointmentId,
			@PathVariable Long schudlehourseId) {
		try {
			appointmentService.patinetRescheduleTheRequest(appointmentId, schudlehourseId);
			return new ResponseEntity<>(
					new Response<>(null, StatusEnum.SUCCESS, "Appoinntment reschedule status sent to doctor"),
					HttpStatus.OK);
		} catch (IllegalArgumentException e) {
			return new ResponseEntity<>(new Response<>(null, StatusEnum.FAILURE, e.getMessage()),
					HttpStatus.BAD_REQUEST);
		} catch (Exception e) {
			throw new PatientServerException(e.getMessage(), e);
		}

	}

	@PostMapping("/voicechat/{patientId}")
	public ResponseEntity<Response<Call>> voiceChat(@PathVariable Long patientId) {
		try {
			return new ResponseEntity<>(
					new Response<>(appointmentService.voiceChat(patientId), StatusEnum.SUCCESS, null), HttpStatus.OK);
		} catch (IllegalArgumentException e) {
			return new ResponseEntity<>(new Response<>(null, StatusEnum.FAILURE, e.getMessage()),
					HttpStatus.BAD_REQUEST);
		} catch (Exception e) {
			throw new PatientServerException(e.getMessage(), e);
		}

	}

	@PostMapping("/recordings/{patientId}/{roomSID}")
	public ResponseEntity<Response<List<RoomRecording>>> fetchRecording(@PathVariable Long patientId,
			@PathVariable String roomSID) {
		try {
			return new ResponseEntity<>(
					new Response<>(appointmentService.fetchRecording(patientId, roomSID), StatusEnum.SUCCESS, null),
					HttpStatus.OK);
		} catch (IllegalArgumentException e) {
			return new ResponseEntity<>(new Response<>(null, StatusEnum.FAILURE, e.getMessage()),
					HttpStatus.BAD_REQUEST);
		} catch (Exception e) {
			throw new PatientServerException(e.getMessage(), e);
		}

	}

	@PostMapping("/view/patientself")
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
			throw new PatientServerException(e.getMessage(), e);
		}

	}

}
