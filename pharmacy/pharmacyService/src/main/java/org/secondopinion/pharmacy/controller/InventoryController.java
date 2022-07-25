package org.secondopinion.pharmacy.controller;

import java.util.List;

import org.secondopinion.pharmacy.dto.Inventory;
import org.secondopinion.pharmacy.dto.Pharmacy;
import org.secondopinion.pharmacy.exception.PharmacyServerException;
import org.secondopinion.pharmacy.service.InventoryService;
import org.secondopinion.request.Response;
import org.secondopinion.request.Response.StatusEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/inventoryController")
public class InventoryController {

	@Autowired
	private InventoryService inventoryService;

	@PostMapping(value = "/saveinventory")
	public ResponseEntity<Response<String>> saveInventory(@RequestBody Inventory inventory) {
		try {
			inventoryService.saveInventory(inventory);
			return new ResponseEntity<>(new Response<>("Inventory data saved successfully", StatusEnum.SUCCESS,
					"Inventory data saved successfully"), HttpStatus.OK);
		} catch (IllegalArgumentException e) {
			return new ResponseEntity<>(new Response<>(null, StatusEnum.FAILURE, e.getMessage()),
					HttpStatus.BAD_REQUEST);
		} catch (Exception e) {
			throw new PharmacyServerException(e.getMessage(), e);
		}
	}

	@GetMapping(value = "/id/{pharmacyId}")
	public ResponseEntity<Response<List<Inventory>>> getAllInventoryByPharmacyId(
			@PathVariable("pharmacyId") Long pharmacyId) {
		try {
			List<Inventory> inventories = inventoryService.getAllInventoryByPharmacyId(pharmacyId);
			return new ResponseEntity<>(
					new Response<>(inventories, StatusEnum.SUCCESS, "Inventory data Get successfully"), HttpStatus.OK);
		} catch (IllegalArgumentException e) {
			return new ResponseEntity<>(new Response<>(null, StatusEnum.FAILURE, e.getMessage()),
					HttpStatus.BAD_REQUEST);
		} catch (Exception e) {
			throw new PharmacyServerException(e.getMessage(), e);
		}
	}

	@GetMapping(value = "/id/{inventoryId}")
	public ResponseEntity<Response<Inventory>> getAllInventory(@PathVariable("inventoryId") Long inventoryId) {
		try {
			Inventory inventories = inventoryService.getAllInventory(inventoryId);
			return new ResponseEntity<>(
					new Response<>(inventories, StatusEnum.SUCCESS, "Inventory data Get successfully"), HttpStatus.OK);
		} catch (IllegalArgumentException e) {
			return new ResponseEntity<>(new Response<>(null, StatusEnum.FAILURE, e.getMessage()),
					HttpStatus.BAD_REQUEST);
		} catch (Exception e) {
			throw new PharmacyServerException(e.getMessage(), e);
		}
	}

}
