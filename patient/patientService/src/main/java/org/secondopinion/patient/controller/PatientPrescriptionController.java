
package org.secondopinion.patient.controller;

import java.util.Collection;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.secondopinion.patient.dto.FillPrescriptionRequestDTO;
import org.secondopinion.patient.dto.Medicalprescription;
import org.secondopinion.patient.dto.MedicalprescriptionSearchCriteria;
import org.secondopinion.patient.dto.Medicaltest;
import org.secondopinion.patient.dto.Medication;
import org.secondopinion.patient.dto.MedicationDTO;
import org.secondopinion.patient.dto.MedicationUsageNewDTO;
import org.secondopinion.patient.dto.Medicationusage;
import org.secondopinion.patient.dto.MedicationusageDTO;
import org.secondopinion.patient.dto.PatientPriceUpdateDTO;
import org.secondopinion.patient.dto.Prescription;
import org.secondopinion.patient.dto.PrescriptionAllRespnceDTO;
import org.secondopinion.patient.dto.PrescriptionDTO;
import org.secondopinion.patient.dto.PrescriptionRespnceDTO;
import org.secondopinion.patient.exception.PatientServerException;
import org.secondopinion.patient.service.IPatientPrescriptionService;
import org.secondopinion.patient.service.helper.DoseTime;
import org.secondopinion.request.Response;
import org.secondopinion.request.Response.StatusEnum;
import org.secondopinion.utils.JSONHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.google.gson.Gson;

@RestController
@RequestMapping("/prescription")
public class PatientPrescriptionController {

	@Autowired
	private IPatientPrescriptionService prescriptionService;

	@Autowired
	private HttpServletRequest httpServletRequest;

	@PostMapping(path = "/save/medical/prescription/fromdoctor")
	public ResponseEntity<Response<String>> savePrescrptionFromDoctor(@RequestBody Prescription prescription) {
		try {

			prescriptionService.saveMedicalprescriptionFromDoctor(prescription, null);
			return new ResponseEntity<>(new Response<>(null, StatusEnum.SUCCESS, null), HttpStatus.OK);
		} catch (IllegalArgumentException e) {
			return new ResponseEntity<>(new Response<>(null, StatusEnum.FAILURE, e.getMessage()),
					HttpStatus.BAD_REQUEST);
		} catch (Exception e) {
			throw new PatientServerException(e.getMessage(), e);
		}

	}

	@PostMapping(path = "/save/medical/prescriptionimage/fromdoctor", consumes = { MediaType.APPLICATION_JSON_VALUE,
			MediaType.MULTIPART_FORM_DATA_VALUE, MediaType.APPLICATION_OCTET_STREAM_VALUE })
	public ResponseEntity<Response<String>> savePrescrptionFromDoctor(
			@RequestParam("userprescriptionJson") String userprescriptionJson,
			@RequestParam(name = "prescriptionPic", required = false) MultipartFile prescriptionPic) {
		try {
			Gson gson = JSONHelper.getGsonWithDate();
			Prescription prescription = gson.fromJson(userprescriptionJson, Prescription.class);
			prescriptionService.saveMedicalprescriptionFromDoctor(prescription, prescriptionPic);
			return new ResponseEntity<>(new Response<>("Prescrption Saved ", StatusEnum.SUCCESS, "Prescrption Saved "), HttpStatus.OK);
		} catch (IllegalArgumentException e) {
			return new ResponseEntity<>(new Response<>(null, StatusEnum.FAILURE, e.getMessage()),
					HttpStatus.BAD_REQUEST);
		} catch (Exception e) {
			throw new PatientServerException(e.getMessage(), e);
		}

	}
	
