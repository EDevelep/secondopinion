package org.secondopinion.pharmacy.controller;

import java.util.Collection;
import java.util.List;
import org.secondopinion.pharmacy.dto.Personaldetail;
import org.secondopinion.pharmacy.dto.Pharmacy;
import org.secondopinion.pharmacy.dto.PharmacySearchRequest;
import org.secondopinion.pharmacy.dto.Pharmacyaddress;
import org.secondopinion.pharmacy.dto.ProfileCompltedDTO;
import org.secondopinion.pharmacy.exception.PharmacyServerException;
import org.secondopinion.pharmacy.service.IPharmacyService;
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
import org.springframework.web.multipart.MultipartFile;

@RestController
public class PharmacyController {

	@Autowired
	private IPharmacyService pharmacyService;

	@PostMapping(value = "/register")
	public ResponseEntity<Response<String>> registerNewPharmacy(@RequestBody Pharmacy pharmacy) {
		try {
			pharmacyService.registerPharmacy(pharmacy);
			return new ResponseEntity<>(new Response<>(null, StatusEnum.SUCCESS, "Pharmacy data saved successfully"),
					HttpStatus.OK);
		} catch (IllegalArgumentException e) {
			return new ResponseEntity<>(new Response<>(null, StatusEnum.FAILURE, e.getMessage()),
					HttpStatus.BAD_REQUEST);
		} catch (Exception e) {
			throw new PharmacyServerException(e.getMessage(), e);
		}
	}

	@GetMapping(value = "/all")
	public ResponseEntity<Response<Collection<Pharmacy>>> fetchAllPharmacies() {
		try {
			return new ResponseEntity<>(new Response<Collection<Pharmacy>>(pharmacyService.fetchAllPharmacies(),
					StatusEnum.SUCCESS, "List of Pharmacies fetched successfully."), HttpStatus.OK);
		} catch (IllegalArgumentException e) {
			return new ResponseEntity<>(new Response<Collection<Pharmacy>>(null, StatusEnum.FAILURE, e.getMessage()),
					HttpStatus.BAD_REQUEST);
		} catch (Exception e) {
			throw new PharmacyServerException(e.getMessage(), e);
		}
	}

	@GetMapping(value = "/id/{pharmacyId}")
	public ResponseEntity<Response<Pharmacy>> fetchByPharmacyId(@PathVariable("pharmacyId") Long pharmacyId) {
		try {
			return new ResponseEntity<>(new Response<Pharmacy>(pharmacyService.fetchByPharmacyId(pharmacyId),
					StatusEnum.SUCCESS, "Pharmacy data fetched successfully."), HttpStatus.OK);
		} catch (IllegalArgumentException e) {
			return new ResponseEntity<>(new Response<Pharmacy>(null, StatusEnum.FAILURE, e.getMessage()),
					HttpStatus.BAD_REQUEST);
		} catch (Exception e) {
			throw new PharmacyServerException(e.getMessage(), e);
		}
	}

	@GetMapping(value = "/license/{licenseId}")
	public ResponseEntity<Response<Pharmacy>> fetchPharmacyByLicenseId(@PathVariable("licenseId") String licenseId) {
		try {
			return new ResponseEntity<>(new Response<Pharmacy>(pharmacyService.fetchPharmacyByLicenseNumber(licenseId),
					StatusEnum.SUCCESS, "Pharmacy data fetched successfully."), HttpStatus.OK);
		} catch (IllegalArgumentException e) {
			return new ResponseEntity<>(new Response<Pharmacy>(null, StatusEnum.FAILURE, e.getMessage()),
					HttpStatus.BAD_REQUEST);
		} catch (Exception e) {
			throw new PharmacyServerException(e.getMessage(), e);
		}
	}

