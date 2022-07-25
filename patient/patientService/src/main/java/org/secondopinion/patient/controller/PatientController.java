package org.secondopinion.patient.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.secondopinion.patient.dto.Address;
import org.secondopinion.patient.dto.CaretakerResponseDTO;
import org.secondopinion.patient.dto.ForUser;
import org.secondopinion.patient.dto.MedicalInsuranceRequest;
import org.secondopinion.patient.dto.Medicalinsurance;
import org.secondopinion.patient.dto.PatientFlagsRequest;
import org.secondopinion.patient.dto.PatientFlagsRequest.PatientFlag;
import org.secondopinion.patient.dto.PatientRequest;
import org.secondopinion.patient.dto.Personaldetail;
import org.secondopinion.patient.dto.RELATIONSHIP_TYPE;
import org.secondopinion.patient.dto.Relationship;
import org.secondopinion.patient.dto.UpdatePasswordRequest;
import org.secondopinion.patient.dto.User;
import org.secondopinion.patient.dto.UserRequest;
import org.secondopinion.patient.dto.Userreference;
import org.secondopinion.patient.dto.Vitals;
import org.secondopinion.patient.dto.VitalsRequest;
import org.secondopinion.patient.exception.PatientServerException;
import org.secondopinion.patient.service.IPatientAssociation;
import org.secondopinion.patient.service.IUserService;
import org.secondopinion.request.Request;
import org.secondopinion.request.Response;
import org.secondopinion.request.Response.StatusEnum;
import org.secondopinion.utils.JSONHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.google.gson.Gson;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/")
@Api("/")
public class PatientController {

	@Autowired
	private IUserService userService;

	@Autowired
	private IPatientAssociation patientassocationServiceImpl;

	@Autowired
	private HttpServletRequest httpServletRequest;

	@PostMapping("/associate/newrequest")
	public ResponseEntity<Response<String>> savePatientRequest(@RequestBody PatientRequest patientRequest) {

		try {
			String moduleName = httpServletRequest.getHeader("moduleName");
			RELATIONSHIP_TYPE relationship_TYPE = RELATIONSHIP_TYPE.valueOf(moduleName);
			patientassocationServiceImpl.savePatientRequest(patientRequest, relationship_TYPE);
			return new ResponseEntity<>(new Response<>(null, StatusEnum.SUCCESS, null), HttpStatus.OK);
		} catch (Exception ex) {
			throw new PatientServerException(ex.getMessage(), ex);
		}

	}

	@PostMapping("/save/userreference")
	public ResponseEntity<Response<String>> saveUserreference(@RequestBody Userreference userreference) {

		try {
			userService.saveUserPrefrence(userreference);
			return new ResponseEntity<>(new Response<>(null, StatusEnum.SUCCESS, null), HttpStatus.OK);
		} catch (Exception ex) {
			throw new PatientServerException(ex.getMessage(), ex);
		}

	}

	@PostMapping("/save/saveuserprefrencediagnosticcenter")
	public ResponseEntity<Response<String>> saveUserPrefrenceDiagnosticcenter(
			@RequestBody Userreference userreference) {

		try {
			userService.saveUserPrefrenceDiagnosticcenter(userreference);
			return new ResponseEntity<>(new Response<>(null, StatusEnum.SUCCESS, null), HttpStatus.OK);
		} catch (Exception ex) {
			throw new PatientServerException(ex.getMessage(), ex);
		}

	}

	@DeleteMapping("/delete/user/refrence")
	public ResponseEntity<Response<String>> deleteUserRefrence(@RequestParam Long userreferenceId) {

		try {
			userService.deleteUserRefrence(userreferenceId);
			return new ResponseEntity<>(new Response<>("User Refrence Delete", StatusEnum.SUCCESS, null),
					HttpStatus.OK);
		} catch (Exception ex) {
			throw new PatientServerException(ex.getMessage(), ex);
		}

	}

	@ApiOperation(value = "/users", notes = "This method is used to get username by key")
	@PostMapping("/users")
	public ResponseEntity<Response<Map<Long, String>>> getUserNameByKey(@RequestBody Request request) {
		try {
			Map<Long, String> userName = patientassocationServiceImpl.getUserNameByUserIds(request.getUserIds());

			return new ResponseEntity<>(new Response<>(userName, StatusEnum.SUCCESS, null), HttpStatus.OK);
		} catch (Exception ex) {
			throw new PatientServerException(ex.getMessage(), ex);
		}
	}

