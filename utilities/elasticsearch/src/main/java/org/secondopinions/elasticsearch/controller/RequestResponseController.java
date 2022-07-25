package org.secondopinions.elasticsearch.controller;

import org.secondopinions.elasticsearch.dto.Response;
import org.secondopinions.elasticsearch.dto.Response.StatusEnum;
import org.secondopinions.elasticsearch.model.BulkRequest;
import org.secondopinions.elasticsearch.model.Request;
import org.secondopinions.elasticsearch.serviceImpl.RequestResponseServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;

@RestController
@Api("/")
@CrossOrigin(origins = "*")
@RequestMapping(value = "/requestResponse")
public class RequestResponseController {
	@Autowired
	private RequestResponseServiceImp responseServiceImp;

	@PostMapping("/saveRequestResponse")
	public ResponseEntity<Response<String>> saveRequestResponse(@RequestBody Request request) {
		try {
			responseServiceImp.saveRequestResponse(request);
			return new ResponseEntity<>(new Response<>("RequestResponce Saved ", StatusEnum.SUCCESS, null),
					HttpStatus.OK);
		} catch (IllegalArgumentException e) {
			return new ResponseEntity<>(new Response<>(null, StatusEnum.FAILURE, e.getMessage()),
					HttpStatus.BAD_REQUEST);
		}
	}

	@PostMapping("/saveBulkRequestResponse")
	public ResponseEntity<Response<String>> saveRequestResponse(@RequestBody BulkRequest bulkRequest) {
		try {
			responseServiceImp.saveRequestResponse(bulkRequest);
			return new ResponseEntity<>(new Response<>("RequestResponce Saved ", StatusEnum.SUCCESS, null),
					HttpStatus.OK);
		} catch (IllegalArgumentException e) {
			return new ResponseEntity<>(new Response<>(null, StatusEnum.FAILURE, e.getMessage()),
					HttpStatus.BAD_REQUEST);
		}
	}

}