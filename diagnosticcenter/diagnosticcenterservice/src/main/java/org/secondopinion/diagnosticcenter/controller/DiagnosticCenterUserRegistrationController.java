package org.secondopinion.diagnosticcenter.controller;

import java.util.Collection;
import java.util.List;

import org.secondopinion.configurations.UtilComponent;
import org.secondopinion.diagnosticcenter.dto.Diagnosticcenter;
import org.secondopinion.diagnosticcenter.dto.DiagnosticcenterSearchRequest;
import org.secondopinion.diagnosticcenter.dto.Diagnosticcenteruser;
import org.secondopinion.diagnosticcenter.dto.DiagnosticcenteruserUpdateDTO;
import org.secondopinion.diagnosticcenter.dto.Personaldetail;
import org.secondopinion.diagnosticcenter.dto.Diagnosticcenteruser.PasswordTypeEnum;
import org.secondopinion.diagnosticcenter.dto.Role;
import org.secondopinion.diagnosticcenter.dto.UpdatePasswordRequest;
import org.secondopinion.diagnosticcenter.exception.DiagnosticCenterServerException;
import org.secondopinion.diagnosticcenter.service.IDiagnosticCenterUserService;
import org.secondopinion.diagnosticcenter.service.IDiagnosticcenterService;
import org.secondopinion.diagnosticcenter.service.IDiagnosticcenteruserRegistrationService;

import org.secondopinion.request.Response;
import org.secondopinion.request.Response.StatusEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
public class DiagnosticCenterUserRegistrationController {

	@Autowired
	private IDiagnosticcenteruserRegistrationService dcuserRegistrationService;
	@Autowired
	private IDiagnosticCenterUserService iDiagnosticCenterUserService;
	@Autowired
	private IDiagnosticcenterService iDiagnosticcenterService;
	@Autowired
	private UtilComponent utilComponent;

	@GetMapping(value = "/roles")
	public ResponseEntity<Response<Collection<Role>>> getAllRoles() {
		try {
			Collection<Role> roles = dcuserRegistrationService.getAllRoles();
			return new ResponseEntity<>(new Response<>(roles, StatusEnum.SUCCESS, null), HttpStatus.OK);
		} catch (IllegalArgumentException e) {
			return new ResponseEntity<>(new Response<>(null, StatusEnum.FAILURE, e.getMessage()),
					HttpStatus.BAD_REQUEST);
		} catch (Exception e) {
			throw new DiagnosticCenterServerException(e.getMessage(), e);
		}
	}

	@PostMapping(value = "/registerassociateuser")
	public ResponseEntity<Response<String>> registerNewDiagnosticcenter(
			@RequestBody Diagnosticcenteruser diagnosticcenteruser) {
		try {
			dcuserRegistrationService.addDiagnosticcenteruser(diagnosticcenteruser, Boolean.FALSE,
					utilComponent.getRoles());
			return new ResponseEntity<>(
					new Response<>(null, StatusEnum.SUCCESS, "Diagnosticcenteruser data saved successfully"),
					HttpStatus.OK);
		} catch (IllegalArgumentException e) {
			return new ResponseEntity<>(new Response<>(null, StatusEnum.FAILURE, e.getMessage()),
					HttpStatus.BAD_REQUEST);
		} catch (Exception e) {
			throw new DiagnosticCenterServerException(e.getMessage(), e);
		}
	}

	@GetMapping(value = "/primarydetails")
	public ResponseEntity<Response<Diagnosticcenteruser>> getPrimaryDetails(@RequestParam Long diagnosticcenteid) {
		try {
			Diagnosticcenteruser pharmacyuser = dcuserRegistrationService.getPrimaryDetails(diagnosticcenteid);
			return new ResponseEntity<>(new Response<>(pharmacyuser, StatusEnum.SUCCESS, null), HttpStatus.OK);
		} catch (IllegalArgumentException e) {
			return new ResponseEntity<>(new Response<>(null, StatusEnum.FAILURE, e.getMessage()),
					HttpStatus.BAD_REQUEST);
		} catch (Exception e) {
			throw new DiagnosticCenterServerException(e.getMessage(), e);
		}
	}

