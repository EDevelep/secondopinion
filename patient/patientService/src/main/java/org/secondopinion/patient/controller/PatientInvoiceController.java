package org.secondopinion.patient.controller;

import java.util.List;
import java.util.Map;

import org.secondopinion.patient.dto.Invoice;
import org.secondopinion.patient.dto.Invoice.ViewInvoiceEnum;
import org.secondopinion.patient.dto.InvoiceSearchDTO;
import org.secondopinion.patient.dto.ViewInvoiceEnumRequset;
import org.secondopinion.patient.exception.PatientServerException;
import org.secondopinion.patient.service.IPatientInvoice;
import org.secondopinion.request.Response;
import org.secondopinion.request.Response.StatusEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/invoice")
public class PatientInvoiceController {

	@Autowired
	private IPatientInvoice patientInvoice;

	@GetMapping(value = "/get/id/{invoiceId}")
	public ResponseEntity<Response<List<Invoice>>> getAllInvoice(@PathVariable("invoiceId") Long invoiceId) {

		try {
			List<Invoice> invoices = patientInvoice.getAllInvoiceForUser(invoiceId);
			return new ResponseEntity<>(new Response<>(invoices, StatusEnum.SUCCESS, "Get Invoice data successfully"),
					HttpStatus.OK);
		} catch (IllegalArgumentException e) {
			return new ResponseEntity<>(new Response<>(null, StatusEnum.FAILURE, e.getMessage()),
					HttpStatus.BAD_REQUEST);
		} catch (Exception e) {
			throw new PatientServerException(e.getMessage(), e);
		}

	}

	@GetMapping(value = "/findInvoiceByuserId/id/{invoiceId}")
	public ResponseEntity<Response<Invoice>> findInvoiceByuserId(@PathVariable("invoiceId") Long invoiceId,
			@RequestParam Long UserId) {

		try {
			Invoice invoices = patientInvoice.findInvoiceByuserId(UserId, invoiceId);
			return new ResponseEntity<>(new Response<>(invoices, StatusEnum.SUCCESS, "Get Invoice data successfully"),
					HttpStatus.OK);
		} catch (IllegalArgumentException e) {
			return new ResponseEntity<>(new Response<>(null, StatusEnum.FAILURE, e.getMessage()),
					HttpStatus.BAD_REQUEST);
		} catch (Exception e) {
			throw new PatientServerException(e.getMessage(), e);
		}

	}

	@PostMapping(value = "/get/request-payments/{pageNum}/{maxResults}")
	public ResponseEntity<Response<List<Invoice>>> getRequestPayments(@PathVariable Integer pageNum,
			@PathVariable Integer maxResults, @RequestParam Long UserId) {

		try {
			Response<List<Invoice>> invoice = patientInvoice.getRequestPayments(UserId, pageNum, maxResults);
			return new ResponseEntity<>(invoice, HttpStatus.OK);
		} catch (IllegalArgumentException e) {
			return new ResponseEntity<>(new Response<>(null, StatusEnum.FAILURE, e.getMessage()),
					HttpStatus.BAD_REQUEST);
		} catch (Exception e) {
			throw new PatientServerException(e.getMessage(), e);
		}

	}

	@PostMapping(value = "/getRequestPaymentsForAll")
	public ResponseEntity<Response<Map<ViewInvoiceEnum, Response<List<Invoice>>>>> getRequestPaymentsForAll(
			@RequestBody ViewInvoiceEnumRequset viewInvoiceEnumRequset,@RequestParam Long UserId) {

		try {
			viewInvoiceEnumRequset.setPatientId(UserId);
			Map<ViewInvoiceEnum, Response<List<Invoice>>> invoice = patientInvoice
					.getRequestPaymentsForAll(viewInvoiceEnumRequset);
			return new ResponseEntity<>(new Response<>(invoice, StatusEnum.SUCCESS, null), HttpStatus.OK);
		} catch (IllegalArgumentException e) {
			return new ResponseEntity<>(new Response<>(null, StatusEnum.FAILURE, e.getMessage()),
					HttpStatus.BAD_REQUEST);
		} catch (Exception e) {
			throw new PatientServerException(e.getMessage(), e);
		}

	}