	// email id exist or not
	@GetMapping(value = "/validate/email")
	public ResponseEntity<Response<Boolean>> isEmailExistOrNot(@RequestParam("email") String email) {
		try {
			Boolean isExist = pharmacyService.isEmailExistOrNot(email);
			String message = (isExist) ? "Email already exist." : " Email not exist.";
			return new ResponseEntity<>(
					new Response<Boolean>(isExist, StatusEnum.SUCCESS, message),
					HttpStatus.OK);
		} catch (IllegalArgumentException e) {
			return new ResponseEntity<>(new Response<Boolean>(null, StatusEnum.FAILURE, e.getMessage()),
					HttpStatus.BAD_REQUEST);
		} catch (Exception e) {
			throw new PharmacyServerException(e.getMessage(), e);
		}
	}
	
	@GetMapping(value = "/validate/userName")
	public ResponseEntity<Response<Boolean>> isUserExistOrNot(@RequestParam("userName") String userName) {
		try {
			Boolean isExist = pharmacyService.isUserExistOrNot(userName);
			String message = (isExist) ? "User already exist." : " User not exist.";
			return new ResponseEntity<>(
					new Response<Boolean>(isExist, StatusEnum.SUCCESS, message),
					HttpStatus.OK);
		} catch (IllegalArgumentException e) {
			return new ResponseEntity<>(new Response<Boolean>(null, StatusEnum.FAILURE, e.getMessage()),
					HttpStatus.BAD_REQUEST);
		} catch (Exception e) {
			throw new PharmacyServerException(e.getMessage(), e);
		}
		
	}

	@GetMapping(value = "/validate/phone")
	public ResponseEntity<Response<Boolean>> isphoneExistOrNot(@RequestParam("phone") String phone) {
		try {
			Boolean isExist = pharmacyService.isphoneExistOrNot(phone);
			String message = (isExist) ? "PhoneNumber already exist." : " PhoneNumber not exist.";
			return new ResponseEntity<>(
					new Response<Boolean>(isExist, StatusEnum.SUCCESS, message),
					HttpStatus.OK);
		} catch (IllegalArgumentException e) {
			return new ResponseEntity<>(new Response<Boolean>(null, StatusEnum.FAILURE, e.getMessage()),
					HttpStatus.BAD_REQUEST);
		} catch (Exception e) {
			throw new PharmacyServerException(e.getMessage(), e);
		}
	}

	@PostMapping(value = "/address")
	public ResponseEntity<Response<String>> saveAddressesOfPharmacy(@RequestBody Pharmacyaddress address) {
		try {
			pharmacyService.saveAddressesOfPharmacy(address);
			return new ResponseEntity<>(new Response<>(null, StatusEnum.SUCCESS, "address saved successfully"),
					HttpStatus.OK);
		} catch (IllegalArgumentException e) {
			return new ResponseEntity<>(new Response<>(null, StatusEnum.FAILURE, e.getMessage()),
					HttpStatus.BAD_REQUEST);
		} catch (Exception e) {
			throw new PharmacyServerException(e.getMessage(), e);
		}
	}

	@PostMapping(value = "/save/personaldetail")
	public ResponseEntity<Response<String>> savePersonaldetail(@RequestBody Personaldetail personaldetail) {
		try {
			pharmacyService.savePersonaldetail(personaldetail);
			return new ResponseEntity<>(new Response<>(null, StatusEnum.SUCCESS, "address saved successfully"),
					HttpStatus.OK);
		} catch (IllegalArgumentException e) {
			return new ResponseEntity<>(new Response<>(null, StatusEnum.FAILURE, e.getMessage()),
					HttpStatus.BAD_REQUEST);
		} catch (Exception e) {
			throw new PharmacyServerException(e.getMessage(), e);
		}
	}

	@PostMapping(value = "/get/personaldetail")
	public ResponseEntity<Response<Personaldetail>> getPersonaldetail(@RequestParam Long pharmacyuserId) {
		try {
			Personaldetail personaldetail = pharmacyService.getPersonaldetail(pharmacyuserId);
			return new ResponseEntity<>(
					new Response<>(personaldetail, StatusEnum.SUCCESS, "address saved successfully"), HttpStatus.OK);
		} catch (IllegalArgumentException e) {
			return new ResponseEntity<>(new Response<>(null, StatusEnum.FAILURE, e.getMessage()),
					HttpStatus.BAD_REQUEST);
		} catch (Exception e) {
			throw new PharmacyServerException(e.getMessage(), e);
		}
	}

