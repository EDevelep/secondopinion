package org.secondopinion.doctor.controller;

import java.util.List;

import org.secondopinion.doctor.dto.Appointment;
import org.secondopinion.doctor.exception.DoctorServerException;
import org.secondopinion.doctor.service.IDoctorAppointmentService;
import org.secondopinion.request.Response;
import org.secondopinion.request.Response.StatusEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.twilio.rest.video.v1.Room;
import com.twilio.rest.video.v1.Room.RoomStatus;
import com.twilio.rest.video.v1.room.Participant;
import com.twilio.rest.video.v1.room.Participant.Status;

import io.swagger.annotations.Api;

@RestController
@RequestMapping("/appointment")
@Api("/appointment")
public class DoctorConsultaionChatController {

	@Autowired
	private IDoctorAppointmentService appointmentService;

	@PostMapping("/room/join/{appointmentId}")
	public ResponseEntity<Response<Appointment>> joinroom(@PathVariable Long appointmentId) {
		try {
			return new ResponseEntity<>(new Response<>(appointmentService.joinroom(appointmentId), 
					StatusEnum.SUCCESS, null), HttpStatus.OK);
		} catch (IllegalArgumentException e) {
			return new ResponseEntity<>(new Response<>(null, StatusEnum.FAILURE, e.getMessage()), HttpStatus.BAD_REQUEST);
		} catch (Exception e) {
			throw new DoctorServerException(e.getMessage(), e);
		}
		
	}

	@GetMapping("/room/retrieve/status/roomname/{roomname}")
	public ResponseEntity<Response<Room>> retrieiveRoomStatusByRoomName(@PathVariable String roomname) {
		try {
			return new ResponseEntity<>(new Response<>(appointmentService.retrieiveRoomStatusByRoomName(roomname), 
					StatusEnum.SUCCESS, null), HttpStatus.OK);
		} catch (IllegalArgumentException e) {
			return new ResponseEntity<>(new Response<>(null, StatusEnum.FAILURE, e.getMessage()), HttpStatus.BAD_REQUEST);
		} catch (Exception e) {
			throw new DoctorServerException(e.getMessage(), e);
		}
		
	}

	@GetMapping("/room/retrieve/status/roomsid/{roomSID}")
	public ResponseEntity<Response<Room>> retrieiveRoomStatusByRoomSID(@PathVariable String roomSID) {
		try {
			return new ResponseEntity<>(new Response<>(appointmentService.retrieiveRoomStatusByRoomSID(roomSID), 
					StatusEnum.SUCCESS, null), HttpStatus.OK);
		} catch (IllegalArgumentException e) {
			return new ResponseEntity<>(new Response<>(null, StatusEnum.FAILURE, e.getMessage()), HttpStatus.BAD_REQUEST);
		} catch (Exception e) {
			throw new DoctorServerException(e.getMessage(), e);
		}
		
	}

	@PutMapping("/room/update/status/{roomSID}/{roomstatus}")
	public ResponseEntity<Response<Room>> updateTheRoomStatus(@PathVariable String roomSID,
			@PathVariable RoomStatus roomstatus) {
		try {
			return new ResponseEntity<>(new Response<>(appointmentService.updateTheRoomStatus(roomSID, roomstatus), 
					StatusEnum.SUCCESS, null), HttpStatus.OK);
		} catch (IllegalArgumentException e) {
			return new ResponseEntity<>(new Response<>(null, StatusEnum.FAILURE, e.getMessage()), HttpStatus.BAD_REQUEST);
		} catch (Exception e) {
			throw new DoctorServerException(e.getMessage(), e);
		}
		
	}

	@PutMapping("/room/participants/retrieve/{roomSID}/")
	public ResponseEntity<Response<List<Participant>>> retrieveRoomParticipants(@PathVariable String roomSID) {
		try {
			return new ResponseEntity<>(new Response<>(appointmentService.retrieveRoomParticipants(roomSID), 
					StatusEnum.SUCCESS, null), HttpStatus.OK);
		} catch (IllegalArgumentException e) {
			return new ResponseEntity<>(new Response<>(null, StatusEnum.FAILURE, e.getMessage()), HttpStatus.BAD_REQUEST);
		} catch (Exception e) {
			throw new DoctorServerException(e.getMessage(), e);
		}
	}

	@GetMapping("/room/participant/retrieve/{roomname}/{participantname}")
	public ResponseEntity<Response<Participant>> retrieveAParticipant(@PathVariable String roomname,
			@PathVariable String participantname) {
		try {
			return new ResponseEntity<>(new Response<>(appointmentService.retrieveAParticipant(roomname, participantname),
					StatusEnum.SUCCESS, null), HttpStatus.OK);
		} catch (IllegalArgumentException e) {
			return new ResponseEntity<>(new Response<>(null, StatusEnum.FAILURE, e.getMessage()), HttpStatus.BAD_REQUEST);
		} catch (Exception e) {
			throw new DoctorServerException(e.getMessage(), e);
		}
		
	}

	@GetMapping("/room/participant/remove/{roomname}/{participantname}")
	public ResponseEntity<Response<Participant>> removeAParticipant(@PathVariable String roomname,
			@PathVariable String participantname) {
		try {
			return new ResponseEntity<>(new Response<>(appointmentService.removeAParticipant(roomname, participantname), StatusEnum.SUCCESS, null), HttpStatus.OK);
		} catch (IllegalArgumentException e) {
			return new ResponseEntity<>(new Response<>(null, StatusEnum.FAILURE, e.getMessage()), HttpStatus.BAD_REQUEST);
		} catch (Exception e) {
			throw new DoctorServerException(e.getMessage(), e);
		}
		
	}

	@GetMapping("/room/participant/retrieve/status/{roomname}/{participantstatus}")
	public ResponseEntity<Response<List<Participant>>> participantsBasedOnStatus(@PathVariable String roomname,
			@PathVariable Status participantstatus) {
		try {
			return new ResponseEntity<>(new Response<>(appointmentService.participantsBasedOnStatus(roomname, participantstatus), 
					StatusEnum.SUCCESS, null), HttpStatus.OK);
		} catch (IllegalArgumentException e) {
			return new ResponseEntity<>(new Response<>(null, StatusEnum.FAILURE, e.getMessage()), HttpStatus.BAD_REQUEST);
		} catch (Exception e) {
			throw new DoctorServerException(e.getMessage(), e);
		}
		
	}

}