	@PutMapping(value = "/updateUserRole")
	public ResponseEntity<Response<String>> updateUserRole(
			@RequestBody DiagnosticcenteruserUpdateDTO diagnosticCenteruser) {
		try {
			iDiagnosticCenterUserService.updateUserRole(diagnosticCenteruser);
			return new ResponseEntity<>(new Response<>("diagnosticcenteruser user details updated successfully",
					StatusEnum.SUCCESS, "diagnosticcenteruser user details updated successfully"), HttpStatus.OK);
		} catch (IllegalArgumentException e) {
			return new ResponseEntity<>(new Response<>(null, StatusEnum.FAILURE, e.getMessage()),
					HttpStatus.BAD_REQUEST);
		} catch (Exception e) {
			throw new DiagnosticCenterServerException(e.getMessage(), e);
		}
	}

	@PutMapping(value = "/update")
	public ResponseEntity<Response<Diagnosticcenteruser>> update(
			@RequestBody Diagnosticcenteruser diagnosticcenteruser) {
		try {
			dcuserRegistrationService.updatePrimaryDetails(diagnosticcenteruser);
			return new ResponseEntity<>(
					new Response<>(null, StatusEnum.SUCCESS, "diagnosticcenteruser user details updated successfully"),
					HttpStatus.OK);
		} catch (IllegalArgumentException e) {
			return new ResponseEntity<>(new Response<>(null, StatusEnum.FAILURE, e.getMessage()),
					HttpStatus.BAD_REQUEST);
		} catch (Exception e) {
			throw new DiagnosticCenterServerException(e.getMessage(), e);
		}
	}

	@PostMapping(value = "/activate/{diagnoticCenterId}")
	public ResponseEntity<Response<String>> activateUser(@PathVariable Long diagnoticCenterId) {
		try {
			dcuserRegistrationService.activateOrDeactivateUser(diagnoticCenterId, false);
			return new ResponseEntity<>(
					new Response<>(null, StatusEnum.SUCCESS, "Diagnosticcenteruser activated successfully"),
					HttpStatus.OK);
		} catch (IllegalArgumentException e) {
			return new ResponseEntity<>(new Response<>(null, StatusEnum.FAILURE, e.getMessage()),
					HttpStatus.BAD_REQUEST);
		} catch (Exception e) {
			throw new DiagnosticCenterServerException(e.getMessage(), e);
		}
	}

	@PostMapping(value = "/deactivate/{diagnoticCenterId}")
	public ResponseEntity<Response<String>> deactivateUser(@PathVariable Long diagnoticCenterId) {
		try {
			dcuserRegistrationService.activateOrDeactivateUser(diagnoticCenterId, true);
			return new ResponseEntity<>(
					new Response<>(null, StatusEnum.SUCCESS, "Diagnosticcenteruser deactivated successfully"),
					HttpStatus.OK);
		} catch (IllegalArgumentException e) {
			return new ResponseEntity<>(new Response<>(null, StatusEnum.FAILURE, e.getMessage()),
					HttpStatus.BAD_REQUEST);
		} catch (Exception e) {
			throw new DiagnosticCenterServerException(e.getMessage(), e);
		}
	}

	@PostMapping(value = "/verifyemail")
	public ResponseEntity<Response<String>> verifyemail(
			@RequestParam(value = "diagnoticCenterId", required = false) Long diagnoticCenterId,
			@RequestParam String emailId) {
		try {
			dcuserRegistrationService.verifyemail(diagnoticCenterId, emailId);
			return new ResponseEntity<>(new Response<>(null, StatusEnum.SUCCESS, null), HttpStatus.OK);
		} catch (IllegalArgumentException e) {
			return new ResponseEntity<>(new Response<>(null, StatusEnum.FAILURE, e.getMessage()),
					HttpStatus.BAD_REQUEST);
		} catch (Exception e) {
			throw new DiagnosticCenterServerException(e.getMessage(), e);
		}
	}

	@PostMapping(value = "/verificationid")
	public ResponseEntity<Response<Diagnosticcenteruser>> getDiagnosticcenteruserByVerificationId(
			@RequestParam String verificationId,
			@RequestParam(name = "passwordType", required = false) PasswordTypeEnum passwordType) {
		try {
			Diagnosticcenteruser pharmacyuser = dcuserRegistrationService
					.getDiagnosticcenteruserByVerificationId(verificationId, passwordType.name());
			return new ResponseEntity<>(
					new Response<>(pharmacyuser, StatusEnum.SUCCESS, "Please enter your otp to change the password."),
					HttpStatus.OK);
		} catch (IllegalArgumentException e) {
			return new ResponseEntity<>(new Response<>(null, StatusEnum.FAILURE, e.getMessage()),
					HttpStatus.BAD_REQUEST);
		} catch (Exception e) {
			throw new DiagnosticCenterServerException(e.getMessage(), e);
		}
	}