	@PostMapping("/requestdoctor/reject/{doctorId}")
	public ResponseEntity<Response<String>> updateRelationshipUponDoctorRejections(

			@PathVariable("doctorId") Long doctorId, @RequestParam Long forUserId) {

		try {
			String moduleName = httpServletRequest.getHeader("moduleName");
			RELATIONSHIP_TYPE relationship_TYPE = RELATIONSHIP_TYPE.valueOf(moduleName);
			patientassocationServiceImpl.updatePatientRequestUponDoctorRejections(doctorId, forUserId,
					relationship_TYPE);
			return new ResponseEntity<>(new Response<>(null, StatusEnum.SUCCESS, null), HttpStatus.OK);
		} catch (Exception ex) {
			throw new PatientServerException(ex.getMessage(), ex);
		}

	}

	@PostMapping("/request/accept/{doctorId}")
	public ResponseEntity<Response<String>> updateRelationshipUponDoctorAccepts(@PathVariable("doctorId") Long doctorId,

			@RequestParam Long userId) {

		try {
			String moduleName = httpServletRequest.getHeader("moduleName");
			RELATIONSHIP_TYPE relationship_TYPE = RELATIONSHIP_TYPE.valueOf(moduleName);
			patientassocationServiceImpl.updateRelationshipUponDoctorAccepts(doctorId, userId, relationship_TYPE);
			return new ResponseEntity<>(new Response<>(null, StatusEnum.SUCCESS, null), HttpStatus.OK);
		} catch (Exception ex) {
			throw new PatientServerException(ex.getMessage(), ex);
		}

	}

	@PostMapping("/medicalinsurance/save")
	public ResponseEntity<Response<String>> saveMedicalInsuranceDetails(
			@RequestBody MedicalInsuranceRequest medicalInsuranceRequest) {

		try {
			String moduleName = httpServletRequest.getHeader("moduleName");
			RELATIONSHIP_TYPE relationship_TYPE = RELATIONSHIP_TYPE.valueOf(moduleName);
			userService.addMedicalInsuranceDetails(medicalInsuranceRequest.getForUser(),
					medicalInsuranceRequest.getMedicalinsurance(), relationship_TYPE);
			return new ResponseEntity<>(new Response<>(null, StatusEnum.SUCCESS, null), HttpStatus.OK);
		} catch (Exception ex) {
			throw new PatientServerException(ex.getMessage(), ex);
		}

	}

	@PostMapping(path = "/vitals/save")
	public ResponseEntity<Response<String>> saveVitals(@RequestBody VitalsRequest vitalsRequest) {

		try {

			String moduleName = httpServletRequest.getHeader("moduleName");
			RELATIONSHIP_TYPE relationship_TYPE = RELATIONSHIP_TYPE.valueOf(moduleName);
			userService.addVitals(vitalsRequest.getForUser(), vitalsRequest.getVitals(), relationship_TYPE);
			return new ResponseEntity<>(new Response<>(null, StatusEnum.SUCCESS, null), HttpStatus.OK);
		} catch (Exception ex) {
			throw new PatientServerException(ex.getMessage(), ex);
		}
	}
	
	
	@PostMapping(path = "/updateVitals")
	public ResponseEntity<Response<String>> updateVitals(@RequestBody VitalsRequest vitalsRequest) {

		try {

			String moduleName = httpServletRequest.getHeader("moduleName");
			RELATIONSHIP_TYPE relationship_TYPE = RELATIONSHIP_TYPE.valueOf(moduleName);
			userService.updateVitals(vitalsRequest.getForUser(), vitalsRequest.getVitals(), relationship_TYPE);
			return new ResponseEntity<>(new Response<>(null, StatusEnum.SUCCESS, null), HttpStatus.OK);
		} catch (Exception ex) {
			throw new PatientServerException(ex.getMessage(), ex);
		}
	}

	@PostMapping(path = "/getuserreference/{userid}/{referencetype}")
	public ResponseEntity<Response<List<Userreference>>> getallUserreference(@PathVariable("userid") Long userid,
			@PathVariable("referencetype") String referencetype) {

		try {
			List<Userreference> userreferences = userService.getallUserreference(userid, referencetype);
			return new ResponseEntity<>(new Response<>(userreferences, StatusEnum.SUCCESS, null), HttpStatus.OK);

		} catch (Exception ex) {
			throw new PatientServerException(ex.getMessage(), ex);
		}

	}

