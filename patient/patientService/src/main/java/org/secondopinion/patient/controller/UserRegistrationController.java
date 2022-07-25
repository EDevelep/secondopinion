package org.secondopinion.patient.controller;

import java.util.Collection;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.secondopinion.patient.UserIdHelper;
import org.secondopinion.patient.dto.DoctorDashBoardDTO;
import org.secondopinion.patient.dto.ForUser;
import org.secondopinion.patient.dto.ProfileCompltedDTO;
import org.secondopinion.patient.dto.RELATIONSHIP_TYPE;
import org.secondopinion.patient.dto.UpdatePasswordRequest;
import org.secondopinion.patient.dto.User;
import org.secondopinion.patient.dto.UserResponseDTO;
import org.secondopinion.patient.exception.PatientServerException;
import org.secondopinion.patient.service.IUserRegistrationService;
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
@RequestMapping("/")
@Api("/")
public class UserRegistrationController {
	@Autowired
	private IUserRegistrationService userRegistrationService;

	@Autowired
	private UserIdHelper userIdHelper;
	@Autowired
	private HttpServletRequest httpServletRequest;

	@PostMapping(value = "/save")
	public ResponseEntity<Response<String>> saveUser(@RequestBody User user) {

		try {
			userRegistrationService.registerUser(user, null);
			return new ResponseEntity<>(new Response<>("User data saved.", StatusEnum.SUCCESS, "User data saved."),
					HttpStatus.OK);
		} catch (IllegalArgumentException e) {
			return new ResponseEntity<>(new Response<>(null, StatusEnum.FAILURE, e.getMessage()),
					HttpStatus.BAD_REQUEST);
		} catch (Exception e) {
			throw new PatientServerException(e.getMessage(), e);
		}

	}

	@GetMapping("/registration/activate")
	public ResponseEntity<Response<Boolean>> activateRegistration(@RequestParam("userId ") Long userId) {
		try {
			return new ResponseEntity<>(new Response<>(userRegistrationService.activateRegistration(userId),
					StatusEnum.SUCCESS, "User activated."), HttpStatus.OK);
		} catch (IllegalArgumentException e) {
			return new ResponseEntity<>(new Response<>(null, StatusEnum.FAILURE, e.getMessage()),
					HttpStatus.BAD_REQUEST);
		} catch (Exception e) {
			throw new PatientServerException(e.getMessage(), e);
		}

	}

	@GetMapping("/getuserbyemail")
	public ResponseEntity<Response<UserResponseDTO>> getUserByEmail(@RequestParam("userEmail") String userEmail,
			@RequestParam("userName") String userName) {
		try {
			Long UserId = userIdHelper.getUserId();
			UserResponseDTO user = userRegistrationService.getUserByEmail(userEmail, UserId, userName);
			if (user == null) {
				return new ResponseEntity<>(new Response<>(user, StatusEnum.SUCCESS, "User Not  found."),
						HttpStatus.OK);
			}
			return new ResponseEntity<>(new Response<>(user, StatusEnum.SUCCESS, "User found."), HttpStatus.OK);
		} catch (IllegalArgumentException e) {
			return new ResponseEntity<>(new Response<>(null, StatusEnum.FAILURE, e.getMessage()),
					HttpStatus.BAD_REQUEST);
		} catch (Exception e) {
			throw new PatientServerException(e.getMessage(), e);
		}

	}

	@GetMapping("/getUserDetailsByEmail")
	public ResponseEntity<Response<UserResponseDTO>> getUserDetailsByEmail(@RequestParam("userEmail") String userEmail,
			@RequestParam("username") String userName) {
		try {
			UserResponseDTO user = userRegistrationService.getUserDetailsByEmail(userEmail, userName);
			if (user == null) {
				return new ResponseEntity<>(new Response<>(null, StatusEnum.SUCCESS, "User Not found."), HttpStatus.OK);
			}
			return new ResponseEntity<>(new Response<>(user, StatusEnum.SUCCESS, "User found."), HttpStatus.OK);
		} catch (IllegalArgumentException e) {
			return new ResponseEntity<>(new Response<>(null, StatusEnum.FAILURE, e.getMessage()),
					HttpStatus.BAD_REQUEST);
		} catch (Exception e) {
			throw new PatientServerException(e.getMessage(), e);
		}

	}