	@PostMapping(path = "/save/medical/prescriptionimage/frompatient", consumes = { MediaType.APPLICATION_JSON_VALUE,
			MediaType.MULTIPART_FORM_DATA_VALUE, MediaType.APPLICATION_OCTET_STREAM_VALUE })
	public ResponseEntity<Response<String>> savePrescrptionFromPatient(
			@RequestParam("userprescriptionJson") String userprescriptionJson,
			@RequestParam(name = "prescriptionPic", required = false) MultipartFile prescriptionPic) {
		try {
			Gson gson = JSONHelper.getGsonWithDate();
			Prescription prescription = gson.fromJson(userprescriptionJson, Prescription.class);
			prescriptionService.saveMedicalprescriptionFromPatient(prescription, prescriptionPic);
			return new ResponseEntity<>(new Response<>("Prescrption Saved ", StatusEnum.SUCCESS, "Prescrption Saved "), HttpStatus.OK);
		} catch (IllegalArgumentException e) {
			return new ResponseEntity<>(new Response<>(null, StatusEnum.FAILURE, e.getMessage()),
					HttpStatus.BAD_REQUEST);
		} catch (Exception e) {
			throw new PatientServerException(e.getMessage(), e);
		}

	}

	@PostMapping("/fillrequest")
	public ResponseEntity<Response<String>> saveFillrequest(
			@RequestBody FillPrescriptionRequestDTO fillPrescriptionRequestDTO) {
		try {

			prescriptionService.fillPrescriptionRequestToPharmacy(fillPrescriptionRequestDTO);
			return new ResponseEntity<>(new Response<>(null, StatusEnum.SUCCESS, null), HttpStatus.OK);
		} catch (IllegalArgumentException e) {
			return new ResponseEntity<>(new Response<>(null, StatusEnum.FAILURE, e.getMessage()),
					HttpStatus.BAD_REQUEST);
		} catch (Exception e) {
			throw new PatientServerException(e.getMessage(), e);
		}

	}

	@GetMapping("/getmedicalprescriptionbyid/{id}")
	public ResponseEntity<Response<Medicalprescription>> getByMedicalprescriptionById(@PathVariable Long id) {
		try {
			Medicalprescription medicalPrescription = prescriptionService.getByMedicalprescriptionId(id);
			return new ResponseEntity<>(new Response<>(medicalPrescription, StatusEnum.SUCCESS, null), HttpStatus.OK);
		} catch (IllegalArgumentException e) {
			return new ResponseEntity<>(new Response<>(null, StatusEnum.FAILURE, e.getMessage()),
					HttpStatus.BAD_REQUEST);
		} catch (Exception e) {
			throw new PatientServerException(e.getMessage(), e);
		}

	}

	@GetMapping("/getprecriptionbyuserId")
	public ResponseEntity<Response<Collection<PrescriptionDTO>>> getPrecriptionByUserId(@RequestParam Long userId) {
		try {
			Collection<PrescriptionDTO> prescriptions = prescriptionService.getPrecriptionByUserId(userId);
			return new ResponseEntity<>(
					new Response<>(prescriptions, StatusEnum.SUCCESS, "prescrption fetched successfully"),
					HttpStatus.OK);
		} catch (IllegalArgumentException e) {
			return new ResponseEntity<>(new Response<>(null, StatusEnum.FAILURE, e.getMessage()),
					HttpStatus.BAD_REQUEST);
		} catch (Exception e) {
			throw new PatientServerException(e.getMessage(), e);
		}

	}

	@PostMapping(path = "/medication/save")
	public ResponseEntity<Response<String>> saveMedication(@RequestBody Medication medication) {
		try {
			prescriptionService.addMedicinesToThePrecription(medication);
			return new ResponseEntity<>(new Response<>(null, StatusEnum.SUCCESS, null), HttpStatus.OK);
		} catch (IllegalArgumentException e) {
			return new ResponseEntity<>(new Response<>(null, StatusEnum.FAILURE, e.getMessage()),
					HttpStatus.BAD_REQUEST);
		} catch (Exception e) {
			throw new PatientServerException(e.getMessage(), e);
		}

	}

