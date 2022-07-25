package org.secondopinion.utilities.jobs.controller;

import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import org.secondopinion.request.Response;
import org.secondopinion.request.Response.StatusEnum;
import org.secondopinion.utilities.jobs.dto.JobDTO;
import org.secondopinion.utilities.jobs.dto.JobDef;
import org.secondopinion.utilities.jobs.dto.JobInfo;

import org.secondopinion.utilities.jobs.dto.RunJobStatusDTO;
import org.secondopinion.utilities.jobs.service.JobService;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/jobs/")
public class JobsController  {
	
	private static Logger LOG = LoggerFactory.getLogger(JobsController.class);
	
	@Autowired
	private JobService jobService;

	/**
	 * @return the jobService
	 */
	public JobService getJobService() {
		return jobService;
	}

	/**
	 * @param jobService the jobService to set
	 */
	public void setJobService(JobService jobService) {
		this.jobService = jobService;
	}
	
	@ApiOperation(value = "/all", notes = "This method is used to get user by id")
	@GetMapping(("/all"))
	public ResponseEntity<Response<List<JobDef>>> getAllJobDefs(){
		LOG.debug("Inside WS Service to retrieve Job Definitions - ");
		try{
			List<JobDef> jobDefs = jobService.getAllJobDefs();
			
			return new ResponseEntity<>(new Response<>(jobDefs, StatusEnum.SUCCESS, null), HttpStatus.OK);
		}catch(Exception ex){
			return new ResponseEntity<>(new Response<>(null, StatusEnum.FAILURE, ex.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
	}
	
	@ApiOperation(value = "/def/{jobBean}", notes = "This method is used to get user by id")
	@GetMapping("/def/{jobBean}")
	public ResponseEntity<Response<JobDef>> getJobDef(@PathVariable("jobBean") String jobBean){
		LOG.debug("Inside WS Service to retrieve Job Definition - ");
		try{
			JobDef jobDef = jobService.getJobDef(jobBean);
			
			return new ResponseEntity<>(new Response<>(jobDef, StatusEnum.SUCCESS, null), HttpStatus.OK);
		}catch(Exception ex){
			return new ResponseEntity<>(new Response<>(null, StatusEnum.FAILURE, ex.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
	}

	@ApiOperation(value = "/executioninfo/{jobId}", notes = "This method is used to get user by id")
	@GetMapping("/executioninfo/{jobId}")
	public ResponseEntity<Response<List<JobInfo>>> getJobsExecutionInfo(@PathVariable("jobId") String jobId){
		LOG.debug("Inside WS Service to retrieve Job execution information - ");
		try{
			List<JobInfo> jobInfos = jobService.getJobsExecutionInfo(jobId);
			
			return new ResponseEntity<>(new Response<>(jobInfos, StatusEnum.SUCCESS, null), HttpStatus.OK);
		}catch(Exception ex){
			return new ResponseEntity<>(new Response<>(null, StatusEnum.FAILURE, ex.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
	}
	
	@ApiOperation(value = "/execute", notes = "This method is used to get user by id")
	@PostMapping("/execute")
	public ResponseEntity<Response<RunJobStatusDTO>> executeJob(@RequestBody @Valid JobDTO jobDTO){
		try{
			RunJobStatusDTO jobStatusDTO = jobService.executeJobRest(jobDTO.getJobBeanId());
			return new ResponseEntity<>(new Response<>(jobStatusDTO, StatusEnum.SUCCESS, null), HttpStatus.OK);
		}catch(Exception ex){
			return new ResponseEntity<>(new Response<>(null, StatusEnum.FAILURE, ex.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
	}
	
	@ApiOperation(value = "/save", notes = "This method is used to get user by id")
	@PostMapping("/save")
	public ResponseEntity<Response<String>> saveJobDev(@RequestBody JobDef jobDef ){
		try{
			jobService.saveJobDef(jobDef);
			return new ResponseEntity<>(new Response<>(null, StatusEnum.SUCCESS, null), HttpStatus.OK);
		}catch(Exception ex){
			return new ResponseEntity<>(new Response<>(null, StatusEnum.FAILURE, ex.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
	}
	
}
