package org.secondopinion.pharmacy.controller;

import java.util.List;
import java.util.Map;


import org.secondopinion.pharmacy.dto.FillPrescriptionRequestDTO;
import org.secondopinion.pharmacy.dto.Invoice;
import org.secondopinion.pharmacy.dto.PrescriptionFillRequestUpdateDTO;
import org.secondopinion.pharmacy.dto.Prescriptionfillrequest;
import org.secondopinion.pharmacy.dto.Prescriptionprice;
import org.secondopinion.pharmacy.dto.Shippingaddress;
import org.secondopinion.pharmacy.exception.PharmacyServerException;
import org.secondopinion.pharmacy.service.IPrescriptionService;
import org.secondopinion.request.Response;
import org.secondopinion.request.Response.StatusEnum;
import org.secondopinion.utils.JSONHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;

@RestController
@RequestMapping("/prescription")
public class PrescriptionController {

	@Autowired
	private IPrescriptionService prescriptionService;

	@PostMapping(value = "/fillrequest/frompatient")
	public ResponseEntity<Response<String>> fillPrescriptionFromPatient(
			@RequestBody FillPrescriptionRequestDTO request) {
		try {
			Gson gson = JSONHelper.getGsonWithDateTime();

			String responseJson = gson.toJson(prescriptionService.fillPrescription(request));
			return new ResponseEntity<>(
					new Response<>(responseJson, StatusEnum.SUCCESS, "Prescription data filled successfully."),
					HttpStatus.OK);
		} catch (IllegalArgumentException e) {
			return new ResponseEntity<>(new Response<>(null, StatusEnum.FAILURE, e.getMessage()),
					HttpStatus.BAD_REQUEST);
		} catch (Exception e) {
			throw new PharmacyServerException(e.getMessage(), e);
		}
	}

	@PostMapping(value = "/fillrequest/fromdoctor")
	public ResponseEntity<Response<String>> fillPrescriptionFromDoctor(
			@RequestBody FillPrescriptionRequestDTO request) {
		try {
			Gson gson = JSONHelper.getGsonWithDateTime();
			String responseJson = gson.toJson(prescriptionService.fillPrescription(request));
			return new ResponseEntity<>(
					new Response<>(responseJson, StatusEnum.SUCCESS, "Prescription data filled successfully."),
					HttpStatus.OK);
		} catch (IllegalArgumentException e) {
			return new ResponseEntity<>(new Response<>(null, StatusEnum.FAILURE, e.getMessage()),
					HttpStatus.BAD_REQUEST);
		} catch (Exception e) {
			throw new PharmacyServerException(e.getMessage(), e);
		}
	}

	@PostMapping(value = "/update/prices")
	public ResponseEntity<Response<String>> updatePrescriptionfillrequestWithPrices(
			@RequestBody PrescriptionFillRequestUpdateDTO fillRequestUpdateDTO) {
		try {
			prescriptionService.updatePrescriptionfillrequestWithPrices(fillRequestUpdateDTO);
			return new ResponseEntity<>(
					new Response<>(null, StatusEnum.SUCCESS, "Prescription prices updated successfully."),
					HttpStatus.OK);
		} catch (IllegalArgumentException e) {
			return new ResponseEntity<>(new Response<>(null, StatusEnum.FAILURE, e.getMessage()),
					HttpStatus.BAD_REQUEST);
		} catch (Exception e) {
			throw new PharmacyServerException(e.getMessage(), e);
		}
	}

	@GetMapping(value = "/newrequests/{pharmacyId}/{pageNum}/{maxResults}")
	public ResponseEntity<Response<List<Prescriptionfillrequest>>> getNewPrescriptionFillRequests(
			@PathVariable("pharmacyId") Long pharmacyId, @PathVariable("pageNum") Integer pageNum,
			@PathVariable("maxResults") Integer maxResults) {
		try {
			Response<List<Prescriptionfillrequest>> prescriptionfillrequests = prescriptionService
					.getNewPrescriptionFillRequests(pharmacyId, pageNum, maxResults);
			return new ResponseEntity<>(prescriptionfillrequests, HttpStatus.OK);
		} catch (IllegalArgumentException e) {
			return new ResponseEntity<>(new Response<>(null, StatusEnum.FAILURE, e.getMessage()),
					HttpStatus.BAD_REQUEST);
		} catch (Exception e) {
			throw new PharmacyServerException(e.getMessage(), e);
		}
	}