	@PostMapping(path = "/addMedicinesToThePrecriptionForImage")
	public ResponseEntity<Response<String>> addMedicinesToThePrecriptionForImage(
			@RequestBody PatientPriceUpdateDTO medication) {
		try {
			prescriptionService.addMedicinesToThePrecriptionForImage(medication);
			return new ResponseEntity<>(new Response<>(null, StatusEnum.SUCCESS, null), HttpStatus.OK);
		} catch (IllegalArgumentException e) {
			return new ResponseEntity<>(new Response<>(null, StatusEnum.FAILURE, e.getMessage()),
					HttpStatus.BAD_REQUEST);
		} catch (Exception e) {
			throw new PatientServerException(e.getMessage(), e);
		}

	}

	@PostMapping("/medication/view/all")
	public ResponseEntity<Response<List<Medication>>> getAllMedications(
			@RequestBody MedicalprescriptionSearchCriteria medicalprescriptionSearchCriteria) {

		try {
			Response<List<Medication>> medicalprescriptions = prescriptionService
					.getAllMedications(medicalprescriptionSearchCriteria);
			return new ResponseEntity<>(medicalprescriptions, HttpStatus.OK);
		} catch (IllegalArgumentException e) {
			return new ResponseEntity<>(new Response<>(null, StatusEnum.FAILURE, e.getMessage()),
					HttpStatus.BAD_REQUEST);
		} catch (Exception e) {
			throw new PatientServerException(e.getMessage(), e);
		}

	}

	@PostMapping("/getMedicationusageBymedicationId")
	public ResponseEntity<List<Medicationusage>> getMedicationusageBymedicationId(@RequestParam Long medicationId) {

		try {
			List<Medicationusage> medicalprescription = prescriptionService
					.getMedicationusageBymedicationId(medicationId);
			return new ResponseEntity<>(medicalprescription, HttpStatus.OK);
		} catch (IllegalArgumentException e) {
			return new ResponseEntity<>(null, HttpStatus.OK);
		} catch (Exception e) {
			throw new PatientServerException(e.getMessage(), e);
		}

	}

	@PostMapping(path = "/medicaltest/save", consumes = { MediaType.APPLICATION_JSON_VALUE,
			MediaType.MULTIPART_FORM_DATA_VALUE, MediaType.APPLICATION_OCTET_STREAM_VALUE })
	public ResponseEntity<Response<String>> savemedicaltest(@RequestParam("medicaltestJson") String medicaltestJson,
			@RequestParam(name = "multipartFile", required = false) MultipartFile multipartFile) {
		try {
			Gson gson = JSONHelper.getGsonWithDate();
			Medicaltest medicaltest = gson.fromJson(medicaltestJson, Medicaltest.class);

			prescriptionService.uploadTestDocument(medicaltest.getMedicalTestPrescriptionId(), medicaltest,
					multipartFile);
			return new ResponseEntity<>(new Response<>(null, StatusEnum.SUCCESS, null), HttpStatus.OK);
		} catch (IllegalArgumentException e) {
			return new ResponseEntity<>(new Response<>(null, StatusEnum.FAILURE, e.getMessage()),
					HttpStatus.BAD_REQUEST);
		} catch (Exception e) {
			throw new PatientServerException(e.getMessage(), e);
		}

	}

	@PostMapping(path = "/uploadmedicalTestForDiagnosticcenter", consumes = { MediaType.APPLICATION_JSON_VALUE,
			MediaType.MULTIPART_FORM_DATA_VALUE, MediaType.APPLICATION_OCTET_STREAM_VALUE })
	public ResponseEntity<Response<String>> uploadmedicalTestForDiagnosticcenter(
			@RequestParam("medicaltestJson") String medicaltestJson,
			@RequestParam(name = "multipartFile", required = false) MultipartFile multipartFile) {
		try {
			Gson gson = JSONHelper.getGsonWithDate();
			Medicaltest medicaltest = gson.fromJson(medicaltestJson, Medicaltest.class);

			prescriptionService.uploadmedicalTestForDiagnosticcenter(medicaltest.getMedicalTestPrescriptionId(),
					medicaltest, multipartFile);
			return new ResponseEntity<>(new Response<>(null, StatusEnum.SUCCESS, null), HttpStatus.OK);
		} catch (IllegalArgumentException e) {
			return new ResponseEntity<>(new Response<>(null, StatusEnum.FAILURE, e.getMessage()),
					HttpStatus.BAD_REQUEST);
		} catch (Exception e) {
			throw new PatientServerException(e.getMessage(), e);
		}

	}

