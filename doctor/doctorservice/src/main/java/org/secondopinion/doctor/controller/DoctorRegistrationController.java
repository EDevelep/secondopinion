package org.secondopinion.doctor.controller;

import java.util.List;
import java.util.Map;

import org.secondopinion.doctor.dto.Doctor;
import org.secondopinion.doctor.dto.DoctorFlagsRequest;
import org.secondopinion.doctor.dto.DoctorPasswordReguest;
import org.secondopinion.doctor.dto.OtpVerificationDTO;
import org.secondopinion.doctor.dto.ProfileCompletedDTO;
import org.secondopinion.doctor.dto.DoctorFlagsRequest.DoctorFlag;
import org.secondopinion.doctor.exception.DoctorServerException;
import org.secondopinion.doctor.service.IDoctorRegistrationService;
import org.secondopinion.request.Response;
import org.secondopinion.request.Response.StatusEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.ApiOperation;

@RestController
public class DoctorRegistrationController {

	@Autowired
	private IDoctorRegistrationService doctorRegistrationService;

	@PostMapping(value = "/register")
	public ResponseEntity<Response<String>> registerNewDoctor(@RequestBody Doctor doctor) {
		try {
			doctorRegistrationService.registerDoctor(doctor);
			return new ResponseEntity<>(new Response<>("Doctor Registered SuccessFully", StatusEnum.SUCCESS, null),
					HttpStatus.OK);
		} catch (IllegalArgumentException e) {
			return new ResponseEntity<>(new Response<>(null, StatusEnum.FAILURE, e.getMessage()),
					HttpStatus.BAD_REQUEST);
		} catch (Exception e) {
			throw new DoctorServerException(e.getMessage(), e);
		}

	}

	@GetMapping("/verification/phoneotp")
	public ResponseEntity<Response<String>> phoneverification(@RequestParam("otp") Integer otp,
			@RequestParam("phonenumber") String phonenumber) {
		try {
			doctorRegistrationService.phoneverification(phonenumber, otp);
			return new ResponseEntity<>(new Response<>(null, StatusEnum.SUCCESS, "Your phone number is now verified"),
					HttpStatus.OK);
		} catch (IllegalArgumentException e) {
			return new ResponseEntity<>(new Response<>(null, StatusEnum.FAILURE, e.getMessage()),
					HttpStatus.BAD_REQUEST);
		} catch (Exception e) {
			throw new DoctorServerException(e.getMessage(), e);
		}

	}

	@GetMapping("/verification/request/email/link")
	public ResponseEntity<Response<String>> requestemailverificationlink(@RequestParam("emailId") String emailId) {
		try {
			doctorRegistrationService.requestEmailVerificationLink(emailId);
			return new ResponseEntity<>(new Response<>(null, StatusEnum.SUCCESS, null), HttpStatus.OK);
		} catch (IllegalArgumentException e) {
			return new ResponseEntity<>(new Response<>(null, StatusEnum.FAILURE, e.getMessage()),
					HttpStatus.BAD_REQUEST);
		} catch (Exception e) {
			throw new DoctorServerException(e.getMessage(), e);
		}

	}

	@GetMapping("/verification/request/email/otp")
	public ResponseEntity<Response<String>> requestOTPForEmailVerification(@RequestParam("emailid") String emailid) {
		try {
			doctorRegistrationService.requestOTPForEmailVerification(emailid);
			return new ResponseEntity<>(new Response<>(null, StatusEnum.SUCCESS, null), HttpStatus.OK);
		} catch (IllegalArgumentException e) {
			return new ResponseEntity<>(new Response<>(null, StatusEnum.FAILURE, e.getMessage()),
					HttpStatus.BAD_REQUEST);
		} catch (Exception e) {
			throw new DoctorServerException(e.getMessage(), e);
		}

	}

	@GetMapping("/resendOTPForEmail")
	public ResponseEntity<Response<String>> resendOTPForEmail(@RequestParam("emailid") String emailid) {
		try {
			doctorRegistrationService.resendOTPForEmail(emailid);
			return new ResponseEntity<>(new Response<>(null, StatusEnum.SUCCESS, null), HttpStatus.OK);
		} catch (IllegalArgumentException e) {
			return new ResponseEntity<>(new Response<>(" Doctor  Not Exit.", StatusEnum.FAILURE, e.getMessage()),
					HttpStatus.BAD_REQUEST);
		} catch (Exception e) {
			throw new DoctorServerException(e.getMessage(), e);
		}

	}

