package org.secondopinion.pharmacy.controller;

import java.util.Collection;
import java.util.List;
import org.secondopinion.configurations.UtilComponent;
import org.secondopinion.pharmacy.dto.PharmacyUserUpadteDTO;
import org.secondopinion.pharmacy.dto.Pharmacyuser;
import org.secondopinion.pharmacy.dto.Pharmacyuser.PasswordTypeEnum;
import org.secondopinion.pharmacy.dto.Roles;
import org.secondopinion.pharmacy.dto.UpdatePasswordRequest;
import org.secondopinion.pharmacy.exception.PharmacyServerException;
import org.secondopinion.pharmacy.service.IPharmacyuserRegistrationService;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PharmacyuserRegistrationController {

	@Autowired
	private IPharmacyuserRegistrationService pharmacyuserRegistrationService;

	@Autowired
	private UtilComponent utilComponent;

	@GetMapping(value = "/roles")
	public ResponseEntity<Response<Collection<Roles>>> getAllRoles() {
		try {
			Collection<Roles> roles = pharmacyuserRegistrationService.getAllRoles();
			return new ResponseEntity<>(new Response<>(roles, StatusEnum.SUCCESS, null), HttpStatus.OK);
		} catch (IllegalArgumentException e) {
			return new ResponseEntity<>(new Response<>(null, StatusEnum.FAILURE, e.getMessage()),
					HttpStatus.BAD_REQUEST);
		} catch (Exception e) {
			throw new PharmacyServerException(e.getMessage(), e);
		}
	}

	@PostMapping(value = "/updatePharmacyuser")
	public ResponseEntity<Response<String>> updatePharmacyuser(
			@RequestBody PharmacyUserUpadteDTO pharmacyUserUpadteDTO) {
		try {
			pharmacyuserRegistrationService.updatePharmacyuser(pharmacyUserUpadteDTO);
			return new ResponseEntity<>(new Response<>("Pharmacyuser data Update successfully", StatusEnum.SUCCESS,
					"Pharmacyuser data Update successfully"), HttpStatus.OK);
		} catch (IllegalArgumentException e) {
			return new ResponseEntity<>(new Response<>(null, StatusEnum.FAILURE, e.getMessage()),
					HttpStatus.BAD_REQUEST);
		} catch (Exception e) {
			throw new PharmacyServerException(e.getMessage(), e);
		}
	}

	@PostMapping(value = "/registeruser")
	public ResponseEntity<Response<Pharmacyuser>> registerNewPharmacyuser(@RequestBody Pharmacyuser pharmacyuser) {
		try {
			pharmacyuser = pharmacyuserRegistrationService.addPharmacyuser(pharmacyuser, Boolean.FALSE,
					utilComponent.getRoles());
			return new ResponseEntity<>(
					new Response<>(pharmacyuser, StatusEnum.SUCCESS, "Pharmacyuser data saved successfully"),
					HttpStatus.OK);
		} catch (IllegalArgumentException e) {
			return new ResponseEntity<>(new Response<>(null, StatusEnum.FAILURE, e.getMessage()),
					HttpStatus.BAD_REQUEST);
		} catch (Exception e) {
			throw new PharmacyServerException(e.getMessage(), e);
		}
	}

	@GetMapping(value = "/primarydetails")
	public ResponseEntity<Response<Pharmacyuser>> getPrimaryDetails(@RequestParam Long pharmacyuserid) {
		try {
			Pharmacyuser pharmacyuser = pharmacyuserRegistrationService.getPrimaryDetails(pharmacyuserid);
			return new ResponseEntity<>(new Response<>(pharmacyuser, StatusEnum.SUCCESS, null), HttpStatus.OK);
		} catch (IllegalArgumentException e) {
			return new ResponseEntity<>(new Response<>(null, StatusEnum.FAILURE, e.getMessage()),
					HttpStatus.BAD_REQUEST);
		} catch (Exception e) {
			throw new PharmacyServerException(e.getMessage(), e);
		}
	}

	@PutMapping(value = "/update")
	public ResponseEntity<Response<Pharmacyuser>> update(@RequestBody Pharmacyuser pharmacyuser) {
		try {
			pharmacyuserRegistrationService.updatePrimaryDetails(pharmacyuser);
			return new ResponseEntity<>(
					new Response<>(null, StatusEnum.SUCCESS, "pharmacy user details updated successfully"),
					HttpStatus.OK);
		} catch (IllegalArgumentException e) {
			return new ResponseEntity<>(new Response<>(null, StatusEnum.FAILURE, e.getMessage()),
					HttpStatus.BAD_REQUEST);
		} catch (Exception e) {
			throw new PharmacyServerException(e.getMessage(), e);
		}
	}

	@PostMapping(value = "/activate/{pharmacyUserId}")
	public ResponseEntity<Response<String>> activateUser(@PathVariable Long pharmacyUserId) {
		try {
			pharmacyuserRegistrationService.activateOrDeactivateUser(pharmacyUserId, false);
			return new ResponseEntity<>(new Response<>(null, StatusEnum.SUCCESS, "Pharmacyuser activated successfully"),
					HttpStatus.OK);
		} catch (IllegalArgumentException e) {
			return new ResponseEntity<>(new Response<>(null, StatusEnum.FAILURE, e.getMessage()),
					HttpStatus.BAD_REQUEST);
		} catch (Exception e) {
			throw new PharmacyServerException(e.getMessage(), e);
		}
	}

	@PostMapping(value = "/deactivate/{pharmacyUserId}")
	public ResponseEntity<Response<String>> deactivateUser(@PathVariable Long pharmacyUserId) {
		try {
			pharmacyuserRegistrationService.activateOrDeactivateUser(pharmacyUserId, true);
			return new ResponseEntity<>(
					new Response<>(null, StatusEnum.SUCCESS, "Pharmacyuser deactivated successfully"), HttpStatus.OK);
		} catch (IllegalArgumentException e) {
			return new ResponseEntity<>(new Response<>(null, StatusEnum.FAILURE, e.getMessage()),
					HttpStatus.BAD_REQUEST);
		} catch (Exception e) {
			throw new PharmacyServerException(e.getMessage(), e);
		}
	}

	@PostMapping(value = "/verifyemail")
	public ResponseEntity<Response<String>> verifyemail(
			@RequestParam(value = "pharmacyAddressId", required = false) Long pharmacyAddressId,
			@RequestParam String emailId) {
		try {
			pharmacyuserRegistrationService.verifyemail(pharmacyAddressId, emailId);
			return new ResponseEntity<>(new Response<>(null, StatusEnum.SUCCESS, null), HttpStatus.OK);
		} catch (IllegalArgumentException e) {
			return new ResponseEntity<>(new Response<>(null, StatusEnum.FAILURE, e.getMessage()),
					HttpStatus.BAD_REQUEST);
		} catch (Exception e) {
			throw new PharmacyServerException(e.getMessage(), e);
		}
	}

	@PostMapping(value = "/verificationid")
	public ResponseEntity<Response<Pharmacyuser>> getPharmacyuserByVerificationId(@RequestParam String verificationId,
			@RequestParam(name = "passwordType", required = false) PasswordTypeEnum passwordType) {
		try {
			Pharmacyuser pharmacyuser = pharmacyuserRegistrationService.getPharmacyuserByVerificationId(verificationId,
					passwordType.name());
			return new ResponseEntity<>(
					new Response<>(pharmacyuser, StatusEnum.SUCCESS, "Please enter your otp to change the password."),
					HttpStatus.OK);
		} catch (IllegalArgumentException e) {
			return new ResponseEntity<>(new Response<>(null, StatusEnum.FAILURE, e.getMessage()),
					HttpStatus.BAD_REQUEST);
		} catch (Exception e) {
			throw new PharmacyServerException(e.getMessage(), e);
		}
	}

	@PostMapping(value = "/password/forgot")
	public ResponseEntity<Response<Pharmacyuser>> forgotPassword(@RequestParam String emailId,
			@RequestParam String resetPwdLink) {
		try {
			pharmacyuserRegistrationService.forgotPassword(emailId, resetPwdLink);
			return new ResponseEntity<>(new Response<>(null, StatusEnum.SUCCESS, "Reset Password link sent succefully"),
					HttpStatus.OK);
		} catch (IllegalArgumentException e) {
			return new ResponseEntity<>(new Response<>(null, StatusEnum.FAILURE, e.getMessage()),
					HttpStatus.BAD_REQUEST);
		} catch (Exception e) {
			throw new PharmacyServerException(e.getMessage(), e);
		}
	}

	@PostMapping(value = "/resetPasswordForPharmacyuser")
	public ResponseEntity<Response<Pharmacyuser>> resetPasswordForPharmacyuser(
			@RequestBody UpdatePasswordRequest updatePasswordRequest) {
		try {
			pharmacyuserRegistrationService.resetPasswordForPharmacyuser(updatePasswordRequest);
			return new ResponseEntity<>(new Response<>(null, StatusEnum.SUCCESS, "Password created successfully."),
					HttpStatus.OK);
		} catch (IllegalArgumentException e) {
			return new ResponseEntity<>(new Response<>(null, StatusEnum.FAILURE, e.getMessage()),
					HttpStatus.BAD_REQUEST);
		} catch (Exception e) {
			throw new PharmacyServerException(e.getMessage(), e);
		}
	}

	@PostMapping(value = "/resetPasswordForPharmacyUserNew")
	public ResponseEntity<Response<Pharmacyuser>> resetPasswordForPharmacyUserNew(
			@RequestBody UpdatePasswordRequest updatePasswordRequest) {
		try {
			pharmacyuserRegistrationService.resetPasswordForPharmacyUserNew(updatePasswordRequest);
			return new ResponseEntity<>(new Response<>(null, StatusEnum.SUCCESS, "Password created successfully."),
					HttpStatus.OK);
		} catch (IllegalArgumentException e) {
			return new ResponseEntity<>(new Response<>(null, StatusEnum.FAILURE, e.getMessage()),
					HttpStatus.BAD_REQUEST);
		} catch (Exception e) {
			throw new PharmacyServerException(e.getMessage(), e);
		}
	}

	@PostMapping(value = "/create/password")
	public ResponseEntity<Response<Pharmacyuser>> createPasswordByVerificationId(
			@RequestBody UpdatePasswordRequest updatePasswordRequest) {
		try {
			pharmacyuserRegistrationService.resetPasswordByVerificationId(updatePasswordRequest);
			return new ResponseEntity<>(new Response<>(null, StatusEnum.SUCCESS, "Password created successfully."),
					HttpStatus.OK);
		} catch (IllegalArgumentException e) {
			return new ResponseEntity<>(new Response<>(null, StatusEnum.FAILURE, e.getMessage()),
					HttpStatus.BAD_REQUEST);
		} catch (Exception e) {
			throw new PharmacyServerException(e.getMessage(), e);
		}
	}

	@PostMapping(value = "/password/reset")
	public ResponseEntity<Response<Pharmacyuser>> resetPasswordByPharmacy(
			@RequestBody UpdatePasswordRequest updatePasswordRequest) {
		try {
			pharmacyuserRegistrationService.resetPasswordByPharmacyUserId(updatePasswordRequest);
			return new ResponseEntity<>(new Response<>(null, StatusEnum.SUCCESS, "Password created successfully."),
					HttpStatus.OK);
		} catch (IllegalArgumentException e) {
			return new ResponseEntity<>(new Response<>(null, StatusEnum.FAILURE, e.getMessage()),
					HttpStatus.BAD_REQUEST);
		} catch (Exception e) {
			throw new PharmacyServerException(e.getMessage(), e);
		}
	}

	@GetMapping(value = "/associated/{pharmacyaddressId}/{pageNum}/{maxResults}")
	public ResponseEntity<Response<List<Pharmacyuser>>> getAssociatedUsers(@PathVariable Long pharmacyaddressId,
			@PathVariable Integer pageNum, @PathVariable Integer maxResults) {
		try {
			Response<List<Pharmacyuser>> pharmacyusers = pharmacyuserRegistrationService
					.getAssociatedUsers(pharmacyaddressId, pageNum, maxResults);
			return new ResponseEntity<>(pharmacyusers, HttpStatus.OK);
		} catch (IllegalArgumentException e) {
			return new ResponseEntity<>(new Response<>(null, StatusEnum.FAILURE, e.getMessage()),
					HttpStatus.BAD_REQUEST);
		} catch (Exception e) {
			throw new PharmacyServerException(e.getMessage(), e);
		}
	}
}
