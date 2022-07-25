package org.secondopinion.diagnosticcenter.controller;


import java.util.List;

import org.secondopinion.diagnosticcenter.dto.Invoice;
import org.secondopinion.diagnosticcenter.dto.InvoicesSerchDTO;
import org.secondopinion.diagnosticcenter.exception.DiagnosticCenterServerException;
import org.secondopinion.diagnosticcenter.service.IDiagnosticcenterInvoiceService;

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
public class DiagnosticCenterInvoiceController {
	
	@Autowired
	private IDiagnosticcenterInvoiceService diagnosticcenterInvoiceService;

	
	@PostMapping("/save")
	public ResponseEntity<Response<String>> saveInvoice(@RequestBody Invoice invoice) {
		Gson gson = JSONHelper.getGsonWithDate();
		try {
			String invoiceJson = gson.toJson(diagnosticcenterInvoiceService.saveInvoice(invoice));
			return new ResponseEntity<>(new Response<>(invoiceJson, StatusEnum.SUCCESS, "Invoice saved successfully"), HttpStatus.OK);
		} catch (IllegalArgumentException e) {
			return new ResponseEntity<>(new Response<>(null, StatusEnum.FAILURE, null), HttpStatus.BAD_REQUEST);
		} catch (Exception e) {
			throw new DiagnosticCenterServerException(e.getMessage(), e);
		}

	}
	
	
	@GetMapping(value = "/get/bystatus/{diagnosticcenterId}/{pageNum}/{maxResults}")
	public ResponseEntity<Response<List<Invoice>>> getInvoicesByStatus(@PathVariable Long diagnosticcenterId, 
			 @PathVariable Integer pageNum, @PathVariable Integer maxResults) {
		try {
			Response<List<Invoice>> response = diagnosticcenterInvoiceService.getInvoicesByStatus(diagnosticcenterId, pageNum, maxResults);
			return new ResponseEntity<>(response, HttpStatus.OK);
		} catch (IllegalArgumentException e) {
			return new ResponseEntity<>(new Response<>(null, StatusEnum.FAILURE, e.getMessage()),
					HttpStatus.BAD_REQUEST);
		} catch (Exception e) {
			throw new DiagnosticCenterServerException(e.getMessage(), e);
		}
	}
	
	@PostMapping("/criteria")
	public ResponseEntity<Response<String>> getinvoiceForSerchcritiria(
			@RequestBody InvoicesSerchDTO invoicesSerchDTO) {
		Gson gson = JSONHelper.getGsonWithDate();
		try {
			Response<List<Invoice>> invoice = diagnosticcenterInvoiceService.getinvoiceForSerchcritiria(invoicesSerchDTO);
			return new ResponseEntity<>(new Response<>(gson.toJson(invoice), StatusEnum.SUCCESS, "Invoice Retrive successfully"), HttpStatus.OK);
		} catch (IllegalArgumentException e) {
			return new ResponseEntity<>(new Response<>(null, StatusEnum.FAILURE, null), HttpStatus.BAD_REQUEST);
		} catch (Exception e) {
			throw new DiagnosticCenterServerException(e.getMessage(), e);
		}

	}
}
