
package org.secondopinion.doctor.controller;

import java.util.Collection;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.google.gson.Gson;

import org.secondopinion.doctor.dto.Association;
import org.secondopinion.doctor.dto.Certification;
import org.secondopinion.doctor.dto.Doctor;
import org.secondopinion.doctor.dto.DoctorAddress;
import org.secondopinion.doctor.dto.DoctorSearchRequest;
import org.secondopinion.doctor.dto.Feedetails;
import org.secondopinion.doctor.dto.Personaldetail;
import org.secondopinion.doctor.dto.Specialization;
import org.secondopinion.doctor.exception.DoctorServerException;
import org.secondopinion.doctor.service.IDoctorService;
import org.secondopinion.request.Response;
import org.secondopinion.request.Response.StatusEnum;
import org.secondopinion.utils.JSONHelper;

@RestController
public class DoctorController {

	@Autowired
	private IDoctorService doctorService;

	@PostMapping("/all/search/critieria")
	public ResponseEntity<Response<Collection<Doctor>>> getAllDoctorsBySearchCritieria(
			@RequestBody DoctorSearchRequest doctorSearchRequest) {
		try {
			Collection<Doctor> doctors = doctorService.getAllDoctorsBySearchCritieria(doctorSearchRequest);
			return new ResponseEntity<>(new Response<>(doctors, StatusEnum.SUCCESS, null), HttpStatus.OK);

		} catch (IllegalArgumentException e) {
			return new ResponseEntity<>(new Response<>(null, StatusEnum.FAILURE, e.getMessage()),
					HttpStatus.BAD_REQUEST);
		} catch (Exception e) {
			throw new DoctorServerException(e.getMessage(), e);
		}

	}

	@GetMapping("/all/{pageNum}/{maxResults}")
	public ResponseEntity<Response<List<Doctor>>> getAllDoctors(@PathVariable Integer pageNum,
			@PathVariable Integer maxResults) {
		try {
			Response<List<Doctor>> doctors = doctorService.getAllDoctors(pageNum, maxResults);
			return new ResponseEntity<>(doctors, HttpStatus.OK);
		} catch (IllegalArgumentException e) {
			return new ResponseEntity<>(new Response<>(null, StatusEnum.FAILURE, e.getMessage()),
					HttpStatus.BAD_REQUEST);
		} catch (Exception e) {
			throw new DoctorServerException(e.getMessage(), e);
		}

	}

	@GetMapping("/myreport/{doctorid}")
	public ResponseEntity<Response<Map<String, Object>>> getMyReports(@PathVariable Long doctorid) {
		try {
			Map<String, Object> myReports = doctorService.getMyReports(doctorid);
			return new ResponseEntity<>(
					new Response<>(myReports, StatusEnum.SUCCESS, "My Reports data fetched successfully."),
					HttpStatus.OK);
		} catch (IllegalArgumentException e) {
			return new ResponseEntity<>(new Response<>(null, StatusEnum.FAILURE, e.getMessage()),
					HttpStatus.BAD_REQUEST);
		} catch (Exception e) {
			throw new DoctorServerException(e.getMessage(), e);
		}

	}

	// Specialization
	@PostMapping("/specilazation")
	public ResponseEntity<Response<String>> saveDoctorSpecilazation(@RequestBody Specialization specialization) {
		try {
			doctorService.saveSpecialization(specialization);
			return new ResponseEntity<>(new Response<>(null, StatusEnum.SUCCESS, null), HttpStatus.OK);
		} catch (IllegalArgumentException e) {
			return new ResponseEntity<>(new Response<>(null, StatusEnum.FAILURE, e.getMessage()),
					HttpStatus.BAD_REQUEST);
		} catch (Exception e) {
			throw new DoctorServerException(e.getMessage(), e);
		}

	}

	@GetMapping("/specilazation/{specializationId}")
	public ResponseEntity<Response<Specialization>> getSpecilazationById(
			@PathVariable("specializationId") Long specializationId) {
		try {
			Specialization specialization = doctorService.getSpecailizationById(specializationId);
			return new ResponseEntity<>(new Response<>(specialization, StatusEnum.SUCCESS, null), HttpStatus.OK);
		} catch (IllegalArgumentException e) {
			return new ResponseEntity<>(new Response<>(null, StatusEnum.FAILURE, e.getMessage()),
					HttpStatus.BAD_REQUEST);
		} catch (Exception e) {
			throw new DoctorServerException(e.getMessage(), e);
		}

	}