	@GetMapping("/userEmailRegistered")
	public ResponseEntity<Response<Boolean>> userEmailRegistered(@RequestParam("userEmail") String userEmail) {
		try {
			Boolean isExist = userRegistrationService.userEmailRegistered(userEmail);
			String message = (isExist) ? "UserEmail already exist." : " UserEmail not exist.";

			return new ResponseEntity<>(new Response<>(isExist, StatusEnum.SUCCESS, message), HttpStatus.OK);

		} catch (IllegalArgumentException e) {
			return new ResponseEntity<>(new Response<>(null, StatusEnum.FAILURE, e.getMessage()),
					HttpStatus.BAD_REQUEST);
		} catch (Exception e) {
			throw new PatientServerException(e.getMessage(), e);
		}

	}

	@GetMapping("/userphonenumberRegistered")
	public ResponseEntity<Response<Boolean>> userPhoneNumberRegistered(@RequestParam("cellNumber") String cellNumber) {
		try {

			Boolean isExist = userRegistrationService.userphoneNumberRegistered(cellNumber);
			String message = (isExist) ? "CellNumber already exist." : " CellNumber not exist.";

			return new ResponseEntity<>(new Response<>(isExist, StatusEnum.SUCCESS, message), HttpStatus.OK);
		} catch (IllegalArgumentException e) {
			return new ResponseEntity<>(new Response<>(null, StatusEnum.FAILURE, e.getMessage()),
					HttpStatus.BAD_REQUEST);
		} catch (Exception e) {
			throw new PatientServerException(e.getMessage(), e);
		}

	}

	@GetMapping("/userNameExists")
	public ResponseEntity<Response<Boolean>> userNameExists(@RequestParam("userName") String userName) {
		try {
			Boolean isExist = userRegistrationService.userNameExists(userName);
			String message = (isExist) ? "UserName already exist." : " UserName not exist.";

			return new ResponseEntity<>(new Response<>(isExist, StatusEnum.SUCCESS, message), HttpStatus.OK);

		} catch (IllegalArgumentException e) {
			return new ResponseEntity<>(new Response<>(null, StatusEnum.FAILURE, e.getMessage()),
					HttpStatus.BAD_REQUEST);
		} catch (Exception e) {
			throw new PatientServerException(e.getMessage(), e);
		}

	}

	@PostMapping(path = "/password/forgot")
	public ResponseEntity<Response<String>> forgotPassword(@RequestBody UpdatePasswordRequest userPasswordReguest) {

		try {
			userRegistrationService.forgotPassword(userPasswordReguest.getEmailId(),
					userPasswordReguest.getResetPasswordLink(), userPasswordReguest.getUserName());
			return new ResponseEntity<>(
					new Response<>("New password saved.", StatusEnum.SUCCESS, "New password saved."), HttpStatus.OK);
		} catch (IllegalArgumentException e) {
			return new ResponseEntity<>(new Response<>(null, StatusEnum.FAILURE, e.getMessage()),
					HttpStatus.BAD_REQUEST);
		} catch (Exception e) {
			throw new PatientServerException(e.getMessage(), e);
		}

	}

	@GetMapping("/verification/request/email/link")
	public ResponseEntity<Response<String>> requestemailverificationlink(@RequestParam("emailId") String emailId) {
		try {
			userRegistrationService.requestEmailVerificationLink(emailId);
			return new ResponseEntity<>(new Response<>(null, StatusEnum.SUCCESS, null), HttpStatus.OK);
		} catch (IllegalArgumentException e) {
			return new ResponseEntity<>(new Response<>(null, StatusEnum.FAILURE, e.getMessage()),
					HttpStatus.BAD_REQUEST);
		} catch (Exception e) {
			throw new PatientServerException(e.getMessage(), e);
		}

	}

