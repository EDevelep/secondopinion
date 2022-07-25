package org.secondopinion.doctor.controller;

import java.util.Collection;
import java.util.List;

import org.secondopinion.doctor.dto.Baseschedule;
import org.secondopinion.doctor.dto.Schedule;
import org.secondopinion.doctor.dto.ScheduleCriteriaDTO;
import org.secondopinion.doctor.dto.Schedulehours;
import org.secondopinion.doctor.exception.DoctorServerException;
import org.secondopinion.doctor.service.IDoctorScheduleService;
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
public class DoctorScheduleController {

	@Autowired
	private IDoctorScheduleService doctorScheduleService;

	//schedules
	@PostMapping("/schedule/all/bycriteria")
	public ResponseEntity<Response<Collection<Schedule>>> allSchedulesBycriteria(@RequestBody ScheduleCriteriaDTO scheduleCriteriaDTO) {

		try {
			Collection<Schedule> schedules = doctorScheduleService.allSchedulesBycriteria(scheduleCriteriaDTO);
			return new ResponseEntity<>(new Response<>(schedules, StatusEnum.SUCCESS, null), HttpStatus.OK);
		} catch (IllegalArgumentException e) {
			return new ResponseEntity<>(new Response<>(null, StatusEnum.FAILURE, e.getMessage()), HttpStatus.BAD_REQUEST);
		} catch (Exception e) {
			throw new DoctorServerException(e.getMessage(), e);
		}

		
	}
	
	@PostMapping(value = "/baseschedule/save")
	public ResponseEntity<Response<Baseschedule>> saveBaseschedule(@RequestBody Baseschedule baseschedule) {

		try {
			doctorScheduleService.saveBaseschedule(baseschedule);
			return new ResponseEntity<>(new Response<>(baseschedule, StatusEnum.SUCCESS, "schedule(s) added successfully."), HttpStatus.OK);
		} catch (IllegalArgumentException e) {
			return new ResponseEntity<>(new Response<>(null, StatusEnum.FAILURE, e.getMessage()), HttpStatus.BAD_REQUEST);
		} catch (Exception e) {
			throw new DoctorServerException(e.getMessage(), e);
		}
		
	}
	
	@PostMapping(value = "/baseschedule/update/{basescheduleId}")
	public ResponseEntity<Response<Baseschedule>> updateBaseschedule(@PathVariable Long basescheduleId, @RequestBody Baseschedule baseschedule) {

		try {
			doctorScheduleService.updateBaseschedule(baseschedule, basescheduleId);
			return new ResponseEntity<>(new Response<>(baseschedule, StatusEnum.SUCCESS, "schedule(s) added successfully."), HttpStatus.OK);
		} catch (IllegalArgumentException e) {
			return new ResponseEntity<>(new Response<>(null, StatusEnum.FAILURE, e.getMessage()), HttpStatus.BAD_REQUEST);
		} catch (Exception e) {
			throw new DoctorServerException(e.getMessage(), e);
		}
		
	}

	@PostMapping(value = "/baseschedule/get/{doctorId}/{pageNum}/{maxResults}")
	public ResponseEntity<Response<List<Baseschedule>>> getBaseschedules(
			@PathVariable Long doctorId, @PathVariable Integer pageNum, @PathVariable Integer maxResults) {

		try {
			return new ResponseEntity<>(doctorScheduleService.getDoctorAllBasesSchedules(doctorId, pageNum, maxResults),
					HttpStatus.OK);
		} catch (IllegalArgumentException e) {
			return new ResponseEntity<>(new Response<>(null, StatusEnum.FAILURE, e.getMessage()), HttpStatus.BAD_REQUEST);
		} catch (Exception e) {
			throw new DoctorServerException(e.getMessage(), e);
		}
		
	}

	
	@PostMapping("/schedule/save")
	public ResponseEntity<Response<String>> saveSchedule(@RequestBody Schedule schedule) {

		try {
			doctorScheduleService.saveSchedule(schedule);
			return new ResponseEntity<>(new Response<>(null, StatusEnum.SUCCESS, "schedule are saved or updated successfully "), HttpStatus.OK);
		} catch (IllegalArgumentException e) {
			return new ResponseEntity<>(new Response<>(null, StatusEnum.FAILURE, e.getMessage()), HttpStatus.BAD_REQUEST);
		} catch (Exception e) {
			throw new DoctorServerException(e.getMessage(), e);
		}
		
	}