	@GetMapping("/all/specilazations/{doctorId}")
	public ResponseEntity<Response<Collection<Specialization>>> getSpecializationsByDoctorId(
			@PathVariable("doctorId") Long doctorId) {
		try {
			Collection<Specialization> specializations = doctorService.getSpecializationsByDoctorId(doctorId);
			return new ResponseEntity<>(new Response<>(specializations, StatusEnum.SUCCESS, null), HttpStatus.OK);
		} catch (IllegalArgumentException e) {
			return new ResponseEntity<>(new Response<>(null, StatusEnum.FAILURE, e.getMessage()),
					HttpStatus.BAD_REQUEST);
		} catch (Exception e) {
			throw new DoctorServerException(e.getMessage(), e);
		}

	}

	@DeleteMapping("/specilazation/{specializationId}")
	public ResponseEntity<Response<String>> deleteDoctorSpecilazation(@PathVariable Long specializationId) {
		try {
			doctorService.deleteSpecialization(specializationId);
			return new ResponseEntity<>(new Response<>(null, StatusEnum.SUCCESS, null), HttpStatus.OK);
		} catch (IllegalArgumentException e) {
			return new ResponseEntity<>(new Response<>(null, StatusEnum.FAILURE, e.getMessage()),
					HttpStatus.BAD_REQUEST);
		} catch (Exception e) {
			throw new DoctorServerException(e.getMessage(), e);
		}

	}

	// Association
	@PostMapping("/association")
	public ResponseEntity<Response<String>> saveDoctorAssociation(@RequestBody Association association) {
		try {
			doctorService.saveAssociation(association);
			return new ResponseEntity<>(new Response<>(null, StatusEnum.SUCCESS, null), HttpStatus.OK);
		} catch (IllegalArgumentException e) {
			return new ResponseEntity<>(new Response<>(null, StatusEnum.FAILURE, e.getMessage()),
					HttpStatus.BAD_REQUEST);
		} catch (Exception e) {
			throw new DoctorServerException(e.getMessage(), e);
		}

	}

	@GetMapping("/association/{entityId}")
	public ResponseEntity<Response<List<Association>>> getAssociationById(@PathVariable("entityId") Long entityId) {
		try {
			List<Association> association = doctorService.getAssociationById(entityId);
			return new ResponseEntity<>(new Response<>(association, StatusEnum.SUCCESS, null), HttpStatus.OK);
		} catch (IllegalArgumentException e) {
			return new ResponseEntity<>(new Response<>(null, StatusEnum.FAILURE, e.getMessage()),
					HttpStatus.BAD_REQUEST);
		} catch (Exception e) {
			throw new DoctorServerException(e.getMessage(), e);
		}

	}

	@GetMapping("/all/associations/{doctorId}")
	public ResponseEntity<Response<Collection<Association>>> getAssocaiationByDoctorId(
			@PathVariable("doctorId") Long doctorId) {
		try {
			Collection<Association> associations = doctorService.getAssocaiationByDoctorId(doctorId);
			return new ResponseEntity<>(new Response<>(associations, StatusEnum.SUCCESS, null), HttpStatus.OK);
		} catch (IllegalArgumentException e) {
			return new ResponseEntity<>(new Response<>(null, StatusEnum.FAILURE, e.getMessage()),
					HttpStatus.BAD_REQUEST);
		} catch (Exception e) {
			throw new DoctorServerException(e.getMessage(), e);
		}

	}

	@GetMapping("/findallassociation")
	public ResponseEntity<Response<List<Association>>> findAllAssociation() {
		try {
			List<Association> associations = doctorService.findAllAssociation();
			return new ResponseEntity<>(new Response<>(associations, StatusEnum.SUCCESS, null), HttpStatus.OK);
		} catch (IllegalArgumentException e) {
			return new ResponseEntity<>(new Response<>(null, StatusEnum.FAILURE, e.getMessage()),
					HttpStatus.BAD_REQUEST);
		} catch (Exception e) {
			throw new DoctorServerException(e.getMessage(), e);
		}

	}