	@GetMapping("/verification/phoneotp")
	public ResponseEntity<Response<String>> phoneverification(@RequestParam("otp") Integer otp,
			@RequestParam("cellNumber") String cellNumber, @RequestParam("userName") String userName,
			@RequestParam("forUserId") Long forUserId) {
		try {
			userRegistrationService.phoneVerification(cellNumber, otp, userName, forUserId);
			return new ResponseEntity<>(new Response<>(null, StatusEnum.SUCCESS, null), HttpStatus.OK);
		} catch (IllegalArgumentException e) {
			return new ResponseEntity<>(new Response<>(null, StatusEnum.FAILURE, e.getMessage()),
					HttpStatus.BAD_REQUEST);
		} catch (Exception e) {
			throw new PatientServerException(e.getMessage(), e);
		}

	}

	@GetMapping("/verification/emailotp")
	public ResponseEntity<Response<String>> emailVerification(@RequestParam("emailotp") Integer emailotp,
			@RequestParam("emailid") String emailid, @RequestParam("userName") String userName) {
		try {
			userRegistrationService.emailVerification(emailid, emailotp, userName);
			return new ResponseEntity<>(new Response<>(null, StatusEnum.SUCCESS, null), HttpStatus.OK);
		} catch (IllegalArgumentException e) {
			return new ResponseEntity<>(new Response<>(null, StatusEnum.FAILURE, e.getMessage()),
					HttpStatus.BAD_REQUEST);
		} catch (Exception e) {
			throw new PatientServerException(e.getMessage(), e);
		}

	}

	@GetMapping("/verification/request/phone/otp")
	public ResponseEntity<Response<String>> requestOTPForPhoneVerification(
			@RequestParam("phonenumber") String phonenumber, @RequestParam("userName") String userName) {
		try {
			userRegistrationService.requestOTPForPhoneVerification(phonenumber, userName);
			return new ResponseEntity<>(new Response<>(null, StatusEnum.SUCCESS, null), HttpStatus.OK);
		} catch (IllegalArgumentException e) {
			return new ResponseEntity<>(new Response<>(null, StatusEnum.FAILURE, e.getMessage()),
					HttpStatus.BAD_REQUEST);
		} catch (Exception e) {
			throw new PatientServerException(e.getMessage(), e);
		}

	}

	@GetMapping("/verification/request/email/otp")
	public ResponseEntity<Response<String>> requestOTPForEamilVerification(@RequestParam("emailId") String emailId,
			@RequestParam("username") String userName) {
		try {
			userRegistrationService.requestOTPForEamilVerification(emailId, userName);
			return new ResponseEntity<>(new Response<>(null, StatusEnum.SUCCESS, null), HttpStatus.OK);
		} catch (IllegalArgumentException e) {
			return new ResponseEntity<>(new Response<>(null, StatusEnum.FAILURE, e.getMessage()),
					HttpStatus.BAD_REQUEST);
		} catch (Exception e) {
			throw new PatientServerException(e.getMessage(), e);
		}

	}

	@PostMapping(path = "/password/reset")
	public ResponseEntity<Response<String>> resetPassword(@RequestBody UpdatePasswordRequest userPasswordReguest) {

		try {
			userRegistrationService.resetPassword(userPasswordReguest.getEmailId(),
					userPasswordReguest.getNewPassword(), userPasswordReguest.getOtp(),
					userPasswordReguest.getUserName());
			return new ResponseEntity<>(new Response<>("New password saved.", StatusEnum.SUCCESS, null), HttpStatus.OK);
		} catch (IllegalArgumentException e) {
			return new ResponseEntity<>(new Response<>(null, StatusEnum.FAILURE, e.getMessage()),
					HttpStatus.BAD_REQUEST);
		} catch (Exception e) {
			throw new PatientServerException(e.getMessage(), e);
		}

	}