	@DeleteMapping("/schedule/id/{scheduleId}")
	public ResponseEntity<Response<String>> deleteSchedule(@PathVariable Long scheduleId) {

		try {
			doctorScheduleService.deletSchedule(scheduleId);
			return new ResponseEntity<>(new Response<>(null, StatusEnum.SUCCESS, null), HttpStatus.OK);
		} catch (IllegalArgumentException e) {
			return new ResponseEntity<>(new Response<>(null, StatusEnum.FAILURE, e.getMessage()), HttpStatus.BAD_REQUEST);
		} catch (Exception e) {
			throw new DoctorServerException(e.getMessage(), e);
		}
		
	}
	
	@PostMapping("/schedulehours/save")
	public ResponseEntity<Response<String>> saveSchedulehours(@RequestBody Schedulehours schedulehours) {

		try {
			doctorScheduleService.saveScheduleHours(schedulehours);
			return new ResponseEntity<>(new Response<>(null, StatusEnum.SUCCESS, null), HttpStatus.OK);
		} catch (IllegalArgumentException e) {
			return new ResponseEntity<>(new Response<>(null, StatusEnum.FAILURE, e.getMessage()), HttpStatus.BAD_REQUEST);
		} catch (Exception e) {
			throw new DoctorServerException(e.getMessage(), e);
		}
		
	}

	@DeleteMapping("/schedulehours/id/{schedulehoursId}")
	public ResponseEntity<Response<String>> deleteSchedulehours(@PathVariable Long schedulehoursId) {

		try {
			doctorScheduleService.deletSchedulehours(schedulehoursId);
			return new ResponseEntity<>(new Response<>(null, StatusEnum.SUCCESS, null), HttpStatus.OK);
		} catch (IllegalArgumentException e) {
			return new ResponseEntity<>(new Response<>(null, StatusEnum.FAILURE, e.getMessage()), HttpStatus.BAD_REQUEST);
		} catch (Exception e) {
			throw new DoctorServerException(e.getMessage(), e);
		}
		
	}
	
	@GetMapping("/schedule/id/{scheduleId}")
	public ResponseEntity<Response<Schedule>> scheduleById(@PathVariable("scheduleId") Long scheduleId) {
		try {
			Schedule schedule = doctorScheduleService.scheduleDetails(scheduleId);
			return new ResponseEntity<>(new Response<>(schedule, StatusEnum.SUCCESS, null), HttpStatus.OK);
		} catch (IllegalArgumentException e) {
			return new ResponseEntity<>(new Response<>(null, StatusEnum.FAILURE, e.getMessage()), HttpStatus.BAD_REQUEST);
		} catch (Exception e) {
			throw new DoctorServerException(e.getMessage(), e);
		}
		
	}

	@GetMapping("/schedulehours/scheduleid/{scheduleId}")
	public ResponseEntity<Response<Collection<Schedulehours>>> scheduleHoursByScheduleId(
			@PathVariable("scheduleId") Long scheduleId) {
		try {
			Collection<Schedulehours> scheduleHours = doctorScheduleService.scheduleHoursDetailsByScheduleId(scheduleId);
			return new ResponseEntity<>(new Response<>(scheduleHours, StatusEnum.SUCCESS, null), HttpStatus.OK);
		} catch (IllegalArgumentException e) {
			return new ResponseEntity<>(new Response<>(null, StatusEnum.FAILURE, e.getMessage()), HttpStatus.BAD_REQUEST);
		} catch (Exception e) {
			throw new DoctorServerException(e.getMessage(), e);
		}
		
	}
	
}
