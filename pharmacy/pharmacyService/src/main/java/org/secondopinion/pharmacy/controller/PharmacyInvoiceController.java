package org.secondopinion.pharmacy.controller;

import java.util.List;

import org.secondopinion.enums.InvoiceStatusEnum;
import org.secondopinion.pharmacy.dto.Invoice;
import org.secondopinion.pharmacy.dto.InvoicesSerchDTO;
import org.secondopinion.pharmacy.exception.PharmacyServerException;
import org.secondopinion.pharmacy.service.IPharmacyInvoiceService;
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
@RequestMapping("/invoice")
public class PharmacyInvoiceController {

	@Autowired
	private IPharmacyInvoiceService pharmacyInvoiceService;

	@PostMapping(value = "/update/frompatient")
	public ResponseEntity<Response<String>> updateInvoiceAfterPaymentByPatient(@RequestBody Invoice invoice) {
		try {
			pharmacyInvoiceService.updateInvoiceAfterPaymentByPatient(invoice);
			return new ResponseEntity<>(
					new Response<>(null, StatusEnum.SUCCESS, "Pharmacy Invoice data updated successfully"),
					HttpStatus.OK);
		} catch (IllegalArgumentException e) {
			return new ResponseEntity<>(new Response<>(null, StatusEnum.FAILURE, e.getMessage()),
					HttpStatus.BAD_REQUEST);
		} catch (Exception e) {
			throw new PharmacyServerException(e.getMessage(), e);
		}
	}

	@PostMapping(value = "/saveInvoiceShippingDetail")
	public ResponseEntity<Response<String>> saveInvoiceShippingDetail(@RequestBody Invoice invoice) {
		try {
			pharmacyInvoiceService.saveInvoiceShipeeingDetail(invoice);
			return new ResponseEntity<>(
					new Response<>(null, StatusEnum.SUCCESS, "Pharmacy Invoice data Save successfully"), HttpStatus.OK);
		} catch (IllegalArgumentException e) {
			return new ResponseEntity<>(new Response<>(null, StatusEnum.FAILURE, e.getMessage()),
					HttpStatus.BAD_REQUEST);
		} catch (Exception e) {
			throw new PharmacyServerException(e.getMessage(), e);
		}
	}

	@PostMapping(value = "/update/updateInvoiceAfterShipped/{invoiceId}/{invoiceStatusEnum}")
	public ResponseEntity<Response<String>> updateInvoiceAfterShipped(@PathVariable Long invoiceId,
			@PathVariable InvoiceStatusEnum invoiceStatusEnum) {
		try {
			pharmacyInvoiceService.updateInvoiceAfterShipped(invoiceId, invoiceStatusEnum);
			return new ResponseEntity<>(
					new Response<>(null, StatusEnum.SUCCESS, "Pharmacy Invoice data updated successfully"),
					HttpStatus.OK);
		} catch (IllegalArgumentException e) {
			return new ResponseEntity<>(new Response<>(null, StatusEnum.FAILURE, e.getMessage()),
					HttpStatus.BAD_REQUEST);
		} catch (Exception e) {
			throw new PharmacyServerException(e.getMessage(), e);
		}
	}

	@PostMapping(value = "/update/bypharmacy/{invoiceId}/{invoiceStatusEnum}")
	public ResponseEntity<Response<String>> updateInvoiceByPharmacy(@PathVariable Long invoiceId,
			@PathVariable InvoiceStatusEnum invoiceStatusEnum) {
		try {
			pharmacyInvoiceService.updateInvoiceByPharmacy(invoiceId, invoiceStatusEnum);
			return new ResponseEntity<>(
					new Response<>(null, StatusEnum.SUCCESS, "Pharmacy Invoice data updated successfully"),
					HttpStatus.OK);
		} catch (IllegalArgumentException e) {
			return new ResponseEntity<>(new Response<>(null, StatusEnum.FAILURE, e.getMessage()),
					HttpStatus.BAD_REQUEST);
		} catch (Exception e) {
			throw new PharmacyServerException(e.getMessage(), e);
		}
	}

	@PostMapping(value = "/getShippingDetail/{invoiceId}/{patientId}")
	public ResponseEntity<Response<List<Invoice>>> getShippingDetail(@PathVariable Long invoiceId,
			@PathVariable Long patientId) {
		try {
			List<Invoice> invoices = pharmacyInvoiceService.getShipeeingDetail(invoiceId, patientId);
			return new ResponseEntity<>(
					new Response<>(invoices, StatusEnum.SUCCESS, "Pharmacy Invoice data Get successfully"),
					HttpStatus.OK);
		} catch (IllegalArgumentException e) {
			return new ResponseEntity<>(new Response<>(null, StatusEnum.FAILURE, e.getMessage()),
					HttpStatus.BAD_REQUEST);
		} catch (Exception e) {
			throw new PharmacyServerException(e.getMessage(), e);
		}
	}

	@GetMapping(value = "/get/bystatus/{pharmacyId}/{invoiceStatusEnum}/{pageNum}/{maxResults}")
	public ResponseEntity<Response<List<Invoice>>> getInvoicesByStatus(@PathVariable Long pharmacyId,
			@PathVariable InvoiceStatusEnum invoiceStatusEnum, @PathVariable Integer pageNum,
			@PathVariable Integer maxResults) {
		try {
			Response<List<Invoice>> response = pharmacyInvoiceService.getInvoicesByStatus(pharmacyId, invoiceStatusEnum,
					pageNum, maxResults);
			return new ResponseEntity<>(response, HttpStatus.OK);
		} catch (IllegalArgumentException e) {
			return new ResponseEntity<>(new Response<>(null, StatusEnum.FAILURE, e.getMessage()),
					HttpStatus.BAD_REQUEST);
		} catch (Exception e) {
			throw new PharmacyServerException(e.getMessage(), e);
		}
	}

	@PostMapping("/criteria")
	public ResponseEntity<Response<String>> getinvoiceForSerchcritiria(@RequestBody InvoicesSerchDTO invoicesSerchDTO) {
		Gson gson = JSONHelper.getGsonWithDate();
		try {
			Response<List<Invoice>> invoice = pharmacyInvoiceService.getinvoiceForSerchcritiria(invoicesSerchDTO);
			return new ResponseEntity<>(
					new Response<>(gson.toJson(invoice), StatusEnum.SUCCESS, "Invoice Retrive successfully"),
					HttpStatus.OK);
		} catch (IllegalArgumentException e) {
			return new ResponseEntity<>(new Response<>(null, StatusEnum.FAILURE, null), HttpStatus.BAD_REQUEST);
		} catch (Exception e) {
			throw new PharmacyServerException(e.getMessage(), e);
		}

	}
}
