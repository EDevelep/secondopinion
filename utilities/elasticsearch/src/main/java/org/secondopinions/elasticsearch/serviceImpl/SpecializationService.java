package org.secondopinions.elasticsearch.serviceImpl;

import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.UUID;
import java.util.stream.Collectors;

import org.elasticsearch.index.query.MatchQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.secondopinions.elasticsearch.model.Doctor;
import org.secondopinions.elasticsearch.model.Specialization;
import org.secondopinions.elasticsearch.repository.SpecializationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.common.collect.ImmutableList;

@Service
public class SpecializationService {

	@Autowired
	private SpecializationRepository specializationRepository;

	public String saveSpecialization(Specialization specialization) {

		Specialization dbspecialization = getspSpecialization(specialization.getDoctorspecialization());
		if (Objects.isNull(dbspecialization)) {
			specialization.setCount(1);
			specialization.setId(UUID.randomUUID().toString());
			specializationRepository.save(specialization);
		} else {

			int count = dbspecialization.getCount();
			dbspecialization.setCount(count + 1);
			dbspecialization.setId(dbspecialization.getId());
			specializationRepository.save(dbspecialization);
		}

		return "Specialization Saved SuccesFully.";
	}

	public Specialization getspSpecialization(String specialization) {
		return specializationRepository.findBydoctorspecialization(specialization);
	}

	public List<Specialization> findAll() {

		List<Specialization> specializations = ImmutableList.copyOf(specializationRepository.findAll());
		return specializations;
	}

	public List<String> searchSpecialization(String specialization) {
		MatchQueryBuilder searchdoctor = QueryBuilders.matchQuery("doctorspecialization", specialization);
		List<Specialization> specializations = this.specializationRepository.search(searchdoctor);

		List<String> specializationsName = specializations.stream().map(Specialization::getDoctorspecialization)
				.collect(Collectors.toList());

		return specializationsName;
	}

	public String delete(String id) {
		specializationRepository.deleteById(id);
		return "data delted";
	}
}