	@GetMapping(value = "/all/{pharmacyId}/{pageNum}/{maxResults}")
	public ResponseEntity<Response<List<Prescriptionfillrequest>>> getAllPrescriptionFillRequests(
			@PathVariable("pharmacyId") Long pharmacyId, @PathVariable("pageNum") Integer pageNum,
			@PathVariable("maxResults") Integer maxResults) {
		try {
			Response<List<Prescriptionfillrequest>> prescriptionfillrequests = prescriptionService
					.getAllPrescriptionFillRequests(pharmacyId, pageNum, maxResults);
			return new ResponseEntity<>(prescriptionfillrequests, HttpStatus.OK);
		} catch (IllegalArgumentException e) {
			return new ResponseEntity<>(new Response<>(null, StatusEnum.FAILURE, e.getMessage()),
					HttpStatus.BAD_REQUEST);
		} catch (Exception e) {
			throw new PharmacyServerException(e.getMessage(), e);
		}
	}

	@GetMapping(value = "/prescriptionprice/bypfr/{prescriptionFillRequestId}")
	public ResponseEntity<Response<List<Prescriptionprice>>> getPrescriptionPricesByPFRId(
			@PathVariable Long prescriptionFillRequestId) {
		try {
			List<Prescriptionprice> prescriptionprices = prescriptionService
					.getPrescriptionPricesByPFRId(prescriptionFillRequestId);
			return new ResponseEntity<>(new Response<>(prescriptionprices, StatusEnum.SUCCESS, null), HttpStatus.OK);
		} catch (IllegalArgumentException e) {
			return new ResponseEntity<>(new Response<>(null, StatusEnum.FAILURE, e.getMessage()),
					HttpStatus.BAD_REQUEST);
		} catch (Exception e) {
			throw new PharmacyServerException(e.getMessage(), e);
		}
	}

	@GetMapping(value = "/invoice/bypfr/{prescriptionFillRequestId}")
	public ResponseEntity<Response<Invoice>> getInvoicesByPFRId(@PathVariable Long prescriptionFillRequestId) {
		try {
			Invoice invoices = prescriptionService.getInvoicesByPFRId(prescriptionFillRequestId);
			return new ResponseEntity<>(new Response<>(invoices, StatusEnum.SUCCESS, null), HttpStatus.OK);
		} catch (IllegalArgumentException e) {
			return new ResponseEntity<>(new Response<>(null, StatusEnum.FAILURE, e.getMessage()),
					HttpStatus.BAD_REQUEST);
		} catch (Exception e) {
			throw new PharmacyServerException(e.getMessage(), e);
		}
	}

	@GetMapping(value = "/shippingaddress/bypfr/{prescriptionFillRequestId}")
	public ResponseEntity<Response<List<Shippingaddress>>> getShippingAddressesByPFRId(
			@PathVariable Long prescriptionFillRequestId) {
		try {
			List<Shippingaddress> shippingaddresses = prescriptionService
					.getShippingAddressesByPFRId(prescriptionFillRequestId);
			return new ResponseEntity<>(new Response<>(shippingaddresses, StatusEnum.SUCCESS, null), HttpStatus.OK);
		} catch (IllegalArgumentException e) {
			return new ResponseEntity<>(new Response<>(null, StatusEnum.FAILURE, e.getMessage()),
					HttpStatus.BAD_REQUEST);
		} catch (Exception e) {
			throw new PharmacyServerException(e.getMessage(), e);
		}
	}
	@GetMapping("/getPharmacyReports/{phamacyId}")
	public ResponseEntity<Response<Map<String, Object>>> getPharmacyReports(@PathVariable Long phamacyId) {
		try {
			Map<String, Object> myReports = prescriptionService.getPharmacyReports(phamacyId);
			return new ResponseEntity<>(
					new Response<>(myReports, StatusEnum.SUCCESS, "My Reports data fetched successfully."),
					HttpStatus.OK);
		} catch (IllegalArgumentException e) {
			return new ResponseEntity<>(new Response<>(null, StatusEnum.FAILURE, e.getMessage()),
					HttpStatus.BAD_REQUEST);
		} catch (Exception e) {
			throw new PharmacyServerException(e.getMessage(), e);
		}

	}
}
