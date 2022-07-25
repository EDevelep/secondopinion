package org.secondopinions.elasticsearch.controller;

import java.util.List;



import org.secondopinions.elasticsearch.dto.Response;
import org.secondopinions.elasticsearch.dto.Response.StatusEnum;
import org.secondopinions.elasticsearch.model.Medicine;
import org.secondopinions.elasticsearch.serviceImpl.MedicineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;

import io.swagger.annotations.Api;

@RestController
@Api("/")
@CrossOrigin(origins = "*")
@RequestMapping(value = "/elasticsearchMedicinecontroller")
public class MedicineController {
	@Autowired
	private MedicineService medicineService;

	@PostMapping("/saveMedicine")
	public ResponseEntity<Response<String>> saveMedicine(@RequestBody Medicine medicine) {
		try {
			medicineService.saveMedicine(medicine);
			return new ResponseEntity<>(new Response<>("Medicine Saved ", StatusEnum.SUCCESS, null), HttpStatus.OK);
		} catch (IllegalArgumentException e) {
			return new ResponseEntity<>(new Response<>(null, StatusEnum.FAILURE, e.getMessage()),
					HttpStatus.BAD_REQUEST);
		}

	}

	@GetMapping("/find/allMedicine/byName/{medicineName}")
	public ResponseEntity<Response<List<Medicine>>> findAllMedicineByName(
			@PathVariable("medicineName") String medicineName) {
		try {
			List<Medicine> medicineDTO = medicineService.search(medicineName);
			return new ResponseEntity<>(new Response<>(medicineDTO, StatusEnum.SUCCESS, null), HttpStatus.OK);
		} catch (IllegalArgumentException e) {
			return new ResponseEntity<>(new Response<>(null, StatusEnum.FAILURE, e.getMessage()),
					HttpStatus.BAD_REQUEST);
		}

	}

	@GetMapping("findmedicineByName/{medicineName}")
	public ResponseEntity<Response<String>> findmedicineByName(@PathVariable("medicineName") String medicineName) {
		try {
			Gson gson = new Gson();
			Medicine medicineDTO = medicineService.findMedicineByName(medicineName);
			String medicine=gson.toJson(medicineDTO);
			return new ResponseEntity<>(new Response<>(medicine, StatusEnum.SUCCESS, null),
					HttpStatus.OK);
		} catch (IllegalArgumentException e) {
			return new ResponseEntity<>(new Response<>(null, StatusEnum.FAILURE, e.getMessage()),
					HttpStatus.BAD_REQUEST);
		}

	}
}