	@PostMapping(path = "/getallUserreferencediagnosticcenter/{userid}/{referencetype}")
	public ResponseEntity<Response<List<Userreference>>> getallUserreferenceDiagnosticcenter(
			@PathVariable("userid") Long userid, @PathVariable("referencetype") String referencetype) {

		try {
			List<Userreference> userreferences = userService.getallUserreferenceDiagnosticcenter(userid, referencetype);
			return new ResponseEntity<>(new Response<>(userreferences, StatusEnum.SUCCESS, null), HttpStatus.OK);

		} catch (Exception ex) {
			throw new PatientServerException(ex.getMessage(), ex);
		}

	}

	@PostMapping(path = "/relations/get")
	public ResponseEntity<Response<List<Relationship>>> getUserRelations(@RequestBody ForUser forUser) {

		try {
			String moduleName = httpServletRequest.getHeader("moduleName");
			RELATIONSHIP_TYPE relationship_TYPE = RELATIONSHIP_TYPE.valueOf(moduleName);

			List<Relationship> relationships = userService.getUserRelations(forUser, relationship_TYPE);
			return new ResponseEntity<>(new Response<>(relationships, StatusEnum.SUCCESS, null), HttpStatus.OK);

		} catch (Exception ex) {
			throw new PatientServerException(ex.getMessage(), ex);
		}

	}

	@PostMapping(path = "/vitals/byday/get")
	public ResponseEntity<Response<List<Vitals>>> getUserVitalsByDay(@RequestBody ForUser forUser) {
		try {
			String moduleName = httpServletRequest.getHeader("moduleName");
			RELATIONSHIP_TYPE relationship_TYPE = RELATIONSHIP_TYPE.valueOf(moduleName);

			List<Vitals> vitals = userService.getUserVitalsByDay(forUser, forUser.getVitalsByDate(), relationship_TYPE);
			return new ResponseEntity<>(new Response<>(vitals, StatusEnum.SUCCESS, null), HttpStatus.OK);

		} catch (Exception ex) {
			throw new PatientServerException(ex.getMessage(), ex);
		}

	}

	@PostMapping("/medicalinsurance/get")
	public ResponseEntity<Response<List<Medicalinsurance>>> getMedicalInsuranceDtailsForUser(
			@RequestBody ForUser forUser) {
		try {
			String moduleName = httpServletRequest.getHeader("moduleName");
			RELATIONSHIP_TYPE relationship_TYPE = RELATIONSHIP_TYPE.valueOf(moduleName);
			List<Medicalinsurance> medicalinsurances = userService.getMedicalInsuranceDtailsForUser(forUser,
					relationship_TYPE);
			return new ResponseEntity<>(new Response<>(medicalinsurances, StatusEnum.SUCCESS, null), HttpStatus.OK);

		} catch (Exception ex) {
			throw new PatientServerException(ex.getMessage(), ex);
		}

	}

	@DeleteMapping("/deleteMedicalinsurance/{medicalinsuranceId}")
	public ResponseEntity<Response<String>> deleteMedicalinsurance(@PathVariable Long medicalinsuranceId) {

		try {
			userService.deleteMedicalinsurance(medicalinsuranceId);
			return new ResponseEntity<>(
					new Response<>(null, StatusEnum.SUCCESS, "Medicalinsurance delete successfully"), HttpStatus.OK);
		} catch (IllegalArgumentException e) {
			return new ResponseEntity<>(new Response<>(null, StatusEnum.FAILURE, e.getMessage()),
					HttpStatus.BAD_REQUEST);
		} catch (Exception e) {
			throw new PatientServerException(e.getMessage(), e);
		}
	}

	@ApiOperation(value = "/all/flags", notes = "This method is used to get all doctors")
	@PostMapping("/all/flags")
	public ResponseEntity<Response<Map<PatientFlag, List<User>>>> fetchDoctorsByFlagWithPagination(
			@RequestBody PatientFlagsRequest doctorFlagsRequest) {
		try {
			Map<PatientFlag, List<User>> doctors = userService.fetchPatientsByFlagWithPagination(doctorFlagsRequest);
			return new ResponseEntity<>(new Response<>(doctors, StatusEnum.SUCCESS, null), HttpStatus.OK);

		} catch (Exception ex) {
			throw new PatientServerException(ex.getMessage(), ex);
		}

	}