	@PostMapping("/medicaltest/view/all")
	public ResponseEntity<Response<List<Medicaltest>>> getAllMedicaltests(
			@RequestBody MedicalprescriptionSearchCriteria medicalprescriptionSearchCriteria) {

		try {
			Response<List<Medicaltest>> medicalprescriptions = prescriptionService
					.getAllMedicaltests(medicalprescriptionSearchCriteria);
			return new ResponseEntity<>(medicalprescriptions, HttpStatus.OK);
		} catch (IllegalArgumentException e) {
			return new ResponseEntity<>(new Response<>(null, StatusEnum.FAILURE, e.getMessage()),
					HttpStatus.BAD_REQUEST);
		} catch (Exception e) {
			throw new PatientServerException(e.getMessage(), e);
		}

	}

	@PostMapping("/updateMedicationusage")
	public ResponseEntity<Response<String>> updateMedicationusage(@RequestBody Medicationusage medicationusageDTO) {

		try {
			prescriptionService.updateMedicationusage(medicationusageDTO);
			return new ResponseEntity<>(new Response<>(null, StatusEnum.SUCCESS, null), HttpStatus.OK);
		} catch (IllegalArgumentException e) {
			return new ResponseEntity<>(new Response<>(null, StatusEnum.FAILURE, e.getMessage()),
					HttpStatus.BAD_REQUEST);
		} catch (Exception e) {
			throw new PatientServerException(e.getMessage(), e);
		}
	}

	@PostMapping("/getMedicationsForTheDay")
	public ResponseEntity<Response<Collection<MedicationUsageNewDTO>>> getMedicationsForTheDay(
			@RequestBody MedicationUsageNewDTO medicationusageDTO) {

		try {
			Collection<MedicationUsageNewDTO> dbmedicationusageDTO = prescriptionService
					.getMedicationsForTheDay(medicationusageDTO);
			return new ResponseEntity<>(new Response<>(dbmedicationusageDTO, StatusEnum.SUCCESS, null), HttpStatus.OK);
		} catch (IllegalArgumentException e) {
			return new ResponseEntity<>(new Response<>(null, StatusEnum.FAILURE, e.getMessage()),
					HttpStatus.BAD_REQUEST);
		} catch (Exception e) {
			throw new PatientServerException(e.getMessage(), e);
		}
	}

	@PostMapping("/is/medication/taken/orNot")
	public ResponseEntity<Response<Boolean>> isMedicationTakenOrNot(@RequestParam Long UserId,
			@RequestParam Long medicationId, @RequestBody DoseTime doseTime) {

		try {
			boolean data = prescriptionService.isMedicationTakenOrNot(UserId, doseTime, medicationId);
			return new ResponseEntity<>(new Response<>(data, StatusEnum.SUCCESS, null), HttpStatus.OK);
		} catch (IllegalArgumentException e) {
			return new ResponseEntity<>(new Response<>(null, StatusEnum.FAILURE, e.getMessage()),
					HttpStatus.BAD_REQUEST);
		} catch (Exception e) {
			throw new PatientServerException(e.getMessage(), e);
		}

	}

	@PostMapping("/getprescriptionswithmedaction")
	public ResponseEntity<Response<PrescriptionRespnceDTO>> getMedicalPrescriptionDetails(
			@RequestParam Long medicalPresciptionId) {

		try {
			PrescriptionRespnceDTO medicalprescriptions = prescriptionService
					.getMedicalPrescriptionDetails(medicalPresciptionId);
			return new ResponseEntity<>(new Response<>(medicalprescriptions, StatusEnum.SUCCESS, null), HttpStatus.OK);
		} catch (IllegalArgumentException e) {
			return new ResponseEntity<>(new Response<>(null, StatusEnum.FAILURE, e.getMessage()),
					HttpStatus.BAD_REQUEST);
		} catch (Exception e) {
			throw new PatientServerException(e.getMessage(), e);
		}

	}

