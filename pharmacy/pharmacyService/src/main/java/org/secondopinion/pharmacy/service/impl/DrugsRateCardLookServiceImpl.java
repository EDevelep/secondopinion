package org.secondopinion.pharmacy.service.impl;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

import org.secondopinion.pharmacy.dao.DruglookupDAO;
import org.secondopinion.pharmacy.domain.BaseDruglookup;
import org.secondopinion.pharmacy.dto.Druglookup;
import org.secondopinion.pharmacy.service.IDrugsRateCardLookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class DrugsRateCardLookServiceImpl implements IDrugsRateCardLookService {

	@Autowired
	private DruglookupDAO drugLookupDAO;
	
	private Map<Long, Druglookup> drugLookupMap = new ConcurrentHashMap<>();

	@Override
	@Transactional(readOnly=true)
	public List<Druglookup> fetchAllDrugs() {
		if(drugLookupMap.isEmpty()) {
			Collection<Druglookup> drugs = drugLookupDAO.findAll();
			drugLookupMap = drugs.stream().collect(Collectors.toMap(Druglookup::getDrugid, dl -> dl));
		}
		return drugLookupMap.entrySet().stream().map(es -> es.getValue()).collect(Collectors.toList());
	}

	@Override
	public Druglookup fetchDrugByName(String name) {
		return drugLookupDAO.findOneByProperty(BaseDruglookup.FIELD_drugname, name);
	}

	@Override
	@Transactional(readOnly=true)
	public Druglookup fetchDrugById(Long drugId) {
		if(drugLookupMap.isEmpty()) {
			return drugLookupDAO.findOneByProperty(BaseDruglookup.FIELD_drugid, drugId);
		}
		return drugLookupMap.get(drugId);
	}

	@Override
	@Transactional(readOnly=true)
	public List<Druglookup> fetchDrugByIds(List<Long> drugIds) {
		
		if(drugLookupMap.isEmpty()) {
			return drugLookupDAO.findByPropertyValues(BaseDruglookup.FIELD_drugid, drugIds);
		}
		return drugIds.stream().map(di -> drugLookupMap.get(di)).collect(Collectors.toList());
		
	}

	@Override
	@Transactional
	public List<Druglookup> fetchDrugByPharmacyId(Long pharmacyId) {
		
		return drugLookupDAO.fetchDrugByPharmacyId(pharmacyId);
	}

}