	@PostMapping(value = "/update", consumes = { MediaType.APPLICATION_JSON_VALUE, MediaType.MULTIPART_FORM_DATA_VALUE,
			MediaType.APPLICATION_OCTET_STREAM_VALUE })
	public ResponseEntity<Response<String>> updateUser(@RequestParam("uploaduserJson") String uploaduserJson,
			@RequestParam(name = "profilePic", required = false) MultipartFile profilePic) {
		Gson gson = JSONHelper.getGsonWithDate();

		try {
			String moduleName = httpServletRequest.getHeader("moduleName");
			String forUserId = httpServletRequest.getHeader("forUserId");
			RELATIONSHIP_TYPE relationship_TYPE = RELATIONSHIP_TYPE.valueOf(moduleName);
			User user = gson.fromJson(uploaduserJson, User.class);
			ForUser foruser = new ForUser();
			foruser.setForUserId(new Long(forUserId));
			foruser.setUserId(user.getUserId());
			userService.updateUser(user, profilePic, foruser, relationship_TYPE);
			return new ResponseEntity<>(new Response<>(null, StatusEnum.SUCCESS, null), HttpStatus.OK);
		} catch (Exception ex) {
			throw new PatientServerException(ex.getMessage(), ex);

		}
	}

	@PostMapping(path = "/password/update")
	public ResponseEntity<Response<String>> updateUserPassword(@RequestBody UpdatePasswordRequest userPasswordReguest) {

		try {
			userService.updateUserPassword(userPasswordReguest.getEmailId(), userPasswordReguest.getNewPassword());
			return new ResponseEntity<>(new Response<>(null, StatusEnum.SUCCESS, null), HttpStatus.OK);
		} catch (Exception ex) {
			throw new PatientServerException(ex.getMessage(), ex);
		}

	}

	@PostMapping("/address/get")
	public ResponseEntity<Response<List<Address>>> getAddress(@RequestBody ForUser forUser) {
		try {
			String moduleName = httpServletRequest.getHeader("moduleName");
			RELATIONSHIP_TYPE relationship_TYPE = RELATIONSHIP_TYPE.valueOf(moduleName);
			List<Address> address = userService.getAddress(forUser, relationship_TYPE);
			return new ResponseEntity<>(new Response<>(address, StatusEnum.SUCCESS, null), HttpStatus.OK);

		} catch (Exception ex) {
			throw new PatientServerException(ex.getMessage(), ex);
		}

	}

	@PostMapping("/verifyUser/{verificationId}")
	public ResponseEntity<Response<String>> verifyUser(@RequestBody UserRequest userRequest,
			@PathVariable("verificationId") String verificationId) {

		try {
			userService.verifyUser(userRequest.getUser().getUserId(), userRequest.getForUser().getForUserId(),
					verificationId, userRequest.getForUser().getRelationType());
			return new ResponseEntity<>(new Response<>("Verification Done.", StatusEnum.SUCCESS, null), HttpStatus.OK);
		} catch (Exception ex) {
			throw new PatientServerException(ex.getMessage(), ex);
		}

	}

	@PostMapping("/address/save")
	public ResponseEntity<Response<String>> saveAddress(@RequestBody Address address) {
		try {

			String moduleName = httpServletRequest.getHeader("moduleName");
			String forUserId = httpServletRequest.getHeader("forUserId");
			RELATIONSHIP_TYPE relationship_TYPE = RELATIONSHIP_TYPE.valueOf(moduleName);
			ForUser foruser = new ForUser();
			foruser.setForUserId(new Long(forUserId));
			foruser.setUserId(address.getUserId());
			userService.addAddress(foruser, address, relationship_TYPE);
			return new ResponseEntity<>(new Response<>("Addres Saved", StatusEnum.SUCCESS, null), HttpStatus.OK);
		} catch (Exception ex) {
			throw new PatientServerException(ex.getMessage(), ex);
		}

	}

	@PostMapping("/personaldetails/add")
	public ResponseEntity<Response<String>> addPersonaldetail(@RequestBody Personaldetail personaldetail) {
		try {
			String moduleName = httpServletRequest.getHeader("moduleName");
			String forUserId = httpServletRequest.getHeader("forUserId");
			RELATIONSHIP_TYPE relationship_TYPE = RELATIONSHIP_TYPE.valueOf(moduleName);
			ForUser foruser = new ForUser();
			foruser.setForUserId(new Long(forUserId));
			foruser.setUserId(personaldetail.getUserId());
			userService.addPersonaldetail(foruser, personaldetail, relationship_TYPE);
			return new ResponseEntity<>(new Response<>("Personaldetails add successfully.", StatusEnum.SUCCESS, "Personaldetails add SuccessFully"), HttpStatus.OK);
		} catch (Exception ex) {
			throw new PatientServerException(ex.getMessage(), ex);
		}

	}

