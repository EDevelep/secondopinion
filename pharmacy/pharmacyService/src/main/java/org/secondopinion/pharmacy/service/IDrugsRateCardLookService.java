package org.secondopinion.pharmacy.service;

import java.util.List;

import org.secondopinion.pharmacy.dto.Druglookup;

public interface IDrugsRateCardLookService {

	List<Druglookup> fetchAllDrugs();

	Druglookup fetchDrugByName(String name);

	List<Druglookup> fetchDrugByPharmacyId(Long pharmacyId);
	Druglookup fetchDrugById(Long drugId);

	List<Druglookup> fetchDrugByIds(List<Long> drugIds);


}
