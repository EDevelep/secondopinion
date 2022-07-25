package org.secondopinion.pharmacy.service;

import java.util.List;

import org.secondopinion.pharmacy.dto.Inventory;

public interface InventoryService {

	void saveInventory(Inventory inventory);
	Inventory  getAllInventory(Long inventoryId );
	
	List<Inventory>  getAllInventoryByPharmacyId(Long PharmacyId );
}