	@GetMapping(path = "/resendOTP")
	public ResponseEntity<Response<String>> resendOTPForEmailid(@RequestParam("emailId") String emailId,
			@RequestParam("UserName") String userName) {
		try {
			userRegistrationService.resendOTPForEmailId(emailId, userName);
			return new ResponseEntity<>(
					new Response<>("OTP  Send successfully.", StatusEnum.SUCCESS, "OTP  Send successfully"),
					HttpStatus.OK);
		} catch (IllegalArgumentException e) {
			return new ResponseEntity<>(new Response<>("You are Not Verifed", StatusEnum.FAILURE, e.getMessage()),
					HttpStatus.BAD_REQUEST);
		} catch (Exception e) {
			throw new PatientServerException(e.getMessage(), e);
		}

	}

	@PostMapping(path = "/password/otp/resend")
	public ResponseEntity<Response<String>> resendOTPForResetPassword(
			@RequestBody UpdatePasswordRequest userPasswordReguest) {

		try {
			userRegistrationService.resendOTPForResetPassword(userPasswordReguest.getEmailId(),
					userPasswordReguest.getResetPasswordLink(), userPasswordReguest.getUserName());
			return new ResponseEntity<>(
					new Response<>("New password saved.", StatusEnum.SUCCESS, "New password saved."), HttpStatus.OK);
		} catch (IllegalArgumentException e) {
			return new ResponseEntity<>(new Response<>(null, StatusEnum.FAILURE, e.getMessage()),
					HttpStatus.BAD_REQUEST);
		} catch (Exception e) {
			throw new PatientServerException(e.getMessage(), e);
		}

	}

	@DeleteMapping("/delete/{userId}")
	public ResponseEntity<Response<String>> deleteUser(@PathVariable Long userId) {
		try {
			userRegistrationService.deleteUser(userId);
			return new ResponseEntity<>(new Response<>(null, StatusEnum.SUCCESS, null), HttpStatus.OK);
		} catch (IllegalArgumentException e) {
			return new ResponseEntity<>(new Response<>(null, StatusEnum.FAILURE, e.getMessage()),
					HttpStatus.BAD_REQUEST);
		} catch (Exception e) {
			throw new PatientServerException(e.getMessage(), e);
		}

	}

	@PostMapping("/getPrimaryDetails/{userId}")
	public ResponseEntity<Response<UserResponseDTO>> getPrimaryDetails(@PathVariable Long userId) {
		try {
			String forUserId = httpServletRequest.getHeader("forUserId");
			String moduleName = httpServletRequest.getHeader("moduleName");
			RELATIONSHIP_TYPE relationship_TYPE = RELATIONSHIP_TYPE.valueOf(moduleName);
			ForUser forUser = new ForUser();
			forUser.setUserId(userId);
			forUser.setForUserId(new Long(forUserId));
			UserResponseDTO user = userRegistrationService.getPrimaryDetails(userId, forUser, relationship_TYPE);

			return new ResponseEntity<>(new Response<>(user, StatusEnum.SUCCESS, null), HttpStatus.OK);
		} catch (IllegalArgumentException e) {
			return new ResponseEntity<>(new Response<>(null, StatusEnum.FAILURE, e.getMessage()),
					HttpStatus.BAD_REQUEST);
		} catch (Exception e) {
			throw new PatientServerException(e.getMessage(), e);
		}

	}

	@PostMapping("/userProfileCompleted/{userid}")
	public ResponseEntity<Response<ProfileCompltedDTO>> userProfileCompleted(@PathVariable Long userid) {
		try {
			ProfileCompltedDTO bolen = userRegistrationService.userProfileCompleted(userid);
			return new ResponseEntity<>(new Response<>(bolen, StatusEnum.SUCCESS, null), HttpStatus.OK);
		} catch (IllegalArgumentException e) {
			return new ResponseEntity<>(new Response<>(null, StatusEnum.FAILURE, e.getMessage()),
					HttpStatus.BAD_REQUEST);
		} catch (Exception e) {
			throw new PatientServerException(e.getMessage(), e);
		}

	}