	@PostMapping("/getallprescription")
	public ResponseEntity<Response<PrescriptionAllRespnceDTO>> getAllPrescriptions() {

		try {
			Long userId = new Long(httpServletRequest.getHeader("forUserId"));
			PrescriptionAllRespnceDTO medicalprescriptions = prescriptionService.geAlltMedicalPrescription(userId);
			return new ResponseEntity<>(new Response<>(medicalprescriptions, StatusEnum.SUCCESS, null), HttpStatus.OK);
		} catch (IllegalArgumentException e) {
			return new ResponseEntity<>(new Response<>(null, StatusEnum.FAILURE, e.getMessage()),
					HttpStatus.BAD_REQUEST);
		} catch (Exception e) {
			throw new PatientServerException(e.getMessage(), e);
		}

	}

	@PostMapping("/getmedicaltestprescriptiondetails")
	public ResponseEntity<Response<PrescriptionRespnceDTO>> getMedicalTestPrescriptionDetails(
			@RequestParam Long medicalTestPresciptionId) {

		try {
			PrescriptionRespnceDTO prescription = prescriptionService
					.getMedicalTestPrescriptionDetails(medicalTestPresciptionId);
			return new ResponseEntity<>(new Response<>(prescription, StatusEnum.SUCCESS, null), HttpStatus.OK);
		} catch (IllegalArgumentException e) {
			return new ResponseEntity<>(new Response<>(null, StatusEnum.FAILURE, e.getMessage()),
					HttpStatus.BAD_REQUEST);
		} catch (Exception e) {
			throw new PatientServerException(e.getMessage(), e);
		}

	}

	@PostMapping("/getPrescriptionDetailsById")
	public ResponseEntity<Response<Prescription>> getPrescriptionDetailsById(@RequestParam Long presciptionId) {

		try {
			Prescription prescription = prescriptionService.getPrescriptionDetailsById(presciptionId);
			return new ResponseEntity<>(new Response<>(prescription, StatusEnum.SUCCESS, null), HttpStatus.OK);
		} catch (IllegalArgumentException e) {
			return new ResponseEntity<>(new Response<>(null, StatusEnum.FAILURE, e.getMessage()),
					HttpStatus.BAD_REQUEST);
		} catch (Exception e) {
			throw new PatientServerException(e.getMessage(), e);
		}

	}
	

	@PostMapping("/getprescriptiondetailsBydoctorappointmentIdanduserid")
	public ResponseEntity<Response<Prescription>> getPrescriptionDetailsBydoctorAppointmentId(@RequestParam Long doctorAppointmentId,@RequestParam Long userId) {

		try {
			Prescription prescription = prescriptionService.getPrescriptionDetailsBydoctorAppointmentId(doctorAppointmentId,userId);
			return new ResponseEntity<>(new Response<>(prescription, StatusEnum.SUCCESS, null), HttpStatus.OK);
		} catch (IllegalArgumentException e) {
			return new ResponseEntity<>(new Response<>(null, StatusEnum.FAILURE, e.getMessage()),
					HttpStatus.BAD_REQUEST);
		} catch (Exception e) {
			throw new PatientServerException(e.getMessage(), e);
		}

	}

	@PostMapping("/getprescriptionBydoctorappointmentId")
	public ResponseEntity<Response<Prescription>> getPrescriptionBydoctorAppointmentId(@RequestParam Long doctorAppointmentId) {

		try {
			Prescription prescription = prescriptionService.getPrescriptionBydoctorAppointmentId(doctorAppointmentId);
			return new ResponseEntity<>(new Response<>(prescription, StatusEnum.SUCCESS, null), HttpStatus.OK);
		} catch (IllegalArgumentException e) {
			return new ResponseEntity<>(new Response<>(null, StatusEnum.FAILURE, e.getMessage()),
					HttpStatus.BAD_REQUEST);
		} catch (Exception e) {
			throw new PatientServerException(e.getMessage(), e);
		}

	}
}
