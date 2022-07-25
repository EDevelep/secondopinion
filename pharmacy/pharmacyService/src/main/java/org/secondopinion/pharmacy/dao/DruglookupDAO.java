package org.secondopinion.pharmacy.dao;

import java.util.List;

import org.secondopinion.dao.IDAO;
import org.secondopinion.pharmacy.dto.Druglookup;

public interface DruglookupDAO extends IDAO<Druglookup,Long >{

	List<Druglookup> fetchDrugByPharmacyId(Long pharmacyId);
}