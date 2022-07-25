package org.secondopninion.servicetest;

import static org.junit.Assert.assertNotNull;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import org.junit.Test;
import org.secondopinion.pharmacy.dto.Druglookup;
import org.secondopinion.pharmacy.service.IDrugsRateCardLookService;
import org.secondopninion.PharmacyApplactionTest;
import org.springframework.beans.factory.annotation.Autowired;

public class DrugsRateCardLookServiceImplTest extends PharmacyApplactionTest {

	@Autowired
	private IDrugsRateCardLookService iDrugsRateCardLookService;

	@Test
	public void testFetchDrugByName() {
		Druglookup druglookup = iDrugsRateCardLookService.fetchDrugByName("cipla");
		assertNotNull(druglookup);
	}

	@Test
	public void testFetchAllDrugs() {
		Map<Long, Druglookup> druglookups = (Map<Long, Druglookup>) iDrugsRateCardLookService.fetchAllDrugs();
		assertNotNull(druglookups);
	}

	@Test
	public void testFetchDrugById() {
		Druglookup druglookup = iDrugsRateCardLookService.fetchDrugById(101L);
		assertNotNull(druglookup);

	}

	@Test
	public void testFetchDrugByIds() {	
		List<Long> dugids = new ArrayList<Long>();
		dugids.add(101L);
		dugids.add(102L);
		List<Druglookup> druglookups = iDrugsRateCardLookService.fetchDrugByIds(dugids);
		assertNotNull(druglookups);
	}





}
