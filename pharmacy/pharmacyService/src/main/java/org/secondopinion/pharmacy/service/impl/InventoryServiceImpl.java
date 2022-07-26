package org.secondopinion.pharmacy.service.impl;

import java.util.List;

import org.secondopinion.pharmacy.dao.InventoryDAO;
import org.secondopinion.pharmacy.dto.Inventory;
import org.secondopinion.pharmacy.service.InventoryService;
import org.secondopinion.pharmacy.service.rest.PharamcyElasticSerchRestAPIService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class InventoryServiceImpl implements InventoryService {
	@Autowired
	private InventoryDAO inventoryDAO;
	@Autowired
	private PharamcyElasticSerchRestAPIService pharamcyElasticSerchRestAPIService;

	@Override
	@Transactional
	public void saveInventory(Inventory inventory) {
		
			inventoryDAO.save(inventory);
		}

	

	@Override
	@Transactional
	public Inventory getAllInventory(Long inventoryId) {
		return inventoryDAO.findById(inventoryId);
	}

	@Override
	@Transactional
	public List<Inventory> getAllInventoryByPharmacyId(Long pharmacyId) {

		return inventoryDAO.getAllInventoryByPharmacyId(pharmacyId);
	}

	private Boolean checkMedication(String drugname) {
		Inventory inventory = pharamcyElasticSerchRestAPIService.getMedication(drugname);
		if (!inventory.getDrugname().equals(drugname)) {
			return false;
		}
		return true;

	}

}