	@PostMapping(value = "/getAssoctedPharmcy")
	public ResponseEntity<Response<List<Pharmacy>>> getAssoctedPharmcy(@RequestParam Long pharmacyadminId) {
		try {
			List<Pharmacy> pharmacies = pharmacyService.getAssoctedPharmcy(pharmacyadminId);
			return new ResponseEntity<>(
					new Response<>(pharmacies, StatusEnum.SUCCESS, "Assocted Pharmcy Get successfully"), HttpStatus.OK);
		} catch (IllegalArgumentException e) {
			return new ResponseEntity<>(new Response<>(null, StatusEnum.FAILURE, e.getMessage()),
					HttpStatus.BAD_REQUEST);
		} catch (Exception e) {
			throw new PharmacyServerException(e.getMessage(), e);
		}
	}

	@PutMapping(value = "/address/update")
	public ResponseEntity<Response<String>> updateAddressesOfPharmacy(@RequestBody Pharmacyaddress address) {
		try {
			pharmacyService.updateAddressesOfPharmacy(address);
			return new ResponseEntity<>(new Response<>(null, StatusEnum.SUCCESS, "address saved successfully"),
					HttpStatus.OK);
		} catch (IllegalArgumentException e) {
			return new ResponseEntity<>(new Response<>(null, StatusEnum.FAILURE, e.getMessage()),
					HttpStatus.BAD_REQUEST);
		} catch (Exception e) {
			throw new PharmacyServerException(e.getMessage(), e);
		}
	}

	@GetMapping(value = "/address/{pharmacyId}/{pageNum}/{maxResults}")
	public ResponseEntity<Response<List<Pharmacyaddress>>> getAddressesOfPharmacy(@PathVariable Long pharmacyId,
			@PathVariable Integer pageNum, @PathVariable Integer maxResults) {
		try {
			Response<List<Pharmacyaddress>> addresses = pharmacyService.getAddressesOfPharmacy(pharmacyId, pageNum,
					maxResults);
			return new ResponseEntity<>(addresses, HttpStatus.OK);
		} catch (IllegalArgumentException e) {
			return new ResponseEntity<>(new Response<>(null, StatusEnum.FAILURE, e.getMessage()),
					HttpStatus.BAD_REQUEST);
		} catch (Exception e) {
			throw new PharmacyServerException(e.getMessage(), e);
		}
	}

	@PostMapping(value = "/searchpharmacy")
	public ResponseEntity<Response<List<Pharmacy>>> getAllPharmacyBySearchCritieria(
			@RequestBody PharmacySearchRequest pharmacySearchRequest) {
		try {
			Response<List<Pharmacy>> pharmacy = pharmacyService.getAllPharmacyBySearchCritieria(pharmacySearchRequest);
			return new ResponseEntity<>(pharmacy, HttpStatus.OK);
		} catch (IllegalArgumentException e) {
			return new ResponseEntity<>(new Response<>(null, StatusEnum.FAILURE, e.getMessage()),
					HttpStatus.BAD_REQUEST);
		} catch (Exception e) {
			throw new PharmacyServerException(e.getMessage(), e);
		}
	}

	@GetMapping("/verification/phone")
	public ResponseEntity<Response<String>> phoneverification(@RequestParam("phonenumber") String phonenumber,
			@RequestParam("otp") Integer otp) {
		try {
			pharmacyService.phoneverification(phonenumber, otp);
			return new ResponseEntity<>(new Response<>("Phone Verification Done.", StatusEnum.SUCCESS, "Phone Verification Done."), HttpStatus.OK);
		} catch (IllegalArgumentException e) {
			return new ResponseEntity<>(new Response<>(null, StatusEnum.FAILURE, e.getMessage()),
					HttpStatus.BAD_REQUEST);
		} catch (Exception e) {
			throw new PharmacyServerException(e.getMessage(), e);
		}

	}

