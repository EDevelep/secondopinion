package org.secondopinion.doctor.controller;

import java.util.List;

import org.secondopinion.doctor.dto.Invoice;
import org.secondopinion.doctor.dto.InvoicesSerchDTO;
import org.secondopinion.doctor.exception.DoctorServerException;
import org.secondopinion.doctor.service.IInvoiceService;
import org.secondopinion.request.Response;
import org.secondopinion.request.Response.StatusEnum;
import org.secondopinion.utils.JSONHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;

@RestController
@RequestMapping("/invoice")
public class InvoiceController {

	@Autowired
	private IInvoiceService invoiceService;

	@PostMapping("/save")
	public ResponseEntity<Response<String>> saveInvoice(@RequestBody Invoice invoice) {
		Gson gson = JSONHelper.getGsonWithDate();
		try {
			String invoiceJson = gson.toJson(invoiceService.saveInvoice(invoice));
			return new ResponseEntity<>(new Response<>(invoiceJson, StatusEnum.SUCCESS, "Invoice saved successfully"), HttpStatus.OK);
		} catch (IllegalArgumentException e) {
			return new ResponseEntity<>(new Response<>(null, StatusEnum.FAILURE, null), HttpStatus.BAD_REQUEST);
		} catch (Exception e) {
			throw new DoctorServerException(e.getMessage(), e);
		}

	}

	@PostMapping("/criteria")
	public ResponseEntity<Response<String>> getinvoiceForSerchcritiria(
			@RequestBody InvoicesSerchDTO invoicesSerchDTO) {
		Gson gson = JSONHelper.getGsonWithDate();
		try {
			Response<List<Invoice>> invoice = invoiceService.getinvoiceForSerchcritiria(invoicesSerchDTO);
			return new ResponseEntity<>(new Response<>(gson.toJson(invoice), StatusEnum.SUCCESS, "Invoice Retrive successfully"), HttpStatus.OK);
		} catch (IllegalArgumentException e) {
			return new ResponseEntity<>(new Response<>(null, StatusEnum.FAILURE, null), HttpStatus.BAD_REQUEST);
		} catch (Exception e) {
			throw new DoctorServerException(e.getMessage(), e);
		}

	}

}