	@GetMapping("/verification/request/phone/otp")
	public ResponseEntity<Response<String>> requestOTPForPhoneVerification(
			@RequestParam("phonenumber") String phonenumber) {
		try {
			doctorRegistrationService.requestOTPForPhoneVerification(phonenumber);
			return new ResponseEntity<>(new Response<>(null, StatusEnum.SUCCESS, null), HttpStatus.OK);
		} catch (IllegalArgumentException e) {
			return new ResponseEntity<>(new Response<>(null, StatusEnum.FAILURE, e.getMessage()),
					HttpStatus.BAD_REQUEST);
		} catch (Exception e) {
			throw new DoctorServerException(e.getMessage(), e);
		}

	}

	@GetMapping("/emailVerification/emailotp")
	public ResponseEntity<Response<String>> emailVerification(@RequestParam("emailid") String emailid,
			@RequestParam("emailotp") Integer emailotp) {
		try {
			doctorRegistrationService.emailVerification(emailid, emailotp);
			return new ResponseEntity<>(new Response<>(null, StatusEnum.SUCCESS, "Your email ID is now verified"),
					HttpStatus.OK);
		} catch (IllegalArgumentException e) {
			return new ResponseEntity<>(new Response<>(null, StatusEnum.FAILURE, e.getMessage()),
					HttpStatus.BAD_REQUEST);
		} catch (Exception e) {
			throw new DoctorServerException(e.getMessage(), e);
		}

	}

	@PostMapping(path = "/password/forgotten")
	public ResponseEntity<Response<String>> forgotPassword(@RequestBody DoctorPasswordReguest doctorPasswordReguest) {

		try {
			doctorRegistrationService.forgotPassword(doctorPasswordReguest.getEmailId(),
					doctorPasswordReguest.getResetPwdLink());
			return new ResponseEntity<>(
					new Response<>(null, StatusEnum.SUCCESS, "Reset pwd link sent to your regirstered email."),
					HttpStatus.OK);
		} catch (IllegalArgumentException e) {
			return new ResponseEntity<>(new Response<>(null, StatusEnum.FAILURE, e.getMessage()),
					HttpStatus.BAD_REQUEST);
		} catch (Exception e) {
			throw new DoctorServerException(e.getMessage(), e);
		}

	}

	@PostMapping(path = "/password/change")
	public ResponseEntity<Response<String>> changePassword(@RequestBody DoctorPasswordReguest doctorPasswordReguest) {

		try {
			doctorRegistrationService.changePassword(doctorPasswordReguest.getEmailId(),
					doctorPasswordReguest.getNewPassword());
			return new ResponseEntity<>(new Response<>(null, StatusEnum.SUCCESS, "Your password changed successfully."),
					HttpStatus.OK);
		} catch (IllegalArgumentException e) {
			return new ResponseEntity<>(new Response<>(null, StatusEnum.FAILURE, e.getMessage()),
					HttpStatus.BAD_REQUEST);
		} catch (Exception e) {
			throw new DoctorServerException(e.getMessage(), e);
		}

	}

	@PostMapping(path = "/password/reset")
	public ResponseEntity<Response<String>> resetPassword(@RequestBody DoctorPasswordReguest doctorPasswordReguest) {

		try {
			doctorRegistrationService.resetPassword(doctorPasswordReguest.getEmailId(),
					doctorPasswordReguest.getNewPassword(), doctorPasswordReguest.getOtp(),
					doctorPasswordReguest.getType());
			return new ResponseEntity<>(new Response<>(null, StatusEnum.SUCCESS, null), HttpStatus.OK);
		} catch (IllegalArgumentException e) {
			return new ResponseEntity<>(new Response<>(null, StatusEnum.FAILURE, e.getMessage()),
					HttpStatus.BAD_REQUEST);
		} catch (Exception e) {
			throw new DoctorServerException(e.getMessage(), e);
		}

	}

	@PostMapping(path = "/password/otp/resend")
	public ResponseEntity<Response<String>> resendOTPForResetPassword(
			@RequestBody DoctorPasswordReguest doctorPasswordReguest) {

		try {
			doctorRegistrationService.resendOTPForResetPassword(doctorPasswordReguest.getEmailId(),
					doctorPasswordReguest.getResetPwdLink());
			return new ResponseEntity<>(new Response<>(null, StatusEnum.SUCCESS, null), HttpStatus.OK);
		} catch (IllegalArgumentException e) {
			return new ResponseEntity<>(new Response<>(null, StatusEnum.FAILURE, e.getMessage()),
					HttpStatus.BAD_REQUEST);
		} catch (Exception e) {
			throw new DoctorServerException(e.getMessage(), e);
		}

	}