	@GetMapping("/phoneverification/for/pharmacyuser")
	public ResponseEntity<Response<String>> phoneverificationForPharmacyUser(
			@RequestParam("phonenumber") String phonenumber, @RequestParam("otp") Integer otp) {
		try {
			pharmacyService.phoneverificationForPharmacyUser(phonenumber, otp);
			return new ResponseEntity<>(new Response<>("Phone Verification Done.", StatusEnum.SUCCESS, "Phone Verification Done."), HttpStatus.OK);
		} catch (IllegalArgumentException e) {
			return new ResponseEntity<>(new Response<>(null, StatusEnum.FAILURE, e.getMessage()),
					HttpStatus.BAD_REQUEST);
		} catch (Exception e) {
			throw new PharmacyServerException(e.getMessage(), e);
		}

	}

	@GetMapping("/verification/email")
	public ResponseEntity<Response<String>> emailVerification(@RequestParam("emilid") String emilid,
			@RequestParam("emailotp") Integer emailotp) {
		try {
			pharmacyService.emailVerification(emilid, emailotp);
			return new ResponseEntity<>(new Response<>("Email Verification Done.", StatusEnum.SUCCESS, "Email Verification Done."), HttpStatus.OK);
		} catch (IllegalArgumentException e) {
			return new ResponseEntity<>(new Response<>(null, StatusEnum.FAILURE, e.getMessage()),
					HttpStatus.BAD_REQUEST);
		} catch (Exception e) {
			throw new PharmacyServerException(e.getMessage(), e);
		}

	}

	@GetMapping("/email/verification/for/pharmacyuser")
	public ResponseEntity<Response<String>> emailVerificationForPharmacyUser(@RequestParam("emilid") String emilid,
			@RequestParam("emailotp") Integer emailotp) {
		try {
			pharmacyService.emailVerificationForPharmacyUser(emilid, emailotp);
			return new ResponseEntity<>(new Response<>(null, StatusEnum.SUCCESS, null), HttpStatus.OK);
		} catch (IllegalArgumentException e) {
			return new ResponseEntity<>(new Response<>(null, StatusEnum.FAILURE, e.getMessage()),
					HttpStatus.BAD_REQUEST);
		} catch (Exception e) {
			throw new PharmacyServerException(e.getMessage(), e);
		}

	}

	@GetMapping("/requestOtoForPhone")
	public ResponseEntity<Response<String>> requestOTPForPhone(@RequestParam("phonenumber") String phonenumber) {
		try {
			pharmacyService.requestOTPForPhone(phonenumber);
			return new ResponseEntity<>(new Response<>("Otp is Send To Phonenumber", StatusEnum.SUCCESS, "Otp is Send To Phonenumber"), HttpStatus.OK);
		} catch (IllegalArgumentException e) {
			return new ResponseEntity<>(new Response<>(null, StatusEnum.FAILURE, e.getMessage()),
					HttpStatus.BAD_REQUEST);
		} catch (Exception e) {
			throw new PharmacyServerException(e.getMessage(), e);
		}

	}

	@GetMapping("/requestOTPForEamil")
	public ResponseEntity<Response<String>> requestOTPForEamil(@RequestParam("email") String email) {
		try {
			pharmacyService.requestOTPForEamil(email);
			return new ResponseEntity<>(new Response<>(" Otp is Send To Email ", StatusEnum.SUCCESS, "Otp is Send To Email "), HttpStatus.OK);
		} catch (IllegalArgumentException e) {
			return new ResponseEntity<>(new Response<>(null, StatusEnum.FAILURE, e.getMessage()),
					HttpStatus.BAD_REQUEST);
		} catch (Exception e) {
			throw new PharmacyServerException(e.getMessage(), e);
		}

	}

	@GetMapping("/requestOTPForEamilForPharmacyUser")
	public ResponseEntity<Response<String>> requestOTPForEamilForPharmacyUser(@RequestParam("email") String email) {
		try {
			pharmacyService.requestOTPForEamilForPharmacyUser(email);
			return new ResponseEntity<>(new Response<>(null, StatusEnum.SUCCESS, null), HttpStatus.OK);
		} catch (IllegalArgumentException e) {
			return new ResponseEntity<>(new Response<>(null, StatusEnum.FAILURE, e.getMessage()),
					HttpStatus.BAD_REQUEST);
		} catch (Exception e) {
			throw new PharmacyServerException(e.getMessage(), e);
		}

	}