	@PostMapping(value = "/get/request-payments/{pageNum}/{maxResults}/{invoicetype}")
	public ResponseEntity<Response<List<Invoice>>> getRequestPaymentDone(@PathVariable Integer pageNum,
			@PathVariable Integer maxResults, @RequestParam Long UserId, @PathVariable String invoicetype) {

		try {
			Response<List<Invoice>> invoice = patientInvoice.getRequestPaymentDone(UserId, invoicetype, pageNum,
					maxResults);
			return new ResponseEntity<>(invoice, HttpStatus.OK);
		} catch (IllegalArgumentException e) {
			return new ResponseEntity<>(new Response<>(null, StatusEnum.FAILURE, e.getMessage()),
					HttpStatus.BAD_REQUEST);
		} catch (Exception e) {
			throw new PatientServerException(e.getMessage(), e);
		}

	}

	@PostMapping(value = "/get/bycriteria")
	public ResponseEntity<Response<List<Invoice>>> getInvoiceSerchCritera(
			@RequestBody InvoiceSearchDTO invoiceSearchDTO) {

		try {
			Response<List<Invoice>> invoice = patientInvoice.getinvoiceForSerchcritiria(invoiceSearchDTO);
			return new ResponseEntity<>(invoice, HttpStatus.OK);
		} catch (IllegalArgumentException e) {
			return new ResponseEntity<>(new Response<>(null, StatusEnum.FAILURE, e.getMessage()),
					HttpStatus.BAD_REQUEST);
		} catch (Exception e) {
			throw new PatientServerException(e.getMessage(), e);
		}

	}

//invoice/updateshipping/entityinvoice/status
//invoice/update/type/status/requestpayment
	@PostMapping("/update/type/status/requestpayment")
	public ResponseEntity<Response<String>> updateInvoiceForPaymentByEntityInvoiceIdWithStatus(
			@RequestBody Invoice invoice) {

		try {
			patientInvoice.updateInvoiceForPaymentByEntityInvoiceIdWithStatus(invoice);
			return new ResponseEntity<>(new Response<>(null, StatusEnum.SUCCESS, "invoice updated successfully"),
					HttpStatus.OK);
		} catch (IllegalArgumentException e) {
			return new ResponseEntity<>(new Response<>(null, StatusEnum.FAILURE, e.getMessage()),
					HttpStatus.BAD_REQUEST);
		} catch (Exception e) {
			throw new PatientServerException(e.getMessage(), e);
		}
	}

	@PostMapping("/update/entityinvoice/status")
	public ResponseEntity<Response<String>> updateInvoiceByEntityInvoiceIdWithStatus(@RequestBody Invoice invoice) {

		try {
			patientInvoice.updateInvoiceByEntityInvoiceIdWithStatus(invoice);
			return new ResponseEntity<>(new Response<>(null, StatusEnum.SUCCESS, "invoice updated successfully"),
					HttpStatus.OK);
		} catch (IllegalArgumentException e) {
			return new ResponseEntity<>(new Response<>(null, StatusEnum.FAILURE, e.getMessage()),
					HttpStatus.BAD_REQUEST);
		} catch (Exception e) {
			throw new PatientServerException(e.getMessage(), e);
		}
	}

	
	
	
	@PostMapping("updateshipping/entityinvoice/status")
	public ResponseEntity<Response<String>> updateInvoiceShippingByEntityInvoiceIdWithStatus(
			@RequestBody Invoice invoice) {

		try {
			patientInvoice.updateInvoiceShippingByEntityInvoiceIdWithStatus(invoice);
			return new ResponseEntity<>(new Response<>(null, StatusEnum.SUCCESS, "invoice updated successfully"),
					HttpStatus.OK);
		} catch (IllegalArgumentException e) {
			return new ResponseEntity<>(new Response<>(null, StatusEnum.FAILURE, e.getMessage()),
					HttpStatus.BAD_REQUEST);
		} catch (Exception e) {
			throw new PatientServerException(e.getMessage(), e);
		}
	}
}
