package org.secondopinion.doctor.controller;

import java.util.List;
import org.secondopinion.doctor.domain.DoctorRatingDTO;
import org.secondopinion.doctor.dto.Doctorratings;
import org.secondopinion.doctor.exception.DoctorServerException;
import org.secondopinion.doctor.service.IDoctorratings;
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
@RequestMapping("/rating")
public class DoctorrRatingController {
	@Autowired
	private IDoctorratings ratingsService;

	@PostMapping("/save/rating")
	public ResponseEntity<Response<String>> saveRatings(@RequestBody Doctorratings ratings) {
		try {
			ratingsService.saveRatings(ratings);
			return new ResponseEntity<>(new Response<>(null, StatusEnum.SUCCESS, null), HttpStatus.OK);
		} catch (IllegalArgumentException e) {
			return new ResponseEntity<>(new Response<>(null, StatusEnum.FAILURE, null), HttpStatus.BAD_REQUEST);
		} catch (Exception e) {
			throw new DoctorServerException(e.getMessage(), e);
		}

	}

	@PostMapping("/doctorratingBYserchcritera")
	public ResponseEntity<Response<List<Doctorratings>>> getDoctorratingSerchCritera(
			@RequestBody DoctorRatingDTO doctorRatingDTO) {
		try {
			Response<List<Doctorratings>> docMap = ratingsService.getDoctorratingSerchCritera(doctorRatingDTO);
			return new ResponseEntity<>(docMap, HttpStatus.OK);
		} catch (IllegalArgumentException e) {
			return new ResponseEntity<>(new Response<>(null, StatusEnum.FAILURE, null), HttpStatus.BAD_REQUEST);
		} catch (Exception e) {
			throw new DoctorServerException(e.getMessage(), e);
		}

	}

	@GetMapping("/get/{doctorId}")
	public ResponseEntity<Response<List<Doctorratings>>> getRatingByid(@PathVariable("doctorId") Long doctorId) {
		try {

			List<Doctorratings> ratings = ratingsService.getDoctorratingsByid(doctorId);

			return new ResponseEntity<>(new Response<>(ratings, StatusEnum.SUCCESS, null), HttpStatus.OK);
		} catch (IllegalArgumentException e) {
			return new ResponseEntity<>(new Response<>(null, StatusEnum.FAILURE, e.getMessage()),
					HttpStatus.BAD_REQUEST);
		} catch (Exception e) {
			throw new DoctorServerException(e.getMessage(), e);
		}

	}

	@GetMapping("/getRatingBydoctorid/{doctorId}")
	public ResponseEntity<Response<String>> getRatingBydoctorid(@PathVariable("doctorId") List<Long> doctorId) {
		try {
			Gson gson = JSONHelper.getGsonWithDate();
			List<Doctorratings> ratings = ratingsService.getDoctorratingsByid(doctorId);

			return new ResponseEntity<>(new Response<>(gson.toJson(ratings), StatusEnum.SUCCESS, null), HttpStatus.OK);
		} catch (IllegalArgumentException e) {
			return new ResponseEntity<>(new Response<>(null, StatusEnum.FAILURE, e.getMessage()),
					HttpStatus.BAD_REQUEST);
		} catch (Exception e) {
			throw new DoctorServerException(e.getMessage(), e);
		}

	}

}