	@PostMapping("/updateUserPrimarydetails")
	public ResponseEntity<Response<String>> updateUserPrimarydetails(@RequestBody User user) {
		try {

			String moduleName = httpServletRequest.getHeader("moduleName");
			String forUserId = httpServletRequest.getHeader("forUserId");
			RELATIONSHIP_TYPE relationship_TYPE = RELATIONSHIP_TYPE.valueOf(moduleName);
			ForUser foruser = new ForUser();
			foruser.setUserId(user.getUserId());
			foruser.setForUserId(new Long(forUserId));
			userService.updateUserPrimarydetails(user, foruser, relationship_TYPE);
			return new ResponseEntity<>(new Response<>("User Updated successfully.", StatusEnum.SUCCESS, null), HttpStatus.OK);
		} catch (Exception ex) {
			throw new PatientServerException(ex.getMessage(), ex);
		}

	}

	@PostMapping(path = "/personaldetails/get/")
	public ResponseEntity<Response<Personaldetail>> getPersonaldetail(@RequestBody ForUser forUser) {
		try {
			String moduleName = httpServletRequest.getHeader("moduleName");
			RELATIONSHIP_TYPE relationship_TYPE = RELATIONSHIP_TYPE.valueOf(moduleName);

			Personaldetail personaldetail = userService.getPersonaldetail(forUser, relationship_TYPE);
			if(personaldetail==null) {
				return new ResponseEntity<>(new Response<>(personaldetail, StatusEnum.SUCCESS, "Personaldetails Not Found."), HttpStatus.OK);
			}
			return new ResponseEntity<>(new Response<>(personaldetail, StatusEnum.SUCCESS, "Personaldetails get successfully."), HttpStatus.OK);
		} catch (IllegalArgumentException e) {
			return new ResponseEntity<>(new Response<>(null, StatusEnum.FAILURE, e.getMessage()),
					HttpStatus.BAD_REQUEST);
		} catch (Exception e) {
			throw new PatientServerException(e.getMessage(), e);
		}

	}

	@PostMapping(path = "/vitals/get")
	public ResponseEntity<Response<List<Vitals>>> getUserVitals(@RequestBody ForUser forUser) {
		try {
			String moduleName = httpServletRequest.getHeader("moduleName");
			RELATIONSHIP_TYPE relationship_TYPE = RELATIONSHIP_TYPE.valueOf(moduleName);
			List<Vitals> vitals = userService.getUserVitals(forUser, relationship_TYPE);
			return new ResponseEntity<>(new Response<>(vitals, StatusEnum.SUCCESS, "Vitals get successfully."), HttpStatus.OK);
		} catch (IllegalArgumentException e) {
			return new ResponseEntity<>(new Response<>(null, StatusEnum.FAILURE, e.getMessage()),
					HttpStatus.BAD_REQUEST);
		} catch (Exception e) {
			throw new PatientServerException(e.getMessage(), e);
		}

	}

	@PostMapping(path = "/vitals/latest")
	public ResponseEntity<Response<List<Vitals>>> getUserLatestVitals(@RequestBody ForUser forUser) {
		try {
			String moduleName = httpServletRequest.getHeader("moduleName");
			RELATIONSHIP_TYPE relationship_TYPE = RELATIONSHIP_TYPE.valueOf(moduleName);
			List<Vitals> vitals = userService.getUserLatestVitals(forUser, relationship_TYPE);
			return new ResponseEntity<>(new Response<>(vitals, StatusEnum.SUCCESS, "Vitals get successfully."), HttpStatus.OK);
		} catch (IllegalArgumentException e) {
			return new ResponseEntity<>(new Response<>(null, StatusEnum.FAILURE, e.getMessage()),
					HttpStatus.BAD_REQUEST);
		} catch (Exception e) {
			throw new PatientServerException(e.getMessage(), e);
		}

	}

	@DeleteMapping("/address/delete/{addressId}")
	public ResponseEntity<Response<String>> deleteAddress(@PathVariable Long addressId) {
		try {
			userService.deleteAddress(addressId);
			return new ResponseEntity<>(new Response<>(null, StatusEnum.SUCCESS, null), HttpStatus.OK);
		} catch (IllegalArgumentException e) {
			return new ResponseEntity<>(new Response<>(null, StatusEnum.FAILURE, e.getMessage()),
					HttpStatus.BAD_REQUEST);
		} catch (Exception e) {
			throw new PatientServerException(e.getMessage(), e);
		}

	}

