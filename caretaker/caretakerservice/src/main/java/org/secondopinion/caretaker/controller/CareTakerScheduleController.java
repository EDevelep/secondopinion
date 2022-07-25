package org.secondopinion.caretaker.controller;

import java.util.Collection;
import java.util.List;

import org.secondopinion.caretaker.dto.Baseschedule;
import org.secondopinion.caretaker.dto.Schedule;
import org.secondopinion.caretaker.dto.ScheduleCriteriaDTO;
import org.secondopinion.caretaker.dto.Schedulehours;
import org.secondopinion.caretaker.exception.CareTakerServerException;
import org.secondopinion.caretaker.service.ICareTakerSchudleService;
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
public class CareTakerScheduleController {

	@Autowired
	private ICareTakerSchudleService caretakerScheduleService;

	//schedules
	@PostMapping("/schedule/all/bycriteria")
	public ResponseEntity<Response<Collection<Schedule>>> allSchedulesBycriteria(@RequestBody ScheduleCriteriaDTO scheduleCriteriaDTO) {

		try {
			Collection<Schedule> schedules = caretakerScheduleService.allSchedulesBycriteria(scheduleCriteriaDTO);
			return new ResponseEntity<>(new Response<>(schedules, StatusEnum.SUCCESS, null), HttpStatus.OK);
		} catch (IllegalArgumentException e) {
			return new ResponseEntity<>(new Response<>(null, StatusEnum.FAILURE, e.getMessage()), HttpStatus.BAD_REQUEST);
		} catch (Exception e) {
			throw new CareTakerServerException(e.getMessage(), e);
		}

		
	}
	
	@PostMapping(value = "/baseschedule/save")
	public ResponseEntity<Response<Baseschedule>> saveBaseschedule(@RequestBody Baseschedule baseschedule) {

		try {
			caretakerScheduleService.saveBaseschedule(baseschedule);
			return new ResponseEntity<>(new Response<>(baseschedule, StatusEnum.SUCCESS, "schedule(s) added successfully."), HttpStatus.OK);
		} catch (IllegalArgumentException e) {
			return new ResponseEntity<>(new Response<>(null, StatusEnum.FAILURE, e.getMessage()), HttpStatus.BAD_REQUEST);
		} catch (Exception e) {
			throw new CareTakerServerException(e.getMessage(), e);
		}
		
	}
	
	@PostMapping(value = "/baseschedule/update/{basescheduleId}")
	public ResponseEntity<Response<Baseschedule>> updateBaseschedule(@PathVariable Long basescheduleId, @RequestBody Baseschedule baseschedule) {

		try {
			caretakerScheduleService.updateBaseschedule(baseschedule, basescheduleId);
			return new ResponseEntity<>(new Response<>(baseschedule, StatusEnum.SUCCESS, "schedule(s) added successfully."), HttpStatus.OK);
		} catch (IllegalArgumentException e) {
			return new ResponseEntity<>(new Response<>(null, StatusEnum.FAILURE, e.getMessage()), HttpStatus.BAD_REQUEST);
		} catch (Exception e) {
			throw new CareTakerServerException(e.getMessage(), e);
		}
		
	}

	@PostMapping(value = "/baseschedule/get/{doctorId}/{pageNum}/{maxResults}")
	public ResponseEntity<Response<List<Baseschedule>>> getBaseschedules(
			@PathVariable Long doctorId, @PathVariable Integer pageNum, @PathVariable Integer maxResults) {

		try {
			return new ResponseEntity<>(caretakerScheduleService.getCaretakerAllBasesSchedules(doctorId, pageNum, maxResults),
					HttpStatus.OK);
		} catch (IllegalArgumentException e) {
			return new ResponseEntity<>(new Response<>(null, StatusEnum.FAILURE, e.getMessage()), HttpStatus.BAD_REQUEST);
		} catch (Exception e) {
			throw new CareTakerServerException(e.getMessage(), e);
		}
		
	}

	
	@PostMapping("/schedule/save")
	public ResponseEntity<Response<String>> saveSchedule(@RequestBody Schedule schedule) {

		try {
			caretakerScheduleService.saveSchedule(schedule);
			return new ResponseEntity<>(new Response<>(null, StatusEnum.SUCCESS, "schedule are saved or updated successfully "), HttpStatus.OK);
		} catch (IllegalArgumentException e) {
			return new ResponseEntity<>(new Response<>(null, StatusEnum.FAILURE, e.getMessage()), HttpStatus.BAD_REQUEST);
		} catch (Exception e) {
			throw new CareTakerServerException(e.getMessage(), e);
		}
		
	}

