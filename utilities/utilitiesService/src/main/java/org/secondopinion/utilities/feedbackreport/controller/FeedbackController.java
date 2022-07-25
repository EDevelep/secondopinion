package org.secondopinion.utilities.feedbackreport.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.google.gson.Gson;
import org.secondopinion.request.Response;
import org.secondopinion.request.Response.StatusEnum;
import org.secondopinion.utilities.feedbackreport.dto.FeedbackLoop;
import org.secondopinion.utilities.feedbackreport.service.impl.FeedbackLoopService;
import org.secondopinion.utilities.jobs.dto.FeedbackRequestDTO;
import org.secondopinion.utils.JSONHelper;

import io.swagger.annotations.ApiOperation;

@RequestMapping("/feedback")
@RestController
public class FeedbackController {

	@Autowired
	private FeedbackLoopService feedbackLoopService;

	public FeedbackLoopService getFeedbackLoopService() {
		return feedbackLoopService;
	}

	public void setFeedbackLoopService(FeedbackLoopService feedbackLoopService) {
		this.feedbackLoopService = feedbackLoopService;
	}

	/**
	 * To create a new feedback
	 * 
	 * @param arpCandidateTo
	 * @return
	 */
	@ApiOperation(value = "To create a new feedback")
	@PostMapping(value = "/create", consumes = { MediaType.APPLICATION_JSON_VALUE, MediaType.MULTIPART_FORM_DATA_VALUE,
			MediaType.APPLICATION_OCTET_STREAM_VALUE })
	public ResponseEntity<Response<String>> createFeedback(@RequestParam("feedbackLoop") String feedbackLoopJson,
			@RequestParam(name = "screenshot", required = false) MultipartFile screenshot) {

		StringBuilder builder = new StringBuilder("");
		builder.append("the request body: " + feedbackLoopJson);

		Gson gson = JSONHelper.getGsonWithDate();
		FeedbackLoop feedbackLoop = gson.fromJson(feedbackLoopJson, FeedbackLoop.class);
		try {
			feedbackLoopService.save(feedbackLoop, screenshot);

			return new ResponseEntity<>(new Response<>(null, StatusEnum.SUCCESS, null), HttpStatus.OK);

		} catch (Exception e) {
			return new ResponseEntity<>(new Response<>(null, StatusEnum.FAILURE, e.getMessage()),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	/**
	 * To fetch all feedbacks
	 * 
	 * @param arpCandidateTo
	 * @return
	 */
	@ApiOperation(value = "To fetch the feedbacks")
	@PostMapping("/getallfeedbackbySearch")
	public ResponseEntity<Response<List<FeedbackLoop>>> getAllFeedBackLoopsByStatusUSerCompany(
			@RequestBody FeedbackRequestDTO feedbackRequestDTO) {
		try {
			List<FeedbackLoop> feedbacks = feedbackLoopService
					.getAllFeedBackLoopsByStatusUSerCompany(feedbackRequestDTO);

			return new ResponseEntity<>(new Response<>(feedbacks, StatusEnum.SUCCESS, null), HttpStatus.OK);

		} catch (Exception e) {
			return new ResponseEntity<>(new Response<>(null, StatusEnum.FAILURE, e.getMessage()),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@ApiOperation(value = "To fetch the feedbacks")
	@PostMapping("/getallfeedback")
	public ResponseEntity<Response<List<FeedbackLoop>>> getAllFeedBack() {
		try {
			List<FeedbackLoop> feedbacks = (List<FeedbackLoop>) feedbackLoopService.getAllFeedBackLoops();

			return new ResponseEntity<>(new Response<>(feedbacks, StatusEnum.SUCCESS, null), HttpStatus.OK);

		} catch (Exception e) {
			return new ResponseEntity<>(new Response<>(null, StatusEnum.FAILURE, e.getMessage()),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@ApiOperation(value = "To fetch the feedbacks")
	@PostMapping("/getFeedBackLoopById")
	public ResponseEntity<Response<FeedbackLoop>> getFeedBackLoopById(@RequestParam Long feedBackId) {
		try {
			FeedbackLoop feedbacks =  feedbackLoopService.getFeedBackLoopById(feedBackId);

			return new ResponseEntity<>(new Response<>(feedbacks, StatusEnum.SUCCESS, null), HttpStatus.OK);

		} catch (Exception e) {
			return new ResponseEntity<>(new Response<>(null, StatusEnum.FAILURE, e.getMessage()),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
