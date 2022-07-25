
package org.secondopinion.patient.controller;

import java.util.TimeZone;

import javax.servlet.http.HttpServletRequest;

import org.secondopinion.patient.dto.ForUser;
import org.secondopinion.patient.dto.RELATIONSHIP_TYPE;
import org.secondopinion.patient.exception.PatientServerException;
import org.secondopinion.patient.service.VitalsReportService;
import org.secondopinion.patient.service.helper.ReportData;
import org.secondopinion.patient.service.impl.TIME;
import org.secondopinion.request.Response;
import org.secondopinion.request.Response.StatusEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController

@RequestMapping("/vitalsReportController")
public class VitalsReportController {

	@Autowired
	private VitalsReportService vitalsReportService;

	@Autowired
	private HttpServletRequest httpServletRequest;
	@PostMapping("/executeReport")
	public ResponseEntity<Response<ReportData>> executeReport(@RequestBody ForUser foruser, @RequestParam TIME time, @RequestParam String vital) {
		try {
			
			String moduleName = httpServletRequest.getHeader("moduleName");
			RELATIONSHIP_TYPE relationship_TYPE = RELATIONSHIP_TYPE.valueOf(moduleName);
			return new ResponseEntity<>(
					new Response<>(vitalsReportService.executeReport(foruser, vital, time, TimeZone.getDefault(),relationship_TYPE),
							StatusEnum.SUCCESS, null),
					HttpStatus.OK);
		} catch (IllegalArgumentException e) {
			return new ResponseEntity<>(new Response<>(null, StatusEnum.FAILURE, e.getMessage()),
					HttpStatus.BAD_REQUEST);
		} catch (Exception e) {
			throw new PatientServerException(e.getMessage(), e);
		}

	}
}