	@PostMapping(value = "/password/forgot")
	public ResponseEntity<Response<Diagnosticcenteruser>> forgotPassword(@RequestParam String emailId,
			@RequestParam String resetPasswordLink) {
		try {
			dcuserRegistrationService.forgotPassword(emailId, resetPasswordLink);
			return new ResponseEntity<>(new Response<>(null, StatusEnum.SUCCESS, "Reset Password link sent succefully"),
					HttpStatus.OK);
		} catch (IllegalArgumentException e) {
			return new ResponseEntity<>(new Response<>(null, StatusEnum.FAILURE, e.getMessage()),
					HttpStatus.BAD_REQUEST);
		} catch (Exception e) {
			throw new DiagnosticCenterServerException(e.getMessage(), e);
		}
	}

	@PostMapping(value = "/create/password")
	public ResponseEntity<Response<Diagnosticcenteruser>> createPassword(
			@RequestBody UpdatePasswordRequest updatePasswordRequest) {
		try {
			dcuserRegistrationService.resetPassword(updatePasswordRequest);
			return new ResponseEntity<>(
					new Response<>(null, StatusEnum.SUCCESS, "Specified password has been saved successfully"),
					HttpStatus.OK);
		} catch (IllegalArgumentException e) {
			return new ResponseEntity<>(new Response<>(null, StatusEnum.FAILURE, e.getMessage()),
					HttpStatus.BAD_REQUEST);
		} catch (Exception e) {
			throw new DiagnosticCenterServerException(e.getMessage(), e);
		}
	}

	@PostMapping(value = "/resetPasswordForDiagnosticcenteruser")
	public ResponseEntity<Response<Diagnosticcenteruser>> resetPasswordForDiagnosticcenteruser(
			@RequestBody UpdatePasswordRequest updatePasswordRequest) {
		try {
			dcuserRegistrationService.resetPasswordForDiagnosticcenteruser(updatePasswordRequest);
			return new ResponseEntity<>(
					new Response<>(null, StatusEnum.SUCCESS, "Specified password has been saved successfully"),
					HttpStatus.OK);
		} catch (IllegalArgumentException e) {
			return new ResponseEntity<>(new Response<>(null, StatusEnum.FAILURE, e.getMessage()),
					HttpStatus.BAD_REQUEST);
		} catch (Exception e) {
			throw new DiagnosticCenterServerException(e.getMessage(), e);
		}
	}

	@PostMapping(value = "/password/reset")
	public ResponseEntity<Response<Diagnosticcenteruser>> resetPasswordByDiagnosticcenter(
			@RequestBody UpdatePasswordRequest updatePasswordRequest) {
		try {
			dcuserRegistrationService.resetPasswordByDiagnosticcenter(updatePasswordRequest);
			return new ResponseEntity<>(
					new Response<>(null, StatusEnum.SUCCESS, "Specified password has been saved successfully"),
					HttpStatus.OK);
		} catch (IllegalArgumentException e) {
			return new ResponseEntity<>(new Response<>(null, StatusEnum.FAILURE, e.getMessage()),
					HttpStatus.BAD_REQUEST);
		} catch (Exception e) {
			throw new DiagnosticCenterServerException(e.getMessage(), e);
		}
	}

	@GetMapping(value = "/associated/{diagnoticCenterId}/{pageNum}/{maxResults}")
	public ResponseEntity<Response<List<Diagnosticcenteruser>>> getAssociatedUsers(@PathVariable Long diagnoticCenterId,
			@PathVariable Integer pageNum, @PathVariable Integer maxResults) {
		try {
			Response<List<Diagnosticcenteruser>> pharmacyusers = dcuserRegistrationService
					.getAssociatedUsers(diagnoticCenterId, pageNum, maxResults);
			return new ResponseEntity<>(pharmacyusers, HttpStatus.OK);
		} catch (IllegalArgumentException e) {
			return new ResponseEntity<>(new Response<>(null, StatusEnum.FAILURE, e.getMessage()),
					HttpStatus.BAD_REQUEST);
		} catch (Exception e) {
			throw new DiagnosticCenterServerException(e.getMessage(), e);
		}
	}