	@DeleteMapping("/association/{associationId}")
	public ResponseEntity<Response<String>> deleteDoctorAssociation(@PathVariable Long associationId) {
		try {
			doctorService.deleteDoctorAssociation(associationId);
			return new ResponseEntity<>(new Response<>(null, StatusEnum.SUCCESS, null), HttpStatus.OK);
		} catch (IllegalArgumentException e) {
			return new ResponseEntity<>(new Response<>(null, StatusEnum.FAILURE, e.getMessage()),
					HttpStatus.BAD_REQUEST);
		} catch (Exception e) {
			throw new DoctorServerException(e.getMessage(), e);
		}

	}

	// Address
	@PostMapping("/address/save")
	public ResponseEntity<Response<String>> saveDoctorAddress(@RequestBody DoctorAddress doctorAddress) {
		try {
			doctorService.saveDoctorAddress(doctorAddress);
			return new ResponseEntity<>(new Response<>(null, StatusEnum.SUCCESS, null), HttpStatus.OK);
		} catch (IllegalArgumentException e) {
			return new ResponseEntity<>(new Response<>(null, StatusEnum.FAILURE, e.getMessage()),
					HttpStatus.BAD_REQUEST);
		} catch (Exception e) {
			throw new DoctorServerException(e.getMessage(), e);
		}

	}

	@GetMapping("/address/id/{addressId}")
	public ResponseEntity<Response<DoctorAddress>> getDoctorAddressById(@PathVariable("addressId") Long addressId) {
		try {
			DoctorAddress doctorAddress = doctorService.getDoctorAddressById(addressId);
			return new ResponseEntity<>(new Response<>(doctorAddress, StatusEnum.SUCCESS, null), HttpStatus.OK);
		} catch (IllegalArgumentException e) {
			return new ResponseEntity<>(new Response<>(null, StatusEnum.FAILURE, e.getMessage()),
					HttpStatus.BAD_REQUEST);
		} catch (Exception e) {
			throw new DoctorServerException(e.getMessage(), e);
		}

	}

	@GetMapping("/address/doctorid/{doctorId}")
	public ResponseEntity<Response<Collection<DoctorAddress>>> getDoctorAddressByDoctorId(
			@PathVariable("doctorId") Long doctorId) {
		try {
			return new ResponseEntity<>(
					new Response<>(doctorService.getDoctorAddressByDoctorId(doctorId), StatusEnum.SUCCESS, null),
					HttpStatus.OK);
		} catch (IllegalArgumentException e) {
			return new ResponseEntity<>(new Response<>(null, StatusEnum.FAILURE, e.getMessage()),
					HttpStatus.BAD_REQUEST);
		} catch (Exception e) {
			throw new DoctorServerException(e.getMessage(), e);
		}

	}

	@DeleteMapping("/address/{addressId}")
	public ResponseEntity<Response<String>> deleteDoctorAddress(@PathVariable Long addressId) {
		try {
			doctorService.deleteDoctorAddress(addressId);
			return new ResponseEntity<>(new Response<>(null, StatusEnum.SUCCESS, null), HttpStatus.OK);
		} catch (IllegalArgumentException e) {
			return new ResponseEntity<>(new Response<>(null, StatusEnum.FAILURE, e.getMessage()),
					HttpStatus.BAD_REQUEST);
		} catch (Exception e) {
			throw new DoctorServerException(e.getMessage(), e);
		}

	}

	// Fee details
	@PostMapping("/feeDetails")
	public ResponseEntity<Response<String>> saveDoctorFeeDetails(@RequestBody Feedetails feeDetails) {
		try {
			doctorService.saveDoctorFeeDetails(feeDetails);
			return new ResponseEntity<>(new Response<>(null, StatusEnum.SUCCESS, null), HttpStatus.OK);
		} catch (IllegalArgumentException e) {
			return new ResponseEntity<>(new Response<>(null, StatusEnum.FAILURE, e.getMessage()),
					HttpStatus.BAD_REQUEST);
		} catch (Exception e) {
			throw new DoctorServerException(e.getMessage(), e);
		}

	}

	@GetMapping("/feeDetails/id/{feeDetailsId}")
	public ResponseEntity<Response<Feedetails>> getFeeDetailsById(@PathVariable("feeDetailsId") Long feeDetailsId) {
		try {
			Feedetails feedetails = doctorService.getFeeDetailsById(feeDetailsId);
			return new ResponseEntity<>(new Response<>(feedetails, StatusEnum.SUCCESS, null), HttpStatus.OK);
		} catch (IllegalArgumentException e) {
			return new ResponseEntity<>(new Response<>(null, StatusEnum.FAILURE, e.getMessage()),
					HttpStatus.BAD_REQUEST);
		} catch (Exception e) {
			throw new DoctorServerException(e.getMessage(), e);
		}

	}

