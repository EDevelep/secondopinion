package org.secondopinions.elasticsearch.serviceImpl;

import java.util.List;
import java.util.UUID;

import org.elasticsearch.index.query.MatchQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.secondopinions.elasticsearch.model.Medicaltest;
import org.secondopinions.elasticsearch.repository.MedicaltestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MedicaltestService {

	@Autowired
	private MedicaltestRepository medicaltestRepository;

	public List<Medicaltest> search(String keywords) {
		MatchQueryBuilder searchdoctor = QueryBuilders.matchQuery("testName", keywords);
		List<Medicaltest> medicaltests = this.medicaltestRepository.search(searchdoctor);
		return medicaltests;
	}
	
	
	public void saveMedicalTests(Medicaltest medicaltest) {
		 medicaltest.setId(UUID.randomUUID().toString());
		 medicaltestRepository.save(medicaltest);
	}
}
