
package org.secondopinion.patient.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.secondopinion.patient.dto.ForUser;
import org.secondopinion.patient.dto.MedcalReportSearchRequest;
import org.secondopinion.patient.dto.Medicalreports;
import org.secondopinion.patient.dto.RELATIONSHIP_TYPE;
import org.secondopinion.patient.dto.UploadMedicalReportRequest;
import org.secondopinion.patient.exception.PatientServerException;
import org.secondopinion.patient.service.IScheduleService;
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

@RestController
@RequestMapping("/Schedule")
@Api("/Schedule")
public class ScheduleController {

	@Autowired
	private IScheduleService scheduleService;
	@Autowired
	private HttpServletRequest httpServletRequest;
	@GetMapping("/medicalreports/retrieve/id/{medicalreportsId}")
	public ResponseEntity<Response<Medicalreports>> retrieveMedicalReportById(
			@PathVariable Long medicalreportsId) {

		try {

			return new ResponseEntity<>(new Response<>(scheduleService.retrieveMedicalReportById(medicalreportsId),
					StatusEnum.SUCCESS, null), HttpStatus.OK);

		} catch (IllegalArgumentException e) {
			return new ResponseEntity<>(new Response<>(null, StatusEnum.FAILURE, e.getMessage()),
					HttpStatus.BAD_REQUEST);
		} catch (Exception e) {
			throw new PatientServerException(e.getMessage(), e);
		}
	}

	@GetMapping("/medicalreports/document/get")
	public ResponseEntity<Response<byte[]>> getMedicalreportsDocument(
			@RequestParam Long medicalreportId) {

		try {
			return new ResponseEntity<>(new Response<>(scheduleService.getMedicalReportsDocuments(medicalreportId),
					StatusEnum.SUCCESS, null), HttpStatus.OK);

		} catch (Exception ex) {
			throw new PatientServerException(ex.getMessage(), ex);
		}
	}

	@PostMapping("/medicalreports/retrieve/user/{pageNum}/{maxResults}")
	public ResponseEntity<Response<List<Medicalreports>>> retrieveMedicalReportByUserId(@RequestBody ForUser forUser,
			@PathVariable("pageNum") Integer pageNum, @PathVariable("maxResults") Integer maxResults) {

		try {
			String moduleName = httpServletRequest.getHeader("moduleName");
			RELATIONSHIP_TYPE relationship_TYPE = RELATIONSHIP_TYPE.valueOf(moduleName);
			
			Response<List<Medicalreports>> medicalreports = scheduleService.retrieveMedicalReportByUserId(forUser,
					pageNum, maxResults,relationship_TYPE);
			return new ResponseEntity<>(medicalreports, HttpStatus.OK);
		} catch (IllegalArgumentException e) {
			return new ResponseEntity<>(new Response<>(null, StatusEnum.FAILURE, e.getMessage()),
					HttpStatus.BAD_REQUEST);
		} catch (Exception e) {
			throw new PatientServerException(e.getMessage(), e);
		}

	}

	@PostMapping("/medicalreports/retrieve/appointment/{appointmentId}/{pageNum}/{maxResults}")
	public ResponseEntity<Response<List<Medicalreports>>> getMedicalReportsForAppointment(@RequestBody ForUser forUser,
			@PathVariable("appointmentId") Long appointmentId, @PathVariable("pageNum") Integer pageNum,
			@PathVariable("maxResults") Integer maxResults) {
		try {
			String moduleName = httpServletRequest.getHeader("moduleName");
			RELATIONSHIP_TYPE relationship_TYPE = RELATIONSHIP_TYPE.valueOf(moduleName);
			Response<List<Medicalreports>> medicalreports = scheduleService.getMedicalReportsForAppointment(forUser,
					appointmentId, pageNum, maxResults,relationship_TYPE);
			return new ResponseEntity<>(medicalreports, HttpStatus.OK);
		} catch (IllegalArgumentException e) {
			return new ResponseEntity<>(new Response<>(null, StatusEnum.FAILURE, e.getMessage()),
					HttpStatus.BAD_REQUEST);
		} catch (Exception e) {
			throw new PatientServerException(e.getMessage(), e);
		}

	}