	@GetMapping("/resendOTPForEamil")
	public ResponseEntity<Response<String>> resendOTPForEamil(@RequestParam("email") String email) {
		try {
			pharmacyService.resendOTPForEamil(email);
			return new ResponseEntity<>(
					new Response<>("OTP is Send to mail", StatusEnum.SUCCESS, " OTP is Send to mail"), HttpStatus.OK);
		} catch (IllegalArgumentException e) {
			return new ResponseEntity<>(
					new Response<>("Pharmacy details are not found", StatusEnum.FAILURE, e.getMessage()),
					HttpStatus.BAD_REQUEST);
		} catch (Exception e) {
			throw new PharmacyServerException(e.getMessage(), e);
		}

	}

	@PostMapping(path = "/profilepic/{pharmacyId}")
	public ResponseEntity<Response<String>> uploadProfilePic(@PathVariable Long pharmacyId,
			@RequestParam(name = "profilePic", required = false) MultipartFile profilePic) {
		try {
			pharmacyService.uploadProfilePic(pharmacyId, profilePic);
			return new ResponseEntity<>(new Response<>(null, StatusEnum.SUCCESS, null), HttpStatus.OK);
		} catch (IllegalArgumentException e) {
			return new ResponseEntity<>(new Response<>(null, StatusEnum.FAILURE, e.getMessage()),
					HttpStatus.BAD_REQUEST);
		} catch (Exception e) {
			throw new PharmacyServerException(e.getMessage(), e);
		}

	}

	@GetMapping("/otp/verification/forEmail")
	public ResponseEntity<Response<String>> otpVerificationForEmail(@RequestParam("emailotp") Integer emailotp,
			@RequestParam("emailId") String emailId) {
		try {
			String value = pharmacyService.otpVerificationForEmail(emailotp, emailId);
			return new ResponseEntity<>(new Response<>(value, StatusEnum.SUCCESS, value), HttpStatus.OK);
		} catch (IllegalArgumentException e) {
			return new ResponseEntity<>(new Response<>(null, StatusEnum.FAILURE, e.getMessage()),
					HttpStatus.BAD_REQUEST);
		} catch (Exception e) {
			throw new PharmacyServerException(e.getMessage(), e);
		}

	}

	@GetMapping("/otp/verification/ForPhoneNumber")
	public ResponseEntity<Response<String>> otpVerificationForPhoneNumber(
			@RequestParam("phonenumberotp") Integer phonenumberotp) {
		try {
			String value = pharmacyService.otpVerificationForPhoneNumber(phonenumberotp);
			return new ResponseEntity<>(new Response<>(value, StatusEnum.SUCCESS, value), HttpStatus.OK);
		} catch (IllegalArgumentException e) {
			return new ResponseEntity<>(new Response<>(null, StatusEnum.FAILURE, e.getMessage()),
					HttpStatus.BAD_REQUEST);
		} catch (Exception e) {
			throw new PharmacyServerException(e.getMessage(), e);
		}
	}

	@PostMapping("/isProfileComplted/{pharmacyId}")
	public ResponseEntity<Response<ProfileCompltedDTO>> isProfileComplted(@PathVariable Long pharmacyId) {
		try {
			ProfileCompltedDTO bolen = pharmacyService.isProfileComplted(pharmacyId);
			return new ResponseEntity<>(new Response<>(bolen, StatusEnum.SUCCESS, null), HttpStatus.OK);
		} catch (IllegalArgumentException e) {
			return new ResponseEntity<>(new Response<>(null, StatusEnum.FAILURE, e.getMessage()),
					HttpStatus.BAD_REQUEST);
		} catch (Exception e) {
			throw new PharmacyServerException(e.getMessage(), e);
		}

	}
}
