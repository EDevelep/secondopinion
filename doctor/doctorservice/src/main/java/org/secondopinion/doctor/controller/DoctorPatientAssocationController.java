package org.secondopinion.doctor.controller;

import java.util.List;

import org.secondopinion.doctor.dto.PatientRequest;
import org.secondopinion.doctor.exception.DoctorServerException;
import org.secondopinion.doctor.service.IDoctorPatientRequestService;
import org.secondopinion.request.Response;
import org.secondopinion.request.Response.StatusEnum;
import org.secondopinion.utils.JSONHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;

import io.swagger.annotations.Api;

@RestController
@RequestMapping("/patient")
@Api("/patient")
public class DoctorPatientAssocationController {
	@Autowired
	private IDoctorPatientRequestService patientRequestService;

	@PostMapping("/save/request")
	public ResponseEntity<Response<String>> savePatientRequest(@RequestBody PatientRequest patientRequest) {
		Gson gson = JSONHelper.getGsonWithDate();
		try {

			return new ResponseEntity<>(new Response<>(gson.toJson(patientRequestService.savePatientRequest(patientRequest)), 
					StatusEnum.SUCCESS, null), HttpStatus.OK);
		} catch (IllegalArgumentException e) {
			return new ResponseEntity<>(new Response<>(null, StatusEnum.FAILURE, e.getMessage()), HttpStatus.BAD_REQUEST);
		} catch (Exception e) {
			throw new DoctorServerException(e.getMessage(), e);
		}
		
	}

	@GetMapping("/all/request/{doctorId}")
	public ResponseEntity<Response<List<PatientRequest>>> getAllPatientRequestsForDoctor(@PathVariable Long doctorId) {
		try {

			return new ResponseEntity<>(new Response<>(patientRequestService.getAllPatientRequestsForDoctor(doctorId), 
					StatusEnum.SUCCESS, null), HttpStatus.OK);
		} catch (IllegalArgumentException e) {
			return new ResponseEntity<>(new Response<>(null, StatusEnum.FAILURE, e.getMessage()), HttpStatus.BAD_REQUEST);
		} catch (Exception e) {
			throw new DoctorServerException(e.getMessage(), e);
		}
		
	}

	@GetMapping("/all/newrequest/{doctorId}")
	public ResponseEntity<Response<List<PatientRequest>>> getAllNewRequestedPAtients(@PathVariable Long doctorId) {
		try {

			return new ResponseEntity<>(new Response<>(patientRequestService.getAllNewRequestedPAtients(doctorId),
					StatusEnum.SUCCESS, null), HttpStatus.OK);
		} catch (IllegalArgumentException e) {
			return new ResponseEntity<>(new Response<>(null, StatusEnum.FAILURE, e.getMessage()), HttpStatus.BAD_REQUEST);
		} catch (Exception e) {
			throw new DoctorServerException(e.getMessage(), e);
		}
		
	}

	@GetMapping("/all/requestaccepted/{doctorId}")
	public ResponseEntity<Response<List<PatientRequest>>> getAllPatientRequestsAcepted(@PathVariable Long doctorId) {
		try {

			return new ResponseEntity<>(new Response<>(patientRequestService.getAllPatientRequestsAcepted(doctorId),
					StatusEnum.SUCCESS, null), HttpStatus.OK);
		} catch (IllegalArgumentException e) {
			return new ResponseEntity<>(new Response<>(null, StatusEnum.FAILURE, e.getMessage()), HttpStatus.BAD_REQUEST);
		} catch (Exception e) {
			throw new DoctorServerException(e.getMessage(), e);
		}
		
	}

	@GetMapping("/all/notaccepted/{doctorId}")
	public ResponseEntity<Response<List<PatientRequest>>> getAllPatientRequestsNotAccepted(
			@PathVariable Long doctorId) {
		try {

			return new ResponseEntity<>(new Response<>(patientRequestService.getAllPatientRequestsNotAccepted(doctorId), StatusEnum.SUCCESS, null), HttpStatus.OK);
		} catch (IllegalArgumentException e) {
			return new ResponseEntity<>(new Response<>(null, StatusEnum.FAILURE, e.getMessage()), HttpStatus.BAD_REQUEST);
		} catch (Exception e) {
			throw new DoctorServerException(e.getMessage(), e);
		}
		
	}

	// update request from doctor if he accepted
	// api to service to dao.
	// dao to service to cal the patient request api of patient project.
	@PostMapping("/request/doctor/update")
	public ResponseEntity<Response<String>> updatePatientRequests(@RequestBody PatientRequest patientRequest) {
		try {
			patientRequestService.updatePatientRequests(patientRequest);
			return new ResponseEntity<>(new Response<>(null, StatusEnum.SUCCESS, null), HttpStatus.OK);
		} catch (IllegalArgumentException e) {
			return new ResponseEntity<>(new Response<>(null, StatusEnum.FAILURE, e.getMessage()), HttpStatus.BAD_REQUEST);
		} catch (Exception e) {
			throw new DoctorServerException(e.getMessage(), e);
		}
		
	}

	// update request from doctor if he accepted
	// api to service to dao.
	// dao to service to cal the patient request api of patient project.
	@PostMapping("/request/accept/{patientrequestid}")
	public ResponseEntity<Response<String>> updatePatientRequestsUponDoctorAccepts(
			@PathVariable("patientrequestid") Long patientRequestId) {
		try {
			patientRequestService.updatePatientRequestsUponDoctorAccepts(patientRequestId);
			return new ResponseEntity<>(new Response<>(null, StatusEnum.SUCCESS, null), HttpStatus.OK);
		} catch (IllegalArgumentException e) {
			return new ResponseEntity<>(new Response<>(null, StatusEnum.FAILURE, e.getMessage()), HttpStatus.BAD_REQUEST);
		} catch (Exception e) {
			throw new DoctorServerException(e.getMessage(), e);
		}
		
	}

	// update request from doctor if he accepted
	// api to service to dao.
	// dao to service to cal the patient request api of patient project.
	@PostMapping("/request/reject/{patientrequestid}")
	public ResponseEntity<Response<String>> updatePatientRequestsUponDoctorRejects(
			@PathVariable("patientrequestid") Long patientRequestId) {
		try {
			patientRequestService.updatePatientRequestsUponDoctorRejects(patientRequestId);
			return new ResponseEntity<>(new Response<>(null, StatusEnum.SUCCESS, null), HttpStatus.OK);
		} catch (IllegalArgumentException e) {
			return new ResponseEntity<>(new Response<>(null, StatusEnum.FAILURE, e.getMessage()), HttpStatus.BAD_REQUEST);
		} catch (Exception e) {
			throw new DoctorServerException(e.getMessage(), e);
		}
		
	}

}
