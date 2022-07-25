package org.secondopinion.patient.controller;

import org.secondopinion.patient.dto.MedicalHistoryDto;
import org.secondopinion.patient.exception.PatientServerException;
import org.secondopinion.patient.service.IMedicalHistory;
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

import io.swagger.annotations.Api;

@RestController
@RequestMapping("/medicalhistorycontroller")
@Api("/medicalhistorycontroller")
public class MedicalHistoryController {

	@Autowired
	private IMedicalHistory medicalHistory;

	@PostMapping("/save")
	public ResponseEntity<Response<String>> saveMedicalHistory(@RequestBody MedicalHistoryDto medicalHistoryDto) {
		try {
			medicalHistory.saveMedicalHistory(medicalHistoryDto);
			return new ResponseEntity<>(new Response<>(null, StatusEnum.SUCCESS, null), HttpStatus.OK);
		} catch (IllegalArgumentException e) {
			return new ResponseEntity<>(new Response<>(null, StatusEnum.FAILURE, e.getMessage()),
					HttpStatus.BAD_REQUEST);
		} catch (Exception e) {
			throw new PatientServerException(e.getMessage(), e);
		}

	}

	@GetMapping("/getAllMedicalhistoryDetail/{userId}")
	public ResponseEntity<Response<MedicalHistoryDto>> getAllMedicalhistoryDetail(@PathVariable("userId") Long userId) {
		try {
			return new ResponseEntity<>(
					new Response<>(medicalHistory.getAllMedicalhistoryDetail(userId), StatusEnum.SUCCESS, null),
					HttpStatus.OK);
		} catch (IllegalArgumentException e) {
			return new ResponseEntity<>(new Response<>(null, StatusEnum.FAILURE, e.getMessage()),
					HttpStatus.BAD_REQUEST);
		} catch (Exception e) {
			throw new PatientServerException(e.getMessage(), e);
		}

	}

	@DeleteMapping("/deleteFamilyHistory/{familyHistoryId}")
	public ResponseEntity<Response<String>> deleteFamilyHistory(@PathVariable("familyHistoryId") Long familyHistoryId) {
		try {

			medicalHistory.deleteFamilyHistory(familyHistoryId);
			return new ResponseEntity<>(new Response<>(null, StatusEnum.SUCCESS, "Deleted SuccessFully."), HttpStatus.OK);
		} catch (IllegalArgumentException e) {
			return new ResponseEntity<>(new Response<>(null, StatusEnum.FAILURE, e.getMessage()),
					HttpStatus.BAD_REQUEST);
		} catch (Exception e) {
			throw new PatientServerException(e.getMessage(), e);
		}

	}
	@DeleteMapping("/deleteSurgerydetails/{surgerydetailsId}")
	public ResponseEntity<Response<String>> deleteSurgerydetails(@PathVariable("surgerydetailsId") Long surgerydetailsId) {
		try {

			medicalHistory.deleteSurgerydetails(surgerydetailsId);
			return new ResponseEntity<>(new Response<>(null, StatusEnum.SUCCESS, "Deleted SuccessFully."), HttpStatus.OK);
		} catch (IllegalArgumentException e) {
			return new ResponseEntity<>(new Response<>(null, StatusEnum.FAILURE, e.getMessage()),
					HttpStatus.BAD_REQUEST);
		} catch (Exception e) {
			throw new PatientServerException(e.getMessage(), e);
		}

	}
}