	@GetMapping("/feeDetails/{doctorId}")
	public ResponseEntity<Response<Collection<Feedetails>>> getDoctorFeeDetails(
			@PathVariable("doctorId") Long doctorId) {
		try {
			Collection<Feedetails> feedetails = doctorService.getDoctorFeeDetails(doctorId);
			return new ResponseEntity<>(new Response<>(feedetails, StatusEnum.SUCCESS, null), HttpStatus.OK);
		} catch (IllegalArgumentException e) {
			return new ResponseEntity<>(new Response<>(null, StatusEnum.FAILURE, e.getMessage()),
					HttpStatus.BAD_REQUEST);
		} catch (Exception e) {
			throw new DoctorServerException(e.getMessage(), e);
		}

	}

	@DeleteMapping("/feeDetails/{feeDetailsId}")
	public ResponseEntity<Response<String>> deleteDoctorFeeDetails(@PathVariable("feeDetailsId") Long feeDetailsId) {
		try {
			doctorService.deleteDoctorFeeDetails(feeDetailsId);
			return new ResponseEntity<>(new Response<>(null, StatusEnum.SUCCESS, null), HttpStatus.OK);
		} catch (IllegalArgumentException e) {
			return new ResponseEntity<>(new Response<>(null, StatusEnum.FAILURE, e.getMessage()),
					HttpStatus.BAD_REQUEST);
		} catch (Exception e) {
			throw new DoctorServerException(e.getMessage(), e);
		}

	}

	// certification
	@PostMapping("/certification")
	public ResponseEntity<Response<String>> saveCertification(@RequestBody Certification certification) {
		try {
			doctorService.saveCertification(certification);
			return new ResponseEntity<>(new Response<>(null, StatusEnum.SUCCESS, null), HttpStatus.OK);
		} catch (IllegalArgumentException e) {
			return new ResponseEntity<>(new Response<>(null, StatusEnum.FAILURE, e.getMessage()),
					HttpStatus.BAD_REQUEST);
		} catch (Exception e) {
			throw new DoctorServerException(e.getMessage(), e);
		}

	}

	@GetMapping("/certification/id/{certificationId}")
	public ResponseEntity<Response<Certification>> getCertificationById(
			@PathVariable("certificationId") Long certificationId) {
		try {
			Certification certification = doctorService.getCertificationById(certificationId);
			return new ResponseEntity<>(new Response<>(certification, StatusEnum.SUCCESS, null), HttpStatus.OK);
		} catch (IllegalArgumentException e) {
			return new ResponseEntity<>(new Response<>(null, StatusEnum.FAILURE, e.getMessage()),
					HttpStatus.BAD_REQUEST);
		} catch (Exception e) {
			throw new DoctorServerException(e.getMessage(), e);
		}

	}

	@GetMapping("/certification/doctorid/{doctorId}")
	public ResponseEntity<Response<Collection<Certification>>> getDoctorCertifications(
			@PathVariable("doctorId") Long doctorId) {
		try {
			Collection<Certification> certifications = doctorService.getDoctorCertifications(doctorId);
			return new ResponseEntity<>(new Response<>(certifications, StatusEnum.SUCCESS, null), HttpStatus.OK);
		} catch (IllegalArgumentException e) {
			return new ResponseEntity<>(new Response<>(null, StatusEnum.FAILURE, e.getMessage()),
					HttpStatus.BAD_REQUEST);
		} catch (Exception e) {
			throw new DoctorServerException(e.getMessage(), e);
		}

	}

	@DeleteMapping("/certification/{certificationId}")
	public ResponseEntity<Response<String>> deleteDoctorCertication(@PathVariable Long certificationId) {
		try {
			doctorService.deleteDoctorCertication(certificationId);
			return new ResponseEntity<>(new Response<>(null, StatusEnum.SUCCESS, null), HttpStatus.OK);
		} catch (IllegalArgumentException e) {
			return new ResponseEntity<>(new Response<>(null, StatusEnum.FAILURE, e.getMessage()),
					HttpStatus.BAD_REQUEST);
		} catch (Exception e) {
			throw new DoctorServerException(e.getMessage(), e);
		}

	}