	@PostMapping(value = "/medicalreports/add", consumes = { MediaType.APPLICATION_JSON_VALUE,
			MediaType.MULTIPART_FORM_DATA_VALUE, MediaType.APPLICATION_OCTET_STREAM_VALUE })
	public ResponseEntity<Response<String>> addMedicalReportForUser(
			@RequestParam("uploadMedicalReportRequestJson") String uploadMedicalReportRequestJson,
			@RequestParam(name = "medicalreportfile", required = false) MultipartFile medicalreportfile) {

		try {
			String moduleName = httpServletRequest.getHeader("moduleName");
			RELATIONSHIP_TYPE relationship_TYPE = RELATIONSHIP_TYPE.valueOf(moduleName);
			Gson gson = JSONHelper.getGsonWithDate();
			UploadMedicalReportRequest uploadMedicalReportRequest = gson.fromJson(uploadMedicalReportRequestJson,
					UploadMedicalReportRequest.class);

			scheduleService.uploadReport(uploadMedicalReportRequest.getForUser(),
					uploadMedicalReportRequest.getMedicalreports(), medicalreportfile,relationship_TYPE);
			return new ResponseEntity<>(new Response<>(null, StatusEnum.SUCCESS, null), HttpStatus.OK);
		} catch (IllegalArgumentException e) {
			return new ResponseEntity<>(new Response<>(null, StatusEnum.FAILURE, e.getMessage()),
					HttpStatus.BAD_REQUEST);
		} catch (Exception e) {
			throw new PatientServerException(e.getMessage(), e);
		}

	}
	
	@PostMapping(value = "/uploadReportByDiagnosticCenter/add/fromdiagnostic")
	public ResponseEntity<Response<String>> uploadReportByDiagnosticCenter(@RequestParam Long diagnosticCenterAppointmentId, 
			@RequestParam String documentLocation,
			@RequestParam String documentName) {
		try {
			Medicalreports medicalReports = scheduleService.uploadReportByDiagnosticCenter(diagnosticCenterAppointmentId,
					documentLocation, documentName);
			return new ResponseEntity<>(new Response<>(medicalReports.toString(), StatusEnum.SUCCESS, "uploaded successfully"), HttpStatus.OK);
		} catch (IllegalArgumentException e) {
			return new ResponseEntity<>(new Response<>(null, StatusEnum.FAILURE, e.getMessage()),
					HttpStatus.BAD_REQUEST);
		} catch (Exception e) {
			throw new PatientServerException(e.getMessage(), e);
		}
	}

	@PostMapping("/medicalreports/search")
	public ResponseEntity<Response<List<Medicalreports>>> getAllMedicalreportsBySearchCritieria(
			@RequestBody MedcalReportSearchRequest medcalReportSearchRequest) {
		try {
			String moduleName = httpServletRequest.getHeader("moduleName");
			RELATIONSHIP_TYPE relationship_TYPE = RELATIONSHIP_TYPE.valueOf(moduleName);
			Response<List<Medicalreports>> medicalreports = scheduleService.getAllMedicalreportsBySearchCritieria(
					medcalReportSearchRequest, medcalReportSearchRequest.getForUser(),relationship_TYPE);
			return new ResponseEntity<>(medicalreports, HttpStatus.OK);
		} catch (IllegalArgumentException e) {
			return new ResponseEntity<>(new Response<>(null, StatusEnum.FAILURE, e.getMessage()),
					HttpStatus.BAD_REQUEST);
		} catch (Exception e) {
			throw new PatientServerException(e.getMessage(), e);
		}

	}

	@DeleteMapping("/deletemedicalreports/{medicalreportId}")
	public ResponseEntity<Response<String>> deleteMedicalReports(
			@PathVariable Long medicalreportId) {

		try {
			scheduleService.deleteMedicalReports(medicalreportId);
			return new ResponseEntity<>(new Response<>(null, StatusEnum.SUCCESS, "medicalreports delete successfully"), HttpStatus.OK);
		} catch (IllegalArgumentException e) {
			return new ResponseEntity<>(new Response<>(null, StatusEnum.FAILURE, e.getMessage()),
					HttpStatus.BAD_REQUEST);
		} catch (Exception e) {
			throw new PatientServerException(e.getMessage(), e);
		}
	}

}
