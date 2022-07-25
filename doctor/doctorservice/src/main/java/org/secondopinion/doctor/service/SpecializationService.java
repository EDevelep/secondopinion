package org.secondopinion.doctor.service;

import java.util.List;

import org.secondopinion.doctor.dto.Specialization;

public interface SpecializationService {
	List<Specialization> fetchAllSpecialization();

	Specialization fetchSpecializationByName(String name);

	Specialization fetchSpecializationById(Long drugId);

	List<Specialization> fetchSpecializationByIds(List<Long> drugIds);

}
