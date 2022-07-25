package org.secondopinion.diagnosticcenter.controller;

import java.util.Collection;
import java.util.List;

import org.secondopinion.diagnosticcenter.dto.Baseschedule;
import org.secondopinion.diagnosticcenter.dto.Schedule;
import org.secondopinion.diagnosticcenter.dto.ScheduleCriteriaDTO;
import org.secondopinion.diagnosticcenter.dto.Schedulehours;
import org.secondopinion.diagnosticcenter.exception.DiagnosticCenterServerException;
import org.secondopinion.diagnosticcenter.service.IDiagnosticCenterScheduleService;
import org.secondopinion.request.Response;
import org.secondopinion.request.Response.StatusEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DiagnosticCenterScheduleController {

	@Autowired
	private IDiagnosticCenterScheduleService diagnosticCenterScheduleService;
	
	
	@PostMapping(value = "/baseschedule/save")
	public ResponseEntity<Response<Baseschedule>> saveBaseschedule(@RequestBody Baseschedule baseschedule) {

		try {
			diagnosticCenterScheduleService.saveBaseschedule(baseschedule);
			return new ResponseEntity<>(new Response<>(baseschedule, StatusEnum.SUCCESS, "schedule(s) added successfully."), HttpStatus.OK);
		} catch (IllegalArgumentException e) {
			return new ResponseEntity<>(new Response<>(null, StatusEnum.FAILURE, e.getMessage()), HttpStatus.BAD_REQUEST);
		} catch (Exception e) {
			throw new DiagnosticCenterServerException(e.getMessage(), e);
		}
		
	}
	
	@PostMapping(value = "/baseschedule/update/{basescheduleId}")
	public ResponseEntity<Response<Baseschedule>> updateBaseschedule(@PathVariable Long basescheduleId, @RequestBody Baseschedule baseschedule) {

		try {
			diagnosticCenterScheduleService.updateBaseschedule(baseschedule, basescheduleId);
			return new ResponseEntity<>(new Response<>(baseschedule, StatusEnum.SUCCESS, "schedule(s) added successfully."), HttpStatus.OK);
		} catch (IllegalArgumentException e) {
			return new ResponseEntity<>(new Response<>(null, StatusEnum.FAILURE, e.getMessage()), HttpStatus.BAD_REQUEST);
		} catch (Exception e) {
			throw new DiagnosticCenterServerException(e.getMessage(), e);
		}
		
	}

	@PostMapping(value = "/baseschedule/get")
	public ResponseEntity<Response<List<Baseschedule>>> getBaseschedules(
			@RequestBody ScheduleCriteriaDTO scheduleCriteriaDTO) {

		try {
			return new ResponseEntity<>(diagnosticCenterScheduleService.getDiagnosticCenterAllBasesSchedules(scheduleCriteriaDTO),
					HttpStatus.OK);
		} catch (IllegalArgumentException e) {
			return new ResponseEntity<>(new Response<>(null, StatusEnum.FAILURE, e.getMessage()), HttpStatus.BAD_REQUEST);
		} catch (Exception e) {
			throw new DiagnosticCenterServerException(e.getMessage(), e);
		}
		
	}
	
	@PostMapping("/schedule/all/bycriteria")
	public ResponseEntity<Response<List<Schedule>>> allSchedulesBycriteria(@RequestBody ScheduleCriteriaDTO scheduleCriteriaDTO) {

		try {
			return new ResponseEntity<>(diagnosticCenterScheduleService.allSchedulesBycriteria(scheduleCriteriaDTO), HttpStatus.OK);
		} catch (IllegalArgumentException e) {
			return new ResponseEntity<>(new Response<>(null, StatusEnum.FAILURE, e.getMessage()), HttpStatus.BAD_REQUEST);
		} catch (Exception e) {
			throw new DiagnosticCenterServerException(e.getMessage(), e);
		}
	}
	
	@PostMapping("/schedule/save")
	public ResponseEntity<Response<String>> saveSchedule(@RequestBody Schedule schedule) {

		try {
			diagnosticCenterScheduleService.saveSchedule(schedule);
			return new ResponseEntity<>(new Response<>(null, StatusEnum.SUCCESS, "schedule are saved or updated successfully "), HttpStatus.OK);
		} catch (IllegalArgumentException e) {
			return new ResponseEntity<>(new Response<>(null, StatusEnum.FAILURE, e.getMessage()), HttpStatus.BAD_REQUEST);
		} catch (Exception e) {
			throw new DiagnosticCenterServerException(e.getMessage(), e);
		}
		
	}

	@DeleteMapping("/schedule/id/{scheduleId}")
	public ResponseEntity<Response<String>> deleteSchedule(@PathVariable Long scheduleId) {

		try {
			diagnosticCenterScheduleService.deletSchedule(scheduleId);
			return new ResponseEntity<>(new Response<>(null, StatusEnum.SUCCESS, null), HttpStatus.OK);
		} catch (IllegalArgumentException e) {
			return new ResponseEntity<>(new Response<>(null, StatusEnum.FAILURE, e.getMessage()), HttpStatus.BAD_REQUEST);
		} catch (Exception e) {
			throw new DiagnosticCenterServerException(e.getMessage(), e);
		}
		
	}
	
	@PostMapping("/schedulehours/save")
	public ResponseEntity<Response<String>> saveSchedulehours(@RequestBody Schedulehours schedulehours) {

		try {
			diagnosticCenterScheduleService.saveScheduleHours(schedulehours);
			return new ResponseEntity<>(new Response<>(null, StatusEnum.SUCCESS, null), HttpStatus.OK);
		} catch (IllegalArgumentException e) {
			return new ResponseEntity<>(new Response<>(null, StatusEnum.FAILURE, e.getMessage()), HttpStatus.BAD_REQUEST);
		} catch (Exception e) {
			throw new DiagnosticCenterServerException(e.getMessage(), e);
		}
		
	}

	@DeleteMapping("/schedulehours/id/{schedulehoursId}")
	public ResponseEntity<Response<String>> deleteSchedulehours(@PathVariable Long schedulehoursId) {

		try {
			diagnosticCenterScheduleService.deletSchedulehours(schedulehoursId);
			return new ResponseEntity<>(new Response<>(null, StatusEnum.SUCCESS, null), HttpStatus.OK);
		} catch (IllegalArgumentException e) {
			return new ResponseEntity<>(new Response<>(null, StatusEnum.FAILURE, e.getMessage()), HttpStatus.BAD_REQUEST);
		} catch (Exception e) {
			throw new DiagnosticCenterServerException(e.getMessage(), e);
		}
		
	}
	
	@GetMapping("/schedule/id/{scheduleId}")
	public ResponseEntity<Response<Schedule>> scheduleById(@PathVariable("scheduleId") Long scheduleId) {
		try {
			Schedule schedule = diagnosticCenterScheduleService.scheduleDetails(scheduleId);
			return new ResponseEntity<>(new Response<>(schedule, StatusEnum.SUCCESS, null), HttpStatus.OK);
		} catch (IllegalArgumentException e) {
			return new ResponseEntity<>(new Response<>(null, StatusEnum.FAILURE, e.getMessage()), HttpStatus.BAD_REQUEST);
		} catch (Exception e) {
			throw new DiagnosticCenterServerException(e.getMessage(), e);
		}
		
	}

	@GetMapping("/schedulehours/scheduleid/{scheduleId}")
	public ResponseEntity<Response<Collection<Schedulehours>>> scheduleHoursByScheduleId(
			@PathVariable("scheduleId") Long scheduleId) {
		try {
			Collection<Schedulehours> scheduleHours = diagnosticCenterScheduleService.scheduleHoursDetailsByScheduleId(scheduleId);
			return new ResponseEntity<>(new Response<>(scheduleHours, StatusEnum.SUCCESS, null), HttpStatus.OK);
		} catch (IllegalArgumentException e) {
			return new ResponseEntity<>(new Response<>(null, StatusEnum.FAILURE, e.getMessage()), HttpStatus.BAD_REQUEST);
		} catch (Exception e) {
			throw new DiagnosticCenterServerException(e.getMessage(), e);
		}
		
	}
	
}
