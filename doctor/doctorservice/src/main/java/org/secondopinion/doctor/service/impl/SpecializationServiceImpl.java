package org.secondopinion.doctor.service.impl;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.secondopinion.doctor.dao.SpecializationDAO;
import org.secondopinion.doctor.dto.Specialization;
import org.secondopinion.doctor.service.SpecializationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class SpecializationServiceImpl implements SpecializationService {

	// crete concurent hash map and get the value and set into the map

	private Map<Long, Specialization> specialization = new ConcurrentHashMap<>();
	@Autowired
	private SpecializationDAO specializationDAO;

	@Override
	@Transactional(readOnly = true)
	public List<Specialization> fetchAllSpecialization() {
		Collection<Specialization> dbspeSpecializations = specializationDAO.findAll();
		specialization = dbspeSpecializations.stream()
				.collect(Collectors.toMap(Specialization::getSpecializationId, sp -> sp));
		return specialization.entrySet().stream().map(sp -> sp.getValue()).collect(Collectors.toList());
	}

	@Override
	@Transactional
	public Specialization fetchSpecializationByName(String name) {
		return specializationDAO.findOneByProperty(Specialization.FIELD_specializations, name);
	}

	@Override
	@Transactional
	public Specialization fetchSpecializationById(Long drugId) {
		if (specialization.isEmpty()) {
			return specializationDAO.findOneByProperty(Specialization.FIELD_specializationId, drugId);
		}
		return specialization.get(drugId);
	}

	@Override
	@Transactional
	public List<Specialization> fetchSpecializationByIds(List<Long> drugIds) {
		if (specialization.isEmpty()) {
			specializationDAO.findByPropertyValues(Specialization.FIELD_specializationId, drugIds);
		}
		return drugIds.stream().map(di -> specialization.get(di)).collect(Collectors.toList());
	}

}