	@GetMapping(path = "/id/{doctorId}")
	public ResponseEntity<Response<Doctor>> doctorById(@PathVariable("doctorId") Long doctorId) {
		try {
			Doctor doctor = doctorRegistrationService.getDoctorById(doctorId);
			return new ResponseEntity<>(new Response<>(doctor, StatusEnum.SUCCESS, null), HttpStatus.OK);
		} catch (IllegalArgumentException e) {
			return new ResponseEntity<>(new Response<>(null, StatusEnum.FAILURE, e.getMessage()),
					HttpStatus.BAD_REQUEST);
		} catch (Exception e) {
			throw new DoctorServerException(e.getMessage(), e);
		}

	}
	
	
	@GetMapping(path = "/get/doctor/byemailid")
	public ResponseEntity<Response<Doctor>> getDoctorByEmailId(@RequestParam("emailId") String emailId) {
		try {
			Doctor doctor = doctorRegistrationService.getDoctorByEmailId(emailId);
			return new ResponseEntity<>(new Response<>(doctor, StatusEnum.SUCCESS, null), HttpStatus.OK);
		} catch (IllegalArgumentException e) {
			return new ResponseEntity<>(new Response<>(null, StatusEnum.FAILURE, e.getMessage()),
					HttpStatus.BAD_REQUEST);
		} catch (Exception e) {
			throw new DoctorServerException(e.getMessage(), e);
		}

	}

	@GetMapping(path = "/verify/email/")
	public ResponseEntity<Response<Boolean>> verifyEmailIdExists(@RequestParam("emailId") String emailId) {
		try {
			Boolean isExist = doctorRegistrationService.verifyEmailIdExists(emailId);
			String message = (isExist) ? "EmailId already exist." : " EmailId not exist.";
			return new ResponseEntity<>(new Response<>(isExist, StatusEnum.SUCCESS, message), HttpStatus.OK);
		} catch (IllegalArgumentException e) {
			return new ResponseEntity<>(new Response<>(null, StatusEnum.FAILURE, e.getMessage()),
					HttpStatus.BAD_REQUEST);
		} catch (Exception e) {
			throw new DoctorServerException(e.getMessage(), e);
		}

	}

	@GetMapping(path = "/doctorNameExists")
	public ResponseEntity<Response<Boolean>> doctorNameExists(@RequestParam("doctorName") String doctorName) {

		try {
			Boolean isExist = doctorRegistrationService.userNameExists(doctorName);
			String message = (isExist) ? "UserName already exist." : " UserName not exist.";
			return new ResponseEntity<>(new Response<>(isExist, StatusEnum.SUCCESS, message), HttpStatus.OK);
		} catch (IllegalArgumentException e) {
			return new ResponseEntity<>(new Response<>(null, StatusEnum.FAILURE, e.getMessage()),
					HttpStatus.BAD_REQUEST);
		} catch (Exception e) {
			throw new DoctorServerException(e.getMessage(), e);
		}

	}

	@GetMapping(path = "/verifyphoneNumberExists")
	public ResponseEntity<Response<Boolean>> verifyPhoneNumberExists(@RequestParam("celnumber") String celnumber) {
		try {
			Boolean isExist = doctorRegistrationService.verifyPhoneNumberExists(celnumber);
			String message = (isExist) ? "Celnumber already exist." : " Celnumber not exist.";
			return new ResponseEntity<>(new Response<>(isExist, StatusEnum.SUCCESS, message), HttpStatus.OK);
		} catch (IllegalArgumentException e) {
			return new ResponseEntity<>(new Response<>(null, StatusEnum.FAILURE, e.getMessage()),
					HttpStatus.BAD_REQUEST);
		} catch (Exception e) {
			throw new DoctorServerException(e.getMessage(), e);
		}

	}

	@DeleteMapping(path = "/id/{doctorid}")
	public ResponseEntity<Response<String>> deleteDoctor(@PathVariable Long doctorId) {
		try {
			doctorRegistrationService.deletDoctor(doctorId);
			return new ResponseEntity<>(new Response<>(null, StatusEnum.SUCCESS, null), HttpStatus.OK);
		} catch (IllegalArgumentException e) {
			return new ResponseEntity<>(new Response<>(null, StatusEnum.FAILURE, e.getMessage()),
					HttpStatus.BAD_REQUEST);
		} catch (Exception e) {
			throw new DoctorServerException(e.getMessage(), e);
		}

	}

	@PutMapping("/update")
	public ResponseEntity<Response<Doctor>> updateDoctorDetails(@RequestBody Doctor doctor) {
		try {
			doctorRegistrationService.updateDoctorDetails(doctor, null);
			return new ResponseEntity<>(
					new Response<>(doctor, StatusEnum.SUCCESS, "doctor details updated successfully"), HttpStatus.OK);
		} catch (IllegalArgumentException e) {
			return new ResponseEntity<>(new Response<>(null, StatusEnum.FAILURE, e.getMessage()),
					HttpStatus.BAD_REQUEST);
		} catch (Exception e) {
			throw new DoctorServerException(e.getMessage(), e);
		}

	}