	@GetMapping("/otpVerificationForPhone")
	public ResponseEntity<Response<String>> otpVerificationForPhone(
			@RequestParam("phonenumberotp") Integer phonenumberotp) {
		try {
			String value = userRegistrationService.otpVerificationForPhone(phonenumberotp);
			return new ResponseEntity<>(new Response<>(value, StatusEnum.SUCCESS, value), HttpStatus.OK);
		} catch (IllegalArgumentException e) {
			return new ResponseEntity<>(new Response<>(null, StatusEnum.FAILURE, e.getMessage()),
					HttpStatus.BAD_REQUEST);
		} catch (Exception e) {
			throw new PatientServerException(e.getMessage(), e);
		}

	}

	@GetMapping("/otpVerificationForEmail")
	public ResponseEntity<Response<String>> otpVerificationForEmail(@RequestParam("emailotp") Integer emailotp,
			@RequestParam("emailId") String emailId, @RequestParam("userName") String userName) {
		try {
			String value = userRegistrationService.otpVerificationForEmail(emailotp, emailId, userName);
			return new ResponseEntity<>(new Response<>(value, StatusEnum.SUCCESS, value), HttpStatus.OK);
		} catch (IllegalArgumentException e) {
			return new ResponseEntity<>(new Response<>(null, StatusEnum.FAILURE, e.getMessage()),
					HttpStatus.BAD_REQUEST);
		} catch (Exception e) {
			throw new PatientServerException(e.getMessage(), e);
		}

	}

	@GetMapping("/oldPaswwordVerification")
	public ResponseEntity<Response<Boolean>> oldPaswwordVerification(@RequestParam("paswword") String paswword,
			@RequestParam("emailId") String emailId, @RequestParam("userName") String userName) {
		try {
			Boolean value = userRegistrationService.oldPaswwordVerification(emailId, paswword, userName);
			return new ResponseEntity<>(new Response<>(value, StatusEnum.SUCCESS, ""), HttpStatus.OK);
		} catch (IllegalArgumentException e) {
			return new ResponseEntity<>(new Response<>(null, StatusEnum.FAILURE, e.getMessage()),
					HttpStatus.BAD_REQUEST);
		} catch (Exception e) {
			throw new PatientServerException(e.getMessage(), e);
		}

	}

	@GetMapping("/gealluser")
	public ResponseEntity<Response<List<User>>> getAllUser() {
		try {
			List<User> user = userRegistrationService.getUserAllUser();
			return new ResponseEntity<>(new Response<>(user, StatusEnum.SUCCESS, "User found."), HttpStatus.OK);
		} catch (IllegalArgumentException e) {
			return new ResponseEntity<>(new Response<>(null, StatusEnum.FAILURE, e.getMessage()),
					HttpStatus.BAD_REQUEST);
		} catch (Exception e) {
			throw new PatientServerException(e.getMessage(), e);
		}

	}

	@GetMapping("/getDoctorDashBoardForDoctorIdAndAppointmentFor")
	public ResponseEntity<Response<Collection<DoctorDashBoardDTO>>> getDoctorDashBoardForDoctorIdAndAppointmentFor(
			@RequestParam("appointmentFor") String appointmentFor, @RequestParam("doctorId") Long doctorId) {
		try {
			Collection<DoctorDashBoardDTO> dashBoardDTOs = userRegistrationService
					.getDoctorDashBoardForDoctorIdAndAppointmentFor(doctorId, appointmentFor);
			return new ResponseEntity<>(new Response<>(dashBoardDTOs, StatusEnum.SUCCESS, ""), HttpStatus.OK);
		} catch (IllegalArgumentException e) {
			return new ResponseEntity<>(new Response<>(null, StatusEnum.FAILURE, e.getMessage()),
					HttpStatus.BAD_REQUEST);
		} catch (Exception e) {
			throw new PatientServerException(e.getMessage(), e);
		}

	}

}
