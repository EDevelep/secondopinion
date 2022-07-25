package org.secondopinion.patient.controller;

import javax.servlet.http.HttpServletRequest;

import org.secondopinion.patient.dto.ForUser;
import org.secondopinion.patient.dto.PrivalgeDTO;
import org.secondopinion.patient.dto.RELATIONSHIP_TYPE;
import org.secondopinion.patient.dto.Relationship;
import org.secondopinion.patient.dto.StatusType;
import org.secondopinion.patient.dto.User;
import org.secondopinion.patient.dto.UserRelations;
import org.secondopinion.patient.dto.UserRequest;
import org.secondopinion.patient.exception.PatientServerException;
import org.secondopinion.patient.service.IPatientAssociation;
import org.secondopinion.patient.service.IUserRegistrationService;
import org.secondopinion.patient.service.IUserService;
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

@RestController
@RequestMapping("/assocation")
public class PatientAssocationController {

	@Autowired
	private IUserService userService;

	@Autowired
	private IPatientAssociation iPatientAssociation;

	@Autowired
	private IUserRegistrationService iUserRegistrationService;

	@Autowired
	private HttpServletRequest httpServletRequest;

	@GetMapping("/resendEmailForAssocateUser")
	public ResponseEntity<Response<String>> resendEmailForAssocateUser(@RequestParam String emailId,
			@RequestParam String username) {

		try {
			iUserRegistrationService.resendEmailForAssocateUser(emailId, username);
			return new ResponseEntity<>(new Response<>(null, StatusEnum.SUCCESS, null), HttpStatus.OK);
		} catch (Exception ex) {
			throw new PatientServerException(ex.getMessage(), ex);
		}

	}

	@DeleteMapping("/deleteAssocateUser")
	public ResponseEntity<Response<String>> deleteAssocateUser(@RequestParam Long userId,

			@RequestParam Long associateUserId) {

		try {
			String moduleName = httpServletRequest.getHeader("moduleName");
			RELATIONSHIP_TYPE relationship_TYPE = RELATIONSHIP_TYPE.valueOf(moduleName);

			iPatientAssociation.deleteAssocateUser(userId, associateUserId, relationship_TYPE);
			return new ResponseEntity<>(new Response<>(null, StatusEnum.SUCCESS, null), HttpStatus.OK);
		} catch (Exception ex) {
			throw new PatientServerException(ex.getMessage(), ex);
		}

	}

	@PostMapping("/updateAssocateUser")
	public ResponseEntity<Response<User>> updateAssocateUser(@RequestBody ForUser forUser) {

		try {
			String moduleName = httpServletRequest.getHeader("moduleName");
			RELATIONSHIP_TYPE relationship_TYPE = RELATIONSHIP_TYPE.valueOf(moduleName);

			iPatientAssociation.updateAssocateUser(forUser, relationship_TYPE);
			return new ResponseEntity<>(new Response<>(null, StatusEnum.SUCCESS, null), HttpStatus.OK);
		} catch (Exception ex) {
			throw new PatientServerException(ex.getMessage(), ex);
		}

	}

	@PostMapping("/addManagedUser")
	public ResponseEntity<Response<User>> addManagedUser(@RequestBody UserRequest userRequest) {

		try {
			iPatientAssociation.addManagedUser(userRequest.getUser(), userRequest.getForUser());
			return new ResponseEntity<>(new Response<>(null, StatusEnum.SUCCESS, null), HttpStatus.OK);
		} catch (Exception ex) {
			throw new PatientServerException(ex.getMessage(), ex);
		}

	}

	@PostMapping("/allAssociateUser/{userId}")
	public ResponseEntity<Response<UserRelations>> allAssociateUser(@PathVariable("userId") Long userId) {

		try {
			UserRelations relationship = userService.allAssociateUser(userId);

			return new ResponseEntity<>(new Response<>(relationship, StatusEnum.SUCCESS, null), HttpStatus.OK);
		} catch (Exception ex) {
			throw new PatientServerException(ex.getMessage(), ex);
		}

	}

	@GetMapping(value = "/activateAssociateUserbyRelationUserId")
	public ResponseEntity<Response<String>> activateAssociateUserbyRelationUserId(
			@RequestParam("relationshipId") Long relationshipId, @RequestParam("status") StatusType status) {

		try {
			iUserRegistrationService.activateAssociateUserbyRelationUserId(relationshipId, status);
			return new ResponseEntity<>(
					new Response<>(null, StatusEnum.SUCCESS, "RelationShip Update saved successfully"), HttpStatus.OK);
		} catch (IllegalArgumentException e) {
			return new ResponseEntity<>(new Response<>(null, StatusEnum.FAILURE, e.getMessage()),
					HttpStatus.BAD_REQUEST);
		} catch (Exception e) {
			throw new PatientServerException(e.getMessage(), e);
		}
	}

	@GetMapping(value = "/associateUserPrivalge")
	public ResponseEntity<Response<PrivalgeDTO>> associateUserPrivalge(@RequestParam Long relationshipId) {

		try {
			PrivalgeDTO privalgeDTO=iUserRegistrationService.associateUserPrivalge(relationshipId);
			return new ResponseEntity<>(
					new Response<>(privalgeDTO, StatusEnum.SUCCESS, "RelationShip Get  successfully"), HttpStatus.OK);
		} catch (IllegalArgumentException e) {
			return new ResponseEntity<>(new Response<>(null, StatusEnum.FAILURE, e.getMessage()),
					HttpStatus.BAD_REQUEST);
		} catch (Exception e) {
			throw new PatientServerException(e.getMessage(), e);
		}
	}
	@PostMapping(value = "/associateUserPrivalgeUpdate")
	public ResponseEntity<Response<String>> associateUserPrivalgeUpdate(@RequestBody PrivalgeDTO privalgeDTO) {

		try {
			iUserRegistrationService.associateUserPrivalgeUpdate(privalgeDTO);
			return new ResponseEntity<>(
					new Response<>("Associate User Privalge Update   successfully", StatusEnum.SUCCESS, "Associate User Privalge Update   successfully"), HttpStatus.OK);
		} catch (IllegalArgumentException e) {
			return new ResponseEntity<>(new Response<>(null, StatusEnum.FAILURE, e.getMessage()),
					HttpStatus.BAD_REQUEST);
		} catch (Exception e) {
			throw new PatientServerException(e.getMessage(), e);
		}
	}
}
