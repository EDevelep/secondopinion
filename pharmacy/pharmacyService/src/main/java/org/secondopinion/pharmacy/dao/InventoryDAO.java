package org.secondopinion.pharmacy.dao;

import java.util.List;

import org.secondopinion.dao.IDAO;
import org.secondopinion.pharmacy.dto.Inventory;

public interface InventoryDAO extends IDAO<Inventory,Long >{

	List<Inventory> getAllInventoryByPharmacyId(Long pharmacyId);
}