	@DeleteMapping("/schedule/id/{scheduleId}")
	public ResponseEntity<Response<String>> deleteSchedule(@PathVariable Long scheduleId) {

		try {
			caretakerScheduleService.deletSchedule(scheduleId);
			return new ResponseEntity<>(new Response<>(null, StatusEnum.SUCCESS, null), HttpStatus.OK);
		} catch (IllegalArgumentException e) {
			return new ResponseEntity<>(new Response<>(null, StatusEnum.FAILURE, e.getMessage()), HttpStatus.BAD_REQUEST);
		} catch (Exception e) {
			throw new CareTakerServerException(e.getMessage(), e);
		}
		
	}
	
	@PostMapping("/schedulehours/save")
	public ResponseEntity<Response<String>> saveSchedulehours(@RequestBody Schedulehours schedulehours) {

		try {
			caretakerScheduleService.saveScheduleHours(schedulehours);
			return new ResponseEntity<>(new Response<>(null, StatusEnum.SUCCESS, null), HttpStatus.OK);
		} catch (IllegalArgumentException e) {
			return new ResponseEntity<>(new Response<>(null, StatusEnum.FAILURE, e.getMessage()), HttpStatus.BAD_REQUEST);
		} catch (Exception e) {
			throw new CareTakerServerException(e.getMessage(), e);
		}
		
	}

	@DeleteMapping("/schedulehours/id/{schedulehoursId}")
	public ResponseEntity<Response<String>> deleteSchedulehours(@PathVariable Long schedulehoursId) {

		try {
			caretakerScheduleService.deletSchedulehours(schedulehoursId);
			return new ResponseEntity<>(new Response<>(null, StatusEnum.SUCCESS, null), HttpStatus.OK);
		} catch (IllegalArgumentException e) {
			return new ResponseEntity<>(new Response<>(null, StatusEnum.FAILURE, e.getMessage()), HttpStatus.BAD_REQUEST);
		} catch (Exception e) {
			throw new CareTakerServerException(e.getMessage(), e);
		}
		
	}
	
	@GetMapping("/schedule/id/{scheduleId}")
	public ResponseEntity<Response<Schedule>> scheduleById(@PathVariable("scheduleId") Long scheduleId) {
		try {
			Schedule schedule = caretakerScheduleService.scheduleDetails(scheduleId);
			return new ResponseEntity<>(new Response<>(schedule, StatusEnum.SUCCESS, null), HttpStatus.OK);
		} catch (IllegalArgumentException e) {
			return new ResponseEntity<>(new Response<>(null, StatusEnum.FAILURE, e.getMessage()), HttpStatus.BAD_REQUEST);
		} catch (Exception e) {
			throw new CareTakerServerException(e.getMessage(), e);
		}
		
	}

	@GetMapping("/schedulehours/scheduleid/{scheduleId}")
	public ResponseEntity<Response<Collection<Schedulehours>>> scheduleHoursByScheduleId(
			@PathVariable("scheduleId") Long scheduleId) {
		try {
			Collection<Schedulehours> scheduleHours = caretakerScheduleService.scheduleHoursDetailsByScheduleId(scheduleId);
			return new ResponseEntity<>(new Response<>(scheduleHours, StatusEnum.SUCCESS, null), HttpStatus.OK);
		} catch (IllegalArgumentException e) {
			return new ResponseEntity<>(new Response<>(null, StatusEnum.FAILURE, e.getMessage()), HttpStatus.BAD_REQUEST);
		} catch (Exception e) {
			throw new CareTakerServerException(e.getMessage(), e);
		}
		
	}
	
}
