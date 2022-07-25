package org.secondopinions.elasticsearch.controller;

import java.util.List;

import org.secondopinions.elasticsearch.dto.Response;
import org.secondopinions.elasticsearch.dto.Response.StatusEnum;
import org.secondopinions.elasticsearch.model.ExceptionIndex;

import org.secondopinions.elasticsearch.serviceImpl.ExceptionIndexService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/exceptionindexcontroller")
public class ExceptionIndexController {

	@Autowired
	private ExceptionIndexService exceptionIndexService;

	@PostMapping("/saveExceptionIndex")
	public ResponseEntity<Response<String>> saveExceptionIndex(@RequestBody ExceptionIndex request) {
		try {

			exceptionIndexService.saveExceptionIndex(request);
			return new ResponseEntity<>(new Response<>("ExceptionIndex Saved ", StatusEnum.SUCCESS, null),
					HttpStatus.OK);
		} catch (IllegalArgumentException e) {
			return new ResponseEntity<>(new Response<>(null, StatusEnum.FAILURE, e.getMessage()),
					HttpStatus.BAD_REQUEST);
		}
	}

	@PostMapping("/getallexceptionindex")
	public ResponseEntity<Response<List<ExceptionIndex>>> getallExceptionIndex() {
		try {

			List<ExceptionIndex> exceptionIndexs = exceptionIndexService.getExceptionIndex();
			return new ResponseEntity<>(new Response<>(exceptionIndexs, StatusEnum.SUCCESS, " ExceptionIndex Get "),
					HttpStatus.OK);
		} catch (IllegalArgumentException e) {
			return new ResponseEntity<>(new Response<>(null, StatusEnum.FAILURE, e.getMessage()),
					HttpStatus.BAD_REQUEST);
		}
	}
}
