package org.secondopinion.utilities.feedbackreport.controller;

import java.util.List;
import java.util.Map;

import org.secondopinion.request.Response;
import org.secondopinion.request.Response.StatusEnum;
import org.secondopinion.utilities.feedbackreport.service.ILookupstaticService;
import org.secondopinion.utilities.jobs.dto.LookupType;
import org.secondopinion.utilities.jobs.dto.Lookupstatic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/lookupstatic")
@RestController
public class LookupStaticController {

	@Autowired
	private ILookupstaticService lookupstaticService;

	@GetMapping("/get/all/lookupTypes")
	public ResponseEntity<Response<List<Lookupstatic>>> getLookupstaticBylookupType() {

		try {

			List<Lookupstatic> lookupstatic = lookupstaticService.getAllLookupstatics();
			return new ResponseEntity<>(new Response<>(lookupstatic, StatusEnum.SUCCESS, null), HttpStatus.OK);
		} catch (IllegalArgumentException e) {
			return new ResponseEntity<>(new Response<>(null, StatusEnum.FAILURE, e.getMessage()),
					HttpStatus.BAD_REQUEST);
		} catch (Exception e) {
			return new ResponseEntity<>(new Response<>(null, StatusEnum.FAILURE, e.getMessage()),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

	@GetMapping("/get/diagnosticcenter/testList")
	public ResponseEntity<Response<List<Lookupstatic>>> getDiagnosticcenterTest(@RequestParam String type,
			@RequestParam String module) {

		try {

			List<Lookupstatic> lookupstatic = lookupstaticService.getDiagnosticcenterTest(type, module);
			return new ResponseEntity<>(new Response<>(lookupstatic, StatusEnum.SUCCESS, null), HttpStatus.OK);
		} catch (IllegalArgumentException e) {
			return new ResponseEntity<>(new Response<>(null, StatusEnum.FAILURE, e.getMessage()),
					HttpStatus.BAD_REQUEST);
		} catch (Exception e) {
			return new ResponseEntity<>(new Response<>(null, StatusEnum.FAILURE, e.getMessage()),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}
}