	@GetMapping("/approve/{doctorId}")
	public ResponseEntity<Response<Boolean>> approveDoctor(@PathVariable("doctorId") Long doctorId) {
		try {
			return new ResponseEntity<>(
					new Response<>(doctorRegistrationService.approveDoctor(doctorId), StatusEnum.SUCCESS, null),
					HttpStatus.OK);
		} catch (IllegalArgumentException e) {
			return new ResponseEntity<>(new Response<>(null, StatusEnum.FAILURE, e.getMessage()),
					HttpStatus.BAD_REQUEST);
		} catch (Exception e) {
			throw new DoctorServerException(e.getMessage(), e);
		}

	}

	@ApiOperation(value = "/all/flags", notes = "This method is used to get all doctors")
	@PostMapping("/all/flags")
	public ResponseEntity<Response<Map<DoctorFlag, List<Doctor>>>> fetchDoctorsByFlagWithPagination(
			@RequestBody DoctorFlagsRequest doctorFlagsRequest) {
		try {
			Map<DoctorFlag, List<Doctor>> doctors = doctorRegistrationService
					.fetchDoctorsByFlagWithPagination(doctorFlagsRequest);
			return new ResponseEntity<>(new Response<>(doctors, StatusEnum.SUCCESS, null), HttpStatus.OK);
		} catch (IllegalArgumentException e) {
			return new ResponseEntity<>(new Response<>(null, StatusEnum.FAILURE, e.getMessage()),
					HttpStatus.BAD_REQUEST);
		} catch (Exception e) {
			throw new DoctorServerException(e.getMessage(), e);
		}

	}

	@PostMapping("/doctorProfileCompleted/{doctoId}")
	public ResponseEntity<Response<ProfileCompletedDTO>> doctorProfileCompleted(@PathVariable Long doctoId) {
		try {
			ProfileCompletedDTO bolen = doctorRegistrationService.doctorProfileCompleted(doctoId);
			return new ResponseEntity<>(new Response<>(bolen, StatusEnum.SUCCESS, null), HttpStatus.OK);
		} catch (IllegalArgumentException e) {
			return new ResponseEntity<>(new Response<>(null, StatusEnum.FAILURE, e.getMessage()),
					HttpStatus.BAD_REQUEST);
		} catch (Exception e) {
			throw new DoctorServerException(e.getMessage(), e);
		}

	}

	@PostMapping("/otpVerificationForEmail")
	public ResponseEntity<Response<String>> otpVerificationForEmail(
			@RequestBody OtpVerificationDTO otpVerificationDTO) {
		try {
			String value = doctorRegistrationService.otpVerificationForEmail(otpVerificationDTO.getOtp(),
					otpVerificationDTO.getEmailId(), otpVerificationDTO.getType());
			return new ResponseEntity<>(new Response<>(value, StatusEnum.SUCCESS, value), HttpStatus.OK);
		} catch (IllegalArgumentException e) {
			return new ResponseEntity<>(new Response<>(null, StatusEnum.FAILURE, e.getMessage()),
					HttpStatus.BAD_REQUEST);
		} catch (Exception e) {
			throw new DoctorServerException(e.getMessage(), e);
		}

	}

	@GetMapping("/oldPaswwordVerification")
	public ResponseEntity<Response<Boolean>> oldPaswwordVerification(@RequestParam String emailid,
			@RequestParam String paswword) {
		try {
			Boolean value = doctorRegistrationService.oldPaswwordVerification(emailid, paswword);
			return new ResponseEntity<>(new Response<>(value, StatusEnum.SUCCESS, null), HttpStatus.OK);
		} catch (IllegalArgumentException e) {
			return new ResponseEntity<>(new Response<>(null, StatusEnum.FAILURE, e.getMessage()),
					HttpStatus.BAD_REQUEST);
		} catch (Exception e) {
			throw new DoctorServerException(e.getMessage(), e);
		}

	}

	@GetMapping("/otpVerificationForPhone")
	public ResponseEntity<Response<String>> otpVerificationForPhone(@RequestParam Integer phoneotp) {
		try {
			String value = doctorRegistrationService.otpVerificationForPhone(phoneotp);
			return new ResponseEntity<>(new Response<>(value, StatusEnum.SUCCESS, value), HttpStatus.OK);
		} catch (IllegalArgumentException e) {
			return new ResponseEntity<>(new Response<>(null, StatusEnum.FAILURE, e.getMessage()),
					HttpStatus.BAD_REQUEST);
		} catch (Exception e) {
			throw new DoctorServerException(e.getMessage(), e);
		}

	}
}