	@PostMapping("/summeryrecord")
	public ResponseEntity<Response<User>> patientSummeryRecord(@RequestBody ForUser forUser) {
		try {
			String moduleName = httpServletRequest.getHeader("moduleName");
			RELATIONSHIP_TYPE relationship_TYPE = RELATIONSHIP_TYPE.valueOf(moduleName);
			User user = userService.patientSummeryRecord(forUser, relationship_TYPE);
			return new ResponseEntity<>(new Response<>(user, StatusEnum.SUCCESS, null), HttpStatus.OK);
		} catch (IllegalArgumentException e) {
			return new ResponseEntity<>(new Response<>(null, StatusEnum.FAILURE, e.getMessage()),
					HttpStatus.BAD_REQUEST);
		} catch (Exception e) {
			throw new PatientServerException(e.getMessage(), e);
		}

	}

	@PostMapping("/patientrecordforpharmacy")
	public ResponseEntity<Response<User>> patientRecordForPharmacy(@RequestBody ForUser forUser) {
		try {
			String moduleName = httpServletRequest.getHeader("moduleName");
			RELATIONSHIP_TYPE relationship_TYPE = RELATIONSHIP_TYPE.valueOf(moduleName);
			User user = userService.patientRecordForPharmacy(forUser, relationship_TYPE);
			return new ResponseEntity<>(new Response<>(user, StatusEnum.SUCCESS, null), HttpStatus.OK);
		} catch (IllegalArgumentException e) {
			return new ResponseEntity<>(new Response<>(null, StatusEnum.FAILURE, e.getMessage()),
					HttpStatus.BAD_REQUEST);
		} catch (Exception e) {
			throw new PatientServerException(e.getMessage(), e);
		}

	}

	@PostMapping("/patientRecordForCaretaker")
	public ResponseEntity<Response<CaretakerResponseDTO>> patientRecordForCaretaker(@RequestParam Long caretakerId) {
		try {

			CaretakerResponseDTO caretakerResponseDTO = userService.patientRecordForCaretaker(caretakerId);
			return new ResponseEntity<>(new Response<>(caretakerResponseDTO, StatusEnum.SUCCESS, null), HttpStatus.OK);
		} catch (IllegalArgumentException e) {
			return new ResponseEntity<>(new Response<>(null, StatusEnum.FAILURE, e.getMessage()),
					HttpStatus.BAD_REQUEST);
		} catch (Exception e) {
			throw new PatientServerException(e.getMessage(), e);
		}

	}

	@DeleteMapping("/personaldetails/delete/{personaldetailId}")
	public ResponseEntity<Response<String>> deletePersonaldetail(@PathVariable Long personaldetailId) {
		try {
			userService.deletePersonaldetail(personaldetailId);

			return new ResponseEntity<>(new Response<>(null, StatusEnum.SUCCESS, null), HttpStatus.OK);
		} catch (IllegalArgumentException e) {
			return new ResponseEntity<>(new Response<>(null, StatusEnum.FAILURE, e.getMessage()),
					HttpStatus.BAD_REQUEST);
		} catch (Exception e) {
			throw new PatientServerException(e.getMessage(), e);
		}

	}

	@PostMapping(path = "/profilepic/{userId}")
	public ResponseEntity<Response<String>> uploadProfilePic(@PathVariable Long userId,
			@RequestParam(name = "profilePic", required = false) MultipartFile profilePic) {
		try {
			userService.uploadProfilePic(userId, profilePic);
			return new ResponseEntity<>(new Response<>(null, StatusEnum.SUCCESS, null), HttpStatus.OK);
		} catch (IllegalArgumentException e) {
			return new ResponseEntity<>(new Response<>(null, StatusEnum.FAILURE, e.getMessage()),
					HttpStatus.BAD_REQUEST);
		} catch (Exception e) {
			throw new PatientServerException(e.getMessage(), e);
		}

	}

	public IUserService getUserService() {
		return userService;
	}

	public void setUserService(IUserService userService) {
		this.userService = userService;
	}

	public IPatientAssociation getPatientassocationServiceImpl() {
		return patientassocationServiceImpl;
	}

	public void setPatientassocationServiceImpl(IPatientAssociation patientassocationServiceImpl) {
		this.patientassocationServiceImpl = patientassocationServiceImpl;
	}

}