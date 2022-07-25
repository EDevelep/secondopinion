package org.secondopinion.caretaker.controller;

import java.util.Collection;
import java.util.List;

import org.secondopinion.caretaker.dto.Address;
import org.secondopinion.caretaker.dto.CareTakerSearchRequest;
import org.secondopinion.caretaker.dto.Caretaker;
import org.secondopinion.caretaker.dto.Certification;
import org.secondopinion.caretaker.dto.Feedetails;
import org.secondopinion.caretaker.dto.Personaldetail;
import org.secondopinion.caretaker.dto.ProfileCompletedDTO;
import org.secondopinion.caretaker.exception.CareTakerServerException;
import org.secondopinion.caretaker.service.CareTakerService;
import org.secondopinion.request.Response;
import org.secondopinion.request.Response.StatusEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CareTakerRegistrationController {
	@Autowired
	private CareTakerService careTakerService;

	@PostMapping(value = "/register")
	public ResponseEntity<Response<String>> registerCareTaker(@RequestBody Caretaker caretaker) {
		try {
			careTakerService.registerCareTaker(caretaker);
			return new ResponseEntity<>(new Response<>("Caretaker Registered SuccessFully", StatusEnum.SUCCESS, null),
					HttpStatus.OK);
		} catch (IllegalArgumentException e) {
			return new ResponseEntity<>(new Response<>(null, StatusEnum.FAILURE, e.getMessage()),
					HttpStatus.BAD_REQUEST);
		} catch (Exception e) {
			throw new CareTakerServerException(e.getMessage(), e);
		}

	}

	@PostMapping(value = "/saveFeeDetail")
	public ResponseEntity<Response<String>> saveFeeDetail(@RequestBody Feedetails feedetails) {
		try {
			careTakerService.saveFeeDetail(feedetails);
			return new ResponseEntity<>(new Response<>("Feedetails Registered SuccessFully", StatusEnum.SUCCESS, null),
					HttpStatus.OK);
		} catch (IllegalArgumentException e) {
			return new ResponseEntity<>(new Response<>(null, StatusEnum.FAILURE, e.getMessage()),
					HttpStatus.BAD_REQUEST);
		} catch (Exception e) {
			throw new CareTakerServerException(e.getMessage(), e);
		}

	}

	@GetMapping(value = "/getCareTakerFeeDetails")
	public ResponseEntity<Response<Collection<Feedetails>>> getCareTakerFeeDetails(@RequestParam Long caretakerId) {
		try {
			Collection<Feedetails> feedetails = careTakerService.getCareTakerFeeDetails(caretakerId);
			return new ResponseEntity<>(new Response<>(feedetails, StatusEnum.SUCCESS, "Feedetails Get SuccessFully"),
					HttpStatus.OK);
		} catch (IllegalArgumentException e) {
			return new ResponseEntity<>(new Response<>(null, StatusEnum.FAILURE, e.getMessage()),
					HttpStatus.BAD_REQUEST);
		} catch (Exception e) {
			throw new CareTakerServerException(e.getMessage(), e);
		}

	}
	
	
	@GetMapping(value = "/getCaretakerDetails")
	public ResponseEntity<Response<Caretaker>> getCaretakerDetails(@RequestParam String emailId) {
		try {
			Caretaker caretaker = careTakerService.getCaretakerDetails(emailId);
			return new ResponseEntity<>(new Response<>(caretaker, StatusEnum.SUCCESS, "Caretaker Get SuccessFully"),
					HttpStatus.OK);
		} catch (IllegalArgumentException e) {
			return new ResponseEntity<>(new Response<>(null, StatusEnum.FAILURE, e.getMessage()),
					HttpStatus.BAD_REQUEST);
		} catch (Exception e) {
			throw new CareTakerServerException(e.getMessage(), e);
		}

	}


	@GetMapping(value = "/getCaretakerCertifications")
	public ResponseEntity<Response<Collection<Certification>>> getCaretakerCertifications(
			@RequestParam Long caretakerId) {
		try {
			Collection<Certification> certifications = careTakerService.getCaretakerCertifications(caretakerId);
			return new ResponseEntity<>(
					new Response<>(certifications, StatusEnum.SUCCESS, "Certification Get SuccessFully"),
					HttpStatus.OK);
		} catch (IllegalArgumentException e) {
			return new ResponseEntity<>(new Response<>(null, StatusEnum.FAILURE, e.getMessage()),
					HttpStatus.BAD_REQUEST);
		} catch (Exception e) {
			throw new CareTakerServerException(e.getMessage(), e);
		}

	}

	@PostMapping(value = "/saveCertification")
	public ResponseEntity<Response<String>> saveCertification(@RequestBody Certification certification) {
		try {
			careTakerService.saveCertification(certification);
			return new ResponseEntity<>(
					new Response<>("Certification Registered SuccessFully", StatusEnum.SUCCESS, null), HttpStatus.OK);
		} catch (IllegalArgumentException e) {
			return new ResponseEntity<>(new Response<>(null, StatusEnum.FAILURE, e.getMessage()),
					HttpStatus.BAD_REQUEST);
		} catch (Exception e) {
			throw new CareTakerServerException(e.getMessage(), e);
		}

	}

	@GetMapping("/email/verification")
	public ResponseEntity<Response<String>> emailVerification(@RequestParam("emailid") String emailid,
			@RequestParam("emailotp") Integer emailotp) {
		try {
			careTakerService.emailVerification(emailid, emailotp);
			return new ResponseEntity<>(new Response<>(null, StatusEnum.SUCCESS, "Your email ID is now verified"),
					HttpStatus.OK);
		} catch (IllegalArgumentException e) {
			return new ResponseEntity<>(new Response<>(null, StatusEnum.FAILURE, e.getMessage()),
					HttpStatus.BAD_REQUEST);
		} catch (Exception e) {
			throw new CareTakerServerException(e.getMessage(), e);
		}

	}

	@GetMapping("/isProfileCompleted")
	public ResponseEntity<Response<ProfileCompletedDTO>> isProfileCompleted(@RequestParam Long caretakerId) {
		try {
			ProfileCompletedDTO value = careTakerService.isProfileCompleted(caretakerId);
			return new ResponseEntity<>(new Response<>(value, StatusEnum.SUCCESS, null), HttpStatus.OK);
		} catch (IllegalArgumentException e) {
			return new ResponseEntity<>(new Response<>(null, StatusEnum.FAILURE, e.getMessage()),
					HttpStatus.BAD_REQUEST);
		} catch (Exception e) {
			throw new CareTakerServerException(e.getMessage(), e);
		}

	}

	@GetMapping("/iscareTakerNameExit")
	public ResponseEntity<Response<Boolean>> iscareTakerNameExit(@RequestParam String userName) {
		try {

			Boolean isExist = careTakerService.iscareTakerNameExit(userName);
			String message = (isExist) ? "UserName already exist." : " UserName not exist.";
			return new ResponseEntity<>(new Response<>(isExist, StatusEnum.SUCCESS, message), HttpStatus.OK);
		} catch (IllegalArgumentException e) {
			return new ResponseEntity<>(new Response<>(null, StatusEnum.FAILURE, e.getMessage()),
					HttpStatus.BAD_REQUEST);
		} catch (Exception e) {
			throw new CareTakerServerException(e.getMessage(), e);
		}

	}

	@GetMapping("/emailOtpVerification")
	public ResponseEntity<Response<String>> emailOtpVerification(@RequestParam("emailid") String emailid,
			@RequestParam("emailotp") Integer emailotp) {
		try {
			String value = careTakerService.emailOtpVerification(emailid, emailotp);
			return new ResponseEntity<>(new Response<>(value, StatusEnum.SUCCESS, value), HttpStatus.OK);
		} catch (IllegalArgumentException e) {
			return new ResponseEntity<>(new Response<>(null, StatusEnum.FAILURE, e.getMessage()),
					HttpStatus.BAD_REQUEST);
		} catch (Exception e) {
			throw new CareTakerServerException(e.getMessage(), e);
		}

	}

	@GetMapping("/phone/verification")
	public ResponseEntity<Response<String>> phoneVerification(@RequestParam("cellNumber") String cellNumber,
			@RequestParam("otp") Integer otp) {
		try {
			careTakerService.phoneVerification(cellNumber, otp);
			return new ResponseEntity<>(new Response<>("Your Phone number is now verified", StatusEnum.SUCCESS,
					"Your Phone number is now verified"), HttpStatus.OK);
		} catch (IllegalArgumentException e) {
			return new ResponseEntity<>(new Response<>(null, StatusEnum.FAILURE, e.getMessage()),
					HttpStatus.BAD_REQUEST);
		} catch (Exception e) {
			throw new CareTakerServerException(e.getMessage(), e);
		}

	}

	@GetMapping("/resendOTP/forphone")
	public ResponseEntity<Response<String>> resendOTPForPhone(@RequestParam("cellNumber") String cellNumber) {
		try {
			careTakerService.resendOTPForPhone(cellNumber);
			return new ResponseEntity<>(
					new Response<>("OTP is send SuccessFully ", StatusEnum.SUCCESS, "OTP is send SuccessFully "),
					HttpStatus.OK);
		} catch (IllegalArgumentException e) {
			return new ResponseEntity<>(new Response<>(null, StatusEnum.FAILURE, e.getMessage()),
					HttpStatus.BAD_REQUEST);
		} catch (Exception e) {
			throw new CareTakerServerException(e.getMessage(), e);
		}

	}

	@GetMapping("/resendOTP/foremail")
	public ResponseEntity<Response<String>> resendOTPForEmail(@RequestParam("emailId") String emailId) {
		try {
			careTakerService.resendOTPForEmail(emailId);
			return new ResponseEntity<>(
					new Response<>("OTP is send successFully ", StatusEnum.SUCCESS, "OTP is send successFully "),
					HttpStatus.OK);
		} catch (IllegalArgumentException e) {
			return new ResponseEntity<>(new Response<>(null, StatusEnum.FAILURE, e.getMessage()),
					HttpStatus.BAD_REQUEST);
		} catch (Exception e) {
			throw new CareTakerServerException(e.getMessage(), e);
		}

	}

	@GetMapping("/verify/emailId/exists")
	public ResponseEntity<Response<Boolean>> verifyEmailIdExists(@RequestParam("emailid") String emailId) {
		try {
			boolean verfiy = careTakerService.verifyEmailIdExists(emailId);
			return new ResponseEntity<>(new Response<>(verfiy, StatusEnum.SUCCESS, " "), HttpStatus.OK);
		} catch (IllegalArgumentException e) {
			return new ResponseEntity<>(new Response<>(null, StatusEnum.FAILURE, e.getMessage()),
					HttpStatus.BAD_REQUEST);
		} catch (Exception e) {
			throw new CareTakerServerException(e.getMessage(), e);
		}

	}

	@GetMapping("/reset/password")
	public ResponseEntity<Response<String>> resetPassword(@RequestParam("emailid") String emailId,
			@RequestParam("otp") Integer otp, @RequestParam("newPassword") String newPassword) {
		try {
			careTakerService.resetPassword(emailId, newPassword, otp);
			return new ResponseEntity<>(new Response<>("Password is change successFully ", StatusEnum.SUCCESS, " "),
					HttpStatus.OK);
		} catch (IllegalArgumentException e) {
			return new ResponseEntity<>(new Response<>(null, StatusEnum.FAILURE, e.getMessage()),
					HttpStatus.BAD_REQUEST);
		} catch (Exception e) {
			throw new CareTakerServerException(e.getMessage(), e);
		}

	}

	@PostMapping(value = "/save/address")
	public ResponseEntity<Response<String>> saveAddress(@RequestBody Address address) {
		try {
			careTakerService.saveAddress(address);
			return new ResponseEntity<>(new Response<>("Address save SuccessFully", StatusEnum.SUCCESS, null),
					HttpStatus.OK);
		} catch (IllegalArgumentException e) {
			return new ResponseEntity<>(new Response<>(null, StatusEnum.FAILURE, e.getMessage()),
					HttpStatus.BAD_REQUEST);
		} catch (Exception e) {
			throw new CareTakerServerException(e.getMessage(), e);
		}

	}

	@PostMapping(value = "/save/personaldetail")
	public ResponseEntity<Response<String>> savePersonaldetail(@RequestBody Personaldetail personaldetail) {
		try {
			careTakerService.savePersonaldetail(personaldetail);
			return new ResponseEntity<>(new Response<>("Personaldetail save SuccessFully", StatusEnum.SUCCESS, null),
					HttpStatus.OK);
		} catch (IllegalArgumentException e) {
			return new ResponseEntity<>(new Response<>(null, StatusEnum.FAILURE, e.getMessage()),
					HttpStatus.BAD_REQUEST);
		} catch (Exception e) {
			throw new CareTakerServerException(e.getMessage(), e);
		}

	}

	@GetMapping(value = "/address/By/caretakerId")
	public ResponseEntity<Response<Collection<Address>>> findCaretakerAddressBycaretakerId(
			@RequestParam Long caretakerId) {
		try {
			Collection<Address> address = careTakerService.findCaretakerAddressBycaretakerId(caretakerId);
			return new ResponseEntity<>(new Response<>(address, StatusEnum.SUCCESS, null), HttpStatus.OK);
		} catch (IllegalArgumentException e) {
			return new ResponseEntity<>(new Response<>(null, StatusEnum.FAILURE, e.getMessage()),
					HttpStatus.BAD_REQUEST);
		} catch (Exception e) {
			throw new CareTakerServerException(e.getMessage(), e);
		}

	}

	@GetMapping(value = "/address")
	public ResponseEntity<Response<Address>> getAddress(@RequestParam Long caretakerId) {
		try {
			Address address = careTakerService.getAddress(caretakerId);
			return new ResponseEntity<>(new Response<>(address, StatusEnum.SUCCESS, null), HttpStatus.OK);
		} catch (IllegalArgumentException e) {
			return new ResponseEntity<>(new Response<>(null, StatusEnum.FAILURE, e.getMessage()),
					HttpStatus.BAD_REQUEST);
		} catch (Exception e) {
			throw new CareTakerServerException(e.getMessage(), e);
		}

	}

	@GetMapping(value = "/get/personaldetail")
	public ResponseEntity<Response<Personaldetail>> getPersonaldetail(@RequestParam Long caretakerId) {
		try {
			Personaldetail personaldetail = careTakerService.getPersonaldetail(caretakerId);
			return new ResponseEntity<>(new Response<>(personaldetail, StatusEnum.SUCCESS, null), HttpStatus.OK);
		} catch (IllegalArgumentException e) {
			return new ResponseEntity<>(new Response<>(null, StatusEnum.FAILURE, e.getMessage()),
					HttpStatus.BAD_REQUEST);
		} catch (Exception e) {
			throw new CareTakerServerException(e.getMessage(), e);
		}

	}

	@GetMapping(value = "/get/getFeeDetail")
	public ResponseEntity<Response<Feedetails>> getFeeDetail(@RequestParam Long caretakerId) {
		try {
			Feedetails feedetails = careTakerService.getFeeDetailsById(caretakerId);
			return new ResponseEntity<>(new Response<>(feedetails, StatusEnum.SUCCESS, null), HttpStatus.OK);
		} catch (IllegalArgumentException e) {
			return new ResponseEntity<>(new Response<>(null, StatusEnum.FAILURE, e.getMessage()),
					HttpStatus.BAD_REQUEST);
		} catch (Exception e) {
			throw new CareTakerServerException(e.getMessage(), e);
		}

	}

	@GetMapping(value = "/get/caretaker")
	public ResponseEntity<Response<Caretaker>> getCaretaker(@RequestParam Long caretakerId) {
		try {
			Caretaker caretaker = careTakerService.getCaretaker(caretakerId);
			return new ResponseEntity<>(new Response<>(caretaker, StatusEnum.SUCCESS, null), HttpStatus.OK);
		} catch (IllegalArgumentException e) {
			return new ResponseEntity<>(new Response<>(null, StatusEnum.FAILURE, e.getMessage()),
					HttpStatus.BAD_REQUEST);
		} catch (Exception e) {
			throw new CareTakerServerException(e.getMessage(), e);
		}

	}

	@PostMapping(value = "/updateCaretaker")
	public ResponseEntity<Response<String>> updateCaretaker(@RequestBody Caretaker caretaker,
			@RequestParam Long caretakerId) {
		try {
			careTakerService.updateCaretaker(caretakerId, caretaker);
			return new ResponseEntity<>(new Response<>("Caretaker Update SuccessFully", StatusEnum.SUCCESS, null),
					HttpStatus.OK);
		} catch (IllegalArgumentException e) {
			return new ResponseEntity<>(new Response<>(null, StatusEnum.FAILURE, e.getMessage()),
					HttpStatus.BAD_REQUEST);
		} catch (Exception e) {
			throw new CareTakerServerException(e.getMessage(), e);
		}

	}

	@PostMapping(value = "/findAllCaretakerSearchRequest")
	public ResponseEntity<Response<List<Caretaker>>> findAllCaretakerSearchRequest(
			@RequestBody CareTakerSearchRequest careTakerSearchRequest) {
		try {
			List<Caretaker> caretaker = careTakerService.findAllCaretaker(careTakerSearchRequest);
			caretaker.stream().forEach(n -> n.setPassword(""));
			return new ResponseEntity<>(new Response<>(caretaker, StatusEnum.SUCCESS, null), HttpStatus.OK);
		} catch (IllegalArgumentException e) {
			return new ResponseEntity<>(new Response<>(null, StatusEnum.FAILURE, e.getMessage()),
					HttpStatus.BAD_REQUEST);
		} catch (Exception e) {
			throw new CareTakerServerException(e.getMessage(), e);
		}

	}
	
	@PostMapping(value = "/findAllCaretaker")
	public ResponseEntity<Response<Collection<Caretaker>>> findAllCaretaker() {
		try {
			Collection<Caretaker> caretaker = careTakerService.findAllCaretaker();
			caretaker.stream().forEach(n -> n.setPassword(""));
			return new ResponseEntity<>(new Response<>(caretaker, StatusEnum.SUCCESS, null), HttpStatus.OK);
		} catch (IllegalArgumentException e) {
			return new ResponseEntity<>(new Response<>(null, StatusEnum.FAILURE, e.getMessage()),
					HttpStatus.BAD_REQUEST);
		} catch (Exception e) {
			throw new CareTakerServerException(e.getMessage(), e);
		}

	}
}