	@PostMapping(value = "/personaldetail")
	public ResponseEntity<Response<String>> savePersonaldetail(@RequestBody Personaldetail personaldetail ) {
		try {
			doctorService.savePersonalDetail(personaldetail, null);
			return new ResponseEntity<>(new Response<>(null, StatusEnum.SUCCESS, null), HttpStatus.OK);
		} catch (IllegalArgumentException e) {
			return new ResponseEntity<>(new Response<>(null, StatusEnum.FAILURE, e.getMessage()),
					HttpStatus.BAD_REQUEST);
		} catch (Exception e) {
			throw new DoctorServerException(e.getMessage(), e);
		}

	}
	@PostMapping(path = "/doctorSignature/{doctorId}")
	public ResponseEntity<Response<String>> uploadDoctorSignature(@PathVariable Long doctorId,
			@RequestParam(name = "doctorSignature", required = false) MultipartFile doctorSignature) {
		try {
			doctorService.uploadDoctorSignature(doctorId, doctorSignature);
			return new ResponseEntity<>(new Response<>(null, StatusEnum.SUCCESS, null), HttpStatus.OK);
		} catch (IllegalArgumentException e) {
			return new ResponseEntity<>(new Response<>(null, StatusEnum.FAILURE, e.getMessage()),
					HttpStatus.BAD_REQUEST);
		} catch (Exception e) {
			throw new DoctorServerException(e.getMessage(), e);
		}

	}

	@PostMapping(path = "/profilepic/{doctorId}")
	public ResponseEntity<Response<String>> uploadProfilePic(@PathVariable Long doctorId,
			@RequestParam(name = "profilePic", required = false) MultipartFile profilePic) {
		try {
			doctorService.uploadProfilePic(doctorId, profilePic);
			return new ResponseEntity<>(new Response<>(null, StatusEnum.SUCCESS, null), HttpStatus.OK);
		} catch (IllegalArgumentException e) {
			return new ResponseEntity<>(new Response<>(null, StatusEnum.FAILURE, e.getMessage()),
					HttpStatus.BAD_REQUEST);
		} catch (Exception e) {
			throw new DoctorServerException(e.getMessage(), e);
		}

	}

	@GetMapping("/personaldetail/id/{personaldetailId}")
	public ResponseEntity<Response<Personaldetail>> getpersonaldetailById(
			@PathVariable("personaldetailId") Long personaldetailId) {
		try {
			Personaldetail personaldetail = doctorService.getPersonaldetailById(personaldetailId);
			return new ResponseEntity<>(new Response<>(personaldetail, StatusEnum.SUCCESS, null), HttpStatus.OK);
		} catch (IllegalArgumentException e) {
			return new ResponseEntity<>(new Response<>(null, StatusEnum.FAILURE, e.getMessage()),
					HttpStatus.BAD_REQUEST);
		} catch (Exception e) {
			throw new DoctorServerException(e.getMessage(), e);
		}

	}


	
	@GetMapping("/personaldetail/doctorid/{doctorId}")
	public ResponseEntity<Response<Personaldetail>> getDoctorPersonaldetailByDoctorId(
			@PathVariable("doctorId") Long doctorId) {
		try {
			Personaldetail personaldetail = doctorService.getDoctorPersonaldetailByDoctorId(doctorId);
			return new ResponseEntity<>(new Response<>(personaldetail, StatusEnum.SUCCESS, null), HttpStatus.OK);
		} catch (IllegalArgumentException e) {
			return new ResponseEntity<>(new Response<>(null, StatusEnum.FAILURE, e.getMessage()),
					HttpStatus.BAD_REQUEST);
		} catch (Exception e) {
			throw new DoctorServerException(e.getMessage(), e);
		}

	}
	
	
	

	@DeleteMapping("/personaldetail/{personaldetailId}")
	public ResponseEntity<Response<String>> deleteDoctorPersonalDetail(@PathVariable Long personaldetailId) {
		try {
			doctorService.deleteDoctorPersonalDetail(personaldetailId);
			return new ResponseEntity<>(new Response<>(null, StatusEnum.SUCCESS, null), HttpStatus.OK);
		} catch (IllegalArgumentException e) {
			return new ResponseEntity<>(new Response<>(null, StatusEnum.FAILURE, e.getMessage()),
					HttpStatus.BAD_REQUEST);
		} catch (Exception e) {
			throw new DoctorServerException(e.getMessage(), e);
		}

	}

}