	@PostMapping(path = "/profilepic/{diagnosticcenterId}")
	public ResponseEntity<Response<String>> uploadProfilePic(@PathVariable Long diagnosticcenterId,
			@RequestParam(name = "profilePic", required = false) MultipartFile profilePic) {
		try {
			dcuserRegistrationService.uploadProfilePic(diagnosticcenterId, profilePic);
			return new ResponseEntity<>(new Response<>(null, StatusEnum.SUCCESS, null), HttpStatus.OK);
		} catch (IllegalArgumentException e) {
			return new ResponseEntity<>(new Response<>(null, StatusEnum.FAILURE, e.getMessage()),
					HttpStatus.BAD_REQUEST);
		} catch (Exception e) {
			throw new DiagnosticCenterServerException(e.getMessage(), e);
		}

	}
	@PostMapping(path = "/uploadReport/{diagnosticcenterId}")
	public ResponseEntity<Response<String>> uploadReport(@PathVariable Long diagnosticcenterId,
			@RequestParam(name = "profilePic", required = false) MultipartFile report) {
		try {
			dcuserRegistrationService.uploadReport(diagnosticcenterId, report);
			return new ResponseEntity<>(new Response<>(null, StatusEnum.SUCCESS, null), HttpStatus.OK);
		} catch (IllegalArgumentException e) {
			return new ResponseEntity<>(new Response<>(null, StatusEnum.FAILURE, e.getMessage()),
					HttpStatus.BAD_REQUEST);
		} catch (Exception e) {
			throw new DiagnosticCenterServerException(e.getMessage(), e);
		}

	}
	@PostMapping(value = "/save/personaldetail")
	public ResponseEntity<Response<String>> savePersonaldetail(@RequestBody Personaldetail personaldetail) {
		try {
			iDiagnosticcenterService.savePersonaldetail(personaldetail);
			return new ResponseEntity<>(new Response<>(null, StatusEnum.SUCCESS, "address saved successfully"),
					HttpStatus.OK);
		} catch (IllegalArgumentException e) {
			return new ResponseEntity<>(new Response<>(null, StatusEnum.FAILURE, e.getMessage()),
					HttpStatus.BAD_REQUEST);
		} catch (Exception e) {
			throw new DiagnosticCenterServerException(e.getMessage(), e);
		}
	}

	@PostMapping(value = "/get/personaldetail")
	public ResponseEntity<Response<Personaldetail>> getPersonaldetail(@RequestParam Long diagnosticcenterId) {
		try {
			Personaldetail personaldetail = iDiagnosticcenterService
					.findPersonalDetailBydiagnosticcenterId(diagnosticcenterId);
			return new ResponseEntity<>(
					new Response<>(personaldetail, StatusEnum.SUCCESS, "address saved successfully"), HttpStatus.OK);
		} catch (IllegalArgumentException e) {
			return new ResponseEntity<>(new Response<>(null, StatusEnum.FAILURE, e.getMessage()),
					HttpStatus.BAD_REQUEST);
		} catch (Exception e) {
			throw new DiagnosticCenterServerException(e.getMessage(), e);
		}
	}

	@PostMapping(value = "/findDiagnosticcenterByLocation")
	public ResponseEntity<Response<List<Diagnosticcenter>>> findDiagnosticcenterByLocation(
			@RequestBody DiagnosticcenterSearchRequest diagnosticcenterSearchRequest) {
		try {
			List<Diagnosticcenter> diagnosticcenters = iDiagnosticcenterService
					.findDiagnosticcenterByLocation(diagnosticcenterSearchRequest);
			return new ResponseEntity<>(
					new Response<>(diagnosticcenters, StatusEnum.SUCCESS, "Diagnosticcenter Retrive successfully"),
					HttpStatus.OK);
		} catch (IllegalArgumentException e) {
			return new ResponseEntity<>(new Response<>(null, StatusEnum.FAILURE, e.getMessage()),
					HttpStatus.BAD_REQUEST);
		} catch (Exception e) {
			throw new DiagnosticCenterServerException(e.getMessage(), e);
		}
	}

}
