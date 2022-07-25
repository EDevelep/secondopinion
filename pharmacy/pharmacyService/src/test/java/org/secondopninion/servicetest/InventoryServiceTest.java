package org.secondopninion.servicetest;

import java.util.Date;

import org.junit.Test;
import org.secondopinion.pharmacy.dto.Inventory;
import org.secondopinion.pharmacy.service.InventoryService;
import org.secondopninion.PharmacyApplactionTest;
import org.springframework.beans.factory.annotation.Autowired;

public class InventoryServiceTest extends PharmacyApplactionTest {

	@Autowired
	private InventoryService inventoryService;

	@Test
	public void saveInventory() {
		Inventory inventory = new Inventory();
		inventory.setActive('Y');
		inventory.setAlternate("dulo");
		inventory.setAvillabe(30L);
		inventory.setDrugform("cipla");
		inventory.setDrugname("dulo 250 mg");
		inventory.setDrugtype("Medication");
		inventory.setExpdate(new Date());
		inventory.setMfgdate(new Date());
		inventory.setInjectable("N");
		inventory.setPrice(20.00);
		inventory.setPotency("NCHL");
		inventory.setManufacturer("cipla pvt ltd");
		inventory.setPharmacyId(2L);
		inventory.setQuantityavailable(15L);
		inventoryService.saveInventory(inventory);

	}

}
